package org.DiegoHvdz.sql.hibernateimpl;

import org.DiegoHvdz.model.Colonia;
import org.DiegoHvdz.model.Estado;
import org.DiegoHvdz.model.Municipio;
import org.DiegoHvdz.sql.GenericSql;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ColoniaHiberImplTest {

    @Test
    void getInstance() {
        GenericSql<Colonia> coloniaHiber = ColoniaHiberImpl.getInstance();
        assertNotNull(coloniaHiber);
    }

    @Test
    void findAll() {
        GenericSql<Colonia> coloniaHiber = ColoniaHiberImpl.getInstance();
        List<Colonia> list = coloniaHiber.findAll();

        assertNotNull(list);
        list.forEach(System.out::println);
    }

    @Test
    void save() {
        GenericSql<Colonia> coloniaHiber = ColoniaHiberImpl.getInstance();
        GenericSql<Municipio> municipioHiber = MunicipioHiberImpl.getInstance();
        GenericSql<Estado> estadoHiber = EstadoHiberImpl.getInstance();

        // Crear y guardar Estado
        Estado estado = new Estado();
        estado.setEstado("Estado para prueba de colonia");
        estadoHiber.save(estado);

        // Crear y guardar Municipio con Estado
        Municipio municipio = new Municipio();
        municipio.setMunicipio("Municipio para colonia prueba");
        municipio.setEstado(estado);
        municipioHiber.save(municipio);

        for (int i = 1; i <= 3; i++) {
            Colonia colonia = new Colonia();
            colonia.setColonia("Colonia de Prueba " + i);
            colonia.setCp("0000" + i);
            colonia.setMunicipio(municipio);

            coloniaHiber.save(colonia);
            assertNotNull(colonia.getId());
        }
    }

    @Test
    void update() {
        GenericSql<Colonia> coloniaHiber = ColoniaHiberImpl.getInstance();
        GenericSql<Municipio> municipioHiber = MunicipioHiberImpl.getInstance();
        GenericSql<Estado> estadoHiber = EstadoHiberImpl.getInstance();

        Estado estado = new Estado();
        estado.setEstado("Estado para actualizar colonia");
        estadoHiber.save(estado);

        Municipio municipio = new Municipio();
        municipio.setMunicipio("Municipio para actualizar colonia");
        municipio.setEstado(estado);
        municipioHiber.save(municipio);

        Colonia colonia = new Colonia();
        colonia.setColonia("Colonia original");
        colonia.setCp("11111");
        colonia.setMunicipio(municipio);
        coloniaHiber.save(colonia);

        colonia.setColonia("Colonia actualizada");
        colonia.setCp("22222");
        coloniaHiber.update(colonia);

        Colonia actualizado = coloniaHiber.findById(colonia.getId());
        assertEquals("Colonia actualizada", actualizado.getColonia());
        assertEquals("22222", actualizado.getCp());
    }

    @Test
    void delete() {
        GenericSql<Colonia> coloniaHiber = ColoniaHiberImpl.getInstance();
        GenericSql<Municipio> municipioHiber = MunicipioHiberImpl.getInstance();
        GenericSql<Estado> estadoHiber = EstadoHiberImpl.getInstance();

        Estado estado = new Estado();
        estado.setEstado("Estado temporal para eliminar colonia");
        estadoHiber.save(estado);

        Municipio municipio = new Municipio();
        municipio.setMunicipio("Municipio temporal para eliminar colonia");
        municipio.setEstado(estado);
        municipioHiber.save(municipio);

        Colonia colonia = new Colonia();
        colonia.setColonia("Colonia para eliminar");
        colonia.setCp("33333");
        colonia.setMunicipio(municipio);
        coloniaHiber.save(colonia);

        Colonia encontrado = coloniaHiber.findById(colonia.getId());
        assertNotNull(encontrado);

        coloniaHiber.delete(encontrado);

        Colonia eliminado = coloniaHiber.findById(colonia.getId());
        assertNull(eliminado);
    }

    @Test
    void findById() {
        GenericSql<Colonia> coloniaHiber = ColoniaHiberImpl.getInstance();
        GenericSql<Municipio> municipioHiber = MunicipioHiberImpl.getInstance();
        GenericSql<Estado> estadoHiber = EstadoHiberImpl.getInstance();

        Estado estado = new Estado();
        estado.setEstado("Estado para buscar colonia");
        estadoHiber.save(estado);

        Municipio municipio = new Municipio();
        municipio.setMunicipio("Municipio para buscar colonia");
        municipio.setEstado(estado);
        municipioHiber.save(municipio);

        Colonia colonia = new Colonia();
        colonia.setColonia("Colonia buscada");
        colonia.setCp("44444");
        colonia.setMunicipio(municipio);
        coloniaHiber.save(colonia);

        Colonia recuperado = coloniaHiber.findById(colonia.getId());
        assertNotNull(recuperado);
        System.out.println(recuperado);
    }
}
