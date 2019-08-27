package session14;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Converter
 */
@WebServlet("/Converter")
public class Converter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       HashMap<String,Double> hm;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Converter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		hm=new HashMap<String,Double>();
		hm.put("USD-GBP",1/0.81);
		hm.put("USD-EUR",1/0.9);
		hm.put("GBP-USD",0.81);
		hm.put("EUR-USD",0.9);
		hm.put("USD-FR",1/0.98);
		hm.put("FR-USD",0.98);
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"ISO-8859-1\">\r\n" + 
				"<title>Insert title here</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<form action=\"Converter\" method=\"post\">\r\n" + 
				"Currency Val <input type=\"number\" name=\"val1\">\r\n" + 
				"Currency Type1 <select  name=\"curr1\">\r\n" +
				"<option value='USD'>USD</option>"+
				"<option value='EUR'>Euros</option>"+
				"<option value='GBP'>British Pounds</option>"+
				"<option value='FR'>Swiss Francs</option></select>"+
				"Currency Type2 <select name=\"curr2\">\r\n" + 
				"<option value='USD'>USD</option>"+
				"<option value='EUR'>Euros</option>"+
				"<option value='GBP'>British Pounds</option>"+
				"<option value='FR'>Swiss Francs</option></select>"+
				"<input type='submit' value='calculate'>"+
				"\r\n" + 
				"</form>\r\n" + 
				"</body>\r\n" + 
				"</html>").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		double val1=Double.parseDouble(request.getParameter("val1"));
		String currOne=request.getParameter("curr1");
		String currTwo=request.getParameter("curr2");
		
		double val2=0;
		if(currOne.equals(currTwo)) {
			val2=val1;
		}
		else {
			Double rate=Converter.getRate(currOne,currTwo,hm);
			val2=val1/rate;
		}
		response.getWriter().append("<html>	<head></head><body>	<table>	<tr><td> Original Value"
				+ "</td><td>Currency 1</td> <td>Currency2</td> <td>Value in new currency</td>"
				+ "</tr><tr>"
				+ "		<td>"+val1 +"</td>"+
		"<td>" + currOne+"</td>"
		+ "<td>"+ currTwo+"</td><td>"+ val2+"</td></tr></table></body></html>");
		doGet(request, response);
	}
	public static double getRate(String curr1,String curr2,HashMap<String,Double> hm) {
		String comb=curr1+"-"+curr2;
		return hm.get(comb);
	}

}