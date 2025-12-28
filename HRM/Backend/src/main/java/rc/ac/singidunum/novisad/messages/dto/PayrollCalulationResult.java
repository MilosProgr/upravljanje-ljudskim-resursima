package rc.ac.singidunum.novisad.messages.dto;

import java.math.BigDecimal;
import java.time.YearMonth;

public record PayrollCalulationResult(
		Long zaposleniId,
        YearMonth period,
        String status,
        BigDecimal brutoPlata,
        BigDecimal neto,
        BigDecimal porez,
	    BigDecimal doprinosi
	) {

}
