package br.com.gerenciamento.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.gerenciamento.model.Gerenciamento;
import br.com.gerenciamento.service.GerenciamentoService;
import br.com.gerenciamento.utility.NegocioExcetion;
import br.com.gerenciamento.utility.Message;

@Named
@ViewScoped
public class GerenciamentoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Gerenciamento gerenciamento;

	@Inject
	private GerenciamentoService service;

	private List<Gerenciamento> gerenciamentos;

	private String buscar;
	private String prioridade;
	private String responsavel;

	@PostConstruct
	public void carregar() {
		gerenciamentos = service.todoAsTarefas();
	}
	
	public void filtrarResponsaveis() {
		if (responsavel != null)
			gerenciamentos = service.filtrarResponsaveis(responsavel);
		
	}

	public void filtrarPrioridades() {
		if (prioridade != null)
			gerenciamentos = service.filtrarPrioridades(prioridade);
		
	}

	public void buscarTask() {
		if (buscar != null)
			gerenciamentos = service.filtrar(buscar);
	}

	public void adicionar() {
		try {
			service.salvar(gerenciamento);
			gerenciamento = new Gerenciamento();
			carregar();
			Message.info("salvo com sucesso");
		} catch (NegocioExcetion e) {
			Message.erro(e.getMessage());
		}
	}

	public void excluir() {
		try {
			service.remover(gerenciamento);
			carregar();
			Message.info(gerenciamento.getTitulo() + "Foi removido");
		} catch (NegocioExcetion e) {
			Message.erro(e.getMessage());
		}
	}

	public String getBuscar() {
		return buscar;
	}

	public void setBuscar(String buscar) {
		this.buscar = buscar;
	}

	public Gerenciamento getGerenciamento() {
		return gerenciamento;
	}

	public void setGerenciamento(Gerenciamento gerenciamento) {
		this.gerenciamento = gerenciamento;
	}

	public List<Gerenciamento> getGerenciamentos() {
		return gerenciamentos;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

}
