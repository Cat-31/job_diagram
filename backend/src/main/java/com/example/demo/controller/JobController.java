package com.example.demo.controller;

import com.example.demo.service.dag.Dag;
import com.example.demo.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/ok")
    public ResponseEntity<String> ok() {
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/run")
    public ResponseEntity<String> run() {
        jobService.runDag();
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/dag")
    public ResponseEntity<Dag> dag() {
        return ResponseEntity.ok(jobService.getStatus());
    }
}
