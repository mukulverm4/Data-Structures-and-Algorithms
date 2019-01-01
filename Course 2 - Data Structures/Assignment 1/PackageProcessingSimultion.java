import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;

class Request {
    public Request(int arrivalTime, int processTime) {
        this.arrivalTime = arrivalTime;
        this.processTime = processTime;
    }

    public int arrivalTime;
    public int processTime;
}

class Response {
    public Response(boolean dropped, int startTime) {
        this.dropped = dropped;
        this.startTime = startTime;
    }

    public boolean dropped;
    public int startTime;
}

class Buffer {
    public Buffer(int size) {
        this.size = size;
        this.finishTime = new ArrayDeque<Integer>();
    }

    public Response Process(Request request) {
    	for(Integer item:finishTime) {
    		if(!(request.arrivalTime<item)) finishTime.remove();
    		else break;
    	}
    
    	if(!(this.size>finishTime.size())) {
    		return new Response(true, -1);
    	}
    	else if(finishTime.isEmpty()) {
    		finishTime.add(request.arrivalTime+request.processTime);
    		return new Response(false,request.arrivalTime);
    	}
    	else {
    		Response response = new Response(false,finishTime.getLast());
    		finishTime.add(finishTime.getLast()+request.processTime);
    		return response;
    	}
		
    }

    private int size;
    private Deque<Integer> finishTime;
}
/* 
    Input Format. The first line of the input contains the size 𝑆 of the buffer and the number 𝑛 of incoming
    network packets. Each of the next 𝑛 lines contains two numbers. 𝑖-th line contains the time of arrival
    𝐴𝑖 and the processing time 𝑃𝑖 (both in milliseconds) of the 𝑖-th packet. It is guaranteed that the
    sequence of arrival times is non-decreasing (however, it can contain the exact same times of arrival in
    milliseconds — in this case the packet which is earlier in the input is considered to have arrived earlier).

    Output Format. For each packet output either the moment of time (in milliseconds) when the processor
    began processing it or −1 if the packet was dropped (output the answers for the packets in the same
    order as the packets are given in the input).
    
*/

class PackageProcessingSimultion {
    private static ArrayList<Request> ReadQueries(Scanner scanner) throws IOException {
        int requestsCount = scanner.nextInt();
        ArrayList<Request> requests = new ArrayList<Request>();
        for (int i = 0; i < requestsCount; ++i) {
            int arrivalTime = scanner.nextInt();
            int processTime = scanner.nextInt();
            requests.add(new Request(arrivalTime, processTime));
        }
        return requests;
    }

    private static ArrayList<Response> ProcessRequests(ArrayList<Request> requests, Buffer buffer) {
        ArrayList<Response> responses = new ArrayList<Response>();
        for (int i = 0; i < requests.size(); ++i) {
            responses.add(buffer.Process(requests.get(i)));
        }
        return responses;
    }

    private static void PrintResponses(ArrayList<Response> responses) {
        for (int i = 0; i < responses.size(); ++i) {
            Response response = responses.get(i);
            if (response.dropped) {
                System.out.println(-1);
            } else {
                System.out.println(response.startTime);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int BufferMaxSize = scanner.nextInt();
        Buffer buffer = new Buffer(BufferMaxSize);

        ArrayList<Request> requests = ReadQueries(scanner);
        ArrayList<Response> responses = ProcessRequests(requests, buffer);
        PrintResponses(responses);
    }
}
