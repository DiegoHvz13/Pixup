package Vista;

public class Menu
{
    public static void principal()
    {
        System.out.println("Bienvenido");
        System.out.println("Elija su opcion deseada:");
        System.out.println("1. Consola");
        System.out.println("2. Salir");
    }

    public static void principal2()
    {
        System.out.println("1.Catalogo");
        System.out.println("2. Pendiente");
        System.out.println("3. salir");
    }

    public static void principal3()
    {
        System.out.println("1. Estado");
        System.out.println("2. Colonia");
        System.out.println("3. Municipio");
        System.out.println("4. Salir");

    }

    public static void errorDato( )
    {
        System.out.println("No es un dato valido");
    }

    public static void opcionInvalida( )
    {
        System.out.println("La opción no es correcta");
    }

    public static void seleccionaOpcion( )
    {
        System.out.println("Dame una opción");
    }



}
