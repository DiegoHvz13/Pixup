package org.DiegoHVZ.Consola;

import org.DiegoHVZ.jdbc.impl.ArtistaJdbcImpl;
import org.DiegoHVZ.jdbc.impl.EstadoJdbcImpl;
import org.DiegoHVZ.model.Artista;
import org.DiegoHVZ.util.ReadUtil;

import java.io.File;

public class ArtistaCatalogo extends Catalogos<Artista>
{

    private static ArtistaCatalogo artistaCatalogo;

    private ArtistaCatalogo()
    {
        super(new ArtistaJdbcImpl());
    }
    public  static ArtistaCatalogo getInstance()
    {
        if (artistaCatalogo == null)
        {
            artistaCatalogo = new ArtistaCatalogo();
        }
        return artistaCatalogo;
    }

    @Override
    public Artista newT()
    {
        return new Artista();
    }

    @Override
    public boolean processNewT(Artista artista)
    {
        System.out.println("Escriba el nombre del artista: ");
        artista.setArtista(ReadUtil.read());
        return true;
    }

    @Override
    public void processEditT(Artista artista)
    {
        System.out.println("Id del Artista " + artista.getId( ) );
        System.out.println("Artista a editar: " + artista.getArtista( ) );
        System.out.println("Teclee el nuevo nombre del artista" );
        artista.setArtista( ReadUtil.read( ) );
    }

    /*@Override
    public File getFile() {
        return new File("Artista.list");
    }

     */


}

