package rc.ac.singidunum.novisad.controller.implementacija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import rc.ac.singidunum.novisad.controller.deklaracija.GenericCrudController;
import rc.ac.singidunum.novisad.dto.OdsekDTO;
import rc.ac.singidunum.novisad.model.Odsek;
import rc.ac.singidunum.novisad.service.deklaracija.CrudService;
import rc.ac.singidunum.novisad.service.implementacija.OdsekService;

@Controller
@RequestMapping("/api/odseci")
public class OdsekController extends GenericCrudController<OdsekDTO, Odsek, Long> {

	@Autowired
	private OdsekService odsekService;
	
	@Override
	protected CrudService<OdsekDTO, Odsek, Long> getService() {
		return odsekService;
	}

}
