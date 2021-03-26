package fogus;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class FI {
    public static List<Long> supplier(long n)
    {
        return Stream.generate(() -> 42L)
                .limit(n)
                .collect(Collectors.toList());
    }

    public static List<String> func()
    {
        return Arrays.asList("foo", "bar", "baz").stream()
                .map(s -> s.toUpperCase())
                .collect(Collectors.toList());
    }

    public static Long bifunc()
    {
        return Arrays.asList("foo", "bar", "baz").stream()
                .reduce(0L, (acc, s) -> acc + s.length(), Long::sum);
    }
}
