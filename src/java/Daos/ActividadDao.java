/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import MySQL.MySql;
import Pojos.ActividadPojo;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.LinkedList;

/**
 *
 * @author vesprada
 */
public class ActividadDao {

    public static String dateToMySQLDate(Date fecha) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(fecha);
    }

    public static LinkedList<ActividadPojo> mostrarActividad() {
        String sql;
        LinkedList<ActividadPojo> lista = new LinkedList();
        try {

            MySql con = new MySql();

            boolean i = con.AbrirConexion();
            int pagina = 1;
            int limite = 10;
            String ordena = " order by fecha ";

            ResultSet result = con.GetPage("actividad", limite, "activo=true", ordena, pagina);

            while (result.next()) {
                ActividadPojo pojo = new ActividadPojo();
                pojo.setId(result.getInt("id"));
                pojo.setEnunciado(result.getString("enunciado"));
                pojo.setFecha(result.getDate("fecha"));
                System.out.println("fecha: " + result.getDate("fecha"));
                pojo.setEvaluacion(result.getInt("evaluacion"));
                pojo.setActivo(result.getBoolean("activo"));
                lista.add(pojo);

            }

        } catch (Exception e) {
        }
        return lista;

    }

    public static LinkedList<ActividadPojo> mostrarActividadSiguiente(int pagina) {
        String sql;
        LinkedList<ActividadPojo> lista = new LinkedList();
        try {

            MySql con = new MySql();

            boolean i = con.AbrirConexion();

            int limite = 10;
            String ordena = " order by fecha ";

            ResultSet result = con.GetPage("actividad", limite, "activo=true", ordena, pagina);

            while (result.next()) {
                ActividadPojo pojo = new ActividadPojo();
                pojo.setId(result.getInt("id"));
                pojo.setEnunciado(result.getString("enunciado"));
                pojo.setFecha(result.getDate("fecha"));
                System.out.println("fecha: " + result.getDate("fecha"));
                pojo.setEvaluacion(result.getInt("evaluacion"));
                pojo.setActivo(result.getBoolean("activo"));
                lista.add(pojo);

            }

        } catch (Exception e) {
        }
        return lista;

    }
}
