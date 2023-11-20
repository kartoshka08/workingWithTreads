import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Runnable logic = () -> {
            String[] texts = new String[5];
            for (int i = 0; i < texts.length; i++) {
                texts[i] = generateText("aab", 30_000);
            }
            for (String text : texts) {
                int maxSize = 0;
                for (int i = 0; i < text.length(); i++) {
                    for (int j = 0; j < text.length(); j++) {
                        if (i >= j) {
                            continue;
                        }
                        boolean bFound = false;
                        for (int k = i; k < j; k++) {
                            if (text.charAt(k) == 'b') {
                                bFound = true;
                                break;
                            }
                        }
                        if (!bFound && maxSize < j - i) {
                            maxSize = j - i;
                        }
                    }
                }
                System.out.println(text.substring(0, 100) + " -> " + maxSize);
            }
        };
        long startTs = System.currentTimeMillis(); // start time

        List<Thread> threads = new ArrayList<>();
        Thread thread1 = new Thread(logic);
        Thread thread2 = new Thread(logic);
        Thread thread3 = new Thread(logic);
        Thread thread4 = new Thread(logic);
        Thread thread5 = new Thread(logic);
        threads.add(thread1);
        threads.add(thread2);
        threads.add(thread3);
        threads.add(thread4);
        threads.add(thread5);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        for (Thread thread : threads) {
            thread.join(); // зависаем, ждём когда поток объект которого лежит в thread завершится
        }
        long endTs = System.currentTimeMillis(); // end time
        System.out.println("Time: " + (endTs - startTs) + "ms");

    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}