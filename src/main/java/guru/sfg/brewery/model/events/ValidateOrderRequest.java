package guru.sfg.brewery.model.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jt on 11/30/19.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// copied + pasted from mssc-beer-order-service project
public class ValidateOrderRequest { 

    private BeerOrderDto beerOrder;
}
