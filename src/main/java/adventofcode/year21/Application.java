package adventofcode.year20;

import adventofcode.utils.DayRunner;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        var day = args[0];
        var input = args[1];
        var inputContent = Files.lines(Path.of(new File(input).toURI())).collect(Collectors.toList());

        var className = "adventofcode2020.Day" + day;

        var runnerClass = Class.forName(className);

        var runner = (DayRunner) runnerClass.getDeclaredConstructor().newInstance();

        if (runner != null) {

            long start = System.currentTimeMillis();
            var part1 = runner.runPart1(inputContent);
            long afterPart1 = System.currentTimeMillis();
            var part2 = runner.runPart2(inputContent);
            long afterPart2 = System.currentTimeMillis();

            System.out.println("Part 1 : " + part1 + " (" + (afterPart1 - start) + "ms)");
            System.out.println("Part 2 : " + part2 + " (" + (afterPart2 -afterPart1) + "ms)");
        }
    }
}
