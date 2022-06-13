package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.Event;
import br.unisinos.edu.engine.repository.EngineRepository;
import br.unisinos.edu.engine.service.SchedulerService;

public class CashierAllocation extends Event {
    @Override
    public void execute(SchedulerService schedulerService) {
        if (EngineRepository.cashier1.allocate(1)) {
            System.out.println("Caixa 1 sendo usado...");
        } else if (EngineRepository.cashier2.allocate(1)) {
            System.out.println("Caixa 2 sendo usado...");
        } else {
            schedulerService.scheduleIn(this, getDuration());
        }
    }
}
