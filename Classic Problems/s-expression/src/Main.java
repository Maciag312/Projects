import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        while(true) {
            System.out.println("Enter s-expression to check");
            Scanner sc = new Scanner(System.in);
            System.out.println(check(sc.nextLine().replaceAll(" ", "")));
        }
    }
    public static String[] spliter(String str){
        int l=0;
        String[] strto= new String[2];
        for(int i=0; i<str.length();i++){
            if(str.charAt(i)=='(') l++;
            if(str.charAt(i)==')') l--;
            if(l==0){
                if(str.charAt(i) == ','){
                strto[0] = str.substring(0,i);
                strto[1] = str.substring(i+1);
                return strto;}
            }
        }
        return strto;
    }
    public static boolean check(String str){
        if(str.length()==1&&str.matches("[a-zA-Z]+")) return true;
        String divided[] = spliter(str.substring(1,str.length()-1));
        boolean leftCorrect = false;
        boolean rightCorrect = false;
        if(divided[0].contains("(")&&divided[0].contains(")")){
            leftCorrect = check(divided[0]);
        } else{
            if(divided[0].length()==1&&divided[0].matches("[a-zA-Z]+")) leftCorrect = true;
            else leftCorrect = false;
        }
        if(divided[1].contains("(")&&divided[1].contains(")")){
            rightCorrect = check(divided[1]);
        } else {
            if(divided[1].length()==1&&divided[0].matches("[a-zA-Z]+")) rightCorrect = true;
            else rightCorrect = false;
        }
        if(leftCorrect&&rightCorrect)
            return true;
        return false;
    }
}
