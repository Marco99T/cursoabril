package org.crud.producto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CRUD {

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
		}
		
		try {
			connection = DriverManager.getConnection(URL, "System", "Oracle99");
			System.out.println("Conexion exitosa");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void create(int id, String nombre, double precio) {
		try {
			connectDataBaseOracle();
			String sql = "INSERT INTO PRODUCTO (ID, NOMBRE, PRECIO) VALUES (?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, nombre);
			ps.setDouble(3, precio);
			ps.execute();
			System.out.println("Insercion exitosa: " + id + ", " + nombre + ", " + precio);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public static void read() {
		try {
			connectDataBaseOracle();
			String sql = "SELECT * FROM PRODUCTO";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				System.out.println("ID: " + result.getString("ID") + ", NOMBRE: " + result.getString("NOMBRE") + ", PRECIO: " + result.getString("PRECIO"));
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
			String sql = "SELECT * FROM PRODUCTO WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				System.out.println("ID: " + result.getString("ID") + ", NOMBRE: " + result.getString("NOMBRE") + ", PRECIO: " + result.getString("PRECIO"));
			}
			else {
				System.out.println("Registro no encontrado.");
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
			String sql = "UPDATE PRODUCTO SET NOMBRE = (?) WHERE ID = (?)";
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
			
			String sql = "DELETE FROM PRODUCTO WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			//System.out.println(ps.execute());
			int status = ps.executeUpdate();
			if (status > 0)	System.out.println("Registro eliminado exitosamente: " + id);
			else System.out.println("No se encontro registro con id: " + id);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
		
	public static void main(String[] args) {
		//create(7, "Takis-Fuego", 10.78);
		//update("Churrumais", 1000);
		read();
		//read_individual(7);
		//delete(7);
	}
}
