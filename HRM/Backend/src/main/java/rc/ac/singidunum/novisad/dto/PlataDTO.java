package rc.ac.singidunum.novisad.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PlataDTO {
	
	private Long id;
	
	private BigDecimal iznos;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate datumIsplate;
	

	public PlataDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlataDTO(Long id, LocalDate datumIsplate,BigDecimal iznos) {
		super();
		this.id = id;
		this.datumIsplate = datumIsplate;
		this.iznos = iznos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public ZaposleniDTO getZaposleni() {
//		return zaposleni;
//	}
//
//	public void setZaposleni(ZaposleniDTO zaposleni) {
//		this.zaposleni = zaposleni;
//	}

	public LocalDate getDatumIsplate() {
		return datumIsplate;
	}

	public void setDatumIsplate(LocalDate datumIsplate) {
		this.datumIsplate = datumIsplate;
	}

	public BigDecimal getIznos() {
		return iznos;
	}

	public void setIznos(BigDecimal iznos) {
		this.iznos = iznos;
	}
	
	
	
}
