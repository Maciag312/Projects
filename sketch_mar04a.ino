#define LB 4
unsigned long start;
unsigned long stop;
bool lastStateLB = HIGH;

void setup() {
  Serial.begin(9600);
  pinMode(LB, INPUT_PULLUP);
}
void loop() {
  bool currentStateLB = digitalRead(LB);
  if(lastStateLB != currentStateLB){
    delay(30);
   if(currentStateLB == LOW)
      start = millis();
   currentStateLB = digitalRead(LB);
    if(currentStateLB == HIGH){
      stop = millis();
     Serial.print("Button pressed for: ");
      Serial.print(stop - start);
      Serial.print(" -> ");
      toString(stop - start);
    }
 
  }
    lastStateLB = currentStateLB;
}
void toString(int time){
int hours = time/(60*60*1000);
time = time%(60*60*1000);
int minutes = time/(60*1000);
time = time%(60*1000);
int seconds = time /1000;
int milis = time %1000;
char x[14];

 sprintf(x, "%02d:%02d:%02d:%03d", hours, minutes, seconds, milis);
  Serial.println(x);
}
