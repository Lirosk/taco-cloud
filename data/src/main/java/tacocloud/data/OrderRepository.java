package tacocloud.data;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import tacocloud.domain.CustomUserDetails;
import tacocloud.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByUserOrderByPlacedAtDesc(CustomUserDetails user, Pageable pageable);
}
