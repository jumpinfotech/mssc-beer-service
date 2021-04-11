package guru.springframework.msscbeerservice.services;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.web.controller.NotFoundException;
import guru.springframework.msscbeerservice.web.mappers.BeerMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by jt on 2019-06-06.
 */
@RequiredArgsConstructor // needed so that the BeerMapper + BeerRepository are @Autowired
@Service // don't forget @Service!!
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper; // converts Beer to BeerDto + vice versa

    @Override
    public BeerDto getById(UUID beerId) {
        return beerMapper.beerToBeerDto( // mapper takes in a Beer + returns a BeerDto
                beerRepository.findById(beerId).orElseThrow(NotFoundException::new) // BeerRepository returns a Beer optional
        );
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        // convert BeerDto to Beer to save, convert Beer to BeerDto to return
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto))); 
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        //update logic added
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }
}
