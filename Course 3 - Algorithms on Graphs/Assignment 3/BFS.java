import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
	Task. Given an undirected graph with n vertices and m edges and two vertices u and v, compute the length
	of a shortest path between u and v (that is, the minimum number of edges in a path from u to v).
	Input Format. A graph is given in the standard format. The next line contains two vertices u and v.
	Constraints. 2 ≤ n ≤ 10 5 , 0 ≤ m ≤ 10 5 , u 6 = v, 1 ≤ u, v ≤ n.
	Output Format. Output the minimum number of edges in a path from u to v, or −1 if there is no path.
*/

public class BFS {
    private static int distance(ArrayList<Integer>[] adj, int s, int t) {
        int[] dist = new int[adj.length];
        for(int i=0; i<dist.length;i++){
            dist[i]=-1;
        }
        dist[s]=0;
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(s);
        while(q.size()!=0){
            int u = q.poll();
            for(Integer i : adj[u]){
                if(dist[i]==-1){
                    dist[i]=dist[u]+1;
                    q.add(i);
                }
            }

        }
        return dist[t];
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
        System.out.println(distance(adj, x, y));
        scanner.close();
    }
}

