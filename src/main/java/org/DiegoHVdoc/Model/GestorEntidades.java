package org.DiegoHVdoc.Model;
import Vista.Menu;
import Util.ReadUtil;
import java.util.ArrayList;
import java.util.List;

    public class GestorEntidades
    {
        private List<Estado> estados;
        private List<Municipio> municipios;
        private List<Colonia> colonias;

        public GestorEntidades()
        {
            estados = new ArrayList<>();
            municipios = new ArrayList<>();
            colonias = new ArrayList<>();
        }

        public void darDeAltaEstado()
        {
            System.out.println("Ingrese el nombre del Estado:");
            String nombreEstado = ReadUtil.read();
            Estado estado = new Estado(estados.size() + 1, nombreEstado);
            estados.add(estado);
            System.out.println("Estado " + nombreEstado + " dado de alta exitosamente.");
        }

        public void gestionarMYC()
        {
            boolean flag = true;
            int opcion;

            while (flag) {
                Menu.mostrarMenuGestion();
                opcion = ReadUtil.readInt();

                switch (opcion)
                {
                    case 1:
                        darDeAltaMunicipio();
                        break;
                    case 2:
                        darDeAltaColonia();
                        break;
                    case 3:
                        darDeBajaMunicipio();
                        break;
                    case 4:
                        darDeBajaColonia();
                        break;
                    case 5:
                        flag = false;
                        break;
                    default:
                        Menu.opcionInvalida();
                        break;
                }
            }
        }

        public void darDeAltaMunicipio()
        {
            System.out.println("Seleccione el Estado al que pertenece el Municipio:");
            for (Estado estado : estados) {
                System.out.println(estado.getId() + ". " + estado.getNombre());
            }
            int idEstado = ReadUtil.readInt();
            Estado estadoSeleccionado = estados.get(idEstado - 1);

            System.out.println("Ingrese el nombre del Municipio:");
            String nombreMunicipio = ReadUtil.read();
            Municipio municipio = new Municipio(municipios.size() + 1, nombreMunicipio, estadoSeleccionado);
            municipios.add(municipio);
            System.out.println("Municipio " + nombreMunicipio + " dado de alta exitosamente.");
        }

        public void darDeAltaColonia()
        {
            System.out.println("Seleccione el Municipio al que pertenece la Colonia:");
            for (Municipio municipio : municipios) {
                System.out.println(municipio.getId() + ". " + municipio.getNombre());
            }
            int idMunicipio = ReadUtil.readInt();
            Municipio municipioSeleccionado = municipios.get(idMunicipio - 1);

            System.out.println("Ingrese el nombre de la Colonia:");
            String nombreColonia = ReadUtil.read();
            System.out.println("Ingrese el código postal de la Colonia:");
            String cpColonia = ReadUtil.read();
            Colonia colonia = new Colonia(colonias.size() + 1, nombreColonia, cpColonia, municipioSeleccionado);
            colonias.add(colonia);
            System.out.println("Colonia " + nombreColonia + " dada de alta exitosamente.");
        }

        public void darDeBajaEstado()
        {
            if (estados.isEmpty())
            {
                System.out.println("No hay ningún estado registrado.");
            }
            else
            {
                System.out.println("Seleccione el estado que desea dar de baja: ");
                for (Estado estado : estados) {
                    System.out.println(estado.getId() + ". " + estado.getNombre());
                }
                int idEstado = ReadUtil.readInt();
                Estado estadoSeleccionado = null;
                for (Estado estado : estados) {
                    if (estado.getId() == idEstado) {
                        estadoSeleccionado = estado;
                        break;
                    }
                }

                if (estadoSeleccionado != null) {
                    estados.remove(estadoSeleccionado);
                    System.out.println("Estado " + estadoSeleccionado.getNombre() + " dado de baja exitosamente.");
                } else {
                    System.out.println("No se encontró el estado con el ID: " + idEstado);
                }

            }

        }

        public void darDeBajaMunicipio()
        {
            if (municipios.isEmpty())
            {
                System.out.println("No hay ningún municipio registrado.");
            }
            else
            {
                System.out.println("Seleccione el municipio que desea dar de baja: ");
                for (Municipio municipio : municipios) {
                    System.out.println(municipio.getId() + ". " + municipio.getNombre());
                }
                int idMunicipio = ReadUtil.readInt();
                Municipio municipioSeleccionado = null;
                for (Municipio municipio : municipios) {
                    if (municipio.getId() == idMunicipio) {
                        municipioSeleccionado = municipio;
                        break;
                    }
                }

                if (municipioSeleccionado != null) {
                    municipios.remove(municipioSeleccionado);
                    System.out.println("Municipio " + municipioSeleccionado.getNombre() + " dado de baja exitosamente.");
                } else {
                    System.out.println("No se encontró el Municipio con el ID: " + idMunicipio);
                }

            }

        }

        public void darDeBajaColonia()
        {
            if (colonias.isEmpty())
            {
                System.out.println("No hay ninguna colonia registrada.");
            }
            else
            {
                System.out.println("Seleccione la colonia que desea dar de baja: ");
                for (Colonia colonia : colonias) {
                    System.out.println(colonia.getId() + ". " + colonia.getNombre());
                }
                int idColonia = ReadUtil.readInt();
                Colonia coloniaSeleccionada = null;
                for (Colonia colonia : colonias) {
                    if (colonia.getId() == idColonia) {
                        coloniaSeleccionada = colonia;
                        break;
                    }
                }

                if (coloniaSeleccionada != null) {
                    colonias.remove(coloniaSeleccionada);
                    System.out.println("Colonia " + coloniaSeleccionada.getNombre() + " dada de baja exitosamente.");
                } else {
                    System.out.println("No se encontró la Colonia con el ID: " + idColonia);
                }

            }

        }

        public void VerRegistros()
        {
            if (estados.isEmpty())
            {
                System.out.println("No hay estados registrados.");
            } else {
                System.out.println("La lista de Estados es:");
                for (Estado estado : estados) {
                    System.out.println(estado.getId() + ". " + estado.getNombre());
                }
                if (municipios.isEmpty())
                {
                    System.out.println("No hay municipios registrados. ");
                } else
                {
                    System.out.println("La lista de Municipios es: ");
                    for (Municipio municipio : municipios)
                    {
                        System.out.println(municipio.getId() + ". " + municipio.getNombre());
                    }
                }

                if (colonias.isEmpty())
                {
                    System.out.println("No hay colonias registradas. ");
                } else
                {
                    System.out.println("La lista de Colonias es: ");
                    for (Colonia colonia : colonias)
                    {
                        System.out.println(colonia.getId() + ". " + colonia.getNombre());
                    }
                }
            }
        }

        public void ActualizarRegistro() {
            if (estados.isEmpty()) {
                System.out.println("No hay ningún estado registrado.");
            } else {
                System.out.println("Seleccione el estado que desea actualizar: ");
                for (Estado estado : estados) {
                    System.out.println(estado.getId() + ". " + estado.getNombre());
                }

                int idEstado = ReadUtil.readInt();
                Estado estadoSeleccionado = buscarEstadoPorId(idEstado);

                if (estadoSeleccionado != null) {
                    System.out.println("Estado seleccionado: " + estadoSeleccionado.getNombre());
                    System.out.println("Ingrese el nuevo nombre para el estado: ");
                    String nuevoNombre = ReadUtil.read();
                    estadoSeleccionado.setNombre(nuevoNombre);
                    System.out.println("Estado actualizado exitosamente.");
                } else {
                    System.out.println("No se encontró el estado con el ID: " + idEstado);
                }
            }

            if (municipios.isEmpty()) {
                System.out.println("No hay ningún municipio registrado.");
            } else {
                System.out.println("Seleccione el municipio que desea actualizar: ");
                for (Municipio municipio : municipios) {
                    System.out.println(municipio.getId() + ". " + municipio.getNombre());
                }

                int idMunicipio = ReadUtil.readInt();
                Municipio municipioSeleccionado = buscarMunicipioPorId(idMunicipio);

                if (municipioSeleccionado != null) {
                    System.out.println("Municipio seleccionado: " + municipioSeleccionado.getNombre());
                    System.out.println("Ingrese el nuevo nombre para el municipio: ");
                    String nuevoNombre = ReadUtil.read();
                    municipioSeleccionado.setNombre(nuevoNombre);
                    System.out.println("Municipio actualizado exitosamente.");
                } else {
                    System.out.println("No se encontró el municipio con el ID: " + idMunicipio);
                }
            }

            if (colonias.isEmpty()) {
                System.out.println("No hay ninguna colonia registrada.");
            } else {
                System.out.println("Seleccione la colonia que desea actualizar: ");
                for (Colonia colonia : colonias) {
                    System.out.println(colonia.getId() + ". " + colonia.getNombre());
                }

                int idColonia = ReadUtil.readInt();
                Colonia coloniaSeleccionado = buscarColoniaPorId(idColonia);

                if (coloniaSeleccionado != null) {
                    System.out.println("Colonia seleccionada: " + coloniaSeleccionado.getNombre());
                    System.out.println("Ingrese el nuevo nombre para la colonia: ");
                    String nuevoNombre = ReadUtil.read();
                    coloniaSeleccionado.setNombre(nuevoNombre);
                    System.out.println("Ingrese el nuevo CP: ");
                    String nuevoCP = ReadUtil.read();
                    coloniaSeleccionado.setCp(nuevoCP);
                    System.out.println("Colonia actualizada exitosamente.");
                } else {
                    System.out.println("No se encontró la colonia con el ID: " + idColonia);
                }
            }
        }


        public Estado buscarEstadoPorId(int idEstado) {
            for (Estado estado : estados) {
                if (estado.getId() == idEstado) {
                    return estado;
                }
            }
            return null;
        }

        public Municipio buscarMunicipioPorId(int idMunicipio) {
            for (Municipio municipio : municipios) {
                if (municipio.getId() == idMunicipio) {
                    return municipio;
                }
            }
            return null;
        }

        public Colonia buscarColoniaPorId(int idColonia) {
            for (Colonia colonia : colonias) {
                if (colonia.getId() == idColonia) {
                    return colonia;
                }
            }
            return null;
        }

    }

