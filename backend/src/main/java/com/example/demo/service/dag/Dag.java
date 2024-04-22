package com.example.demo.service.dag;

import java.util.List;

public class Dag {
    private List<Node> nodes;
    private List<Edge> edges;

    public Dag() {
    }

    public Dag(List<Node> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
