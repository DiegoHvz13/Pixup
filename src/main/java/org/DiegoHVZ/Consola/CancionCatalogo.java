package org.DiegoHVZ.Consola;

import org.DiegoHVZ.model.Cancion;
import org.DiegoHVZ.model.Disco;
import org.DiegoHVZ.util.ReadUtil;

import java.io.File;

public class CancionCatalogo extends Catalogos<Cancion>
{
    private static CancionCatalogo cancionCatalogo;

    private CancionCatalogo() { super();}
    public  static CancionCatalogo getInstance()
    {
        if (cancionCatalogo == null)
        {
            cancionCatalogo = new CancionCatalogo();
        }
        return cancionCatalogo;
    }



    @Override
    public Cancion newT() {
        return new Cancion();
    }

    @Override
    public boolean processNewT(Cancion cancion) {
        System.out.println("Escriba el nombre de la cancion: ");
        cancion.setTitulo(ReadUtil.read());
        System.out.println("Escriba la duracion de la cancion: ");
        cancion.setDuracion(ReadUtil.readFloat());
        System.out.println("Escriba el ID del disco al que pertenece la cancion: ");
        int idDisco = ReadUtil.readInt();

        Disco disco = DiscoCatalogo.getInstance().getDiscoById(idDisco);

        if (disco == null) {
            System.out.println("El disco con el ID " + idDisco + " no existe.");
            return false;
        }

        cancion.setIdDisco(disco.getId());
        return true;
    }

    @Override
    public void processEditT(Cancion cancion) {
        System.out.println("Id de la cancion " + cancion.getId());
        System.out.println("Cancion a editar: " + cancion.getTitulo());
        System.out.println("Teclee el nuevo nombre de la cancion");
        cancion.setTitulo(ReadUtil.read());
        System.out.println("Escriba el nuevo ID del disco al que pertenece la cancion: ");
        int idDisco = ReadUtil.readInt();
        Disco disco = DiscoCatalogo.getInstance().getDiscoById(idDisco);

        if (disco == null) {
            System.out.println("El disco con el ID " + idDisco + " no existe.");
            return;
        }
        cancion.setIdDisco(disco.getId());
    }

    @Override
    public File getFile() {
        return new File("Cancion.list");
    }
}
