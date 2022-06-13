package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.Event;
import br.unisinos.edu.engine.repository.EngineRepository;
import br.unisinos.edu.engine.service.SchedulerService;
import br.unisinos.edu.engine.settings.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Eat extends Event {

    private ClientGroup clientGroup;

    @Override
    public boolean execute(SchedulerService schedulerService) {
        System.out.println("Cliente comendo...");

        clientGroup.setStatus(Status.Finished);

        EngineRepository.entities.remove(clientGroup);

        return true;
    }
}
