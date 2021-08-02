package graph;

import java.util.*;
import java.util.NoSuchElementException;


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
	 * use undirect graph
	 * so one edge makes two closed Vertexs
	 * 
	 * @param v1 start
	 * @param v2 end
	 * @param weight edge weight
	 * @throws NoSuchElementException, no given vertexs exist
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
		
		//undirect graph
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
	
	/*public void startBFS(String start) {
		visited = new HashMap<>();
		for(String key : visited.keySet()) {
			visited.replace(key, null);
		}
		bfs(start);
	}
	
	private void bfs(String start) {
		Queue<String> q = new LinkedList<>();
		visited.replace(start, null);
		q.add(start);
		
		while(!q.isEmpty()) {
			String now = q.poll();
			//for(Vertex v : vertexMap.get(now).closedVertexList) {
				//if(!visited.get(v.name)) {
					//q.add(v.name);
					//visited.replace(v.name, true);
				}
			}
		}
	}*/
	
	//vertex
	private class Vertex{
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

	}
	
	//edge
	private class Edge{
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
		
		for(int i=0; i<10; i++) {
			g.addEdge(Character.toString((char) ((int)(Math.random()*26) + 65)), Character.toString((char) ((int)(Math.random()*26) + 65)), 1);
		}
		
		/*for(char t = 'A'; t < 'Z'; t++) {
			g.addEdge(Character.toString(t), Character.toString(t+1), 1);
		}*/
		
		System.out.println(g.hasCycle("A"));
	}
}
