import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
//        SubstringWithConcatentionOfAllWords substringWithConcatentionOfAllWords = new SubstringWithConcatentionOfAllWords();
//        int[] arr = substringWithConcatentionOfAllWords.substring("barfoothefoobarman",new String[]{"foo", "bar"});
//        for(int i: arr){
//            System.out.println(i);
//        }
//
        LongestSubstringWithDistincCharacters longestSubstringWithDistincCharacters = new LongestSubstringWithDistincCharacters();
        Calculable calculable = new Calculable();
        int[] arr = {1,4,2,3};
        int result = 11;
        while(true){
            //System.out.println(calculable.isCalculable(arr,result));
            System.out.println(longestSubstringWithDistincCharacters.LongestSubstringWithKDistinctCharacters("aabcdefff", 3));

        }
    }
}
