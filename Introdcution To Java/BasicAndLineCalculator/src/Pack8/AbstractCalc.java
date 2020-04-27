package Pack8;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class AbstractCalc {
	public abstract String calculate(String argument);
	/* the format of the argument:
	* operand operator operand
	* possible operators are:
	* + for sum
	* * for product
	* - for difference
	*/
	protected boolean isInteger(String s) {
	Pattern pat= Pattern.compile("^\\d+$");
	Matcher m= pat.matcher(s);
	return (m.find());
	}
	protected boolean isFloatingPoint(String s) {
	Pattern pat= Pattern.compile("^\\d+.\\d+$");
	Matcher m= pat.matcher(s);
	return (m.find());
	}
	public abstract int sum(int k1, int k2);
	// returns k1 + k2
	public abstract BigDecimal sum(double k1, double k2);
	// returns k1 + k2
	public abstract String sum(String k1, String k2);
	// returns strings with all words in k1 or k2 or in both of them
	public abstract int product(int k1, int k2);
	// returns k1 * k2
	public abstract BigDecimal product(double k1, double k2);
	// returns k1 * k2
	public abstract String product(String k1, String k2);
	// returns string with all words in k1 AND k2 or in both of them
	public abstract int difference(int k1, int k2);
	// returns abs(k1-k2)
	public abstract BigDecimal difference(double k1, double k2);
	// returns abs(k1-k2)
	public abstract String difference(String k1, String k2);
	// returns string with all words in k1 but not k2
	}