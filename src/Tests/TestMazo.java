/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Logica.Mazo;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author 44777219
 */
public class TestMazo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Mazo m = new Mazo();
        m.generar_mazo_completo();
        m.imprimir_mazo();
        System.out.println(m.cant_cartas());
        System.out.println("****************");
        m.barajar();
        m.imprimir_mazo();
        System.out.println(m.cant_cartas());
      
    }

}
