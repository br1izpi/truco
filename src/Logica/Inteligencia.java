package Logica;

import Grafica.MensajeEnvido;
import java.util.Random;
import java.util.Scanner;

public class Inteligencia {

    public Inteligencia() {
    }

    public int retorna_pos_menor() {
        Fachada_Grafica_Logica fgl = Fachada_Grafica_Logica.getSingletonInstancia();
        int pos_menor = 0;
        for (int i = 0; i < Mano.MAX_CARTAS_MANO; i++) {
            if (fgl.partida.getMano_ia().getCartas_mano()[i].valor_truco(fgl.partida.getMuestra()) < fgl.partida.getMano_ia().getCartas_mano()[pos_menor].valor_truco(fgl.partida.getMuestra())) {
                pos_menor = i;
            }
        }
        return pos_menor;
    }

    public int retorna_pos_mayor() {
        Fachada_Grafica_Logica fgl = Fachada_Grafica_Logica.getSingletonInstancia();
        int pos_mayor = 0;
        for (int i = 0; i < Mano.MAX_CARTAS_MANO; i++) {
            if (fgl.partida.getMano_ia().getCartas_mano()[i].valor_truco(fgl.partida.getMuestra()) > fgl.partida.getMano_ia().getCartas_mano()[pos_mayor].valor_truco(fgl.partida.getMuestra())) {
                pos_mayor = i;
            }
        }
        return pos_mayor;
    }

    public int retorna_pos_menor_ganando(Carta en_juego_humano) {
        int pos_menor = 0;
        int dif_menor = 99;
        Fachada_Grafica_Logica fgl = Fachada_Grafica_Logica.getSingletonInstancia();
        for (int i = 0; i < Mano.MAX_CARTAS_MANO; i++) {
            int dif = fgl.partida.getMano_ia().getCartas_mano()[i].ataque(en_juego_humano, fgl.partida.getMuestra());
            if (dif > 0 && dif < dif_menor) {
                dif_menor = dif;
                pos_menor = i;
            }
        }
        return pos_menor;
    }

    public boolean gana_con_alguna(Carta en_juego_humano) {
        boolean gana = false;
        Fachada_Grafica_Logica fgl = Fachada_Grafica_Logica.getSingletonInstancia();
        for (int i = 0; i < Mano.MAX_CARTAS_MANO; i++) {
            if (fgl.partida.getMano_ia().getCartas_mano()[i].valor_truco(fgl.partida.getMuestra()) > en_juego_humano.valor_truco(fgl.partida.getMuestra())) {
                gana = true;
            }
        }
        return gana;
    }

    public boolean posible_envido() {
        /*INTELIGENCIA TOCA ENVIDO EN LAS SIGUIENTES CIRCUNSTANCIAS:
        1) SI TIENE MÁS DE 30 PUNTOS
        2) SI TIENE MÁS DE 23 PUNTOS Y SALE UN BOOLEANO ALEATORIO true*/
        boolean envido = false;
        Fachada_Grafica_Logica fgl = Fachada_Grafica_Logica.getSingletonInstancia();
        int puntaje = fgl.partida.getMano_ia().puntaje_envido(fgl.partida.getMuestra());
        Random aleatorio = new Random();
        if (puntaje > 30) {
            envido = true;
        } else if (puntaje > 23 && aleatorio.nextBoolean()) {
            envido = true;
        }
        return envido;
    }

    public void tocar_envido() {
        Fachada_Grafica_Logica fgl = Fachada_Grafica_Logica.getSingletonInstancia();
        Scanner teclado = new Scanner(System.in);
        if (this.posible_envido()) {
//            System.out.println("ENVIDO?");
//            System.out.println("1- QUIERO   -   2- NO QUIERO");
//            int quiero = teclado.nextInt();
            MensajeEnvido me = new MensajeEnvido(null, true);
            me.setVisible(true);
            if (me.isEnvido()) {
                System.out.println("QUIERO");
                if (fgl.partida.getMano_ia().puntaje_envido(fgl.partida.getMuestra()) > fgl.partida.getMano_humano().puntaje_envido(fgl.partida.getMuestra())) {
                    fgl.partida.setPuntaje_ia(fgl.partida.getPuntaje_ia() + 2);
                    System.out.println("GANÓ LA INTELIGENCIA CON " + fgl.partida.getMano_ia().puntaje_envido(fgl.partida.getMuestra()));
                } else {
                    fgl.partida.setPuntaje_humano(fgl.partida.getPuntaje_humano() + 2);
                    System.out.println("GANASTE. INTELIGENCIA TIENE " + fgl.partida.getMano_ia().puntaje_envido(fgl.partida.getMuestra()) + " PUNTOS.");
                }
            } else {
                System.out.println("NO QUIERO");
                fgl.partida.setPuntaje_ia(fgl.partida.getPuntaje_ia() + 1);
            }
        }
    }

    public boolean aceptar_envido(){
        Fachada_Grafica_Logica fgl = Fachada_Grafica_Logica.getSingletonInstancia();
        return posible_envido() || fgl.partida.getPuntaje_humano() == 39;
        //RETORNA true SI LA INTELIGENCIA TIENE PUNTAJE COMO PARA JUGAR EL ENVIDO
        //O AL JUGADOR HUMANO LE FALTA UN PUNTO PARA GANAR.
    
    }
    
    

}