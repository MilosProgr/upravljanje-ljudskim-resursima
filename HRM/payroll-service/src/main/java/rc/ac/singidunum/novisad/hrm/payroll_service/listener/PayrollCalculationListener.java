package rc.ac.singidunum.novisad.hrm.payroll_service.listener;

import java.math.BigDecimal;
import java.time.YearMonth;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import rc.ac.singidunum.novisad.hrm.payroll_service.dto.PayrollCalculationRequest;
import rc.ac.singidunum.novisad.hrm.payroll_service.entity.Payroll;
import rc.ac.singidunum.novisad.hrm.payroll_service.repository.PayrollRepository;

@Service
public class PayrollCalculationListener {
	private final PayrollRepository repository;

    public PayrollCalculationListener(PayrollRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = "${rabbitmq.payroll.queue}")
    public void handle(PayrollCalculationRequest req) {

        BigDecimal bruto = req.osnovnaPlata();
        BigDecimal porez = bruto.multiply(new BigDecimal("0.1"));
        BigDecimal doprinosi = bruto.multiply(new BigDecimal("0.2"));
        BigDecimal neto = bruto.subtract(porez).subtract(doprinosi);

        Payroll payroll = new Payroll();
        payroll.setZaposleniId(req.zaposleniId());
        payroll.setPeriod(YearMonth.now()); // primer, može iz DTO
        payroll.setBruto(bruto);
        payroll.setNeto(neto);
        payroll.setPorez(porez);
        payroll.setDoprinosi(doprinosi);

        repository.save(payroll);
    }
}
