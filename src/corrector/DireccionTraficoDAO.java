package corrector;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DireccionTraficoDAO implements IDireccionTraficoDAO{
    private File archivoSoluciones;
    private File archivoPuntaje;

    private static final String NOMBRE_ARCHIVO_SOLUCIONES = "soluciones.txt";
    private static final String NOMBRE_ARCHIVO_PUNTAJE = "puntajes.txt";

    public DireccionTraficoDAO() throws IllegalArgumentException {
        archivoSoluciones = new File(NOMBRE_ARCHIVO_SOLUCIONES);

        if (!archivoSoluciones.exists()) {
            throw new IllegalArgumentException("No se puede construir el objeto de acceso a datos porque el archivo de soluciones " + NOMBRE_ARCHIVO_SOLUCIONES + " no existe");
        }

        archivoPuntaje = new File(NOMBRE_ARCHIVO_PUNTAJE);
    }

    @Override
    public Map<String, List<String>> cargarSoluciones() throws DAOException {
        Map<String, List<String>> mapaSoluciones = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoSoluciones))) {
            
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] entrada = linea.split(";");
                String nombreTest = entrada[0];
                List<String> respuestasCorrectas = new ArrayList<>();

                for (int i = 1; i < entrada.length; i++) {
                    respuestasCorrectas.add(entrada[i]);
                }

                mapaSoluciones.putIfAbsent(nombreTest, respuestasCorrectas);
            }
        } catch (FileNotFoundException e) {
            throw new DAOException("El archivo de soluciones " + NOMBRE_ARCHIVO_SOLUCIONES + " no se encuentra");
        } catch (IOException e) {
            throw new DAOException("Error leyendo el archivo de soluciones " + NOMBRE_ARCHIVO_SOLUCIONES);
        }

        return mapaSoluciones;
    }

    @Override
    public List<Candidato> cargarCandidatos(String idTest) throws DAOException{
        List<Candidato> listaCandidatos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(idTest + ".txt"))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] entrada = linea.split(";");
                String nombreCandidato = entrada[0];

                Candidato candidato = new Candidato(nombreCandidato, idTest);

                for (int i = 1; i < entrada.length; i++) {
                    candidato.aniadirRespuesta(entrada[i]);
                }

                listaCandidatos.add(candidato);
            }
        } catch (FileNotFoundException e) {
            throw new DAOException("El archivo del test " + idTest + ".txt no se encuentra");
        } catch (IOException e) {
            throw new DAOException("Error leyendo el archivo del test " + idTest);
        }

        return listaCandidatos;
    }

    @Override
    public void guardarPuntajes(List<Candidato> candidatos) throws DAOException {
        
        try (FileWriter fw = new FileWriter(archivoPuntaje, true);
            BufferedWriter bw = new BufferedWriter(fw)) {
            for (Candidato candidato : candidatos) {
                bw.write(candidato + "\n");
            }
        } catch (IOException e) {
            throw new DAOException("Error escribiendo el archivo de puntajes " + NOMBRE_ARCHIVO_PUNTAJE);
        }
    }
}
