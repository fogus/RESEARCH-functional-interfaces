package fogus;

import java.util.ArrayList;
import java.util.Collection;

public class VARARGS {
    public static void m(String... args) {
        System.out.println("String ... args");
    }

    public static void m(String arg) {
        System.out.println("String arg");
    }

    public static void m(Collection args) {
        System.out.println("Collection arg");
    }

    public static void main(String [] args) {
        m("A");
        m("a", "b");
        m(new ArrayList());
    }
}
