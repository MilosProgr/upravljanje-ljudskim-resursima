package rc.ac.singidunum.novisad.service.implementacija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rc.ac.singidunum.novisad.dto.KorisnikPravoPristupaDTO;
import rc.ac.singidunum.novisad.dto.PlataDTO;
import rc.ac.singidunum.novisad.dto.PozicijaDTO;
import rc.ac.singidunum.novisad.dto.ZaposleniDTO;
import rc.ac.singidunum.novisad.mapper.deklaracija.Mapper;
import rc.ac.singidunum.novisad.mapper.implementacija.PlataMapper;
import rc.ac.singidunum.novisad.mapper.implementacija.ZaposleniMapper;
import rc.ac.singidunum.novisad.model.KorisnikPravoPristupa;
import rc.ac.singidunum.novisad.model.Plata;
import rc.ac.singidunum.novisad.model.Zaposleni;
import rc.ac.singidunum.novisad.repository.KorisnikPravoPristupaRepository;
import rc.ac.singidunum.novisad.repository.PlataRepository;
import rc.ac.singidunum.novisad.repository.ZaposleniRepository;
import rc.ac.singidunum.novisad.service.deklaracija.GenericCrudService;


import org.springframework.scheduling.annotation.Scheduled;

import java.io.Console;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service
public class PlataService extends GenericCrudService<PlataDTO, Plata, Long>{

	@Autowired
	private ZaposleniService zaposleniService;
	
	@Autowired
	private ZaposleniMapper zaposleniMapper;

	@Autowired
	private PlataRepository plataRepository;
	
	@Autowired
	private PlataMapper plataMapper;
	
	@Autowired
	private KorisnikPravoPristupaRepository korisnikPravoPristupaRepository;
	

	protected PlataService(JpaRepository<Plata, Long> repository, Mapper<PlataDTO, Plata> mapper) {
		super(repository, mapper);
		// TODO Auto-generated constructor stub
	}
	
	@Scheduled(cron = "0 0 0 1 * ?") // Runs at midnight on the 1st of every month
	public void generisiPlateZaNoviMesec() {
	LocalDate danas = LocalDate.now();
   
        Plata plata = new Plata();
        plata.setDatumIsplate(danas);
        plataRepository.save(plata);
    

        System.out.println("Plate su automatski generisane za datum: " + danas);

	}
	
	public PlataDTO dobaviPlatuPrekoPravaPristupa(Long zaposlenId) {
	    List<KorisnikPravoPristupa> prava = korisnikPravoPristupaRepository.findByZaposlenId(zaposlenId);

	    for (KorisnikPravoPristupa pravo : prava) {
	        if (pravo.getPozicija() != null && pravo.getPozicija().getPlata() != null) {
	            Plata plata = pravo.getPozicija().getPlata();
	            // Dinamički izračunaj datum isplate
	            LocalDate datumZaposlenja = zaposleniService.findZaposlenById(zaposlenId).getDatumZaposlenja();
	            if (datumZaposlenja == null) {
	                return null; // ili baci exception po potrebi
	            }

	            LocalDate datumIsplate = izracunajSledeciDatumIsplate(datumZaposlenja);
	            plata.setDatumIsplate(datumIsplate);

	            return plataMapper.map(plata);
	        }
	    }

	    // Ako nema prava sa platom, napravi samo datum isplate na osnovu datumZaposlenja i vrati novi PlataDTO sa tim datumom
	    Zaposleni zaposleni = zaposleniService.findZaposlenById(zaposlenId);
	    if (zaposleni == null || zaposleni.getDatumZaposlenja() == null) {
	        return null;
	    }

	    LocalDate datumIsplate = izracunajSledeciDatumIsplate(zaposleni.getDatumZaposlenja());

	    PlataDTO plataDTO = new PlataDTO();
	    plataDTO.setDatumIsplate(datumIsplate);
	    // Ostala polja po potrebi ostavi prazna ili dodaj po želji

	    return plataDTO;
	}

	// Pomoćna metoda koja računa sledeći datum isplate na osnovu datuma zaposlenja
	private LocalDate izracunajSledeciDatumIsplate(LocalDate datumZaposlenja) {
	    LocalDate danas = LocalDate.now();
	    YearMonth startMonth = YearMonth.from(datumZaposlenja);
	    YearMonth candidateMonth = startMonth;

	    // Iteriraj dok datum ne bude danas ili u budućnosti
	    while (!candidateMonth.atDay(datumZaposlenja.getDayOfMonth()).isAfter(danas) &&
	           !candidateMonth.atDay(datumZaposlenja.getDayOfMonth()).isEqual(danas)) {
	        candidateMonth = candidateMonth.plusMonths(1);
	    }

	    try {
	        return candidateMonth.atDay(datumZaposlenja.getDayOfMonth());
	    } catch (Exception e) {
	        // Ako dan ne postoji u mesecu (npr. 31. februar), vrati poslednji dan meseca
	        return candidateMonth.atEndOfMonth();
	    }
	}


}
