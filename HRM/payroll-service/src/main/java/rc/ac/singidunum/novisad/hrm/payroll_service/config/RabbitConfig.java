package rc.ac.singidunum.novisad.hrm.payroll_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // ===== Exchange =====
    public static final String EXCHANGE = "hrm-exchange";

    // ===== Queues =====
    public static final String PAYROLL_REQUEST_QUEUE = "payroll.request.queue";
    public static final String PAYROLL_RESULT_QUEUE = "payroll.result.queue";

    // ===== Routing keys =====
    public static final String PAYROLL_REQUEST_KEY = "payroll.calculate";
    public static final String PAYROLL_RESULT_KEY = "payroll.result";

    // ===== Exchange bean =====
    @Bean
    public DirectExchange hrmExchange() {
        return new DirectExchange(EXCHANGE);
    }

    // ===== Queues beans =====
    @Bean
    public Queue payrollRequestQueue() {
        return new Queue(PAYROLL_REQUEST_QUEUE, true);
    }

    @Bean
    public Queue payrollResultQueue() {
        return new Queue(PAYROLL_RESULT_QUEUE, true);
    }

    // ===== Bindings =====
    @Bean
    public Binding payrollRequestBinding() {
        return BindingBuilder
                .bind(payrollRequestQueue())
                .to(hrmExchange())
                .with(PAYROLL_REQUEST_KEY);
    }

    @Bean
    public Binding payrollResultBinding() {
        return BindingBuilder
                .bind(payrollResultQueue())
                .to(hrmExchange())
                .with(PAYROLL_RESULT_KEY);
    }

    // ===== JSON message converter =====
    @Bean
    public Jackson2JsonMessageConverter  messageConverter() {
        return new Jackson2JsonMessageConverter();

    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
