package tacocloud.api;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import tacocloud.domain.Taco;

public class TacoModelAssembler extends RepresentationModelAssemblerSupport<Taco, TacoModel> {
    public TacoModelAssembler() {
        super(DesignTacoController.class, TacoModel.class);
    }

    @Override
    protected TacoModel instantiateModel(Taco entity) {
        return new TacoModel(entity);
    }

    @Override
    public TacoModel toModel(Taco entity) {
        return createModelWithId(entity.getId(), entity);
    }
}
