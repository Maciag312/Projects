public class ShortestPalindrome {
    public String shortestPalindrome(String str){
        char[] chars = str.toCharArray();
        int counterD = 0;
        int counterU = 0;
        int[] longest = new int[chars.length];
        int[] longestBetween = new int[chars.length];
        int smaller = 0;
        for(int i = 0; i<chars.length;i++){
            if(chars.length-i<i){
                smaller = chars.length-i;
            }else{
                smaller = i;
            }
            for(int j = i; j<smaller; j++){

            }
        }
        return null;

    }
}
