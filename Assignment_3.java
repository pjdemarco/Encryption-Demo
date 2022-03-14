import java.util.Scanner;

public class Assignment_3 {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int p = 555;
        int q = 712;
        int r = 25;

        // take input from keyboard
        String input = input();
        System.out.println("Input: " + input);

        int charLength = input.length();
        int bitLength = charLength * 8;

        int[] bitArray = new int[bitLength];
        char[] chars = new char[charLength];
        int[] ints = new int[charLength];
        String[] binary = new String[charLength];

        //populate char array with input characters
        for(int i = 0; i < input.length(); i++) {
            chars[i] = input.charAt(i);
        }

        //convert chars to ints
        for(int i = 0; i < chars.length; i++) {
            ints[i] = (int)chars[i];
        }

        // convert ints to binary
        for(int i = 0; i < ints.length; i++) {
            binary[i] = convertToBinary(ints[i]);
        }

        // create binary string
        StringBuilder binaryString = new StringBuilder();
        for(int i = 0; i < binary.length; i++) {
            binaryString.append(binary[i]);
        }
        System.out.println("Binary Representation: " + binaryString);
        System.out.println("Encrypting...");


        // populate bit array
        for (int i = 0; i < bitLength; i++) {
            bitArray[i] = binaryString.charAt(i);
        }

        // encrypt bits
        StringBuilder cipherText = new StringBuilder();
        int[] cipherArray = new int[bitArray.length];
        for(int i = 0; i < bitArray.length; i++) {
            cipherArray[i] = encryptBit((int)bitArray[i], p, q, r);
            cipherText.append(cipherArray[i]);
        }
        System.out.println("Cipher Text: " + cipherText);

        // decrypt bits
        System.out.println("Decrypting...");
        StringBuilder decrypted = new StringBuilder();
        for(int i = 0; i < cipherArray.length; i++) {
            decrypted.append(decryptBit(cipherArray[i], p));
        }
        System.out.println("Decrypted Bits: " + decrypted);

        // convert bits back to ints
        int[] decryptedInts = new int[charLength];
        convertToInt(decrypted.toString(), decryptedInts);

        // convert ints back to chars
        StringBuilder decryptedString = new StringBuilder();
        char[] decryptedChars = new char[charLength];
        convertToChars(decryptedInts, decryptedString);

        // print decrypted string
        System.out.println("Decrypted String: " + decryptedString);
    }

    public static String input() {
        System.out.println("Enter a string to encrypt");
        return scanner.nextLine();
    }

    public static int encryptBit(int b, int p, int q, int r) {
        int keyMod = p * q;
        int noise = (2 * r) + b;
        int c = keyMod + noise;
        return c;
    }

    public static int decryptBit(int c, int p) {
        int result = c % p;
        if (result % 2 == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public static String convertToBinary(int input) {
        String binaryInput = Integer.toBinaryString(input);
        String withPadding = String.format("%8s", binaryInput).replaceAll(" ", "0");
        return withPadding;
    }

    public static void convertToInt(String byteString, int[] decryptedInts) {
        int counter = 0;
        for (int i = 0; i < decryptedInts.length; i++) {
            String substring = byteString.substring(counter, counter + 8);
            int converted = Integer.parseInt(substring, 2);
            decryptedInts[i] = converted;
            counter += 8;
        }
    }

    public static void convertToChars(int[] decryptedInts, StringBuilder decryptedString) {
        for(int i = 0; i < decryptedInts.length; i++) {
            decryptedString.append((char)decryptedInts[i]);
        }
    }
}
