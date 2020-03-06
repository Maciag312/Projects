#define BUTTON_PIN 4
bool lastAddBtnState = HIGH;
int time;
int timePassed = 0;
int isPrinted = false;
void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  time = millis(); 
  pinMode(BUTTON_PIN, INPUT_PULLUP);
}

void loop() {
  // put your main code here, to run repeatedly:
   bool currentBtnState = digitalRead(BUTTON_PIN);
   if(lastAddBtnState != currentBtnState){    //wykrywamy zbocze
      if(currentBtnState == LOW) {
        isPrinted = false;
        timePassed = millis() - time;
      }
  }
  if(currentBtnState == HIGH) {
    time = millis();
    if(!isPrinted) {
      int sec = timePassed / 1000;
      int ms = timePassed - sec * 1000;
      String timeString = "-> 0:00:" + String(sec) + "." + String(ms);
      Serial.println("Przycisk wcisniety przez:" + String(timePassed) + timeString);
      isPrinted = true;
    }
  }
}
