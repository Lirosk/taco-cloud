package tacocloud.api;

import java.util.Date;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import tacocloud.domain.Taco;

@Getter
@Relation(value = "taco", collectionRelation = "tacos")
public class TacoModel extends RepresentationModel<TacoModel> {
    private static final IngredientModelAssembler ingredientModelAssembler = new IngredientModelAssembler();

    private final String name;
    private final Date createdAt;
    private final CollectionModel<IngredientModel> ingredients;

    public TacoModel(Taco taco) {
        this.name = taco.getName();
        this.createdAt = taco.getCreatedAt();
        this.ingredients = ingredientModelAssembler.toCollectionModel(taco.getIngredients());
    }
}
