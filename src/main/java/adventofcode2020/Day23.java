package adventofcode2020;

import adventofcode2020.day23.GameState;

import java.util.*;
import java.util.stream.Collectors;

public class Day23 implements DayRunner<String, Long> {

    public GameState runState(GameState state, long numberOfRuns) {
        var currentState = state;

        for (long i = 0; i < numberOfRuns; i++) {
            if (i % 100000 == 0) {
                System.out.println(". " + i);
            }
            currentState = runStep(currentState);
        }

        System.out.println("");

        return currentState;
    }


    GameState parseInput(List<String> input) {
        var cups = Arrays.stream(input.get(0).split(""))
                .map(Long::parseLong).collect(Collectors.toList());

        Map<Long, GameState.Cup> cupsMap = new HashMap<>();

        for (long i = 1; i <= cups.size(); i++) {
            cupsMap.put(i, new GameState.Cup(i));
        }

        for (int i = 0; i < cups.size(); i++) {
            var nextVal = cups.get((i + 1) % cups.size());
            cupsMap.get(cups.get(i)).next(cupsMap.get(nextVal));
        }

        cupsMap.get(cups.get(cups.size() - 1)).next(cupsMap.get(cups.get(0)));

        return new GameState(cupsMap, cups.get(0), cups.get(cups.size() - 1));
    }

    @Override
    public String runPart1(List<String> input) {

        var state = parseInput(input);
        var finalState = runState(state, 100);

        return getDisplayVal(finalState).replace("1", "");

    }

    private String getDisplayVal(GameState state) {

        StringBuilder stb = new StringBuilder();
        var current = state.get(1L);
        do {
            stb.append(current.getLabel());
            current = current.next();
        } while (!current.getLabel().equals(1L));

        return stb.toString();
    }


    public GameState runStep(GameState state) {

        var currentLocation = state.getCurrentLocation();

        var item1 = currentLocation.next();
        var item2 = item1.next();
        var item3 = item2.next();

        var destination = currentLocation.getLabel() - 1;

        if (destination <= 0) {
            destination = state.getMaxLabel();
        }

        while (item1.getLabel().equals(destination)
                || item2.getLabel().equals(destination)
                || item3.getLabel().equals(destination)) {
            destination--;
            if (destination <= 0) {
                destination = state.getMaxLabel();
            }
        }

        var newLocation = state.get(destination);

        currentLocation.next(item3.next());
        item3.next(newLocation.next());
        newLocation.next(item1);

        var nextLocation = currentLocation.next();

        state.setCurrentLocation(nextLocation.getLabel());

        return state;
    }

    @Override
    public Long runPart2(List<String> input) {

        var state = parseInput(input);

        var lastval = state.getLastVal();
        var current = state.get(lastval);
        var start = current.next();
        for (long i = 10; i <= 1000000; i++) {
            GameState.Cup nextCup = new GameState.Cup(i);
            current.next(nextCup);
            state.addCup(i, nextCup);
            current = nextCup;
        }
        current.next(start);

        runState(state, 10000000);

        var val = state.get(1L);
        var next1 = val.next();
        var next2 = next1.next();

        return next1.getLabel() * next2.getLabel();

    }
}
