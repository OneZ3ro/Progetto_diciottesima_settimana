package angelomoreno.Progetto_diciottesima_settimana.controllers;

import angelomoreno.Progetto_diciottesima_settimana.entities.Utente;
import angelomoreno.Progetto_diciottesima_settimana.exceptions.BadRequestException;
import angelomoreno.Progetto_diciottesima_settimana.payloads.entities.UtenteDTO;
import angelomoreno.Progetto_diciottesima_settimana.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/utenti")
public class UtentiController {
    @Autowired
    private UtenteService utenteService;

    @GetMapping("")
    public Page<Utente> getUtenti (@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "id") String id
                                   ) {
        return utenteService.getUtenti(page, size, id);
    }
    
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Utente saveUtente(@RequestBody @Validated UtenteDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return utenteService.saveUtente(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @GetMapping("/{id}")
    public Utente findById(@PathVariable int id) {
        return utenteService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Utente modificaUtente(@PathVariable int id, @RequestBody UtenteDTO body) {
        return utenteService.modificaUtente(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminaUtente(@PathVariable int id) {
        utenteService.eliminaUtente(id);
    }
}
