package adventofcode2020;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
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
            var part1 = runner.runPart1(inputContent);
            var part2 = runner.runPart2(inputContent);

            System.out.println("Part 1 : " + part1);
            System.out.println("Part 2 : " + part2);
        }
    }
}
