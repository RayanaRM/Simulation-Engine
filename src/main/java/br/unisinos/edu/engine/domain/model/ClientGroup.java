package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.Entity;
import br.unisinos.edu.engine.settings.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientGroup extends Entity {
    private int size;
    private Status status;

    public ClientGroup(int size){
        this.size = size;
    }
}
