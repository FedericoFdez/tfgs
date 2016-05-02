/**
 * 
 */
package es.upm.dit.isst.tfg.dao;

import java.util.List;

import es.upm.dit.isst.tfg.model.TFG;

/**
 * @author Federico A. Fernandez Moreno
 *
 */
public interface TFGDAO {
	
	//CREATE methods
	public TFG createTFG(String author, String title, String summary,
			String tutor, String secretary, String file, int status, boolean rejected);

	//READ methods
	public TFG getTFG(String author);
	
	public List<TFG> getAllTFGs();
	
	public List<TFG> getTFGsByTutor(String tutor);
	
	public List<TFG> getTFGsBySecretary(String secretary);
	
	public List<TFG> getTFGsByStatus(int status);
	
	//UPDATE methods
	public void updateTFG(TFG tfg);
	
	//DELETE methods
	public void deleteTFG(TFG tfg);
	
}
