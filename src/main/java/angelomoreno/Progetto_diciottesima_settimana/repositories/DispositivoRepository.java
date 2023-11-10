package angelomoreno.Progetto_diciottesima_settimana.repositories;

import angelomoreno.Progetto_diciottesima_settimana.entities.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Integer> {
}
