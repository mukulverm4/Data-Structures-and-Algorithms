import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;


/*
    Input Format. There is a single integer 𝑚 in the first line — the number of buckets you should have. The
    next line contains the number of queries 𝑁. It’s followed by 𝑁 lines, each of them contains one query
    in the format described above.

    Output Format. Print the result of each of the find and check queries, one result per line, in the same
    order as these queries are given in the input.
*/
public class HashChains {

    private FastScanner in;
    private PrintWriter out;
    // store all strings in one list
    private LinkedList<String>[] elems;
    // for hash function
    private int bucketCount;
    private int prime = 1000000007;
    private int multiplier = 263;

    public static void main(String[] args) throws IOException {
        new HashChains().processQueries();
    }

    private int hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int)hash % bucketCount;
    }

    private Query readQuery() throws IOException {
        String type = in.next();
        if (!type.equals("check")) {
            String s = in.next();
            return new Query(type, s);
        } else {
            int ind = in.nextInt();
            return new Query(type, ind);
        }
    }

    private void writeSearchResult(boolean wasFound) {
        out.println(wasFound ? "yes" : "no");
    }

    private void processQuery(Query query) {
    	int currentHash;
    	boolean valueFound = false;
        switch (query.type) {
            case "add":
            	currentHash = hashFunc(query.s);
            		
            		for(String value : elems[currentHash]) {
            			if(value.equals(query.s)) valueFound = true;
            		}
            		if(!valueFound) elems[currentHash].addFirst(query.s);

                break;
            case "del":
            	currentHash = hashFunc(query.s);
            	for (int i = 0; i < elems[currentHash].size(); i++) {
            	   if(elems[currentHash].get(i).equals(query.s)) {
            		   elems[currentHash].remove(i);
            	   }
            	}

                break;
            case "find":
            	currentHash = hashFunc(query.s);
        		for(String value : elems[currentHash]) {
        			if(value.equals(query.s)) valueFound = true;
        		}
            	
                writeSearchResult(valueFound);
                break;
            case "check":
            	
            	
            	for(String value : elems[query.ind]) 
        			 out.print(value + " ");
                out.println();

                break;
            default:
                throw new RuntimeException("Unknown query: " + query.type);
        }
    }

    public void processQueries() throws IOException {
        
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        bucketCount = in.nextInt();
        elems = new LinkedList[bucketCount];
        for(int i = 0;i<bucketCount;i++) elems[i] = new LinkedList<String>();
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i) {
            processQuery(readQuery());
        }
        out.close();
    }

    static class Query {
        String type;
        String s;
        int ind;

        public Query(String type, String s) {
            this.type = type;
            this.s = s;
        }

        public Query(String type, int ind) {
            this.type = type;
            this.ind = ind;
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
