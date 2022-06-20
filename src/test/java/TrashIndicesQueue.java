import java.io.*;
import java.util.*;

//
public class TrashIndicesQueue
{
    public static int findKthMin(int[] arr, int k){
        final PriorityQueue<Integer> queue = new PriorityQueue();
        for(int i = 0; i < arr.length; i++){
            for( int j = i + 1; j < arr.length; j++){

                final int dif = Integer.MAX_VALUE - Math.abs(arr[i] - arr[j]);
                if (queue.isEmpty()) {
                    queue.add(dif);
                    continue;
                }

                queue.add(dif);
                if (queue.size() > k) queue.poll();

            }
        }
        return Integer.MAX_VALUE - queue.poll();
    }

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int size = Integer.parseInt(reader.readLine());
            final int[] arr = readList(reader, size);
            final int k = Integer.parseInt(reader.readLine());
            System.out.println(findKthMin(arr, k));
        }
    }

    private static int[] readList(BufferedReader reader, int size) throws IOException {
        final int[] arr = new int[size];
        int i =0;
        final StringTokenizer st = new StringTokenizer(reader.readLine());
        while (st.hasMoreTokens()) {
            arr[i] = Integer.parseInt(st.nextToken());
            i++;
        }
        return arr;
    }
}