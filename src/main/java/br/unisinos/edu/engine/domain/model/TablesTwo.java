package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.Resource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class TablesTwo extends Resource {
    public TablesTwo(String name, int id, int quantity) {
        super(name, id, quantity);
    }
}
