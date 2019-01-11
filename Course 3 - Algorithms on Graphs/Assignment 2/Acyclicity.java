import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/*

Task. Check whether a given directed graph with n vertices and m edges contains a cycle.
Input Format. A graph is given in the standard format.
Constraints. 1 ≤ n ≤ 10 3 , 0 ≤ m ≤ 10 3 .
Output Format. Output 1 if the graph contains a cycle and 0 otherwise.

*/

public class Acyclicity {
    private static int acyclic(ArrayList<Integer>[] adj) {
        int[] visited = new int[adj.length];
    
        for(int i = 0;i<adj.length;i++){
            
           
            if(visited[i]==0) {
            	HashSet<Integer> set = new HashSet<>();
            	set.add(i);
            	visited[i] = 1;
            	if(!isAcyclicDFS(adj, visited,i,set))
                return 1;
            }
            }
             
        return 0;
    }

    private static boolean isAcyclicDFS(ArrayList<Integer>[] adj, int[] visited, int curr, HashSet<Integer> set){
            
            boolean acyclic = true;

            for(int j =0;j<adj[curr].size();j++){
                int vertex = adj[curr].get(j);
                
                if(set.contains(vertex)) { acyclic = false;
                break;}
                
                else {
                	if(visited[vertex]==1) {
                		set.add(vertex);
                		acyclic = isAcyclicDFS(adj, visited, vertex, set);
                		if(!acyclic) break;
                	}
                	set.remove(vertex);
                	}
            }

            return acyclic;
        
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
        System.out.println(acyclic(adj));
    }
}

