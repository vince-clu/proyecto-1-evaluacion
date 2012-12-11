package Daos;

import MySQL.MySql;
import Pojos.UsuarioPojo;
import java.sql.ResultSet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vesprada
 */
public class UsuarioDao {

    public UsuarioPojo autentificar(UsuarioPojo pojo) {
        String sql;
        try {

            MySql con = new MySql();

            boolean i = con.AbrirConexion();
            sql = "select * from usuario where login= '" + pojo.getUser() + "' and password = '" + pojo.getPass() + "'";
  
            ResultSet result = con.Get(sql);

            if (result.next()) {
                pojo.setId(result.getInt("id"));
                pojo.setNombre(result.getString("nombre"));
                pojo.setApe1(result.getString("ape1"));
                pojo.setApe2(result.getString("ape2"));
                pojo.setUser(result.getString("login"));
                pojo.setPass(result.getString("password"));
                pojo.setEmail(result.getString("email"));
                pojo.setTelefono(result.getString("telefono"));
                pojo.setId_tipo_usuario(result.getInt("id_tipo_usuario"));
                pojo.setPass(result.getString("password"));                             
            }



        } catch (Exception e) {
        }
        return pojo;


    }
}
