package br.unisinos.edu.engine.domain.model;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Waiter{

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

    public void addTokenToPlace(int id){
                restTemplate.postForEntity(
                        "http://localhost:8080/insere-token-em-lugar?id=" + id, id,
                        Integer.class);
    }

    public void removeTokenFromPlace(int id){
        restTemplate.postForEntity(
                "http://localhost:8080/remove-token-de-lugar?id=" + id + "&arcType=regular", id,
                Integer.class);
    }

    public void setReplaceCashier(boolean add){
        if (add) addTokenToPlace(8);
        else {
            removeTokenFromPlace(8);
        }    }

    public void setOrderDone(boolean add){
        if (add) addTokenToPlace(9);
        else {
            removeTokenFromPlace(9);
        }
    }

    public void setClientUseTable(boolean add){
        if (add) addTokenToPlace(10);
        else {
            removeTokenFromPlace(10);
        }
    }


    public void runStep(){
        // TODO: substitui enter no Petri-Net-Simulator
    }
}
