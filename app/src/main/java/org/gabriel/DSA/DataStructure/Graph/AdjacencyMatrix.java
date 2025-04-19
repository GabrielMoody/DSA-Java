package org.gabriel.DSA.DataStructure.Graph;

public class AdjacencyMatrix {
  private int[][] graph;

  public AdjacencyMatrix(int size) {
    this.graph = new int[size][size];
  }

  public void insert_edge(int v1, int v2) {
    if(v1 > graph.length || v2 > graph.length) {
      System.err.println("Graph is out of size");
      return;
    }

    graph[v1][v2] = 1;
    graph[v2][v1] = 1;
  }

  public void remove_edge(int v1, int v2) {
    if(graph[v1][v2] == 0) {
      System.err.printf("There is no edge between %d and %d", v1, v2);
      return;
    }

    graph[v1][v2] = 0;
    graph[v2][v1] = 0;
  }

  public void print() {
    for(int[] row : graph) {
      for(int val : row) {
        System.err.printf("%d ", val);
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    AdjacencyMatrix g = new AdjacencyMatrix(5);
    g.insert_edge(0, 1);
    g.insert_edge(0, 2);
    g.insert_edge(1, 2);
    g.insert_edge(2, 0);
    g.insert_edge(2, 3);

    g.print();
  }
}
