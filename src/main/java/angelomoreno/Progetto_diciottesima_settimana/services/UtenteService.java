package angelomoreno.Progetto_diciottesima_settimana.services;

import angelomoreno.Progetto_diciottesima_settimana.entities.Utente;
import angelomoreno.Progetto_diciottesima_settimana.exceptions.BadRequestException;
import angelomoreno.Progetto_diciottesima_settimana.exceptions.NotFoundException;
import angelomoreno.Progetto_diciottesima_settimana.payloads.entities.UtenteDTO;
import angelomoreno.Progetto_diciottesima_settimana.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    public Page<Utente> getUtenti (int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return utenteRepository.findAll(pageable);
    }

    public Utente saveUtente(UtenteDTO body) throws IOException {
        utenteRepository.findByEmail(body.email()).ifPresent(utente -> {
            throw new BadRequestException("L'email " + utente.getEmail() + " è già stata utilizzata!");
        });

        Utente utente = new Utente();
        utente.setUsername(body.username());
        utente.setNome(body.nome());
        utente.setCognome(body.cognome());
        utente.setEmail(body.email());
        utente.setUrlImg("https://ui-avatars.com/api/?name=" + body.nome() + "+" + body.cognome());
        return utenteRepository.save(utente);
    }

    public Utente findById(int id) throws NotFoundException {
        return utenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Utente modificaUtente(int id, Utente body) throws NotFoundException {
         Utente utente = this.findById(id);
         utente.setUsername(body.getUsername());
         utente.setNome(body.getNome());
         utente.setCognome(body.getCognome());
         utente.setEmail(body.getEmail());
         utente.setUrlImg("https://ui-avatars.com/api/?name=" + body.getNome() + "+" + body.getCognome());
         return utenteRepository.save(utente);
    }

    public void eliminaUtente(int id) throws NotFoundException {
        utenteRepository.deleteById(id);
    }
}
