package rc.ac.singidunum.novisad.messages.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import rc.ac.singidunum.novisad.features.adresa.Adresa;
import rc.ac.singidunum.novisad.messages.dto.PayrollCalculationRequest;

@Service
public class RabbitMQJsonProducer {
	
	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	
	@Value("${rabbitmq.routing.json.key}")
	private String routingJsonKey;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);
	
	private RabbitTemplate rabbitTemplate;

	public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendJsonMessage(PayrollCalculationRequest p) {
		LOGGER.info(String.format( "JSON poruka poslata -> %s", p));
		rabbitTemplate.convertAndSend(exchange, routingJsonKey, p);
	}
	
}
