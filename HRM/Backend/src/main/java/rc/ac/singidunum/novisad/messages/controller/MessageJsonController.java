package rc.ac.singidunum.novisad.messages.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rc.ac.singidunum.novisad.features.zaposlen.ZaposleniService;
import rc.ac.singidunum.novisad.messages.dto.PayrollCalculationRequest;
import rc.ac.singidunum.novisad.messages.dto.PayrollCalulationResult;
import rc.ac.singidunum.novisad.messages.publisher.RabbitMQJsonProducer;

@RestController
@RequestMapping("/api/v2")
public class MessageJsonController {

    private final RabbitMQJsonProducer producer;
    private final ZaposleniService zaposleniService;

    public MessageJsonController(RabbitMQJsonProducer producer,
                                 ZaposleniService zaposleniService) {
        this.producer = producer;
        this.zaposleniService = zaposleniService;
    }

    @PostMapping("/publish")
    public ResponseEntity<PayrollCalulationResult> send(
            @RequestBody PayrollCalculationRequest req) {

        if (!zaposleniService.existsById(req.zaposleniId())) {
            return ResponseEntity.badRequest().body(
                    new PayrollCalulationResult(
                            req.zaposleniId(),
                            req.period(),
                            "ZAPOSLENI_NE_POSTOJI",
                            null, null, null, null
                    )
            );
        }

        producer.sendJsonMessage(req);

        return ResponseEntity.accepted().body(
                new PayrollCalulationResult(
                        req.zaposleniId(),
                        req.period(),
                        "PROCESSING",
                        null, null, null, null
                )
        );
    }
}
