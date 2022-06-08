package br.unisinos.edu.engine.repository;

import br.unisinos.edu.engine.domain.model.*;

public class EngineRepository {
    public CounterBench counterBench;
    public QueueCounter queueCounter;

    public Cashier cashier1;
    public Cashier cashier2;

    public Kitchen kitchen;

    public QueueCashier queueCashier1;
    public QueueCashier queueCashier2;

    public QueueOrders queueOrders;
    public QueueTables queueTables;

    public TablesFour tablesFourSeats;
    public TablesTwo tablesTwoSeats;
}
