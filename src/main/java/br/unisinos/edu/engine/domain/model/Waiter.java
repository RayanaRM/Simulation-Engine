package br.unisinos.edu.engine.domain.model;

import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@NoArgsConstructor
public class Waiter{

    private RestTemplate restTemplate = new RestTemplate();

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

    public void sentToReplaceCashier(){
        if(isWaiterFree()){
            addTokenToPlace(8);
            runStep();
        }
    }

    public void sendCashierBack(){
            addTokenToPlace(4);
            runStep();
    }

    public void sendWaiterToServeOrder(){
        if(isWaiterFree()){
            addTokenToPlace(9);
            runStep();
        }
    }

    public void setOrderAtTable(){
            addTokenToPlace(5);
            runStep();
    }

    public void sendWaiterToCleanTable(){
        if(isWaiterFree()) {
            addTokenToPlace(10);
            runStep();
        }
    }

    public void setCleanTable(){
            addTokenToPlace(7);
            runStep();
    }

    public void runStep(){
        restTemplate.getForEntity(
                "http://localhost:8080/executa/passo-a-passo", null);
    }
}
