package ru.vsu.cs.uliyanov_n_s.utils;

import ru.vsu.cs.uliyanov_n_s.Graph;

public class GraphUtils {
    public static Graph fromString(String input) {
        Graph graph = new Graph();
        String[] lines = input.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String[] values = lines[i].split(" ");
            graph.addEdge(values[0], values[1], Integer.parseInt(values[2]));
        }
        return graph;
    }
}
