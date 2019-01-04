package Logica;

import java.util.ArrayList;
import java.util.Random;

public class Mazo {

    static final int MAXIMO_CARTAS = 40;

    private ArrayList<Carta> cartas;

    public Mazo() {
        this.cartas = new ArrayList();
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    public void generar_mazo_completo() {
        ArrayList<Integer> lista = new ArrayList();
        for (int i = 1; i < 8; i++) {
            lista.add(i);
        }
        lista.add(10);
        lista.add(11);
        lista.add(12);
        for (int palo = 1; palo < 5; palo++) {
            for (Integer valor : lista) {
                Carta c = new Carta(String.valueOf(palo) + String.valueOf(valor), valor, palo, "");
                this.cartas.add(c);
            }
        }
    }

    public void imprimir_mazo() {
        for (int i = 0; i < this.getCartas().size(); i++) {
            System.out.println(this.cartas.get(i).toString());
        }
    }

    public boolean esta_vacio() {
        return this.cartas.isEmpty();
    }

    public Carta extraer_carta() {
        Carta c = new Carta();
        c = this.cartas.get(0);
        this.cartas.remove(0);
        return c;
    }

    public int cant_cartas() {
        return this.cartas.size();
    }

    public void barajar() {
        Carta aux1 = new Carta();
        Random aleatorio = new Random();
        int pos = 0;
        for (int i = 0; i < this.cant_cartas(); i++) {
            pos = aleatorio.nextInt(MAXIMO_CARTAS);
            aux1 = this.cartas.get(pos);
            this.cartas.set(pos, this.cartas.get(i));
            this.cartas.set(i, aux1);
        }
    }
}
