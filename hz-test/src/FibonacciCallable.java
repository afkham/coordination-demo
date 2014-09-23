import java.io.Serializable;
import java.util.concurrent.Callable;

public class FibonacciCallable implements Callable<Long>, Serializable {
    private final int input;

    public FibonacciCallable(int input) {
        this.input = input;
    }

    @Override
    public Long call() {
        System.out.println("FibonacciCallable - called");
        return calculate(input);
    }

    private long calculate(int n) {
        if (n <= 1) {
            return n;
        } else {
            return calculate(n - 1) + calculate(n - 2);
        }
    }
}