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
public class Docente extends Persona {
    Conexion cn;
    private String codigo;
    private float salario;
    private Integer id_profesion;
    private int id_docente;

    public Docente() {}

    public Docente(String codigo, float salario, Integer id_profesion,
                   String nombres, String apellidos, String direccion, String telefono,
                   String fecha_nacimiento, Boolean genero) {
        super(Integer.valueOf(telefono), null, nombres, apellidos, direccion, fecha_nacimiento, genero);
        this.codigo = codigo;
        this.salario = salario;
        this.id_profesion = id_profesion;
    }

    // --- Getters y Setters ---
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public float getSalario() { return salario; }
    public void setSalario(float salario) { this.salario = salario; }

    public Integer getId_profesion() { return id_profesion; }
    public void setId_profesion(Integer id_profesion) { this.id_profesion = id_profesion; }

    public int getId_docente() { return id_docente; }
    public void setId_docente(int id_docente) { this.id_docente = id_docente; }

    @Override
    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT d.id_docente, d.codigo, d.nit, d.nombres, d.apellidos, d.direccion, d.telefono, d.fecha_nacimiento, d.genero, d.salario, " +
                           "CONCAT(d.id_profesion, ') ', p.profesion) AS profesion " +
                           "FROM docente AS d INNER JOIN profesion AS p ON d.id_profesion = p.id_profesion;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);

            String encabezado[] = {"Id", "Codigo", "Nit", "Nombres", "Apellidos", "Direccion", "Telefono", "Nacimiento", "Genero", "Salario", "Profesion"};
            tabla.setColumnIdentifiers(encabezado);

            String datos[] = new String[11];
            while (consulta.next()) {
                datos[0] = consulta.getString("id_docente");
                datos[1] = consulta.getString("codigo");
                datos[2] = consulta.getString("nit");
                datos[3] = consulta.getString("nombres");
                datos[4] = consulta.getString("apellidos");
                datos[5] = consulta.getString("direccion");
                datos[6] = consulta.getString("telefono");
                datos[7] = consulta.getString("fecha_nacimiento");
                datos[8] = consulta.getBoolean("genero") ? "Masculino" : "Femenino";
                datos[9] = consulta.getString("salario");
                datos[10] = consulta.getString("profesion");
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error al leer docentes: " + ex.getMessage());
        }
        return tabla;
    }

    @Override
    public void crear() {
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "INSERT INTO docente(codigo, nit, nombres, apellidos, direccion, telefono, fecha_nacimiento, genero, salario, id_profesion) " +
                           "VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement parametro = cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getCodigo());
            parametro.setString(2, getNit());
            parametro.setString(3, getNombres());
            parametro.setString(4, getApellidos());
            parametro.setString(5, getDireccion());
            parametro.setString(6, getTelefono() != null ? String.valueOf(getTelefono()) : null);
            parametro.setString(7, getFecha_nacimiento());
            parametro.setBoolean(8, getGenero() != null ? getGenero() : false);
            parametro.setFloat(9, getSalario());
            parametro.setInt(10, getId_profesion());
            int ejecutar = parametro.executeUpdate();
            System.out.println("Se insertó " + ejecutar + " registro");
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error al crear docente: " + ex.getMessage());
        }
    }

    @Override
    public void actualizar() {
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "UPDATE docente SET codigo=?, nit=?, nombres=?, apellidos=?, direccion=?, telefono=?, fecha_nacimiento=?, genero=?, salario=?, id_profesion=? " +
                           "WHERE id_docente=?";
            PreparedStatement parametro = cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getCodigo());
            parametro.setString(2, getNit());
            parametro.setString(3, getNombres());
            parametro.setString(4, getApellidos());
            parametro.setString(5, getDireccion());
            parametro.setString(6, getTelefono() != null ? String.valueOf(getTelefono()) : null);
            parametro.setString(7, getFecha_nacimiento());
            parametro.setBoolean(8, getGenero() != null ? getGenero() : false);
            parametro.setFloat(9, getSalario());
            parametro.setInt(10, getId_profesion());
            parametro.setInt(11, getId_docente());
            int ejecutar = parametro.executeUpdate();
            System.out.println("Se actualizó " + ejecutar + " registro");
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar docente: " + ex.getMessage());
        }
    }

    @Override
    public void borrar() {
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "DELETE FROM docente WHERE id_docente=?";
            PreparedStatement parametro = cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId_docente());
            int ejecutar = parametro.executeUpdate();
            System.out.println("Se eliminó " + ejecutar + " registro");
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error al borrar docente: " + ex.getMessage());
        }
    }
}
