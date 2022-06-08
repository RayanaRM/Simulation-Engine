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
    private EngineRepository engineRepository;
    public void simulate(){
        engineRepository.counterBench = new CounterBench("balcao", 1, 6);
        engineRepository.queueCounter = new QueueCounter("queueCounter", 100);

        engineRepository.cashier1 = new Cashier("caixa1", 2, 1);
        engineRepository.cashier2 = new Cashier("caixa2", 3, 1);

        engineRepository.kitchen = new Kitchen("kitchen", 4, 3);

        engineRepository.queueCashier1 = new QueueCashier("queueCashier1", 100);
        engineRepository.queueCashier2 = new QueueCashier("queueCashier2", 100);

        engineRepository.queueOrders = new QueueOrders("queueOrders", 100);
        engineRepository.queueTables = new QueueTables("queueTables", 100);

        engineRepository.tablesFourSeats = new TablesFour("mesas4lug", 5, 4);
        engineRepository.tablesTwoSeats = new TablesTwo("mesas2lug", 6, 4);

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
