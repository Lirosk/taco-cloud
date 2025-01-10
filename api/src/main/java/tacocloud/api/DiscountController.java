package tacocloud.api;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/discounts", produces = "application/json")
@RequiredArgsConstructor
public class DiscountController {
    private final DiscountCodeProperties discountCodeProperties;

    @GetMapping
    public Map<String, Integer> getDiscountCodes() {
        return discountCodeProperties.getCodes();
    }
}
