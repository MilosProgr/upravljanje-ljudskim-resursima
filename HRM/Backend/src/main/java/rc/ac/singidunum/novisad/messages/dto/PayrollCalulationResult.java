package rc.ac.singidunum.novisad.messages.dto;

import java.math.BigDecimal;
import java.time.YearMonth;

public record PayrollCalulationResult(
		Long zaposleniId,
        YearMonth period,
        BigDecimal brutoPlata,
        BigDecimal netoPlata,
        BigDecimal porez,
        BigDecimal doprinosi
	) {

}
