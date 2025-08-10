package rc.ac.singidunum.novisad.service.implementacija;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Service;

import rc.ac.singidunum.novisad.dto.RadnoVremeDTO;
import rc.ac.singidunum.novisad.mapper.deklaracija.Mapper;
import rc.ac.singidunum.novisad.mapper.implementacija.RadnoVremeMapper;
import rc.ac.singidunum.novisad.model.RadnoVreme;
import rc.ac.singidunum.novisad.model.Zaposleni;
import rc.ac.singidunum.novisad.repository.RadnoVremeRepository;
//import rc.ac.singidunum.novisad.repository.ZaposleniRepository;
import rc.ac.singidunum.novisad.service.deklaracija.GenericCrudService;
import rc.ac.singidunum.novisad.tipovi.TipRadnogVremena;

@Service
public class RadnoVremeService extends GenericCrudService<RadnoVremeDTO, RadnoVreme, Long> {

	@Autowired
    private ZaposleniService zaposleniService;
	@Autowired
    private RadnoVremeRepository radnoVremeRepo;
//    private ZaposleniRepository zapRepository;
    
    @Autowired
    private RadnoVremeMapper rMapper;

    protected RadnoVremeService(JpaRepository<RadnoVreme, Long> repository, Mapper<RadnoVremeDTO, RadnoVreme> mapper) {
        super(repository, mapper);
       
    }
    
    public List<RadnoVremeDTO> getRadnoVremeZaZaposlenog(String korisnickoIme) {
        Optional<Zaposleni> zaposleniOpt = zaposleniService.findKorisnikByKorisnickoIme(korisnickoIme);
        if (zaposleniOpt.isEmpty()) {
            throw new IllegalArgumentException("Zaposleni sa korisnickim imenom " + korisnickoIme + " nije pronađen.");
        }
        Zaposleni zaposleni = zaposleniOpt.get();

        List<RadnoVreme> radnaVremena = radnoVremeRepo.findByZaposleniId(zaposleni.getId());

        return rMapper.map(radnaVremena);
    }

    
    public String evidentirajDolazak(String korisnickoIme) {
        Optional<Zaposleni> zaposleniOpt = zaposleniService.findKorisnikByKorisnickoIme(korisnickoIme);
        if (zaposleniOpt.isEmpty()) {
            throw new IllegalArgumentException("Zaposleni sa korisnickim imenom " + korisnickoIme + " nije pronadjen.");
        }
        Zaposleni zaposleni = zaposleniOpt.get();
        
        RadnoVreme rv = new RadnoVreme();
        rv.setZaposleni(zaposleni);
        rv.setDatum(LocalDate.now());
        rv.setVremeDolaska(LocalTime.now());
        rv.setVremeOdlaska(null);
        radnoVremeRepo.save(rv);
        
        return "Dolazak evidentiran";
    }

    public String evidentirajOdlazak(String korisnickoIme) {
        Optional<Zaposleni> zaposleniOpt = zaposleniService.findKorisnikByKorisnickoIme(korisnickoIme);
        if (zaposleniOpt.isEmpty()) {
            throw new IllegalArgumentException("Zaposleni sa korisnickim imenom " + korisnickoIme + " nije pronadjen.");
        }
        Zaposleni zaposleni = zaposleniOpt.get();
        
        Optional<RadnoVreme> danasnje = radnoVremeRepo.findByZaposleniIdAndDatum(zaposleni.getId(), LocalDate.now());
        if (danasnje.isPresent()) {
            RadnoVreme rv = danasnje.get();
            rv.setVremeOdlaska(LocalTime.now());
            kreirajRadnoVreme(rv);
            radnoVremeRepo.save(rv);
            return "Odlazak evidentiran";
        } else {
            throw new IllegalStateException("Nema evidentiran dolazak");
        }
    }
    
    public void kreirajRadnoVreme(RadnoVreme radnoVreme) {

    	LocalTime dolazak = radnoVreme.getVremeDolaska();
        LocalTime odlazak = radnoVreme.getVremeOdlaska();

        long sati = java.time.Duration.between(dolazak, odlazak).toHours();

        if (dolazak.isAfter(LocalTime.of(22, 0)) || odlazak.isBefore(LocalTime.of(6, 0))) {
            radnoVreme.setTipRadnogVremena(TipRadnogVremena.NOĆNA_SMENA);
        } else if (sati >= 8) {
            radnoVreme.setTipRadnogVremena(TipRadnogVremena.PUNO_RADNO_VREME);
        } else if (sati >= 4) {
            radnoVreme.setTipRadnogVremena(TipRadnogVremena.NEPUNO_RADNO_VREME);
        } else {
            radnoVreme.setTipRadnogVremena(TipRadnogVremena.FLEKSIBILNO);
        }
    }

}
