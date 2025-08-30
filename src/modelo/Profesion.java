/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author VELA
 */
public class Profesion {
    Conexion cn;
    private String profesion;
    private int id, id_profesion;

    public Profesion() {
    }

    public Profesion(String profesion, int id, int id_profesion) {
        this.profesion = profesion;
        this.id = id;
        this.id_profesion = id_profesion;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_profesion() {
        return id_profesion;
    }

    public void setId_profesion(int id_profesion) {
        this.id_profesion = id_profesion;
    }
    
    public DefaultComboBoxModel leer(){
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        try{
            cn  = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT id_profesion, profesion FROM profesion;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            combo.addElement("0) Elija Profesión");
            while(consulta.next()){
                combo.addElement(consulta.getString("id_profesion") + ") " + consulta.getString("profesion"));
            }
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return combo;
    }
}
