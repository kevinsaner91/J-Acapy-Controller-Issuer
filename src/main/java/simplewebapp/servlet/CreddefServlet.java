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

import simplewebapp.beans.Creddef;
import simplewebapp.properties.PropertiesUtil;

@WebServlet(urlPatterns = { "/creddef" })
public class CreddefServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public CreddefServlet() {
		super();
	}

	// Show Login page.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(new PropertiesUtil().getScheme() +"://" +  new PropertiesUtil().getFaberAgentURL()+ "/credential-definitions/created");
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
				
				request.setAttribute("creddefList", creddefList);
			} catch (ParseException p) {

			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		// Forward to /WEB-INF/views/loginView.jsp
		// (Users can not access directly into JSP pages placed in WEB-INF)
		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/creddefView.jsp");

		dispatcher.forward(request, response);

	}

	// When the user enters userName & password, and click Submit.
	// This method will be executed.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Something happened");
		String schemaId = request.getParameter("schemaId");
		String tag = request.getParameter("tag");
		
		if(schemaId.isEmpty()|| tag.isEmpty()) {
			String errorString = "Error: Field cannot be empty"; 
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher //
					= this.getServletContext().getRequestDispatcher("/WEB-INF/views/creddefView.jsp");

			dispatcher.forward(request, response);
		} else {
			System.out.println("Here I am!");
			request.setAttribute("errorString", "");
			
			JSONObject creddef = new JSONObject();

			creddef.put("schema_id", schemaId);
			creddef.put("tag", tag);

			System.out.println(creddef.toString());
			
			CloseableHttpClient httpclient = HttpClients.createDefault();

			HttpPost httpPost = new HttpPost(new PropertiesUtil().getScheme() + "://" + new PropertiesUtil().getFaberAgentURL()+ "/credential-definitions");

			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");

			StringEntity stringEntity = new StringEntity(creddef.toString());

			httpPost.setEntity(stringEntity);

			System.out.println("Executing request " + httpPost.getRequestLine());

			HttpResponse response1 = httpclient.execute(httpPost);

			doGet(request, response);
		}

	}
	
	private Creddef getCreddefDetails(String creddefId) throws ClientProtocolException, IOException, URISyntaxException {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();

		URIBuilder builder = new URIBuilder().setScheme(new PropertiesUtil().getScheme()).setHost(new PropertiesUtil().getFaberAgentURL()).setPath("/credential-definitions/" + creddefId);


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

}
