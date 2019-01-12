package algorithmsOnGraphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
	Task. Given an undirected graph with n vertices and m edges, check whether it is bipartite.
	Input Format. A graph is given in the standard format.
	Constraints. 1 ≤ n ≤ 10 5 , 0 ≤ m ≤ 10 5 .
	Output Format. Output 1 if the graph is bipartite and 0 otherwise.
*/

public class Bipartite {
    private static int bipartite(ArrayList<Integer>[] adj) {
        
        int[] party = new int[adj.length];
        for(int i=0; i<party.length;i++){
            party[i]=-1;
        }
        party[0]=0;
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(0);
        while(q.size()!=0){
            int u = q.poll();
            for(Integer i : adj[u]){
            	if(party[i]==party[u]) {
            		return 0;
            	}
                if(party[i]==-1){
                	party[i]=party[u]==0?1:0;
                    q.add(i);
                }
            }

        }
        return 1;
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
        System.out.println(bipartite(adj));
    }
}
