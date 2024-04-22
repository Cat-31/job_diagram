package com.example.demo.service.dag;

import java.util.HashMap;
import java.util.Map;

public class Node {
    private String id;
    private Map<String, String> data;
    private String type;
    private Position position;
    private int status;
    private Map<String, String> style = new HashMap<>();

    public Node() {
    }

    public Node(String id, Map<String, String> data, String type, Position position) {
        this.id = id;
        this.data = data;
        this.type = type;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public Map<String, String> getData() {
        return data;
    }

    public String getType() {
        return type;
    }

    public Position getPosition() {
        return position;
    }

    public void setStatus(int status) {
        this.status = status;
        String [] color = new String[] {
                "black",
                "white",
                "white",
        };

        String [] bgColor = new String[] {
                "#FFFFFF",
                "#FC5C30",
                "#13A52E",
        };

        style.put("color", color[status]);
        style.put("backgroundColor", bgColor[status]);
    }

    public int getStatus() {
        return status;
    }

    public Map<String, String> getStyle() {
        return style;
    }
}
