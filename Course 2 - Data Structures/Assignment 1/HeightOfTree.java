import java.util.*;
import java.io.*;


/*
    Input Format. The first line contains the number of nodes 𝑛. The second line contains 𝑛 integer numbers
    from −1 to 𝑛 − 1 — parents of nodes. If the 𝑖-th one of them (0 ≤ 𝑖 ≤ 𝑛 − 1) is −1, node 𝑖 is the root,
    otherwise it’s 0-based index of the parent of 𝑖-th node. It is guaranteed that there is exactly one root.
    It is guaranteed that the input represents a tree.

    Output Format. Output the height of the tree.

*/

public class HeightOfTree{
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

	public class TreeHeight {
		int n;
		int parent[];
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
			}
		}

		int computeHeight() {

			int maxHeight = 0;
			int visited[] = new int[n];
			int height[] = new int[n];
			for(int i = 0; i<n;i++) {
				int h = recurseHeight(parent,visited,height,i);
				maxHeight = Math.max(h, maxHeight);
			}
			
			return maxHeight;
		}
		
		int recurseHeight(int[] parent,int[] visited, int[] height, int i) {
			if(parent[i]==-1) return 1;
			if(visited[i]==1) return height[i];
			
			visited[i] = 1;
			height[i] = 1 +recurseHeight(parent, visited, height, parent[i]);
			
			return height[i];
			
		}
		
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new HeightOfTree().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight());
		
	}
}
