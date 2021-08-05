package adventofcode.year20;

import adventofcode.utils.DayRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day16 implements DayRunner<Integer, Long> {

    List<String> fields = new ArrayList<>();
    String myTicket;
    List<String> otherTickets = new ArrayList<>();
    private List<Field> processedFields;

    private void parseInput(List<String> input) {
        boolean myTicketFound = false;
        fields = new ArrayList<>();
        otherTickets = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {

            if (input.get(i).isEmpty() || "nearby tickets:".equals(input.get(i))) {
                continue;
            }
            if ("your ticket:".equals(input.get(i))) {
                myTicketFound = true;
                myTicket = input.get(++i);
            } else if (!myTicketFound) {
                fields.add(input.get(i));
            } else {
                otherTickets.add(input.get(i));
            }

        }

        processedFields = fields.stream().map(this::parseField).collect(Collectors.toList());

    }

    boolean ticketMatches(String ticket) {
        var ticketFields = ticket.split(",");
        return Arrays.stream(ticketFields)
                .allMatch(t -> processedFields.stream().anyMatch(p -> p.isValid(Integer.parseInt(t))));
    }

    @Override
    public Integer runPart1(List<String> input) {

        this.parseInput(input);
        return otherTickets.stream().map(
                ticket -> {
                    var ticketFields = ticket.split(",");
                    return Arrays.stream(ticketFields)
                            .map(Integer::parseInt)
                            .filter(a -> !processedFields.stream().anyMatch(f -> f.isValid(a)))
                            .reduce(0, Integer::sum);
                }
        ).reduce(0, Integer::sum);
    }

    public Long findProduct(List<String> input, List<String> fieldsToMult) {

        this.parseInput(input);

        do {
            var ticketValues = otherTickets.stream()
                    .filter(this::ticketMatches)
                    .map(a -> Arrays.stream(a.split(","))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList()))
                    .collect(Collectors.toList());

            for (int i = 0; i < processedFields.size(); i++) {
                int finalI = i;
                var fieldValues = ticketValues.stream().map(t -> t.get(finalI)).collect(Collectors.toList());
                var matchingVals = processedFields.stream()
                        .filter(f -> f.position < 0 && fieldValues.stream().allMatch(f::isValid))
                        .collect(Collectors.toList());

                if (matchingVals.size() == 1) {
                    matchingVals.get(0).position = i;
                }
            }
        } while (processedFields.stream().anyMatch(a -> a.position == -1));

        var myTicketFields = myTicket.split(",");

        return fieldsToMult.stream()
                .map(f -> processedFields.stream()
                        .filter(p -> f.equals(p.name))
                        .findFirst().map(t -> t.position).orElse(-1))
                .map(t -> Long.parseLong(myTicketFields[t]))
                .reduce(1L, (a,b) -> a * b );
    }

    @Override
    public Long runPart2(List<String> input) {
        return findProduct(input,
                List.of(
                        "departure location",
        "departure station",
        "departure platform",
        "departure track",
        "departure date",
        "departure time"
                ));
    }

    Field parseField(String fieldValue) {
        var fieldComponents = fieldValue.split(":");
        var name = fieldComponents[0];
        var minMaxComponents = fieldComponents[1].trim().split(" ");
        var minMax = new int[][]{minMax(minMaxComponents[0]), minMax(minMaxComponents[2])};

        return new Field(name, minMax);
    }

    int[] minMax(String value) {
        var components = value.split("-");
        return new int[]{Integer.parseInt(components[0]), Integer.parseInt(components[1])};
    }

    static class Field {

        String name;
        int[][] minMax;
        int position = -1;

        public Field(String name, int[][] minMax) {
            this.name = name;
            this.minMax = minMax;
        }

        public boolean isValid(int val) {
            return Arrays.stream(minMax).anyMatch(a -> val >= a[0] && val <= a[1]);
        }
    }
}
