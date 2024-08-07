package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
import io.github.cdimascio.dotenv.Dotenv;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=" + Dotenv.load().get("API_KEY");

    public void exibeMenu() {
        System.out.println("Digite o nome da série: ");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);
        List<DadosTemporada> temporadas = new ArrayList<>();
        for (int i = 1; i <= dados.totalTemporadas(); i++) {
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&Season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        //        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
        //                                                       .flatMap(t -> t.episodios().stream())
        //                                                       .collect(Collectors.toList());
        //        dadosEpisodios.stream()
        //                      .filter(e -> !e.avaliacao().equalsIgnoreCase("n/a"))
        //                      .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
        //                      .limit(5)
        //                      .forEach(System.out::println);
        List<Episodio> episodios = temporadas.stream()
                                             .flatMap(t -> t.episodios().stream()
                                                            .map(d -> new Episodio(t.numero(), d)))
                                             .collect(Collectors.toList());
        episodios.forEach(System.out::println);
        //        System.out.println("Trecho titulo");
        //        var trechoTitulo = leitura.nextLine();
        //        Optional<Episodio> ep = episodios.stream()
        //                                         .filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase()))
        //                                         .findFirst();
        //        if (ep.isPresent()) {
        //            System.out.println(ep.get());
        //        } else {
        //            System.out.println("Episódio não encontrado");
        //        }
        //        System.out.println("A partir de que ano voce deseja ver os episodios?");
        //
        //        var ano = leitura.nextInt();
        //        leitura.nextLine();
        //
        //        LocalDate dataBusca = LocalDate.of(ano, 1, 1);
        //
        //        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //        episodios.stream()
        //                 .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
        //                 .forEach(e -> System.out.println(
        //                         "Temporada: " + e.getTemporada() +
        //                                 " Episodio: " + e.getTitulo() +
        //                                 " Data de lançamento: " + e.getDataLancamento().format(formatter)));
        Map<Integer, Double> avaliacoesPorTemporada = episodios.stream()
                                                               .filter(e -> e.getAvaliacao() > 0.0)
                                                               .collect(Collectors.groupingBy(Episodio::getTemporada,
                                                                       Collectors.averagingDouble(Episodio::getAvaliacao)));
        System.out.println(avaliacoesPorTemporada);
        DoubleSummaryStatistics est = episodios.stream()
                                               .filter(e -> e.getAvaliacao() > 0.0)
                                               .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));
        System.out.println(est.getAverage());
    }
}
