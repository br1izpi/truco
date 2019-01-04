package Logica;

public class Jugador {

    private String nombre;

    private String pass;

    private String preg;

    private String resp;

    public Jugador(String nombre, String pass, String preg, String resp) {
        this.nombre = nombre;
        this.pass = pass;
        this.preg = preg;
        this.resp = resp;
    }

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.pass = "1234";
        this.preg = "";
        this.resp = "";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPreg() {
        return preg;
    }

    public void setPreg(String preg) {
        this.preg = preg;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + ", pass=" + pass + ", preg=" + preg + ", resp=" + resp + '}';
    }


}
