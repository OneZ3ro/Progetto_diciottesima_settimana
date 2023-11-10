package angelomoreno.Progetto_diciottesima_settimana.repositories;

import angelomoreno.Progetto_diciottesima_settimana.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer> {
    Optional<Utente> findByEmail(String email);
}
