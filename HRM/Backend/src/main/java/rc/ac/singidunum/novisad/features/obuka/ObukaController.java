package rc.ac.singidunum.novisad.features.obuka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import rc.ac.singidunum.novisad.features.obuka.ObukaDTO.ObukaDTORecord;
import rc.ac.singidunum.novisad.generics.controller.GenericCrudController;
import rc.ac.singidunum.novisad.generics.service.CrudService;

@Controller
@RequestMapping("/api/obuke")
public class ObukaController extends GenericCrudController<ObukaDTORecord, Obuka, Long> {
	
	@Autowired
	private ObukaService obukaService;

	@Override
	protected CrudService<ObukaDTORecord, Obuka, Long> getService() {
		return obukaService;
	}
	
	@RequestMapping("/me")
	public ResponseEntity<List<ObukaDTORecord>> getObukeZaZaposlenog(Authentication auth){
		try {
			List<ObukaDTORecord> obuke = obukaService.getObukaZaZaposlenog(auth.getName());
			return ResponseEntity.ok(obuke);
		} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		}
	}
}
