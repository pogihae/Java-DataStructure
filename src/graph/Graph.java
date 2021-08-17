package graph;

import java.util.*;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


/**
 * @author tir29
 * This class implements Graph by Map, List.
 * Map for Vertexs, List for Edges
 */
public class Graph {
	
	private final Map<String, Vertex> vertexMap;
	private final List<Edge> edgeList;
	private Map<String, Vertex> visited;
	private boolean cycle;
	
	
	/**
	 * Constructor
	 */
	public Graph() {
		this.vertexMap = new HashMap<>();
		this.edgeList = new LinkedList<>();
	}
	
	/**
	 * add Vertex in graph
	 * add Vertex only, not edge
	 * 
	 * @param name, Vertex's name
	 */
	public void addVertex(String name) {
		vertexMap.put(name, new Vertex(name));
	}
	
	/**
	 * 
	 * add Edge in edge list
	 * use Indirect graph
	 * so one edge makes two closed Vertexes
	 * 
	 * @param v1 start
	 * @param v2 end
	 * @param weight edge weight
	 * @throws NoSuchElementException, no given vertexes exist
	 */
	public void addEdge(String v1, String v2, int weight) {
		//check v1,v2 are exist
		if(!vertexMap.containsKey(v1) || !vertexMap.containsKey(v2)) {
			throw new NoSuchElementException();
		}
		
		//add closed list
		vertexMap.get(v1)
			.closedVertexList
			.add(vertexMap.get(v2));
		
		//Indirect graph
		vertexMap.get(v2)
		.closedVertexList
		.add(vertexMap.get(v1));
		
		//add edge
		edgeList.add(new Edge(v1, v2, weight));
	}
	
	/**
	 * @param name vertex's name
	 * @return true, if remove success
	 * false, if no exist
	 */
	public boolean removeVertex(String name) {
		Vertex toDel = vertexMap.get(name);
		if(toDel == null) return false;
		
		//closed list remove
		for(Vertex tmp : toDel.closedVertexList) {
			tmp.closedVertexList.remove(toDel);
		}
		//edge remove
		/*Iterator<Edge> i = edgeList.iterator();
		while(i.hasNext()) {
			Edge tmp = i.next();
			if(tmp.v1.equals(name) || tmp.v2.equals(name)) {
				i.remove();
			}
		}*/
		edgeList.removeIf(e -> e.isEdgeFor(name));
		
		vertexMap.remove(name);
		return true;
	}
	
	public void startDFS(String start) {
		if(!vertexMap.containsKey(start)) {
			System.out.println("NO START VERTEX");
			return;
		}
		visited = new HashMap<>();
		for(String key : vertexMap.keySet()) {
			visited.put(key, null);
		}
		dfs(start);
	}
	
	public boolean hasCycle(String start) {
		cycle = false;
		startDFS(start);
		return cycle;
	}
	
	private void dfs(String start) {
		visited.replace(start, visited.getOrDefault(start, new Vertex(start)));
		for(Vertex v : vertexMap.get(start).closedVertexList) {
			if(visited.get(v.name) == null) {
				visited.replace(v.name, vertexMap.get(start));
				dfs(v.name);
			}
			else if(!visited.get(start).equals(vertexMap.get(v.name))) cycle = true;
		}
	}
	
	public void startBFS(String start) {
		visited = new HashMap<>();
		for(String key : vertexMap.keySet()) {
			visited.put(key, null);
		}
		bfs(start);
	}
	
	private void bfs(String start) {
		Queue<String> q = new LinkedList<>();
		visited.replace(start, new Vertex(start));
		q.add(start);
		
		while(!q.isEmpty()) {
			String now = q.poll();
			for(Vertex v : vertexMap.get(now).closedVertexList) {
				if(visited.get(v.name) == null) {
					q.add(v.name);
					visited.replace(v.name, vertexMap.get(now));
				}
			}
		}
	}

	public void print() {
		vertexMap.keySet()
				.stream()
				.sorted()
				.forEach(s->{
					System.out.print(s+": ");
					vertexMap.get(s)
							.closedVertexList
							.forEach(vertex -> System.out.print(vertex+" "));
					System.out.println();
				});
	}
	
	//vertex
	private static class Vertex{
		String name;
		List<Vertex> closedVertexList;
		
		Vertex(String name){
			this.name = name;
			this.closedVertexList = new LinkedList<>();
		}
		
		@Override
		public boolean equals(Object o) {
			Vertex v = (Vertex) o;
			
			return this.name.equals(v.name);
		}
		@Override
		public String toString() {
			return name;
		}
	}
	
	//edge
	private static class Edge{
		final String v1;
		final String v2;
		final int weight;
		
		Edge(final String v1, final String v2, final int weight){
			this.v1 = v1;
			this.v2 = v2;
			this.weight = weight;
		}
		
		boolean isEdgeFor(String name) {
			return (v1.equals(name)) || (v2.equals(name));
		}
	}
	
	public static void main(String[] args) {
		Graph g = new Graph();
		
		for(char t = 'A'; t <= 'Z'; t++) {
			g.addVertex(Character.toString(t));
		}
		
		for(char t = 'A'; t <= 'Z'; t++) {
			g.addEdge(Character.toString(t), Character.toString((char) ((int)(Math.random()*26) + 65)), 1);
		}
		g.startBFS("A");
		g.print();
		//g.removeVertex("A");
		//System.out.println(g.hasCycle("A"));
	}
}
