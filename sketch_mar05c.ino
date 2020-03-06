#define BUTTON_PIN 4
 
//licznik wcisniecia przycisku
int counter = 0;
bool lastBtnState = HIGH;
 
void setup() {
  Serial.begin(9600);
  pinMode(BUTTON_PIN, INPUT_PULLUP); //przycisk jako wejście
  Serial.println("Wcisnij przycisk!");
}
 
void loop() {
  bool currentBtnState = digitalRead(BUTTON_PIN);
  
  if(lastBtnState != currentBtnState){    //wykrywamy zbocze
    if(currentBtnState == LOW){           //przycisk wcisniety
      counter++;
      Serial.print("Liczba nacisniec: ");
      Serial.println(counter);
    }
  }
  lastBtnState = currentBtnState;
}
