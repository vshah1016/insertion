import java.util.HashSet;

public class Data {
    public final Integer[] list;

    public Data(Integer[] list) {
        this.list = list;
    }

    public void sort() {
        int length = filled() + 1;
        for (int i = 1; i < length; i++) {
            int temp = list[i];
            int j = i - 1;
            while (j >= 0 && list[j] > temp) {
                list[j + 1] = list[j];
                j = j - 1;
            }
            list[j + 1] = temp;
        }
    }

    public int filled() {
        int startToAddIndex = 0;
        for (int i = 0; i < list.length; i++) if (list[i] != null) startToAddIndex = i;
        return startToAddIndex;
    }

    public boolean reset() {
        for (int i = 0; i < filled() + 1; i++) list[i] = null;
        return true;
    }

    public double mean() {
        double mean = 0;
        int length = filled() + 1;
        for (int i = 0; i < length; i++) mean += list[i];
        return mean / (double) length;
    }

    public double median() {
        int length = filled() + 1;
        if (length % 2 == 0) return (double) (list[length / 2] + list[(length / 2) - 1]) / 2;
        else return list[length / 2];
    }

    public HashSet<Integer> modes() {
        HashSet<Integer> modes = new HashSet<>();
        int highestOccurrences = 1;
        int length = filled() + 1;
        for (int i = 0; i < length; i++) {
            int localOccurrences = 0;
            for (int j = 0; j < length; j++) if (list[i].equals(list[j])) localOccurrences++;
            if (localOccurrences > highestOccurrences) {
                highestOccurrences = localOccurrences;
                modes.clear();
                modes.add(list[i]);
            } else if (localOccurrences == highestOccurrences && highestOccurrences > 1) modes.add(list[i]);
        }
        return modes;
    }

    public double standardDeviation() {
        double standardDeviation = 0;
        for (int i = 0; i < filled() + 1; i++) standardDeviation += Math.pow((list[i] - mean()), 2);
        return Math.pow(standardDeviation / (filled() + 1), 0.5);
    }

    public boolean isEmpty() {
        return filled() == 0;
    }
}
