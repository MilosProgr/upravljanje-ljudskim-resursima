package rc.ac.singidunum.novisad.service.implementacija;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import rc.ac.singidunum.novisad.dto.ObukaDTO;
import rc.ac.singidunum.novisad.mapper.deklaracija.Mapper;
import rc.ac.singidunum.novisad.mapper.implementacija.ObukaMapper;
import rc.ac.singidunum.novisad.model.Obuka;
import rc.ac.singidunum.novisad.model.Zaposleni;
import rc.ac.singidunum.novisad.repository.ObukaRepository;
import rc.ac.singidunum.novisad.service.deklaracija.GenericCrudService;

@Service
public class ObukaService extends GenericCrudService<ObukaDTO, Obuka, Long> {

	@Autowired
	public ObukaRepository obukaRepository;
	
	protected ObukaService(JpaRepository<Obuka, Long> repository, Mapper<ObukaDTO, Obuka> mapper) {
		super(repository, mapper);
	}
	
	@Autowired
	private ZaposleniService zaposleniService;
	
	@Autowired
	private ObukaMapper obukaMapper;
	
	public List<ObukaDTO> getObukaZaZaposlenog(String korisnickoIme){
        Optional<Zaposleni> zaposleniOpt = zaposleniService.findKorisnikByKorisnickoIme(korisnickoIme);
        if (zaposleniOpt.isEmpty()) {
            throw new IllegalArgumentException("Zaposleni sa korisnickim imenom " + korisnickoIme + " nije pronađen.");
        }
        Zaposleni zaposleni = zaposleniOpt.get();
        List<Obuka> obuke = obukaRepository.findByZaposleniId(zaposleni.getId());
        return obukaMapper.map(obuke);
	}

}
