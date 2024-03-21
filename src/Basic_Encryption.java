import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Basic_Encryption {

    // This method will take in a file and convert it into a string. This string will be our private_key to encrypt and decrypt messages.
    public static String convertToString(String fileName) {
        String private_key = "";
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                private_key = scanner.nextLine();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return private_key;
    }

    // This method takes in the private key and checks if it seems like a private key. If it does, it will return true, else it will return false. A private key should be an equation as a string with commas between values and actions. Ex. 2,+,(,x,*,5,) means 2 + (x * 5).
    public static boolean checkPrivateKey(String private_key) {
        String[] private_key_array = private_key.split(",");
        boolean xChecker = false;
        boolean operatorChecker = false;

        // x Checker ( x is the variable that will be used in the equation)
        for (int i = 0; i < private_key_array.length; i++) {
            if (private_key_array[i].equals("x")) {
                xChecker = true;
            }
        }
        // Operator Checker
        for (int i = 0; i < private_key_array.length; i++) {
            if (private_key_array[i].equals("+") || private_key_array[i].equals("-") || private_key_array[i].equals("*") || private_key_array[i].equals("/")) {
                operatorChecker = true;
            }
        }
        if (xChecker && operatorChecker) {
            return true;
        } else {
            return false;
        }
    }

    // Method that will take a string in and convert it to an int array with each section being a number
    public static int[] createArray(String text) {
        int[] string_array = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            string_array[i] = text.charAt(i);
        }
        return string_array;
    }

    // Method that will take in a string and a private key and encrypt the string using the private key by multiplying each value in the encrypted int array as another int array. For example, if the private key is 2,*,(,x,*,5,), and the int array is [13, 1, 7, 9, 3], the encrypted int array will be [26, 2, 14, 18, 6] and spaces are 0.
    public static int[] encrypt(String text, String private_key) {
        int[] encrypted = createArray(text);
        String[] private_key_array = private_key.split(",");
        int x = 0;
        int operator = 0;
        int value = 0;
        for (int i = 0; i < private_key_array.length; i++) {
            if (private_key_array[i].equals("x")) {
                x = encrypted[i];
            } else if (private_key_array[i].equals("+")) {
                operator = 1;
            } else if (private_key_array[i].equals("-")) {
                operator = 2;
            } else if (private_key_array[i].equals("*")) {
                operator = 3;
            } else if (private_key_array[i].equals("/")) {
                operator = 4;
            } else if (private_key_array[i].equals("(")) {
                value = encrypted[i];
            } else if (private_key_array[i].equals(")")) {
                if (operator == 1) {
                    encrypted[i] = value + x;
                } else if (operator == 2) {
                    encrypted[i] = value - x;
                } else if (operator == 3) {
                    encrypted[i] = value * x;
                } else if (operator == 4) {
                    encrypted[i] = value / x;
                }
            }
        }
        return encrypted;
    }



}
