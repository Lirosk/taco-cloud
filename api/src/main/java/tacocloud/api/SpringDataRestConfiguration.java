package tacocloud.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelProcessor;

import tacocloud.domain.Taco;

@Configuration
public class SpringDataRestConfiguration {
    @Bean
    public RepresentationModelProcessor<PagedModel<TacoModel>> tacoProcessor(EntityLinks entityLinks) {
        return model -> {
            model.add(entityLinks.linkFor(Taco.class).slash("recent").withRel("recents"));
            return model;
        };
    }
}
