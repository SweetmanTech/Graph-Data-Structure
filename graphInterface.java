import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public interface graphInterface {
	//The adjacency_list class has 2 fields
	final static int numberOfVertices = 0;
	final static Map<Integer, List<Integer>> adjacencyList = new HashMap<Integer, List<Integer>>();
	
	/**Adds an edge to the graph
	 * 
	 * @param start
	 * @param finish
	 */
	public static void addEdge(int start, int finish) {
	}
	
	/**Removes an edge from the graph
	 * 
	 * @param start
	 * @param finish
	 */
	public static void removeEdge(int start, int finish) {
	}
	
	/**Creates Queue of adjacent vertices
	 * 
	 * @param vertex the vertex adjacents are being found
	 * @return the queue of adjacent vertices
	 */
	public static Queue<Integer> queueAdjacent(int vertex) {
		return null;
	}
	
	/**Reports the degree of a vertex
	 * 
	 * @param vertex the vertex we are finding the degree of
	 * @return degree of vertex
	 */
	public static int degree(int vertex) {
		return vertex;
	}
	
	/**Reports the number of vertices in the graph
	 * 
	 * @return number of vertices
	 */
	public static int sizeOfGraph() {
		return numberOfVertices;
	}
}
