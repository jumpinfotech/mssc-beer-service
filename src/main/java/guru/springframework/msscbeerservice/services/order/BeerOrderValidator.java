package guru.springframework.msscbeerservice.services.order;

import guru.sfg.brewery.model.events.BeerOrderDto;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jt on 12/2/19.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderValidator {

    private final BeerRepository beerRepository;

    public Boolean validateOrder(BeerOrderDto beerOrder){

        AtomicInteger beersNotFound = new AtomicInteger();

        // loop through orderlines on BeerOrderDto 
        beerOrder.getBeerOrderLines().forEach(orderline -> { 
            if(beerRepository.findByUpc(orderline.getUpc()) == null){
                // increment beersNotFound by 1 if Upc is missing from repository
                beersNotFound.incrementAndGet();
            }
        });

        // if 0 then it's a valid order
        return beersNotFound.get() == 0;
    }

}
