import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/*
Task. Compute a topological ordering of a given directed acyclic graph (DAG) with n vertices and m edges.
Input Format. A graph is given in the standard format.
Constraints. 1 ≤ n ≤ 10 5 , 0 ≤ m ≤ 10 5 . The given graph is guaranteed to be acyclic.
Output Format. Output any topological ordering of its vertices.
*/

public class Toposort {
	private static Stack<Integer> toposort(ArrayList<Integer>[] adj) {
		int used[] = new int[adj.length];
		Stack<Integer> order = new Stack<Integer>();

		for(int i=0;i<adj.length;i++) {
			if(used[i]==0) { 
				used[i]=1;
				dfs(adj, used, order, i);
			}
		}

		return order;
	}

	private static void dfs(ArrayList<Integer>[] adj, int[] used, Stack<Integer> order, int s) {

		for(int i = 0;i<adj[s].size();i++) {
			int vertex = adj[s].get(i);
			if(used[vertex]==0) {
				used[vertex]=1;
				dfs(adj, used, order, vertex);
			}	
		}
		order.push(s);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < m; i++) {
			int x, y;
			x = scanner.nextInt();
			y = scanner.nextInt();
			adj[x - 1].add(y - 1);
		}
		Stack<Integer> order = toposort(adj);
		while (!order.isEmpty()) {
			System.out.print((order.pop() + 1) + " ");
		}
	}
}

