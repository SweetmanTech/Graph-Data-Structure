import java.util.LinkedList;
import java.util.Queue;

public class adjacency_matrix implements graphInterface {
	
	//The adjacency_list class has 2 fields
	public int numberOfVertices;
	public int adjacencyMatrix[][] ;
	
	/**Constructor
	 * 
	 * @param numberOfVerts number of vertices in the graph
	 */
	public adjacency_matrix(int numberOfVerts) {
		numberOfVertices = numberOfVerts;
		adjacencyMatrix = new int[numberOfVerts][numberOfVerts];
	}
	
	/**Adds an UNDIRECTED edge to the graph
	 * 
	 * @param start
	 * @param finish
	 */
	public void addEdge(int start, int finish) {
		adjacencyMatrix[start][finish] = 1;
		adjacencyMatrix[finish][finish] = 1;//Delete to make graph edges DIRECTED
	}
	
	/**Removes an edge from the graph
	 * 
	 * @param start
	 * @param finish
	 */
	public void removeEdge(int start, int finish) {
		adjacencyMatrix[start][finish] = 0;
		adjacencyMatrix[finish][finish] = 0;//Delete to make graph edges DIRECTED
	}
	
	/**Creates Queue of adjacent vertices
	 * 
	 * @param vertex the vertex adjacents are being found
	 * @return the queue of adjacent vertices
	 */
	public Queue<Integer> queueAdjacent(int vertex) {
		Queue<Integer> adjacent = new LinkedList<Integer>();
		for (int i = 0; i < adjacencyMatrix[vertex].length; i++) {
			if (adjacencyMatrix[vertex][i] == 1) {
				adjacent.add(i);
			}
		}
		return adjacent;
	}
	
	/**Reports the degree of a vertex
	 * 
	 * @param vertex the vertex we are finding the degree of
	 * @return degree of vertex
	 */
	public int degree(int vertex) {
		int degree = 0;
		for (int i = 0; i < adjacencyMatrix[vertex].length; i++) {
			if (adjacencyMatrix[vertex][i] == 1) {
				degree++;
			}
		}
		return degree;
	}
	
	/**Reports the number of vertices in the graph
	 * 
	 * @return number of vertices
	 */
	public int sizeOfGraph() {
		return numberOfVertices;
	}

}
