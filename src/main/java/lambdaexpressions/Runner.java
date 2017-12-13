package lambdaexpressions;

public class Runner {

    private static Runnable r1 = () -> System.out.println("Hello world 1");

    private static Runnable r2 = new Runnable() {
        @Override
        public void run() {
            System.out.println("Hello world 2");
        }
    };

    private static void run(Runnable r) {
        r.run();
    }

    public static void main(String[] args) {
        run(r1);
        run(r2);
        run(() -> System.out.println("Hello World 3"));
    }
}
