package guru.springframework.msscbeerservice.services.brewing;

import guru.springframework.msscbeerservice.config.JmsConfig;
import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.events.BrewBeerEvent;
import guru.springframework.msscbeerservice.events.NewInventoryEvent;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jt on 2019-07-21.
 */
@Service // could make a @Component, either works - little difference between the annotations
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener { // component listens for a brew beer event

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    // @Transactional added to avoid lazy init error:-
    // org.hibernate.LazyInitializationException: could not initialize proxy [guru.springframework.msscbeerservice.domain.Beer#c8f24113-7182-4d10-b781-00bbedc798ff] - no Session
    // Without a transactional scope we wouldn't have a Hibernate session.
    @Transactional
    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE) // listen on that queue for messages
    public void listen(BrewBeerEvent event){ 
        // the DTO from the received BrewBeerEvent doesn't have QuantityToBrew>JT didn't want to expose property>could add it though>look at efficiency, using different types etc.
        BeerDto beerDto = event.getBeerDto();

        Beer beer = beerRepository.getOne(beerDto.getId()); // goto DB to getQuantityToBrew, JT prefers this

        // reuses beerDto>setting QuantityOnHand
        // In production>take the brewing through a life cycle - brewing run, inventory used etc + track in a db
        beerDto.setQuantityOnHand(beer.getQuantityToBrew()); // 'our simple beer brewing logic', I brewed it :-)

        //  We set the QuantityOnHand = QuantityToBrew, inventory service can use that for the quantity on hand of inventory.
        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto); 

        log.debug("Brewed beer " + beer.getMinOnHand() + " : QOH: " + beerDto.getQuantityOnHand());

        //  sends out a newInventoryEvent > we have more beer!!
        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent); 
    }
}
