package br.com.fiap.dto;

public record DashboardStatsDTO(
        long totalFuncionarios,
        long totalFuncionariosAtivos,
        long totalEmRiscoDeBurnout,
        double mediaNivelEstresse,
        double mediaReunioes

) {}