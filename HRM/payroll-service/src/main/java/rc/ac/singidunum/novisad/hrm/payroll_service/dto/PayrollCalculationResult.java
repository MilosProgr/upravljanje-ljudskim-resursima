package rc.ac.singidunum.novisad.hrm.payroll_service.dto;

import java.math.BigDecimal;

public record PayrollCalculationResult (
		Long zaposleniId,
	    BigDecimal bruto,
	    BigDecimal neto
		) {

}
