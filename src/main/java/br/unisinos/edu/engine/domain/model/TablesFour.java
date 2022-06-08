package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.Resource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TablesFour extends Resource {
    public TablesFour(String name, int id, int quantity) {
        super(name, id, quantity);
    }
}
