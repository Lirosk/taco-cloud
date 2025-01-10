package tacocloud.api;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import tacocloud.data.TacoRepository;
import tacocloud.domain.Taco;

@RepositoryRestController
@RequiredArgsConstructor
public class RecentTacosController {
    private final TacoRepository tacoRepository;

    @GetMapping(path = "/tacos/recent", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<TacoModel>> getRecentTacos() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        List<Taco> tacos = tacoRepository.findAll(page).getContent();
        CollectionModel<TacoModel> recentModels = new TacoModelAssembler().toCollectionModel(tacos);
        recentModels.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RecentTacosController.class).getRecentTacos()).withRel("recents"));
        return new ResponseEntity<>(recentModels, HttpStatus.OK);
    }
}
