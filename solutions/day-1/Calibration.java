import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class Calibration {
    final static Map<String, Character> numberMap = Map.of("one", '1', "two", '2', "three", '3', "four", '4', "five", '5', "six", '6', "seven", '7', "eight", '8', "nine", '9');
    public static void main(String[] args) {
        try {
            File myObj = new File("./inputs/day-1/input.txt");
            Scanner myReader = new Scanner(myObj);
            int totalCount = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                char firstNum = getFirstNumber(data);
                char lastNum = getLastNumber(data);
                int combinedNum = Integer.valueOf(""+firstNum+lastNum);
                totalCount += combinedNum;
            }
            System.out.println("Total: " + totalCount);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private static char getFirstNumber(String line) {
        for(int i = 0; i < line.length(); i++) {
            char c = assessIndex(line, i);
            if (c != '0') {
                return c;
            }
        }
        return '0';
    }
    private static char getLastNumber(String line) {
        for(int i = line.length()-1; i >= 0; i--) {
            char c = assessIndex(line, i);
            if (c != '0') {
                return c;
            }
        }
        return '0';
    }
    private static char assessIndex(String line, int index) {
        if(numberMap.values().contains(line.charAt(index))) {
            return line.charAt(index);
        }
        int remainingLength = line.length() - index;
        for(String s : numberMap.keySet()) {
            int stringLength = s.length();
            if(stringLength <= remainingLength) {
                if(line.substring(index, index+stringLength).equals(s)) {
                    return numberMap.get(s);
                }
            }
        }
        return '0';
    }
}