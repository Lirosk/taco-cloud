package tacocloud.api;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import tacocloud.data.OrderRepository;
import tacocloud.domain.CustomUserDetails;
import tacocloud.domain.Order;

@RestController
@RequestMapping(path = "/orders", produces = "application/json")
@RequiredArgsConstructor
public class OrderController {
    private final OrderRepository orderRepository;
    private final OrderProperties orderProperties;

    @PostMapping
    public Order postOrder(@Valid Order order, @AuthenticationPrincipal CustomUserDetails user) {
        order.setUser(user);
        return orderRepository.save(order);
    }

    @GetMapping
    public List<Order> getOrdersForUser(@AuthenticationPrincipal CustomUserDetails user) {
        Pageable pageable = PageRequest.of(0, orderProperties.getPageSize());
        return orderRepository.findByUserOrderByPlacedAtDesc(user, pageable);
    }
}
