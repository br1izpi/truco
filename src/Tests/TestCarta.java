/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Logica.Carta;
import Logica.Mazo;
import java.util.ArrayList;

/**
 *
 * @author 44777219
 */
public class TestCarta {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Carta atacante = new Carta(6, Carta.PALO_ORO);
        Carta atacada = new Carta(6, Carta.PALO_ESPADA);
        Carta muestra = new Carta(3, Carta.PALO_ORO);
        System.out.println("RESULTADO ATAQUE: "+atacante.ataque(atacada, muestra));
//        Mazo mazo = new Mazo();
//        mazo.generar_mazo_completo();
//        Mazo mazo_2 = new Mazo();
//        mazo_2.generar_mazo_completo();
//        mazo.getCartas().forEach((i) -> {
//            System.out.println(i.toString() + " ATACA A " + mazo_2.getCartas().get(i.getValor_carta() + 3).toString() + " -> RESULTADO: " + i.ataque(mazo_2.getCartas().get(i.getValor_carta() + 3), muestra));
//        });
    }

}
