package adventofcode2020;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day4 implements DayRunner<Long, Long> {

    private List<Map<String, String>> parseInput(List<String> input) {

        List<Map<String, String>> res = new ArrayList<>();

        Map<String, String> content = new HashMap<>();
        for (var row : input) {
            if ("".equals(row.trim())) {
                res.add(content);
                content = new HashMap<>();
            } else {
                var rowValues = Arrays.stream(row.split(" "))
                        .collect(Collectors.toMap(a -> a.split(":")[0],
                                a -> a.split(":")[1]));
                content.putAll(rowValues);
            }
        }

        if (input.get(input.size() - 1).trim().length() > 0) {
            res.add(content);
        }

        return res;
    }

    @Override
    public Long runPart1(List<String> input) {

        var parsedInput = this.parseInput(input);

        return parsedInput.stream().filter(a ->
                a.containsKey("byr") &&
                        a.containsKey("iyr") &&
                        a.containsKey("eyr") &&
                        a.containsKey("hgt") &&
                        a.containsKey("hcl") &&
                        a.containsKey("ecl") &&
                        a.containsKey("pid"))
                .count();

    }

    @Override
    public Long runPart2(List<String> input) {
        var parsedInput = this.parseInput(input);

        return parsedInput.stream().filter(
                a -> this.validNumber(a.get("byr"), 1920, 2002)
                        && this.validNumber(a.get("iyr"), 2010, 2020)
                        && this.validNumber(a.get("eyr"), 2020, 2030)
                        && this.validHeight(a.get("hgt"))
                        && this.validColour(a.get("hcl"))
                        && this.validEyeColour(a.get("ecl"))
                        && this.validPptNumber(a.get("pid"))
        ).count();
    }

    boolean validNumber(String val, int min, int max) {
        try {
            var intVal = Integer.parseInt(val);
            return intVal >= min && intVal <= max;
        } catch (Exception e) {
            return false;
        }
    }

    boolean validHeight(String val) {
        if (val != null && (val.endsWith("in") || val.endsWith("cm"))) {
            var height = Integer.parseInt(val.substring(0, val.length() - 2));
            if (val.endsWith("in")) {
                return height >= 59 && height <= 76;
            } else {
                return height >= 150 && height <= 193;
            }
        }

        return false;
    }

    boolean validEyeColour(String val) {
        var colours = Set.of(
                "amb", "blu", "brn", "gry", "grn", "hzl", "oth"
        );

        return val != null && colours.contains(val);
    }

    boolean validPptNumber(String val) {
        if (val != null && val.length() == 9) {
            try {
                Integer.parseInt(val);
                return true;
            } catch ( Exception e) {
                // do nothing
            }
        }
        return false;
    }

    boolean validColour(String val) {

        var pattern = Pattern.compile("#[a-f0-9]{6}");

        return val != null && pattern.matcher(val).matches();
    }
}
