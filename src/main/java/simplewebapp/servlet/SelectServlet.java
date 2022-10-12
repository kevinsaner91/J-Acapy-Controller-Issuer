package simplewebapp.servlet;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import simplewebapp.beans.Schema;
import simplewebapp.beans.Selection;
import simplewebapp.beans.Attribute;

@WebServlet(urlPatterns = { "/select" })
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SelectServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String connection = request.getParameter("connections");
		String schema = request.getParameter("schemas");
		String creddef = request.getParameter("creddef");
		
		if(connection == null || schema == null || creddef== null) {
			String errorString = "Error: Field cannot be empty"; 
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher //
					= this.getServletContext().getRequestDispatcher("/WEB-INF/views/issueCredentialView.jsp");

			dispatcher.forward(request, response);
		}

		List<Selection> selectionList = new ArrayList<Selection>();
		selectionList.add(new Selection(connection, schema, creddef));

		request.setAttribute("selection", selectionList);
		
		
		// get Schema Attributes
		try {
			Schema schemaObj = getSchemaDetails(schema);
			String[] keys = schemaObj.getAttributes().replace("[", "").replace("]", "").split(",");
			
			List<Attribute> attributes = new ArrayList<Attribute>();
			for(int i = 0; i < keys.length; i++) {
				attributes.add(new Attribute(keys[i]));
			}
			
		request.setAttribute("attributes", attributes);	
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/sendCredentialView.jsp");

		dispatcher.forward(request, response);
	}
	
	private Schema getSchemaDetails(String schemaId) throws ClientProtocolException, IOException, URISyntaxException {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();

		URIBuilder builder = new URIBuilder().setScheme("https").setHost("faber-api.educa.ch").setPath("/schemas/" + schemaId);


		System.out.println(builder.toString());

		HttpGet httpget = new HttpGet(builder.toString());

		HttpResponse httpresponse = httpclient.execute(httpget);
		Scanner sc = new Scanner(httpresponse.getEntity().getContent());

		System.out.println(httpresponse.getStatusLine());
		if (httpresponse.getStatusLine().toString().contains("200")) {
			List<String> httpcontent = new ArrayList<String>();
			while (sc.hasNext()) {
				String nextLine = sc.nextLine();
				System.out.println(nextLine);
				httpcontent.add(nextLine);
			}

			JSONParser parser = new JSONParser();

			try {
				JSONObject schemaIds = (JSONObject) parser.parse(httpcontent.get(0));
				JSONObject object = (JSONObject) schemaIds.get("schema_json");

				System.out.println(object.toString());
				JSONArray attributes = (JSONArray) object.get("attrNames");
				
				Schema schema = new Schema(object.get("id").toString(), object.get("name").toString(), object.get("version").toString(), attributes.toString().replace("\"", ""));
				return schema;
			} catch (ParseException e) {
				// TODO: handle exception
			}

			
		}
		return null;
	}

}
