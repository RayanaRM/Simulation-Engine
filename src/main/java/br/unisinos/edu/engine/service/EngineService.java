package br.unisinos.edu.engine.service;

import br.unisinos.edu.engine.domain.model.*;
import br.unisinos.edu.engine.repository.EngineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
@RequiredArgsConstructor
public class EngineService {
    private EngineRepository engineRepository;
    public void simulate(){
        engineRepository.bancoBalcao = new BancoBalcao("balcao", 1, 6);
        engineRepository.filaBalcao = new FilaBalcao("filaBalcao", 100);

        engineRepository.caixa1 = new Caixa("caixa1", 2, 1);
        engineRepository.caixa2 = new Caixa("caixa2", 3, 1);

        engineRepository.filaCaixa1 = new FilaCaixa("filaCaixa1", 100);
        engineRepository.filaCaixa2 = new FilaCaixa("filaCaixa2", 100);

        engineRepository.filaPedidos = new FilaPedidos("filaPedidos", 100);
        engineRepository.filaMesas = new FilaMesas("filaMesas", 100);

        engineRepository.mesasQuatroLugares = new MesasQuatro("mesas4lug", 4, 4);
        engineRepository.mesasDoisLugares = new MesasDois("mesas2lug", 5, 4);
    }

    public void executeEngine(){
        int Minutos = 180; //3 horas
        Random r = new Random();

        ClientGroup clientGroup = new ClientGroup(r.nextInt(4) + 1);

        if(clientGroup.getSize() == 1){
            if(!engineRepository.bancoBalcao.allocate(1))
                engineRepository.filaBalcao.insert(clientGroup);
        }
    }
}
