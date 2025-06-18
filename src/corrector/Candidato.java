package corrector;

import java.util.ArrayList;
import java.util.List;

public class Candidato {

    private String nombre;
    private String idTest;
    private int puntaje;
    private List<String> respuestasTest;

    public Candidato(String nombre, String idTest) {
        this.nombre = nombre;
        this.idTest = idTest;
        puntaje = 0;
        respuestasTest = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdTest() {
        return idTest;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public List<String> getRespuestasTest() {
        return respuestasTest;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public void aniadirRespuesta(String respuesta) {
        respuestasTest.add(respuesta);
    }

    @Override
    public String toString() {
        return String.format("%15s %15s ", nombre, puntaje) + idTest + " " + (puntaje < 7 ? "suspenso" : "");
    }
}