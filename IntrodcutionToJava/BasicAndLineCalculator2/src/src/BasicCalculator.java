package src;

public class BasicCalculator extends AbstractCalc {

	@Override
	public String calculate(String argument) {
		// TODO Auto-generated method stub
		String result = "";
		String one; 
		String second;
		String[] Arr = new String[3];
		
		
		Arr = argument.split("\\*");
		if(Arr.length==1) {
			Arr = argument.split("\\-");
		}
		if(Arr.length == 1) {
			Arr = argument.split("\\+");
		}
		
		if (Arr.length>2 && !(Arr[0].matches("[0-9]+") && Arr[2].matches("[0-9]+"))){
			System.out.println("Invalid input");
			return "";
		}else {
		
			one = Arr[0].trim();
			second = Arr[1].trim();
			
				
			
			if(argument.contains("*")){
				result = this.product(one, second);
			}
			if(argument.contains("-")){
				result = this.difference(one, second);
			}
			if(argument.contains("+")){
				result = this.sum(one, second);
			}
			return result;
		}
	}

	@Override
	public int sum(int k1, int k2) {
		// TODO Auto-generated method stub
		return (k1+k2);
	}

	@Override
	public double sum(double k1, double k2) {
		// TODO Auto-generated method stub
		return (k1+k2);
	}

	@Override
	public String sum(String k1, String k2) {
		// TODO Auto-generated method stub
		String result = "";
		if(k1.contains(".") || k2.contains(".")) 
		{
			result = Double.toString(this.sum(Double.parseDouble(k1), Double.parseDouble(k2)));
		}
		else {
			result = Integer.toString(this.sum(Integer.parseInt(k1), Integer.parseInt(k2)));
		}
		return result;
	}

	@Override
	public int product(int k1, int k2) {
		// TODO Auto-generated method stub
		return (k1*k2);
	}

	@Override
	public double product(double k1, double k2) {
		// TODO Auto-generated method stub
		return (k1*k2);
	}

	@Override
	public String product(String k1, String k2) {
		// TODO Auto-generated method stub
		String result = "";
		if(k1.contains(".") || k2.contains(".")) 
		{
			result = Double.toString(this.product(Double.parseDouble(k1), Double.parseDouble(k2)));
		}
		else {
			result = Integer.toString(this.product(Integer.parseInt(k1), Integer.parseInt(k2)));
		}
		return result;
	}

	@Override
	public int difference(int k1, int k2) {
		// TODO Auto-generated method stub
		return (k1-k2);
	}

	@Override
	public double difference(double k1, double k2) {
		// TODO Auto-generated method stub
		return (k1-k2);
	}

	@Override
	public String difference(String k1, String k2) {
		// TODO Auto-generated method stub
		String result = "";
		if(k1.contains(".") || k2.contains(".")) 
		{
			result = Double.toString(this.difference(Double.parseDouble(k1), Double.parseDouble(k2)));
		}
		else {
			result = Integer.toString(this.difference(Integer.parseInt(k1), Integer.parseInt(k2)));
		}
		return result;
	}
	

}
