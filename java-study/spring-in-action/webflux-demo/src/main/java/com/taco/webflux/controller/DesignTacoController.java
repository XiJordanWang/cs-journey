package com.taco.webflux.controller;

import com.taco.cloud.entity.Ingredient;
import com.taco.webflux.dao.IngredientRepository;
import com.taco.webflux.dao.OrderRepository;
import com.taco.webflux.dao.TacoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/design")
public class DesignTacoController {

    @Resource
    private IngredientRepository ingredientRepository;

    @Resource
    private OrderRepository orderRepository;

    @Resource
    private TacoRepository tacoRepository;


    @GetMapping("/add")
    public Flux<Ingredient> addIngredient() {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
        );
        Flux<Ingredient> flux = Flux.fromIterable(ingredients);
        return ingredientRepository.saveAll(flux);
    }

    @GetMapping
    public Flux<Ingredient> get() {
        return ingredientRepository.findAll();
    }
}
