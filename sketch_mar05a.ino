#define LED_RED 6              
#define LED_GREEN 5
 
#define BUTTON_RED 2
#define BUTTON_GREEN 4

int sekund;
void setup() {
  Serial.begin(9600);
  Serial.println("Wystartowałem");
  
  pinMode(LED_RED, OUTPUT);       //konfigurujemy LED_PIN jako wyjście
  pinMode(LED_GREEN, OUTPUT);
  
  pinMode(BUTTON_RED, INPUT_PULLUP); //przycisk jako wejście
  pinMode(BUTTON_GREEN, INPUT_PULLUP);
}
 
void loop() {
  if(digitalRead(BUTTON_RED) == LOW){
    digitalWrite(LED_RED, HIGH);
    pushRedButton();
  }
  else
    digitalWrite(LED_RED, LOW);    // wyłączamy diodę LED
    Serial.print("Przycisk RED wciśnięty przez ");
    Serial.print(sekund/1000);
    Serial.print(":");
    Serial.println(sekund%1000);
    sekund = 0;

    if(digitalRead(BUTTON_GREEN) == LOW){ //sprawdzamy czy przycisk jest wciśnięty
    digitalWrite(LED_GREEN, HIGH);
    pushGreenButton();
    }
  else
    digitalWrite(LED_GREEN, LOW);
    Serial.print("Przycisk GREEN wciśnięty przez ");
    Serial.print(sekund/60000);
    Serial.print(":");
    Serial.print(sekund/1000);
    Serial.print(":");
    Serial.println(sekund%1000);
    sekund = 0;
    delay(1000);
   
}



void pushRedButton() {
  Serial.begin(9600);
  sekund = 0;
  while(digitalRead(BUTTON_RED) == LOW){
    sekund++;
    delay(1);
  }
}

void pushGreenButton() {
  Serial.begin(9600);
  sekund = 0;
  while(digitalRead(BUTTON_GREEN) == LOW){
    sekund++;
    delay(1);
  }
}
