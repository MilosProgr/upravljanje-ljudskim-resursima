package rc.ac.singidunum.novisad.messages.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import rc.ac.singidunum.novisad.features.adresa.Adresa;
import rc.ac.singidunum.novisad.messages.dto.PayrollCalculationRequest;

@Service
public class RabbitMQJsonConsumer {
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);
	
	@RabbitListener(queues = {"${rabbitmq.jsonqueue.name}"})
	public void consumeJsonMessage(PayrollCalculationRequest p)
	{
		LOGGER.info(String.format("Primljen JSON poruka -> %s",p));
	}
}
