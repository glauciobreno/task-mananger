package br.com.gerenciamento.service;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

import br.com.gerenciamento.dao.DAO;
import br.com.gerenciamento.model.Gerenciamento;
import br.com.gerenciamento.utility.NegocioExcetion;

public class GerenciamentoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DAO<Gerenciamento> dao;

	public void salvar(Gerenciamento g) throws NegocioExcetion {
		if (g.getTitulo().length() < 3) {
			throw new NegocioExcetion("O titulo não pode ter menos de 3 caracteres");
		}
		dao.salvar(g);
	}

	public void remover(Gerenciamento g) throws NegocioExcetion {
		dao.remover(Gerenciamento.class, g.getId());
	}

	public List<Gerenciamento> todoAsTarefas() {
		return dao.listarTodos();
	}

	public List<Gerenciamento> filtrar(String buscar) {
		return dao.filtro(buscar);
	}

	public List<Gerenciamento> filtrarPrioridades(String buscar) {
		return dao.filtrarPrioridades(buscar);
	}

	public List<Gerenciamento> filtrarResponsaveis(String buscar) {
		return dao.filtrarResponsaveis(buscar);
	}

}