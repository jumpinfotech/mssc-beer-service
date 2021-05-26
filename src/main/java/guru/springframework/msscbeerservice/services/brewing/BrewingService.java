package guru.springframework.msscbeerservice.services.brewing;

import guru.springframework.msscbeerservice.config.JmsConfig;
import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.events.BrewBeerEvent;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.services.inventory.BeerInventoryService;
import guru.springframework.msscbeerservice.web.mappers.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jt on 2019-07-21.
 */
@Slf4j // logger
@Service // indicates it's a service
@RequiredArgsConstructor
public class BrewingService {
    
    //Spring will inject these>Lombok providing the constructor:-
    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000) // every 5 seconds
    public void checkForLowInventory(){
        List<Beer> beers = beerRepository.findAll();

        beers.forEach(beer -> {
            // get current stock level
            // beerInventoryService>makes a REST call to the inventory service>gets the on-hand inventory for that item
            Integer invQOH = beerInventoryService.getOnhandInventory(beer.getId()); 

            log.debug("Min Onhand is: " + beer.getMinOnHand());
            log.debug("Inventory is: "  + invQOH);

            // if minimum on-hand is greater than or equal to the current inventory>create a BrewBeerEvent + submit that to the JMS queue
            if(beer.getMinOnHand() >= invQOH){
                // brew more inventory
                // previously defined beerMapper>convert the entity to a DTO 
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
            }
        });

    }
}
