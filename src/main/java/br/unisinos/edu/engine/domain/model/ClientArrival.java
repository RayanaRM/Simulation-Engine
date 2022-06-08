package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.Event;
import br.unisinos.edu.engine.repository.EngineRepository;

import java.util.Random;

public class ClientArrival extends Event {
    private EngineRepository engineRepository;
    private Cashier cashierUsed;
    public ClientGroup clientGroup;
    public void executeOnStart(){
        Random r = new Random();
        clientGroup = new ClientGroup(r.nextInt(4) + 1);

        if (engineRepository.queueCashier1.getEntityList().size() <
                engineRepository.queueCashier2.getEntityList().size()) {
            engineRepository.queueCashier1.insert(clientGroup);
        } else {
            engineRepository.queueCashier2.insert(clientGroup);
        }

        if(!engineRepository.queueCashier1.getEntityList().isEmpty()){
            if(engineRepository.cashier1.allocate(1)){
                System.out.println("Caixa 1 sendo usado");
                cashierUsed = engineRepository.cashier1;
                engineRepository.queueCashier1.getEntityList().remove(clientGroup);
            }
        }

        if(!engineRepository.queueCashier2.getEntityList().isEmpty()){
            if(engineRepository.cashier2.allocate(1)){
                System.out.println("Caixa 2 sendo usado");
                cashierUsed = engineRepository.cashier2;
                engineRepository.queueCashier2.getEntityList().remove(clientGroup);
            }
        }
    }

    public void executeOnEnd(){
        if (cashierUsed == engineRepository.cashier1)
            engineRepository.cashier1.release(1);
        else engineRepository.cashier2.release(1);
    }
}
