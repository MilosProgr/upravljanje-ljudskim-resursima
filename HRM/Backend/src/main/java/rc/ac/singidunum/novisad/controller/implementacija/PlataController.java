package rc.ac.singidunum.novisad.controller.implementacija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import rc.ac.singidunum.novisad.controller.deklaracija.GenericCrudController;
import rc.ac.singidunum.novisad.dto.PlataDTO;
import rc.ac.singidunum.novisad.model.Plata;
import rc.ac.singidunum.novisad.service.deklaracija.CrudService;
import rc.ac.singidunum.novisad.service.implementacija.PlataService;
import rc.ac.singidunum.novisad.service.implementacija.ZaposleniService;

@Controller
@RequestMapping("/api/plate")
public class PlataController extends GenericCrudController<PlataDTO, Plata, Long> {
	@Autowired
	private PlataService plataService;
	
	@Autowired
	private ZaposleniService zaposleniService;

	@Override
	protected CrudService<PlataDTO, Plata, Long> getService() {
		return plataService;
	}
	
	@GetMapping("/zaposleni/{id}")
	public ResponseEntity<PlataDTO> getPlataZaZaposlenog(@PathVariable("id") Long id) {
	    PlataDTO plataDTO = plataService.dobaviPlatuPrekoPravaPristupa(id);
	    if (plataDTO == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }
	    return ResponseEntity.ok(plataDTO);
	}


}
