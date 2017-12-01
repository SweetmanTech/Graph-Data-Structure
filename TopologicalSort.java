import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class TopologicalSort extends adjacency_list{
	
	//The Bipartite graph subclass has 4 fields
	public static boolean isBipartite;
	public static adjacency_list adjList;
	public static int[] discovery;
	public static Integer[] finish;
	public static int currentTime = 0;
	public static boolean backEdgeExists = false;

	// the MountainBike subclass has
    // one constructor
    public TopologicalSort() {
        super(0);
    }  
	
	/**Attempts to load file
	 * 
	 * @param fileName name of file
	 * @return whether file was successfully read
	 */
	public static boolean tryLoadingFile(String fileName) {
		boolean loaded = false;
		try {
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(new FileReader(fileName));
            
            String line = bufferedReader.readLine();
            adjList = new adjacency_list(Integer.parseInt(line));
            line = bufferedReader.readLine();
            while((line) != null) {
            	if (line.length() > 0 ) {
	            	List<String> strVertexList = Arrays.asList(line.split(","));
	            	int start = Integer.parseInt(strVertexList.get(0).trim());
	    			int finish = Integer.parseInt(strVertexList.get(1).trim());
	                adjList.addEdge(start, finish);
            	}
                line = bufferedReader.readLine();
            }   
            loaded = true;
            // Always close files.
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
            // Or we could just do this: 
            // ex.printStackTrace();
        }
		return loaded;
	}
	
	/**Executes DFS 
	 * 
	 * @param graph
	 * @param currentVertex
	 */
	public static void DFS(adjacency_list graph, int currentVertex) {
		discovery[currentVertex] = currentTime; //set discovery time
		currentTime++;
		Queue<Integer> adjacent = graph.queueAdjacent(currentVertex);
		while (adjacent.size() > 0) {//check adjacent vertices
			int neighbor = adjacent.remove();
			if (discovery[neighbor] == -1) {//<1,x> -> <x,x> undiscovered neighbor: Tree
				System.out.println("Tree Edge: (" + currentVertex + ", " + neighbor + ")");
				DFS(graph, neighbor);
			} else if (finish[neighbor] == -1) {// <2,x> -> <1,x> discovered: Back
				backEdgeExists = true;
				System.out.println("Back Edge: (" + currentVertex + ", " + neighbor + ")");
			} else if (discovery[currentVertex] < discovery[neighbor]) {// <1,x> -> <2,3> forward edge
				System.out.println("Forward Edge: (" + currentVertex + ", " + neighbor + ")");
			} else {// <3,x> -> <1,2> cross edge
				System.out.println("Cross Edge: (" + currentVertex + ", " + neighbor + ")");
			}
		}
		finish[currentVertex] = currentTime;
		currentTime++;
	}
	
	public static void main(String[] args) {
		//Initialize Graph
		Scanner keyboard = new Scanner(System.in);
		boolean fileLoaded = false;
		isBipartite = true;
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
		
		// Set Start/Finish Times to -1
		discovery = new int[adjList.sizeOfGraph()];
		finish = new Integer[adjList.sizeOfGraph()];
        for (int i = 0; i < adjList.sizeOfGraph(); i++) {
        	discovery[i] = -1;
        	finish[i] = -1;
        }
		
        //Run DFS coloring on the graph
        DFS(adjList, 0);
        
        //Print results
        String result = "topological sort exists";
        if (backEdgeExists) {// No topo sort
        	result = "There is a loop in this graph.";
        } else { // Topological sort exists
        	result = "Topological Sort: [";
			//Print vertices in order of highest finish times
        	for (int i = 2*adjList.sizeOfGraph(); i > 0; i--) {
            	if (Arrays.asList(finish).contains(i)) {
            		result += Integer.toString(Arrays.asList(finish).indexOf(i)) + ", ";
            	} 
            }
        	result += "]";
        }
		System.out.println(result);
		keyboard.close();
	}

}
