package org.DiegoHvdz.sql.hibernateimpl;

import org.DiegoHvdz.model.Estado;
import org.DiegoHvdz.model.Municipio;
import org.DiegoHvdz.sql.GenericSql;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MunicipioHiberImplTest {

    @Test
    void getInstance() {
        GenericSql<Municipio> municipioHiber = MunicipioHiberImpl.getInstance();
        assertNotNull(municipioHiber);
    }

    @Test
    void findAll() {
        GenericSql<Municipio> municipioHiber = MunicipioHiberImpl.getInstance();
        List<Municipio> list = municipioHiber.findAll();

        assertNotNull(list);
        list.forEach(System.out::println);
    }

    @Test
    void save() {
        GenericSql<Municipio> municipioHiber = MunicipioHiberImpl.getInstance();
        GenericSql<Estado> estadoHiber = EstadoHiberImpl.getInstance();

        Estado estado = new Estado();
        estado.setEstado("Estado para municipio prueba");

        System.out.println("Longitud ESTADO: " + estado.getEstado().length());
        System.out.println("Texto ESTADO: " + estado.getEstado());

        estadoHiber.save(estado);

        for (int i = 1; i <= 3; i++) {
            Municipio municipio = new Municipio();
            municipio.setMunicipio("Municipio de Prueba " + i);
            municipio.setEstado(estado);

            municipioHiber.save(municipio);
            assertNotNull(municipio.getId());
        }
    }

    @Test
    void update() {
        GenericSql<Municipio> municipioHiber = MunicipioHiberImpl.getInstance();
        GenericSql<Estado> estadoHiber = EstadoHiberImpl.getInstance();

        Estado estado = new Estado();
        estado.setEstado("Estado para actualizar municipio");
        estadoHiber.save(estado);

        Municipio municipio = new Municipio();
        municipio.setMunicipio("Municipio original");
        municipio.setEstado(estado);
        municipioHiber.save(municipio);

        municipio.setMunicipio("Municipio actualizado");
        municipioHiber.update(municipio);

        Municipio actualizado = municipioHiber.findById(municipio.getId());
        assertEquals("Municipio actualizado", actualizado.getMunicipio());
    }

    @Test
    void delete() {
        GenericSql<Municipio> municipioHiber = MunicipioHiberImpl.getInstance();
        GenericSql<Estado> estadoHiber = EstadoHiberImpl.getInstance();

        Estado estado = new Estado();
        estado.setEstado("Estado temporal para eliminación");
        estadoHiber.save(estado);

        Municipio municipio = new Municipio();
        municipio.setMunicipio("Municipio para eliminar");
        municipio.setEstado(estado);
        municipioHiber.save(municipio);

        Municipio encontrado = municipioHiber.findById(municipio.getId());
        assertNotNull(encontrado);

        municipioHiber.delete(encontrado);

        Municipio eliminado = municipioHiber.findById(municipio.getId());
        assertNull(eliminado);
    }

    @Test
    void findById() {
        GenericSql<Municipio> municipioHiber = MunicipioHiberImpl.getInstance();
        GenericSql<Estado> estadoHiber = EstadoHiberImpl.getInstance();

        Estado estado = new Estado();
        estado.setEstado("Estado para búsqueda");
        estadoHiber.save(estado);

        Municipio municipio = new Municipio();
        municipio.setMunicipio("Municipio buscado");
        municipio.setEstado(estado);
        municipioHiber.save(municipio);

        Municipio recuperado = municipioHiber.findById(municipio.getId());
        assertNotNull(recuperado);
        System.out.println(recuperado);
    }
}
