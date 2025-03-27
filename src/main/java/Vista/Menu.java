package Vista;

public class Menu {
    public static void mostrarMenuPrincipal() {
        System.out.println("Bienvenido al sistema de gestión");
        System.out.println("1. Dar de alta un Estado");
        System.out.println("2. Dar de baja un Estado");
        System.out.println("3. Gestionar Municipios y Colonias");
        System.out.println("4. Ver todos los registros guardados");
        System.out.println("5. Actualizar un registro guardado");
        System.out.println("6. Salir");
    }

    public static void mostrarMenuGestion() {
        System.out.println("1. Dar de alta un Municipio");
        System.out.println("2. Dar de alta una Colonia");
        System.out.println("3. Dar de baja un Municipio");
        System.out.println("4. Dar de baja una Colonia");
        System.out.println("5. Volver al menú principal");
    }

    public static void opcionInvalida() {
        System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
    }

    public static void errorDato( )
    {
        System.out.println("No es un dato valido");
    }
}
