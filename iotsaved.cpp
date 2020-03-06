
#define BUTTON1_PIN 2
#define BUTTON2_PIN 4
#define BLUE_LED_PIN 3 
#define GREEN_LED_PIN 5 
#define RED_LED_PIN 6 

int counter;
bool last4State = HIGH;
bool last2State = HIGH;

void setup() {
	counter=0;
	pinMode(BUTTON1_PIN, INPUT_PULLUP);
	pinMode(BUTTON2_PIN, INPUT_PULLUP);
  	pinMode(BLUE_LED_PIN, OUTPUT);
  	pinMode(GREEN_LED_PIN, OUTPUT);
  	pinMode(RED_LED_PIN, OUTPUT);
  	Serial.begin(9600);
  	display();
}

void loop() {
	digitalWrite(BLUE_LED_PIN, HIGH);

	bool current4State = digitalRead(BUTTON2_PIN);
	bool current2State = digitalRead(BUTTON1_PIN);
  
  	if( (last2State != current2State) || (last4State != current4State) ) {
      	delay(20);
		current4State = digitalRead(BUTTON2_PIN);
	    current2State = digitalRead(BUTTON1_PIN);
  	}
  
  

	if( (last2State != current2State) && (last4State != current4State) && (current2State == HIGH) && (current4State == HIGH) ){
		Serial.println("Wyzerowanie licznika...");
      	while(counter>0) { 
			digitalWrite(GREEN_LED_PIN, HIGH);
			counter -= 1;
			display();
			delay(500); 
			digitalWrite(GREEN_LED_PIN, LOW);
		} 
      if(counter==0)
      	Serial.println("Zrobione!");
        else
        Serial.println("Odmowa.");
	} 
	else {
		if( (last4State != current4State) && (current4State == HIGH) ){
			digitalWrite(BLUE_LED_PIN, LOW); 
			digitalWrite(GREEN_LED_PIN, HIGH);
			counter += 1;
			display(); 
			delay(100); 
			digitalWrite(GREEN_LED_PIN, LOW);
		} 
		if( (last2State != current2State) && (current2State == HIGH) ){
			digitalWrite(BLUE_LED_PIN, LOW); 
			digitalWrite(RED_LED_PIN, HIGH);
			counter -= 1;
			display(); 
			delay(100); 
			digitalWrite(RED_LED_PIN, LOW);
		} 
	}
	last4State = current4State; 
	last2State = current2State; 
}

void display() {
	Serial.print("Stan licznika: ");
  	Serial.println(counter);
}
