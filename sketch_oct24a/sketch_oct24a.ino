int flex1 = A0; // flex sensor is connected with analog pin A0
int data1 = 0;
int data2 =0;
int flex2 = A1; 
void setup()
{
  Serial.begin(9600); 
  pinMode(flex1, INPUT);
  pinMode(flex2, INPUT);
}

void loop()
{
  data1 = analogRead(flex1);
  data2 = analogRead(flex2);
  String d1=String(data1);
  String d2=String(data2);
  
  Serial.println(d1+"hxhx"+d2);
  delay(500); 
}
