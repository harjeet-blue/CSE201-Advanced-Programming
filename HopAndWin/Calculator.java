package HopAndWin;

import java.util.ArrayList;

public class Calculator<T> {
    private T a;
    private T b;

    public Calculator(T a, T b) {

        if ((a instanceof Integer & b instanceof Integer) ||
                (a instanceof String & b instanceof String)) {
            this.a = a;
            this.b = b;
        } else {
            throw new InvalidTypeException("Calculator doesn't support this Type");
        }
    }

    public void set_a(T a) {
        this.a = a;
    }

    public void set_b(T b) {
        this.b = b;
    }

    public T get_a() {
        return this.a;
    }

    public T get_b() {
        return this.b;
    }

}

class Division extends Calculator<Integer> {

    public Division(Integer a, Integer b) {
        super(a, b);

    }

    public int divide() {
        return get_a() / get_b();
    }

    public boolean verify(Integer a) {

        return a == divide();
    }

    public void check() {
        if (get_b() == 0)
            throw new DividebyZeroException("Divide by zero exception");
    }
}

class Concatenate extends Calculator<String> {

    public Concatenate(String a, String b) {
        super(a, b);
    }

    public String concatenate() {
        return get_a() + get_b();
    }

    public boolean verify(String a) {
        return a.equals(concatenate());
    }
}

class Bucket<T> {
    private ArrayList<T> list;

    public Bucket() {
        list = new ArrayList<T>();
    }

    public void add(T o) {
        list.add(o);
    }

    public T get(int i) {
        try {
            return list.get(i);
        } catch (NullPointerException e) {
            System.out.println("Null value found");
        }
        return list.get(i);
    }
}