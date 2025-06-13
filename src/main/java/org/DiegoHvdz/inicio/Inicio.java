package org.DiegoHvdz.inicio;

import org.DiegoHvdz.vista.SeleccionEjecutable;

public class Inicio
{
    public static void main(String[] args)
    {
        System.out.println("\n\t================================");
        System.out.println("\t       <<  PixUp iniciado  >>");
        System.out.println("\t================================\n");

        SeleccionEjecutable.getInstance().run();

        System.out.println("\n\t--------------------------------");
        System.out.println("\t    ¡Gracias por usar PixUp!");
        System.out.println("\t--------------------------------\n");
    }
}
