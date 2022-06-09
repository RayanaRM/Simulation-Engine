package br.unisinos.edu.engine.service;

import br.unisinos.edu.engine.domain.Scheduler;
import br.unisinos.edu.engine.domain.model.*;
import br.unisinos.edu.engine.repository.EngineRepository;
import br.unisinos.edu.engine.settings.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class EngineService {
    public void simulate(){
        executeEngine();
    }

    public void executeEngine(){
        // filas dos caixas
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
        preparation.executeOnEnd();

        // verificar se grupo de cliente ta na fila, se tiver deixar pedido esperando em outra fila (tem que criar essa classe de fila)
        // se grupo estiver em mesa, mandar pedidos para mesa e settar status do grupo para eating

        //cliente termina de comer
        clientSetup.executeOnEnd();

        //garçom

    }
}
