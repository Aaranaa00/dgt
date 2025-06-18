package corrector;

import java.util.List;
import java.util.Map;

interface IDireccionTraficoDAO {
    Map<String, List<String>> cargarSoluciones() throws DAOException;
    List<Candidato> cargarCandidatos(String idTest) throws DAOException;
    void guardarPuntajes(List<Candidato> candidatos) throws DAOException;
}
