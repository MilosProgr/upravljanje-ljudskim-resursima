package rc.ac.singidunum.novisad.hrm.payroll_service.dto;

import java.math.BigDecimal;

public record PayrollCalculationRequest (
		Long zaposleniId,
	    BigDecimal osnovnaPlata,
	    long ukupnoRadnihSati,
	    long odsustvoDana
		){

}
