package br.unisinos.edu.engine.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Resource {
    private String name;
    private int id;
    private int quantity;

    public boolean allocate(int quantity) {
        if (quantity <= getQuantity()) {
            setQuantity(getQuantity() - quantity);
            return true;
        }
        return false;
    }

    public void release(int quantity) {
        setQuantity(getQuantity() + quantity);
    }

    public double allocationRate() {
        return 1;
    }

    public double averageAllocation() {
        return 1;
    }
}
