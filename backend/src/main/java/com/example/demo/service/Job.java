package com.example.demo.service;

public abstract class Job {
    public String name() {
        return this.getClass().getSimpleName();
    }

    public void execute() {
        try {
            Thread.sleep((int)(Math.random() * 3000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        doBusiness();
    }

    public abstract void doBusiness();
}
