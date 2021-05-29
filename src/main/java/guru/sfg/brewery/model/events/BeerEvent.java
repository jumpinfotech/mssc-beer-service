package guru.sfg.brewery.model.events;

import guru.sfg.brewery.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by jt on 2019-07-21.
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BeerEvent implements Serializable {

    // BeerEvent, BrewBeerEvent + NewInventoryEvent where moved into this package 
    // mssc-beer-service\src\main\java\guru\sfg\brewery\model\events\ from
    // mssc-beer-service\src\main\java\guru\sfg\common\events\
    // after moving classes around in maven always do a clean>then run package
    static final long serialVersionUID = -5781515597148163111L;

    private BeerDto beerDto;
}
