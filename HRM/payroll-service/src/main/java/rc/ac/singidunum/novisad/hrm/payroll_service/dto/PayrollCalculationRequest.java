package rc.ac.singidunum.novisad.hrm.payroll_service.dto;

import java.math.BigDecimal;
import java.time.YearMonth;

public record PayrollCalculationRequest (
		Long zaposleniId,
	    YearMonth period,
	    String status,
	    BigDecimal osnovnaPlata,
	    Integer brojRadnihSati,
	    Integer brojDanaOdsustva,
	    Integer brojPrekovremenihSati
		){
}
