#include <Wire.h> 
#include <LiquidCrystal_I2C.h>
LiquidCrystal_I2C lcd(0x27, 2, 1, 0, 4, 5, 6, 7, 3, POSITIVE);
#define BUTTON_GREEN 4
#define BUTTON_RED 2
#define LED_RED 6

int counter = 5;

void setup() {
  // put your setup code here, to run once:
  pinMode(LED_RED, OUTPUT);
  lcd.begin(16,2);
  lcd.clear();
  pinMode(LED_RED, OUTPUT);
  pinMode(BUTTON_GREEN, INPUT_PULLUP);
  pinMode(BUTTON_RED, INPUT_PULLUP);
}

void loop() {
  // put your main code here, to run repeatedly:
  lcd.clear();
  lcd.print(counter);
  delay(100);
  
  if(digitalRead(BUTTON_GREEN) == LOW){
    delay(40);
    if(digitalRead(BUTTON_GREEN) == LOW){
      counter++;
      counter++;
    }
  }
  
  if(digitalRead(BUTTON_RED) == LOW){
    delay(40);
    if(digitalRead(BUTTON_RED) == LOW){
      if(counter > 0){
        counter--;
        counter--;
      }
    }
  }
  if(digitalRead(BUTTON_GREEN) == LOW && digitalRead(BUTTON_RED) == LOW){
    delay(100);
  if(digitalRead(BUTTON_GREEN) == LOW && digitalRead(BUTTON_RED) == LOW){
    for ( int i = 0; i < counter; i++){
      digitalWrite(LED_RED, HIGH);
      delay(100);
      digitalWrite(LED_RED, LOW);
      delay(100);
    }
  }
  }
}
