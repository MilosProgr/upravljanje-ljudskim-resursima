package rc.ac.singidunum.novisad.features.zaposlen;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import rc.ac.singidunum.novisad.features.zaposlen.ZaposleniDTO.ZaposleniDTORecord;
import rc.ac.singidunum.novisad.generics.mapper.Mapper;
import rc.ac.singidunum.novisad.generics.service.GenericCrudService;

@Service
public class ZaposleniService extends GenericCrudService<ZaposleniDTORecord, Zaposleni, Long> {

	@Autowired
	private ZaposleniRepository zaposlenRepository;
	
	@Autowired
	private ZaposleniMapper mapper;
	
	public ZaposleniService(JpaRepository<Zaposleni, Long> repository, Mapper<ZaposleniDTORecord, Zaposleni> mapper) {
		super(repository, mapper);
		this.zaposlenRepository =  (ZaposleniRepository) repository;
		this.mapper = (ZaposleniMapper) mapper;
	}
	
	public Optional<Zaposleni> findKorisnikByKorisnickoIme(String korisnickoIme){
		return zaposlenRepository.findKorisnikByKorisnickoIme(korisnickoIme);
	}
	
	public List<ZaposleniDTORecord> pretrazi(String ime, String prezime, String email, Long odsekId) {
	    List<Zaposleni> rezultati = zaposlenRepository.pretrazi(ime, prezime, email, odsekId);
	    return mapper.map(rezultati);
	}
	
	public Zaposleni findZaposlenById(Long id){
		
		Optional<Zaposleni> z  = zaposlenRepository.findById(id);
		Zaposleni zap = z.get();
		return zap;
	}
	
	public boolean existsById(Long id) {
		return zaposlenRepository.existsById(id);
	}
	
	
	


}
