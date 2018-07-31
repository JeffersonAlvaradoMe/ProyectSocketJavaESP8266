#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>
#include <WiFiUdp.h>

char *ssid="Wifi-Repeater";
char *password="";
int contconexion = 0;//contador para contar los mseg para validar conexion a red wifi
int valor;

unsigned long previousMillis = 0;

//char host[48];
//String strhost = "192.168.10.100";//dir. servidor
const char * host = "192.168.10.101";//DIR DEL SERVIDOR
String chipid = "";
boolean Est_Conect_Red;
String RespServer="";
int Puerto=5000;


char packetBuffer[255]; //buffer to hold incoming packet
char  ReplyBuffer[] = "S: 1 2 3 4 1254 1245 1234";       // a string to send back
int packetNumber=0;

//ESP8266WiFiMulti WiFiMulti;
WiFiUDP Udp;

//PINES DEL MODULO ESP8266, ESTOS DEPENDEN DE LOS GPIOs, HAY 
//ASEGURARSE DE QUE ESTEN CONRRECTOS
/*D0=GPIO16   D1=GPIO5    D2=GPIO4    D3=GPIO0*/


String enviardatostcp(String datos) {
  String linea = "error";
  WiFiClient client;
 // strhost.toCharArray(host, 49);
  if (!client.connect(host, 5010)) {
    Serial.println("Fallo de conexion");
    return linea;
  }

   client.println(datos);                    
  //delay(10);             
 
  
 /* unsigned long timeout = millis();
  while (client.available() == 0) {
    if (millis() - timeout > 100) {//le cmabie de 100 a 10
      Serial.print("Cliente fuera de tiempo!");
      client.stop();
      return linea;
    }
  }*/
  // Lee todas las lineas que recibe del servidro y las imprime por la terminal serial
  while(client.available()){
    linea = client.readStringUntil('\r');
  }  
 // Serial.println(linea);
  return linea;
}

//FUNCION PARA CONECTAR A LA RED WIFI
boolean coneccion_red_wifi(char *ssid, char *pass){
  WiFi.mode(WIFI_STA);//modo para recibir ip del dhcp
  WiFi.begin(ssid);
  while (WiFi.status() != WL_CONNECTED and contconexion <50) { //Cuenta hasta 50 si no se puede conectar lo cancela
    ++contconexion;
    delay(500);
    Serial.print(".");
  }
  if (contconexion <50) {
    /*
      //para usar con ip fija
      IPAddress ip(192,168,10,110); 
      IPAddress gateway(192,168,10,1); 
      IPAddress subnet(255,255,255,0); 
      WiFi.config(ip, gateway, subnet); 
      */
      Serial.println("");
      Serial.println("WiFi conectado");
      Serial.println(WiFi.localIP());
      return true;
  }
  else { 
      Serial.println("");
      Serial.println("Error de conexion a la red");
      return false;
  }
 
  }

void setup() {
    // Inicia Serial
  Serial.begin(115200);
  Serial.println("");

  Serial.print("chipId: "); 
  chipid = String(ESP.getChipId());
  Serial.println(chipid); 
/*D0=GPIO16   D1=GPIO5    D2=GPIO4    D3=GPIO0*/
   pinMode(16, INPUT); // Setup for leads off detection LO +
  pinMode(5, INPUT); // Setup for leads off detection LO -
  pinMode(4, OUTPUT);//Led indicador de conexion a la red
  pinMode(2, OUTPUT);//led indicador de conexion establecida con socket
   //Si se establecio conexion a la RED entonces q se prenda led de conexion de red osea D2

   Est_Conect_Red = coneccion_red_wifi(ssid, password);
  if(Est_Conect_Red==true){
    }else{
      digitalWrite(4, LOW);
      }

}

void loop() {
  if((digitalRead(16) == 1)||(digitalRead(5) == 1)){
    Serial.println('!');
    // RespServer=enviardatostcp(String(0)+"\n");
     int n=0;
     char msg[30];
      Udp.beginPacket(host,Puerto);
         if(packetNumber<255) packetNumber ++;
         else packetNumber=0;
         for(int i=0; i<11; i++){
           n=n+1;
         sprintf(msg, "%d",0 );//funcion para convertir el entero a una cadena de caracteres
          Serial.print(n+ "   ");
           Serial.println(msg);
           Udp.write(msg);
           Udp.endPacket();
         }
           
  }
  else{
    int val = analogRead(A0);
   // RespServer=enviardatostcp(String(val)+"\n");
    // send the value of analog input 0:
      Serial.println(val);
     
     // int n=0;
      
      char msg[30];
      Udp.beginPacket(host,Puerto);
         if(packetNumber<255) packetNumber ++;
         else packetNumber=0;
         //for(int i=0; i<11; i++){
         //  n=n+1;
         sprintf(msg, "%d", val);//funcion para convertir el entero a una cadena de caracteres
          Serial.print(val+ "   ");
           Serial.println(msg);
           Udp.write(msg);
           Udp.endPacket();
        // time0=micros();
         delay(10);
      
      
         
         
           
        //}
      
     
  }
  //Wait for a bit to keep serial data from saturating
 // delay(10);

}
