package com.example.registration;

import com.example.registration.service.test.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;


@SpringBootApplication

public class RegistrationApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(RegistrationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
//		generator.initialize(2048);
//		KeyPair pair = generator.generateKeyPair();
//		PrivateKey privateKey = pair.getPrivate();
//		String privateKey1= Base64.getEncoder().encodeToString(privateKey.getEncoded());
//		System.out.println("private--"+privateKey1);
//		PublicKey publicKey = pair.getPublic();
//		String publicKey1=Base64.getEncoder().encodeToString(publicKey.getEncoded());
//		System.out.println("public--"+publicKey1  );

	}
}
