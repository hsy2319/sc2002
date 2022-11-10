package Controller;

import Model.Constant.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles user input and formatting.
 *
 * @version 1.0
 */
public class IOController {
    /**
     * Reads an integer with value in a range.
     * @param i the lower bound of the input
     * @param j the upper bound of the input
     * @return the input within the specified range
     */
    public static int readChoice(int i, int j) {
        Scanner sc = new Scanner(System.in);
        int choice;

        try {
            choice = sc.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input, try again.");
            sc.nextLine();
            return readChoice(i, j);
        }

        if (choice < i || choice > j) {
            System.out.println("Invalid input, try again.");
            return readChoice(i, j);
        }
        return choice;
    }

    /**
     * Reads a {@code String} .
     * @param message the message to be shown
     * @return the inputted message
     */
    public static String readString(String... message) {
        for (String m : message) System.out.println(m);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Reads a {@code double} .
     * @param message the message to be shown
     * @return the input
     */
    public static double readDouble(String... message) {
        for (String m : message) System.out.println(m);
        Scanner sc = new Scanner(System.in);
        double output;

        try {
            output = sc.nextDouble();
            return output;
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input, try again.");
            sc.nextLine();  // flush scanner
            return readDouble(message);
        }
    }

    /**
     * Generates spaces with specified size.
     * @param size the number of spaces
     * @return the spaces generated
     */
    public static String generateSpaces(int size) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) stringBuilder.append(" ");
        return stringBuilder.toString();
    }

    /**
     * Adds line breaks for a {@code String} when length is greater than a specified value
     * and adds spaces after the first line.
     * @param input the String to be formatted
     * @param maxLineLength the maximum length a line can be
     * @param lengthOfSpace the number of spaces to be added to the second line onwards
     * @return the formatted String
     */
    public static String addLinebreaks(String input, int maxLineLength, int lengthOfSpace) {
        StringTokenizer tok = new StringTokenizer(input, " ");
        StringBuilder output = new StringBuilder(input.length());
        int lineLen = 0;
        while (tok.hasMoreTokens()) {
            String word = tok.nextToken();

            if (lineLen + word.length() > maxLineLength) {
                output.append("\n");
                for (int i = 0; i < lengthOfSpace; i++) output.append(" ");
                lineLen = 0;
            }
            output.append(word).append(" ");
            lineLen += word.length();
        }
        return output.toString();
    }

    /**
     * Reads email address.
     * @param message the message to be shown
     * @return the input with Email format
     */
    public static String readEmail(String... message) {
        for (String m : message) System.out.println(m);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        Pattern EMAIL_PATTERN = Pattern.compile(
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = EMAIL_PATTERN.matcher(input);
        if (matcher.matches()) {
            return input;
        }
        else {
            System.out.println("Invalid input, try again.");
            return readEmail(message);
        }
    }

    /**
     * maps the {@code String} to respective {@code AgeRestriction}.
     * @param input the {@code String} to be mapped
     * @return the {@code AgeRestriction} the input mapped to
     */
    public static AgeRestriction readAgeRestriction(String input) {
        switch (input.toUpperCase()) {
            case "G":
                return AgeRestriction.G;
            case "PG":
                return AgeRestriction.PG;
            case "PG13":
                return AgeRestriction.PG13;
            case "NC16":
                return AgeRestriction.NC16;
            case "M18":
                return AgeRestriction.M18;
            case "R21":
                return AgeRestriction.R21;
            default:
                return null;
        }
    }

    /**
     * maps the {@code String} to {@code MovieStatus}.
     * @param input the {@code String} to be mapped
     * @return the {@code MovieStatus} the input mapped to
     */
    public static MovieStatus readMovieStatus(String input) {
        switch (input.toUpperCase()) {
            case "COMING SOON":
                return MovieStatus.COMING_SOON;
            case "NOW SHOWING":
                return MovieStatus.NOW_SHOWING;
            case "END OF SHOWING":
                return MovieStatus.END_OF_SHOWING;
            default:
                return null;
        }
    }

    /**
     * reads a {@code String} with format MM-dd kk:mm and turns into {@code Date}.
     * @param message the message to be shown
     * @return the formatted {@code Date}
     */
    public static Date readTimeMMddkkmm(String... message) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm");
        try {
            String input = readString(message);
            input = new SimpleDateFormat("yyyy").format(new Date()) + "-" + input;
            Date date = simpleDateFormat.parse(input);
            return date;
        } catch (ParseException ex) {
            System.out.println("Invalid input, try again.");
            return readTimeMMddkkmm(message);
        }
    }

    /**
     * reads a {@code String} with format MM-dd and turns into {@code Date}.
     * @param message the message to be shown
     * @return the formatted {@code Date}
     */
    public static Date readTimeMMdd(String... message) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String input = readString(message);
            input = new SimpleDateFormat("yyyy").format(new Date()) + "-" + input;  // set year as current year
            Date date = simpleDateFormat.parse(input);
            return date;
        } catch (ParseException ex) {
            System.out.println("Invalid input, try again.");
            return readTimeMMdd(message);
        }
    }

    /**
     * Asks user for confirmation.
     * @param message the message to be shown
     * @return true if input is Y, else false
     */
    public static boolean askConfirm(String... message) {
        for (String m : message) System.out.println(m);
        Scanner sc = new Scanner(System.in);
        if (sc.next().toUpperCase().equals("Y")) return true;
        else return false;
    }

    /**
     * prints specified {@code String}
     * @param choices the choices to be printed
     */
    public static void printChoices(String... choices) {
        for (String s : choices) {
            System.out.println(s);
        }
    }

    /**
     * prints specified {@code String}
     * @param title the title to be printed
     */
    public static void printTitle(String title) {
        int length = 65;
        for (int i = 0; i < length; i++) System.out.print("-");
        System.out.println();

        int indent = (length - title.length()) / 2;
        for (int i = 0; i < indent; i++) System.out.print(" ");
        System.out.print(title);
        for (int i = 0; i < indent; i++) System.out.print(" ");
        System.out.println();

        for (int i = 0; i < length; i++) System.out.print("-");
        System.out.println();
    }

    /**
     * formats a {@code Date} to a {@code String} with format MMMM dd, kk:mm.
     * @param time the {@code Date} to be formatted
     * @return the {@code String} formatted
     */
    public static String formatTimeMMddkkmm(Date time) {
        return new SimpleDateFormat("MMMM dd, kk:mm").format(time);
    }

    /**
     * formats a {@code Date} to a {@code String} with format MMMM dd.
     * @param time the {@code Date} to be formatted
     * @return the {@code String} formatted
     */
    public static String formatTimeMMdd(Date time) {
        return new SimpleDateFormat("MMMM, dd").format(time);
    }

    /**
     * checks if {@code Date} is a weekend
     * @param time the {@code Date} to be checked
     * @return true if {@code Date} is weekend, else false
     */
    public static boolean isWeekend(Date time) {
        String whatDay = new SimpleDateFormat("EEEE").format(time);
        if (whatDay.equals("Saturday") || whatDay.equals("Sunday")) return true;
        else return false;
    }

    /**
     * tests if 2 {@code Date} equals in month and date
     * @param d1 the first {@code Date}
     * @param d2 the second {@code Date}
     * @return true if equals in month and date, else false
     */
    public static boolean dateEquals(Date d1, Date d2) {
        return formatTimeMMdd(d1).equals(formatTimeMMdd(d2));
    }


    /**
     * rounds a double value to a specified decimal place.
     * @param value value to be rounded
     * @param places the number of decimal places of the result
     * @return the result after rounding
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
