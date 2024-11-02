package com.example.tacocloud.repositories;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.tacocloud.models.Order;
import com.example.tacocloud.models.Taco;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JdbcOrderRepository implements OrderRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert orderInserter = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("Taco_Order")
            .usingGeneratedKeyColumns("id");
    private final SimpleJdbcInsert orderToTacoInserter = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("Taco_Order_Tacos");
    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public Order save(Order order) {
        order.setPlacedAt(new Date());
        long orderId = saveOrderDetails(order);
        order.setId(orderId);
        List<Taco> tacos = order.getTacos();
        for (Taco taco : tacos) {
            saveTacoToOrder(taco, order);
        }
        return order;
    }

    private long saveOrderDetails(Order order) {
        Map<String, Object> values = objectMapper.convertValue(order, Map.class);
        values.put("placedAt", order.getPlacedAt());

        return orderInserter.executeAndReturnKey(values).longValue();
    }

    private void saveTacoToOrder(Taco taco, Order order) {
        Map<String, Object> values = new HashMap<>();
        values.put("tacoOrder", order.getId());
        values.put("taco", taco.getIngredients());
        orderToTacoInserter.execute(values);
    }
}
