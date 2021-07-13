package graph;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

import java.util.NoSuchElementException;


/**
 * @author tir29
 * This class implements Graph by Map, List.
 * Map for Vertexs, List for Edges
 */
public class Graph {
	
	private Map<String, Vertex> vertexMap;
	private List<Edge> edgeList;
	private Map<String, Boolean> visited;
	
	
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
		vertexMap.put(name, new Vertex());
	}
	
	/**
	 * 
	 * add Edge in edge list
	 * use undirect graph
	 * so one edge makes two closed Vertexs
	 * 
	 * @param start Vertex
	 * @param end Vertex
	 * @param edge weight
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
	 * @param vertex's name
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
		visited = new Map<>();
		for(String key : visited.keySet()) {
			visited.replace(key, false);
		}
		dfs(start);
	}
	
	private void dfs(String start) {
		visited.replace(start, true);
		for(Vertex v : vertexMap.get(start).closedVertexList) {
			if(!visited.get(v.name)) {
				dfs(v.name);
			}
		}
	}
	
	private void bfs(String start) {
		Queue<String> q = new LinkedList<>();
		visited.replace(start, true);
		q.add(start);
		
		while(!q.isEmpty()) {
			String now = q.poll();
			for(Vertex v : vertexMap.get(now).closedVertexList) {
				if(!visited.get(v.name)) {
					q.add(v.name);
					visited.replace(v.name, true);
				}
			}
		}
	}
	
	//vertex
	private class Vertex{
		String name;
		List<Vertex> closedVertexList;
		
		Vertex(String name){
			this.name = name;
			this.closedVertexList = new LinkedList<>();
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
}
