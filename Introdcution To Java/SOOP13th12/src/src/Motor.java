package src;

public class Motor implements hasEngine{
	protected boolean engine;
	protected double gas;
	@Override
	public boolean turnOn() {
		// TODO Auto-generated method stub
		if(!engine) {
			return true;
		}
		return false;
	}

	@Override
	public boolean turnOff() {
		// TODO Auto-generated method stub
		if(engine) {
			return false;
		}
		return true;
	}
	public double checkGas() { 
		return gas;
	}

}
