package adventofcode2020;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

record Bag(String colour, Map<String, Integer> holds) {
}

public class Day7 implements DayRunner<Long, Long> {

    private Bag processContent(String rule) {
        var bagColour = rule.substring(0, rule.indexOf("bags contain")).trim();

        if (rule.contains("contain no other bags")) {
            return new Bag(bagColour, null);
        }

        var contents = rule.substring(rule.indexOf("bags contain") + 12).trim().split(",");

        Map<String, Integer> bagHolds = new HashMap<>();

        Arrays.stream(contents).forEach(content -> {
            var bagVal = content.replaceAll("bags?[,.]?", "").trim();

            int number = Integer.parseInt(bagVal.substring(0, bagVal.indexOf(" ")));
            String colour = bagVal.substring(bagVal.indexOf(" ")).trim();

            bagHolds.put(colour, number);
        });

        return new Bag(bagColour, bagHolds);

    }

    boolean holdsAColour(String bagColour, Map<String, Bag> bags, String target) {

        var bag = bags.get(bagColour);
        if (bag.colour().equals(target)) {
            return true;
        }

        if (bag.holds() == null) {
            return false;
        }
        return bag.holds().keySet().stream().anyMatch(a -> holdsAColour(a, bags, target));
    }

    Long bagsContaining(String bagColour, Map<String, Bag> bags) {
        var current = bags.get(bagColour);

        if (current.holds() == null) {
            return 1L;
        }

        return current.holds().keySet().stream()
                .map(a -> current.holds().get(a) * bagsContaining(a, bags))
                .reduce(Long::sum)
                .orElse(0L) + 1;
    }

    @Override
    public Long runPart1(List<String> input) {

        Map<String, Bag> bags = input.stream().map(this::processContent).collect(
                Collectors.toMap(Bag::colour, a -> a)
        );

        return bags.keySet().stream().filter(a -> !"shiny gold".equals(a) && holdsAColour(a, bags, "shiny gold")).count();
    }

    @Override
    public Long runPart2(List<String> input) {

        Map<String, Bag> bags = input.stream().map(this::processContent).collect(
                Collectors.toMap(Bag::colour, a -> a)
        );

        // We take of the 1 as we don't want to count the shiny gold bag.
        return bagsContaining("shiny gold", bags) - 1;

    }
}
