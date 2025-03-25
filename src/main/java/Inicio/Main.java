package Inicio;

import Util.ReadUtil;
import Vista.Ejecutable;
import Vista.Menu;

public class Main {
    public static void main(String[] args) {
        boolean flag = true;
        int opcion = 0;
        Ejecutable ejecutable = null;

        Menu.principal();
        Menu.seleccionaOpcion();
        opcion = ReadUtil.readInt();
    }
}