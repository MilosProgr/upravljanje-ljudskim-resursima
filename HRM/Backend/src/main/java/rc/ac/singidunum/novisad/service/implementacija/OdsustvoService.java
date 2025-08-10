package rc.ac.singidunum.novisad.service.implementacija;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import rc.ac.singidunum.novisad.dto.OdsustvoDTO;
import rc.ac.singidunum.novisad.dto.ZaposleniDTO;
import rc.ac.singidunum.novisad.mapper.deklaracija.Mapper;
import rc.ac.singidunum.novisad.mapper.implementacija.OdsustvoMapper;
import rc.ac.singidunum.novisad.mapper.implementacija.ZaposleniMapper;
import rc.ac.singidunum.novisad.model.Odsustvo;
import rc.ac.singidunum.novisad.model.Zaposleni;
import rc.ac.singidunum.novisad.repository.OdsustvoRepository;
import rc.ac.singidunum.novisad.service.deklaracija.GenericCrudService;
import rc.ac.singidunum.novisad.tipovi.StatusZaposlenja;

@Service
public class OdsustvoService extends GenericCrudService<OdsustvoDTO, Odsustvo, Long> {

	@Autowired
	private ZaposleniService zaposleniService;
	
	@Autowired
	private ZaposleniMapper zaposleniMapper;
	
	@Autowired
	private OdsustvoMapper odsustvoMapper;
	
	@Autowired
	private OdsustvoRepository odsustvoRepository;
	
	protected OdsustvoService(JpaRepository<Odsustvo, Long> repository, Mapper<OdsustvoDTO, Odsustvo> mapper) {
		super(repository, mapper);
		this.odsustvoMapper = (OdsustvoMapper) mapper;
		
	}
	
	public OdsustvoDTO update(Odsustvo o) {		
	        	
	        	Zaposleni z = zaposleniService.findZaposlenById(o.getZaposleni().getId());
	            StatusZaposlenja status = z.getStatusZaposlenja();
	            z.setStatusZaposlenja(status.NA_ODMORU);
	            zaposleniService.update(z);

		
		return super.update(o);
	}
	
	public List<OdsustvoDTO> getOdsustvaZaposlenog(Long id){
		Zaposleni z = zaposleniService.findZaposlenById(id);
		List<Odsustvo> odsustvaZaposlenih = odsustvoRepository.findByZaposleniId(z.getId());
		return odsustvoMapper.map(odsustvaZaposlenih);
	}
	

	
	
	
	

}
