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
public class CashierRelease extends Event {

    private ClientGroup clientGroup;

    private Cashier cashierUsed;

    @Override
    public boolean execute(SchedulerService schedulerService) {
        if (cashierUsed.equals(EngineRepository.cashier1)) {
            System.out.println("Caixa 1 liberado...");
            EngineRepository.cashier1.release(1);
        } else {
            System.out.println("Caixa 1 liberado...");
            EngineRepository.cashier2.release(1);
        }

        OrderArrival orderArrival = new OrderArrival();
        orderArrival.setDuration(0);
        schedulerService.scheduleIn(orderArrival, getDuration());

        ClientSetup clientSetup = new ClientSetup();
        clientSetup.setDuration(0);
        schedulerService.scheduleIn(clientSetup, getDuration());

        return true;
    }

}
