package br.com.addr;

import br.com.addr.spark.SparkBoot;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("br.com.addr")
@Configuration
public class AddressCrudApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AddressCrudApplication.class);
        SparkBoot boot = context.getBean(SparkBoot.class);
        boot.bootApplication();
    }
}
