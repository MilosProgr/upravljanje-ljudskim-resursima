package rc.ac.singidunum.novisad.features.zaduzenje;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import rc.ac.singidunum.novisad.features.zaduzenje.ZaduzenjeDTO.ZaduzenjeDTORecord;
import rc.ac.singidunum.novisad.features.zaposlen.Zaposleni;
import rc.ac.singidunum.novisad.features.zaposlen.ZaposleniService;
import rc.ac.singidunum.novisad.generics.mapper.Mapper;
import rc.ac.singidunum.novisad.generics.service.GenericCrudService;

@Service
public class ZaduzenjeService extends GenericCrudService<ZaduzenjeDTORecord, Zaduzenje, Long> {

	@Autowired
	private ZaposleniService zaposleniService;
	
	@Autowired
	private ZaduzenjeRepository zaduzenjeRepository;
	
	@Autowired
	private ZaduzenjeMapper zaduzenjeMapper;
	
	protected ZaduzenjeService(JpaRepository<Zaduzenje, Long> repository, Mapper<ZaduzenjeDTORecord, Zaduzenje> mapper) {
		super(repository, mapper);
	}
	
	public List<ZaduzenjeDTORecord> getZaduzenjeZaZaposlenog(String korisnickoIme){
		Optional<Zaposleni> zaposleniOpt = zaposleniService.findKorisnikByKorisnickoIme(korisnickoIme);
        if (zaposleniOpt.isEmpty()) {
            throw new IllegalArgumentException("Zaposleni sa korisnickim imenom " + korisnickoIme + " nije pronađen.");
        }
        Zaposleni zaposleni = zaposleniOpt.get();
        List<Zaduzenje> zaduzenja = zaduzenjeRepository.findByZaposleniId(zaposleni.getId());
        return zaduzenjeMapper.map(zaduzenja);
	}
	
	

}
