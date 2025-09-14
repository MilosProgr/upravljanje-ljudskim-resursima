package rc.ac.singidunum.novisad.features.plata;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PlataDTO {
	
	
	public record PlataDTORecord(Long id, 
		    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
			LocalDate datumIsplate,
			BigDecimal iznos) {}
	
	
	
}
