package rc.ac.singidunum.novisad.features.odsustvo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import rc.ac.singidunum.novisad.features.odsustvo.OdsustvoDTO.OdsustvoDTORecord;
import rc.ac.singidunum.novisad.generics.controller.GenericCrudController;
import rc.ac.singidunum.novisad.generics.service.CrudService;

@Controller
@RequestMapping("/api/odsustva")
public class OdsustvoController extends GenericCrudController<OdsustvoDTORecord, Odsustvo, Long> {

	@Autowired
	private OdsustvoService odsustvoService;
	
	@Override
	protected CrudService<OdsustvoDTORecord, Odsustvo, Long> getService() {
		// TODO Auto-generated method stub
		return odsustvoService;
	}
	@GetMapping("/zaposleniOdsustvo/{id}")
	public ResponseEntity<List<OdsustvoDTORecord>> getOdsustvaZaposlenog(@PathVariable Long id){
		List<OdsustvoDTORecord> odsustva = odsustvoService.getOdsustvaZaposlenog(id);
		return ResponseEntity.ok(odsustva);
	}

}
