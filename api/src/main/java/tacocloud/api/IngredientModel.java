package tacocloud.api;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import tacocloud.domain.Ingredient;

@Getter
public class IngredientModel extends RepresentationModel<IngredientModel> {
    private final String name;
    private final Ingredient.Type type;

    public IngredientModel(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }
}
