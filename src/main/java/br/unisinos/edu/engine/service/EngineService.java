package br.unisinos.edu.engine.service;

import br.unisinos.edu.engine.domain.model.*;
import br.unisinos.edu.engine.repository.EngineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EngineService {
    public void simulate(){
        executeEngine();
    }

    public void executeEngine(){
        //filas dos caixas
        ClientArrival clientArrival = new ClientArrival();

        clientArrival.executeOnStart();

        // inicia preparo do pedido
        Preparation preparation = new Preparation();
        preparation.executeOnStart(clientArrival.clientGroup);

        //cliente sai do caixa
        clientArrival.executeOnEnd();

        //move cliente para mesa ou fila de espera
        ClientSetup clientSetup = new ClientSetup();
        clientSetup.executeOnStart(clientArrival.clientGroup);

        //pedido fica pronto
        preparation.executeOnEnd(clientArrival.clientGroup);

        // TODO: Implementar o tempo pro garçom substituir o caixa
        EngineRepository.waiter.sentToReplaceCashier();
        EngineRepository.waiter.sendCashierBack();

        //cliente termina de comer
        clientSetup.executeOnEnd(clientArrival.clientGroup);

        //garçom

    }

    public int getEntityTotalQuantity(){
        return EngineRepository.entities.size();
    }
}
