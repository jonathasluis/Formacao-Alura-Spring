package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Dotenv dotenv = Dotenv.load(); // Carrega o arquivo .env na raiz do projeto
		String minhaVariavel = dotenv.get("API_KEY");
		ConsumoApi consumoApi = new ConsumoApi();
		String url = "http://www.omdbapi.com/?apikey="+minhaVariavel+"&t=Game+of+Thrones";
		System.out.println(url);
		String json = consumoApi.obterDados(url);
		System.out.println(json );
		ConverteDados converteDados = new ConverteDados();
		DadosSerie dados = converteDados.obterDados(json, DadosSerie.class);
		System.out.println(dados);
	}
}
