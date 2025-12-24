package rc.ac.singidunum.novisad.hrm.payroll_service.dto;

import java.math.BigDecimal;

public class PlataDTO {
	public record PayrollCalculationRequest(
		    Long zaposleniId,
		    BigDecimal osnovnaPlata,
		    long ukupnoRadnihSati,
		    long odsustvoDana
		) {}
	
	public record PayrollCalculationResult(
		    Long zaposleniId,
		    BigDecimal bruto,
		    BigDecimal neto
		) {}
}
