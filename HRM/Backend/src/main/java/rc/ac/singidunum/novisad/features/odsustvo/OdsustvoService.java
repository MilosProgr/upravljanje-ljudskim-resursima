package rc.ac.singidunum.novisad.features.odsustvo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Service;

import rc.ac.singidunum.novisad.features.odsustvo.OdsustvoDTO.OdsustvoDTORecord;
import rc.ac.singidunum.novisad.features.zaposlen.Zaposleni;

import rc.ac.singidunum.novisad.features.zaposlen.ZaposleniService;
import rc.ac.singidunum.novisad.generics.mapper.Mapper;
import rc.ac.singidunum.novisad.generics.service.GenericCrudService;
import rc.ac.singidunum.novisad.types.StatusZaposlenja;

@Service
public class OdsustvoService extends GenericCrudService<OdsustvoDTORecord, Odsustvo, Long> {

	@Autowired
	private ZaposleniService zaposleniService;
	
//	@Autowired
//	private ZaposleniMapper zaposleniMapper;
	
	@Autowired
	private OdsustvoMapper odsustvoMapper;
	
	@Autowired
	private OdsustvoRepository odsustvoRepository;
	
	protected OdsustvoService(JpaRepository<Odsustvo, Long> repository, Mapper<OdsustvoDTORecord, Odsustvo> mapper) {
		super(repository, mapper);
		this.odsustvoMapper = (OdsustvoMapper) mapper;
		
	}
	
	public OdsustvoDTORecord update(Odsustvo o) {		
	        	
	        	Zaposleni z = zaposleniService.findZaposlenById(o.getZaposleni().getId());
	            StatusZaposlenja status = z.getStatusZaposlenja();
	            z.setStatusZaposlenja(status.NA_ODMORU);
	            zaposleniService.update(z);

		
		return super.update(o);
	}
	
	public List<OdsustvoDTORecord> getOdsustvaZaposlenog(Long id){
		Zaposleni z = zaposleniService.findZaposlenById(id);
		List<Odsustvo> odsustvaZaposlenih = odsustvoRepository.findByZaposleniId(z.getId());
		return odsustvoMapper.map(odsustvaZaposlenih);
	}
	

	
	
	
	

}
