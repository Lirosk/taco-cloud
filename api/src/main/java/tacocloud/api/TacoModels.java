package tacocloud.api;

import java.util.List;

import org.springframework.hateoas.CollectionModel;

public class TacoModels extends CollectionModel<TacoModel> {
    public TacoModels(List<TacoModel> tacos) {
        super(tacos);
    }
}
