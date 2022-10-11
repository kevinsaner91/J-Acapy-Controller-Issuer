package simplewebapp.servlet;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import simplewebapp.beans.Schema;

@WebServlet(urlPatterns = { "/schema" })
public class SchemaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SchemaServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("https://faber-api.educa.ch/schemas/created");
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
				JSONArray array = (JSONArray) schemaIds.get("schema_ids");
				List<Schema> schemaList = new ArrayList<Schema>();
				
				for (int i = 0; i < array.size(); i++) {
					System.out.println(array.get(i).toString());
					Schema schema = getSchemaDetails(array.get(i).toString());
					System.out.println(schema.toString());
					schemaList.add(schema);
				}
				
				request.setAttribute("schemaList", schemaList);
			} catch (ParseException p) {

			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/schemaView.jsp");

		dispatcher.forward(request, response);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Something happened");
		String schemaName = request.getParameter("schemaName");
		String schemaVersion = request.getParameter("schemaVersion");
		String attributes = request.getParameter("attributes");
		
		if(schemaName.isEmpty()|| schemaVersion.isEmpty() || attributes.isEmpty()) {
			String errorString = "Error: Field cannot be empty"; 
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher //
					= this.getServletContext().getRequestDispatcher("/WEB-INF/views/schemaView.jsp");

			dispatcher.forward(request, response);
		} else {
			System.out.println("Here I am!");
			request.setAttribute("errorString", "");
			JSONArray attributesJSONArr = new JSONArray();
			String[] attributesArr = attributes.split(",");

			for (int i = 0; i < attributesArr.length; i++) {
				attributesJSONArr.add(attributesArr[i]);
			}

			JSONObject schema = new JSONObject();
			schema.put("attributes", attributesJSONArr);
			schema.put("schema_version", schemaVersion);
			schema.put("schema_name", schemaName);

			System.out.println(schema.toString());
			
			CloseableHttpClient httpclient = HttpClients.createDefault();

			HttpPost httpPost = new HttpPost("https://faber-api.educa.ch/schemas");

			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");

			StringEntity stringEntity = new StringEntity(schema.toString());

			httpPost.setEntity(stringEntity);

			System.out.println("Executing request " + httpPost.getRequestLine());

			HttpResponse response1 = httpclient.execute(httpPost);

			doGet(request, response);
		}
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
