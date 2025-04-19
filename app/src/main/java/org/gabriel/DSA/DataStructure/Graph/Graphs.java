package org.gabriel.DSA.DataStructure.Graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Graphs {
  private ArrayList<LinkedList<Integer>> adjLists;
  private boolean visited[];
 
  Graphs(int vertices) {
    adjLists = new ArrayList<>(vertices);
    visited = new boolean[vertices];

    for (int i = 0; i < vertices; i++)
      adjLists.add(new LinkedList<>());
  }

  public void insertEdge(int v1, int v2) {
    adjLists.get(v1).add(v2);
    adjLists.get(v2).add(v1);
  }

  public void DFS(int vertex) {
    visited[vertex] = true;
    System.out.printf("%d ", vertex);
    
    Iterator<Integer> it = adjLists.get(vertex).listIterator();

    while(it.hasNext()) {
      int adj = it.next();
      if(!visited[adj]) {
        DFS(adj);
      }
    }
  }

  public void BFS(int vertex) {
    boolean visited[] = new boolean[adjLists.size()];
    Queue<Integer> q = new LinkedList<>();

    visited[vertex] = true;
    q.add(vertex);
    
    while(!q.isEmpty()) {
      int v = q.poll();
      System.out.printf("%d ", v);

      Iterator<Integer> it = adjLists.get(v).listIterator();
      while(it.hasNext()) {
        int adj = it.next();
        if(!visited[adj]) {
          visited[adj] = true;
          q.add(adj);
        }
      }

    }
  }

  public static void main(String[] args) {
    Graphs g = new Graphs(5);

    g.insertEdge(0, 1);
    g.insertEdge(0, 2);
    g.insertEdge(0, 3);
    g.insertEdge(1, 2);
    g.insertEdge(2, 4);

    g.BFS(0);
  }
}