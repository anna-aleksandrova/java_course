package homeworks.hw08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class B_08_03 {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> vertices = new ArrayList<>(Arrays.asList(1, 2, 3));
        ArrayList<HashSet<Integer>> edges = new ArrayList<>();
        edges.add(new HashSet<>(Set.of(2, 3)));
        edges.add(new HashSet<>(Set.of(1, 3)));
        edges.add(new HashSet<>(Set.of(1, 2)));

        try {
            Graph K3 = new Graph(vertices, edges);
            System.out.println(K3);
            K3.DeleteEdge(4, 1);
            K3.DeleteVertex(4);
            K3.AddVertex(4, new HashSet<>(Set.of(1, 2, 3)));
            System.out.println(K3);
            K3.DeleteVertex(4);
            System.out.println(K3);
            K3.DeleteEdge(1, 3);
            K3.DeleteEdge(1, 2);
            System.out.println(K3);
        } catch (Exception e) {
            System.out.println(e.getMessage());        
        }

    }
}

class Graph {
    HashMap<Integer, HashSet<Integer>> graph;

    public Graph(ArrayList<Integer> vertices, ArrayList<HashSet<Integer>> edges) throws Exception {
        if (vertices.size() != edges.size()) throw new Exception("The amount of vertices != the amount of lists of corresponding vertices.");
        else {
            graph = new HashMap<>();
            for (int i = 0; i < vertices.size(); i++) {
                graph.put(vertices.get(i), edges.get(i));
            }
        }
    }

    public void AddVertex(int vertex, HashSet<Integer> incident) {
        graph.put(vertex, incident);
        for (Integer v : incident) {
            if (graph.get(v) == null) graph.put(v, new HashSet<Integer>());
            graph.get(v).add(vertex);
        }
    }

    public void DeleteVertex(int vertex) {
        if (graph.containsKey(vertex)) {
            for (Integer neighbor : graph.get(vertex)) {
                if (graph.containsKey(neighbor)) graph.get(neighbor).remove(vertex);
            }
            graph.remove(vertex);
        }
    }

    public void AddEdge(int u, int v) {
        if (graph.get(u) == null) graph.put(u, new HashSet<Integer>());
        graph.get(u).add(v);
        
        if (graph.get(v) == null) graph.put(v, new HashSet<Integer>());
        graph.get(v).add(u);
    }

    public void DeleteEdge(int u, int v) {
        if (graph.get(u) != null) graph.get(u).remove(v);
        if (graph.get(v) != null) graph.get(v).remove(u);
    }

    @Override
    public String toString() {
        return graph.toString();
    }
}
