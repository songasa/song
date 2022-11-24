import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
//@ComponentScan(basePackages = {"com"})
@ComponentScan("com")
//@Configuration()
public class start {
    public static void main(String[] args) {
        SpringApplication.run(start.class,args);
    }
}
