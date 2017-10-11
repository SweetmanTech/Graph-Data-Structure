import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class adjacency_list {
	
	//The adjacency_list class has 2 fields
	public static int numberOfVertices;
	public static Map<Integer, List<Integer>> adjacencyList;
	
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
	
	/**Adds an edge to the graph
	 * 
	 * @param start
	 * @param finish
	 */
	public void addEdge(int start, int finish) {
		//Add edge to START vertex
		adjacencyList.get(start).add(finish);
		//Add edge to FINISH vertex
		adjacencyList.get(finish).add(start);
	}
	
	/**Removes an edge from the graph
	 * 
	 * @param start
	 * @param finish
	 */
	private static void removeEdge(int start, int finish) {
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
	public static Queue<Integer> queueAdjacent(int vertex) {
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
	public static int degree(int vertex) {
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
