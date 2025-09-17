package rc.ac.singidunum.novisad.features.zaduzenje;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import rc.ac.singidunum.novisad.features.zaduzenje.ZaduzenjeDTO.ZaduzenjeDTORecord;
import rc.ac.singidunum.novisad.generics.controller.GenericCrudController;
import rc.ac.singidunum.novisad.generics.service.CrudService;

@Controller
@RequestMapping("/api/zaduzenje")
public class ZaduzenjeController extends GenericCrudController<ZaduzenjeDTORecord, Zaduzenje, Long> {

	@Autowired
	private ZaduzenjeService zaduzenjeService;
	
	@Override
	protected CrudService<ZaduzenjeDTORecord, Zaduzenje, Long> getService() {
		return zaduzenjeService;
	}
	
	@RequestMapping("/me")
	public ResponseEntity<List<ZaduzenjeDTORecord>> getZaduzenjeZaZaposlenog(Authentication auth){
		try {
			List<ZaduzenjeDTORecord> zaduzenja = zaduzenjeService.getZaduzenjeZaZaposlenog(auth.getName());
		    return ResponseEntity.ok(zaduzenja);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@Override
	protected String getBasePath() {
		// TODO Auto-generated method stub
		return "/api/zaduzenje";
	}

	@Override
	protected Long getEntityId(ZaduzenjeDTORecord dto) {
		// TODO Auto-generated method stub
		return dto.id();
	}

}
