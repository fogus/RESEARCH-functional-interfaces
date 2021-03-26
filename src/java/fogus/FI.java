package fogus;

import java.util.function.Supplier;

public class FI {
    public static Long supplier()
    {
        ThreadLocal<Long> local = ThreadLocal.withInitial(() -> 42L);
        return local.get();
    }
}