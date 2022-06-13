package br.unisinos.edu.engine.service;

import br.unisinos.edu.engine.domain.model.ClientArrival;
import br.unisinos.edu.engine.repository.EngineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@RequiredArgsConstructor
public class EngineService {

    SchedulerService schedulerService = new SchedulerService();

    public void simulate() {
        executeEngine();
    }

    public void executeEngine() {
        double maxTimeOfArrivals = 10800; // 3 horas em segundos;
        int arrivalsRate = 180; // 3 min em segundos;
        int maxArrivals = (int) maxTimeOfArrivals / arrivalsRate;
        int arrivalDuration = 480; // 8 min em segundos

        for (int i = 0; i < maxArrivals; i++) {
         ClientArrival clientArrival = new ClientArrival();
         clientArrival.setDuration(arrivalDuration);

         schedulerService.scheduleAt(clientArrival, arrivalsRate * i);
         }

//        ClientArrival ca1 = new ClientArrival();
//        ca1.setDuration(0);
//
//        ClientArrival ca2 = new ClientArrival();
//        ca2.setDuration(0);
//
//        ClientArrival ca3 = new ClientArrival();
//        ca3.setDuration(0);

//        schedulerService.scheduleNow(ca1);
//        schedulerService.scheduleNow(ca2);
//        schedulerService.scheduleNow(ca3);


        // configurar todos os 60 eventos de chegada (22 pq são 3h e tem 8min cada chegada)
        // random = uniform || time || normal
        // time = uniform(minValue, maxValue: double) ou time = exponential(meanValue): double ou normal(meanValue, stdDeviationValue): double
        // for i in schedulerService.scheduleAt(time)

        schedulerService.simulate();

        //output
        getTotalClients();
        getTotalTimeSimulation();
        getAverageQueuesSize();
        getAverageQueuesTime();
        getQueueChartSize();
        getClientsEatingSizeChart();
    }
    public void getTotalClients(){
        System.out.println("Número total de pessoas que passaram pelo restaurante durante a simulação = " + EngineRepository.clients);
        System.out.println("\n");
    }

    public void getTotalTimeSimulation(){
        System.out.println("Tempo de duração simulação em segundos = " + schedulerService.getTime());
        System.out.println("\n");
    }

    public void getAverageQueuesSize(){
        System.out.println("Tamanho médio da fila dos caixas = " + EngineRepository.queueCashier1.averageSize());
        System.out.println("Tamanho médio da fila do balcão = " + EngineRepository.queueCounter.averageSize());
        System.out.println("Tamanho médio da fila das mesas = " + EngineRepository.queueTables.averageSize());
        System.out.println("Tamanho médio da fila dos pedidos = " + EngineRepository.queueOrders.averageSize());
        System.out.println("Tamanho médio da fila dos pedidos prontos = " + EngineRepository.queueReadyOrders.averageSize());
        System.out.println("\n");
    }

    public void getAverageQueuesTime(){
        System.out.println("Tempo médio da fila do balcão = " + EngineRepository.queueCounter.averageTimeOnQueue());
        System.out.println("Tempo médio da fila das mesas = " + EngineRepository.queueTables.averageTimeOnQueue());
        System.out.println("\n");
    }

    public void getQueueChartSize(){
        System.out.println("Filas Caixa");
        System.out.println("Tamanho / Tempo");
        EngineRepository.queueCashier1.getLog();
        System.out.println("\n");

        System.out.println("Fila balcão");
        System.out.println("Tamanho / Tempo");
        EngineRepository.queueCounter.getLog();
        System.out.println("\n");

        System.out.println("Fila mesas");
        System.out.println("Tamanho / Tempo");
        EngineRepository.queueTables.getLog();
        System.out.println("\n");

        System.out.println("Fila pedidos");
        System.out.println("Tamanho / Tempo");
        EngineRepository.queueOrders.getLog();
        System.out.println("\n");

        System.out.println("Fila pedidos prontos");
        System.out.println("Tamanho / Tempo");
        EngineRepository.queueReadyOrders.getLog();
        System.out.println("\n");
    }

    public void getClientsEatingSizeChart(){
        System.out.println("Clientes comendo ao longo da simulação");
        System.out.println("Tamanho / Tempo");

        for (Map.Entry<Integer, Double> values : EngineRepository.clientsEating.entrySet()) {
            System.out.println(values);
        }
        System.out.println("\n");
    }
}
