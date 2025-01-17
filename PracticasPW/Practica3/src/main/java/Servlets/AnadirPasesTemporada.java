package Servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import GeneralDTO.PaseDTO;
import javax.servlet.*;
import GeneralDAO.EspectaculoDAO;

@WebServlet(name="AnadirPasesTemporada", urlPatterns="/AnadirPasesTemporada")

public class AnadirPasesTemporada extends HttpServlet{
	
	private static final long serialVersionUID = 3058004877733204408L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ServletContext app = getServletContext();
			
		String passBD = app.getInitParameter("password");
		String urlBD = app.getInitParameter("url");
		String userBD = app.getInitParameter("user");

		EspectaculoDAO tempDAO = new EspectaculoDAO(urlBD,userBD,passBD);
		int id = Integer.parseInt(request.getParameter("idesp"));
		
		int idPase = tempDAO.generarIdPases();
		
		String fechai = request.getParameter("fechai");
		fechai = fechai.replace('T', ' ');
		fechai = fechai + ":00.0";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
		LocalDateTime Fechai = LocalDateTime.parse(fechai,formatter);
		
		String fechaf = request.getParameter("fechaf");
		fechaf = fechaf.replace('T', ' ');
		fechaf = fechaf + ":00.0";
		LocalDateTime Fechaf = LocalDateTime.parse(fechaf,formatter);
		
		String diaSemana = request.getParameter("diasemana");
		
		PaseDTO newPase = new PaseDTO(idPase, Fechai, diaSemana, Fechaf);
		tempDAO.CrearPase(newPase, id);
		
		String ruta = request.getContextPath() + "/GetMultiplesTemporadaServlet";
		tempDAO.desEspectaculoDAO();
		PrintWriter out = response.getWriter();
		out.println("<script type=\"text/javascript\">");
        out.println("alert('Pase a�adido correctamente.');");
        out.println("window.location.href =" + "'" + ruta + "'" + ";" );
        out.println("</script>");
	}
}
