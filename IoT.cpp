#define LED_PIN 13
#define BUTTON_BLUE_PIN 2
#define BUTTON_RED_PIN 3

int counter = 0; 

bool lastBtnBlueState = LOW; 
bool lastBtnRedState = LOW; 

void setup()
{
  	Serial.begin(9600);
  	pinMode(BUTTON_BLUE_PIN, INPUT_PULLUP); //przycisk jako wejście
    pinMode(BUTTON_RED_PIN, INPUT_PULLUP); //przycisk jako wejście
  	Serial.println("Wcisnij przycisk!");
  	pinMode(LED_PIN, OUTPUT);
}

void loop()
{
  bool currentBtnBlueState = digitalRead(BUTTON_BLUE_PIN);
  bool currentBtnRedState = digitalRead(BUTTON_RED_PIN);
  
  if((currentBtnRedState == HIGH && currentBtnBlueState == HIGH)){
    delay(50);
    if((currentBtnRedState == HIGH && currentBtnBlueState == HIGH)){ // when two buttons are pushed
      for (int i=0; i < counter; i++){
          digitalWrite(LED_PIN, HIGH);   // turn on 
          delay(200);
          digitalWrite(LED_PIN, LOW);   // turns off led
          delay(200);
      }
    }
  }
  else if(lastBtnBlueState!=currentBtnBlueState ){ //INCREMENT
    delay(50);
    if(currentBtnBlueState == HIGH){
  		counter++;
      	Serial.print("Liczba nacisniec: ");
      	Serial.println(counter);
    }
  }
   lastBtnBlueState = currentBtnBlueState;
 	 if(lastBtnRedState!=currentBtnRedState){ //DECREMENT
    delay(50);
       if(currentBtnRedState == HIGH){
  		counter--;
      	Serial.print("Liczba nacisniec2: ");
      	Serial.println(counter);
       }
  }
  lastBtnRedState = currentBtnRedState;

}
