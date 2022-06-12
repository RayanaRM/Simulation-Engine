package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.Event;
import br.unisinos.edu.engine.domain.Resource;
import br.unisinos.edu.engine.repository.EngineRepository;
import br.unisinos.edu.engine.settings.Status;

public class ClientSetup extends Event {
    Resource usedTable;
    public void executeOnStart(ClientGroup clientGroup){
        if(clientGroup.getSize() == 1){
            EngineRepository.queueCounter.insert(clientGroup);
            clientGroup.setStatus(Status.WaitingInLine);
            if(!EngineRepository.queueCounter.isEmpty()){
                if(EngineRepository.counterBench.allocate(1)){
                    System.out.println("Garçom limpando balcão");
                    // TODO: colocar tempo para garçom limpar mesa
                    EngineRepository.waiter.sendWaiterToCleanTable();

                    System.out.println("Banco do balcão sendo usado");
                    clientGroup.setStatus(Status.WaitingInTable);
                    usedTable = EngineRepository.counterBench;
                    EngineRepository.queueCounter.getEntityList().remove(clientGroup);

                    EngineRepository.waiter.setCleanTable();
                }
            }
        }

        else if(clientGroup.getSize() > 2){
            EngineRepository.queueTables.insert(clientGroup);
            clientGroup.setStatus(Status.WaitingInLine);
            if(!EngineRepository.queueTables.isEmpty()){
                if(EngineRepository.tablesFourSeats.allocate(1)){
                    System.out.println("Garçom limpando mesa de quatro lugares");
                    EngineRepository.waiter.sendWaiterToCleanTable();

                    System.out.println("Mesa de quatro lugares sendo usada");
                    clientGroup.setStatus(Status.WaitingInTable);
                    usedTable = EngineRepository.tablesFourSeats;
                    EngineRepository.queueTables.getEntityList().remove(clientGroup);

                    EngineRepository.waiter.setCleanTable();
                }
            }
        }

        else{
            EngineRepository.queueTables.insert(clientGroup);
            clientGroup.setStatus(Status.WaitingInLine);
            if(!EngineRepository.queueTables.isEmpty()){
                if(EngineRepository.tablesTwoSeats.allocate(1)){
                    System.out.println("Garçom limpando mesa de dois lugares");
                    EngineRepository.waiter.sendWaiterToCleanTable();

                    System.out.println("Mesa de dois lugares sendo usada");
                    clientGroup.setStatus(Status.WaitingInTable);
                    usedTable = EngineRepository.tablesTwoSeats;
                    EngineRepository.queueTables.getEntityList().remove(clientGroup);

                    EngineRepository.waiter.setCleanTable();
                }
            }
        }
    }

    public void executeOnEnd(){
        if(usedTable == EngineRepository.counterBench)
            EngineRepository.counterBench.release(1);
        else if(usedTable == EngineRepository.tablesTwoSeats)
            EngineRepository.tablesTwoSeats.release(1);
        else EngineRepository.tablesFourSeats.release(1);
    }
}
