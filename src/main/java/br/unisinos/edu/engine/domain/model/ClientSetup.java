package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.Event;
import br.unisinos.edu.engine.domain.Resource;
import br.unisinos.edu.engine.repository.EngineRepository;
import br.unisinos.edu.engine.settings.Status;

import java.util.Random;

public class ClientSetup extends Event {
    private EngineRepository engineRepository;
    Resource usedTable;
    public void executeOnStart(ClientGroup clientGroup){
        if(clientGroup.getSize() == 1){
            engineRepository.queueCounter.insert(clientGroup);
            clientGroup.setStatus(Status.WaitingInLine);
            if(!engineRepository.queueCounter.isEmpty()){
                if(engineRepository.counterBench.allocate(1)){
                    System.out.println("Banco do balcão sendo usado");
                    clientGroup.setStatus(Status.WaitingInTable);
                    usedTable = engineRepository.counterBench;
                    engineRepository.queueCounter.getEntityList().remove(clientGroup);
                }
            }
        }

        else if(clientGroup.getSize() > 2){
            engineRepository.queueTables.insert(clientGroup);
            clientGroup.setStatus(Status.WaitingInLine);
            if(!engineRepository.queueTables.isEmpty()){
                if(engineRepository.tablesFourSeats.allocate(1)){
                    System.out.println("Mesa de quatro lugares sendo usada");
                    clientGroup.setStatus(Status.WaitingInTable);
                    usedTable = engineRepository.tablesFourSeats;
                    engineRepository.queueTables.getEntityList().remove(clientGroup);
                }
            }
        }

        else{
            engineRepository.queueTables.insert(clientGroup);
            clientGroup.setStatus(Status.WaitingInLine);
            if(!engineRepository.queueTables.isEmpty()){
                if(engineRepository.tablesTwoSeats.allocate(1)){
                    System.out.println("Mesa de dois lugares sendo usada");
                    clientGroup.setStatus(Status.WaitingInTable);
                    usedTable = engineRepository.tablesTwoSeats;
                    engineRepository.queueTables.getEntityList().remove(clientGroup);
                }
            }
        }
    }

    public void executeOnEnd(){
        if(usedTable == engineRepository.counterBench)
            engineRepository.counterBench.release(1);
        else if(usedTable == engineRepository.tablesTwoSeats)
            engineRepository.tablesTwoSeats.release(1);
        else engineRepository.tablesFourSeats.release(1);
    }
}
