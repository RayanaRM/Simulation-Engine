package br.unisinos.edu.engine.domain.model;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Waiter {

    private RestTemplate restTemplate;

    public int getTokensFromPlace(int id){
        ResponseEntity<Integer> response =
                restTemplate.getForEntity(
                        "http://localhost:8080/get-token-quantity?id=" + id,
                        Integer.class);
        Integer tokensWaiter = response.getBody();
        return tokensWaiter;
    }

    public boolean isWaiterFree(){
        return getTokensFromPlace(1) > 0;
    }

    public boolean isWaiterAtCashier(){
        return getTokensFromPlace(2) > 0;
    }

    public boolean isWaiterServing(){
        return getTokensFromPlace(3) > 0;
    }

    public boolean isCashierBack(){
        return getTokensFromPlace(4) > 0;
    }

    public boolean isOrderAtTable(){
        return getTokensFromPlace(5) > 0;
    }

    public boolean isWaiterCleaningTable(){
        return getTokensFromPlace(6) > 0;
    }

    public boolean isTableClean(){
        return getTokensFromPlace(7) > 0;
    }

    // TODO: Implementar método para setar quantidade de tokens nos lugares de interface (Substituir Caixa, Pedido pronto e Cliente vai pra mesa)

    public void runStep(){
        // TODO: substitui enter no Petri-Net-Simulator
    }
}
