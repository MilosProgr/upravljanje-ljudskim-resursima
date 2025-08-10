package rc.ac.singidunum.novisad.service.implementacija;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import rc.ac.singidunum.novisad.dto.ZaduzenjeDTO;
import rc.ac.singidunum.novisad.mapper.deklaracija.Mapper;
import rc.ac.singidunum.novisad.mapper.implementacija.ZaduzenjeMapper;
import rc.ac.singidunum.novisad.model.Zaduzenje;
import rc.ac.singidunum.novisad.model.Zaposleni;
import rc.ac.singidunum.novisad.repository.ZaduzenjeRepository;
import rc.ac.singidunum.novisad.service.deklaracija.GenericCrudService;

@Service
public class ZaduzenjeService extends GenericCrudService<ZaduzenjeDTO, Zaduzenje, Long> {

	@Autowired
	private ZaposleniService zaposleniService;
	
	@Autowired
	private ZaduzenjeRepository zaduzenjeRepository;
	
	@Autowired
	private ZaduzenjeMapper zaduzenjeMapper;
	
	protected ZaduzenjeService(JpaRepository<Zaduzenje, Long> repository, Mapper<ZaduzenjeDTO, Zaduzenje> mapper) {
		super(repository, mapper);
	}
	
	public List<ZaduzenjeDTO> getZaduzenjeZaZaposlenog(String korisnickoIme){
		Optional<Zaposleni> zaposleniOpt = zaposleniService.findKorisnikByKorisnickoIme(korisnickoIme);
        if (zaposleniOpt.isEmpty()) {
            throw new IllegalArgumentException("Zaposleni sa korisnickim imenom " + korisnickoIme + " nije pronađen.");
        }
        Zaposleni zaposleni = zaposleniOpt.get();
        List<Zaduzenje> zaduzenja = zaduzenjeRepository.findByZaposleniId(zaposleni.getId());
        return zaduzenjeMapper.map(zaduzenja);
	}
	
	

}
