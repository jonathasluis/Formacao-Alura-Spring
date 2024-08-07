package br.com.alura.screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Episodio {
    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double avaliacao;
    private LocalDate dataLancamento;

    public Episodio(Integer numeroTempora, DadosEpisodio dadosEpisodio) {
        this.temporada = numeroTempora;
        this.titulo = dadosEpisodio.titulo();
        this.numeroEpisodio = dadosEpisodio.numero();

        try {
            this.avaliacao = Double.parseDouble(dadosEpisodio.avaliacao());
            this.dataLancamento = LocalDate.parse(dadosEpisodio.dataLancamento());
        } catch (NumberFormatException e) {
            this.avaliacao = 0.0;
        } catch (DateTimeParseException ex) {
            this.dataLancamento = null;
        }


    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    @Override
    public String toString() {
        return
                "temporada=" + temporada +
                        ", titulo='" + titulo + '\'' +
                        ", numeroEpisodio=" + numeroEpisodio +
                        ", avaliacao=" + avaliacao +
                        ", dataLancamento=" + dataLancamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Episodio episodio = (Episodio) o;
        return Objects.equals(temporada, episodio.temporada)
                && Objects.equals(titulo, episodio.titulo)
                && Objects.equals(numeroEpisodio, episodio.numeroEpisodio)
                && Objects.equals(avaliacao, episodio.avaliacao)
                && Objects.equals(dataLancamento, episodio.dataLancamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(temporada, titulo, numeroEpisodio, avaliacao, dataLancamento);
    }
}
