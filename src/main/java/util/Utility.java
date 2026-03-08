package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utility {

    public static String getRandomUsername() {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return "user_" + timestamp;
    }

    public static String getRandomName() {
        String[] firstNames = {"Alice", "Bob", "Charlie", "David", "Emily", "Frank", "Grace", "Henry", "Isabella", "Jack", "Soka", "Boka"};
        Random random = new Random();
        int index = random.nextInt(firstNames.length);
        return firstNames[index];

    }

    public static String getRandomFirstName() {
        String[] firstNames = {"Alice", "Bob", "Charlie", "David", "Emily", "Frank", "Grace", "Henry", "Isabella", "Jack"};
        Random random = new Random();
        int index = random.nextInt(firstNames.length);
        return firstNames[index];

    }

    public static String getRandomLastName() {
        String[] lastNames = {"Alice", "Bob", "Charlie", "David", "Emily", "Frank", "Grace", "Henry", "Isabella", "Jack"};
        Random random = new Random();
        int index = random.nextInt(lastNames.length);
        return lastNames[index];

    }

    //generate complex password with 8 characters
    public static String generatePassword() {
        final String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String lowerLetters = capitalLetters.toLowerCase();
        final String specialCharacters = "!@#$%^&*()-+";
        final String allCharacters = capitalLetters + lowerLetters + specialCharacters;
        final int passwordLength = 8;

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        int charCategories = 3; // Consider at least one character from each category

        // Ensure at least one character from each category
        for (int i = 0; i < charCategories; i++) {
            int charGroupIndex = random.nextInt(charCategories);
            String charGroup;
            switch (charGroupIndex) {
                case 0:
                    charGroup = capitalLetters;
                    break;
                case 1:
                    charGroup = lowerLetters;
                    break;
                case 2:
                    charGroup = specialCharacters;
                    break;
                default:
                    throw new IllegalStateException("Unexpected character group index");
            }
            int charIndex = random.nextInt(charGroup.length());
            password.append(charGroup.charAt(charIndex));
        }

        // Fill remaining characters with any character
        for (int i = password.length(); i < passwordLength; i++) {
            int charIndex = random.nextInt(allCharacters.length());
            password.append(allCharacters.charAt(charIndex));
        }

        // Shuffle the characters for better randomness
        for (int i = 0; i < password.length(); i++) {
            int targetIndex = random.nextInt(password.length());
            char temp = password.charAt(i);
            password.setCharAt(i, password.charAt(targetIndex));
            password.setCharAt(targetIndex, temp);
        }

        return password.toString();
    }

    // generate random email with gmail domain only
    public static String generateRandomGmailEmail() {
        int usernameLength = 8; // Adjust username length as needed
        StringBuilder usernameBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < usernameLength; i++) {
            int charType = random.nextInt(3);
            if (charType == 0) {
                usernameBuilder.append((char) (random.nextInt(26) + 'a')); // Lowercase letter
            } else if (charType == 1) {
                usernameBuilder.append((char) (random.nextInt(26) + 'A')); // Uppercase letter
            } else {
                usernameBuilder.append((char) (random.nextInt(10) + '0')); // Number
            }
        }

        String username = usernameBuilder.toString();
        String domain = "gmail.com"; // Guaranteed Gmail domain
        return username + "@" + domain;
    }

    //generate uniqe number
    public static List<Integer> generateUniqueRandomNumbers(int count) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers;
    }

    //generate random integer
    public static Integer removeRandomInteger(List<Integer> list) {
        // Check if the list is empty
        if (list.isEmpty()) {
            throw new IllegalArgumentException("The list is empty.");
        }

        // Generate a random index within the bounds of the list
        Random random = new Random();
        int index = random.nextInt(list.size());

        // Remove the element at the random index
        return list.remove(index);
    }

    // TODO: start html report
    public static void startHtmlReport(String reportDirName, String reportFileName) throws IOException {
        String path = System.getProperty("user.dir") + "/testReport.html";
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "cd " + reportDirName + " && " + reportFileName);
        builder.redirectErrorStream(true);
        Process p = builder.start();
    }


    // parse float values
    public static float parsePriceFromString(String priceString) {
        if (priceString == null || priceString.isEmpty()) {
            throw new IllegalArgumentException("Price string cannot be null or empty");
        }

        // Remove leading and trailing whitespaces (optional)
        priceString = priceString.trim();

        // Check if the string starts with a dollar sign ($)
        if (!priceString.startsWith("$")) {
            throw new IllegalArgumentException("Price string must start with a dollar sign ($)");
        }

        // Extract the number part (everything after the dollar sign)
        String numberString = priceString.substring(1);

        // Parse the number string to a float
        try {
            return Float.parseFloat(numberString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid price format. Please provide a valid number after the dollar sign ($)", e);
        }
    }

    public static String getProperty(String key) {
        Properties properties = new Properties();
        String path = "src/test/resources/properties/environment.properties"; // Dein Pfad aus dem Screenshot

        try (FileInputStream fis = new FileInputStream(path)) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Properties Datei konnte nicht geladen werden: " + e.getMessage());
        }

        return properties.getProperty(key);
    }
}
