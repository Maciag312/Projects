public class JadenCase {

    public String toJadenCase(String phrase) {
        String add = null;
        if (phrase != null && phrase.length() >= 1) {
            add = "";
            String[] str = phrase.split(" ");
            String space = " ";
            for (int i = 0; i < str.length; i++) {
                str[i] = str[i].substring(0, 1).toUpperCase() + str[i].substring(1);
                if (i + 1 == str.length)
                    space = "";
                add += str[i] + space;
            }
        }
        return add;

    }
}