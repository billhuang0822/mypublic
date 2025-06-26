package com.tsb;

import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.tsb.logging.EnableLog;
import com.tsb.logging.LogAspectProperties;

@SpringBootApplication
@EnableConfigurationProperties(LogAspectProperties.class)
@EnableLog(
    maskRequestFieldFormats = {"user.password=default"},
    maskResponseFieldFormats = {"account.cardNumber=creditCard"},
    maskFormats = {"email", "mobile"}
)
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            User user = new User("Tom", "12345678", "tom@gmail.com", "0912345678");
            Account account = new Account("4000123412345678");
            System.out.println(new DemoApplication().handleRequest(user, account));
        };
    }

    @EnableLog
    public Map<String, Object> handleRequest(User user, Account account) {
        return Map.of(
            "user", user,
            "account", account,
            "status", "OK"
        );
    }

    public static class User {
        public String name;
        public String password;
        public String email;
        public String phone;

        public User(String name, String password, String email, String phone) {
            this.name = name;
            this.password = password;
            this.email = email;
            this.phone = phone;
        }
    }

    public static class Account {
        public String cardNumber;

        public Account(String cardNumber) {
            this.cardNumber = cardNumber;
        }
    }
}