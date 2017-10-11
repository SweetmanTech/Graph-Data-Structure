import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite extends adjacency_list{
	
	//The Bipartite graph subclass has 4 fields
	public static boolean isBipartite;
	public static adjacency_list adjList;
	public static char[] color;
	public static int greenCount = 0;
	public static int blueCount = 0;

	// the MountainBike subclass has
    // one constructor
    public Bipartite() {
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
		Queue<Integer> adjacent = graph.queueAdjacent(currentVertex);
		while (adjacent.size() > 0) {
			int neighbor = adjacent.remove();
			if (color[neighbor] == 'w') {
				if (color[currentVertex] == 'g') {
					color[neighbor] = 'b';
					blueCount++;
					DFS(graph, neighbor);
				} else {
					color[neighbor] = 'g';
					greenCount++;
					DFS(graph, neighbor);
				}
			} else if (color[neighbor] == color[currentVertex]) {
				isBipartite = false;
			}
		}
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
		
		//Initialize Bipartite coloring
		color = new char[adjList.sizeOfGraph()];
        for (int i = 0; i < adjList.sizeOfGraph(); i++) {
        	color[i] = 'w';
        }
		
        //Run DFS coloring on the graph
        DFS(adjList, 0);
        
        //Print results
        //TODO Add check to determine if graph is Bipartite, but NOT Complete Bipartite
        String result = "complete bipartite";
        if (!isBipartite) {//Neighbor is same color
        	result = "neither";
        } else { //DFS coloring passes
			for (int i = 0; i < adjList.sizeOfGraph(); i++) {
				if (color[i] == 'w') {//Some vertices were not visited by DFS
					result = "neither";
				} else {//All vertices were visited
					Queue<Integer> adj = adjacency_list.queueAdjacent(i);
					for (int j = 0; i < adj.size(); j++) {
						if (color[i] == color[adj.remove()]) {
							result = "neither";
						}
						if (color[i] == 'g' && adjacency_list.degree(i) != blueCount) {
							result = "bipartite";
						} else if (color[i] == 'b' && adjacency_list.degree(i) != greenCount) {
							result = "bipartite";
						}
					}
				}
			}
        }
		System.out.println("Bipartite: " + result);
		keyboard.close();
	}

}
