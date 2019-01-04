package Logica;

import java.util.ArrayList;

public class Mano {

    public static final int MAX_CARTAS_MANO = 3;

    private Carta cartas_mano[];

    public Mano() {
        cartas_mano = new Carta[MAX_CARTAS_MANO];
    }

    public Mano(Carta c1, Carta c2, Carta c3) {
        cartas_mano = new Carta[MAX_CARTAS_MANO];
        cartas_mano[0] = c1;
        cartas_mano[1] = c2;
        cartas_mano[2] = c3;
    }

    public Carta[] getCartas_mano() {
        return cartas_mano;
    }

    public void setCartas_mano(Carta[] cartas_mano) {
        this.cartas_mano = cartas_mano;
    }

    public boolean _2_mismo_palo_exclusivo_no_piezas(Carta muestra) {
        int cant_basto = 0;
        int cant_copa = 0;
        int cant_espada = 0;
        int cant_oro = 0;
        for (int i = 0; i < MAX_CARTAS_MANO; i++) {
            if (!this.cartas_mano[i].es_pieza(muestra)) {
                switch(this.cartas_mano[i].getPalo_carta()) {
                    case 1:
                        cant_basto++;
                        break;
                    case 2:
                        cant_copa++;
                        break;
                    case 3:
                        cant_espada++;
                        break;
                    case 4:
                        cant_oro++;
                        break;
                }
            }
        }
        return cant_basto == 2 || cant_copa == 2 || cant_espada == 2 || cant_oro == 2;
    }

    public boolean _1_pieza_exlusivo(Carta muestra) {
        int cant = 0;
        for (int i = 0; i < MAX_CARTAS_MANO; i++) {
            if (this.cartas_mano[i].es_pieza(muestra)) {
                cant++;
            }
        }
        return cant == 1;
    }

    public boolean flor_3_mismo_palo() {
        return cartas_mano[0].getPalo_carta() == cartas_mano[1].getPalo_carta() && cartas_mano[1].getPalo_carta() == cartas_mano[2].getPalo_carta();
    }

    public boolean flor_3_mismo_valor() {
        return cartas_mano[0].getValor_carta() == cartas_mano[1].getValor_carta() && cartas_mano[1].getValor_carta() == cartas_mano[2].getValor_carta();
    }

    public boolean flor_2_piezas(Carta muestra) {
        int cuentaPiezas = 0;
        for (int i = 0; i < MAX_CARTAS_MANO; i++) {
            if (this.cartas_mano[i].es_pieza(muestra)) {
                cuentaPiezas++;
            }
        }
        return cuentaPiezas == 2;
    }

    public boolean flor_1_pieza_2_mismo_palo(Carta muestra) {
        return this._1_pieza_exlusivo(muestra) && this._2_mismo_palo_exclusivo_no_piezas(muestra);
    }

    public boolean tiene_flor(Carta muestra) {
        return flor_3_mismo_palo() || flor_3_mismo_valor() || flor_2_piezas(muestra) || flor_1_pieza_2_mismo_palo(muestra);
    }

    public int puntaje_flor(Carta muestra) {
        int puntaje = 0;
        int punt_cartas[] = new int[MAX_CARTAS_MANO];
        boolean piezas[] = new boolean[MAX_CARTAS_MANO];
        int cant_piezas = 0;
        int pos_mayor_pieza = 0;
        for (int i = 0; i < MAX_CARTAS_MANO; i++) {
            if (this.cartas_mano[i].es_pieza(muestra)) {
                punt_cartas[i] = this.cartas_mano[i].puntaje_pieza(muestra);
                piezas[i] = true;
                cant_piezas++;
            } else {
                punt_cartas[i] = this.cartas_mano[i].puntaje_carta_no_pieza();
                piezas[i] = false;
            }
        }
        if (cant_piezas > 0) {
            int valor_mayor_pieza = 0;
            for (int i = 0; i < MAX_CARTAS_MANO; i++) {
                if (piezas[i] && punt_cartas[i] > valor_mayor_pieza) {
                    valor_mayor_pieza = punt_cartas[i];
                    pos_mayor_pieza = i;
                }
            }
            for (int i = 0; i < MAX_CARTAS_MANO; i++) {
                if (cartas_mano[i].es_pieza(muestra) && i != pos_mayor_pieza) {
                    puntaje += (cartas_mano[i].puntaje_pieza(muestra) - 20);
                } else if (!cartas_mano[i].es_pieza(muestra)) {
                    puntaje += cartas_mano[i].puntaje_carta_no_pieza();
                } else if (cartas_mano[i].es_pieza(muestra) && i == pos_mayor_pieza) {
                    puntaje += cartas_mano[i].puntaje_pieza(muestra);
                }
            }
        } else {
            int punt_aux = 20;
            for (int i = 0; i < MAX_CARTAS_MANO; i++) {
                punt_aux += this.cartas_mano[i].puntaje_carta_no_pieza();
            }
            if (punt_aux < 47 && punt_aux > 0) {
                puntaje = punt_aux;
            } else {
                System.out.println("ERROR PUNTAJE (" + punt_aux + ") DE FLOR CON 3 CARTAS NO PIEZAS");
            }
        }
        return puntaje;
    }

    public int puntaje_envido(Carta muestra) {
        int puntaje = 0;
        boolean piezas[] = new boolean[MAX_CARTAS_MANO];
        int pos_pieza = -1;
        boolean _2_igual_palo = false;
        boolean igual_palo[] = new boolean[MAX_CARTAS_MANO];
        for (int i = 0; i < MAX_CARTAS_MANO; i++) {
            if (this.cartas_mano[i].es_pieza(muestra)) {
                pos_pieza = i;
            }
        }
        if (pos_pieza < 0) {
            igual_palo[0] = false;
            igual_palo[1] = false;
            igual_palo[2] = false;
            if (this.cartas_mano[0].getPalo_carta() == this.cartas_mano[1].getPalo_carta()) {
                _2_igual_palo = true;
                igual_palo[0] = true;
                igual_palo[1] = true;
            } else if (this.cartas_mano[1].getPalo_carta() == this.cartas_mano[2].getPalo_carta()) {
                _2_igual_palo = true;
                igual_palo[1] = true;
                igual_palo[2] = true;
            } else if (this.cartas_mano[0].getPalo_carta() == this.cartas_mano[2].getPalo_carta()) {
                _2_igual_palo = true;
                igual_palo[0] = true;
                igual_palo[2] = true;
            }
            if (_2_igual_palo) {
                puntaje = 20;
                for (int i = 0; i < MAX_CARTAS_MANO; i++) {
                    if (igual_palo[i]) {
                        puntaje += this.cartas_mano[i].puntaje_carta_no_pieza();
                    }
                }
            } else {
                for (int i = 0; i < MAX_CARTAS_MANO; i++) {
                    if (this.cartas_mano[i].puntaje_carta_no_pieza() > puntaje) {
                        puntaje = this.cartas_mano[i].puntaje_carta_no_pieza();
                    }
                }
            }
        } else {
            int mayor_valor_no_pieza = 0;
            for (int i = 0; i < MAX_CARTAS_MANO; i++) {
                if (i != pos_pieza && this.cartas_mano[i].puntaje_carta_no_pieza() > mayor_valor_no_pieza) {
                    mayor_valor_no_pieza = this.cartas_mano[i].puntaje_carta_no_pieza();
                }
            }
            puntaje = this.cartas_mano[pos_pieza].puntaje_pieza(muestra) + mayor_valor_no_pieza;
        }
        return puntaje;
    }
}
