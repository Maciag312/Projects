#define BUTTON_BLUE 6
#define BUTTON_RED 7

#include <LiquidCrystal.h>
LiquidCrystal lcd(12, 11, 5, 4, 3, 2);
float holder = 0; 

bool lastRedState = HIGH; 
bool curRedState = HIGH;
bool lastBlueState = HIGH; 
bool curBlueState = HIGH;
bool backlight = true; 

String wiadomosc = "";

String buffor[10] = {};
int lineNumber = 0;
int cursorLine = 0;
int textScrollPositon[2] = {0,0}; // first and second row describe text align respectivly  
int bufforSize[10] = {};

void writeToLine(String line,int length, int linePositon){
  buffor[linePositon] = line;
  bufforSize[linePositon] = length;
  lineNumber = linePositon;
}


void writeAndGoNext(String line,int length){
  writeToLine(line, length, lineNumber);
  lineNumber++;
  if(lineNumber>9)
    lineNumber = 0;
}

void setup() {
  Serial.begin(9600);
  lcd.begin(16, 2);
  pinMode(BUTTON_BLUE, INPUT_PULLUP);  
  pinMode(BUTTON_RED, INPUT_PULLUP);     
  
}

void loop() { 
  lcd.setCursor(0, 0);
  for(int icurs = 0; icurs<2;icurs++){
    
    lcd.print(buffor[cursorLine+icurs].substring(textScrollPositon[icurs], textScrollPositon[icurs]+16));
    if(buffor[cursorLine+icurs].length()<17){
    	lcd.setCursor(buffor[cursorLine+icurs].length(), icurs);
      for(int i = buffor[cursorLine+icurs].length(); i<17; i++){
        lcd.print(' '); 
      }
    }

    lcd.setCursor(0, 1);
  }
  if(Serial.available() > 0) { //Czy Arduino odebrało dane
    wiadomosc = Serial.readStringUntil('\n'); //Jeśli tak, to odczytaj je do znaku końca linii i zapisz w zmiennej wiadomosc

	writeAndGoNext(wiadomosc, wiadomosc.length()-1); //Wyświetl komunikat
  }  
  
  textScrollPositon[0]++;
  textScrollPositon[1]++;
  if(bufforSize[cursorLine]<14+textScrollPositon[0]){
    textScrollPositon[0] = 0;
  }
  if(bufforSize[cursorLine+1]<14+textScrollPositon[1]){
    textScrollPositon[1] = 0;
  }
  
  curRedState = digitalRead(BUTTON_RED);
  if(lastRedState != curRedState){
    delay(40);
    if(curRedState == LOW){
      cursorLine++;
    }
  }
  lastRedState = curRedState;
  
  curBlueState = digitalRead(BUTTON_BLUE);
  if(lastBlueState != curBlueState){
    delay(40);
    if(curBlueState == LOW){
      cursorLine--;
    }
  }
  lastBlueState = curBlueState;
  if(cursorLine<0)
    cursorLine = 0;
  else if(cursorLine>9)
    cursorLine = 9;
  
  delay(40);



  // set the cursor to (16,1):
  // set the display to automatically scroll:
  // print from 0 to 9:


  // turn off automatic scrolling

  // clear screen for the next loop:
}
