/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Logica.Carta;
import Logica.Mano;

/**
 *
 * @author 44777219
 */
public class TestMano {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Carta e1 = new Carta(11, Carta.PALO_ESPADA);
        Carta e2 = new Carta(10, Carta.PALO_BASTO);
        Carta e3 = new Carta(7, Carta.PALO_ORO);
        Carta muestra = new Carta(2, Carta.PALO_ORO);

        System.out.println("MUESTRA: " + muestra.toString());
        Mano mano = new Mano(e1, e2, e3);
        for (int i = 0; i < Mano.MAX_CARTAS_MANO; i++) {
            System.out.println(mano.getCartas_mano()[i].toString() + ". Es pieza? " + mano.getCartas_mano()[i].es_pieza(muestra));
        }
        System.out.println("\n\n*******************************************");
//        System.out.println("FLOR 3 MISMO PALO: "+mano.flor_3_mismo_palo());
//        System.out.println("FLOR 3 MISMO VALOR: "+mano.flor_3_mismo_valor());
//        System.out.println("FLOR 2 PIEZAS AL MENOS: "+mano.flor_2_piezas(muestra));
//        System.out.println("2 MISMO PALO EXCLUSIVO QUE NO SON PIEZAS: "+mano._2_mismo_palo_exclusivo_no_piezas(muestra));
//        System.out.println("1 PIEZA EXCLUSIVO: "+mano._1_pieza_exlusivo(muestra));
//        System.out.println("FLOR 1 PIEZA Y 2 MISMO PALO: "+mano.flor_1_pieza_2_mismo_palo(muestra));

        System.out.println("TIENE FLOR?? -> " + mano.tiene_flor(muestra));
        if (mano.tiene_flor(muestra)) {
            System.out.println("PUNTAJE FLOR: " + mano.puntaje_flor(muestra));
        }

        System.out.println("PUNTAJE ENVIDO -> " + mano.puntaje_envido(muestra));

    }

}
