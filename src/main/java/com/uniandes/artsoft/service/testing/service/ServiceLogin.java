package com.uniandes.artsoft.service.testing.service;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import java.sql.*;
import java.util.ArrayList;

import com.uniandes.artsoft.service.testing.database.*;

import com.uniandes.artsoft.service.testing.VO.VOUsuario;

@Path("/JavaArtsoft")
public class ServiceLogin {
	
	Connection conn = null;
	ResultSet resultado = null;
	Statement sentencia = null;
	
	private String[][] matriz = {};
	private String[] vector = {"Id","version"};
	private DefaultTableModel obj = new DefaultTableModel(matriz, vector);
	
	@POST
	@Path("/validaUsuario")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public VOUsuario validaUsuario(VOUsuario us) {
		us.setUserValido(false);
		if (us.getUsuario().equals("java") && us.getPassword().equals("artsoft")) {
			us.setUserValido(true);
		}
		return us;
	}	
	
	void conexionBD() throws SQLException {
		PreparedStatement query = null;
		String myString = null;
		String returnString = null;
		
		
		try {
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://localhost:5432/postgres";
		conn = DriverManager.getConnection(url, "postgres", "");
		if(conn != null) {
			System.out.println("Conexion hecha");
		}
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	@Path("/Mostrar")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public ArrayList<VOUsuario> mostrar() {
		try {
			ArrayList<VOUsuario> users = new ArrayList<VOUsuario>();
			conexionBD();
			sentencia = conn.createStatement();
			resultado = sentencia.executeQuery("SELECT * FROM \"Version\"");
			
			while(resultado.next()) {
				String id = resultado.getString("Id");
				String version = resultado.getString("version");
				
				VOUsuario u = new VOUsuario();
				u.setUsuario(id);
				u.setPassword(version);
				
				users.add(u);
				
			}
			//resultado.next();
			/*String id = resultado.getString("Id");
			String version = resultado.getString("version");
			
			VOUsuario u = new VOUsuario();
			u.setUsuario(id);
			u.setPassword(version);
			*/
			//String[] modelo = {id, version};
			return users;
		}
		catch(Exception ex){
		ex.printStackTrace();	
		}
		return null;
	}
	
	@Path("/Insertar")
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public VOUsuario mostrar(VOUsuario vo) {
		try {
			conexionBD();
			sentencia = conn.createStatement();
			resultado = sentencia.executeQuery("INSERT INTO public.\"Version\"(\"Id\", \"Version\") VALUES " +
			"("+ vo.getUsuario() +", '"+ vo.getPassword() +"');");

			vo.setUserValido(true);
			//String[] modelo = {id, version};
			return vo;
		}
		catch(Exception ex){
		ex.printStackTrace();	
		}
		return vo;
	}
	/*
	 * eje insertar:
	  {
	"usuario" : "3",
	"password" : "1.0.3"	
}
	 */
	
	/*@Path("/database")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public String returnDatabaseStatus() throws Exception {
		PreparedStatement query = null;
		String myString = null;
		String returnString = null;
		Connection conn = null;
		
		try {
			conn = PostgreJDBC.PostgreJDBCConn().getConnection();
			query = conn.prepareStatement("select to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS') DATETIME" + "from sys.dual");
			ResultSet rs = query.executeQuery();
			
			while (rs.next()) {
				myString = rs.getString("DATETIME");
			}
			
			query.close();
			returnString = "<p>Database Status</p>" +
			"<p>Database Date/Time return: "+ myString+"</p>";
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			
		}
		return returnString;
	}*/
}
