package rc.ac.singidunum.novisad.messages.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@Configuration
public class RabbitConfig {
	
	@Value("${rabbitmq.queue.name}")
	private String name;
	
	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	
	@Value("${rabbitmq.routing.key}")
	private String routingKey;
	
	@Value("${rabbitmq.jsonqueue.name}")
	private String jsonQueue;
	
	@Value("${rabbitmq.routing.json.key}")
	private String routingJsonKey;
	
	@Bean
	public Queue queue(){
		return new Queue(name);
	}
	
	@Bean
	public Queue jsonQueue() {
		return new Queue(jsonQueue);
	}
	
	//spring bean for rabbitmq exchange 
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchange);
	}
	
	//binding izmedju queue i izmena koristeci routing key
	@Bean
	public Binding binding() {
		return BindingBuilder
				.bind(queue())
				.to(exchange())
				.with(routingKey);
		
	}
	
	//binding izmedju queue i izmena koristeci routing key ali za json
	@Bean
	public Binding jsonBinding() {
		return BindingBuilder
				.bind(jsonQueue())
				.to(exchange())
				.with(routingJsonKey);
		
	}
	
	@Bean
	public ObjectMapper objectMapper() {
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.registerModule(new JavaTimeModule());
	    return mapper;
	}
	
	@Bean
	public MessageConverter converter(ObjectMapper mapper) {
		return new Jackson2JsonMessageConverter(mapper);
	}
	
	@Bean
	public AmqpTemplate amqpTemplate(
			ConnectionFactory connectionFactory,
			MessageConverter converter
	) 
	{
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter);
		return rabbitTemplate;
	}
	
	//connectionFactory
	//RabbitTemplate
	//RabbitAdmin
	
	
}
