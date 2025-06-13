package org.DiegoHvdz.vista.consola;
import org.DiegoHvdz.sql.GenericSql;
import org.DiegoHvdz.model.Catalogo;
import org.DiegoHvdz.util.ReadUtil;
import org.DiegoHvdz.vista.LeerAcciones;
import org.DiegoHvdz.vista.Menu;

import java.util.List;

public abstract class GestorCatalogos<T extends Catalogo> extends LeerAcciones
{
    protected List<T> list;
    protected T t;
    protected boolean flag2;
    protected GenericSql<T> genericSql;

    public GestorCatalogos(GenericSql<T> genericSql)
    {
        this.genericSql = genericSql;
    }

    public abstract T newT();
    public abstract boolean processNewT(T t);
    public abstract boolean processEditT(T t);

    public void print()
    {
        List<T> list = genericSql.findAll();
        if(list.isEmpty())
        {
            System.out.println("\n\t> [Info] No hay elementos registrados.");
        }
        else
        {
            System.out.println("\n\t> Lista de elementos:");
            list.forEach(System.out::println);
        }
    }

    public void add()
    {
        t = newT();
        if(processNewT(t))
        {
            System.out.println("\n\t> [Éxito] Elemento añadido correctamente.");
        }
    }

    public void edit()
    {
        List<T> list = genericSql.findAll();
        if(list.isEmpty())
        {
            System.out.println("\n\t> [Aviso] No hay elementos para editar.");
            return;
        }

        flag2 = true;
        while(flag2)
        {
            System.out.println("\n\t> Lista de elementos:");
            list.forEach(System.out::println);
            System.out.print("\n> Ingresa el ID del elemento a editar: ");

            t = list.stream()
                    .filter(e -> e.getId().equals(ReadUtil.readInt()))
                    .findFirst()
                    .orElse(null);

            if(t == null)
            {
                System.out.println("\n\t> [Error] No se encontró el elemento.");
                System.out.print("> ¿Deseas intentarlo de nuevo? (s/n): ");
                String respuesta = ReadUtil.read();
                flag2 = respuesta.equalsIgnoreCase("s");
            }
            else
            {
                if(processEditT(t))
                {
                    System.out.println("\n\t> [Éxito] Elemento editado correctamente.");
                }
                flag2 = false;
            }
        }
    }

    public void remove()
    {
        List<T> list = genericSql.findAll();
        if(list.isEmpty())
        {
            System.out.println("\n\t> [Aviso] No hay elementos para eliminar.");
            return;
        }

        flag2 = true;
        while(flag2)
        {
            System.out.println("\n\t> Lista de elementos:");
            list.forEach(System.out::println);
            System.out.print("\n> Ingresa el ID del elemento a eliminar: ");

            t = list.stream()
                    .filter(e -> e.getId().equals(ReadUtil.readInt()))
                    .findFirst()
                    .orElse(null);

            if(t == null)
            {
                System.out.println("\n\t> [Error] No se encontró el elemento.");
                System.out.print("> ¿Deseas intentarlo de nuevo? (s/n): ");
                String respuesta = ReadUtil.read();
                flag2 = respuesta.equalsIgnoreCase("s");
            }
            else
            {
                if(genericSql.delete(t))
                {
                    System.out.println("\n\t> [Éxito] Elemento eliminado correctamente.");
                }
                flag2 = false;
            }
        }
    }

    public void findById()
    {
        System.out.print("\n> Ingresa un ID para buscar: ");
        t = genericSql.findById(ReadUtil.readInt());

        if(t != null)
        {
            System.out.println("\n\t> Resultado encontrado:");
            System.out.println(t);
        }
        else
        {
            System.out.println("\n\t> [Info] No existe un elemento con ese ID.");
        }
    }

    @Override
    public void despliegaMenu()
    {
        System.out.println("\n\t===============================");
        System.out.println("\t   :: Gestión de Catálogos ::");
        System.out.println("\t===============================\n");
        System.out.println("\t1. Agregar");
        System.out.println("\t2. Eliminar");
        System.out.println("\t3. Editar");
        System.out.println("\t4. Mostrar elementos");
        System.out.println("\t5. Buscar por ID");
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
        switch (opcion)
        {
            case 1: add(); break;
            case 2: remove(); break;
            case 3: edit(); break;
            case 4: print(); break;
            case 5: findById(); break;
            default: Menu.opcionInvalida();
        }
    }
}
