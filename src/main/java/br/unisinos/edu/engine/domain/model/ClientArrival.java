package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.Event;
import br.unisinos.edu.engine.repository.EngineRepository;
import br.unisinos.edu.engine.service.SchedulerService;

import java.util.Random;

public class ClientArrival extends Event {
    private Cashier cashierUsed;
    public ClientGroup clientGroup;

    @Override
    public void execute(SchedulerService schedulerService) {
        Random r = new Random();
        clientGroup = new ClientGroup(r.nextInt(4) + 1);
        EngineRepository.entities.add(clientGroup);

        int sizeOfQueueCashier1 = EngineRepository.queueCashier1.getEntityList().size();
        int sizeOfQueueCashier2 = EngineRepository.queueCashier2.getEntityList().size();

        if (sizeOfQueueCashier1 < sizeOfQueueCashier2) {
            EngineRepository.queueCashier1.insert(clientGroup, getTime());
        } else {
            EngineRepository.queueCashier2.insert(clientGroup, getTime());
        }

        CashierAllocation cashierAllocation = new CashierAllocation();
        cashierAllocation.setDuration(120);

        schedulerService.scheduleIn(cashierAllocation, getDuration());
    }

    public void executeOnStart() {
        // evento de chegada
        Random r = new Random();
        clientGroup = new ClientGroup(r.nextInt(4) + 1);
        EngineRepository.clients += clientGroup.getSize();
        EngineRepository.entities.add(clientGroup);

        if (EngineRepository.queueCashier1.getEntityList().size() <
                EngineRepository.queueCashier2.getEntityList().size()) {
            EngineRepository.queueCashier1.insert(clientGroup, getTime());
        } else {
            EngineRepository.queueCashier2.insert(clientGroup, getTime());
        }

        // evento pra alocar o caixa
        if (!EngineRepository.queueCashier1.getEntityList().isEmpty()) {
            if (EngineRepository.cashier1.allocate(1)) {
                System.out.println("Caixa 1 sendo usado");
                cashierUsed = EngineRepository.cashier1;
                EngineRepository.queueCashier1.getEntityList().remove(clientGroup);
                // liberar o caixa
                // cria evento do preparo da refeição
                // cria evento da mesa
                return;
            }
        }

        if (!EngineRepository.queueCashier2.getEntityList().isEmpty()) {
            if (EngineRepository.cashier2.allocate(1)) {
                System.out.println("Caixa 2 sendo usado");
                cashierUsed = EngineRepository.cashier2;
                EngineRepository.queueCashier2.getEntityList().remove(clientGroup);
                // liberar o caixa
                // cria evento do preparo da refeição
                // cria evento da mesa
                return;
            }
        }

        // reagenda o tempo do evento
    }

    public void executeOnEnd() {
        if (cashierUsed == EngineRepository.cashier1)
            EngineRepository.cashier1.release(1);
        else EngineRepository.cashier2.release(1);
    }
}
