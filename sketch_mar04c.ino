#include <Wire.h> 
#include <LiquidCrystal_I2C.h>
LiquidCrystal_I2C lcd(0x27, 2, 1, 0, 4, 5, 6, 7, 3, POSITIVE);
#define BUTTON_GREEN 4
#define BUTTON_RED 2
#define LED_RED 6

int countTimeRed = 0;
int countTimeGreen = 0;
bool isDown = false;
int suma = 0;

void setup() {
  // put your setup code here, to run once:
  lcd.begin(16,2);
  lcd.clear();
  isDown = false;
  Serial.println("Start");
  pinMode(BUTTON_RED, INPUT_PULLUP);
}

void formatTimeRed(){
  int hours = (int)(countTimeRed/3600000);
  int minutes = (int)((countTimeRed%3600000)/60000);
  int seconds = (int)(((countTimeRed%3600000)%60000)/1000);
  int miliseconds = (int)(((countTimeRed%3600000)%60000)%1000);

  lcd.print(hours);
  lcd.print(":");
  lcd.print(minutes);
  lcd.print(":");
  lcd.print(seconds);
  lcd.print(":");
  lcd.print(miliseconds);
  lcd.print(countTimeRed);
}

void formatTimeGreen(){
  int hours = (int)(countTimeGreen/3600000);
  int minutes = (int)((countTimeGreen%3600000)/60000);
  int seconds = (int)(((countTimeGreen%3600000)%60000)/1000);
  int miliseconds = (int)(((countTimeGreen%3600000)%60000)%1000);

  lcd.print(hours);
  lcd.print(":");
  lcd.print(minutes);
  lcd.print(":");
  lcd.print(seconds);
  lcd.print(":");
  lcd.print(miliseconds);
  lcd.print(countTimeGreen);
}

void sumTime(){
  lcd.print("Suma czasów wynosi:");
  suma = countTimeRed + countTimeGreen;
  lcd.print(suma);
}


void loop() {
  // put your main code here, to run repeatedly:
  if(digitalRead(BUTTON_RED) == LOW && !isDown){
    while(digitalRead(BUTTON_RED) != HIGH){
      delay(1);
      countTimeRed++;
    }
    isDown = true;
  }
  if(digitalRead(BUTTON_GREEN) == LOW && !isDown){
    while(digitalRead(BUTTON_GREEN) != HIGH){
      delay(1);
      countTimeGreen++;
    }
    isDown = true;
  }
  if(formatTimeRed > 0 && formatTimeGreen > 0){
  formatTimeRed();
  delay(1000);
  formatTimeGreen();
  delay(1000);
  sumTime();
  delay(1000);
  }
  lcd.clear();

}
