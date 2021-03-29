package fogus;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.nio.file.Files;

public class FI {
    public static List<Long> supplier(long n)
    {
        return Stream.generate(() -> 42L)
                .limit(n)
                .collect(Collectors.toList());
    }

    public static Long consumer(long n)
    {
        AtomicLong accesses = new AtomicLong(0L);

        Stream.generate(() -> 42L)
                .limit(n)
                .forEach(x -> { System.out.println(x); accesses.getAndIncrement(); });

        return accesses.get();
    }

    public static Long biconsumer()
    {
        AtomicLong sum = new AtomicLong(0L);
        Map<String, Long> m = new HashMap<String, Long>() {{
            put("a", 1L);
            put("b", 2L);
            put("c", 3L);
            put("d", 4L);
            put("e", 5L);
        }};

        m.forEach((k,v) -> { sum.getAndAdd(v); System.out.println(k + "->" + v); });

        return sum.get();
    }

    public static List<String> func()
    {
        return Arrays.asList("foo", "bar", "baz").stream()
                .map(s -> s.toUpperCase())
                .collect(Collectors.toList());
    }

    public static List<Long> toLongFunc() {
        String[] ary = {"-42", "0", "42"};
        return Arrays.stream(ary)
                .mapToLong(s -> Long.parseLong(s, 10))
                .boxed()
                .collect(Collectors.toList());
    }

    public static Long toLongBiFunc() {
        ConcurrentHashMap<String, Long> m = new ConcurrentHashMap<String, Long>() {{
            put("a", 1);
            put("b", 2);
            put("c", 3);
            put("d", 4);
            put("e", 5);
        }};

        return m.reduceToLong(1,
                              (k, v) -> v,
                             0,
                              (acc, v) -> acc + v);

    }

    public static Long bifunc()
    {
        return Arrays.asList("foo", "bar", "baz").stream()
                .reduce(0L, (acc, s) -> acc + s.length(), Long::sum);
    }

    public static List<String> pred()
    {
        return Arrays.asList("foo", "frob", "bar", "baz", "quux").stream()
                .filter(s -> s.length() % 2 == 1)
                .collect(Collectors.toList());
    }

    public static List<Path> bipred() throws IOException {
        return Files.find(Path.of("."), 1, (path, attr) -> attr.isRegularFile() && path.toString().endsWith("md"))
                .collect(Collectors.toList());
    }

}
