package br.com.fiap.service;

import br.com.fiap.dto.FeedbackCheckOutDTO; // Importe o DTO novo
import br.com.fiap.model.RegistroRotina;
import br.com.fiap.repository.RegistroRotinaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class RegistroRotinaService {

    @Inject
    private RegistroRotinaRepository registroRotinaRepository;

    @Transactional
    public FeedbackCheckOutDTO realizarCheckOut(RegistroRotina registro) {
        if (registro.getDataRegistro() == null) {
            registro.setDataRegistro(LocalDate.now());
        }

        registroRotinaRepository.persist(registro);

        boolean nivelCritico = registro.getNivelEstresse() >= 4 || registro.getHorasTrabalhadas() > 10.0;

        String mensagem;
        if (nivelCritico) {
            mensagem = "Opa! Detectamos um dia pesado. Sua meta agora é Desconexão Total. Evite o Slack até amanhã!";
        } else {
            mensagem = "Check-out realizado com sucesso. Ótimo trabalho hoje! Aproveite seu descanso.";
        }

        return new FeedbackCheckOutDTO(registro.getId(), nivelCritico, mensagem);
    }

    public List<RegistroRotina> getAll() {
        return registroRotinaRepository.listAll();
    }

    public RegistroRotina getById(Long id) {
        return registroRotinaRepository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado: " + id));
    }

    @Transactional
    public void delete(Long id) {
        registroRotinaRepository.deleteById(id);
    }
}