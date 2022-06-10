package ru.vsu.cs.uliyanov_n_s;

import java.util.*;

public class Graph {
    private final Map<String, Vertex> graph;

    public static class Edge {
        public final String v1, v2;
        public final int dist;

        public Edge(String v1, String v2, int dist) {
            this.v1 = v1;
            this.v2 = v2;
            this.dist = dist;
        }
    }

    public static class Vertex implements Comparable<Vertex> {
        public final String name;
        public int dist = Integer.MAX_VALUE;
        public Vertex previous = null;
        public final Map<Vertex, Integer> neighbours = new HashMap<>();

        public Vertex(String name) {
            this.name = name;
        }

        private String printPath() {
            StringBuilder sb = new StringBuilder();
            if (this == this.previous) {
                sb.append(this.name);
            } else if (this.previous == null) {
                sb.append(this.name).append("(unreached)");
            } else {
                sb.append(this.previous.printPath());
                sb.append(" -> ").append(this.name).append("(").append(this.dist).append("km)");
            }

            return sb.toString();
        }

        public int compareTo(Vertex other) {
            return Integer.compare(dist, other.dist);
        }
    }

    public Graph(){
        graph = new HashMap<>(0);
    }

//    public Graph(Edge[] edges) {
//        graph = new HashMap<>(edges.length);
//
//        for (Edge e : edges) {
//            if (!graph.containsKey(e.v1)) graph.put(e.v1, new Vertex(e.v1));
//            if (!graph.containsKey(e.v2)) graph.put(e.v2, new Vertex(e.v2));
//        }
//
//        for (Edge e : edges) {
//            graph.get(e.v1).neighbours.put(graph.get(e.v2), e.dist);
//            //graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); // если нужно сделать неориентированный граф
//        }
//    }

    public void addEdge(String v1, String v2, int dist) {
        Edge e = new Edge(v1, v2, dist);
        if (!graph.containsKey(e.v1)) graph.put(e.v1, new Vertex(e.v1));
        if (!graph.containsKey(e.v2)) graph.put(e.v2, new Vertex(e.v2));

        graph.get(e.v1).neighbours.put(graph.get(e.v2), e.dist);
        //graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); // если нужно сделать неориентированный граф
    }

    public void removeVertex(String name) {
        if (graph.containsKey(name)) {
            Vertex vertexForRemoving = graph.get(name);
            for (String currName : graph.keySet()) {
                Vertex currVertex = graph.get(currName);
                currVertex.neighbours.remove(vertexForRemoving);
            }
            graph.remove(name);
        }
    }

    public String findWayWithDijkstraWithBadVertexes(String startName, String endName, String badVertexesStr) {
        Graph newGraph = this;
        String[] badVertexes = badVertexesStr.split(" ");
        for (String badCity : badVertexes) {
            newGraph.removeVertex(badCity);
        }
        return newGraph.findWayWithDijkstra(startName, endName);
    }

    public String findWayWithDijkstra(String startName, String endName) {
        this.dijkstra(startName);
        return this.printPath(endName);
    }

    private void dijkstra(String startName) {
        if (!graph.containsKey(startName)) {
            System.err.printf("Graph doesn't contain start vertex \"%s\"\n", startName);
            return;
        }
        final Vertex source = graph.get(startName);
        NavigableSet<Vertex> q = new TreeSet<>();

        for (Vertex v : graph.values()) {

            v.previous = v == source ? source : null;
            v.dist = v == source ? 0 : Integer.MAX_VALUE;
            q.add(v);

        }

        dijkstra(q);
    }

    private void dijkstra(final NavigableSet<Vertex> q) {
        Vertex u, v;
        while (!q.isEmpty()) {

            u = q.pollFirst();
            if (u.dist == Integer.MAX_VALUE)
                break;

            for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
                v = a.getKey();

                final int alternateDist = u.dist + a.getValue();
                if (alternateDist < v.dist) {
                    q.remove(v);
                    v.dist = alternateDist;
                    v.previous = u;
                    q.add(v);
                }
            }
        }
    }

    private String printPath(String endName) {
        if (!graph.containsKey(endName)) {
            return "Graph doesn't contain end vertex \"" + endName + "\"\n";
        }

        return graph.get(endName).printPath();
    }

    public String toDot() {
        StringBuilder sb = new StringBuilder();
        String nl = System.getProperty("line.separator");
        sb.append("digraph").append(" {").append(nl);
        for (Map.Entry<String, Vertex> entry : graph.entrySet()) {
            Vertex v = entry.getValue();
            for (Vertex v1 : v.neighbours.keySet()) {
                sb.append(entry.getKey()).append(" -> ").append(v1.name).append(" [label = ").append(v.neighbours.get(v1)).append("]");
                sb.append(nl);
            }
        }
        sb.append("}").append(nl);

        return sb.toString();
    }
}
