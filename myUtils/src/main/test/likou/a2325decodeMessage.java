package likou;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class a2325decodeMessage {
    public static void main(String[] args) {
        System.out.println(decodeMessage(
                "the quick brown fox jumps over the lazy dog",
                "vkbs bs t suepuv"));
        System.out.println(decodeMessage2(
                "the quick brown fox jumps over the lazy dog",
                "vkbs bs t suepuv"));
    }

    public static String decodeMessage(String key, String message) {
        StringBuilder sb = new StringBuilder();
        Map<Character,Character> map = new HashMap<>();
        key = key.replaceAll(" ","");
        int len = key.length();
        char count = 97;
        for (int i = 0; i < len; i++) {
            if (!map.containsValue(key.charAt(i))){
                map.put(count++,key.charAt(i));
            }
        }
        //key value 互换
        Map<Character, Character> collect = map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        int msglen = message.length();
        for (int i = 0; i < msglen; i++) {
            if (" ".equals(message.charAt(i)+"")){
                sb.append(" ");
            }else {
                sb.append(collect.get(message.charAt(i)));
            }
        }
        return sb.toString();
    }

    public static String decodeMessage2(String key, String message) {
        StringBuilder sb = new StringBuilder();
        Map<Character,Character> map = new HashMap<>();
        key = key.replaceAll(" ","");
        int len = key.length();
        char count = 97;
        for (int i = 0; i < len; i++) {
            if (!map.containsKey(key.charAt(i))){
                map.put(key.charAt(i),count++);
            }
        }
        int msglen = message.length();
        for (int i = 0; i < msglen; i++) {
            if (" ".equals(message.charAt(i)+"")){
                sb.append(" ");
            }else {
                sb.append(map.get(message.charAt(i)));
            }
        }
        return sb.toString();
    }
}
