package br.unisinos.edu.engine.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Entity {
    private int id;
    private String name;
    private double creationTime;
    private int priority;
    private int petryNet;

    public Entity(String name){
        this.name = name;
    }

    public Entity(int petryNet){
        this.petryNet = petryNet;
    }

   public double getTimeSinceCreation(double now){
        return now - creationTime;
   }

   //public List<EntitySet> getSets(){
   //}
}

