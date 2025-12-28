package rc.ac.singidunum.novisad.messages.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import rc.ac.singidunum.novisad.messages.dto.PayrollCalulationResult;

@Service
public class PayrollResultListener {

    @RabbitListener(queues = "${rabbitmq.payroll.result.queue}")
    public void handleResult(PayrollCalulationResult result) {
        // ovde možeš:
        // - update status u bazi
        // - obavestiti frontend preko WebSocket/REST
        System.out.println("Primljen payroll result: " + result);
    }
}
