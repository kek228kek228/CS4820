import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
 
class Main
{
    private int[] parent;
    private Queue<Integer> queue;
    private int n;
    private boolean[] visited;
    private Graph graph;
    private Graph residgraph;
 
    public Main(int n)
    {
        this.n = n;
        this.queue = new LinkedList<Integer>();
        parent = new int[n + 1];
        visited = new boolean[n + 1];
        graph=new Graph (n);
        residgraph=new Graph (n);
    }
    public class Graph {
    	int[][] edgeweights;
    	public Graph (int n) {
    		edgeweights=new int[n+1][n+1];
    	}
    	
    }
    public void addedge (int source, int dest, int weight) {
    	graph.edgeweights[source][dest]=weight;
    	residgraph.edgeweights[source][dest]=weight;
    }
 
    public boolean bfs(int source, int goal)
    {
        boolean pathFound = false;
        int destination;
        int element;
 
        for(int vertex = 1; vertex <= n; vertex++)
        {
            parent[vertex] = -1;
            visited[vertex] = false;
        }
 
        queue.add(source);
        parent[source] = -1;
        visited[source] = true;
 
        while (!queue.isEmpty())
        { 
            element = queue.remove();
            destination = 1;
 
            while (destination <= n)
            {
                if (residgraph.edgeweights[element][destination] > 0 &&  !visited[destination])
                {
                    parent[destination] = element;
                    queue.add(destination);
                    visited[destination] = true;
                }
                destination++;
            }
        }
        if(visited[goal])
        {
            pathFound = true;
        }
        return pathFound;
    }
 
    public int fordfulkerson(int source, int destination)
    {
        int u;
        int v;
        int maxFlow = 0;
        int pathFlow;
 
        for (int sourceVertex = 1; sourceVertex <= n; sourceVertex++)
        {
            for (int destinationVertex = 1; destinationVertex <= n; destinationVertex++)
            {
                residgraph.edgeweights[sourceVertex][destinationVertex] = graph.edgeweights[sourceVertex][destinationVertex];
            }
        }
 
        while (bfs(source ,destination))
        {
            pathFlow = Integer.MAX_VALUE;
            for (v = destination; v != source; v = parent[v])
            {
                u = parent[v];
                if (pathFlow>residgraph.edgeweights[u][v]) {
                	pathFlow=residgraph.edgeweights[u][v];
                }
            }
            for (v = destination; v != source; v = parent[v])
            {
                u = parent[v];
                residgraph.edgeweights[u][v] = residgraph.edgeweights[u][v]-pathFlow;
                residgraph.edgeweights[v][u] = residgraph.edgeweights[v][u] +pathFlow;
            }
            maxFlow =maxFlow + pathFlow;	
        }
 
        return maxFlow;
    }
 
    public static void main(String [] args)
    {
        int[][] graph;
        int numberOfNodes;
        int numberofEdges;
        int source;
        int sink;
        int maxFlow;
 
        Scanner scanner = new Scanner(System.in);
        numberOfNodes = scanner.nextInt();
        numberofEdges = scanner.nextInt();
        Main fordFulkerson = new Main(numberOfNodes);
 
        for (int s = 1; s <= numberofEdges; s++)
        {
        	int sourceVertex=scanner.nextInt();
        	int destinationVertex=scanner.nextInt();
        	
        	fordFulkerson.addedge(sourceVertex,destinationVertex,scanner.nextInt());
        }
 
        source= 1;
 

        sink = 2;
 

        maxFlow = fordFulkerson.fordfulkerson(source, sink);
        System.out.println(maxFlow);
        scanner.close();
    }
}