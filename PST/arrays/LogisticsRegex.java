package PST.arrays;

import java.util.*;
import java.util.regex.*;

public class LogisticsRegex {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String S = sc.nextLine().trim();

        if (S.matches("^[A-Z]{4}\\d{7}$")) {
            System.out.println("CONTAINER");
        } else if (S.matches("^TRK-\\d{9}$")) {
            System.out.println("TRACKING");
        } else if (S.matches("^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}$")) {
            System.out.println("TIMESTAMP");
        } else {
            System.out.println("INVALID");
        }
        sc.close();
    }
}