package idusw.springboot.boardjhm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  //(exclude = DataSourceAutoConfiguration.class)
public class BoardJhmApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardJhmApplication.class, args);
    }

}
