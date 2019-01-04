package Logica;

import Grafica.VentanaPartida;
import javax.swing.JOptionPane;

public class Truco {

    public static void main(String[] args) throws InterruptedException {
        
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
