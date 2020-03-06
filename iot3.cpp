#define LED_PIN 6
#define BUTTON_BLUE_PIN 2
#define BUTTON_RED_PIN 3

int counter = 0; 

bool lastBtnBlueState = LOW; 
bool lastBtnRedState = LOW; 
String wiadomosc = "";
bool turnlight = false;
bool isBlinked = false;
unsigned long timeStart = 0;
unsigned long towait = 0;

void setup()
{
    Serial.begin(9600);
    pinMode(BUTTON_BLUE_PIN, INPUT_PULLUP); //przycisk jako wejście
    Serial.println("Give message!");
    pinMode(LED_PIN, OUTPUT);
}

void loop()
{
  if(turnlight==true){
    digitalWrite(LED_PIN, HIGH);
  }else{
    digitalWrite(LED_PIN, LOW);
  }
  
  if(((millis()-timeStart)>towait)&&towait!=0&&isBlinked){
    turnlight = false;
    isBlinked = false;
  }
  if(Serial.available() > 0) { //Czy Arduino odebrało dane
    wiadomosc = Serial.readStringUntil('\n'); 
        Serial.println("message is recieved!");
        Serial.println(wiadomosc);

    if(wiadomosc == "LED ON"){
      turnlight = true;
      wiadomosc = "";
    }else if(wiadomosc == "LED OFF"){
      turnlight = false; 
      wiadomosc = "";
    }else if(wiadomosc.substring(0,9) == "LED BLINK"){
              
      if(wiadomosc.length()==9){
           turnlight = true;
           isBlinked = true;
          timeStart = millis();
      }
      else if(wiadomosc.charAt(10) == '<' && wiadomosc.charAt(wiadomosc.length()-1) == '>'){
         
         String numb = wiadomosc.substring(11,wiadomosc.length()-1);
          timeStart = millis();
          towait = numb.toInt();
          turnlight = true; 
          isBlinked = true;
      }
      wiadomosc = "";
    }
  }  

}
