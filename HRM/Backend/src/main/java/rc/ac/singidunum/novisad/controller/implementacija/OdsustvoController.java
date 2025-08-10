package rc.ac.singidunum.novisad.controller.implementacija;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import rc.ac.singidunum.novisad.controller.deklaracija.GenericCrudController;
import rc.ac.singidunum.novisad.dto.OdsustvoDTO;
import rc.ac.singidunum.novisad.model.Odsustvo;
import rc.ac.singidunum.novisad.service.deklaracija.CrudService;
import rc.ac.singidunum.novisad.service.implementacija.OdsustvoService;

@Controller
@RequestMapping("/api/odsustva")
public class OdsustvoController extends GenericCrudController<OdsustvoDTO, Odsustvo, Long> {

	@Autowired
	private OdsustvoService odsustvoService;
	
	@Override
	protected CrudService<OdsustvoDTO, Odsustvo, Long> getService() {
		// TODO Auto-generated method stub
		return odsustvoService;
	}
	@GetMapping("/zaposleniOdsustvo/{id}")
	public ResponseEntity<List<OdsustvoDTO>> getOdsustvaZaposlenog(@PathVariable Long id){
		List<OdsustvoDTO> odsustva = odsustvoService.getOdsustvaZaposlenog(id);
		return ResponseEntity.ok(odsustva);
	}

}
