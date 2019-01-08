package Logica;

import Grafica.MensajeEnvido;
import Grafica.VentanaPartida;
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

    public boolean aceptar_envido() {
        Fachada_Grafica_Logica fgl = Fachada_Grafica_Logica.getSingletonInstancia();
        return posible_envido() || fgl.partida.getPuntaje_humano() == 39;
        //RETORNA true SI LA INTELIGENCIA TIENE PUNTAJE COMO PARA JUGAR EL ENVIDO
        //O AL JUGADOR HUMANO LE FALTA UN PUNTO PARA GANAR.

    }

    public void mostrar_mensaje(String msj) {
        Fachada_Grafica_Logica fgl = Fachada_Grafica_Logica.getSingletonInstancia();
        fgl.vp.cargar_mensaje_ia(msj);
    }

    public void jugar_demente() throws InterruptedException {
        /*ESTE MÉTODO HACE QUE LA INTELIGENCIA JUEGE SIEMPRE LA CARTA MÁS ALTA*/
        Fachada_Grafica_Logica fgl = Fachada_Grafica_Logica.getSingletonInstancia();
        if (fgl.partida.getMano_ia().tiene_flor(fgl.partida.getMuestra())) {
            fgl.vp.cargar_mensaje_ia("Tengo Flor!");
            fgl.partida.validar_flor_ia();//comprueba que el humano no tenga o que tenga menos puntos de flor(actualiza pts)
        }
        //despues juega la mayor carta que tenga en la mano
        jugar_mayor_carta();
        if (fgl.partida.getCarta_en_juego_humano().getValor_carta() > 0) {
            //SI EL HUMANO YA JUGÓ SU CARTA
            if (fgl.partida.getCarta_en_juego_humano().valor_truco(fgl.partida.getMuestra())
                    < fgl.partida.getCarta_en_juego_ia().valor_truco(fgl.partida.getMuestra())) {
                mostrar_mensaje("Mía!");
                fgl.partida.setParcial_ia(fgl.partida.getParcial_ia() + 1);
                if (fgl.partida.getParcial_ia() == 1) {
                    //esta sería la segunda que gana por lo tanto se lleva un punto por ahora
                    Thread.sleep(500);
                    fgl.partida.setPuntaje_ia(fgl.partida.getPuntaje_ia() + 1);
                }

            } else {
                mostrar_mensaje("Tuya...");
                fgl.partida.setParcial_hum(fgl.partida.getParcial_hum()+ 1);
                if (fgl.partida.getParcial_hum() == 1) {
                    //esta sería la segunda que gana por lo tanto se lleva un punto por ahora
                    Thread.sleep(500);
                    fgl.partida.setPuntaje_humano(fgl.partida.getPuntaje_humano()+ 1);
                }
            }
        }

    }

    public void jugar_mayor_carta() {
        /*ESTE METODO EXTRAE LA MAYOR CARTA DE LA MANO Y LA CARGA A LA CARTA ACTIVA DE LA IA EN LA MESA*/
        Fachada_Grafica_Logica fgl = Fachada_Grafica_Logica.getSingletonInstancia();
        int pos = retorna_pos_mayor();
        System.out.println("POS: "+pos);
        fgl.partida.setCarta_en_juego_ia(fgl.partida.getMano_ia().getCartas_mano()[pos]);
        fgl.partida.getMano_ia().getCartas_mano()[pos] = new Carta();
        fgl.vp.cargar_lbl("", pos+5);//en blanco queda la que sacamos de la mano_ia
        fgl.vp.cargar_lbl(fgl.partida.getCarta_en_juego_ia().toString(), VentanaPartida.LBL_ACTIVA_IA);
    }
}
