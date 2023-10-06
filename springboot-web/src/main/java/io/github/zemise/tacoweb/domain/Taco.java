package io.github.zemise.tacoweb.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class Taco {
    private Long id;

    private Date createdAt;
    @NotNull
    @Size(min = 5, message = "Name must be at least 5 character long")
    private String name;

    @NotNull
    @Size(min = 1, message = "You must chose at least 1 ingredient")
    private List<Ingredient> ingredients;
}
