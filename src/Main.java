import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++){
            new Thread(() -> {
                String text = generateRoute("RLRFR", 100);
                int key = (int) text.chars().filter(sym -> sym == 'R').count();
                synchronized (sizeToFreq){
                    if (sizeToFreq.containsKey(key)){
                        sizeToFreq.put(key, sizeToFreq.get(key)+1);
                    } else{ sizeToFreq.put(key, 1); }
                }
            }).start();
        }


        Map.Entry<Integer, Integer> maxPovtor = sizeToFreq.entrySet().stream().max(Map.Entry.comparingByValue()).get();
        System.out.println("Самое частое количество повторений = " + maxPovtor.getKey() + " ( встретилось " + maxPovtor.getValue() + " раз )");

        System.out.println("Другие размеры:");
        sizeToFreq.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEach(s -> System.out.println(" - " + s.getKey() + " ( " + s.getValue() + " раз)"));
    }

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }
}