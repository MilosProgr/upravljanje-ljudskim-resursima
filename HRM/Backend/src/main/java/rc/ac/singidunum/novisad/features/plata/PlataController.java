package rc.ac.singidunum.novisad.features.plata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import rc.ac.singidunum.novisad.features.plata.PlataDTO.PlataDTORecord;
import rc.ac.singidunum.novisad.features.zaposlen.ZaposleniService;
import rc.ac.singidunum.novisad.generics.controller.GenericCrudController;
import rc.ac.singidunum.novisad.generics.service.CrudService;

@Controller
@RequestMapping("/api/plate")
public class PlataController extends GenericCrudController<PlataDTORecord, Plata, Long> {
	@Autowired
	private PlataService plataService;
	
	@Autowired
	private ZaposleniService zaposleniService;

	@Override
	protected CrudService<PlataDTORecord, Plata, Long> getService() {
		return plataService;
	}
	
	@GetMapping("/zaposleni/{id}")
	public ResponseEntity<PlataDTORecord> getPlataZaZaposlenog(@PathVariable("id") Long id) {
		PlataDTORecord plataDTO = plataService.dobaviPlatuPrekoPravaPristupa(id);
	    if (plataDTO == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }
	    return ResponseEntity.ok(plataDTO);
	}


}
