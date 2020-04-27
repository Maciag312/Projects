public class Calculable {
    public boolean isCalculable(int[] numbs, int result){
        VariationWithRepetition variation = new VariationWithRepetition();
        String str = "+-/*";
        String[] strings = variation.getVariation(str, numbs.length-1);
        for(int i = 0; i<strings.length;i++){
            if(calculate(strings[i],numbs)==result) return true;
        }
        return false;
    }
    private int calculate(String str, int[] numbs){
        char sign;
        int result = numbs[0];
        for(int i = 0; i<str.length();i++){
            sign = str.charAt(i);
            switch (sign){
                case '*':result*=numbs[i+1];
                case '/':result/=numbs[i+1];
                case '-':result-=numbs[i+1];
                case '+':result+=numbs[i+1];
            }
        }
        return result;
    }
}
