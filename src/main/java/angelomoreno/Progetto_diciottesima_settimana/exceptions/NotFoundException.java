package angelomoreno.Progetto_diciottesima_settimana.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(int id) {
        super("L'elemento con id: " + id + " non è stato trovato. Riprovare con un id diverso");
    }
}
