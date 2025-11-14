package br.com.fiap.service;

import br.com.fiap.dto.DashboardStatsDTO;
import br.com.fiap.repository.FuncionarioRepository;
import br.com.fiap.repository.RegistroRotinaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Optional;

@ApplicationScoped
public class DashboardService {

    @Inject
    FuncionarioRepository funcionarioRepository;

    @Inject
    RegistroRotinaRepository registroRotinaRepository;

    public DashboardStatsDTO getStats() {

        // 1. Totais de Funcionários
        long totalFuncionarios = funcionarioRepository.count();
        long totalAtivos = funcionarioRepository.count("ativo = true");
        long totalEmRisco = funcionarioRepository.count("tentativasFalhas >= 3");

        // 2. Média de Estresse
        Optional<Double> media = registroRotinaRepository
                .find("SELECT AVG(nivelEstresse) FROM RegistroRotina")
                .project(Double.class)
                .firstResultOptional();
        double mediaEstresse = media.orElse(0.0);

        // 3. Média de Reuniões (CORRIGIDO: MOVIDO PARA DENTRO DO MÉTODO)
        Optional<Double> mediaReunioesOpt = registroRotinaRepository
                .find("SELECT AVG(numeroReunioes) FROM RegistroRotina")
                .project(Double.class)
                .firstResultOptional();
        double mediaReunioes = mediaReunioesOpt.orElse(0.0);

        // 4. Retorno (Agora ele enxerga a variável mediaReunioes)
        return new DashboardStatsDTO(
                totalFuncionarios,
                totalAtivos,
                totalEmRisco,
                mediaEstresse,
                mediaReunioes // <--- Agora funciona!
        );
    }
}