package guru.springframework.msscbeerservice.repositories;

import guru.springframework.msscbeerservice.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

/**
 * Created by jt on 2019-05-17.
 */
// PagingAndSortingRepository>has findAll>returns pageable instance, 
// extends CrudRepository interface (use when no paging + sorting needed) which has many methods.
// some put @Repository here, unnecessary>already defined as a Repository up the chain>
// spring data JPA picks it up and wires it into your classes>covered in Spring 5 Course
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
}
