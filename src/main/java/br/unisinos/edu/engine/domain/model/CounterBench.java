package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.Resource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CounterBench extends Resource {
    public CounterBench(String name, int id, int quantity) {
        super(name, id, quantity);
    }
}
