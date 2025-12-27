package rc.ac.singidunum.novisad.hrm.payroll_service.entity;

import java.math.BigDecimal;
import java.time.YearMonth;


import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "payroll",
uniqueConstraints = {@UniqueConstraint(columnNames = {"zaposleniId", "period"})})
public class Payroll {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
    private Long zaposleniId;

    @Column(nullable = false)
    @Convert(converter = ConvertYear.class)
    private YearMonth period;

    @Column(nullable = false)
    private BigDecimal bruto;

    @Column(nullable = false)
    private BigDecimal neto;

    @Column(nullable = false)
    private BigDecimal porez;

    @Column(nullable = false)
    private BigDecimal doprinosi;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getZaposleniId() {
		return zaposleniId;
	}

	public void setZaposleniId(Long zaposleniId) {
		this.zaposleniId = zaposleniId;
	}

	public YearMonth getPeriod() {
		return period;
	}

	public void setPeriod(YearMonth period) {
		this.period = period;
	}

	public BigDecimal getBruto() {
		return bruto;
	}

	public void setBruto(BigDecimal bruto) {
		this.bruto = bruto;
	}

	public BigDecimal getNeto() {
		return neto;
	}

	public void setNeto(BigDecimal neto) {
		this.neto = neto;
	}

	public BigDecimal getPorez() {
		return porez;
	}

	public void setPorez(BigDecimal porez) {
		this.porez = porez;
	}

	public BigDecimal getDoprinosi() {
		return doprinosi;
	}

	public void setDoprinosi(BigDecimal doprinosi) {
		this.doprinosi = doprinosi;
	}
    
    

}
