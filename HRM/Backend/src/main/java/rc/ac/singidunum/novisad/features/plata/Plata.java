package rc.ac.singidunum.novisad.features.plata;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import rc.ac.singidunum.novisad.generics.base.BaseEntity;

@Entity
@Table(name = "plata")
public class Plata implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal iznos;
    
    private LocalDate datumIsplate;
	
    public Plata() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Plata(Long id,BigDecimal iznos, LocalDate datumIsplate) {
		super();
		this.id = id;
//		this.zaposleni = zaposleni;
		this.iznos = iznos;
		this.datumIsplate = datumIsplate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Zaposleni getZaposleni() {
//		return zaposleni;
//	}
//
//	public void setZaposleni(Zaposleni zaposleni) {
//		this.zaposleni = zaposleni;
//	}

	public BigDecimal getIznos() {
		return iznos;
	}

	public void setIznos(BigDecimal iznos) {
		this.iznos = iznos;
	}

	public LocalDate getDatumIsplate() {
		return datumIsplate;
	}

	public void setDatumIsplate(LocalDate datumIsplate) {
		this.datumIsplate = datumIsplate;
	}
    
    
    
    
}