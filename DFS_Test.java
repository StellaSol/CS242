/*************************************************************************
 *
 *  Pace University
 *  Spring 2020
 *  Algorithms and Computing Theory
 *
 *  Course: CS 242
 *  Team members: Stella Solano
 *  Collaborators: NONE
 *  References: Cormen textbook for pseudocode(DFS) and references, class notes
 *				https://www.baeldung.com/java-graphs : Helped figure out how to create directed graphs
 * 
 *  Assignment: Assignment4
 *  Problem:Depth-First Search with different edge sets
 *  Description: Evaluate experimentally the performance of an efficient implementation of DFS.
 *
 *  Input: Int v and int numEdges
 *  Output: Prints running times
 *
 *  Visible data fields: NONE
 *
 *  Visible methods:
 *  public void setEdge(int to, int from) 
 *	public List<Integer> getEdge(int to) 
 *	public int V() 
 *	public List<Integer> adj(int vertex) 
 *	static void dfs(DFS_Test G) 
 *	static void dfs_visit(DFS_Test G, int u)
 *	static int[] createArray(int len, int val)
 *
 *   Remarks
 *   -------
 *   2) 			          Running Time
 *  	               |	E=|V|-1		|	E=(|V|-1)^3/2	|	E=(|V|-1)^2				
 *   V=10              |	26,105		|	50,240			|	57,305		
 *   V=100             |	64,202		|	82,048			|	67,236
 *   V=1000      	   |	77,756		|	98,487			|	134,724		
 *   
 *   
 *   3) The approximate formula based on the running times would be:  O(|V| + |E|). As the number of vertices and the number of edges increase, so does the running times. There is no exponential growth, but there is linear growth. Hence the running time is still O(|V|+|E|). 
 *************************************************************************/
import java.util.*;

public class DFS_Test {
    private Map<Integer, List<Integer>> adjacencyList;
	private int numVertex;

	//Creating the adjacency list 
    public DFS_Test(int v) 
    {
        adjacencyList = new HashMap<Integer, List<Integer>>();
        for (int i = 1; i <= v; i++) {
            adjacencyList.put(i, new LinkedList<Integer>());
        }
    }
 
    public void setEdge(int to, int from) 
    {
        List<Integer> sls = adjacencyList.get(to);
        sls.add(from);
        List<Integer> dls = adjacencyList.get(from);
        dls.add(to);
    }
 
    public List<Integer> getEdge(int to) 
    {
        return adjacencyList.get(to);
    }
    
    public int V() {
        return numVertex;
    }

    public List<Integer> adj(int v) {
        return adjacencyList.get(v);
    }
    
    // DFS arrays and variables
    static int time; 
    
    //Arrays
    static int[] d;
    static int[] f;
    static int[] color;
    static int[] pi;
    
    //Colors and their values
    static int NIL = -1;
    static int WHITE = 0;
    static int GRAY = 1;
    static int BLACK = 2;

  
    //DFS Algorithm based on pseudocode
    static void DFS(DFS_Test G) {
        int V = G.V();
        for (int u = 0; u < V; u++) {
            color[u]=WHITE;
            pi[u]=NIL;
        }
        time=0;
        for (int u = 0; u < V; u++) {
            if (color[u] == WHITE) {
                DFS_Visit(G, u);
            }
        }
    }

    static void DFS_Visit(DFS_Test G, int u) {
    	//initialize arrays
        int V = G.V();
        List<Integer> adjList = G.adj(u);
        for (int i = 0; i < V; i++) {
            d[i] = 0;
        }
        for (int i = 0; i < V; i++) {
            f[i] = 0;
        }
   
        time = time + 1;
        d[u] = time;
        color[u] = GRAY;
        for (int v : adjList) {
            if (color[v] == WHITE) {
                pi[v] = u;
                DFS_Visit(G, u);
            }
        }
        color[u] = BLACK;
        time = time + 1;
        f[u] = time;
    }
    

    public static void main(String args[]) 
    {
    	//User Inputs number of nodes and edges
    	int numEdges, v;
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter number of nodes: ");
		v=scan.nextInt();
		System.out.println("Please enter number of edges: ");
		numEdges=scan.nextInt();
		scan.close();
		
		//Creating a directed graph
        Random random = new Random();
        DFS_Test G = new DFS_Test(v);
        int count = 1, to, from;
          
        //Setting random edges depending on the number of nodes
        while (count <= numEdges) {
                to = random.nextInt(v) + 1;
                from = random.nextInt(v) + 1;
                G.setEdge(to, from);
                count++;
         } 
         
        //Testing DFS running times
        
		// store the time now
        long startTime = System.nanoTime();
		DFS_Test.DFS(G);        
        // display the time elapsed
        System.out.println("DFS time = " + (System.nanoTime() - startTime) + " nanosecs.");

		} 
         
    }
 
