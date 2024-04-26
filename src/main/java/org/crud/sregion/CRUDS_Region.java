package org.crud.sregion;

import java.io.InterruptedIOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sound.midi.VoiceStatus;

/*
 * APP JAVA ---> JDBC ---> ORACLE
 * DRIVER
 * URL
 * PASS
 * USER
 */

public class CRUDS_Region {
	public static Connection connection = null;
	public static String driver = "oracle.jdbc.driver.OracleDriver";
	public static String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	
	public static void connectDataBaseOracle (){
		try {
			Class.forName(driver).newInstance();
			System.out.println("Cargo driver: ojdbc6.jar");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
		
		try {
			connection = DriverManager.getConnection(URL, "System", "Oracle99");
			System.out.println("Conexion exitosa");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public static void create(int id, String name) {
		try {
			connectDataBaseOracle();
			String sql = "INSERT INTO S_REGION (ID, NAME) VALUES (?, ?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.execute();
			System.out.println("Insercion exitosa: " + id + ", " + name);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
	}
	
	public static void read() {
		try {
			connectDataBaseOracle();
			String sql = "SELECT * FROM S_REGION";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				System.out.println("ID: " + result.getString("ID") + ", NAME: " + result.getString("NAME"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
	}
	
	public static void read_individual(int id) {
		try {
			connectDataBaseOracle();
			String sql = "SELECT * FROM S_REGION WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				System.out.println("ID: " + result.getString("ID") + ", NAME: " + result.getString("NAME"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
	}
	
	public static void update(String name, int id) {
		try {
			connectDataBaseOracle();
			String sql = "UPDATE S_REGION SET NAME = (?) WHERE ID = (?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, id);
			ps.execute();
				System.out.println("Actualizacion exitosa: " + id + ", " + name);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
	}
	
	public static void delete(int id){
		try {
			connectDataBaseOracle();
			/*
			String sql = "DELETE FROM S_REGION WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			//System.out.println(ps.execute());
			int status = ps.executeUpdate();
			connection.commit();
			if (status > 0)	System.out.println("Registro eliminado exitosamente: " + id);
			else System.out.println("No se encontro registro con id: " + id);
			*/
			
			// Sentencia SQL para eliminar un registro
            String sql = "DELETE FROM S_REGION WHERE id = ?";

            // Preparar la declaración
            PreparedStatement statement = connection.prepareStatement(sql);

            // Asignar valores a los parámetros de la declaración
            int idAEliminar = 7; // ID del registro a eliminar
            statement.setInt(1, idAEliminar);

            // Ejecutar la sentencia SQL de eliminación
            int filasAfectadas = statement.executeUpdate();

            // Verificar si se eliminó correctamente
            if (filasAfectadas > 0) {
                System.out.println("Se eliminó el registro con éxito.");
            } else {
                System.out.println("No se encontró ningún registro con el ID especificado.");
            }
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
	}
		
	public static void main(String[] args) {
		//create(4, "Tijuana");
		//update("D.F.", 6);
		read();
		//read_individual(9);
		//delete(7);
	}
}

