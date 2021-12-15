package aoc2020.Day04;

import aoc2020.utils.FileUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
The line is moving more quickly now, but you overhear airport security talking about how passports with invalid data are getting through. Better add some data validation, quick!

You can continue to ignore the cid field, but each other field has strict rules about what values are valid for automatic validation:

byr (Birth Year) - four digits; at least 1920 and at most 2002.
iyr (Issue Year) - four digits; at least 2010 and at most 2020.
eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
hgt (Height) - a number followed by either cm or in:
If cm, the number must be at least 150 and at most 193.
If in, the number must be at least 59 and at most 76.
hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
pid (Passport ID) - a nine-digit number, including leading zeroes.
cid (Country ID) - ignored, missing or not.
 */
public class PassportValidifierV2 {

    private static  Set<String> validKeys = new HashSet<String>(Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid"));
    private static String optionalKey = "cid";
    private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    private static Pattern colorPattern = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
    private static Set<String> eyeColors = new HashSet<>(Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth"));

    public static void main(String[] args) {
        String inputPath = "C:\\MyDrive\\Study\\My_Github_Repo\\Advent-of-Code\\src\\aoc2020\\Day4\\input1";
        List<String> input1 = FileUtils.readInputFile(inputPath);
        System.out.println(getValidPassportCount(input1));

        inputPath = "C:\\MyDrive\\Study\\My_Github_Repo\\Advent-of-Code\\src\\aoc2020\\Day4\\input2";
        List<String> input2 = FileUtils.readInputFile(inputPath);
        System.out.println(getValidPassportCount(input2));
    }

    public static int getValidPassportCount(List<String> inputList) {
        int validCount = 0;
        StringBuilder passportDetails = new StringBuilder();
        boolean isValid;
        for (String line : inputList) {
            if (line.equals("")) {
                isValid = checkValid(passportDetails.toString());
                passportDetails = new StringBuilder();
                validCount = isValid ? validCount + 1 : validCount;
            } else {
                passportDetails.append(line + " ");
            }
        }

        isValid = checkValid(passportDetails.toString());
        validCount = isValid ? validCount + 1 : validCount;
        return validCount;
    }

    private static boolean checkValid(String passportDetails) {
        String[] keyValues = passportDetails.split("\\s+");
        int count = 0;
        boolean isCID = false;
        for (String keyPair : keyValues) {
            String key = keyPair.substring(0, keyPair.indexOf(":"));
            String value = keyPair.substring(keyPair.indexOf(":") + 1).trim();
            if (validKeys.contains(key) && isValidKey(key, value)) {
                count++;
                if (key.equals("cid")) {
                    isCID = true;
                }
            }
        }
        return (count == 8 || (count == 7 && !isCID));
    }

    /*
    byr (Birth Year) - four digits; at least 1920 and at most 2002.
    iyr (Issue Year) - four digits; at least 2010 and at most 2020.
    eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
    cid (Country ID) - ignored, missing or not.
     */
    private static boolean isValidKey(String key, String value) {
        boolean isValid = false;
        switch(key) {
            case "byr" :
                isValid = isValidYear(value, 1920, 2002);
                break;
            case "iyr":
                isValid = isValidYear(value, 2010, 2020);
                break;
            case "eyr":
                isValid = isValidYear(value, 2020, 2030);
                break;
            case "hgt":
                isValid = isValidHeight(value);
                break;
            case "hcl":
                isValid = isValidColor(value);
                break;
            case "ecl":
                isValid = isValidEyeColor(value);
                break;
            case "pid":
                isValid = isValidPassportNumber(value);
                break;
            case "cid":
                isValid = true;
                break;
        }
        return isValid;
    }

   private static boolean isValidYear(String yearStr, int start, int end) {
       boolean isValid = false;
       try {
           Integer year = Integer.parseInt(yearStr);
           if (yearStr.length() == 4 && year >= start && year <= end) {
               isValid = true;
           }
       } catch (Exception ex) {
           isValid = false;
       }
       return isValid;
   }

   /*
    hgt (Height) - a number followed by either cm or in:
    If cm, the number must be at least 150 and at most 193.
    If in, the number must be at least 59 and at most 76.
    */
   private static boolean isValidHeight(String height) {
        int index = height.indexOf("cm");
        if (index != -1) {
            int h = Integer.parseInt(height.substring(0, index));
            return h >= 150 && h <= 193;
        }
       index = height.indexOf("in");
       if (index != -1) {
           int h = Integer.parseInt(height.substring(0, index));
           return h >= 59 && h <= 76;
       }
       return false;
   }

   /*
   hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
    */
   private static boolean isValidColor(String colorStr) {
       if (colorStr == null || colorStr.isEmpty()) {
           return false;
       }
       Matcher m = colorPattern.matcher(colorStr);
       return m.matches();
   }

    /*
    ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
     */
    private static boolean isValidEyeColor(String colorStr) {
        if (colorStr == null || colorStr.isEmpty()) {
            return false;
        }
        if (colorStr.length() != 3) return false;
        return eyeColors.contains(colorStr);
    }

    /*
     pid (Passport ID) - a nine-digit number, including leading zeroes.
     */
    private static boolean isValidPassportNumber(String number) {
        if (number == null || number.isEmpty()) {
            return false;
        }
        if (number.length() != 9) return false;
        for (int i = 0; i < 9; i++) {
            boolean isDigit = Character.isDigit(number.charAt(i));
            if (!isDigit) return false;
        }
        return true;
    }
}
