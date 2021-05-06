package guru.springframework.msscbeerservice.services.inventory;

import guru.springframework.msscbeerservice.bootstrap.BeerLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled // utility for manual testing, I think you can only manually run this test??
@SpringBootTest // want context to autowire, takes a while to run as we bring up the spring context
class BeerInventoryServiceRestTemplateImplTest {

    @Autowired
    BeerInventoryService beerInventoryService;

    @BeforeEach
    void setUp() {

    }

    @Test // make sure mssc-beer-inventory-service app is running, clear console to see the endpoint is hit 
    void getOnhandInventory() {
        Integer qoh = beerInventoryService.getOnhandInventory(BeerLoader.BEER_1_UUID);

        System.out.println(qoh);

    }
}
