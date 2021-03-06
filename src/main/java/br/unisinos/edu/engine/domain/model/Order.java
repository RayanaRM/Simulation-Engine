package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order extends Entity {
    private ClientGroup clientGroup;
}
