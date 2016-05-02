package es.upm.dit.isst.tfg.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import es.upm.dit.isst.tfg.model.TFG;

public class TFGDAOImplTest {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());

	@Before
	public void setUp() throws Exception {
		helper.setUp();
	}

	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}

	@Test
	public void testCreateTFG() {
		TFGDAO dao = TFGDAOImpl.getInstance();

		TFG tfg1 = dao.createTFG("autor@gmail.com", "titulo", "resumen",
				"tutor@gmail.com", "secretario@gmail.com", null, 1, false);
		assertEquals("autor@gmail.com", tfg1.getAuthor());
		assertEquals(1, tfg1.getStatus());

		TFG tfg2 = dao.createTFG("autor@gmail.com", "titulo", "resumen",
				"tutor@gmail.com", "secretario@gmail.com", null, 1, false);
		assertEquals("autor@gmail.com", tfg2.getAuthor());
	}

	@Test
	public void testGetTFG() {
		TFGDAO dao = TFGDAOImpl.getInstance();

		dao.createTFG("autor@gmail.com", "titulo", "resumen",
				"tutor@gmail.com", "secretario@gmail.com", null, 1, false);
		TFG tfg = dao.getTFG("autor@gmail.com");
		assertEquals(tfg.getTitle(), "titulo");
		
		assertNull(dao.getTFG("otro@gmail.com"));
	}

	@Test
	public void testGetAllTFGs() {
		TFGDAO dao = TFGDAOImpl.getInstance();

		TFG tfg1 = dao.createTFG("autor1@gmail.com", "titulo", "resumen",
				"tutor@gmail.com", "secretario@gmail.com", null, 1, false);
		TFG tfg2 = dao.createTFG("autor2@gmail.com", "titulo", "resumen",
				"tutor@gmail.com", "secretario@gmail.com", null, 1, false);
		TFG tfg3 = dao.createTFG("autor3@gmail.com", "titulo", "resumen",
				"tutor@gmail.com", "secretario@gmail.com", null, 1, false);
		
		List<TFG> tfgs = dao.getAllTFGs();
		
		assertEquals(3, tfgs.size());
		assertEquals(tfg1.getAuthor(), tfgs.get(0).getAuthor());
		assertEquals(tfg2.getAuthor(), tfgs.get(1).getAuthor());
		assertEquals(tfg3.getAuthor(), tfgs.get(2).getAuthor());
	}

	@Test
	public void testGetTFGsByTutor() {
		TFGDAO dao = TFGDAOImpl.getInstance();

		TFG tfg1 = dao.createTFG("autor1@gmail.com", "titulo", "resumen",
				"tutor1@gmail.com", "secretario@gmail.com", null, 1, false);
		TFG tfg2 = dao.createTFG("autor2@gmail.com", "titulo", "resumen",
				"tutor2@gmail.com", "secretario@gmail.com", null, 1, false);
		TFG tfg3 = dao.createTFG("autor3@gmail.com", "titulo", "resumen",
				"tutor2@gmail.com", "secretario@gmail.com", null, 1, false);
		
		List<TFG> tfgs1 = dao.getTFGsByTutor("tutor1@gmail.com");
		List<TFG> tfgs2 = dao.getTFGsByTutor("tutor2@gmail.com");
		List<TFG> tfgs3 = dao.getTFGsByTutor("tutor3@gmail.com");
		
		assertEquals(1, tfgs1.size());
		assertEquals(tfg1.getTutor(), tfgs1.get(0).getTutor());
		assertEquals(2, tfgs2.size());
		assertEquals(tfg2.getTutor(), tfgs2.get(0).getTutor());
		assertEquals(tfg3.getTutor(), tfgs2.get(1).getTutor());
		assertEquals(0, tfgs3.size());
	}

	@Test
	public void testGetTFGsBySecretary() {
		TFGDAO dao = TFGDAOImpl.getInstance();

		TFG tfg1 = dao.createTFG("autor1@gmail.com", "titulo", "resumen",
				"tutor@gmail.com", "secretario1@gmail.com", null, 1, false);
		TFG tfg2 = dao.createTFG("autor2@gmail.com", "titulo", "resumen",
				"tutor@gmail.com", "secretario2@gmail.com", null, 1, false);
		TFG tfg3 = dao.createTFG("autor3@gmail.com", "titulo", "resumen",
				"tutor@gmail.com", "secretario2@gmail.com", null, 1, false);
		
		List<TFG> tfgs1 = dao.getTFGsBySecretary("secretario1@gmail.com");
		List<TFG> tfgs2 = dao.getTFGsBySecretary("secretario2@gmail.com");
		List<TFG> tfgs3 = dao.getTFGsBySecretary("secretario3@gmail.com");
		
		assertEquals(1, tfgs1.size());
		assertEquals(tfg1.getSecretary(), tfgs1.get(0).getSecretary());
		assertEquals(2, tfgs2.size());
		assertEquals(tfg2.getSecretary(), tfgs2.get(0).getSecretary());
		assertEquals(tfg3.getSecretary(), tfgs2.get(0).getSecretary());
		assertEquals(0, tfgs3.size());
	}

	@Test
	public void testGetTFGsByStatus() {
		TFGDAO dao = TFGDAOImpl.getInstance();

		TFG tfg1 = dao.createTFG("autor1@gmail.com", "titulo", "resumen",
				"tutor@gmail.com", "secretario@gmail.com", null, 1, false);
		TFG tfg2 = dao.createTFG("autor2@gmail.com", "titulo", "resumen",
				"tutor@gmail.com", "secretario@gmail.com", null, 2, false);
		TFG tfg3 = dao.createTFG("autor3@gmail.com", "titulo", "resumen",
				"tutor@gmail.com", "secretario@gmail.com", null, 2, false);
		
		List<TFG> tfgs1 = dao.getTFGsByStatus(1);
		List<TFG> tfgs2 = dao.getTFGsByStatus(2);
		List<TFG> tfgs3 = dao.getTFGsByStatus(3);
		
		assertEquals(1, tfgs1.size());
		assertEquals(tfg1.getStatus(), tfgs1.get(0).getStatus());
		assertEquals(2, tfgs2.size());
		assertEquals(tfg2.getStatus(), tfgs2.get(0).getStatus());
		assertEquals(tfg3.getStatus(), tfgs2.get(0).getStatus());
		assertEquals(0, tfgs3.size());
	}

	@Test
	public void testUpdateTFG() {
		TFGDAO dao = TFGDAOImpl.getInstance();

		TFG tfg1 = dao.createTFG("autor1@gmail.com", "titulo", "resumen",
				"tutor@gmail.com", "secretario@gmail.com", null, 1, false);
		TFG tfg2 = dao.createTFG("autor2@gmail.com", "titulo", "resumen",
				"tutor@gmail.com", "secretario@gmail.com", null, 1, false);
		
		tfg1.setStatus(2);
		dao.updateTFG(tfg1);
		assertEquals(2, dao.getTFG("autor1@gmail.com").getStatus());
		assertEquals(1, dao.getTFG("autor2@gmail.com").getStatus());
	}

	@Test
	public void testDeleteTFG() {
		TFGDAO dao = TFGDAOImpl.getInstance();

		TFG tfg = dao.createTFG("autor@gmail.com", "titulo", "resumen",
				"tutor@gmail.com", "secretario@gmail.com", null, 1, false);
		
		assertEquals(1, dao.getTFG("autor@gmail.com").getStatus());
		
		dao.deleteTFG(tfg);
		assertNull(dao.getTFG("autor@gmail.com"));
	}

}
