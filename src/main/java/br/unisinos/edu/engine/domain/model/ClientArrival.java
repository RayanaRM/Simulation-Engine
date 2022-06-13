package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.Event;
import br.unisinos.edu.engine.repository.EngineRepository;
import br.unisinos.edu.engine.service.SchedulerService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientArrival extends Event {

    private ClientGroup clientGroup;

    @Override
    public void execute(SchedulerService schedulerService) {
        Random r = new Random();
        clientGroup = new ClientGroup(r.nextInt(4) + 1);
        EngineRepository.entities.add(clientGroup);

        int sizeOfQueueCashier1 = EngineRepository.queueCashier1.getEntityList().size();
        int sizeOfQueueCashier2 = EngineRepository.queueCashier2.getEntityList().size();

        if (sizeOfQueueCashier1 < sizeOfQueueCashier2) {
            EngineRepository.queueCashier1.insert(clientGroup);
        } else {
            EngineRepository.queueCashier2.insert(clientGroup);
        }

        CashierAllocation cashierAllocation = new CashierAllocation();
        cashierAllocation.setDuration(480);
        cashierAllocation.setClientGroup(clientGroup);

        schedulerService.scheduleIn(cashierAllocation, getDuration());
    }

}
