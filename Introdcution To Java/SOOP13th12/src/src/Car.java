package src;

public class Car implements Driveable, hasEngine {
	Car(){
		
	}
	protected boolean isFrontWindowDirty = true;
	public void CleanFrontWindow() {
		isFrontWindowDirty = false;
	}
	@Override
	public boolean turnOn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean turnOff() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void drive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	
}
