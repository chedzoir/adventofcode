package adventofcode2020;

import java.util.LinkedList;
import java.util.List;

public class Day18 implements DayRunner<Long, Long> {

    public long[] evaluate(String sum, long index) {
        int pos = (int) index;

        LinkedList<Object> ops = new LinkedList<>();
        do {
            var currentChar = sum.charAt(pos);
            if (currentChar >= '0' && currentChar <= '9') {
                if (ops.size() > 0 && (Character.valueOf('+').equals(ops.getFirst()) || Character.valueOf('*').equals(ops.getFirst()))) {
                    var operator = (Character) ops.pop();
                    var value = (Long) ops.pop();
                    ops.push(calc(value, Long.parseLong("" + currentChar), operator));
                } else {
                    ops.push(Long.parseLong("" + currentChar));
                }
            }
            if (currentChar == '+' || currentChar == '*') {
                ops.push(currentChar);
            }
            if (currentChar == '(') {
                var val = evaluate(sum, pos + 1);
                if (ops.size() > 0) {
                    var operator = (Character) ops.pop();
                    var value = (Long) ops.pop();
                    ops.push(calc(value, val[0], operator));
                } else {
                    ops.push(val[0]);
                }
                pos = (int) val[1];
            }
            if (currentChar == ')') {
                break;
            }
            pos++;
        } while (pos < sum.length());


        return new long[]{(Long) ops.pop(), pos};
    }

    public long calc(long v1, long v2, char operator) {
        return '*' == operator ? v1 * v2 : v1 + v2;

    }

    public long evaluate(String sum) {
        return evaluate(sum, 0)[0];
    }

    @Override
    public Long runPart1(List<String> input) {
        return input.stream().map(this::evaluate).reduce(Long::sum).orElse(0L);
    }

    @Override
    public Long runPart2(List<String> input) {
        return input.stream().map(this::evaluateWithAdditionPrecedence).reduce(Long::sum).orElse(0L);
    }

    public Long evaluateWithAdditionPrecedence(String sum) {
        return evaluateWithAdditionPrecedence(sum, 0)[0];
    }

    public long[] evaluateWithAdditionPrecedence(String sum, int index) {
        int pos = (int) index;

        LinkedList<Object> ops = new LinkedList<>();
        do {
            var currentChar = sum.charAt(pos);
            if (currentChar >= '0' && currentChar <= '9') {
                if (ops.size() > 0 && (Character.valueOf('+').equals(ops.getFirst()))) {
                    var operator = (Character) ops.pop();
                    var value = (Long) ops.pop();
                    ops.push(calc(value, Long.parseLong("" + currentChar), operator));
                } else {
                    ops.push(Long.parseLong("" + currentChar));
                }
            }
            if (currentChar == '+' || currentChar == '*') {
                ops.push(currentChar);
            }
            if (currentChar == '(') {
                var val = evaluateWithAdditionPrecedence(sum, pos + 1);
                if (ops.size() > 0 && Character.valueOf('+').equals(ops.getFirst())) {
                    var operator = (Character) ops.pop();
                    var value = (Long) ops.pop();
                    ops.push(calc(value, val[0], operator));
                } else {
                    ops.push(val[0]);
                }
                pos = (int) val[1];
            }
            if (currentChar == ')') {
                break;
            }
            pos++;
        } while (pos < sum.length());

        long res = (Long)ops.pop();
        while(ops.size() > 0) {
            var op = (Character)ops.pop();
            var val = (Long)ops.pop();
            res *= val;
        }

        return new long[]{res, pos};
    }
}
