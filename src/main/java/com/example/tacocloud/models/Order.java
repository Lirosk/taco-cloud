package com.example.tacocloud.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.CreditCardNumber;

import com.example.tacocloud.users.CustomUserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "Taco_Order")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private Date placedAt;

    @NotBlank(message = "Name is required")
    private String deliveryName;

    @NotBlank(message = "Street is required")
    private String deliveryStreet;

    @NotBlank(message = "City is required")
    private String deliveryCity;

    @NotBlank(message = "State is required")
    @Size(max = 2, message = "Max length is 2")
    private String deliveryState;

    @NotBlank(message = "Zip code is required")
    private String deliveryZip;

//    @CreditCardNumber(ignoreNonDigitCharacters = true, message = "Not a valid credit card number")
    @NotBlank(message = "Credit card number is required")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    @ManyToOne
    private CustomUserDetails user;

    @ManyToMany
    private List<Taco> tacos = new ArrayList<>();

    public void addDesign(Taco design) {
        tacos.add(design);
    }

    @PrePersist
    void placedAt() {
        this.placedAt = new Date();
    }
}
