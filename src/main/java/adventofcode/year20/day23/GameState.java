package adventofcode.year20.day23;

import java.util.Map;

public class GameState {


    public void addCup(long label, Cup cup) {
        this.cups.put(label, cup);
    }

    public static class Cup {
        Long label;
        Cup nextCup;

        public Cup(Long label) {
            this.label = label;
        }

        public Long getLabel() {
            return label;
        }

        public Cup next() {
            return nextCup;
        }

        public void next(Cup nextCup) {
            this.nextCup = nextCup;
        }
    }


    Map<Long, Cup> cups;
    Long currentLocation;
    Long lastVal;

    public GameState(Map<Long, Cup> cups, Long currentLocation, Long lastVal) {
        this.cups = cups;
        this.currentLocation = currentLocation;
        this.lastVal = lastVal;
    }

    public Long getLastVal() {
        return lastVal;
    }

    public void setCurrentLocation(Long currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Cup getCurrentLocation() {
        return cups.get(currentLocation);
    }

    public int getMaxLabel() {
        return cups.size();
    }

    public Cup get(Long destination) {
        return this.cups.get(destination);
    }


}
