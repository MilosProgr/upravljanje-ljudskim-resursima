package rc.ac.singidunum.novisad.hrm.payroll_service.listener;

import java.math.BigDecimal;
import java.time.YearMonth;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import rc.ac.singidunum.novisad.hrm.payroll_service.dto.PayrollCalculationRequest;
import rc.ac.singidunum.novisad.hrm.payroll_service.dto.PayrollCalculationResult;
import rc.ac.singidunum.novisad.hrm.payroll_service.entity.Payroll;
import rc.ac.singidunum.novisad.hrm.payroll_service.repository.PayrollRepository;

@Service
public class PayrollCalculationListener {
	
	@Value("${rabbitmq.exchange.name}")
	private String exchange;

	@Value("${rabbitmq.routing.payroll.result.key}")
	private String resultRoutingKey;
	
	private final PayrollRepository repository;
	
	private final RabbitTemplate rabbitTemplate;

    public PayrollCalculationListener(RabbitTemplate rabbitTemplate,PayrollRepository repository) {
    	this.rabbitTemplate = rabbitTemplate;
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
        payroll.setPeriod(req.period()); // primer, može iz DTO
        payroll.setBruto(bruto);
        payroll.setNeto(neto);
        payroll.setPorez(porez);
        payroll.setDoprinosi(doprinosi);

        repository.save(payroll);
        
        PayrollCalculationResult result =
                new PayrollCalculationResult(
                        req.zaposleniId(),
                        req.period(),
                        "SUCCESS",
                        bruto,
                        neto,
                        porez,
                        doprinosi
                );
        rabbitTemplate.convertAndSend(exchange, resultRoutingKey, result);
    }
}
