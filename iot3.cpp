#define LED_PIN 13
#define BUTTON_BLUE_PIN 2
#define BUTTON_RED_PIN 3

int counter = 0; 

bool lastBtnBlueState = LOW; 
bool lastBtnRedState = LOW; 
String wiadomosc = "";
bool turnlight = false;
unsigned long timeStart = 0;
unsigned long towait = 0;

void setup()
{
  	Serial.begin(9600);
  	pinMode(BUTTON_BLUE_PIN, INPUT_PULLUP); //przycisk jako wejście
  	Serial.println("Wcisnij przycisk!");
  	pinMode(LED_PIN, OUTPUT);
}

void loop()
{
  if(turnlight){
    digitalWrite(LED_PIN, HIGH);
  }else{
    digitalWrite(LED_PIN, LOW);
  }
  
  if(((millis()-timeStart)>towait)&&towait!=0){
    turnlight = false;
  }
  if(Serial.available() > 0) { //Czy Arduino odebrało dane
    wiadomosc = Serial.readStringUntil('\n'); 
    if(wiadomosc=="LED ON"){
      turnlight = true;
    }else if(wiadomosc=="LED OFF"){
      turnlight = false; 
    }else if(wiadomosc.substring(0,9)=="LED BLINK"){
      if(wiadomosc.length()==9){
           turnlight = true;
      }
      else if(wiadomosc.charAt(11)=='<'&&wiadomosc.charAt(wiadomosc.length()-1)=='>'){
         String numb = wiadomosc.substring(12,wiadomosc.length()-2);
        	timeStart = millis();
        	towait = numb.toInt();
        	turnlight = true; 
      }
    }
  }  

}
