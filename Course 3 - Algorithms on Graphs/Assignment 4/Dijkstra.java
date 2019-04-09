import java.util.*;

/*

Task. Given an directed graph with positive edge weights and with n vertices and m edges as well as two
vertices u and v, compute the weight of a shortest path between u and v (that is, the minimum total
weight of a path from u to v).
Input Format. A graph is given in the standard format. The next line contains two vertices u and v.
Constraints. 1 ≤ n ≤ 10 3 , 0 ≤ m ≤ 10 5 , u 6 = v, 1 ≤ u, v ≤ n, edge weights are non-negative integers not
exceeding 10 3 .
Output Format. Output the minimum weight of a path from u to v, or −1 if there is no path.

*/

public class Dijkstra {
	private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {

		int[] distance = new int[adj.length];
		int[] previous = new int[adj.length];
		boolean[] visited = new boolean[adj.length];

		for (int i = 0; i < adj.length; i++) {
			distance[i] = Integer.MAX_VALUE;
			previous[i] = -1;
		}
		distance[s] = 0;
		int minIndex = s;
		while(minIndex!=Integer.MAX_VALUE) {
	

			if(minIndex!= Integer.MAX_VALUE) {
				for (int i = 0; i < adj[minIndex].size(); i++) {
					if(distance[adj[minIndex].get(i)]>distance[minIndex]+cost[minIndex].get(i))
						distance[adj[minIndex].get(i)] = distance[minIndex] + cost[minIndex].get(i);
				}
			}

			visited[minIndex] = true;
			minIndex = extractMin(distance, visited);
		}


		return distance[t]==Integer.MAX_VALUE?-1:distance[t];
	}

	private static int extractMin(int[] distance, boolean[] visited) {

		int min = Integer.MAX_VALUE;
		for (int i = 0; i < distance.length; i++) {
			if(distance[i]<min && !visited[i])
				min = i;
		}

		return min;
	}



	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
		ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<Integer>();
			cost[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < m; i++) {
			int x, y, w;
			x = scanner.nextInt();
			y = scanner.nextInt();
			w = scanner.nextInt();
			adj[x - 1].add(y - 1);
			cost[x - 1].add(w);
		}
		int x = scanner.nextInt() - 1;
		int y = scanner.nextInt() - 1;
		System.out.println(distance(adj, cost, x, y));
	}
}
