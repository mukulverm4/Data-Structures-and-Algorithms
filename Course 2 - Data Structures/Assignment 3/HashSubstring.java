import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
    Input Format. There are two strings in the input: the pattern 𝑃 and the text 𝑇.

    Output Format. Print all the positions of the occurrences of 𝑃 in 𝑇 in the ascending order. Use 0-based
    indexing of positions in the the text 𝑇.
*/

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }

    private static List<Integer> getOccurrences(Data input) {
        String s = input.pattern, t = input.text;
        return rabinKarp(t, s);
    }
    
    private static List<Integer> rabinKarp(String T, String P) {
    	int prime = 500107 ;
    	double xx = Math.random()*(prime-1);
    	int x = (int) xx+1;
    	
    	ArrayList<Integer> result = new ArrayList<>();
    	int pHash = hashFunc(P,prime,x);
    	long[] H = precomputeHashes(T, P.length(), prime, x);
    	
    	for(int i=0;i<T.length()-P.length()+1;i++) {
    		if(pHash!=H[i]) continue;
    		if(P.equals(T.substring(i, i+P.length()))) result.add(i);
    	}
    	
		return result;
    	
    }
    private static int hashFunc(String s, int prime, int multiplier) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int)hash;
    }
    
    private static long[] precomputeHashes(String T, int P, int prime, int x) {
		long[] H = new long[T.length()-P+1];
		String S = T.substring(T.length()-P, T.length());
    	H[T.length()-P]= hashFunc(S, prime, x);
    	long y =1;
    	for (int i = 1; i < P+1; i++) {
			y = (y*x)%prime;
		}
    	for (int j = T.length()-P-1; j > -1; j--) {
			H[j] = (x*H[j+1]+T.charAt(j)-(int)y*T.charAt(j+P)%prime+prime)%prime;
		}
    	return H;
    	
    }

    static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

