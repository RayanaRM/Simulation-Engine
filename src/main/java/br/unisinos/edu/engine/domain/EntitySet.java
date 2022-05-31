package br.unisinos.edu.engine.domain;

import br.unisinos.edu.engine.settings.Mode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public boolean isEmpty(){
        return size < 0;
    }

    //public Entity findEntity(int id){
    //}
   //averageSize()
   //getSize()
   //getMaxPossibleSize()
   //averageTimeInSet()
   //maxTimeInSet()
   //startLog(timeGap)
   //getLog()
}
