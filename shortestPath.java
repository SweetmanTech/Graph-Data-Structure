import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class shortestPath extends adjacency_matrix {

	public static adjacency_matrix adjMatrix;
	public static char[] color;
	public static int[] distance;
	public static int[] parent;
	
	public shortestPath(int numberOfVerts) {
		super(numberOfVerts);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		//Load graph
		loadFile(args);
        BFS(adjMatrix, 0);
        
        //Print results
        printReachableVertices();
		printUnreachableVertices();
	}
	
	/** Retrieves file from user input.
	 *  
	 * @param args command-line arguments
	 */
	public static void loadFile(String[] args) {
		//Initialize Graph
		Scanner keyboard = new Scanner(System.in);
		boolean fileLoaded = false;
		if (args.length < 1) {
			while (!fileLoaded) {
				System.out.println("file location:");
				String fileName = keyboard.nextLine();
				fileLoaded = tryLoadingFile(fileName);
			}
		} else {
			String fileName = args[0];
			fileLoaded = tryLoadingFile(fileName);
		}
		keyboard.close();
	}
	
	/** Attempts to load file
	 * 
	 * @param fileName name of file
	 * @return whether file was successfully read
	 */
	public static boolean tryLoadingFile(String fileName) {
		boolean loaded = false;
		try {
            BufferedReader bufferedReader = 
                new BufferedReader(new FileReader(fileName));
            String line = bufferedReader.readLine();
            adjMatrix = new adjacency_matrix(Integer.parseInt(line));
            line = bufferedReader.readLine();
            while((line) != null) {
            	if (line.length() > 0 ) {
	            	List<String> strVertexList = Arrays.asList(line.split(","));
	            	int start = Integer.parseInt(strVertexList.get(0).trim());
	    			int finish = Integer.parseInt(strVertexList.get(1).trim());
	                adjMatrix.addEdge(start, finish);
            	}
                line = bufferedReader.readLine();
            }   
            loaded = true;
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
        }
		return loaded;
	}
	
	/**Executes Breadth First Search
	 * 
	 * @param graph the graph being searched
	 * @param currentVertex the vertex currently being searched on
	 */
	public static void BFS(adjacency_matrix graph, int currentVertex) {
		int graphSize = graph.numberOfVertices;
		color = new char[graphSize];
		distance = new int[graphSize];
		parent = new int[graphSize];
		for (int i = 0; i < graphSize; i++) {
			color[i] = 'w';
			distance[i] = -1;
			parent[i] = -1;
		}
		color[currentVertex] = 'g';
		distance[currentVertex] = 0;
		parent[currentVertex] = 0;
		Queue<Integer> bfsQueue = new LinkedList<Integer>();
		bfsQueue.add(currentVertex);
		while (bfsQueue.size() > 0) {
			int source = bfsQueue.remove();
			Queue<Integer> adjVertices = graph.queueAdjacent(source);
			while (adjVertices.size() > 0) {
				int adj = adjVertices.remove();
				if (color[adj] == 'w') {
					color[adj] = 'g';
					distance[adj] = distance[source] + 1;
					parent[adj] = source;
					bfsQueue.add(adj);
				}
			}
			color[source] = 'b';
		}
	}
	
	/** Prints all vertices reachable from the source
	 *  
	 */
	public static void printReachableVertices() {
		System.out.println("Reachable vertices:");
		for (int i = 0; i < parent.length; i++) {
			if (parent[i] >= 0) {
				System.out.println(i + "[" + distance[i] + "]:" + parentPrint(i));
			}
		}
	}
	
	/** Prints all vertices NOT reachable from the source
	 *  
	 */
	public static void printUnreachableVertices() {
		System.out.println("Unreachable vertices:");
		for (int i = 0; i < parent.length; i++) {
			if (parent[i] == -1) {
				System.out.println(i);
			}
		}
	}
	
	/** Prints all vertices NOT reachable from the source
	 *  
	 */
	public static String parentPrint(int vertex) {
		String result = "";
		if (parent[vertex] == 0) {
			if (vertex == 0) {
				result = "0";
			} else {
				result = "0->" + vertex;
			}
		} else {
			result += parentPrint(parent[vertex]) + "->" + vertex;
		}
		return result;
	}	
	
}
