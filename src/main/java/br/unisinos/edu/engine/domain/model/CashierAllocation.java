package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.Event;
import br.unisinos.edu.engine.repository.EngineRepository;
import br.unisinos.edu.engine.service.SchedulerService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CashierAllocation extends Event {

    private ClientGroup clientGroup;

    @Override
    public boolean execute(SchedulerService schedulerService) {
        if (EngineRepository.cashier1.allocate(1)) {
            System.out.println("Caixa 1 sendo usado...");

            EngineRepository.queueCashier1.getEntityList().remove(clientGroup);

            Cashier cashierUsed = EngineRepository.cashier1;
            CashierRelease cashierRelease = new CashierRelease(clientGroup, cashierUsed);
            cashierRelease.setDuration(0);

            schedulerService.scheduleIn(cashierRelease, getDuration());

            return true;
        } else if (EngineRepository.cashier2.allocate(1)) {
            System.out.println("Caixa 2 sendo usado...");

            EngineRepository.queueCashier2.getEntityList().remove(clientGroup);

            Cashier cashierUsed = EngineRepository.cashier2;
            CashierRelease cashierRelease = new CashierRelease(clientGroup, cashierUsed);
            cashierRelease.setDuration(0);

            schedulerService.scheduleIn(cashierRelease, getDuration());

            return true;
        } else {
            schedulerService.scheduleIn(this, getDuration());
            return false;
        }
    }
}
