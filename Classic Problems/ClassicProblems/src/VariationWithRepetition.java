import java.util.ArrayList;

public class VariationWithRepetition {

    private ArrayList<String> strings = new ArrayList<>();
    public String[] getVariation(String str, int length){
        strings.clear();
        add(str, new StringBuffer(), length);
        return strings.toArray(new String[0]);
    }
    private void add(String initial, StringBuffer stringBuffer, int length){
        if(length==0){
            strings.add(stringBuffer.toString());
        }else{
            for(int i = 0; i<initial.length();i++){
                stringBuffer.append(initial.charAt(i));
                add(initial, stringBuffer, length-1);
                stringBuffer.deleteCharAt(stringBuffer.length()-1);

            }
        }
    }
}
