#define BUTTON_ADD 2
#define BUTTON_SUB 4
#define LED_PIN 6

//licznik wcisniecia przycisku
int counter = 0;
bool lastAddBtnState = HIGH;
bool lastSubBtnState = HIGH;

void setup() {
  Serial.begin(9600);
  pinMode(BUTTON_ADD, INPUT_PULLUP); //przycisk jako wejście
  pinMode(BUTTON_SUB, INPUT_PULLUP);
  pinMode(LED_PIN, OUTPUT);
  Serial.println("Wcisnij przycisk!");
}
 
void loop() {
 
bool currentAddBtnState = digitalRead(BUTTON_ADD);
bool currentSubBtnState = digitalRead(BUTTON_SUB);
  
  if(lastAddBtnState != currentAddBtnState){    //wykrywamy zbocze
 
    if(currentSubBtnState == LOW && currentAddBtnState == LOW ){
       for(int i = 0; i < counter; i++) {
    digitalWrite(LED_PIN, HIGH);   // włączamy diodę LED
    delay(1000);                   // czekamy 2 sekundy
    digitalWrite(LED_PIN, LOW);    // wyłączamy diodę LED
    delay(500); 
  }}else if(currentAddBtnState == HIGH){      //przycisk wcisniety
      delay(200);
      if(currentAddBtnState == HIGH){
        delay(200);
          if(currentAddBtnState == HIGH){
      counter++;
      Serial.print("Liczba nacisniec: ");
      Serial.println(counter);
  }
  }
  }
  }else if(lastSubBtnState != currentSubBtnState){    //wykrywamy zbocze
 
    if(currentSubBtnState == LOW && currentAddBtnState == LOW ){
       for(int i = 0; i < counter; i++) {
    digitalWrite(LED_PIN, HIGH);   // włączamy diodę LED
    delay(1000);                   // czekamy 2 sekundy
    digitalWrite(LED_PIN, LOW);    // wyłączamy diodę LED
    delay(500); 
  }}else if(currentSubBtnState == HIGH){      //przycisk wcisniety
      delay(200);
      if(currentSubBtnState == HIGH){
              delay(200);
      if(currentSubBtnState == HIGH){
      counter--;
      Serial.print("Liczba nacisniec: ");
      Serial.println(counter);
  }
      }
  }
  }
  lastAddBtnState = currentAddBtnState;
    lastSubBtnState = currentSubBtnState;
}
