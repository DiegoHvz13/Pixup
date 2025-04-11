package org.DiegoHVZ.Consola;

import org.DiegoHVZ.jdbc.impl.GeneroMusicalJdbcImpl;
import org.DiegoHVZ.model.Disquera;
import org.DiegoHVZ.model.GeneroMusical;
import org.DiegoHVZ.util.ReadUtil;

import java.io.File;

public class GeneroMusicalCatalogo extends Catalogos<GeneroMusical>
{

    private static GeneroMusicalCatalogo generoMusicalCatalogo;

    private GeneroMusicalCatalogo()
    {
        super(new GeneroMusicalJdbcImpl());
    }
    public  static GeneroMusicalCatalogo getInstance()
    {
        if (generoMusicalCatalogo == null)
        {
            generoMusicalCatalogo = new GeneroMusicalCatalogo();
        }
        return generoMusicalCatalogo;
    }

    @Override
    public GeneroMusical newT()
    {
        return new GeneroMusical();
    }

    @Override
    public boolean processNewT(GeneroMusical generoMusical)
    {
        System.out.println("Escriba el genero musical: ");
        generoMusical.setGeneroMusical(ReadUtil.read());
        return true;
    }

    @Override
    public void processEditT(GeneroMusical generoMusical)
    {
        System.out.println("Id del genero musical " + generoMusical.getId( ) );
        System.out.println("Genero musical a editar: " + generoMusical.getGeneroMusical( ) );
        System.out.println("Teclee el nuevo genero musical: " );
        generoMusical.setGeneroMusical( ReadUtil.read( ) );
    }

   /* @Override
    public File getFile() {
        return new File("GeneroMusical.list");
    }
*/

}
