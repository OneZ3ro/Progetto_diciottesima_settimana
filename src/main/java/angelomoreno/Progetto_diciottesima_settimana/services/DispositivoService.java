package angelomoreno.Progetto_diciottesima_settimana.services;

import angelomoreno.Progetto_diciottesima_settimana.entities.Dispositivo;
import angelomoreno.Progetto_diciottesima_settimana.entities.Utente;
import angelomoreno.Progetto_diciottesima_settimana.enums.StatoDispositivo;
import angelomoreno.Progetto_diciottesima_settimana.exceptions.BadRequestException;
import angelomoreno.Progetto_diciottesima_settimana.exceptions.NotFoundException;
import angelomoreno.Progetto_diciottesima_settimana.payloads.entities.DispositivoDTO;
import angelomoreno.Progetto_diciottesima_settimana.repositories.DispositivoRepository;
import angelomoreno.Progetto_diciottesima_settimana.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DispositivoService {
    @Autowired
    private DispositivoRepository dispositivoRepository;
    @Autowired
    private UtenteService utenteService;

    public Page<Dispositivo> getDispositivi(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return dispositivoRepository.findAll(pageable);
    }

    public Dispositivo saveDispositivo(DispositivoDTO body) throws NotFoundException {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setTipoDispositivo(body.tipoDispositivo());
        dispositivo.setStatoDispositivo(body.statoDispositivo());
        return dispositivoRepository.save(dispositivo);
    }

    public Dispositivo findById(int id) throws NotFoundException {
        return dispositivoRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Dispositivo modificaDispositivo(int id, DispositivoDTO body) throws NotFoundException, BadRequestException {
        Dispositivo dispositivo = this.findById(id);
        dispositivo.setTipoDispositivo(body.tipoDispositivo());
        dispositivo.setStatoDispositivo(body.statoDispositivo());
        if (body.statoDispositivo().equals(StatoDispositivo.DISPONIBILE)) {
            Utente app = utenteService.findById(body.utenteId());
            dispositivo.setUtente(app);
        } else {
            throw new BadRequestException("Questo dispositivo al momento non può essere assegnato al momento. Riprova più tardi");
        }

        return dispositivoRepository.save(dispositivo);
    }

    public void eliminaDispositivo(int id) throws NotFoundException {
        dispositivoRepository.deleteById(id);
    }
}
