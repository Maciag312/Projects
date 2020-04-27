import java.util.HashMap;

public class LongestSubstringWithDistincCharacters {



    public int  LongestSubstringWithKDistinctCharacters(String s, int k){
        int longestLength = 0;

        int currentLength = 0;

        int lastPos = 0;


        HashMap characterCouner = new HashMap<Character, Integer>();


        for(int i = 0; i<s.length();i++){
            currentLength++;
            char ch = s.charAt(i);
            characterCouner.put(ch, 1);
            if(characterCouner.keySet().size()>k){
                lastPos++;
                i = lastPos-1;
                if(currentLength>longestLength){
                    longestLength = currentLength;
                }
                currentLength = 0;
                characterCouner.clear();
            }
        }
        return longestLength;
    }



}
