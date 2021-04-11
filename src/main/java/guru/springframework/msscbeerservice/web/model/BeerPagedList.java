package guru.springframework.msscbeerservice.web.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by jt on 2019-05-12.
 */
public class BeerPagedList extends PageImpl<BeerDto> {

//  Jackson can consume a Page Object from another Spring Service>JSON containing the page object is consumed>then bound to a BeerPageList. 
//  The constructor takes in many properties>greyed out properties aren't used>later a new POJO will be created + these properties will be passed into the super constructor of that.
//  Binding creates a complex POJO. He's not sure if he will need to use this in this service, this is just an example ... not sure if this is useful???
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BeerPagedList(@JsonProperty("content") List<BeerDto> content,
                         @JsonProperty("number") int number,
                         @JsonProperty("size") int size,
                         @JsonProperty("totalElements") Long totalElements,
                         @JsonProperty("pageable") JsonNode pageable,
                         @JsonProperty("last") boolean last,
                         @JsonProperty("totalPages") int totalPages,
                         @JsonProperty("sort") JsonNode sort,
                         @JsonProperty("first") boolean first,
                         @JsonProperty("numberOfElements") int numberOfElements) {

        super(content, PageRequest.of(number, size), totalElements);
    }

    public BeerPagedList(List<BeerDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public BeerPagedList(List<BeerDto> content) {
        super(content);
    }
}
