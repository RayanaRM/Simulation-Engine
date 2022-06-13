package br.unisinos.edu.engine.service;

import br.unisinos.edu.engine.domain.model.ClientArrival;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


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

        /**for (int i = 0; i < maxArrivals; i++) {
         ClientArrival clientArrival = new ClientArrival();
         clientArrival.setDuration(arrivalDuration);

         schedulerService.scheduleAt(clientArrival, arrivalsRate * i);
         }*/

        ClientArrival ca1 = new ClientArrival();
        ca1.setDuration(0);

        ClientArrival ca2 = new ClientArrival();
        ca2.setDuration(0);

        ClientArrival ca3 = new ClientArrival();
        ca3.setDuration(0);

        schedulerService.scheduleNow(ca1);
        schedulerService.scheduleNow(ca2);
        schedulerService.scheduleNow(ca3);


        // configurar todos os 60 eventos de chegada (22 pq são 3h e tem 8min cada chegada)
        // random = uniform || time || normal
        // time = uniform(minValue, maxValue: double) ou time = exponential(meanValue): double ou normal(meanValue, stdDeviationValue): double
        // for i in schedulerService.scheduleAt(time)

        schedulerService.simulate();
    }
}
