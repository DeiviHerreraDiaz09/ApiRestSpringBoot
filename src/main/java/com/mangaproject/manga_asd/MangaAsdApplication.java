package com.mangaproject.manga_asd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.mangaproject")
public class MangaAsdApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangaAsdApplication.class, args);
	}

}
