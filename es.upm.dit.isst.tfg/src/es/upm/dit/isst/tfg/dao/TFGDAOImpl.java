/**
 * 
 */
package es.upm.dit.isst.tfg.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.tfg.model.TFG;

/**
 * @author Federico A. Fernandez Moreno
 *
 */
public class TFGDAOImpl implements TFGDAO {
	
	private static TFGDAOImpl instance;
	
	private TFGDAOImpl() {
		
	}
	
	public static TFGDAOImpl getInstance(){
		if (instance==null)
			instance = new TFGDAOImpl();
		return instance;
	}

	/* (non-Javadoc)
	 * @see es.upm.dit.isst.tfg.dao.TFGDAO#createTFG(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int)
	 */
	@Override
	public void createTFG(String author, String title, String summary,
			String tutor, String secretary, String file, int status) {
		EntityManager em = EMFService.get().createEntityManager();
		
		TFG tfg = new TFG(author, title, summary, tutor, secretary, file, status);
		em.persist(tfg);
		
		em.close();
	}

	/* (non-Javadoc)
	 * @see es.upm.dit.isst.tfg.dao.TFGDAO#getTFG(java.lang.String)
	 */
	@Override
	public TFG getTFG(String author) {
		EntityManager em = EMFService.get().createEntityManager();
		
		Query q = em.createQuery("SELECT i from TFG i where author = :author", TFG.class);
		q.setParameter("author", author);
		List<TFG> tfgs = q.getResultList();
		
		em.close();
		
		if (tfgs.size() != 0)
			return tfgs.get(0);
		else return null;
	}

	/* (non-Javadoc)
	 * @see es.upm.dit.isst.tfg.dao.TFGDAO#getAllTFGs()
	 */
	@Override
	public List<TFG> getAllTFGs() {
		EntityManager em = EMFService.get().createEntityManager();
		
		Query q = em.createQuery("SELECT i from TFG i", TFG.class);
		List<TFG> tfgs = q.getResultList();
		
		em.close();
		
		return tfgs;
	}

	/* (non-Javadoc)
	 * @see es.upm.dit.isst.tfg.dao.TFGDAO#getTFGsByTutor(java.lang.String)
	 */
	@Override
	public List<TFG> getTFGsByTutor(String tutor) {
		EntityManager em = EMFService.get().createEntityManager();
		
		Query q = em.createQuery("SELECT i from TFG i where tutor = :tutor", TFG.class);
		q.setParameter("tutor", tutor);
		List<TFG> tfgs = q.getResultList();
		
		em.close();
		
		return tfgs;
	}

	/* (non-Javadoc)
	 * @see es.upm.dit.isst.tfg.dao.TFGDAO#getTFGsBySecretary(java.lang.String)
	 */
	@Override
	public List<TFG> getTFGsBySecretary(String secretary) {
		EntityManager em = EMFService.get().createEntityManager();
		
		Query q = em.createQuery("SELECT i from TFG i where secretary = :secretary", TFG.class);
		q.setParameter("secretary", secretary);
		List<TFG> tfgs = q.getResultList();
		
		em.close();
		
		return tfgs;
	}

	/* (non-Javadoc)
	 * @see es.upm.dit.isst.tfg.dao.TFGDAO#getTFGsByStatus(int)
	 */
	@Override
	public List<TFG> getTFGsByStatus(int status) {
		EntityManager em = EMFService.get().createEntityManager();
		
		Query q = em.createQuery("SELECT i from TFG i where status = :status", TFG.class);
		q.setParameter("status", status);
		List<TFG> tfgs = q.getResultList();
		
		em.close();
		
		return tfgs;
	}

	/* (non-Javadoc)
	 * @see es.upm.dit.isst.tfg.dao.TFGDAO#updateTFG(es.upm.dit.isst.tfg.model.TFG)
	 */
	@Override
	public void updateTFG(TFG tfg) {
		EntityManager em = EMFService.get().createEntityManager();
		em.merge(tfg);
		em.close();
	}

	/* (non-Javadoc)
	 * @see es.upm.dit.isst.tfg.dao.TFGDAO#deleteTFG(es.upm.dit.isst.tfg.model.TFG)
	 */
	@Override
	public void deleteTFG(TFG tfg) {
		EntityManager em = EMFService.get().createEntityManager();		
		em.remove(tfg);
		em.close();
	}

}
