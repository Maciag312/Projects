#define LED_PIN 6
#define BUTTON_BLUE_PIN 2
#define BUTTON_RED_PIN 4

int counter = 0; 

bool lastBtnBlueState = HIGH; 
bool lastBtnRedState = HIGH; 

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
  if((currentBtnRedState == LOW && currentBtnBlueState == LOW)){

    delay(50);
    if((currentBtnRedState == LOW && currentBtnBlueState == LOW)){ // when two buttons are pushed
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
    if(currentBtnBlueState == LOW){
      counter++;
        Serial.print("Red counter: ");
        Serial.println(counter);
    }
  }
  else if(lastBtnRedState!=currentBtnRedState){ //DECREMENT

    delay(50);
       if(currentBtnRedState == HIGH){
      counter--;
        Serial.print("Blue counter: ");
        Serial.println(counter);
       }
  }
  lastBtnBlueState = currentBtnBlueState;
  lastBtnRedState = currentBtnRedState;
}
