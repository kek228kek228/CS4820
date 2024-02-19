import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.LinkedList;
class Main {
    static class Edge {
        int source;
        int destination;
        int weight;
        int edgenum;

        public Edge(int source, int destination, int weight, int edgenum) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
            this.edgenum=edgenum;
        }
    }
    static class Vertex{
    	int vertexnum;
    	int minedge;
    	ArrayList<Edge> connectededges=new ArrayList<Edge>();
    	Vertex(int vnum){
    		vertexnum=vnum;
    		minedge=Integer.MAX_VALUE;
    	}
    	public void addnewEdge(int self,int dest, int weight, int enumb) {
    		connectededges.add(new Edge(self,dest,weight,enumb));
    	}
    }
    static class Graph2{
    	int N;
    	ArrayList<Vertex> vertexlist= new ArrayList<>();
    	Graph2(int num) {
    		N=num;
    	}
    	public void addVertex(int i) {
            Vertex vertex = new Vertex(i);
            vertexlist.add(vertex);
        }
    	public void addEdge(int src, int dest, int weight, int enumb) {
    		vertexlist.get(src).addnewEdge(src,dest, weight, enumb);
    		vertexlist.get(dest).addnewEdge(dest,src,weight, enumb);
    	}
        public void prim(int M){
        	boolean[] verticesadded=new boolean[N];
        	Comparator<Edge> EdgeComparator = new Comparator<Edge>() {
                @Override
                public int compare(Edge e1, Edge e2) {
                	if (e1.weight!=e2.weight) {
                		return e1.weight-e2.weight;
                	}
                	return e1.edgenum-e2.edgenum;
                }
            };
        	PriorityQueue<Edge> pq = new PriorityQueue<>(M, EdgeComparator);
        	verticesadded[0]=true;
        	for(int i=0;i<vertexlist.get(0).connectededges.size();i++) {
        		pq.add(vertexlist.get(0).connectededges.get(i));
        	}
        	int total=1;
        	int[]edgenums=new int[N-1];
        	while (total<N) {
        		Edge e=pq.remove();
        		if(verticesadded[(e.destination)]==true) {
        		}
        		else {
        			edgenums[total-1]=e.edgenum;
        			total=total+1;
        			for(int i=0;i<vertexlist.get(e.destination).connectededges.size();i++) {
                		pq.add(vertexlist.get(e.destination).connectededges.get(i));
                	}
        			verticesadded[e.destination]=true;
        		}
        	}
        	for(int i=0;i<edgenums.length;i++) {
        		System.out.println(Integer.toString(edgenums[i]));
        	}
        	
        }
    }
    static class Graph {
        int N;
        ArrayList<Edge> edgelist = new ArrayList<>();
        Graph(int N) {
            this.N = N;
        }

        public void addEdge(int source, int destination, int weight, int edgenum) {
            Edge edge = new Edge(source, destination, weight, edgenum);
            edgelist.add(edge);
            
        }

        
        public void kruskal(){
        	Comparator<Edge> EdgeComparator = new Comparator<Edge>() {
                @Override
                public int compare(Edge e1, Edge e2) {
                	if (e1.weight!=e2.weight) {
                		return e1.weight-e2.weight;
                	}
                	return e1.edgenum-e2.edgenum;
                }
            };
            PriorityQueue<Edge> pq = new PriorityQueue<>(edgelist.size(), EdgeComparator);

            for (int i = 0; i <edgelist.size() ; i++) {
                pq.add(edgelist.get(i));
            }
            int [] parent = new int[N];
            for (int i = 0; i <N ; i++) {
                parent[i] = i;
            }
            
            ArrayList<Edge> t = new ArrayList<>();

            int index = 0;
            while(index<N-1){
                Edge edge = pq.remove();
                int vertex=edge.source;
                while(parent[vertex]!=vertex) {
                	vertex=parent[vertex];
                }
                int x_set = vertex;
                int vertex2=edge.destination;
                while(parent[vertex2]!=vertex2) {
                	vertex2=parent[vertex2];
                }
                int y_set = vertex2;
                if(x_set==y_set){
                }else {
                    t.add(edge);
                    index=index+1;
                    int vertex3=y_set;
                    while(parent[vertex3]!=vertex3) {
                    	vertex3=parent[vertex3];
                    }
                    int y=vertex3;
                    int vertex4=x_set;
                    while(parent[vertex4]!=vertex4) {
                    	vertex2=parent[vertex4];
                    }
                    int x=vertex4;
                    parent[y] = x;
                }
            }
            for (int i = 0; i <N-1 ; i++) {
                Edge edge = t.get(i);
                System.out.println(edge.edgenum);
            }
            
        }

    }
	public static void main(String [] Args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		int P = scanner.nextInt();
		if(P==1) {
			Graph2 graph2 = new Graph2(N);
			for (int i=0;i<N;i++)
			{
				graph2.addVertex(i);
			}
			for (int i=1;i<=M;i++)
			{
				int X = scanner.nextInt();
				int Y = scanner.nextInt();
				int C = scanner.nextInt();
				graph2.addEdge(X-1, Y-1, C, i);
			}
			graph2.prim(M);

		}
		else {
			Graph graph = new Graph(N);
			for (int i=1;i<=M;i++)
			{
				int X = scanner.nextInt();
				int Y = scanner.nextInt();
				int C = scanner.nextInt();
				graph.addEdge(X-1, Y-1, C, i);
			}
			graph.kruskal();
		}
	}
}
