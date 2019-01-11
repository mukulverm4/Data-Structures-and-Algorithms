import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

/*
Task. Compute the number of strongly connected components of a given directed graph with n vertices and
m edges.
Input Format. A graph is given in the standard format.
Constraints. 1 ≤ n ≤ 10 4 , 0 ≤ m ≤ 10 4 .
Output Format. Output the number of strongly connected components.
*/

public class StronglyConnected {
	private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
		int count = 0;

		ArrayList<Integer>[] revAdj = reverseGraph(adj);

		Stack<Integer> order = new Stack<Integer>();
		int used[] = new int[adj.length];

		for(int i=0;i<adj.length;i++) {
			if(used[i]==0) { 
				used[i]=1;
				dfs(revAdj, used, order, i);
			}
		}
		int visited[] = new int[adj.length];
		while(!order.isEmpty()) {
			Integer vertex = order.pop();

			if(visited[vertex]==0) {
				if(adj[vertex]!=null)
					explore(adj, vertex, visited);
				count++;
			}
		}


		return count;
	}

	public static ArrayList<Integer>[] reverseGraph(ArrayList<Integer>[] adj) {
		ArrayList<Integer>[] revAdj = (ArrayList<Integer>[])new ArrayList[adj.length];

		for(int i = 0;i<adj.length;i++) {
			for(Integer vertex : adj[i]) {
				if(revAdj[vertex]==null)
					revAdj[vertex] = new ArrayList<>();
				revAdj[vertex].add(i); 
			}
		}

		return revAdj;
	}

	private static void dfs(ArrayList<Integer>[] adj, int[] used, Stack<Integer> order, int s) {
		if(adj[s]!=null) {
			for(int i = 0;i<adj[s].size();i++) {
				if(adj[s].get(i)!=null) {
					int vertex = adj[s].get(i);
					if(used[vertex]==0) {
						used[vertex]=1;

						dfs(adj, used, order, vertex);
					}	
				}
			}
		}
		order.push(s);
	}

	private static void explore(ArrayList<Integer>[] adj,Integer vertex, int[] visited) {

		for(Integer i : adj[vertex]) {
			if(visited[i]==0) {
				visited[i]=1;
				if(adj[i]!=null)
					explore(adj, i,visited);
			}
		}

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
		scanner.close();
		System.out.println(numberOfStronglyConnectedComponents(adj));

	}
}



