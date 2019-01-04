package Logica;

import Grafica.VentanaPartida;

public class Fachada_Grafica_Logica {

    private static Fachada_Grafica_Logica fgl = null;
    private Jugador jugador;
    private int turno;
    public Partida partida = new Partida(jugador, turno);
    public VentanaPartida vp;

    private Fachada_Grafica_Logica() {
        super();
    }

    public static Fachada_Grafica_Logica getSingletonInstancia() {
        if (fgl == null) {
            fgl = new Fachada_Grafica_Logica();
        }
        return fgl;
    }
}