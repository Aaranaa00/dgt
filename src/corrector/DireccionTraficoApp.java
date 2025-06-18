package corrector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DireccionTraficoApp {
    private IDireccionTraficoDAO dao;

    public DireccionTraficoApp(IDireccionTraficoDAO dao) {
        this.dao = dao;
    }

    public void procesaTest() throws RuntimeException {
        Map<String, List<String>> soluciones = new HashMap<>();

        try {
            soluciones = dao.cargarSoluciones();
        } catch (DAOException e) {
            throw new RuntimeException();
        }

        for (String clave : soluciones.keySet()) {
            List<Candidato> candidatos = new ArrayList<>();
            try {
                candidatos = dao.cargarCandidatos(clave);
                for (Candidato candidato : candidatos) {
                    int puntaje = CalculadoraPuntajes.calcularPuntaje(soluciones.get(clave), candidato.getRespuestasTest());
                    candidato.setPuntaje(puntaje);
                }
            } catch (DAOException e) {
                System.err.println(e.getMessage());
            }

            try {
                dao.guardarPuntajes(candidatos);
            } catch (DAOException e) {
                throw new RuntimeException();
            }
        }
        
    }

    public static void main(String[] args) {
        try {
            IDireccionTraficoDAO dao = new DireccionTraficoDAO();
            DireccionTraficoApp appTrafico = new DireccionTraficoApp(dao);

            appTrafico.procesaTest();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }
}
