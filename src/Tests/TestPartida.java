/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Grafica.VentanaPartida;
import Logica.Carta;
import Logica.Fachada_Grafica_Logica;
import Logica.Inteligencia;
import Logica.Jugador;
import Logica.Mano;
import Logica.Mazo;
import Logica.Partida;
import java.awt.BorderLayout;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author 44777219
 */
public class TestPartida {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
         
        for (int i = 0; i < 5; i++) {
            VentanaPartida vp = new VentanaPartida();

            Fachada_Grafica_Logica fgl = Fachada_Grafica_Logica.getSingletonInstancia();
            fgl.partida.setJugador_humano(new Jugador("Bruno"));
            int contador_manos=0;
            while (fgl.partida.getPuntaje_humano() < Partida.MAX_PUNTAJE && fgl.partida.getPuntaje_ia() < Partida.MAX_PUNTAJE) {
                fgl.partida.setMazo(new Mazo());
                fgl.partida.getMazo().generar_mazo_completo();
                fgl.partida.barajar_mazo();

                fgl.partida.repartir_cartas(true);
                vp.cargar_lbl(fgl.partida.getMuestra().toString(), VentanaPartida.LBL_MUESTRA);
                vp.cargar_lbl(fgl.partida.getMano_humano().getCartas_mano()[0].toString(), VentanaPartida.LBL_MANO_HUM_1);
                vp.cargar_lbl(fgl.partida.getMano_humano().getCartas_mano()[1].toString(), VentanaPartida.LBL_MANO_HUM_2);
                vp.cargar_lbl(fgl.partida.getMano_humano().getCartas_mano()[2].toString(), VentanaPartida.LBL_MANO_HUM_3);
                vp.cargar_lbl(fgl.partida.getMano_ia().getCartas_mano()[0].toString(), VentanaPartida.LBL_MANO_IA_1);
                vp.cargar_lbl(fgl.partida.getMano_ia().getCartas_mano()[1].toString(), VentanaPartida.LBL_MANO_IA_2);
                vp.cargar_lbl(fgl.partida.getMano_ia().getCartas_mano()[2].toString(), VentanaPartida.LBL_MANO_IA_3);
                vp.actualizar_puntajes(fgl.partida.getPuntaje_humano(), fgl.partida.getPuntaje_ia());
                Thread.sleep(100);

                if (fgl.partida.getMano_humano().tiene_flor(fgl.partida.getMuestra()) && !fgl.partida.getMano_ia().tiene_flor(fgl.partida.getMuestra())) {
                    fgl.partida.setPuntaje_humano(fgl.partida.getPuntaje_humano() + 3);
                } else if (!fgl.partida.getMano_humano().tiene_flor(fgl.partida.getMuestra()) && fgl.partida.getMano_ia().tiene_flor(fgl.partida.getMuestra())) {
                    fgl.partida.setPuntaje_ia(fgl.partida.getPuntaje_ia() + 3);
                } else if(!fgl.partida.getMano_humano().tiene_flor(fgl.partida.getMuestra()) && !fgl.partida.getMano_ia().tiene_flor(fgl.partida.getMuestra())){
                    fgl.partida.getJugador_inteligencia().tocar_envido();
                }

                contador_manos++;
            }
            if (fgl.partida.getPuntaje_humano() > fgl.partida.getPuntaje_ia()) {
                JOptionPane.showMessageDialog(null, "GANADOR: " + fgl.partida.getJugador_humano().getNombre()+"\nCantidad manos: "+contador_manos);
            } else {
                JOptionPane.showMessageDialog(null, "GANADOR: " + fgl.partida.getJugador_inteligencia().toString()+"\nCantidad manos: "+contador_manos);
            }
            fgl.partida.setPuntaje_ia(0);
            fgl.partida.setPuntaje_humano(0);
            vp.dispose();

        }
       
    }
}
