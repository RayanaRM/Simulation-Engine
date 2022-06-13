package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderSend extends Event {

    private Order order;

}
