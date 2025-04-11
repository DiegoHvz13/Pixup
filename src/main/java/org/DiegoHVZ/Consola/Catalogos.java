package org.DiegoHVZ.Consola;

import org.DiegoHVZ.jdbc.GenericJdbc;
import org.DiegoHVZ.jdbc.impl.EstadoJdbcImpl;
import org.DiegoHVZ.model.Catalogo;
import org.DiegoHVZ.model.Estado;
import org.DiegoHVZ.util.ReadUtil;
import org.DiegoHVZ.ventana.LecturaAccion;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

    public abstract class Catalogos<T extends Catalogo> extends LecturaAccion
    {
        protected List<T> list;
        protected T t;
        protected boolean flag2;
        protected File file;
        protected GenericJdbc<T> jdbc;


        public Catalogos(GenericJdbc<T> jdbc)

        {
            this.jdbc = jdbc;
            list = new ArrayList<>( );
        }

        public boolean isListEmpty()
        {
            return list.isEmpty();
        }

        public void print( )
        {
            List<T> items = jdbc.findAll();
            if (items.isEmpty()) {
                System.out.println("> No hay elementos.");
            } else {
                items.forEach(System.out::println);
            }
        }

        public abstract T newT( );
        public abstract boolean processNewT( T t );
        public abstract void processEditT( T t );

        public void add( )
        {
            t = newT();
            if (processNewT(t)) {
                if (jdbc.save(t)) {
                    System.out.println("Objeto guardado exitosamente en la base de datos.");
                } else {
                    System.out.println("Error al guardar el objeto en la base de datos.");
                }
            }
        }

        public void edit( )
        {
            List<T> items = jdbc.findAll();
            if (items.isEmpty()) {
                System.out.println("> No hay elementos.");
                return;
            }

            flag2 = true;
            while (flag2) {
                System.out.println("> Ingrese el ID del elemento a editar: ");
                for (T item : items) {
                    System.out.println(item);
                }

                int id = ReadUtil.readInt();
                T item = jdbc.findById(id);

                if (item == null) {
                    System.out.println("> ID incorrecto, inténtelo nuevamente.");
                } else {
                    processEditT(item);
                    boolean resultado = jdbc.update(item);
                    if (resultado) {
                        System.out.println("> Elemento modificado y guardado en la base de datos.");
                    } else {
                        System.out.println("> Error al guardar el elemento modificado.");
                    }
                    flag2 = false;
                }
            }
        }

        public void remove( )
        {
            List<T> items = jdbc.findAll();
            if (items.isEmpty()) {
                System.out.println("> No hay elementos.");
                return;
            }

            flag2 = true;
            while ( flag2 )
            {
                System.out.println("> Ingrese el ID del elemento a editar: ");
                for (T item : items) {
                    System.out.println(item);
                }
                System.out.print("Su opcion >");

                int id = ReadUtil.readInt();
                T item = jdbc.findById(id);

                if (item == null) {
                    System.out.println("> ID incorrecto, inténtelo nuevamente.");
                }
                else
                {
                    jdbc.delete(item);
                    flag2 = false;
                    System.out.println( "Elemento eliminado." );
                }
            }
        }

        @Override
        public void procesaOpcion()
        {
            switch (opcion)
            {
                case 1:
                    add( );
                    break;
                case 2:
                    edit( );
                    break;
                case 3:
                    remove( );
                    break;
                case 4:
                    print( );
                    break;
            }
        }

        /*
        private void leerArchivo()
        {
            ObjectInputStream objectInputStream = null;
            FileInputStream fileInputStream = null;

            try {
                file = getFile( );
            /*
                Creación de los objetos de tipo ObjectInputStream y FileInputStream
                FileInputStream para primero traer datos de un archivo
                ObjectInputStream para leer los datos del fileInputStream

                fileInputStream = new FileInputStream( file );
                objectInputStream = new ObjectInputStream( fileInputStream );

                // Lee la lista almacenada en el archivo como un objeto y la guarda en list
                List<T> items = jdbc.findAll();
                list = (List<T>)objectInputStream.readObject( );

                objectInputStream.close( );
                fileInputStream.close( );

                System.out.println("> Archivo leído con éxito.");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public abstract File getFile( );

        private void guardarArchivo()
        {
        /*
        Creación de los objetos de tipo ObjectOutputStream y FileOutputStream
        FileOutputStream para mandar datos a un archivo
        ObjectOutputStream para primero mandar los datos al fileOutputStream

            ObjectOutputStream objectOutputStream = null;
            FileOutputStream fileOutputStream = null;

            try
            {List<T> items= jdbc.findAll();
                if(items.isEmpty())
                {
                    System.out.println("> No hay elementos para guardar.");
                }

                // Archivo file
                file = getFile(  );
                fileOutputStream = new FileOutputStream( file );
                objectOutputStream = new ObjectOutputStream( fileOutputStream );

                //Manda al objeto al objectOutputStream :3
                objectOutputStream.writeObject( items );

                // Cerrar las instancias de los objetos, como si fuera un scanner (porque un scanner también es un objeto xd)
                objectOutputStream.close();
                fileOutputStream.close();

                System.out.println("> Elementos guardados con éxito en el archivo.");
            } catch (FileNotFoundException e)
            {
                throw new RuntimeException(e);
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
        */

        @Override
        public void despliegaMenu()
        {
            System.out.println(":: Menú de Entidades ::");
            System.out.println("Seleccione una opción:");
            System.out.println("1.- Agregar");
            System.out.println("2.- Editar");
            System.out.println("3.- Borrar");
            System.out.println("4.- Imprimir");
            System.out.println("5.- Guardar en archivo");
            System.out.println("6.- Leer en archivo");
            System.out.println("7.- Salir");
            System.out.print("Su opcion > ");
        }

        @Override
        public int valorMinMenu( )
        {
            return 1;
        }

        @Override
        public int valorMaxMenu()
        {
            return 7;
        }



    }
