package rc.ac.singidunum.novisad.features.radnoVreme;

import java.time.LocalDate;
//import java.time.LocalDate;
//import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rc.ac.singidunum.novisad.features.radnoVreme.RadnoVremeDTO.RadnoVremeDTORecord;
import rc.ac.singidunum.novisad.features.zaposlen.Zaposleni;
import rc.ac.singidunum.novisad.features.zaposlen.ZaposleniService;
import rc.ac.singidunum.novisad.generics.controller.GenericCrudController;
import rc.ac.singidunum.novisad.generics.service.CrudService;

@Controller
@RequestMapping("/api/radnoVreme")
public class RadnoVremeController extends GenericCrudController<RadnoVremeDTORecord, RadnoVreme, Long> {
    
    @Autowired
    private RadnoVremeService rvService;
    
    @Autowired
    private RadnoVremeRepository radnoVremeRepo;
    
    @Autowired
    private ZaposleniService zaposleniService;

    @Override
    protected CrudService<RadnoVremeDTORecord, RadnoVreme, Long> getService() {
        return rvService;
    }
    
    @RequestMapping("/me")
    public ResponseEntity<List<RadnoVremeDTORecord>> getRadnoVremeZaUlogovanog(Authentication auth) {
        try {
            List<RadnoVremeDTORecord> radnaVremena = rvService.getRadnoVremeZaZaposlenog(auth.getName());
            return ResponseEntity.ok(radnaVremena);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    
    @PostMapping("/dolazak")
    public ResponseEntity<Map<String, String>> evidentirajDolazak(Authentication auth) {
        try {
            Optional<Zaposleni> zaposleniOpt = zaposleniService.findKorisnikByKorisnickoIme(auth.getName());
            if (zaposleniOpt.isEmpty()) {
                throw new IllegalArgumentException("Zaposleni sa korisničkim imenom nije pronađen.");
            }

            Optional<RadnoVreme> danasnje = radnoVremeRepo.findByZaposleniIdAndDatum(zaposleniOpt.get().getId(), LocalDate.now());
            if (danasnje.isPresent()) {
                throw new IllegalStateException("Dolazak za današnji dan je već evidentiran.");
            }

            String poruka = rvService.evidentirajDolazak(auth.getName());
            return ResponseEntity.ok(Map.of("poruka", poruka));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("greska", e.getMessage()));
        }
    }

    @PutMapping("/odlazak")
    public ResponseEntity<Map<String, String>> evidentirajOdlazak(Authentication auth) {
        try {
            String poruka = rvService.evidentirajOdlazak(auth.getName());
            return ResponseEntity.ok(Map.of("poruka", poruka));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("greska", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("greska", "Greška prilikom evidentiranja odlaska"));
        }
    }

}
