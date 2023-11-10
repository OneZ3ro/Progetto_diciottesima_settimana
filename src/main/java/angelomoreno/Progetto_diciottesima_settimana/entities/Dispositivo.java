package angelomoreno.Progetto_diciottesima_settimana.entities;

import angelomoreno.Progetto_diciottesima_settimana.enums.StatoDispositivo;
import angelomoreno.Progetto_diciottesima_settimana.enums.TipoDispositivo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dispositivi")
@Getter
@Setter
@ToString
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dispositivo_id")
    private int dispositivoId;
    @Enumerated(EnumType.STRING)
    private TipoDispositivo tipoDispositivo;
    @Enumerated(EnumType.STRING)
    private StatoDispositivo statoDispositivo;
    @ManyToOne
    @JoinColumn(name = "utenti_id", nullable = false)
    private Utente utente;
}
