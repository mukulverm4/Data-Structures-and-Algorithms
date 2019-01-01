import java.util.ArrayList;
import java.util.Scanner;

/*
	Task. Given an undirected graph with n vertices and m edges, compute the number of connected components
	in it.
	Input Format. A graph is given in the standard format.
	Constraints. 1 ≤ n ≤ 10 3 , 0 ≤ m ≤ 10 3 .
	Output Format. Output the number of connected components.
*/

public class ConnectedComponents {
    private static int numberOfComponents(ArrayList<Integer>[] adj) {
        int result = 0;
        int count = 0;
        int[] visited = new int[adj.length];
        
        for(int i =0;i<adj.length;i++) {
        	
        	if(visited[i]==0) {count++;
        	explore(adj, visited, i,count);}
        }
        
        return count;
    }
    private static  void explore(ArrayList<Integer>[] adj, int[] visited, int curr,int count) {
        
    	for(Integer i : adj[curr]) {
    		if(visited[i]==0) {
    			visited[i]=1;
    			explore(adj, visited, i,count);
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
        System.out.println(numberOfComponents(adj));
    }
}

