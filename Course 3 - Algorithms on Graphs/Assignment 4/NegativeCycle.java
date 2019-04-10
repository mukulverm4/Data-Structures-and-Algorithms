import java.util.ArrayList;
import java.util.Scanner;

/*
    Task. Given an directed graph with possibly negative edge weights and with n vertices and m edges, check
    whether it contains a cycle of negative weight.
    Input Format. A graph is given in the standard format.
    Constraints. 1 ≤ n ≤ 10 3 , 0 ≤ m ≤ 10 4 , edge weights are integers of absolute value at most 10 3 .
    Output Format. Output 1 if the graph contains a cycle of negative weight and 0 otherwise.
*/

public class NegativeCycle {
    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        int[] distance = new int[adj.length];
    	int[] previous = new int[adj.length];
    	for (int i = 0; i < adj.length; i++) {
			distance[i] = Integer.MAX_VALUE/10;
			previous[i] = -1;
		}
    	distance[0] = 0;
    	for (int i = 0; i < adj.length; i++) {
			boolean change = false;
			for(int j = 0; j<adj.length;j++){
				for (int k = 0;k<adj[j].size(); k++) {
					if(distance[adj[j].get(k)]>distance[j]+cost[j].get(k)) {
						change = true;
						distance[adj[j].get(k)] = distance[j]+cost[j].get(k);
					}
				}
			}
			if(!change) break;
			if(i==(adj.length-1) && change ) {
				return 1;
			}
		}
    	return 0;
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
        System.out.println(negativeCycle(adj, cost));
    }
}

