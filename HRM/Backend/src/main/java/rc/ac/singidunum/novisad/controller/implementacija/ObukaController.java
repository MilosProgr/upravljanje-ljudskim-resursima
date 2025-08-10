package rc.ac.singidunum.novisad.controller.implementacija;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import rc.ac.singidunum.novisad.controller.deklaracija.GenericCrudController;
import rc.ac.singidunum.novisad.dto.ObukaDTO;
import rc.ac.singidunum.novisad.model.Obuka;
import rc.ac.singidunum.novisad.service.deklaracija.CrudService;
import rc.ac.singidunum.novisad.service.implementacija.ObukaService;

@Controller
@RequestMapping("/api/obuke")
public class ObukaController extends GenericCrudController<ObukaDTO, Obuka, Long> {
	
	@Autowired
	private ObukaService obukaService;

	@Override
	protected CrudService<ObukaDTO, Obuka, Long> getService() {
		return obukaService;
	}
	
	@RequestMapping("/me")
	public ResponseEntity<List<ObukaDTO>> getObukeZaZaposlenog(Authentication auth){
		try {
			List<ObukaDTO> obuke = obukaService.getObukaZaZaposlenog(auth.getName());
			return ResponseEntity.ok(obuke);
		} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		}
	}
}
