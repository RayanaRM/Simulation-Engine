package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.EntitySet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class QueueReadyOrders extends EntitySet {
    public QueueReadyOrders(String name, int max){
        super(name, max);
    }

    int totalPedidos = 0;
    double tempoMedioPermanencia = 0.56; //34 min de permanencia no sistema
    double tempoMedioAtendimento = 0.33; //20 minutos

    public double calculateArrivalRate(){
        return totalPedidos/0.05;
    }
    public double averageSize(){
        return calculateArrivalRate() * averageTimeOnQueue();
    }

    public double averageTimeOnQueue(){
        return tempoMedioPermanencia - tempoMedioAtendimento;
    }

}
