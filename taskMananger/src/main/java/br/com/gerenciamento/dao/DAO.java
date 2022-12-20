package br.com.gerenciamento.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.gerenciamento.model.Base;
import br.com.gerenciamento.model.Gerenciamento;

public class DAO<T extends Base> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static EntityManager manager = ConnectionFactory.getEntityMananger();

	public T buscarPorId(Class<T> clazz, Long id) {
		return manager.find(clazz, id);
	}

	public void salvar(T t) {
		try {
			manager.getTransaction().begin();
			if (t.getId() == null) {
				manager.persist(t);
			} else {
				manager.merge(t);
			}
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
	}

	public void remover(Class<T> clazz, Long id) {
		T t = buscarPorId(clazz, id);

		try {
			manager.getTransaction().begin();
			manager.remove(t);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
	}

	public List<Gerenciamento> listarTodos() {
		Query query = manager.createQuery("select g from Gerenciamento g order by g.id");
		return query.getResultList();
	}

	public List<Gerenciamento> filtro(String buscar) {
		Query query = manager.createQuery("select g from Gerenciamento g where g.titulo like :buscar order by g.id")
				.setParameter("buscar", buscar);
		return query.getResultList();
	}

	public List<Gerenciamento> filtrarPrioridades(String buscar) {
		Query query = manager
				.createQuery("select g from Gerenciamento g where g.prioridade like :prioridade order by g.id")
				.setParameter("prioridade", buscar);
		return query.getResultList();
	}

	public List<Gerenciamento> filtrarResponsaveis(String buscar) {
		Query query = manager
				.createQuery("select g from Gerenciamento g where g.responsavel like :responsavel order by g.id")
				.setParameter("responsavel", buscar);
		return query.getResultList();
	}

}
