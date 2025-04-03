package org.DiegoHVZ.Consola;

import org.DiegoHVZ.model.Disco;
import org.DiegoHVZ.util.ReadUtil;
import java.io.File;
import java.util.Optional;

public class DiscoCatalogo extends Catalogos<Disco> {
    private static DiscoCatalogo discoCatalogo;

    private DiscoCatalogo() {
        super();
    }

    public static DiscoCatalogo getInstance() {
        if (discoCatalogo == null) {
            discoCatalogo = new DiscoCatalogo();
        }
        return discoCatalogo;
    }

    @Override
    public Disco newT() {
        return new Disco();
    }

    @Override
    public boolean processNewT(Disco disco) {
        System.out.println("Escriba el título del disco: ");
        disco.setTitulo(ReadUtil.read());

        System.out.println("Ingrese el precio del disco: ");
        disco.setPrecio(ReadUtil.readFloat());

        System.out.println("Ingrese la existencia del disco: ");
        disco.setExistencia(ReadUtil.readInt());

        System.out.println("Ingrese el descuento aplicado al disco: ");
        disco.setDescuento(ReadUtil.readFloat());

        System.out.println("Ingrese la fecha de lanzamiento del disco: ");
        disco.setFechaLanzamiento(ReadUtil.read());

        System.out.println("Ingrese la URL de la imagen del disco: ");
        disco.setImagen(ReadUtil.read());

        System.out.println("Seleccione el ID de la disquera: ");
        DisqueraCatalogo.getInstance().print();
        int idDisquera = ReadUtil.readInt();
        disco.setIdDisquera(idDisquera);

        System.out.println("Seleccione el ID del artista: ");
        ArtistaCatalogo.getInstance().print();
        int idArtista = ReadUtil.readInt();
        disco.setIdArtista(idArtista);

        System.out.println("Seleccione el ID del género musical: ");
        GeneroMusicalCatalogo.getInstance().print();
        int idGeneroMusical = ReadUtil.readInt();
        disco.setIdGeneroMusical(idGeneroMusical);
        return true;
    }

    @Override
    public void processEditT(Disco disco) {
        System.out.println("ID del disco: " + disco.getId());
        System.out.println("Título actual: " + disco.getTitulo());
        System.out.println("Ingrese el nuevo título del disco: ");
        disco.setTitulo(ReadUtil.read());

        System.out.println("Precio actual: " + disco.getPrecio());
        System.out.println("Ingrese el nuevo precio del disco: ");
        disco.setPrecio(ReadUtil.readFloat());

        System.out.println("Existencia actual: " + disco.getExistencia());
        System.out.println("Ingrese la nueva existencia del disco: ");
        disco.setExistencia(ReadUtil.readInt());

        System.out.println("Descuento actual: "+disco.getDescuento());
        System.out.println("Ingrese el nuevo descuento para el disco: ");
        disco.setDescuento(ReadUtil.readFloat());

        System.out.println("Fecha de lanzamiento actual: "+ disco.getFechaLanzamiento());
        System.out.println("Ingrese la nueva fecha de lanzamiento: ");
        disco.setFechaLanzamiento(ReadUtil.read());

        System.out.println("URl actual de la imagen del disco: "+disco.getImagen());
        System.out.println("Ingrese la nueva URL de la imagen del disco: ");
        disco.setImagen(ReadUtil.read());

        System.out.println("ID actual de la disquera: "+disco.getIdDisquera());
        System.out.println("Seleccione el nuevo ID de la disquera: ");
        DisqueraCatalogo.getInstance().print();
        int idDisquera = ReadUtil.readInt();
        disco.setIdDisquera(idDisquera);

        System.out.println("ID actual del artista: "+disco.getIdArtista());
        System.out.println("Seleccione el nuevo ID del artista: ");
        ArtistaCatalogo.getInstance().print();
        int idArtista = ReadUtil.readInt();
        disco.setIdArtista(idArtista);

        System.out.println("ID actual del género musical: "+disco.getIdGeneroMusical());
        System.out.println("Seleccione el nuevo ID del género musical: ");
        GeneroMusicalCatalogo.getInstance().print();
        int idGeneroMusical = ReadUtil.readInt();
        disco.setIdGeneroMusical(idGeneroMusical);


    }

    @Override
    public File getFile() {
        return new File("Disco.list");
    }

    public Disco getDiscoById(int idDisco) {
        Optional<Disco> disco = list.stream()
                .filter(d -> d.getId().equals(idDisco))
                .findFirst();
        return disco.orElse(null);
    }
}

