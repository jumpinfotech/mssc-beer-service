package guru.springframework.msscbeerservice.web.mappers;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.services.inventory.BeerInventoryService;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BeerMapperDecorator implements BeerMapper {
    private BeerInventoryService beerInventoryService; // takes in instance of BeerInventoryService
    private BeerMapper mapper; // abstract class takes in original mapper

//    Always do constructor implementation as far as dependency injection - however Mapstruct wasn't happy with having those constructors. 
//    These 2 set* allow Mapstruct to manage everything.  

    @Autowired
    public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
        this.beerInventoryService = beerInventoryService;
    }

    @Autowired
    public void setMapper(BeerMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public BeerDto beerToBeerDto(Beer beer) {
       return mapper.beerToBeerDto(beer); // returns back original mapper, not enhanced
    }

    @Override
    public BeerDto beerToBeerDtoWithInventory(Beer beer) {
        BeerDto dto = mapper.beerToBeerDto(beer);
        // enhancing BeerDto object, with quantity information coming back from service call
        dto.setQuantityOnHand(beerInventoryService.getOnhandInventory(beer.getId())); // get on hand inventory for beer id 
        
        return dto;
    }

    @Override
    public Beer beerDtoToBeer(BeerDto beerDto) {
        return mapper.beerDtoToBeer(beerDto); // we don't need to change this original functionality
    } 
}
