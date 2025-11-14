package br.com.fiap.dto;

public record FeedbackCheckOutDTO(
        Long idRegistro,
        boolean alertaCritico,
        String mensagem
) {}