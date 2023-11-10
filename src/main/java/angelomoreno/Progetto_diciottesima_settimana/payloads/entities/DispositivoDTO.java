package angelomoreno.Progetto_diciottesima_settimana.payloads.entities;

import angelomoreno.Progetto_diciottesima_settimana.entities.Utente;
import angelomoreno.Progetto_diciottesima_settimana.enums.StatoDispositivo;
import angelomoreno.Progetto_diciottesima_settimana.enums.TipoDispositivo;
import jakarta.validation.constraints.NotNull;

public record DispositivoDTO(
        @NotNull(message = "Lo stato del dispositivo è obbligatorio")
        TipoDispositivo tipoDispositivo,
        @NotNull(message = "Lo stato del dispositivo è obbligatorio")
        StatoDispositivo statoDispositivo,
        @NotNull(message = "L'utente è obbligatorio")
        Utente utente
) {
}
