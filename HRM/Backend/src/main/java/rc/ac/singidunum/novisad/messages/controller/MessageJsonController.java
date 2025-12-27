package rc.ac.singidunum.novisad.messages.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rc.ac.singidunum.novisad.messages.dto.PayrollCalculationRequest;
import rc.ac.singidunum.novisad.messages.dto.PayrollCalulationResult;
import rc.ac.singidunum.novisad.messages.publisher.RabbitMQJsonProducer;

@RestController
@RequestMapping("/api/v2")
public class MessageJsonController {
	
	private RabbitMQJsonProducer jsonProducer;

	public MessageJsonController(RabbitMQJsonProducer jsonProducer) {
		this.jsonProducer = jsonProducer;
	}
	@PostMapping("/publish")
	public ResponseEntity<PayrollCalulationResult> sendJsonMessage(@RequestBody PayrollCalculationRequest p){
		jsonProducer.sendJsonMessage(p);
//		return ResponseEntity.ok("JSON poruka poslata u RabbitMQ ...");
		PayrollCalulationResult response =
	            new PayrollCalulationResult(
	                    p.zaposleniId(),
	                    p.period(),
	                    "PROCESSING"
	            );

	    return ResponseEntity.accepted().body(response);
	}
	
}
