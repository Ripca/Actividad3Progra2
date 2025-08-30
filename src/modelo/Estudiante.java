/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author VELA
 */
public class Estudiante extends Persona {
    Conexion cn;
    private String carnet;
    private int id_estudiante;

    public Estudiante() {
    }

    public Estudiante(String carnet, String nit, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento, Boolean genero) {
        super(Integer.valueOf(telefono), nit, nombres, apellidos, direccion, fecha_nacimiento, genero);
        this.carnet = carnet;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public int getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(int id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    @Override
    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT id_estudiante, carnet, nit, nombres, apellidos, direccion, telefono, fecha_nacimiento, genero FROM estudiante;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"Id", "Carnet", "Nit", "Nombres", "Apellidos", "Direccion", "Telefono", "Nacimiento", "Genero"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[9];
            while (consulta.next()) {
                datos[0] = consulta.getString("id_estudiante");
                datos[1] = consulta.getString("carnet");
                datos[2] = consulta.getString("nit");
                datos[3] = consulta.getString("nombres");
                datos[4] = consulta.getString("apellidos");
                datos[5] = consulta.getString("direccion");
                datos[6] = consulta.getString("telefono");
                datos[7] = consulta.getString("fecha_nacimiento");
                datos[8] = consulta.getBoolean("genero") ? "Masculino" : "Femenino";
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return tabla;
    }

    @Override
    public void crear() {
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "INSERT INTO estudiante(carnet, nit, nombres, apellidos, direccion, telefono, fecha_nacimiento, genero) VALUES(?,?,?,?,?,?,?,?)";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getCarnet());
            parametro.setString(2, getNit());
            parametro.setString(3, getNombres());
            parametro.setString(4, getApellidos());
            parametro.setString(5, getDireccion());
            parametro.setString(6, getTelefono() != null ? String.valueOf(getTelefono()) : null);
            parametro.setString(7, getFecha_nacimiento());
            parametro.setBoolean(8, getGenero() != null ? getGenero() : false);
            int executar = parametro.executeUpdate();
            System.out.println("Se insertó " + executar + " registro");
            cn.cerrar_conexion();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    @Override
    public void actualizar() {
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "UPDATE estudiante SET carnet=?, nit=?, nombres=?, apellidos=?, direccion=?, telefono=?, fecha_nacimiento=?, genero=? WHERE id_estudiante = ?;";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getCarnet());
            parametro.setString(2, getNit());
            parametro.setString(3, getNombres());
            parametro.setString(4, getApellidos());
            parametro.setString(5, getDireccion());
            parametro.setString(6, getTelefono() != null ? String.valueOf(getTelefono()) : null);
            parametro.setString(7, getFecha_nacimiento());
            parametro.setBoolean(8, getGenero() != null ? getGenero() : false);
            parametro.setInt(9, getId_estudiante());
            int executar = parametro.executeUpdate();
            System.out.println("Se actualizó " + executar + " registro");
            cn.cerrar_conexion();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    @Override
    public void borrar() {
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "DELETE FROM estudiante WHERE id_estudiante = ?;";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId_estudiante());
            int executar = parametro.executeUpdate();
            System.out.println("Se eliminó " + executar + " registro");
            cn.cerrar_conexion();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
