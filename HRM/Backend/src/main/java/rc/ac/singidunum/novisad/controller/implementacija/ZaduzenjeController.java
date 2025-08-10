package rc.ac.singidunum.novisad.controller.implementacija;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import rc.ac.singidunum.novisad.controller.deklaracija.GenericCrudController;
import rc.ac.singidunum.novisad.dto.ZaduzenjeDTO;
import rc.ac.singidunum.novisad.model.Zaduzenje;
import rc.ac.singidunum.novisad.service.deklaracija.CrudService;
import rc.ac.singidunum.novisad.service.implementacija.ZaduzenjeService;

@Controller
@RequestMapping("/api/zaduzenje")
public class ZaduzenjeController extends GenericCrudController<ZaduzenjeDTO, Zaduzenje, Long> {

	@Autowired
	private ZaduzenjeService zaduzenjeService;
	
	@Override
	protected CrudService<ZaduzenjeDTO, Zaduzenje, Long> getService() {
		return zaduzenjeService;
	}
	
	@RequestMapping("/me")
	public ResponseEntity<List<ZaduzenjeDTO>> getZaduzenjeZaZaposlenog(Authentication auth){
		try {
			List<ZaduzenjeDTO> zaduzenja = zaduzenjeService.getZaduzenjeZaZaposlenog(auth.getName());
		    return ResponseEntity.ok(zaduzenja);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
