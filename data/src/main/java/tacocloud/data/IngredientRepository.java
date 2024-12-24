package tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import tacocloud.domain.Ingredient;

@CrossOrigin(origins = "*")
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
