package GeneralDAO;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;

import GeneralDTO.*;
import ConexDB.DBConexion;

public class UsuarioDAO {

	private String RutaSQL ="C:\\Users\\�ngel Barbero\\eclipse-workspace\\Practica3\\src\\main\\webapp\\WEB-INF\\sql.properties";
	private String url;
	private String usuario;
	private String contrasena;
	private DBConexion ConexDB;
	private Connection conexion;

	
	public UsuarioDAO( String url, String usuario, String contrasena){
	
		this.url = url;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.ConexDB = new DBConexion(this.url, this.usuario, this.contrasena);
 		this.conexion = ConexDB.getConnection();
	}
	
	public void desUsuarioDAO(){
	
		this.ConexDB.closeConnection();
	}

	public void CrearUsuario(UsuarioDTO NuevoUsuario) {
		
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
			
			Statement declaracion = conexion.createStatement();
			declaracion.executeUpdate(cuestion);
			
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
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
			
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("seleccionarPorTipo");
			cuestion = cuestion.replace("vartipo", TipoUsuario);
			Statement declaracion = conexion.createStatement();
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

			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
		return usuarios;
	}
	
	/* Funcion que solicita los datos de inicio de sesion del usuario
	 * @param correo 
	 * @return Contrasena 
	 */
	
	public String SolicitarDatosSesion(String correo) {
		
		String Contrasena = null;
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
			
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("solicitarContrasena");
			cuestion=cuestion.replaceAll("varcorreo", correo);
			
			Statement declaracion = conexion.createStatement();
			ResultSet resultado = (ResultSet) declaracion.executeQuery(cuestion);
		
			if(resultado.next()){
				
				Contrasena = resultado.getString("Contrasena");
			}
			
			if (declaracion != null){ 
			
				declaracion.close(); 
			}
			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
		
		return Contrasena;
	}
	
	/* Funcion que elimina un usuario por correo introducido
	 * @param correo 
	 */
	
	public void EliminarUsuario(String correo) {
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
		
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("eliminarUsuario");
			cuestion=cuestion.replaceAll("varcorreo", correo);
			
			Statement declaracion = conexion.createStatement();
			declaracion.executeUpdate(cuestion);
			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
	}
	
	/* Funcion que acutaliza un usuario 
	 * @param actualizarUsuario 
	 */
	
	public void ActualizarUsuario(UsuarioDTO actualizarUsuario) {
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
	
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("actualizarUsuario");
			cuestion=cuestion.replaceAll("varcorreo", actualizarUsuario.getCorreo());
			cuestion=cuestion.replaceAll("varcontrasena", actualizarUsuario.getContrasena());
			cuestion=cuestion.replaceAll("varnombre", actualizarUsuario.getNombre());
			cuestion=cuestion.replaceAll("varnombreusuario", actualizarUsuario.getNombreUsuario());
			
			Statement declaracion = conexion.createStatement();
			declaracion.executeUpdate(cuestion);
			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
	}
	
	public void ActualizarLog(UsuarioDTO updateUser, String fecha)
	{
		try(InputStream input = new FileInputStream(RutaSQL)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("updateLog");
			query=query.replaceAll("varmail", updateUser.getCorreo());
			query=query.replaceAll("fechaLog", fecha);
			
			
			Statement stmt = conexion.createStatement();
			stmt.executeUpdate(query);
			
		} catch (Exception e){
			
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	/* Funcion que solicita informacion de un usuario por Correo introducido
	 * @param correo
	 * @return UsuarioSolicitado 
	 */
	
	public UsuarioDTO SolicitarUsuario(String Correo) {
		
		UsuarioDTO UsuarioSolicitado = new UsuarioDTO();
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("solicitarInfoCorreoUsuario");
			cuestion=cuestion.replaceAll("varcorreo", Correo);
			
			Statement declaracion = conexion.createStatement();
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
			
			
		} 
		catch (Exception Excep){
		
			System.err.println(Excep);
			Excep.printStackTrace();
		}
		
		return UsuarioSolicitado;
	}
	
	/* Funcion que solicita los datos de inicio de sesion de un usuario por el nombre de usuario introducido
	 * @param nombreUsuario 
	 * @return UsuarioSolicitado 
	 */
	
	public UsuarioDTO SolicitarUsuarioPorNombreUsuario(String nombreUsuario) {
	
		UsuarioDTO UsuarioSolicitado = new UsuarioDTO();
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
		
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("solicitarInfoNombreUsuario");
			cuestion=cuestion.replaceAll("varnombreusuario", nombreUsuario);
			
			Statement declaracion = conexion.createStatement();
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
			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
		return UsuarioSolicitado;
	}
	
	/* Funcion que solicita los datos de inicio de sesion de un usuario
	 * @return usuarios 
	 */
	
	public ArrayList<UsuarioDTO> SolicitarTodosUsuarios(){
		
		ArrayList<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
			
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("solicitarTodosUsuarios");
			
			Statement declaracion = conexion.createStatement();
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
			
		}
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
		return usuarios;
	}
}
