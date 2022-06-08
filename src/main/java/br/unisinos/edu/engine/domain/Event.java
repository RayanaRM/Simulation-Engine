package br.unisinos.edu.engine.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private String name;

    private int id;

    private double time;

    public void executeOnStart() {
    }

    public void executeOnEnd() {
    }
}
