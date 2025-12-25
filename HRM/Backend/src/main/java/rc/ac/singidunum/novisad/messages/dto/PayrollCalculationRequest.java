package rc.ac.singidunum.novisad.messages.dto;

import java.math.BigDecimal;
import java.time.YearMonth;

public record PayrollCalculationRequest(
		Long zaposleniId,
	    YearMonth period,
	    BigDecimal osnovnaPlata,
	    Integer brojRadnihSati,
	    Integer brojDanaOdsustva,
	    Integer brojPrekovremenihSati
) {
	
}
