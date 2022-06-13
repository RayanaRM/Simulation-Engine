package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.EntitySet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueueCounter extends EntitySet {
    public QueueCounter(String name, int max){
        super(name, max);
    }

    int totalClients = 0;
    double tempoMedioPermanencia = 0.7; //42 min de permanencia no sistema
    double tempoMedioAtendimento = 0.56; //34 minutos

    public double calculateArrivalRate(){
        return totalClients /0.13;
    }
    public double averageSize(){
        return calculateArrivalRate() * averageTimeOnQueue();
    }

    public double averageTimeOnQueue(){
        return tempoMedioPermanencia - tempoMedioAtendimento;
    }
}
