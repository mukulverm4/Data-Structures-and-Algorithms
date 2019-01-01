import java.util.ArrayList;
import java.util.Scanner;

/*
	Task. Given an undirected graph and two distinct vertices u and v, check if there is a path between u and v.
	Input Format. An undirected graph with n vertices and m edges. The next line contains two vertices u
	and v of the graph.
	Constraints. 2 ≤ n ≤ 10 3 ; 1 ≤ m ≤ 10 3 ; 1 ≤ u, v ≤ n; u ̸ = v.
	Output Format. Output 1 if there is a path between u and v and 0 otherwise.
*/

public class Reachability {
    private static int reach(ArrayList<Integer>[] adj, int x, int y) {
    	int visited[] = new int[adj.length];
    	
    	explore(adj, visited, x);
    	if(visited[y]==0) return 0;
    	 	else return 1;
    }
    
    private static  void explore(ArrayList<Integer>[] adj, int[] visited, int curr) {
    
    	for(Integer i : adj[curr]) {
    		if(visited[i]==0) {
    			visited[i]=1;
    			explore(adj, visited, i);
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
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(reach(adj, x, y));
    }
}

