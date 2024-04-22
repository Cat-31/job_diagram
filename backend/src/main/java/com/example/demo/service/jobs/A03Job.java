package com.example.demo.service.jobs;

import com.example.demo.service.Job;
import org.springframework.stereotype.Component;

@Component
public class A03Job extends Job {
    @Override
    public void doBusiness() {
        System.out.println(name() + " invoked...");
    }
}
