
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
    of the tree. The third line should contain the keys of the vertices in the post-order traversal of the tree.
*/

public class is_bst {
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

    public class IsBST {
        class Node {
            int key;
            int left;
            int right;

            Node(int key, int left, int right) {
                this.left = left;
                this.right = right;
                this.key = key;
            }
        }

        int nodes;
        Node[] tree;
        long max;
        boolean maxInitialized = false;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[nodes];
            for (int i = 0; i < nodes; i++) {
                tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
            }
        }

        boolean solve(int j) {
        	maxInitialized=false;
            if(tree.length==0) return true;
            else  return inOrder(tree[0]);
        }
        
		boolean inOrder(Node root) {
			boolean result = true;
            if(root.left!=-1) 
                result = inOrder(tree[root.left]);
			if(result) {
				if(!maxInitialized) {
                    max = root.key;
				    maxInitialized = true;
				}
			if(max>root.key) 
				return false;
            else 
                max = root.key;
			}
			if(result) {
            if(root.right!=-1) 
                result = inOrder(tree[root.right]);			
			}
			return result;
		}
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new is_bst().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        IsBST tree = new IsBST();
        tree.read();
        if (tree.solve(0)) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
}
