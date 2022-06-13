package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.EntitySet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueueOrders extends EntitySet {
    public QueueOrders(String name, int max){
        super(name, max);
    }
    int totalPedidos = 0;

    public double calculateArrivalRate(){
        return totalPedidos/0.05;
    }
    public double averageSize(){
        return calculateArrivalRate() * 0.23;
    }
}
