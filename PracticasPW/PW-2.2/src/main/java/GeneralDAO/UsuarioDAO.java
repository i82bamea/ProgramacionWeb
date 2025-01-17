package GeneralDAO;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;

import ConexDB.DBConexion;
import GeneralDTO.UsuarioDTO;

/*
 * Clase con funciones de los usuarios y BD
 */
public class UsuarioDAO {
	
	private String RutaSQL ="C:\\Users\\�ngel Barbero\\eclipse-workspace\\PW-2.2\\src\\main\\webapp\\WEB-INF\\sql.properties";

	private String url;
	private String user;
	private String password;
	
	public UsuarioDAO(String url, String user, String password){

		this.url = url;
		this.user = user;
		this.password = password;
	}
	/* Funcion que crea un user 
	 * @param NuevoUsuario 
	 */
	public void CrearUsuario(UsuarioDTO NuevoUsuario) {
	
		DBConexion DBConexion = new DBConexion(this.url, this.user, this.password);
		Connection Conexion = DBConexion.getConnection();
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
		
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("crearNuevoUsuario");
			cuestion=cuestion.replaceAll("varcorreo", NuevoUsuario.getCorreo());
			cuestion=cuestion.replaceAll("varcontrasena", NuevoUsuario.getContrasena());
			cuestion=cuestion.replaceAll("varnombre", NuevoUsuario.getNombre());
			cuestion=cuestion.replaceAll("varnombreusuario", NuevoUsuario.getNombreUsuario());
			cuestion=cuestion.replaceAll("vartipousuario", NuevoUsuario.getTipoUsuario());
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
			String formatoDiaHora = NuevoUsuario.getHoraRegistro().format(formato);
			cuestion = cuestion.replaceAll("varhoraregistro", formatoDiaHora);
			cuestion = cuestion.replaceAll("varultimaconexion", formatoDiaHora);
			
			Statement declaracion = Conexion.createStatement();
			declaracion.executeUpdate(cuestion);
			DBConexion.closeConnection();
		}
		catch (Exception Excep){
		
			System.err.println(Excep);
			Excep.printStackTrace();
		}
	}
	
	/* Funcion que busca todos los usuarios con determinado TipoUsuario
	 * @param TipoUsuario
	 * @return usuarios 
	 */
	
	public ArrayList<UsuarioDTO> SolicitarUsuarioPorTipo(String TipoUsuario){
		
		ArrayList<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
		DBConexion DBConexion = new DBConexion(this.url, this.user, this.password);
		Connection Conexion = DBConexion.getConnection();
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
			
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("seleccionarPorTipo");
			cuestion = cuestion.replace("vartipo", TipoUsuario);
			Statement declaracion = Conexion.createStatement();
			ResultSet resultado = (ResultSet) declaracion.executeQuery(cuestion);
			
			while (resultado.next()) {
				
				String Nombre = resultado.getString("Nombre");
				String Correo = resultado.getString("Correo");
				String NombreUsuario = resultado.getString("NombreUsuario");
				usuarios.add(new UsuarioDTO(Nombre, Correo,NombreUsuario));
			}

			if (declaracion != null){ 
				
				declaracion.close(); 
			}

			DBConexion.closeConnection();
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
		return usuarios;
	}
	
	/* Funcion que solicita los datos de inicio de sesion del user
	 * @param correo 
	 * @return Contrasena 
	 */
	
	public String SolicitarDatosSesion(String correo) {
		
		String Contrasena = null;
		DBConexion DBConexion = new DBConexion(this.url, this.user, this.password);
		Connection Conexion = DBConexion.getConnection();
	
		try(InputStream InSt = new FileInputStream(RutaSQL)){
			
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("solicitarContrasena");
			cuestion=cuestion.replaceAll("varcorreo", correo);
			
			Statement declaracion = Conexion.createStatement();
			ResultSet resultado = (ResultSet) declaracion.executeQuery(cuestion);
		
			if(resultado.next()){
				
				Contrasena = resultado.getString("Contrasena");
			}
			
			if (declaracion != null){ 
			
				declaracion.close(); 
			}
			DBConexion.closeConnection();
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
		
		return Contrasena;
	}
	
	/* Funcion que elimina un user por correo introducido
	 * @param correo 
	 */
	
	public void EliminarUsuario(String correo) {
		
		DBConexion DBConexion = new DBConexion(this.url, this.user, this.password);
		Connection Conexion = DBConexion.getConnection();
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
		
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("eliminarUsuario");
			cuestion=cuestion.replaceAll("varcorreo", correo);
			
			Statement declaracion = Conexion.createStatement();
			declaracion.executeUpdate(cuestion);
			DBConexion.closeConnection();
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
	}
	
	/* Funcion que acutaliza un user 
	 * @param actualizarUsuario 
	 */
	
	public void ActualizarUsuario(UsuarioDTO actualizarUsuario) {
		
		DBConexion DBConexion = new DBConexion(this.url, this.user, this.password);
		Connection Conexion = DBConexion.getConnection();
	
		try(InputStream InSt = new FileInputStream(RutaSQL)){
	
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("actualizarUsuario");
			cuestion=cuestion.replaceAll("varcorreo", actualizarUsuario.getCorreo());
			cuestion=cuestion.replaceAll("varcontrasena", actualizarUsuario.getContrasena());
			cuestion=cuestion.replaceAll("varnombre", actualizarUsuario.getNombre());
			cuestion=cuestion.replaceAll("varnombreusuario", actualizarUsuario.getNombreUsuario());
			
			Statement declaracion = Conexion.createStatement();
			declaracion.executeUpdate(cuestion);
			DBConexion.closeConnection();
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
	}
	
	/* Funcion que solicita informacion de un user por Correo introducido
	 * @param correo
	 * @return UsuarioSolicitado 
	 */
	
	public UsuarioDTO SolicitarUsuario(String Correo) {
		
		UsuarioDTO UsuarioSolicitado = new UsuarioDTO();
		
		DBConexion DBConexion = new DBConexion(this.url, this.user, this.password);
		Connection Conexion = DBConexion.getConnection();
		try(InputStream InSt = new FileInputStream(RutaSQL)){
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("solicitarInfoCorreoUsuario");
			cuestion=cuestion.replaceAll("varcorreo", Correo);
			
			Statement declaracion = Conexion.createStatement();
			ResultSet resultado = (ResultSet) declaracion.executeQuery(cuestion);
			
			if(resultado.next()){
				
				String Nombre = resultado.getString("Nombre");
				String correo = resultado.getString("Correo");
				String NombreUsuario = resultado.getString("NombreUsuario");
				String TipoUsuario = resultado.getString("TipoUsuario");
				
				UsuarioSolicitado.setCorreo(correo);
				UsuarioSolicitado.setNombre(Nombre);
				UsuarioSolicitado.setNombreUsuario(NombreUsuario);
				UsuarioSolicitado.setTipoUsuario(TipoUsuario);
			}
			
			if (declaracion != null){ 
				
				declaracion.close(); 
			}
			
			DBConexion.closeConnection();
		} 
		catch (Exception Excep){
		
			System.err.println(Excep);
			Excep.printStackTrace();
		}
		
		return UsuarioSolicitado;
	}
	
	/* Funcion que solicita los datos de inicio de sesion de un user por el nombre de user introducido
	 * @param nombreUsuario 
	 * @return UsuarioSolicitado 
	 */
	
	public UsuarioDTO SolicitarUsuarioPorNombreUsuario(String nombreUsuario) {
	
		UsuarioDTO UsuarioSolicitado = new UsuarioDTO();
		
		DBConexion DBConexion = new DBConexion(this.url, this.user, this.password);
		Connection Conexion = DBConexion.getConnection();
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
		
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("solicitarInfoNombreUsuario");
			cuestion=cuestion.replaceAll("varnombreusuario", nombreUsuario);
			
			Statement declaracion = Conexion.createStatement();
			ResultSet resultado = (ResultSet) declaracion.executeQuery(cuestion);
			
			String Nombre = resultado.getString("Nombre");
			String correo = resultado.getString("Correo");
			String NombreUsuario = resultado.getString("NombreUsuario");
			String TipoUsuario = resultado.getString("TipoUsuario");
			
			UsuarioSolicitado.setCorreo(correo);
			UsuarioSolicitado.setNombre(Nombre);
			UsuarioSolicitado.setNombreUsuario(NombreUsuario);
			UsuarioSolicitado.setTipoUsuario(TipoUsuario);
			
			if (declaracion != null){ 
				
				declaracion.close(); 
			}
			
			DBConexion.closeConnection();
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
		return UsuarioSolicitado;
	}
	
	/* Funcion que solicita los datos de inicio de sesion de un user
	 * @return usuarios 
	 */
	
	public ArrayList<UsuarioDTO> SolicitarTodosUsuarios(){
		
		ArrayList<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
		
		DBConexion DBConexion = new DBConexion(this.url, this.user, this.password);
		Connection Conexion = DBConexion.getConnection();
	
		try(InputStream InSt = new FileInputStream(RutaSQL)){
			
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("solicitarTodosUsuarios");
			
			Statement declaracion = Conexion.createStatement();
			ResultSet resultado = (ResultSet) declaracion.executeQuery(cuestion);
			
			while (resultado.next()) {
				
				String Nombre = resultado.getString("Nombre");
				String Correo = resultado.getString("Correo");
				String NombreUsuario = resultado.getString("NombreUsuario");
				String TipoUsuario = resultado.getString("TipoUsuario");
				
				usuarios.add(new UsuarioDTO(Nombre, Correo,NombreUsuario,TipoUsuario));
			}

			if (declaracion != null){ 
				
				declaracion.close(); 
			}

			DBConexion.closeConnection();
		}
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
		return usuarios;
	}
}