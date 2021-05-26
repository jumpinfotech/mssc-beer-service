package guru.springframework.msscbeerservice.events;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by jt on 2019-07-21.
 */
@Data
// @AllArgsConstructor replaces @RequiredArgsConstructor>caused conflicting annotations error:- 
// Error:(17, 1) java: constructor BeerEvent() is already defined in class guru.springframework.msscbeerservice.events.BeerEvent
// Caused by removing final from private final BeerDto beerDto; 
@AllArgsConstructor 
@Builder
// Jackson wants a @NoArgsConstructor, 
// There are Jackson annotations we could use to set up a constructor 
// + tell Jackson to use a constructor, we went this way instead it's easier 
@NoArgsConstructor 
// BeerEvent>root class(base object) for sending event messages>implements serializable>actually not needed>this will be JSON.
public class BeerEvent implements Serializable {

// Multiple objects created here>to show intent>in a complex system, we'd have additional properties + more customization
    
    
    static final long serialVersionUID = -5781515597148163111L;


    // Cannot construct instance of ‘guru.springframework.msscbeerservice.events.BrewBeerEvent‘
    // Means Jackson doesn't know how to construct the object. 
    // @NoArgsConstructor added + this can no longer be final:-
    private BeerDto beerDto;
}
