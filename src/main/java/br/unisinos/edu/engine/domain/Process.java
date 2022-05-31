package br.unisinos.edu.engine.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Process {
    private int id;
    private String name;
    private double duration;
    private boolean active;

    public Process(String name, double duration){
        this.name = name;
        this.duration = duration;
    }
}
