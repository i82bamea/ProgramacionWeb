package GeneralDAO;

import GeneralDTO.*;
import ConexDB.DBConexion;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

public class EspectaculoDAO {

	private String RutaSQL ="C:\\Users\\�ngel Barbero\\eclipse-workspace\\Practica3\\src\\main\\webapp\\WEB-INF\\sql.properties";
	private String url;
	private String usuario;
	private String contrasena;
	private DBConexion ConexDB;
	private Connection conexion;
	
	public EspectaculoDAO( String url, String usuario, String contrasena){

		this.url = url;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.ConexDB = new DBConexion(this.url, this.usuario, this.contrasena);
 		this.conexion = ConexDB.getConnection();
	}
	
	public void desEspectaculoDAO(){
	
		this.ConexDB.closeConnection();
	}

	
	/* Funcion que comprueba si existe un ID de un espectaculo Puntual
	 * @param id
	 * @return true / false
	 */
	
private boolean ExisteIdEspectaculoPuntual(int Id){
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
			
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("existeIdEspectaculoPuntual");
			Statement declaracion = conexion.createStatement();
			ResultSet resultado = (ResultSet) declaracion.executeQuery(cuestion);
			
			while (resultado.next()){
				
				if(Integer.parseInt(resultado.getString("Id"))==Id){
					
					return true; 
				}
			}
			
		} 
		catch (Exception Excep){
		
			System.err.println(Excep);
			Excep.printStackTrace();
		}
		return false; 
	}
	
	/* Funcion que comprueba si un Id de EspectaculoPaseMultiple existee
	 * @param Id Identificador a comprobar
	 * @return true / false
	 */
	
	private boolean ExisteIdEspectaculoPaseMultiple(int Id){
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
		
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("existeIdEspectaculoPaseMultiple");
			Statement declaracion = conexion.createStatement();
			ResultSet resultado = (ResultSet) declaracion.executeQuery(cuestion);
			
			while (resultado.next()){
				
				if(Integer.parseInt(resultado.getString("Id"))==Id){
					
					return true; 
				}
			}
			
			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
		return false; 
	}
	
	/* Funcion que comprueba si un Id de EspectaculoTemporada existee
	 * @param Id Identificador a comprobar
	 * @return true / false
	 */
	
	private boolean ExisteIdEspectaculoTemporada(int Id){
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
			
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("existeIdEspectaculosTemporada");
			Statement declaracion = conexion.createStatement();
			ResultSet resultado = (ResultSet) declaracion.executeQuery(cuestion);
			
			while (resultado.next()){
				
				if(Integer.parseInt(resultado.getString("Id"))==Id){
					
					return true; 
				}
			}
			
			
		}
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
		return false; 
	}
	
	/* Funcion que comprueba si existee Id de fecha
	 * @param Id Identificador a comprobar
	 * @return true / false
	 */
	
	private boolean ExisteIdFecha(int Id){
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
			
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("existeIdFecha");
			Statement declaracion = conexion.createStatement();
			ResultSet resultado = (ResultSet) declaracion.executeQuery(cuestion);
			
			while (resultado.next()){
				
				if(Integer.parseInt(resultado.getString("Id"))==Id){
				
					return true; 
				}
			}
			
			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
		return false; 
	}
	
	/* Funcion que comprueba si existee un Id de MultiplesFechas
	 * @param Id Identificador a comprobar
	 * @return true / false
	 */

	private boolean ExisteIdMultiplesFechas(int Id){
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
		
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("existeIdMultiplesFechas");
			Statement declaracion = conexion.createStatement();
			ResultSet resultado = (ResultSet) declaracion.executeQuery(cuestion);
			
			while (resultado.next()){

				if(Integer.parseInt(resultado.getString("Id"))==Id){
					
					return true; 
				}
			}
			
			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
		return false; 
	}
	
	/* Funcion que comprueba si existee un Id de pase
	 * @param Id 
	 * @return true / false
	 */
	
	private boolean ExisteIdPase(int Id){
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
			
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("existeIdPases");
			Statement declaracion = conexion.createStatement();
			ResultSet resultado = (ResultSet) declaracion.executeQuery(cuestion);
			
			while (resultado.next()){
				
				if(Integer.parseInt(resultado.getString("Id"))==Id){
					
					return true; 
				}
			}
		
			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
		return false; 
	}
	
	/* Funcion que genera Id para un pase
	 * @return Id 
	 */
	
	public int generarIdPases(){
		
		Random num = new Random();
		int Id = num.nextInt(99999)+1; 
		if (ExisteIdPase(Id) == true){
			
			generarIdPases();
		}
		return Id;
	}
	
	/* Funcion que genera Id para  MultiplesFechas
	 * @return Id 
	 */
	
	public int GenerarIdMultiplesFechas(){
		
		Random num = new Random();
		int Id = num.nextInt(99999)+1; 
		if (ExisteIdMultiplesFechas(Id) == true){
			
			GenerarIdMultiplesFechas();
		}
		return Id;
	}
	
	/* Funcion que genera Id para  fecha
	 * @return Id 
	 */
	
	public int GenerarIdFechas(){
		
		Random num = new Random();
		int Id = num.nextInt(99999)+1; 
		
		if (ExisteIdFecha(Id) == true){
			
			GenerarIdFechas();
		}
		return Id;
	}
	
	/* Funcion que genera Id para  Espectaculo Pase Multiple
	 * @return Id 
	 */
	
	public int GenerarIdEspectaculoPaseMultiple(){
		
		Random num = new Random();
		int Id = num.nextInt(99999)+1; 
		if (ExisteIdEspectaculoPaseMultiple(Id) == true){
			
			GenerarIdEspectaculoPaseMultiple();
		}
		return Id;
	}
	
	/* Funcion que genera Id para Espectaculo Puntual
	 * @return Id 
	 */

	public int GenerarIdEspectaculoPuntual(){
		
		Random num = new Random();
		int Id = num.nextInt(99999)+1; 
		if (ExisteIdEspectaculoPuntual(Id) == true){
			
			GenerarIdEspectaculoPuntual();
		}
		return Id;
	}
	
	/* Funcion que genera Id para  Espectaculo de Temporada
	 * @return Id 
	 */
	
	public int GenerarIdEspectaculoTemporada(){
		
		Random num = new Random();
		int Id = num.nextInt(99999)+1; 
		if (ExisteIdEspectaculoTemporada(Id) == true){
			
			GenerarIdEspectaculoTemporada();
		}
		return Id;
	}
	
	/* Funcion que crea nuevo Espectaculo Puntual 
	 * @param nuevoEspectaculoPuntual 
	 */
	
	public void CrearEspectaculoPuntual(EspectaculoPuntualDTO nuevoEspectaculoPuntual) {
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
		
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("crearEspectaculoPuntual");
			cuestion=cuestion.replaceAll("varid", Integer.toString(nuevoEspectaculoPuntual.getId())); 
			cuestion=cuestion.replaceAll("vartitulo", nuevoEspectaculoPuntual.getNombre());
			cuestion=cuestion.replaceAll("varcontenido", nuevoEspectaculoPuntual.getContenido());
			cuestion=cuestion.replaceAll("varlocalidadestotales", Integer.toString(nuevoEspectaculoPuntual.getLocalidadesTotales()));
			cuestion=cuestion.replaceAll("varlocalidadesvendidas", Integer.toString(nuevoEspectaculoPuntual.getLocalidadesVendidas())); 
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"); 
			String formatoHoraFecha = nuevoEspectaculoPuntual.getFecha().format(formato);
			cuestion=cuestion.replaceAll("varfecha",	formatoHoraFecha);
			cuestion=cuestion.replaceAll("vartipo", nuevoEspectaculoPuntual.getTipo());
			Statement declaracion = conexion.createStatement();
			declaracion.executeUpdate(cuestion);
			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
	}
	
	/* Funcion que elimina Espectaculo Puntual 
	 * @param Id 
	 */
	
	public void EliminarEspectaculoPuntual(int Id) {
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
			
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("eliminarEspectaculoPuntual");
			cuestion=cuestion.replaceAll("varid", Integer.toString(Id));
			Statement declaracion = conexion.createStatement();
			declaracion.executeUpdate(cuestion);
			
			String cuestion2 = propiedades.getProperty("eliminarCriticaEspectaculo");
			cuestion2=cuestion2.replaceAll("varidEspectaculo", Integer.toString(Id));
			declaracion = conexion.createStatement();
			declaracion.executeUpdate(cuestion2);
			
			String cuestion3 = propiedades.getProperty("eliminarVotantes");
			cuestion3=cuestion3.replaceAll("varid", Integer.toString(Id));
			declaracion = conexion.createStatement();
			declaracion.executeUpdate(cuestion3);
			
			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
	}
	
	/* Funcion para acutualizar un Espectaculo Puntual
	 * @param actualizarEspectaculoPuntual 
	 */
	
	public void ActualizarEspectaculoPuntual(EspectaculoPuntualDTO actualizarEspectaculoPuntual) { 
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
			
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("actualizarEspectaculoPuntual"); 
			cuestion=cuestion.replaceAll("varid", Integer.toString(actualizarEspectaculoPuntual.getId()));
			cuestion=cuestion.replaceAll("vartitulo", actualizarEspectaculoPuntual.getNombre());
			cuestion=cuestion.replaceAll("varcontenido", actualizarEspectaculoPuntual.getContenido());
			cuestion=cuestion.replaceAll("varlocalidadestotales", Integer.toString(actualizarEspectaculoPuntual.getLocalidadesTotales()));
			cuestion=cuestion.replaceAll("varlocalidadesvendidas", Integer.toString(actualizarEspectaculoPuntual.getLocalidadesVendidas())); 
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"); 
			String formatoHoraFecha = actualizarEspectaculoPuntual.getFecha().format(formato);
			cuestion=cuestion.replaceAll("varfecha", formatoHoraFecha);
			cuestion=cuestion.replaceAll("vartipo", actualizarEspectaculoPuntual.getTipo());
			
			Statement declaracion = conexion.createStatement();
			declaracion.executeUpdate(cuestion);
			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
	}
	
	/* Funcion que solicita Espectaculo Puntual por Id introducido
	 * @param Id 
	 * @return EspectaculoPuntualSolicitado 
	 */
	
	public EspectaculoPuntualDTO SolicitarEspectaculoPuntual(int Id) {
		
		EspectaculoPuntualDTO EspectaculoPuntualSolicitado = new EspectaculoPuntualDTO();
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
		
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("solicitarInfoEspectaculoPuntual");
			cuestion=cuestion.replaceAll("varid", Integer.toString(Id));
			
			Statement declaracion = conexion.createStatement();
			ResultSet resultado = (ResultSet) declaracion.executeQuery(cuestion);
			
			
			String identificacion = resultado.getString("Id");
			String titulo = resultado.getString("Titulo");
			String contenido = resultado.getString("Contenido");
			String localidadesTotales = resultado.getString("LocalidadesTotales");
			String localidadesVendidas = resultado.getString("LocalidadesVendidas");
			String fecha = resultado.getString("Fecha");
			String tipo = resultado.getString("Tipo");
			
			EspectaculoPuntualSolicitado.setId(Integer.parseInt(identificacion));
			EspectaculoPuntualSolicitado.setNombre(titulo);
			EspectaculoPuntualSolicitado.setContenido(contenido);
			EspectaculoPuntualSolicitado.setLocalidadesTotales(Integer.parseInt(localidadesTotales));
			EspectaculoPuntualSolicitado.setLocalidadesVendidas(Integer.parseInt(localidadesVendidas));

			DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
			LocalDateTime fechaD = LocalDateTime.parse(fecha, formato);
			
			EspectaculoPuntualSolicitado.setFecha(fechaD);
			EspectaculoPuntualSolicitado.setTipo(tipo);
			
			if (declaracion != null){ 
				declaracion.close(); 
			}
			
		} catch (Exception Excep){
			System.err.println(Excep);
			Excep.printStackTrace();
		}
		
		return EspectaculoPuntualSolicitado;
	}
	
	/* Funcion solicita informacion detodos los Espectaculos Puntuales 
	 * @return espectaculosPuntualesInfo 
	 */
	
	public ArrayList<EspectaculoPuntualDTO> SolicitarEspectaculoPuntualInfo(){
	
		ArrayList<EspectaculoPuntualDTO> espectaculosPuntualesInfo = new ArrayList<EspectaculoPuntualDTO>();
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
		
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("solicitarTodosEspectaculoPuntual");
			
			Statement declaracion = conexion.createStatement();
			ResultSet resultado = (ResultSet) declaracion.executeQuery(cuestion);
			
			while (resultado.next()) {
			
				String identificacion = resultado.getString("Id");
				String titulo = resultado.getString("Titulo");
				String contenido = resultado.getString("Contenido");
				String localidadesTotales = resultado.getString("LocalidadesTotales");
				String localidadesVendidas = resultado.getString("LocalidadesVendidas");
				String fecha = resultado.getString("Fecha");
				String tipo = resultado.getString("Tipo");
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime fechaD = LocalDateTime.parse(fecha, formato);
				espectaculosPuntualesInfo.add(new EspectaculoPuntualDTO(Integer.parseInt(identificacion), titulo, tipo, contenido, Integer.parseInt(localidadesTotales), Integer.parseInt(localidadesVendidas), fechaD)); 
			}

			if (declaracion != null){ 
				
				declaracion.close(); 
			}

			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
		return espectaculosPuntualesInfo;
	}
	
	/* Funcion que crea un Espectaculo Multiple 
	 * @param nuevoEspectaculoPaseMultiple 
	 */
	
	public void CrearEspectaculoPaseMultiple(EspectaculoPaseMultipleDTO nuevoEspectaculoPaseMultiple) {
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
		
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("crearEspectaculoPaseMultiple");
			
			cuestion=cuestion.replaceAll("varid", Integer.toString(nuevoEspectaculoPaseMultiple.getId())); //TODO: Make sense?
			cuestion=cuestion.replaceAll("vartitulo", nuevoEspectaculoPaseMultiple.getNombre());
			cuestion=cuestion.replaceAll("varcontenido", nuevoEspectaculoPaseMultiple.getContenido());
			cuestion=cuestion.replaceAll("varlocalidadestotales", Integer.toString(nuevoEspectaculoPaseMultiple.getLocalidadesTotales()));
			cuestion=cuestion.replaceAll("varlocalidadesvendidas", Integer.toString(nuevoEspectaculoPaseMultiple.getLocalidadesVendidas())); 
			cuestion=cuestion.replaceAll("vartipo", nuevoEspectaculoPaseMultiple.getTipo());
			
			Statement declaracion = conexion.createStatement();
			declaracion.executeUpdate(cuestion);
			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
	}

	/* Funcion que crea Fecha para un Espectaculo Pase Multiple 
	 * @param nuevaFecha 
	 * @param idEspectaculo
	 */
	
	public void CrearFecha(FechaDTO nuevaFecha, int idEspectaculo) {
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
			
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			
			int idFecha = GenerarIdFechas();
			
			String cuestion = propiedades.getProperty("createFecha");
			
			cuestion=cuestion.replaceAll("varid", Integer.toString(idFecha)); 
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"); 
			String formatoHoraFecha = nuevaFecha.getFecha().format(formato);
			cuestion=cuestion.replaceAll("varfecha", formatoHoraFecha);
			
			Statement declaracion = conexion.createStatement();
			declaracion.executeUpdate(cuestion);
			
			String cuestion2=propiedades.getProperty("crearMultiplesFechas");
			
			cuestion2=cuestion2.replaceAll("varid", Integer.toString(idEspectaculo));
			cuestion2=cuestion2.replaceAll("varidfechas", Integer.toString(idFecha));
			declaracion.executeUpdate(cuestion2);
			
			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
	}
	
	/* Funcion que elimina espectaculo pase multiple
	 * @param Id 
	 */
	
	public void EliminarEspectaculoPaseMultiple(int Id) {
	
		try(InputStream InSt = new FileInputStream(RutaSQL)){
			
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("eliminarEspectaculoPaseMultiple");
			cuestion=cuestion.replaceAll("varid", Integer.toString(Id));
			
			Statement declaracion = conexion.createStatement();
			declaracion.executeUpdate(cuestion);
			
			String cuestion2 = propiedades.getProperty("eliminarFechaEspectaculo");
			cuestion2 = cuestion2.replaceAll("varid", Integer.toString(Id));
			declaracion.executeUpdate(cuestion2);
			
			String cuestion3 = propiedades.getProperty("eliminarMultiplesFechas2");
			cuestion3 = cuestion3.replaceAll("varid", Integer.toString(Id));
			declaracion.executeUpdate(cuestion3);
			
			String cuestion4 = propiedades.getProperty("eliminarCriticaEspectaculo");
			cuestion4 = cuestion4.replaceAll("varidEspectaculo", Integer.toString(Id));
			
			String cuestion5 = propiedades.getProperty("eliminarVotantes");
			cuestion5 = cuestion5.replaceAll("varid", Integer.toString(Id));
			
			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
	}
	
	/* Funcion para eliminar una fecha
	 * @param eliminarFecha 
	 */
	
	public void EliminarFecha(FechaDTO eliminarFecha) {
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
			
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("eliminarFecha");
			cuestion=cuestion.replaceAll("varid", Integer.toString(eliminarFecha.getId()));
			
			Statement declaracion = conexion.createStatement();
			declaracion.executeUpdate(cuestion);

			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
	}
	
	/* Funcion qu elimina Fechas de Espectaculos Pase Multiple
	 * @param idfecha 
	 */
	
	public void EliminarFechaEspectaculoPaseMultiple(int idfecha) {
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
			
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("eliminarFechaEspectaculoPaseMultiple");
			cuestion=cuestion.replaceAll("varid", Integer.toString(idfecha));
			
			Statement declaracion = conexion.createStatement();
			declaracion.executeUpdate(cuestion);

			
		}
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
	}
	
	/* Funcion para actualizar Espectaculo Pase Multiple
	 * @param actualizarEspectaculoPaseMultiple
	 */
	
	public void ActualizarEspectaculoPaseMultiple(EspectaculoPaseMultipleDTO actualizarEspectaculoPaseMultiple) {
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
		
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("actualizarEspectaculoPaseMultiple"); 
			
			cuestion=cuestion.replaceAll("varid", Integer.toString(actualizarEspectaculoPaseMultiple.getId()));
			cuestion=cuestion.replaceAll("vartitulo", actualizarEspectaculoPaseMultiple.getNombre());
			cuestion=cuestion.replaceAll("varcontenido", actualizarEspectaculoPaseMultiple.getContenido());
			cuestion=cuestion.replaceAll("varlocalidadestotales", Integer.toString(actualizarEspectaculoPaseMultiple.getLocalidadesTotales()));
			cuestion=cuestion.replaceAll("varlocalidadesvendidas", Integer.toString(actualizarEspectaculoPaseMultiple.getLocalidadesVendidas())); 
			cuestion=cuestion.replaceAll("vartipo", actualizarEspectaculoPaseMultiple.getTipo());
			
			Statement declaracion = conexion.createStatement();
			declaracion.executeUpdate(cuestion);
			
		}
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
	}
	
	/* Funcion para actualizar una fecha de un espectaculo pase multiple
	 * @param actualizarFecha 
	 * @param idEspectaculo 
	 */
	
	public void ActualizarFecha(FechaDTO actualizarFecha, int idEspectaculo) {
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			
			String cuestion = propiedades.getProperty("actualizarFecha");
			cuestion=cuestion.replaceAll("varid", Integer.toString(actualizarFecha.getId()));
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"); 
			String formatoHoraFecha = actualizarFecha.getFecha().format(formato);
			cuestion=cuestion.replaceAll("varfecha", formatoHoraFecha);
			
			Statement declaracion = conexion.createStatement();
			declaracion.executeUpdate(cuestion);

			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
	}
	
	/* Funcion que solicita un Espectaculo Pase Multiple por Id introducido
	 * @param Id 
	 * @return EspectaculoPaseMultipleSolicitado 
	 */
	
	public EspectaculoPaseMultipleDTO SolicitarEspectaculoPaseMultiple(int Id) {
		
		EspectaculoPaseMultipleDTO EspectaculoPaseMultipleSolicitado = new EspectaculoPaseMultipleDTO();
		ArrayList<FechaDTO> fechasSolicitadas = new ArrayList<FechaDTO>();
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
		
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("solicitarInfoEspectaculoPaseMultiple");
			cuestion=cuestion.replaceAll("varid", Integer.toString(Id));
			
			Statement declaracion = conexion.createStatement();
			ResultSet resultado = (ResultSet) declaracion.executeQuery(cuestion);
			
			String identificacion = resultado.getString("Id");
			String titulo = resultado.getString("Titulo");
			String contenido = resultado.getString("Contenido");
			String localidadesTotales = resultado.getString("LocalidadesTotales");
			String localidadesVendidas = resultado.getString("LocalidadesVendidas");
			String tipo = resultado.getString("Tipo");
			
			EspectaculoPaseMultipleSolicitado.setId(Integer.parseInt(identificacion));
			EspectaculoPaseMultipleSolicitado.setNombre(titulo);
			EspectaculoPaseMultipleSolicitado.setContenido(contenido);
			EspectaculoPaseMultipleSolicitado.setLocalidadesTotales(Integer.parseInt(localidadesTotales));
			EspectaculoPaseMultipleSolicitado.setLocalidadesVendidas(Integer.parseInt(localidadesVendidas));
			EspectaculoPaseMultipleSolicitado.setTipo(tipo);
			
			String cuestion2 = propiedades.getProperty("solicitarFechasEspectaculoPaseMultiple");
			cuestion2 = cuestion2.replaceAll("varid", Integer.toString(Id));
			
			ResultSet resultado2 = (ResultSet) declaracion.executeQuery(cuestion);
			
			while (resultado2.next()) {
			
				String iden = resultado.getString("Id");
				String fecha = resultado.getString("Fecha");
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime fechaD = LocalDateTime.parse(fecha, formato);
				fechasSolicitadas.add(new FechaDTO(Integer.parseInt(iden), fechaD));
			}
			
			EspectaculoPaseMultipleSolicitado.setFecha(fechasSolicitadas);
			
			if (declaracion != null){ 
			
				declaracion.close(); 
			}
			
			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
		
		return EspectaculoPaseMultipleSolicitado;
	}
	
	/* Funcion que dsolicita todos los Espectaculos Pase Multiple 
	 * @return espectaculosPaseMultipleInfo 
	 */
	
	public ArrayList<EspectaculoPaseMultipleDTO> SolicitarEspectaculoPaseMultipleInfo(){
		
		ArrayList<EspectaculoPaseMultipleDTO> espectaculosPaseMultipleInfo = new ArrayList<EspectaculoPaseMultipleDTO>();
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
		
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("solicitarTodosEspectaculoPaseMultiple");
			
			Statement declaracion = conexion.createStatement();
			ResultSet resultado = (ResultSet) declaracion.executeQuery(cuestion);
			
			while (resultado.next()) {
			
				String identificacion = resultado.getString("Id");
				String titulo = resultado.getString("Titulo");
				String contenido = resultado.getString("Contenido");
				String localidadesTotales = resultado.getString("LocalidadesTotales");
				String localidadesVendidas = resultado.getString("LocalidadesVendidas");
				String tipo = resultado.getString("Tipo");
				int identificacionEspectaculo = Integer.parseInt(identificacion);
				
				espectaculosPaseMultipleInfo.add(new EspectaculoPaseMultipleDTO(identificacionEspectaculo, titulo, tipo, contenido, Integer.parseInt(localidadesTotales), Integer.parseInt(localidadesVendidas), requestFechasEsp(identificacionEspectaculo)));
			}

			if (declaracion != null){ 
				
				declaracion.close(); 
			}

			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
		return espectaculosPaseMultipleInfo;
	}
	
	/* Funcion para crear un Espectaculo de Temporada
	 * @param nuevoEspectaculoTemporada 
	 */
	
	public void CrearEspectaculoTemporada(EspectaculoTemporadaDTO nuevoEspectaculoTemporada) {
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
		
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("crearEspectaculosTemporada");
			
			cuestion=cuestion.replaceAll("varid", Integer.toString(nuevoEspectaculoTemporada.getId()));
			cuestion=cuestion.replaceAll("vartitulo", nuevoEspectaculoTemporada.getNombre()); 
			cuestion=cuestion.replaceAll("varcontenido", nuevoEspectaculoTemporada.getContenido());
			cuestion=cuestion.replaceAll("varlocalidadestotales", Integer.toString(nuevoEspectaculoTemporada.getLocalidadesTotales()));
			cuestion=cuestion.replaceAll("varlocalidadesvendidas", Integer.toString(nuevoEspectaculoTemporada.getLocalidadesVendidas())); 
			cuestion=cuestion.replaceAll("vartipo", nuevoEspectaculoTemporada.getTipo());
			
			Statement declaracion = conexion.createStatement();

			declaracion.executeUpdate(cuestion);
			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
	}

	/* Funcion para crear pase de un Espectaculo Temporada
	 * @param nuevoPase 
	 * @param idEspectaculo 
	 */
	
	public void CrearPase(PaseDTO nuevoPase, int idEspectaculo) {
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
		
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			int identificacionPase = generarIdPases();
			String cuestion = propiedades.getProperty("crearPases");
			cuestion=cuestion.replaceAll("varid", Integer.toString(identificacionPase));
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"); 
			String formatoHoraFecha = nuevoPase.getInicioFecha().format(formato);
			cuestion=cuestion.replaceAll("variniciofecha", formatoHoraFecha);
			
			cuestion = cuestion.replaceAll("vardiass", nuevoPase.getDiaSemana());
			
			formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"); 
			formatoHoraFecha = nuevoPase.getFinalFecha().format(formato);
			cuestion=cuestion.replaceAll("varfinalfecha", formatoHoraFecha);

			
			Statement declaracion = conexion.createStatement();
			declaracion.executeUpdate(cuestion);
			
			String cuestion2=propiedades.getProperty("crearMultiplesPase");
			cuestion2=cuestion2.replaceAll("varid", Integer.toString(idEspectaculo));
			cuestion2=cuestion2.replaceAll("varidpase",Integer.toString(identificacionPase));

			
			declaracion.executeUpdate(cuestion2);
			
			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
	}
	
	/* Funcion para eliminar Espectaculo de Temporada
	 * @param Id 
	 */
	
	public void EliminarEspectaculoTemporada(int Id) {
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
			
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			System.out.println("dao:");
			System.out.println(Id);
			String cuestion = propiedades.getProperty("eliminarEspectaculosTemporada");
			cuestion=cuestion.replaceAll("varid", Integer.toString(Id));
			System.out.println(cuestion);
			Statement declaracion = conexion.createStatement();
			declaracion.executeUpdate(cuestion);
			
			String cuestion2 = propiedades.getProperty("eliminarPases");
			cuestion2 = cuestion2.replaceAll("varid", Integer.toString(Id));
			declaracion.executeUpdate(cuestion2);
			
			String cuestion3 = propiedades.getProperty("eliminarMultiplesPases2");
			cuestion3 = cuestion3.replaceAll("varid", Integer.toString(Id));
			declaracion.executeUpdate(cuestion3);
			
			String cuestion4 = propiedades.getProperty("eliminarCriticaEspectaculo");
			cuestion4=cuestion4.replaceAll("varidEspectaculo", Integer.toString(Id));
			declaracion = conexion.createStatement();
			declaracion.executeUpdate(cuestion4);
			
			String cuestion5 = propiedades.getProperty("eliminarVotantes");
			cuestion5=cuestion5.replaceAll("varid", Integer.toString(Id));
			declaracion = conexion.createStatement();
			declaracion.executeUpdate(cuestion5);
			
			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
	}
	
	/* Funcion para eliminar un Pase
	 * @param eliminarPase 
	 */
	
	public void EliminarPase(PaseDTO eliminarPase) {
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
			
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("eliminarPase");
			cuestion=cuestion.replaceAll("varid", Integer.toString(eliminarPase.getId()));
			
			Statement declaracion = conexion.createStatement();
			declaracion.executeUpdate(cuestion);

			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
	}
	
	/* Funcion para eliminar Pase de Espectaculo de Temporada
	 * @param IdPase 	 * 
	 */
	
	public void eliminarTPase(int IdPase) {
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
			
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("eliminarMultiplesPase");
			cuestion=cuestion.replaceAll("varid", Integer.toString(IdPase));
			
			Statement declaracion = conexion.createStatement();
			declaracion.executeUpdate(cuestion);

			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
	}
	
	/* Funcion para actualizar un Espectaculo Temporada
	 * @param actualizarEspectaculoTemporada
	 */
	
	public void ActualizarEspectaculoTemporada(EspectaculoTemporadaDTO actualizarEspectaculoTemporada) {
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
		
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("actualizarEspectaculosTemporada"); 
		
			cuestion=cuestion.replaceAll("varid", Integer.toString(actualizarEspectaculoTemporada.getId()));
			cuestion=cuestion.replaceAll("vartitulo", actualizarEspectaculoTemporada.getNombre());
			cuestion=cuestion.replaceAll("varcontenido", actualizarEspectaculoTemporada.getContenido());
			cuestion=cuestion.replaceAll("varlocalidadestotales", Integer.toString(actualizarEspectaculoTemporada.getLocalidadesTotales()));
			cuestion=cuestion.replaceAll("varlocalidadesvendidas", Integer.toString(actualizarEspectaculoTemporada.getLocalidadesVendidas())); 
			cuestion=cuestion.replaceAll("vartipo", actualizarEspectaculoTemporada.getTipo());
			Statement declaracion = conexion.createStatement();
			declaracion.executeUpdate(cuestion);
			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
	}
	
	/* Funcion para actualizar un pase de un Espectaculo Temporada
	 * @param actualizarPase 
	 * @param idEspectaculo 
	 */
	
	public void ActualizarPase(PaseDTO actualizarPase) {
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
		
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			
			String cuestion = propiedades.getProperty("actualizarPase");
			cuestion=cuestion.replaceAll("varid", Integer.toString(actualizarPase.getId()));
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"); 
			String formatoHoraFecha = actualizarPase.getInicioFecha().format(formato);
			cuestion=cuestion.replaceAll("variniciofecha", formatoHoraFecha);
			
			cuestion=cuestion.replaceAll("vardiass", actualizarPase.getDiaSemana());
			
			formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"); 
			formatoHoraFecha = actualizarPase.getInicioFecha().format(formato);
			cuestion=cuestion.replaceAll("varfinalfecha", formatoHoraFecha);
			
			Statement declaracion = conexion.createStatement();
			declaracion.executeUpdate(cuestion);
			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
	}
	
	/* Funcion que solicita un Espectaculo Temporada
	 * @param Id 
	 * @return espectaculoTemporadaSolicitado
	 */
	
	public EspectaculoTemporadaDTO SolicitarEspectaculoTemporada(int Id) {
		
		EspectaculoTemporadaDTO espectaculoTemporadaSolicitado = new EspectaculoTemporadaDTO();
		ArrayList<PaseDTO> pases = new ArrayList<PaseDTO>();
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
			
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("solicitarInfoEspectaculosTemporada");
			cuestion=cuestion.replaceAll("varid", Integer.toString(Id));
			
			Statement declaracion = conexion.createStatement();
			ResultSet resultado = (ResultSet) declaracion.executeQuery(cuestion);
			
			String identificacion = resultado.getString("Id");
			String titulo = resultado.getString("Titulo");
			String contenido = resultado.getString("Contenido");
			String localidadesTotales = resultado.getString("LocalidadesTotales");
			String localidadesVendidas = resultado.getString("LocalidadesVendidas");
			String tipo = resultado.getString("Tipo");
			
			espectaculoTemporadaSolicitado.setId(Integer.parseInt(identificacion));
			espectaculoTemporadaSolicitado.setNombre(titulo);
			espectaculoTemporadaSolicitado.setContenido(contenido);
			espectaculoTemporadaSolicitado.setLocalidadesTotales(Integer.parseInt(localidadesTotales));
			espectaculoTemporadaSolicitado.setLocalidadesVendidas(Integer.parseInt(localidadesVendidas));
			espectaculoTemporadaSolicitado.setTipo(tipo);
			
			String cuestion2 = propiedades.getProperty("solicitarPasesEspectaculoTemporada");
			cuestion2 = cuestion2.replaceAll("varid", Integer.toString(Id));
			
			ResultSet resultado2 = (ResultSet) declaracion.executeQuery(cuestion);
			
			while (resultado2.next()) {
				
				String iden = resultado.getString("Id");
				String fecha = resultado.getString("InicioFecha");
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime inicioFecha = LocalDateTime.parse(fecha, formato);
				String diasS = resultado.getString("DiasS");
				fecha = resultado.getString("FinalFecha");
				formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime finalFecha = LocalDateTime.parse(fecha, formato);
				pases.add(new PaseDTO(Integer.parseInt(iden), inicioFecha, diasS, finalFecha));
			}
			
			espectaculoTemporadaSolicitado.setPase(pases);
			
			if (declaracion != null){ 
				
				declaracion.close(); 
			}
			
			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
		
		return espectaculoTemporadaSolicitado;
	}
	
	/* Funcion que solicita todos los Espectaculos Temporada 
	 * @return lsitEspectaculosTemporadas 
	 */
	
	public ArrayList<EspectaculoTemporadaDTO> SolicitarEspectaculoTemporadaInfo(){
		
		ArrayList<EspectaculoTemporadaDTO> espectaculosTemporadaInfo = new ArrayList<EspectaculoTemporadaDTO>();
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
		
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("solicitarTodosEspectaculosTemporada");
			
			Statement declaracion = conexion.createStatement();
			ResultSet resultado = (ResultSet) declaracion.executeQuery(cuestion);
			
			while (resultado.next()) {
			
				String identificacion = resultado.getString("Id");
				String titulo = resultado.getString("Titulo");
				String contenido = resultado.getString("Contenido");
				String localidadesTotales = resultado.getString("LocalidadesTotales");
				String localidadesVendidas = resultado.getString("LocalidadesVendidas");
				String tipo = resultado.getString("Tipo");
				int identificacionEspectaculo = Integer.parseInt(identificacion);
				
				espectaculosTemporadaInfo.add(new EspectaculoTemporadaDTO(identificacionEspectaculo, titulo, tipo, contenido, Integer.parseInt(localidadesTotales), Integer.parseInt(localidadesVendidas),SolicitarPasesEspectaculoTemporada(identificacionEspectaculo)));
			}
			if (declaracion != null){ 
				
				declaracion.close(); 
			}

			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
		return espectaculosTemporadaInfo;
	}
	
	/* Funcion que solicita todas las Fechas   
	 * @return fechasSolicitadas
	 */
	
	public ArrayList<FechaDTO> SolicitarFechas(){
		
		ArrayList<FechaDTO> fechasSolicitadas = new ArrayList<FechaDTO>();
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("solicitarTodasFechas");
			
			Statement declaracion = conexion.createStatement();
			ResultSet resultado = (ResultSet) declaracion.executeQuery(cuestion);
			
			while (resultado.next()) {
				String identificacion = resultado.getString("Id");
				String fechasBaseD =  resultado.getString("Fecha");
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime fecha = LocalDateTime.parse(fechasBaseD, formato);
				fechasSolicitadas.add(new FechaDTO(Integer.parseInt(identificacion), fecha));
			}
			if (declaracion != null){ 
				declaracion.close(); 
			}

			
		} catch (Exception Excep){
			System.err.println(Excep);
			Excep.printStackTrace();
		}
		return fechasSolicitadas;
	}
	
	/* Funcion que devuelve los datos de todos los Pases 
	 * @return pases Vector de DTOs de Pases con los datos de cada pase
	 * 
	 */
	
	public ArrayList<PaseDTO> SolicitarPases(){
		ArrayList<PaseDTO> pases = new ArrayList<PaseDTO>();
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("solicitarTodosPases");
			
			Statement declaracion = conexion.createStatement();
			ResultSet resultado = (ResultSet) declaracion.executeQuery(cuestion);
			
			while (resultado.next()) {
				String identificacion = resultado.getString("Id");
				String fechasBaseD =  resultado.getString("InicioFecha");
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime inicioFecha = LocalDateTime.parse(fechasBaseD, formato);
				String diasS = resultado.getString("DiasS");
				fechasBaseD =  resultado.getString("FinalFecha");		
				formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime finalFecha = LocalDateTime.parse(fechasBaseD, formato);
				pases.add(new PaseDTO(Integer.parseInt(identificacion), inicioFecha, diasS,finalFecha));
			}
			if (declaracion != null){ 
				declaracion.close(); 
			}

			
		} catch (Exception Excep){
			System.err.println(Excep);
			Excep.printStackTrace();
		}
		return pases;
	}
	
	/* Funcion que devuelve los datos de una Fecha concreta 
	 * @param Id Id de la fecha concreta
	 * @return fechaRequest DTO de Fecha con los datos de la fecha concreta
	 * 
	 */
	
	public FechaDTO SolicitarFecha(int Id){
		
		FechaDTO fechaRequest = new FechaDTO();

		try(InputStream InSt = new FileInputStream(RutaSQL)){
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("solicitarInfoFechaAdmin");
			cuestion=cuestion.replaceAll("varid", Integer.toString(Id));
			Statement declaracion = conexion.createStatement();
			ResultSet resultado = (ResultSet) declaracion.executeQuery(cuestion);
			
			if(resultado.next()){
				
				String identificacion = resultado.getString("Id");
				String fechaDB = resultado.getString("Fecha");
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime fecha = LocalDateTime.parse(fechaDB, formato);		
				fechaRequest.setId(Integer.parseInt(identificacion));
				fechaRequest.setFecha(fecha);
			}
			
			if (declaracion != null){ 
				declaracion.close(); 
			}
			
			
		} catch (Exception Excep){
			System.err.println(Excep);
			Excep.printStackTrace();
		}
		
		return fechaRequest;	
	}
	
	/* Funcion que devuelve los datos de un pase concreto
	 * @param Id Id del pase concreto
	 * @return paseRequest DTO de Pase con los datos del pase concreto
	 * 
	 */

	public PaseDTO SolicitarPase(int Id){
		
		PaseDTO paseRequest = new PaseDTO();

		try(InputStream InSt = new FileInputStream(RutaSQL)){
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("solicitarInfoPases");
			cuestion=cuestion.replaceAll("varid", Integer.toString(Id));
			
			Statement declaracion = conexion.createStatement();
			ResultSet resultado = (ResultSet) declaracion.executeQuery(cuestion);
			
			if(resultado.next()){
				
				String iden = resultado.getString("Id");
				String fecha = resultado.getString("InicioFecha");
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime inicioFecha = LocalDateTime.parse(fecha, formato);
				String diasS = resultado.getString("DiasS");
				fecha = resultado.getString("FinalFecha");
				formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime finalFecha = LocalDateTime.parse(fecha, formato);
				
				paseRequest.setId(Integer.parseInt(iden));
				paseRequest.setInicioFecha(inicioFecha);
				paseRequest.setDia(diasS);
				paseRequest.setInicioFecha(finalFecha);
			}
			if (declaracion != null){ 
				declaracion.close(); 
			}
			
			
		} catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
		
		return paseRequest;	
	}

	/* Funcion que devuelve los datos de todas las Fechas de un espectaculo Multiple concreto
	 * @param Id Id del espectaculo concreto
	 * @return fechasSolicitadas Vector de fechasSolicitadas con todas las fechasSolicitadas asociadas al espectaculo
	 * 
	 */
	
	public ArrayList<FechaDTO> requestFechasEsp(int Id){
		
		ArrayList<FechaDTO> fechasSolicitadas = new ArrayList<FechaDTO>();
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
		
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("solicitarFechasEspectaculoPaseMultiple");
			cuestion = cuestion.replaceAll("varid", Integer.toString(Id));
			
			Statement declaracion = conexion.createStatement();
			ResultSet resultado = (ResultSet) declaracion.executeQuery(cuestion);
			
			while (resultado.next()) {
				
				String identificacion = resultado.getString("Id");
				String fechasBaseD =  resultado.getString("Fecha");
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime fecha = LocalDateTime.parse(fechasBaseD, formato);
				fechasSolicitadas.add(new FechaDTO(Integer.parseInt(identificacion), fecha));
			}
			if (declaracion != null){ 
				
				declaracion.close(); 
			}

			
		}
		catch (Exception Excep){
		
			System.err.println(Excep);
			Excep.printStackTrace();
		}
		return fechasSolicitadas;
	}
	
	/* Funcion que solicita todas las fechas de Espectaculo Temporada
	 * @param Id 
	 * @return pases 
	 */
	
	
	public ArrayList<PaseDTO> SolicitarPasesEspectaculoTemporada(int Id){
	
		ArrayList<PaseDTO> pases = new ArrayList<PaseDTO>();
		
		try(InputStream InSt = new FileInputStream(RutaSQL)){
			
			Properties propiedades = new Properties();
			propiedades.load(InSt);
			String cuestion = propiedades.getProperty("solicitarPasesEspectaculoTemporada");
			cuestion = cuestion.replaceAll("varid", Integer.toString(Id));
			Statement declaracion = conexion.createStatement();
			ResultSet resultado = (ResultSet) declaracion.executeQuery(cuestion);
			
			while (resultado.next()){
				
				String identificacion = resultado.getString("Id");
				String fechasBaseD =  resultado.getString("InicioFecha");
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime inicioFecha = LocalDateTime.parse(fechasBaseD, formato);
				String diasS = resultado.getString("DiasS");
				fechasBaseD =  resultado.getString("FinalFecha");		
				formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime finalFecha = LocalDateTime.parse(fechasBaseD, formato);
				pases.add(new PaseDTO(Integer.parseInt(identificacion), inicioFecha, diasS,finalFecha));
			}
			if (declaracion != null){ 
				
				declaracion.close(); 
			}

			
		} 
		catch (Exception Excep){
			
			System.err.println(Excep);
			Excep.printStackTrace();
		}
		return pases;
	}
}
