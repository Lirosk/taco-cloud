package com.example.tacocloud.repositories;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.tacocloud.models.Ingredient;
import com.example.tacocloud.models.Taco;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JdbcTacoRepository implements TacoRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Taco save(Taco taco) {
        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        for (Ingredient ingredient : taco.getIngredients()) {
            saveIngredientToTaco(ingredient, taco);
        }

        return taco;
    }

    private long saveTacoInfo(Taco taco) {
        taco.setCreatedAt(new Date());
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory("insert into Taco (name, createdAt) values (?, ?)", Types.VARCHAR, Types.TIMESTAMP);
        pscf.setReturnGeneratedKeys(true);
        PreparedStatementCreator psc = pscf
                .newPreparedStatementCreator(
                        Arrays.asList(
                                taco.getName(),
                                new Timestamp(taco.getCreatedAt().getTime())
                        )
                );

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    private void saveIngredientToTaco(Ingredient ingredient, Taco taco) {
        jdbcTemplate.update("insert into Taco_Ingredients (taco, ingredient) values (?, ?)", taco.getId(), ingredient.getId());
    }
}
