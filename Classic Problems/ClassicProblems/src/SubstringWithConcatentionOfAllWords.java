import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class SubstringWithConcatentionOfAllWords {
    public int[] substring(String str, String[] words){
        int lenght = words[0].length();
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        Vector<String> vWords = new Vector<>(Arrays.asList(words));
        Vector<String> copy = new Vector<>(Arrays.asList(words));
        boolean match = false;
        boolean first = true;
        int addedValue = 0;
        for(int i = 0;i<str.length();i++){
            if(i+lenght<str.length()){
                if(vWords.contains(str.substring(i, i+lenght))){
                    if(first)
                        addedValue = i;
                    first = false;
                    vWords.remove(str.substring(i, i+lenght));
                    i+=lenght-1;

                }else{
                    vWords = (Vector)copy.clone();
                    first = true;
                }
            }

            if(vWords.isEmpty()) {
                integerArrayList.add(Integer.valueOf(addedValue));
                vWords = (Vector) copy.clone();
            }
        }
        return integerArrayList.stream().mapToInt(i->i).toArray();
    }

}
