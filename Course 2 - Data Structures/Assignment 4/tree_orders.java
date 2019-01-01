import java.util.*;
import java.io.*;

/*
	Input Format. The first line contains the number of vertices 𝑛. The vertices of the tree are numbered
	from 0 to 𝑛 − 1. Vertex 0 is the root.
	The next 𝑛 lines contain information about vertices 0, 1, ..., 𝑛−1 in order. Each of these lines contains
	three integers 𝑘𝑒𝑦𝑖, 𝑙𝑒𝑓𝑡𝑖 and 𝑟𝑖𝑔ℎ𝑡𝑖 — 𝑘𝑒𝑦𝑖 is the key of the 𝑖-th vertex, 𝑙𝑒𝑓𝑡𝑖 is the index of the left
	child of the 𝑖-th vertex, and 𝑟𝑖𝑔ℎ𝑡𝑖 is the index of the right child of the 𝑖-th vertex. If 𝑖 doesn’t have
	left or right child (or both), the corresponding 𝑙𝑒𝑓𝑡𝑖 or 𝑟𝑖𝑔ℎ𝑡𝑖 (or both) will be equal to −1.

	Output Format. Print three lines. The first line should contain the keys of the vertices in the in-order
	traversal of the tree. The second line should contain the keys of the vertices in the pre-order traversal
*/

public class tree_orders {
    class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}
	
		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}

	public class TreeOrders {
		int n;
		int[] key, left, right;
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			key = new int[n];
			left = new int[n];
			right = new int[n];
			for (int i = 0; i < n; i++) { 
				key[i] = in.nextInt();
				left[i] = in.nextInt();
				right[i] = in.nextInt();
			}
		}

		void inOrder(int i) {
			if(left[i]!=-1) inOrder(left[i]);	
			System.out.print(key[i]+ " ");
			if(right[i]!=-1) inOrder(right[i]);			
		}

		void preOrder(int i) {
			System.out.print(key[i]+ " ");
			if(left[i]!=-1) preOrder(left[i]);	
			if(right[i]!=-1) preOrder(right[i]);	
		}

		void postOrder(int i) {
			if(left[i]!=-1) postOrder(left[i]);		
			if(right[i]!=-1) postOrder(right[i]);	
			System.out.print(key[i]+ " ");
		}
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_orders().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}

	public void print(List<Integer> x) {
		for (Integer a : x) {
			System.out.print(a + " ");
		}
		System.out.println();
	}

	public void run() throws IOException {
		TreeOrders tree = new TreeOrders();
		tree.read();
		tree.inOrder(0);
		System.out.println();
		tree.preOrder(0);
		System.out.println();
		tree.postOrder(0);
		System.out.println();
	}
}
