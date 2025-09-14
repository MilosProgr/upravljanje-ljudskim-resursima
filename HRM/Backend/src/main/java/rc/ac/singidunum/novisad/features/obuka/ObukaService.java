package rc.ac.singidunum.novisad.features.obuka;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import rc.ac.singidunum.novisad.features.obuka.ObukaDTO.ObukaDTORecord;
import rc.ac.singidunum.novisad.features.zaposlen.Zaposleni;
import rc.ac.singidunum.novisad.features.zaposlen.ZaposleniService;
import rc.ac.singidunum.novisad.generics.mapper.Mapper;
import rc.ac.singidunum.novisad.generics.service.GenericCrudService;

@Service
public class ObukaService extends GenericCrudService<ObukaDTORecord, Obuka, Long> {

	@Autowired
	public ObukaRepository obukaRepository;
	
	protected ObukaService(JpaRepository<Obuka, Long> repository, Mapper<ObukaDTORecord, Obuka> mapper) {
		super(repository, mapper);
	}
	
	@Autowired
	private ZaposleniService zaposleniService;
	
	@Autowired
	private ObukaMapper obukaMapper;
	
	public List<ObukaDTORecord> getObukaZaZaposlenog(String korisnickoIme){
        Optional<Zaposleni> zaposleniOpt = zaposleniService.findKorisnikByKorisnickoIme(korisnickoIme);
        if (zaposleniOpt.isEmpty()) {
            throw new IllegalArgumentException("Zaposleni sa korisnickim imenom " + korisnickoIme + " nije pronađen.");
        }
        Zaposleni zaposleni = zaposleniOpt.get();
        List<Obuka> obuke = obukaRepository.findByZaposleniId(zaposleni.getId());
        return obukaMapper.map(obuke);
	}

}
