package org.DiegoHvdz.vista.consola;

import org.DiegoHvdz.vista.*;
import org.DiegoHvdz.vista.consola.disco.*;

public class ListaDisco extends LeerAcciones
{
    private static ListaDisco listaDisco;

    private ListaDisco()
    {
    }

    public static ListaDisco getInstance()
    {
        if (listaDisco == null)
        {
            listaDisco = new ListaDisco();
        }
        return listaDisco;
    }

    @Override
    public void despliegaMenu()
    {
        System.out.println("\n\t===============================");
        System.out.println("\t    ::: Catálogo de Discos :::");
        System.out.println("\t===============================\n");
        System.out.println("\t1. Artista");
        System.out.println("\t2. Disquera");
        System.out.println("\t3. Género musical");
        System.out.println("\t4. Disco");
        System.out.println("\t5. Canción");
        System.out.println("\t6. Volver\n");
        System.out.println("--------------------------------");
        System.out.print("> Selecciona una opción: ");
        Menu.seleccionaOpcion();
    }

    @Override
    public int valorMinMenu()
    {
        return 1;
    }

    @Override
    public int valorMaxMenu()
    {
        return 6;
    }

    @Override
    public void procesaOpcion()
    {
        Ejecutable ejecutable = null;
        switch (opcion)
        {
            case 1:
                ejecutable = ArtistaCatalogo.getInstance();
                break;
            case 2:
                ejecutable = DisqueraCatalogo.getInstance();
                break;
            case 3:
                ejecutable = GeneroMusicalCatalogo.getInstance();
                break;
            case 4:
                ejecutable = DiscoCatalogo.getInstance();
                break;
            case 5:
                ejecutable = CancionCatalogo.getInstance();
                break;
            case 6:
                flag = false;
                break;
            default:
                Menu.opcionInvalida();
                break;
        }

        if (ejecutable != null)
        {
            ejecutable.setFlag(true);
            ejecutable.run();
        }
    }
}
