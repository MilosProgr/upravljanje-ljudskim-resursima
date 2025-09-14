package rc.ac.singidunum.novisad.features.odsustvo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import rc.ac.singidunum.novisad.features.zaposlen.ZaposleniDTO;
import rc.ac.singidunum.novisad.types.TipOdsustva;

public class OdsustvoDTO {
	
	
	public record OdsustvoDTORecord(Long id, ZaposleniDTO zaposleni, TipOdsustva tip,@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") LocalDate datumPocetka,@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") LocalDate datumKraja,
			ZaposleniDTO odobrio) {}
	
	
}
