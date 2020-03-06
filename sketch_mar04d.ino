#define BUTTON_ADD 4
#define BUTTON_SUB 2
#define LED_PIN 13

//licznik wcisniecia przycisku
int counter = 0;
bool lastAddBtnState = HIGH;
bool lastSubBtnState = HIGH;
bool isAddPressed = false;
bool isSubPressed =false;
void setup() {
  Serial.begin(9600);
  pinMode(BUTTON_ADD, INPUT_PULLUP); //przycisk jako wejście
  pinMode(BUTTON_SUB, INPUT_PULLUP);
  pinMode(LED_PIN, OUTPUT);
  Serial.println("Wcisnij przycisk!");
}
 
void loop() {
 addButton();
 subButton();
 if(isAddPressed == true && isSubPressed == true) {
  blinkLed();
 }
}

void addButton() {
  bool currentBtnState = digitalRead(BUTTON_ADD);
  if(currentBtnState == LOW) {
        isAddPressed = true;
  }
  if(currentBtnState == HIGH) {
        isAddPressed = false;
  }
  if(lastAddBtnState != currentBtnState){    //wykrywamy zbocze
      if(currentBtnState == LOW && !isSubPressed) {
        counter++;
        Serial.print("Liczba nacisniec: ");
        Serial.println(counter);
      }
  }
  lastAddBtnState = currentBtnState;
}

void subButton() {
  bool currentBtnState = digitalRead(BUTTON_SUB);
  if(currentBtnState == LOW) {
        isSubPressed = true;
  }
  if(currentBtnState == HIGH) {
        isSubPressed = false;
  }
  if(lastSubBtnState != currentBtnState){    //wykrywamy zbocze
    if(currentBtnState == LOW && !isAddPressed){           //przycisk wcisniety
      counter--;
      Serial.print("Liczba nacisniec: ");
      Serial.println(counter);
    }
  }
  lastSubBtnState = currentBtnState;
}

void blinkLed() {
  for(int i = 0; i < counter; i++) {
    digitalWrite(LED_PIN, HIGH);   // włączamy diodę LED
    delay(1000);                   // czekamy 2 sekundy
    digitalWrite(LED_PIN, LOW);    // wyłączamy diodę LED
    delay(500); 
  }
}
