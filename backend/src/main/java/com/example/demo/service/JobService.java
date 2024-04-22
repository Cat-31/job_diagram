package com.example.demo.service;

import com.example.demo.service.dag.Dag;
import com.example.demo.service.dag.Node;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JobService {

    private Map<String, Job> jobMap;
    private Dag dag;

    public JobService(List<Job> jobList) throws IOException {
        jobMap = jobList.stream().collect(Collectors.toMap(Job::name, x -> x));
        dag = new ObjectMapper().readValue(this.getClass().getResourceAsStream("/dag.json"), Dag.class);
    }

    public void runDag() {
        dag.getNodes().forEach(n -> n.setStatus(0));
        visit(dag.getNodes().get(0));
    }

    public Dag getStatus() {
        return dag;
    }

    public void visit(Node node) {
        String jobId = node.getData().get("jobId");
        if (canRun(node)) {
            node.setStatus(1);
            jobMap.get(jobId).execute();
            node.setStatus(2);
        }

        dag.getEdges().stream().filter(o -> node.getId().equals(o.getSource())).forEach(o -> {
            o.setAnimated(true);
            dag.getNodes().stream().filter(n -> o.getTarget().equals(n.getId())).forEach(
                    s -> visit(s)
            );
            o.setAnimated(false);
        });
    }

    private boolean canRun(Node node) {
        List<String> sourceNodeIdList = dag.getEdges().stream().filter(
                o -> node.getId().equals(o.getTarget())
        ).map(
                o -> o.getSource()
        ).collect(Collectors.toList());

        List<Node> nodes = sourceNodeIdList.stream().map(
                id ->  dag.getNodes().stream().filter(n -> n.getId().equals(id)).findAny().get()
        ).collect(Collectors.toList());

        return !nodes.stream().filter(o -> o.getStatus() != 2).findAny().isPresent();
    }
}
