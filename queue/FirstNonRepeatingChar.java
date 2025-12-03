package queue;

import java.util.*;

public class FirstNonRepeatingChar {
    public static List<Character> firstNonRepeatingChar(String stream) {
        Map<Character, Integer> charCount = new HashMap<>();
        Queue<Character> queue = new LinkedList<>();
        List<Character> result = new ArrayList<>();

        for (char ch : stream.toCharArray()) {
            // update frequency
            charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);
            queue.add(ch);

            // remove repeated chars from front of queue
            while (!queue.isEmpty() && charCount.get(queue.peek()) > 1) {
                queue.poll();
            }

            // append current first non-repeating or '#' if none
            if (!queue.isEmpty()) {
                result.add(queue.peek());
            } else {
                result.add('#');
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String stream = sc.nextLine();
        List<Character> output = firstNonRepeatingChar(stream);
        for (char c : output) {
            System.out.print(c + " ");
        }
        sc.close();
    }
}