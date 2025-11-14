package br.com.fiap.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "TB_SLOWRUSH_REGISTRO_ROTINA")
public class RegistroRotina {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "registro_seq")
    @SequenceGenerator(name = "registro_seq", sequenceName = "SQ_T_REGISTRO_ROTINA", allocationSize = 1)
    private Long id;

    private LocalDate dataRegistro;

    // Pergunta 1: Carga Horária
    private double horasTrabalhadas;

    // Pergunta 2: Intensidade (NOVO CAMPO)
    private int numeroReunioes;

    // Pergunta 3: Sentimento (1 a 5 - Emoji)
    private int nivelEstresse;

    private String descricao; // Pode manter como opcional ou remover

    public RegistroRotina() {}

    // Construtor atualizado
    public RegistroRotina(LocalDate dataRegistro, double horasTrabalhadas, int numeroReunioes, int nivelEstresse) {
        this.dataRegistro = dataRegistro;
        this.horasTrabalhadas = horasTrabalhadas;
        this.numeroReunioes = numeroReunioes;
        this.nivelEstresse = nivelEstresse;
    }

    // --- GETTERS E SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDataRegistro() { return dataRegistro; }
    public void setDataRegistro(LocalDate dataRegistro) { this.dataRegistro = dataRegistro; }

    public double getHorasTrabalhadas() { return horasTrabalhadas; }
    public void setHorasTrabalhadas(double horasTrabalhadas) { this.horasTrabalhadas = horasTrabalhadas; }

    public int getNumeroReunioes() { return numeroReunioes; } // Novo Getter
    public void setNumeroReunioes(int numeroReunioes) { this.numeroReunioes = numeroReunioes; } // Novo Setter

    public int getNivelEstresse() { return nivelEstresse; }
    public void setNivelEstresse(int nivelEstresse) { this.nivelEstresse = nivelEstresse; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}