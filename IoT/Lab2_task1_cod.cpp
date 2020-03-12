int adc = 1;
float voltage = 0.0;
char data[100];

#define BUTTON 6

#include <LiquidCrystal.h>
LiquidCrystal lcd(12, 11, 5, 4, 3, 2);
float holder = 0; 

bool lastState = HIGH; 
bool curState = HIGH;
bool backlight = true; 
void setup() {
  Serial.begin(9600);
  lcd.begin(16, 2);
  pinMode(BUTTON, INPUT_PULLUP);     

  lcd.print("Miernik A0 ");
}

void loop() {
  if(backlight){
    lcd.backlight(); 
   // Serial.println("backlight is now on");
  }else{
    lcd.noBacklight(); 
    //Serial.println("backlight is turned off");
  }
  curState = digitalRead(BUTTON); 
  if(curState != lastState){
    delay(50);
    if(curState == LOW){
      backlight = !backlight;
    }
  }
  lastState = curState;
  lcd.setCursor(0, 1);
  adc = analogRead(A0);
  voltage = (adc*5)/1024.0;
  sprintf(data, "V=%d.%02d ADC=%d", (int)voltage, (int)(voltage*100)%100, adc);
  lcd.print(data);
  Serial.print(voltage);
  Serial.print(",");//seperator another version is '/t' although here only ',' works
  Serial.print(backlight);
  Serial.println("");
  delay(25);
}
