package es.upm.dit.isst.tfg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.*;

import es.upm.dit.isst.tfg.dao.TFGDAO;
import es.upm.dit.isst.tfg.dao.TFGDAOImpl;
import es.upm.dit.isst.tfg.model.TFG;

public class TFGsAPIServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		TFGDAO dao = TFGDAOImpl.getInstance();

		List<TFG> tfgs = dao.getAllTFGs();
		for (TFG tfg : tfgs) {
			resp.getWriter().print(tfg.getTitle() + "\n");
		}

		resp.getWriter().print("\n\n\n");

		JSONArray array = new JSONArray();

		for (TFG tfg : tfgs) {
			array.add(tfg);
		}

		resp.getWriter().print(array.toJSONString());

		resp.getWriter().print("\n\n\n");

		String json = array.toJSONString();

		JSONArray obj = (JSONArray) JSONValue.parse(json);

		List<TFG> tfgs2 = new ArrayList<TFG>();

		for (Object o : obj) {
			JSONObject jsonObject = (JSONObject) o;
			String author = (String) jsonObject.get("author");
			String title = (String) jsonObject.get("title");
			String summary = (String) jsonObject.get("summary");
			String tutor = (String) jsonObject.get("tutor");
			String secretary = (String) jsonObject.get("secretary");
			String file = (String) jsonObject.get("file");
			int status = ((Long)jsonObject.get("status")).intValue();
			tfgs2.add(new TFG(author, title, summary, tutor, secretary, file, status));
		}

		for (TFG tfg : tfgs2) {
			resp.getWriter().print(tfg.getTitle() + "\n");
		}

	}

}
