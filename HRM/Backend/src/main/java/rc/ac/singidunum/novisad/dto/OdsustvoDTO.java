package rc.ac.singidunum.novisad.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import rc.ac.singidunum.novisad.tipovi.TipOdsustva;

public class OdsustvoDTO {
	private Long id;
	
	private ZaposleniDTO zaposleni;
	private TipOdsustva tip;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate datumPocetka;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate datumKraja;
	
	private ZaposleniDTO odobrio;

	public OdsustvoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OdsustvoDTO(Long id, ZaposleniDTO zaposleni, TipOdsustva tip, LocalDate datumPocetka, LocalDate datumKraja,
			ZaposleniDTO odobrio) {
		super();
		this.id = id;
		this.zaposleni = zaposleni;
		this.tip = tip;
		this.datumPocetka = datumPocetka;
		this.datumKraja = datumKraja;
		this.odobrio = odobrio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ZaposleniDTO getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(ZaposleniDTO zaposleni) {
		this.zaposleni = zaposleni;
	}

	public TipOdsustva getTip() {
		return tip;
	}

	public void setTip(TipOdsustva tip) {
		this.tip = tip;
	}

	public LocalDate getDatumPocetka() {
		return datumPocetka;
	}

	public void setDatumPocetka(LocalDate datumPocetka) {
		this.datumPocetka = datumPocetka;
	}

	public LocalDate getDatumKraja() {
		return datumKraja;
	}

	public void setDatumKraja(LocalDate datumKraja) {
		this.datumKraja = datumKraja;
	}

	public ZaposleniDTO getOdobrio() {
		return odobrio;
	}

	public void setOdobrio(ZaposleniDTO odobrio) {
		this.odobrio = odobrio;
	}
	
	
}
