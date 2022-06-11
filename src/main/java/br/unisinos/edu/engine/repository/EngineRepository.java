package br.unisinos.edu.engine.repository;

import br.unisinos.edu.engine.domain.model.*;

public class EngineRepository {

    public static Waiter waiter = new Waiter();
    public static CounterBench counterBench = new CounterBench("balcao", 1, 6);;
    public static QueueCounter queueCounter =  new QueueCounter("queueCounter", 100);

    public static Cashier cashier1 = new Cashier("caixa1", 2, 1);
    public static Cashier cashier2 = new Cashier("caixa2", 3, 1);

    public static Kitchen kitchen = new Kitchen("kitchen", 4, 3);

    public static QueueCashier queueCashier1 = new QueueCashier("queueCashier1", 100);
    public static QueueCashier queueCashier2 = new QueueCashier("queueCashier2", 100);

    public static QueueOrders queueOrders = new QueueOrders("queueOrders", 100);
    public static QueueTables queueTables = new QueueTables("queueTables", 100);

    public static TablesFour tablesFourSeats  = new TablesFour("mesas4lug", 5, 4);
    public static TablesTwo tablesTwoSeats = new TablesTwo("mesas2lug", 6, 4);
}
