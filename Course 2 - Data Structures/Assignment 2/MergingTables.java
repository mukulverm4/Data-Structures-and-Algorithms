import java.io.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

/*
    Input Format. The first line of the input contains two integers 𝑛 and 𝑚 — the number of tables in the
    database and the number of merge queries to perform, respectively.
    The second line of the input contains 𝑛 integers 𝑟𝑖 — the number of rows in the 𝑖-th table.
    Then follow 𝑚 lines describing merge queries. Each of them contains two integers 𝑑𝑒𝑠𝑡𝑖𝑛𝑎𝑡𝑖𝑜𝑛𝑖 and
    𝑠𝑜𝑢𝑟𝑐𝑒𝑖 — the numbers of the tables to merge.

    Output Format. For each query print a line containing a single integer — the maximum of the sizes of all
    tables (in terms of the number of rows) after the corresponding operation.

*/

public class MergingTables {
    private final InputReader reader;
    private final OutputWriter writer;

    public MergingTables(InputReader reader, OutputWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        OutputWriter writer = new OutputWriter(System.out);
        new MergingTables(reader, writer).run();
        writer.writer.flush();
    }

    class Table {
        Table parent;
        int rank;
        int numberOfRows;
        int actualParent;
        int id;

        Table(int numberOfRows, int id) {
            this.numberOfRows = numberOfRows;
            rank = 0;
            parent = this;
            actualParent = -1; 	
            this.id = id;
        }

        // recursion results in overflow, so iteration used.
        Table getParent() {

            Table superRoot = this, i = this;
            while (superRoot != superRoot.parent) {
                superRoot = superRoot.parent;
            }
            while (i != superRoot) {
                Table oldParent = i.parent;
                i.parent = superRoot;
                i = oldParent;
            }
            return superRoot;
        }
    }

    int maximumNumberOfRows = -1;

    void merge(Table destination, Table source) {
    	
        Table realDestination = destination.getParent();
        Table realSource = source.getParent();
        
        if (realDestination == realSource) {
            return;
        }

        realDestination.numberOfRows = realSource.numberOfRows+realDestination.numberOfRows;
        realSource.numberOfRows = 0;
       
        realSource.parent = realDestination;
        
        // update maximumNumberOfRows
        maximumNumberOfRows = Math.max(maximumNumberOfRows,realDestination.numberOfRows);
    }
    

    public void run() {
        int n = reader.nextInt();
        int m = reader.nextInt();
        Table[] tables = new Table[n];
        for (int i = 0; i < n; i++) {
            int numberOfRows = reader.nextInt();
            tables[i] = new Table(numberOfRows,i+1 );
            maximumNumberOfRows = Math.max(maximumNumberOfRows, numberOfRows);
        }
       
        tableSet = tables;
        
        	for (int i = 0; i < m; i++) {
            int destination = reader.nextInt() - 1;
            int source = reader.nextInt() - 1;
            merge(tables[destination], tables[source]);
            writer.printf(maximumNumberOfRows);
        }
    }


    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class OutputWriter {
        public PrintWriter writer;

        OutputWriter(OutputStream stream) {
            writer = new PrintWriter(stream);
        }

        public void printf(int x) {
            writer.println(x);
        }
    }
}
