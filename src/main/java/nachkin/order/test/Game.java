package nachkin.order.test;

public class Game {

    private static void a() {
        System.out.println("empty");
    }

    private static void a(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {

        a();
        a("dkkcm");
        a(null);

    }

}
