package corrector;

import java.io.IOException;

public class DAOException extends IOException {
    public DAOException(String mensaje) {
        super(mensaje);
    }
}
