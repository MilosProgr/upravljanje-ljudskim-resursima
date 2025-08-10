package rc.ac.singidunum.novisad.model;

import java.time.LocalDate;

//import jakarta.persistence.CascadeType;
//import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import rc.ac.singidunum.novisad.model.base.BaseEntity;
import rc.ac.singidunum.novisad.tipovi.TipOdsustva;

@Entity
@Table(name = "odsustvo")
public class Odsustvo implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zaposleni_id")
    private Zaposleni zaposleni;

    @Enumerated(EnumType.STRING)
    private TipOdsustva tip;

    private LocalDate datumPocetka;
    private LocalDate datumKraja;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "odobrio_id",nullable = true)
    private Zaposleni odobrio;

	public Odsustvo(Long id, Zaposleni zaposleni, TipOdsustva tip, LocalDate datumPocetka, LocalDate datumKraja,
			Zaposleni odobrio) {
		super();
		this.id = id;
		this.zaposleni = zaposleni;
		this.tip = tip;
		this.datumPocetka = datumPocetka;
		this.datumKraja = datumKraja;
		this.odobrio = odobrio;
	}

	public Odsustvo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Zaposleni getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(Zaposleni zaposleni) {
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

	public Zaposleni getOdobrio() {
		return odobrio;
	}

	public void setOdobrio(Zaposleni odobrio) {
		this.odobrio = odobrio;
	}
    
    
}

