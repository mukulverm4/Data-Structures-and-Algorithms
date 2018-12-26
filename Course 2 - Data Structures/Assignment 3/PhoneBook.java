import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
    Input Format. There is a single integer 𝑁 in the first line — the number of queries. It’s followed by 𝑁
    lines, each of them contains one query in the format described above.

    Output Format. Print the result of each find query — the name corresponding to the phone number or
    “not found" (without quotes) if there is no person in the phone book with such phone number. Output
    one result per line in the same order as the find queries are given in the input.
*/

public class PhoneBook {

    private FastScanner in = new FastScanner();
    // Keep list of all existing (i.e. not deleted yet) contacts.
    private Contact[] pBook = new Contact[10000000];

    public static void main(String[] args) {
        new PhoneBook().processQueries();
    }

    private Query readQuery() {
        String type = in.next();
        int number = in.nextInt();
        if (type.equals("add")) {
            String name = in.next();
            return new Query(type, name, number);
        } else {
            return new Query(type, number);
        }
    }


    private void processQuery(Query query) {
        if (query.type.equals("add")) {
        	pBook[query.number]=new Contact(query.name,query.number);
        } else if (query.type.equals("del")) {
        	pBook[query.number] = null;
        } else {
            String response = "not found";
            if(pBook[query.number]!=null) response = pBook[query.number].name;
            System.out.println(response);
        }
    }

    public void processQueries() {
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i)
            processQuery(readQuery());
    }

    static class Contact {
        String name;
        int number;

        public Contact(String name, int number) {
            this.name = name;
            this.number = number;
        }
    }

    static class Query {
        String type;
        String name;
        int number;

        public Query(String type, String name, int number) {
            this.type = type;
            this.name = name;
            this.number = number;
        }

        public Query(String type, int number) {
            this.type = type;
            this.number = number;
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
