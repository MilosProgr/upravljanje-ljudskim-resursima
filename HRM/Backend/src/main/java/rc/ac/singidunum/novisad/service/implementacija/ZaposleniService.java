package rc.ac.singidunum.novisad.service.implementacija;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import rc.ac.singidunum.novisad.dto.ZaposleniDTO;
import rc.ac.singidunum.novisad.mapper.deklaracija.Mapper;
import rc.ac.singidunum.novisad.mapper.implementacija.ZaposleniMapper;
import rc.ac.singidunum.novisad.model.Zaposleni;
import rc.ac.singidunum.novisad.repository.ZaposleniRepository;
import rc.ac.singidunum.novisad.service.deklaracija.GenericCrudService;

@Service
public class ZaposleniService extends GenericCrudService<ZaposleniDTO, Zaposleni, Long> {

	
	private ZaposleniRepository zaposlenRepository;
	
	@Autowired
	private ZaposleniMapper mapper;
	
	public ZaposleniService(JpaRepository<Zaposleni, Long> repository, Mapper<ZaposleniDTO, Zaposleni> mapper) {
		super(repository, mapper);
		this.zaposlenRepository =  (ZaposleniRepository) repository;
		this.mapper = (ZaposleniMapper) mapper;
	}
	
	public Optional<Zaposleni> findKorisnikByKorisnickoIme(String korisnickoIme){
		return zaposlenRepository.findKorisnikByKorisnickoIme(korisnickoIme);
	}
	
	public List<ZaposleniDTO> pretrazi(String ime, String prezime, String email, Long odsekId) {
	    List<Zaposleni> rezultati = zaposlenRepository.pretrazi(ime, prezime, email, odsekId);
	    return mapper.map(rezultati);
	}
	
	public Zaposleni findZaposlenById(Long id){
		
		Optional<Zaposleni> z  = zaposlenRepository.findById(id);
		Zaposleni zap = z.get();
		return zap;
	}
	
	
	


}
