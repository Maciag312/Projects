import java.util.HashMap;

public class BullsAndCows {
    String getBullAndCows(int guess, int numb){
        char[] chNumbs = String.valueOf(numb).toCharArray();
        char[] chGuesses = String.valueOf(guess).toCharArray();
        HashMap<Integer, Character> NumbshashMap = new HashMap<>();
        int bulls = 0;
        int cows = 0;
        for(int i = 0; i<chNumbs.length;i++){
            NumbshashMap.put(i, chNumbs[i]);
        }
        for(int i = 0; i<chGuesses.length;i++){
            Character ch = NumbshashMap.get(i);
            if(ch.charValue()==chGuesses[i])
            {NumbshashMap.remove(i); bulls++;}
        }
        for(int j = 0; j<chGuesses.length;j++){
            if(NumbshashMap.containsValue(chGuesses[j])){
                cows++;
            }
        }
        return "A" + String.valueOf(cows) + "B" + String.valueOf(bulls);
    }
}
