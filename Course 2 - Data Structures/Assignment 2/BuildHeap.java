import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* 
    Input Format. The first line of the input contains single integer 𝑛. 
    The next line contains 𝑛 space-separated integers 𝑎𝑖.

    Output Format. The first line of the output should contain single integer 𝑚 — the total number of swaps.
    𝑚 must satisfy conditions 0 ≤ 𝑚 ≤ 4𝑛. The next 𝑚 lines should contain the swap operations used
    to convert the array 𝑎 into a heap. Each swap is described by a pair of integers 𝑖, 𝑗 — the 0-based
    indices of the elements to be swapped. After applying all the swaps in the specified order the array
    must become a heap, that is, for each 𝑖 where 0 ≤ 𝑖 ≤ 𝑛 − 1 the following conditions must be true:
    1. If 2𝑖 + 1 ≤ 𝑛 − 1, then 𝑎𝑖 < 𝑎2𝑖+1.
    2. If 2𝑖 + 2 ≤ 𝑛 − 1, then 𝑎𝑖 < 𝑎2𝑖+2.
    Note that all the elements of the input array are distinct. Note that any sequence of swaps that has
    length at most 4𝑛 and after which your initial array becomes a correct heap will be graded as correct.

*/
public class BuildHeap {
    private int[] data;
    private List<Swap> swaps;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
          data[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
          out.println(swap.index1 + " " + swap.index2);
        }
    }

    private void generateSwaps() {
      swaps = new ArrayList<Swap>();
      for (int i = (data.length-1)/2; i > -1; i--) {
    	  ShiftDown(i);
      }
    }
    
    private void ShiftDown(int i) {
		int minIndex = i;
		int leftChild = 2*i+1;
		if(leftChild<data.length && data[leftChild]<data[minIndex]) {
			minIndex = leftChild;
		}
		int rightChild = 2*i+2;
		if(rightChild<data.length && data[rightChild]<data[minIndex]) {
			minIndex = rightChild;
		}
		if(i!=minIndex) {
			swaps.add(new Swap(i,minIndex));
			int temp = data[i];
			data[i] = data[minIndex];
			data[minIndex] = temp;
			ShiftDown(minIndex);
		}
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        generateSwaps();
        writeResponse();
        out.close();
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
