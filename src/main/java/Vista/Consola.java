package Vista;

public class Consola
{
    private static Consola consola;

    public Consola()
    {
    }

    public static Consola getInstance()
    {
       if(consola==null)
       {
           return new Consola();
       }
        return  consola;
    }

}
