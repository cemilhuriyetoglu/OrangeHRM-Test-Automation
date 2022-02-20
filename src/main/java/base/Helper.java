package base;

import java.util.Random;

public class Helper {

    public static int numberGenerator(int low, int high) {

        Random r = new Random();
        int lowvalue = low;
        int highvalue = high;
        int result = r.nextInt(highvalue - lowvalue) + lowvalue;
        return result;
    }

    public static String stringGenerator() {
        String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder candidate = new StringBuilder();
        Random rnd = new Random();
        while (candidate.length() < 8) { // length of the random string.
            int index = (int) (rnd.nextFloat() * candidateChars.length());
            candidate.append(candidateChars.charAt(index));
        }
        String result = candidate.toString();
        return result;
    }

}

