package rc.ac.singidunum.novisad.features.odsek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import rc.ac.singidunum.novisad.features.odsek.OdsekDTO.OdsekDTORecord;
import rc.ac.singidunum.novisad.generics.controller.GenericCrudController;
import rc.ac.singidunum.novisad.generics.service.CrudService;

@Controller
@RequestMapping("/api/odseci")
public class OdsekController extends GenericCrudController<OdsekDTORecord, Odsek, Long> {

	@Autowired
	private OdsekService odsekService;
	
	@Override
	protected CrudService<OdsekDTORecord, Odsek, Long> getService() {
		return odsekService;
	}

	@Override
	protected String getBasePath() {
		// TODO Auto-generated method stub
		return "/api/odseci";
	}

	@Override
	protected Long getEntityId(OdsekDTORecord dto) {
		// TODO Auto-generated method stub
		return dto.id();
	}

}
