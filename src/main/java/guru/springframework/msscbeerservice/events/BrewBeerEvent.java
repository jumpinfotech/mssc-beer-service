package guru.springframework.msscbeerservice.events;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.NoArgsConstructor;

/**
 * Created by jt on 2019-07-21.
 */
@NoArgsConstructor // added due to error:- Cannot construct instance of ‘guru.springframework.msscbeerservice.events.BrewBeerEvent‘
public class BrewBeerEvent extends BeerEvent {
    //   Demonstrates setting up events using a specific type (BeerEvent.java) for events
    public BrewBeerEvent(BeerDto beerDto) { // can mix setting up a constructor here + using Lombok, some people may complain
        super(beerDto);
    }
}
