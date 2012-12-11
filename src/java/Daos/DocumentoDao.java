/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import MySQL.MySql;

import Pojos.DocumentoPojo;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.LinkedList;

/**
 *
 * @author VINCE
 */
public class DocumentoDao {

    public static String dateToMySQLDate(Date fecha) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(fecha);
    }

    public static LinkedList<DocumentoPojo> mostrarDocumento(DocumentoPojo pojo) {
        String sql;
        LinkedList<DocumentoPojo> lista = new LinkedList();

        try {

            MySql con = new MySql();

            boolean i = con.AbrirConexion();

            sql = "select * from documento where id_usuario=" + pojo.getId_usuario();


            ResultSet result = con.Get(sql);

            while (result.next()) {
                DocumentoPojo pojo2 = new DocumentoPojo();
                pojo2.setId(result.getInt("id"));
                pojo2.setTitulo(result.getString("titulo"));
                pojo2.setContenido(result.getString("contenido"));
                pojo2.setFecha(result.getDate("fecha"));
                pojo2.setNota(result.getInt("nota"));
                pojo2.setId_usuario(pojo.getId_usuario());
                pojo2.setEtiquetas(result.getString("etiquetas"));
                pojo2.setPrivado(result.getBoolean("privado"));
                lista.add(pojo2);

            }

        } catch (Exception e) {
        }
        return lista;
    }
}
