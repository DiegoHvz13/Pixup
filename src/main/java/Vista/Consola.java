package Vista;

public class Consola {
    private static Consola consola;

    public Consola() {}

    public static Consola getInstance() {
        if (consola == null) {
            consola = new Consola();
        }
        return consola;
    }

}
