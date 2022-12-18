package likou;

public class a1832checkIfPangram {
    public static boolean checkIfPangram(String sentence) {
        int len = sentence.length();
        if (sentence.length()<26){
            return false;
        }else {
            for (int i = 97; i < 123; i++) {
                sentence = sentence.replaceFirst((char)i+"", "");
            }
        }
        return len-sentence.length()==26;
    }

    public static void main(String[] args) {
        System.out.println(checkIfPangram("thequickbrownfoxjumpsoverthelazydog"));
    }
}
