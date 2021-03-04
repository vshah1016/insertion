import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

public class Main {
    private static final Client client = new Client();
    private static final Data data = new Data(new Integer[25]);

    public static void main(String[] args) {
        Supplier<Integer> choiceSupplier = client::prompt;
        int choice = choiceSupplier.get();
        while (client.isRunAgain()) {
            switch (choice) {
                case 1 -> System.out.println(inputNumbers() ? "Numbers Inputted!" : "There was an error inputting the numbers.");
                case 3 -> System.out.println(sortedList());
                case 2 -> System.out.println(data.reset() ? "Reset" : "Error.");
                case 4 -> System.out.println(data.isEmpty() ? "There is no mean if there is no data!" : "The mean of the data set is: " + data.mean());
                case 5 -> System.out.println(data.isEmpty() ? "There is no median if there is no data!" : "The median of the data set is: " + data.median());
                case 6 -> System.out.println(data.isEmpty() ? "There are no mode(s) if there is no data!" : "The mode(s) of the data set are: " + (data.modes().size() == 0 ? "none" : data.modes().toString().replace("[", "")
                        .replace("]", "")
                        .replace(" ", "")));
                case 7 -> System.out.println(data.isEmpty() ? "There are no standard deviations if there is no data!" : "The standard deviation is: " + data.standardDeviation());
            }
            choice = choiceSupplier.get();
        }
    }

    private static String sortedList() {
        final String[] sortedList = {""};
        Arrays.stream(data.list).filter(Objects::nonNull).forEach(i -> sortedList[0] += i + ", ");
        if (sortedList[0].equals("")) return "No Numbers.";
        sortedList[0] = sortedList[0].substring(0, sortedList[0].length() - 2);
        return sortedList[0];
    }

    private static boolean inputNumbers() {
        System.out.print("Input a list of numbers separated with a comma and a space: ");
        String input = client.gatherInput();
        int[] inputNumbers = Arrays.stream(input.split(", ")).mapToInt(Integer::parseInt).toArray();
        if (inputNumbers.length > 25 || inputNumbers.length < 1) return false;
        int startToAddIndex = data.filled() == 0 ? 0 : data.filled() + 1;
        int capacity = 25 - startToAddIndex;
        if (inputNumbers.length > capacity) return false;
        for (int inputNumber : inputNumbers) {
            data.list[startToAddIndex] = inputNumber;
            startToAddIndex++;
        }
        data.sort();
        return true;
    }
}
