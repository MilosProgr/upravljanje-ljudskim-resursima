package rc.ac.singidunum.novisad.features.obuka;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import rc.ac.singidunum.novisad.features.zaposlen.ZaposleniDTO.ZaposleniDTORecord;

public class ObukaDTO {
	
	
	public record ObukaDTORecord(
		Long id,
		String naziv,
		String opis,
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
		LocalDate datumOdrzavanja,
		ZaposleniDTORecord zaposleni
	) 
	{
		
	}
	
	
	
}
