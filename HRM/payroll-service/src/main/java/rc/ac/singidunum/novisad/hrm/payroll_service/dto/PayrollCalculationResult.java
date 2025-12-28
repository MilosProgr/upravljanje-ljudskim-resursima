package rc.ac.singidunum.novisad.hrm.payroll_service.dto;

import java.math.BigDecimal;
import java.time.YearMonth;

public record PayrollCalculationResult (
		Long zaposleniId,
        YearMonth period,
        String status,
        BigDecimal brutoPlata,
        BigDecimal neto,
        BigDecimal porez,
        BigDecimal doprinosi
		) {

}
