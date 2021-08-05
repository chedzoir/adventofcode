package adventofcode.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MapGrids {

    public static List<String> rotateLeft(List<String> map) {
        List<StringBuilder> tmp = new ArrayList<>();

        int size = map.size();
        for (var i = 0; i < size; i++) {
            tmp.add(new StringBuilder());
        }
        for (var i = 0; i < size; i++) {
            for (String s : map) {
                tmp.get(i).append(s.charAt(size - i - 1));
            }
        }

        return tmp.stream().map(StringBuilder::toString).collect(Collectors.toList());

    }

    public static List<String> flipVertical(List<String> map) {
        List<String> tmp = new ArrayList<>();
        for (var i=0;i<map.size(); i++) {
            tmp.add(map.get(map.size() - i - 1));
        }
        return tmp;
    }
}
