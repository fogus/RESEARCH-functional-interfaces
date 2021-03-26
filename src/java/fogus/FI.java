package fogus;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class FI {
    public static List<Long> supplier(long limit)
    {
        return Stream.generate(() -> 42L)
                .limit(limit)
                .collect(Collectors.toList());
    }
}
