/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import MySQL.MySql;
import Pojos.DocumentoPojo;
import Pojos.EntregaPojo;
import java.util.Date;

import java.sql.Time;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

/**
 *
 * @author vesprada
 */
public class EntregaDao {

    public static String dateToMySQLDate(Date fecha) {

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(fecha);

    }

    public static LinkedList<EntregaPojo> entregarymostrarEntrega(EntregaPojo pojo) {

//        Este codigo esta comentado porque para introducir fecha y hora dentro de MySql, tenemos que recurrir
//        a DateTime o Timestamp
        
        
//        Date fecha = new Date();
//        java.sql.Timestamp momentoTimestamp = new java.sql.Timestamp(fecha.getTime());
//        System.out.println("a "+momentoTimestamp);

//        String fechaActual = dateToMySQLDate (fecha);
//        System.out.println("b "+fechaActual);
//       String sqlparafechayhora = " insert into entrega(id, id_actividad,
//        id_documento, nota, entrega) values(null, 2,3,5,'2012-12-12 8:54:32')";




        String sqlMuestra;
        String sqlInserta;
        MySql con = new MySql();
        LinkedList<EntregaPojo> lista = new LinkedList();
        int id_documento = pojo.getId_documento();
        int id_actividad = pojo.getId_actividad();


        try {

            //Podria cambiar now() introduciendo momentoTimestamp, tanto para un date, como para un datatime como un timestamp
            sqlInserta = "insert into entrega (id, id_documento, id_actividad,nota,entrega) values(" + null + "," + id_documento + "," + id_actividad + "," + "0," + "now())";

            con.execute(sqlInserta);
            sqlMuestra = "select distinct e.id, e.id_actividad, e.id_documento, e.entrega"
                    + " from entrega e, documento d where e.id_documento in (select id from"
                    + " documento where id_usuario=" + pojo.getId_usuario() + ")";

            ResultSet result = con.Get(sqlMuestra);
            System.out.println("sql: "+sqlMuestra);
            while (result.next()) {
                EntregaPojo pojo2 = new EntregaPojo();
                pojo2.setId(result.getInt("id"));
                System.out.println(pojo2.getId());
                pojo2.setId_actividad(result.getInt("id_actividad"));
                System.out.println(pojo2.getId_actividad());
                pojo2.setId_documento(result.getInt("id_documento"));
                System.out.println(pojo2.getId_documento());
                pojo2.setEntrega(result.getDate("entrega"));
                System.out.println(pojo2.getEntrega());

                lista.add(pojo2);

            }

        } catch (Exception e) {
        }
        return lista;
    }
    
    
    public static LinkedList<EntregaPojo> mostrarEntregas(EntregaPojo pojo) {




        String sqlMuestra;
    
        MySql con = new MySql();
        LinkedList<EntregaPojo> lista = new LinkedList();



        try {

            //Podria cambiar now() introduciendo momentoTimestamp, tanto para un date, como para un datatime como un timestamp

            sqlMuestra = "select distinct e.id, e.id_actividad, e.id_documento, e.entrega"
                    + " from entrega e, documento d where e.id_documento in (select id from"
                    + " documento where id_usuario=" + pojo.getId_usuario() + ")";

            ResultSet result = con.Get(sqlMuestra);

            while (result.next()) {
                EntregaPojo pojo2 = new EntregaPojo();
                pojo2.setId(result.getInt("id"));
                System.out.println(pojo2.getId());
                pojo2.setId_actividad(result.getInt("id_actividad"));
                System.out.println(pojo2.getId_actividad());
                pojo2.setId_documento(result.getInt("id_documento"));
                System.out.println(pojo2.getId_documento());
                pojo2.setEntrega(result.getDate("entrega"));
                System.out.println(pojo2.getEntrega());

                lista.add(pojo2);

            }

        } catch (Exception e) {
        }
        return lista;
    }
}
