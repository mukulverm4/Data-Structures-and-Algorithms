import java.util.*;
import java.io.*;

/*

Input Format. The first line of the input contains the number 𝑞 of queries. Each of the following 𝑞 lines
specifies a query of one of the following formats: push v, pop, or max.

Output Format. For each max query, output (on a separate line) the maximum value of the stack.

*/
public class StackWithMax {
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

    public void solve() throws IOException {
        FastScanner scanner = new FastScanner();
        int queries = scanner.nextInt();
        //Stack<Integer> stack = new Stack<Integer>();

        // using just the auxiliary stack
        Stack<Integer> auxStack = new Stack<Integer>();

        for (int qi = 0; qi < queries; ++qi) {
            String operation = scanner.next();
            if ("push".equals(operation)) {
                int value = scanner.nextInt();
               // stack.push(value);
                if(auxStack.isEmpty()||auxStack.peek()<value) auxStack.push(value);
                else {
                	auxStack.push(auxStack.peek());
                }
            } else if ("pop".equals(operation)) {
               // stack.pop();
                auxStack.pop();
            } else if ("max".equals(operation)) {
                System.out.println(auxStack.peek());
            }
        }
    }

    static public void main(String[] args) throws IOException {
        new StackWithMax().solve();
    }
}
