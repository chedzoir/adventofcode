package adventofcode.year20;

import adventofcode.utils.DayRunner;

import java.util.*;
import java.util.stream.Collectors;


public class Day21 implements DayRunner<Long, String> {


    static class IngredientsListItem {
        List<String> ingredients;
        List<String> allergens;

        public IngredientsListItem(List<String> ingredients, List<String> allergens) {
            this.ingredients = ingredients;
            this.allergens = allergens;
        }
    }

    @Override
    public Long runPart1(List<String> input) {
        var ingredientsList = parseInput(input);

        var possibleIngredients = getIngredientsWithAllergens(ingredientsList);

        var possibleIngredientsWithAllergen = possibleIngredients.values()
                .stream().flatMap(a -> a.stream())
                .collect(Collectors.toSet());

        // How many ingredients?
        return ingredientsList.stream().flatMap(i -> i.ingredients.stream())
                .filter(i -> !possibleIngredientsWithAllergen.contains(i))
                .count();

    }

    private Map<String, Set<String>> getIngredientsWithAllergens(List<IngredientsListItem> ingredientsList) {

        // get the list of allergens
        var allergens = ingredientsList.stream().flatMap(a -> a.allergens.stream())
                .collect(Collectors.toSet());

        Map<String, Set<String>> possibleIngredients = new HashMap<>();

        allergens.forEach(a -> {
            var hasAllergen = ingredientsList.stream()
                    .filter(i -> i.allergens.contains(a))
                    .collect(Collectors.toList());

            possibleIngredients.put(a, new HashSet<>());
            possibleIngredients.get(a).addAll(hasAllergen.get(0).ingredients);

            for (int i = 1; i < hasAllergen.size(); i++) {
                possibleIngredients.get(a).retainAll(hasAllergen.get(i).ingredients);
            }
        });

        return possibleIngredients;
    }

    @Override
    public String runPart2(List<String> input) {

        var ingredientsList = parseInput(input);

        var possibleIngredients = getIngredientsWithAllergens(ingredientsList);

        Map<String, String> ingredientsWithAllergen = new HashMap<>();

        // Get items that only have one thing in them
        do {
            var itemsWithOneIngredient = possibleIngredients.entrySet().stream().filter(e -> e.getValue().size() == 1)
                    .collect(Collectors.toList());

            itemsWithOneIngredient.forEach(e -> {
                var allergen = e.getKey();
                var ingredient = e.getValue().toArray(new String[1])[0];

                // Add the allergen and ingredient into the map
                ingredientsWithAllergen.put(allergen, ingredient);
                // remove that allergen from the items we're looking at
                possibleIngredients.remove(allergen);
                // remove the ingredient from everywhere else
                possibleIngredients.values().forEach(v -> v.remove(ingredient));
            });
        } while (possibleIngredients.size() > 0);

        var answer = ingredientsWithAllergen.keySet()
                .stream()
                .sorted()
                .map(a -> ingredientsWithAllergen.get(a))
                .toArray(String[]::new);

        return String.join(",", answer);
    }

    private List<IngredientsListItem> parseInput(List<String> items) {
        return items.stream().map(a -> {
            var pieces = a.split("\\(");
            var ingredients = List.of(pieces[0].split(" "));
            var allergens = List.of(pieces[1].replaceAll("contains ", "")
                    .replace(")", "")
                    .replace(",", "")
                    .split(" "));

            return new IngredientsListItem(ingredients, allergens);
        }).collect(Collectors.toList());
    }
}
