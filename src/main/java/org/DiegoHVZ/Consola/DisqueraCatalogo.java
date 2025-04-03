package org.DiegoHVZ.Consola;

import org.DiegoHVZ.model.Artista;
import org.DiegoHVZ.model.Disquera;
import org.DiegoHVZ.util.ReadUtil;

import java.io.File;

public class DisqueraCatalogo extends Catalogos<Disquera>
{

    private static DisqueraCatalogo disqueraCatalogo;

    private DisqueraCatalogo()
    {
        super();
    }
    public  static DisqueraCatalogo getInstance()
    {
        if (disqueraCatalogo == null)
        {
            disqueraCatalogo = new DisqueraCatalogo();
        }
        return disqueraCatalogo;
    }

    @Override
    public Disquera newT()
    {
        return new Disquera();
    }

    @Override
    public boolean processNewT(Disquera disquera)
    {
        System.out.println("Escriba el nombre de la disquera: ");
        disquera.setDisquera(ReadUtil.read());
        return true;
    }

    @Override
    public void processEditT(Disquera disquera)
    {
        System.out.println("Id de la disquera " + disquera.getId( ) );
        System.out.println("Disquera a editar: " + disquera.getDisquera( ) );
        System.out.println("Teclee el nuevo nombre de la disquera" );
        disquera.setDisquera( ReadUtil.read( ) );
    }

    @Override
    public File getFile() {
        return new File("Disquera.list");
    }


}

