package br.unisinos.edu.engine.domain;

import br.unisinos.edu.engine.domain.model.Waiter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Entity {
    private int id;
    private String name;
    private double creationTime;
    private int priority;

    public Entity(String name){
        this.name = name;
    }


   public double getTimeSinceCreation(double now){
        return now - creationTime;
   }

}

