package br.com.alura.screenmatch;
//import br.com.alura.screenmatch.principal.Principal;
//import br.com.alura.screenmatch.repository.SerieRepository;
//import io.github.cdimascio.dotenv.Dotenv;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class ScreenmatchApplicationSemWEB implements CommandLineRunner {
//	@Autowired
//	private SerieRepository repositorio;
//
//	public static void main(String[] args) {
//		Dotenv dotenv = Dotenv.configure().load();
//		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
//		SpringApplication.run(ScreenmatchApplicationSemWEB.class, args);
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		Principal principal = new Principal(repositorio);
//		principal.exibeMenu();
//	}
//}