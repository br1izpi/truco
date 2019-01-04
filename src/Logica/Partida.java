package Logica;

import java.util.Scanner;

public class Partida {

    public static int MAX_PUNTAJE = 40;

    public static int TURNO_COMIENZA_HUMANO = 1;
    private Jugador jugador_humano;
    private Inteligencia jugador_inteligencia;
    private Mazo mazo;
    private int puntaje_humano;
    private int puntaje_ia;
    private Mano mano_humano;
    private Mano mano_ia;
    private Carta carta_en_juego_humano;
    private Carta carta_en_juego_ia;
    private Carta muestra;
    private boolean turno_jug_humano;
    private boolean turno_jug_ia;
    private boolean mazo_barajado;
    private boolean cartas_repartidas;

    public Partida(Jugador jugador_humano, int turno) {
        this.jugador_humano = jugador_humano;
        this.jugador_inteligencia = new Inteligencia();
        this.mazo = new Mazo();
        this.mazo.generar_mazo_completo();
        this.puntaje_humano = 0;
        this.puntaje_ia = 0;
        this.mano_humano = new Mano();
        this.mano_ia = new Mano();
        this.carta_en_juego_humano = new Carta();
        this.carta_en_juego_ia = new Carta();
        this.muestra = new Carta();
        if (turno == TURNO_COMIENZA_HUMANO) {
            this.turno_jug_humano = true;
            this.turno_jug_ia = false;
        } else {
            this.turno_jug_humano = false;
            this.turno_jug_ia = true;
        }
        this.mazo_barajado = false;
        this.cartas_repartidas = false;
    }

   

    public Jugador getJugador_humano() {
        return jugador_humano;
    }

    public void setJugador_humano(Jugador jugador_humano) {
        this.jugador_humano = jugador_humano;
    }

    public Inteligencia getJugador_inteligencia() {
        return jugador_inteligencia;
    }

    public void setJugador_inteligencia(Inteligencia jugador_inteligencia) {
        this.jugador_inteligencia = jugador_inteligencia;
    }

    public Carta getCarta_en_juego_humano() {
        return carta_en_juego_humano;
    }

    public void setCarta_en_juego_humano(Carta carta_en_juego_humano) {
        this.carta_en_juego_humano = carta_en_juego_humano;
    }

    public Carta getCarta_en_juego_ia() {
        return carta_en_juego_ia;
    }

    public void setCarta_en_juego_ia(Carta carta_en_juego_ia) {
        this.carta_en_juego_ia = carta_en_juego_ia;
    }

    public Mazo getMazo() {
        return mazo;
    }

    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
    }

    public int getPuntaje_humano() {
        return puntaje_humano;
    }

    public void setPuntaje_humano(int puntaje_humano) {
        this.puntaje_humano = puntaje_humano;
    }

    public int getPuntaje_ia() {
        return puntaje_ia;
    }

    public void setPuntaje_ia(int puntaje_ia) {
        this.puntaje_ia = puntaje_ia;
    }

    public Mano getMano_humano() {
        return mano_humano;
    }

    public void setMano_humano(Mano mano_humano) {
        this.mano_humano = mano_humano;
    }

    public Mano getMano_ia() {
        return mano_ia;
    }

    public void setMano_ia(Mano mano_ia) {
        this.mano_ia = mano_ia;
    }

    public Carta getMuestra() {
        return muestra;
    }

    public void setMuestra(Carta muestra) {
        this.muestra = muestra;
    }

    public boolean isTurno_jug_humano() {
        return turno_jug_humano;
    }

    public void setTurno_jug_humano(boolean turno_jug_humano) {
        this.turno_jug_humano = turno_jug_humano;
    }

    public boolean isTurno_jug_ia() {
        return turno_jug_ia;
    }

    public void setTurno_jug_ia(boolean turno_jug_ia) {
        this.turno_jug_ia = turno_jug_ia;
    }

    public boolean isMazo_barajado() {
        return mazo_barajado;
    }

    public void setMazo_barajado(boolean mazo_barajado) {
        this.mazo_barajado = mazo_barajado;
    }

    public boolean isCartas_repartidas() {
        return cartas_repartidas;
    }

    public void setCartas_repartidas(boolean cartas_repartidas) {
        this.cartas_repartidas = cartas_repartidas;
    }

    public void barajar_mazo() {
        this.getMazo().barajar();
        this.mazo_barajado = true;
    }

    public void repartir_cartas(boolean reparte_humano) {
        if (mazo_barajado) {
            if (reparte_humano) {
                for (int i = 0; i < Mano.MAX_CARTAS_MANO; i++) {
                    this.mano_ia.getCartas_mano()[i] = this.mazo.extraer_carta();
                    this.mano_humano.getCartas_mano()[i] = this.mazo.extraer_carta();
                }
                this.muestra = this.mazo.extraer_carta();
            } else if (!reparte_humano) {
                for (int i = 0; i < Mano.MAX_CARTAS_MANO; i++) {
                    this.mano_ia.getCartas_mano()[i] = this.mazo.extraer_carta();
                    this.mano_humano.getCartas_mano()[i] = this.mazo.extraer_carta();
                }
                this.muestra = this.mazo.extraer_carta();
            }
        } else {
        }
    }

    public void muestra_cartas_mesa() {
        System.out.println("*** CARTAS DEL HUMANO ***");
        for (Carta i : mano_humano.getCartas_mano()) {
            System.out.println(i.toString());
        }
        System.out.println("*** CARTAS DE LA IA ***");
        for (Carta i : mano_ia.getCartas_mano()) {
            System.out.println(i.toString());
        }
        System.out.println("*** MUESTRA ***");
        System.out.println(this.muestra.toString());
    }
    public void muestra_cartas_humano_muestra() {
        System.out.println("*** CARTAS DEL HUMANO ***");
        for (Carta i : mano_humano.getCartas_mano()) {
            System.out.println(i.toString());
        }
        System.out.println("*** MUESTRA ***");
        System.out.println(this.muestra.toString());
    }

    
}
