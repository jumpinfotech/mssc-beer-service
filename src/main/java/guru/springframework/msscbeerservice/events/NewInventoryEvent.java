package guru.springframework.msscbeerservice.events;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.NoArgsConstructor;

/**
 * Created by jt on 2019-07-21.
 */
@NoArgsConstructor // added due to error:- Cannot construct instance of ‘guru.springframework.msscbeerservice.events.BrewBeerEvent‘
public class NewInventoryEvent extends BeerEvent { // NewInventoryEvent extends BeerEvent
    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
