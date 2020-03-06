#define LED_RED 6

String LED_ON = "LED ON";
String LED_OFF = "LED OFF";
String LED_BLINK_TIME = "LED BLINK ";
String LED_BLINK = "LED BLINK";

String msg = "";
int delayy = 150;
bool isOn = false;
bool isBlink = false;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(LED_RED, OUTPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  if(Serial.available() > 0){
    msg = Serial.readStringUntil('\n');
    Serial.println(msg.substring(10, msg.length()).toInt());
    if(msg == LED_ON) {
      isOn = true;
  } else if(msg == LED_OFF){
    isOn = false;
  } else if(msg == LED_BLINK){
    isOn = true;
    isBlink = true;
  } else if(msg.substring(0,10) == LED_BLINK_TIME){
    isOn = true;
    isBlink = true;
    delayy = msg.substring(10, msg.length()).toInt();
  }
  else {
    Serial.print("Wrong command!");
  }
  }

  if(isBlink){
    isOn = !isOn;
  }

  if(isOn) {
    digitalWrite(LED_RED, HIGH);
  } else {
    digitalWrite(LED_RED, LOW);
  }
  delay(delayy);
}
