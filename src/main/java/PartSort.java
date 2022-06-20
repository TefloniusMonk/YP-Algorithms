import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class PartSort {

    public static int countMaxBlocks(List<Integer> arr) {
        var maxBlocks = 1;
        int startOfBlock = 0;
        int endOfBlock = 1;
        while (endOfBlock < arr.size()) {
            final int maxLeft = maxLeft(arr, startOfBlock, endOfBlock);
            if (allMoreOrEqThan(arr, endOfBlock, maxLeft)) {
                startOfBlock = endOfBlock;
                maxBlocks++;
            }
            endOfBlock++;
        }
        return maxBlocks;
    }

    private static int maxLeft(List<Integer> arr, int startOfBlock, int endOfBlock) {
        int max = Integer.MIN_VALUE;
        for (int i = startOfBlock; i < endOfBlock; i++) {
            if (arr.get(i) > max) {
                max = arr.get(i);
            }
        }
        return max;
    }

    public static boolean allMoreOrEqThan(List<Integer> arr, int idx, int max) {
        for (int i = idx; i < arr.size(); i++) {
            if (arr.get(i) < max) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        try (final var reader = new BufferedReader(new InputStreamReader(System.in))) {
            final var size = Integer.parseInt(reader.readLine());
            final var arr = readList(reader, size + 100);
            System.out.println(countMaxBlocks(arr));
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readList(BufferedReader reader, int size) throws IOException {
        final var lst = new ArrayList<Integer>(size);
        final var st = new StringTokenizer(reader.readLine());
        while (st.hasMoreTokens()) {
            lst.add(Integer.parseInt(st.nextToken()));
        }
        return lst;
    }
}