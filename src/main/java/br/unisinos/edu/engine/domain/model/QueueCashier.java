package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.EntitySet;

public class QueueCashier extends EntitySet {
    public QueueCashier(String name, int max){
        super(name, max);
    }

    double arrivelRate = 20; //grupos de cliente por hora
    double tempoMedioPermanencia = 0.7; //42 min de permanencia no sistema
    double tempoMedioAtendimento = 0.13; //8 minutos
    public double averageSize(){
        return arrivelRate * averageTimeOnQueue();
    }

    public double averageTimeOnQueue(){
        return tempoMedioPermanencia - tempoMedioAtendimento;
    }
}
