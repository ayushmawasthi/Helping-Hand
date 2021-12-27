#include<SoftwareSerial.h>
#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <WiFiClient.h>
const char* ssid="FTTH";
const char* password = "ayush@1234";
const char *URL = "http://myvoice.atwebpages.com/test.php";
WiFiClient client;
HTTPClient httpClient;
SoftwareSerial myserial (D1,D2);

void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);
  myserial.begin(9600);
  WiFi.begin(ssid, password);
    while (WiFi.status() != WL_CONNECTED) {
        delay(500);
        Serial.print(".");
    }

}

void loop() {
  // put your main code here, to run repeatedly:
  String msg = myserial.readStringUntil('\r');
  Serial.print(msg);
  
  
  httpClient.begin(client, URL);
    httpClient.addHeader("Content-Type", "application/x-www-form-urlencoded");
    httpClient.POST(msg);
    String content = httpClient.getString();
    httpClient.end();
    Serial.println(content);
    delay(500);

}
