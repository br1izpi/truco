package Logica;

import java.io.Serializable;

public class Carta implements Serializable {

    public static final int PALO_BASTO = 1;

    public static final int PALO_COPA = 2;

    public static final int PALO_ESPADA = 3;

    public static final int PALO_ORO = 4;

    private String id_carta;

    private int valor_carta;

    private int palo_carta;

    private String url_imagen;

    public Carta() {
    }

    public Carta(String id_carta, int valor_carta, int palo_carta, String url_imagen) {
        this.id_carta = id_carta;
        this.valor_carta = valor_carta;
        this.palo_carta = palo_carta;
        this.url_imagen = url_imagen;
    }

    public Carta(int valor_carta, int palo_carta) {
        this.valor_carta = valor_carta;
        this.palo_carta = palo_carta;
    }

    public String getId_carta() {
        return id_carta;
    }

    public void setId_carta(String id_carta) {
        this.id_carta = id_carta;
    }

    public int getValor_carta() {
        return valor_carta;
    }

    public void setValor_carta(int valor_carta) {
        this.valor_carta = valor_carta;
    }

    public int getPalo_carta() {
        return palo_carta;
    }

    public void setPalo_carta(int palo_carta) {
        this.palo_carta = palo_carta;
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    public String palo_String() {
        String palo = "";
        switch(this.palo_carta) {
            case 1:
                palo = "BASTO";
                break;
            case 2:
                palo = "COPA";
                break;
            case 3:
                palo = "ESPADA";
                break;
            case 4:
                palo = "ORO";
                break;
        }
        return palo;
    }

    @Override
    public String toString() {
        return this.valor_carta + " de " + this.palo_String();
    }

    public boolean _2_4_5_10_11() {
        return this.valor_carta == 2 || this.valor_carta == 4 || this.valor_carta == 5 || this.valor_carta == 10 || this.valor_carta == 11;
    }

    public boolean _12() {
        return this.valor_carta == 12;
    }

    public boolean es_pieza(Carta carta_muestra) {
        if (this.palo_carta != carta_muestra.palo_carta) {
            return false;
        } else {
            if (this._2_4_5_10_11()) {
                return true;
            } else {
                return carta_muestra._2_4_5_10_11() && this._12();
            }
        }
    }

    public int puntaje_pieza(Carta muestra) {
        int alcahuete = 0;
        if (this._12()) {
            alcahuete = muestra.getValor_carta();
        } else {
            alcahuete = this.getValor_carta();
        }
        int valor_devuelve = 0;
        switch(alcahuete) {
            case 2:
                valor_devuelve = 30;
                break;
            case 4:
                valor_devuelve = 29;
                break;
            case 5:
                valor_devuelve = 28;
                break;
            case 10:
            case 11:
                valor_devuelve = 27;
                break;
        }
        return valor_devuelve;
    }

    public int puntaje_carta_no_pieza() {
        if (this.valor_carta > 7) {
            return 0;
        } else {
            return this.valor_carta;
        }
    }

    public int puntaje_cualquier_carta(Carta muestra) {
        if (this.es_pieza(muestra)) {
            return this.puntaje_pieza(muestra);
        } else {
            return this.puntaje_carta_no_pieza();
        }
    }

    public int valor_mata() {
        int valor = 0;
        if (this.valor_carta == 1 && this.palo_carta == Carta.PALO_ESPADA) {
            valor = 14;
        } else if (this.valor_carta == 1 && this.palo_carta == Carta.PALO_BASTO) {
            valor = 13;
        } else if (this.valor_carta == 7 && this.palo_carta == Carta.PALO_ESPADA) {
            valor = 12;
        } else if (this.valor_carta == 7 && this.palo_carta == Carta.PALO_ORO) {
            valor = 11;
        }
        return valor;
    }

    public int valor_truco(Carta muestra) {
        int valor_truco = 0;
        if (this.es_pieza(muestra)) {
            if (this.puntaje_pieza(muestra) > 27) {
                valor_truco = this.puntaje_pieza(muestra) - 11;
            } else {
                if (this.valor_carta == 10 || this.valor_carta == 12 && muestra.valor_carta == 10) {
                    valor_truco = 15;
                } else {
                    valor_truco = 16;
                }
            }
        } else {
            if (this.valor_mata() > 0) {
                valor_truco = this.valor_mata();
            } else {
                switch(this.valor_carta) {
                    case 3:
                        valor_truco = 10;
                        break;
                    case 2:
                        valor_truco = 9;
                        break;
                    case 1:
                        valor_truco = 8;
                        break;
                    case 12:
                        valor_truco = 7;
                        break;
                    case 11:
                        valor_truco = 6;
                        break;
                    case 10:
                        valor_truco = 5;
                        break;
                    case 7:
                        valor_truco = 4;
                        break;
                    case 6:
                        valor_truco = 3;
                        break;
                    case 5:
                        valor_truco = 2;
                        break;
                    case 4:
                        valor_truco = 1;
                        break;
                }
            }
        }
        return valor_truco;
    }

    public int ataque(Carta atacada, Carta muestra) {
        int puntaje_atacante = this.valor_truco(muestra);
        int puntaje_atacada = atacada.valor_truco(muestra);
        return puntaje_atacante - puntaje_atacada;
    }
}
