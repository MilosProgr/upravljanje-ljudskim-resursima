package rc.ac.singidunum.novisad.features.projekat;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProjekatDTO {
	
	public record ProjekatDTORecord(
			
			 Long id,
		     String naziv,
		     String opis,
		     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
		     LocalDate datumPocetka,
		     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
		     LocalDate datumKraja
			) {}
	
	
	
}
