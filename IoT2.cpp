#define LED_PIN 13
#define BUTTON_BLUE_PIN 2
#define BUTTON_RED_PIN 3

int counter = 0; 

bool lastBtnBlueState = LOW; 
bool lastBtnRedState = LOW; 
unsigned long diftime;
int hours = 0;
int minutes = 0; 
int seconds = 0; 
int miliseconds =0;

void setup()
{
  	Serial.begin(9600);
  	pinMode(BUTTON_BLUE_PIN, INPUT_PULLUP); //przycisk jako wejście
  	Serial.println("Wcisnij przycisk!");
  	pinMode(LED_PIN, OUTPUT);
}

void loop()
{
  bool currentBtnBlueState = digitalRead(BUTTON_BLUE_PIN);
  if(lastBtnBlueState!=currentBtnBlueState ){ //INCREMENT
    delay(50);
     if(currentBtnBlueState == HIGH){
      	diftime = millis();
    }
    else if(currentBtnBlueState == LOW){
  		counter++;
      	diftime =  millis()-diftime;
      Serial.print("Czas: ");
      Serial.println(diftime);
      Serial.print("Czas w formacie: ");
      hours = diftime/(1000*60*60); 
      diftime-=1000*60*60*hours;
      
      minutes = diftime/(1000*60); 
      diftime-=1000*60*minutes;
      
      seconds = diftime/1000; 
      diftime-=1000*minutes;
      
      Serial.print(hours);
      Serial.print(':');
      Serial.print(minutes);
      Serial.print(':');
      Serial.print(seconds);
      Serial.print(':');
      Serial.println(diftime);

    }
  }
   lastBtnBlueState = currentBtnBlueState;

}
