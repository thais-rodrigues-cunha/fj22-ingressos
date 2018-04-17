package br.com.caelum.ingresso.validacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessao {
	private List <Sessao> sessoesDaSala;
	
	public GerenciadorDeSessao(List<Sessao> sessoesDaSala) {
		this.sessoesDaSala = sessoesDaSala;
	}
	private boolean horarioIsConflitante (Sessao sessao, Sessao sessaoExistente) {
		LocalDateTime horarioSessaoNova = sessao.getHorario().atDate(LocalDate.now());
		LocalDateTime terminoSessaoNova = sessao.getHorarioTermino().atDate(LocalDate.now());
		LocalDateTime horarioSessaoExistente = sessaoExistente.getHorario().atDate(LocalDate.now());
		LocalDateTime terminoSessaoExistente = sessaoExistente.getHorarioTermino().atDate(LocalDate.now());
		
		boolean terminaAntes = terminoSessaoNova.isBefore(horarioSessaoExistente);
		boolean comecaDepois = horarioSessaoNova.isAfter(terminoSessaoExistente);
		if (terminaAntes || comecaDepois) {
			return false;
		}
		return true;
	}
	public boolean cabe (Sessao sessaoNova) {
		return sessoesDaSala.stream().noneMatch(sessaoExistente -> horarioIsConflitante (sessaoExistente, sessaoNova));
	}
}
