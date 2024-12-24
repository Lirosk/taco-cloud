package tacocloud.api;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/discounts")
@RequiredArgsConstructor
public class DiscountController {
    private final DiscountCodeProperties discountCodeProperties;

    @GetMapping(produces = "application/json")
    public Map<String, Integer> getDiscountCodes() {
        return discountCodeProperties.getCodes();
    }
}
