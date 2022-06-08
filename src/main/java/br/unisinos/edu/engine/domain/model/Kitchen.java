package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.Resource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Kitchen extends Resource {
    public Kitchen(String name, int id, int quantity) {
        super(name, id, quantity);
    }
}
