package corrector;

import java.util.List;

public class CalculadoraPuntajes {
    public static int calcularPuntaje(List<String> soluciones, List<String> respuestasCandidato) {
        int aciertos = 0;

        for (int i = 0; i < soluciones.size(); i++) {
            if (soluciones.get(i).equals(respuestasCandidato.get(i))) {
                aciertos ++;
            }
        }
        return aciertos;
    }
}
