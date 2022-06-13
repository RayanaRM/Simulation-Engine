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
    public void execute(SchedulerService schedulerService) {
        if (cashierUsed.equals(EngineRepository.cashier1)) {
            EngineRepository.cashier1.release(1);
        } else {
            EngineRepository.cashier2.release(1);
        }

        Preparation preparation = new Preparation();
        preparation.setDuration(840);
        schedulerService.scheduleIn(preparation, getDuration());

        ClientSetup clientSetup = new ClientSetup();
        clientSetup.setDuration(0);
        schedulerService.scheduleIn(preparation, getDuration());
    }

}
