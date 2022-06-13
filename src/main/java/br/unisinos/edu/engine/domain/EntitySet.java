package br.unisinos.edu.engine.domain;

import br.unisinos.edu.engine.settings.Mode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntitySet {
    private String name;
    private int id;
    private Mode mode = Mode.None;
    private int size;
    private int maxPossibleSize;
    private List<Entity> entityList = new ArrayList<>();
    private HashMap<Integer, Double> logQueue = new HashMap<Integer, Double>();

    public EntitySet(String name, int maxPossibleSize){
        this.name = name;
        this.maxPossibleSize = maxPossibleSize;
    }
    public boolean isEmpty(){
        return size < 0;
    }

    public void insert(Entity entity, double time){
        entityList.add(entity);
        logQueue.put(entityList.size(), time);
    }
   public void getLog(){
       for (Map.Entry<Integer, Double> queueValues : logQueue.entrySet()) {
           System.out.println(queueValues);
       }
   }
}
