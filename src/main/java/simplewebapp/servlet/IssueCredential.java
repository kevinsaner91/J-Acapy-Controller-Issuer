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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.owlike.genson.Genson;

import simplewebapp.beans.ActiveConnection;
import simplewebapp.beans.Creddef;
import simplewebapp.beans.Result;
import simplewebapp.beans.Schema;

@WebServlet(urlPatterns = { "/issueCredential" })
public class IssueCredential extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public IssueCredential() {
		super();
	}

	// Show Login page.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			List<Result> connectionIds = getConnections();
			request.setAttribute("connectionList", connectionIds);
			
			List<Schema> schemas = getSchemas();
			request.setAttribute("schemaList", schemas);
			
			List<Creddef> creddefs = getCreddefs();
			request.setAttribute("creddefList", creddefs);
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		// Forward to /WEB-INF/views/loginView.jsp
		// (Users can not access directly into JSP pages placed in WEB-INF)
		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/issueCredentialView.jsp");

		dispatcher.forward(request, response);

	}



	// When the user enters userName & password, and click Submit.
	@SuppressWarnings("unchecked")
	// This method will be executed.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String connection = request.getParameter("connection");
		String schema = request.getParameter("schema");
		String creddef = request.getParameter("creddef");
		
		String[] keys = request.getParameterValues("key");
		String[] values = request.getParameterValues("value");
		
		JSONObject credential = new JSONObject();
		credential.put("comment", "Congrashululations to you!");
		credential.put("cred_def_id", creddef);
		
		JSONObject credentialPreview = new JSONObject();
		credentialPreview.put("@type", "did:sov:BzCbsNYhMrjHiqZDTUASHg;spec/issue-credential/1.0/credential-preview");
		
		JSONArray attributes = new JSONArray();
		
		for(int i = 0; i < keys.length; i++) {
			JSONObject attribute = new JSONObject();
			attribute.put("name", keys[i]);
			attribute.put("value", values[i]);
			attributes.add(attribute);
		}
		credentialPreview.put("attributes", attributes);
		
		credential.put("credential_preview", credentialPreview);
	
		credential.put("connection_id", connection);
		credential.put("auto_issue", "true");
		
		System.out.println("Credential: " + credential.toString());
		
		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost("https://faber-api.educa.ch/issue-credential/send-offer");

		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");

		StringEntity stringEntity = new StringEntity(credential.toString());

		httpPost.setEntity(stringEntity);

		System.out.println("Executing request " + httpPost.getRequestLine());

		HttpResponse response1 = httpclient.execute(httpPost);

		doGet(request, response);
	}
	
	private List<Creddef> getCreddefs() throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("https://faber-api.educa.ch/credential-definitions/created");
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
				JSONObject creddefIds = (JSONObject) parser.parse(httpcontent.get(0));
				JSONArray array = (JSONArray) creddefIds.get("credential_definition_ids");
				List<Creddef> creddefList = new ArrayList<Creddef>();
				
				for (int i = 0; i < array.size(); i++) {
					System.out.println(array.get(i).toString());
					Creddef creddef = getCreddefDetails(array.get(i).toString());
					System.out.println(creddef.toString());
					creddefList.add(creddef);
				}
				
				return creddefList;
			} catch (ParseException p) {

			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;

	}
	
	private Creddef getCreddefDetails(String creddefId) throws ClientProtocolException, IOException, URISyntaxException {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();

		URIBuilder builder = new URIBuilder().setScheme("https").setHost("faber-api.educa.ch").setPath("/credential-definitions/" + creddefId);


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
				JSONObject creddefJson = (JSONObject) parser.parse(httpcontent.get(0));
				JSONObject object = (JSONObject) creddefJson.get("credential_definition");

				System.out.println(object.toString());
				
				Creddef creddef = new Creddef(object.get("id").toString(), object.get("tag").toString(), object.get("ver").toString());
				return creddef;
			} catch (ParseException e) {
				// TODO: handle exception
			}

			
		}
		return null;
	}

	private List<Result> getConnections() throws ClientProtocolException, IOException{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("https://faber-api.educa.ch/connections");
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

			ActiveConnection activeConnection = new Genson().deserialize(httpcontent.get(0), ActiveConnection.class);

			return activeConnection.getResults();
		}
		return null;
	}
	
	private List<Schema> getSchemas() throws ClientProtocolException, IOException, URISyntaxException, ParseException{
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

			JSONObject schemaIds = (JSONObject) parser.parse(httpcontent.get(0));
			JSONArray array = (JSONArray) schemaIds.get("schema_ids");
			List<Schema> schemaList = new ArrayList<Schema>();

			for (int i = 0; i < array.size(); i++) {
				System.out.println(array.get(i).toString());
				Schema schema = getSchemaDetails(array.get(i).toString());
				System.out.println(schema.toString());
				schemaList.add(schema);
			}

			return schemaList;
		}
		return null;

	}

	private Schema getSchemaDetails(String schemaId) throws ClientProtocolException, IOException, URISyntaxException {

		CloseableHttpClient httpclient = HttpClients.createDefault();

		URIBuilder builder = new URIBuilder().setScheme("https").setHost("faber-api.educa.ch")
				.setPath("/schemas/" + schemaId);

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

				Schema schema = new Schema(object.get("id").toString(), object.get("name").toString(),
						object.get("version").toString(), attributes.toString().replace("\"", ""));
				return schema;
			} catch (ParseException e) {
				// TODO: handle exception
			}

		}
		return null;
	}

}
