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

import simplewebapp.beans.Creddef;

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
		RequestDispatcher dispatcher //
		= this.getServletContext().getRequestDispatcher("/WEB-INF/views/creddefView.jsp");

		dispatcher.forward(request, response);

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
				
				Creddef creddef = new Creddef(object.get("id").toString(), object.get("ver").toString(), object.get("tag").toString());
				return creddef;
			} catch (ParseException e) {
				// TODO: handle exception
			}

			
		}
		return null;
	}

}
