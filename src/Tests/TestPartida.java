/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Logica.Carta;
import Logica.Fachada_Grafica_Logica;
import Logica.Inteligencia;
import Logica.Jugador;
import Logica.Mano;
import Logica.Mazo;
import Logica.Partida;
import java.awt.BorderLayout;
import java.util.Scanner;

/**
 *
 * @author 44777219
 */
public class TestPartida {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Inteligencia ia = new Inteligencia();
        Fachada_Grafica_Logica fgl = Fachada_Grafica_Logica.getSingletonInstancia();
        fgl.partida = new Partida(new Jugador("Bruno"), Partida.TURNO_COMIENZA_HUMANO);
        int cant_flores_humano = 0;
        int cant_flores_ia = 0;
        float cantidad_partidas = 100;
        int i = 0;
        Scanner teclado = new Scanner(System.in);
//        for (int i = 0; i < cantidad_partidas; i++) {
        while (fgl.partida.getPuntaje_humano() < 40 && fgl.partida.getPuntaje_ia() < 40) {
            System.out.println("******  PARTIDA NÚMERO " + (i + 1) + "  ******");
            Mazo mazo = new Mazo();
            mazo.generar_mazo_completo();
            fgl.partida.setMazo(mazo);
            fgl.partida.barajar_mazo();
//        Mano mano_ia = new Mano(new Carta(1, Carta.PALO_ORO), new Carta(4, Carta.PALO_ORO), new Carta(5, Carta.PALO_ORO));
//        Mano mano_hum = new Mano(new Carta(2, Carta.PALO_ORO), new Carta(4, Carta.PALO_ESPADA), new Carta(5, Carta.PALO_ESPADA));
//        fgl.partida.setMuestra(new Carta(3, Carta.PALO_BASTO));
//        fgl.partida.setMano_ia(mano_ia);
//        fgl.partida.setMano_humano(mano_hum);
            fgl.partida.repartir_cartas(true);
//            if (p.getMano_humano().tiene_flor(p.getMuestra()) || p.getMano_ia().tiene_flor(p.getMuestra())) {
//            fgl.partida.muestra_cartas_mesa();
            fgl.partida.muestra_cartas_humano_muestra();

//                if (p.getMano_humano().tiene_flor(p.getMuestra())) {
//                    cant_flores_humano++;
//                    System.out.println("Puntaje Flor Humano: " + p.getMano_humano().puntaje_flor(p.getMuestra()));
//                }
//                if (p.getMano_ia().tiene_flor(p.getMuestra())) {
//                    cant_flores_ia++;
//                    System.out.println("Puntaje Flor IA: " + p.getMano_ia().puntaje_flor(p.getMuestra()));
//                }
//                System.out.println("*************************************");
//            }
//        System.out.println("MAYOR POS IA: " + ia.retorna_pos_mayor());
//        System.out.println("MENOR POS IA: " + ia.retorna_pos_menor());
//        fgl.partida.setCarta_en_juego_humano(fgl.partida.getMano_humano().getCartas_mano()[0]);
//        System.out.println("CARTA EN JUEGO HUMANO: " + fgl.partida.getCarta_en_juego_humano().toString());
//        if (ia.gana_con_alguna(fgl.partida.getCarta_en_juego_humano())) {
//            System.out.println("IA GANA CON MENOR CARTA EN POSICIÓN: " + ia.retorna_pos_menor_ganando(fgl.partida.getCarta_en_juego_humano()));
//            System.out.println("VALOR TRUCO CARTA HUMANO: " + fgl.partida.getCarta_en_juego_humano().valor_truco(fgl.partida.getMuestra()));
//            System.out.println("VALOR TRUCO CARTA IA: " + fgl.partida.getMano_ia().getCartas_mano()[ia.retorna_pos_menor_ganando(fgl.partida.getCarta_en_juego_humano())].valor_truco(fgl.partida.getMuestra()));
//        } else {
//            System.out.println("IA NO TIENE CARTA PARA GANAR");
//        }
            if (fgl.partida.getMano_humano().tiene_flor(fgl.partida.getMuestra())) {
                System.out.println("HUMANO TIENE FLOR. PUNTAJE FLOR: " + fgl.partida.getMano_humano().puntaje_flor(fgl.partida.getMuestra()) + " PTS.");
            } 
//            else {
//                System.out.println("HUMANO NO TIENE FLOR. PUNTAJE ENVIDO: " + fgl.partida.getMano_humano().puntaje_envido(fgl.partida.getMuestra()) + " PTS.");
//            }
            if (fgl.partida.getMano_ia().tiene_flor(fgl.partida.getMuestra())) {
                System.out.println("INTELIGENCIA TIENE FLOR. PUNTAJE FLOR: " + fgl.partida.getMano_ia().puntaje_flor(fgl.partida.getMuestra()) + " PTS.");
            } 
//            else {
//                System.out.println("INTELIGENCIA NO TIENE FLOR. PUNTAJE ENVIDO: " + fgl.partida.getMano_ia().puntaje_envido(fgl.partida.getMuestra()) + " PTS.");
//            }
            if (fgl.partida.getMano_humano().tiene_flor(fgl.partida.getMuestra()) && !fgl.partida.getMano_ia().tiene_flor(fgl.partida.getMuestra())) {
                fgl.partida.setPuntaje_humano(fgl.partida.getPuntaje_humano() + 3);
            } else if (!fgl.partida.getMano_humano().tiene_flor(fgl.partida.getMuestra()) && fgl.partida.getMano_ia().tiene_flor(fgl.partida.getMuestra())) {
                fgl.partida.setPuntaje_ia(fgl.partida.getPuntaje_ia() + 3);
            } else if (fgl.partida.getMano_humano().tiene_flor(fgl.partida.getMuestra()) && fgl.partida.getMano_ia().tiene_flor(fgl.partida.getMuestra())) {
                //SI LOS DOS TIENEN FLOR
                if (fgl.partida.getMano_humano().puntaje_flor(fgl.partida.getMuestra()) > fgl.partida.getMano_ia().puntaje_flor(fgl.partida.getMuestra())) {
                    fgl.partida.setPuntaje_humano(fgl.partida.getPuntaje_humano() + 3);
                } else {
                    fgl.partida.setPuntaje_ia(fgl.partida.getPuntaje_ia() + 3);
                }
            } else {
                /*SI NINGUNO DE LOS DOS TIENEN FLOR DECISIÓN ENVIDO*/
//                if (fgl.partida.getMano_humano().puntaje_envido(fgl.partida.getMuestra()) > fgl.partida.getMano_ia().puntaje_envido(fgl.partida.getMuestra())) {
//                    fgl.partida.setPuntaje_humano(fgl.partida.getPuntaje_humano() + 2);
//                } else {
//                    fgl.partida.setPuntaje_ia(fgl.partida.getPuntaje_ia() + 2);
//                }
                fgl.partida.getJugador_inteligencia().tocar_envido();

            }
            i++;
        }
        System.out.println("PUNTAJE HUMANO: " + fgl.partida.getPuntaje_humano() + " pts.");
        System.out.println("PUNTAJE INTELIGENCIA: " + fgl.partida.getPuntaje_ia() + " pts.");
//            float porc_flores_hum = cant_flores_humano * ((100 / cantidad_partidas));
//            float porc_flores_ia = cant_flores_ia * ((100 / cantidad_partidas));
//            System.out.println("% FLORES HUMANO-> " + porc_flores_hum + "%");
//            System.out.println("% FLORES IA-> " + porc_flores_ia + "%");
        if (fgl.partida.getPuntaje_humano() > fgl.partida.getPuntaje_ia()) {
            System.out.println("FELICITACIONES " + fgl.partida.getJugador_humano().getNombre().toUpperCase() + ", HAS GANADO LA PARTIDA!!");
        } else {
            System.out.println("OH. HAS PERDIDO");
        }
    }
}
