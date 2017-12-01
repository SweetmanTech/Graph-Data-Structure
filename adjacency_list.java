import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class adjacency_list implements graphInterface {
	
	//The adjacency_list class has 2 fields
	public int numberOfVertices;
	public Map<Integer, List<Integer>> adjacencyList;
	
	/**Constructor
	 * 
	 * @param numberOfVerts number of vertices in the graph
	 */
	public adjacency_list(int numberOfVerts) {
		numberOfVertices = numberOfVerts;
		adjacencyList = new HashMap<Integer, List<Integer>>();
		for (int i = 0; i < numberOfVertices; i++) {
			List<Integer> newVertexList = new ArrayList<Integer>();
			adjacencyList.put(i, newVertexList);
		}
	}
	
	/**Adds an UNDIRECTED edge to the graph
	 * 
	 * @param start
	 * @param finish
	 */
	public void addEdge(int start, int finish) {
		//Add edge to START vertex
		adjacencyList.get(start).add(finish);
		adjacencyList.get(finish).add(start);//Delete to make graph edges DIRECTED
	}
	
	/**Removes an edge from the graph
	 * 
	 * @param start
	 * @param finish
	 */
	public void removeEdge(int start, int finish) {
		//Add edge to START vertex
		adjacencyList.get(start).remove(adjacencyList.get(start).indexOf(finish));
		//Add edge to FINISH vertex
		adjacencyList.get(finish).remove(adjacencyList.get(finish).indexOf(start));
	}
	
	/**Creates Queue of adjacent vertices
	 * 
	 * @param vertex the vertex adjacents are being found
	 * @return the queue of adjacent vertices
	 */
	public Queue<Integer> queueAdjacent(int vertex) {
		Queue<Integer> adjacent = new LinkedList<Integer>();
		for (int i = 0; i < adjacencyList.get(vertex).size(); i++) {
			adjacent.add(adjacencyList.get(vertex).get(i));
		}
		return adjacent;
	}
	
	/**Reports the degree of a vertex
	 * 
	 * @param vertex the vertex we are finding the degree of
	 * @return degree of vertex
	 */
	public int degree(int vertex) {
		return adjacencyList.get(vertex).size();
	}
	
	/**Reports the number of vertices in the graph
	 * 
	 * @return number of vertices
	 */
	public int sizeOfGraph() {
		return numberOfVertices;
	}

}
