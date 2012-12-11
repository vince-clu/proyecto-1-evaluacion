package MySQL;

import java.awt.List;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class MySql {

    protected static Connection con = null;

    public boolean AbrirConexion() throws Exception {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String urlOdbc = "jdbc:mysql://localhost:3307/proyecto";
            con = (java.sql.DriverManager.getConnection(urlOdbc, "root", "rootroot"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("No se ha podido establecer la conexion" + e.getMessage());
        }
        if (con == null) {
            return false;
        } else {
            return true;
        }
    }

    public void CerrarConexion(Connection con) throws Exception {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("No se ha podido cerrar la conexion" + e.getMessage());
        }
    }

    public void InitTrans() throws Exception {
        try {
            con.setAutoCommit(false);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("No va el init" + e.getMessage());
        }

    }

    public void CommitTrans() throws Exception {
        try {
            con.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("No va el commit" + e.getMessage());
        }

    }

    public void RollbackTrans() throws Exception {
        try {
            con.rollback();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("No va el rollback" + e.getMessage());
        }

    }

    public boolean DeleteOne(int id, String tabla) throws Exception {
        try {
            Statement statement = null;
            statement = con.createStatement();
            String SqlBorrar = "Delete from " + tabla + " where id=" + id;
            int i = statement.executeUpdate(SqlBorrar);
            if (i == 0) {

                System.out.println("Prueba: " + i);


                return false;
            } else {

                System.out.println("Prueba: " + i);
                return true;

            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("No va el deleteOne" + e.getMessage());
        }

    }

    public boolean UpdateOne(int id, String nombre, String tabla) throws Exception {
        try {
            Statement statement = null;
            statement = con.createStatement();
            String SqlUpdateOne = "update " + tabla + " set nombre='" + nombre + "' WHERE id=" + id;
            System.out.println(SqlUpdateOne);
            int i = statement.executeUpdate(SqlUpdateOne);
            if (i == 0) {

                System.out.println("Prueba: " + i);


                return false;
            } else {

                System.out.println("Prueba: " + i);
                return true;

            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("No va el UpdateOne" + e.getMessage());
        }

    }

    public int GetOne(String tabla) throws Exception {
        ResultSet resultset = null;
        PreparedStatement stmt = null;
        String SqlGetOne = "select * from " + tabla + " where 1=1";
        System.out.println(SqlGetOne);
        int i = 0;
        try {

            stmt = con.prepareStatement(SqlGetOne);
            resultset = stmt.executeQuery();


            if (resultset.next()) {
                i = resultset.getInt("id");
                return i;
            } else {

                return 0;
            }


        } catch (SQLException e) {


            e.getStackTrace();
            throw new Exception("No va el GetOne " + e.getMessage());
        }

    }

    public int GetPages(String tabla, String where, int limite) throws Exception {
        ResultSet resultset = null;
        PreparedStatement stmt = null;

        String SqlGetPages = "select * from " + tabla + " where " + where ;



        System.out.println("sql: " + SqlGetPages);
        int i = 0;
        int p = 0;

        try {

            stmt = con.prepareStatement(SqlGetPages);
            resultset = stmt.executeQuery();
            while (resultset.next()) {
                System.out.println("b" + i);
                i++;
            }

            if (i % limite > 0) {

                p = (i / limite) + 1;
            }
        } catch (SQLException e) {


            e.getStackTrace();
            throw new Exception("No va el GetPages " + e.getMessage());
        }
        return p;

    }

    public ResultSet GetPage(String tabla, int limite, String condiciones, String ordena, int pagina ) throws Exception {


        int inicio = (pagina * limite) - limite;

        ResultSet resultset = null;
        PreparedStatement stmt = null;

//        if (condiciones != null) {
//
//            wheres += condiciones.get(0);
//            System.out.println("ordenes: " + wheres);
//            for (int i = 1; i < condiciones.size(); i++) {
//                wheres += " and " + condiciones.get(i);
//            }
//            System.out.println("ordenes: " + wheres);
//        }

//        if (ordenacion != null) {
//            ordenes2 += ordenacion.get(0);
//            System.out.println("ordenes: " + ordenes2);
//            for (int i = 1; i < ordenacion.size(); i++) {
//                ordenes2 += " and " + ordenacion.get(i);
//            }

//       }


        String SqlGetPage = "select * from " + tabla + " where " + condiciones + ordena
                + " limit " + inicio + " , " + limite ;




        System.out.println("sql: " + SqlGetPage);
        try {

            stmt = con.prepareStatement(SqlGetPage);
            resultset = stmt.executeQuery();

        } catch (SQLException e) {


            e.getStackTrace();
            throw new Exception("No va el GetPage " + e.getMessage());
        }
        return resultset;



    }

    public int GetId(String tabla, ArrayList where) throws Exception {
        ResultSet resultset = null;
        PreparedStatement stmt = null;

        String SqlGetId = "select * from " + tabla + " where 1=1 ";

        if (where.size() >= 1) {
            for (int i = 0; i < where.size(); i++) {
                SqlGetId += " and " + where.get(i);
            }
        }

        System.out.println("sql: " + SqlGetId);

        int p = 0;
        try {

            stmt = con.prepareStatement(SqlGetId);
            resultset = stmt.executeQuery();
            while (resultset.next()) {

                p = resultset.getInt("id");

            }


        } catch (SQLException e) {


            e.getStackTrace();
            throw new Exception("No va el GetOne " + e.getMessage());
        }
        return p;

    }

    public HashMap GetRow(String tabla, String where) throws Exception {

        HashMap row = new HashMap();
        ResultSet resultset = null;
        PreparedStatement stmt = null;

        String SqlGetRow = "select * from " + tabla + " where " + where;
        int i = 0;
        String nombre;

        try {

            stmt = con.prepareStatement(SqlGetRow);
            resultset = stmt.executeQuery();


            if (resultset.next()) {
                i = resultset.getInt("id");
                nombre = resultset.getString("nombre");
                row.put("id", i);
                row.put("nombre", nombre);


            } else {

                return null;
            }


        } catch (SQLException e) {


            e.getStackTrace();
            throw new Exception("No va el GetOne " + e.getMessage());
        }
        return row;

    }

    public int GetCount(String tabla, String campo, String valor) throws Exception {
        ResultSet resultset = null;
        PreparedStatement stmt = null;
        String SqlGetCount = "select count(id) as resultado from " + tabla + " where " + campo + " = '" + valor + "'";
        int i = 0;
        try {

            stmt = con.prepareStatement(SqlGetCount);
            resultset = stmt.executeQuery();


            if (resultset.next()) {
                i = resultset.getInt("resultado");
                return i;
            } else {

                return 0;
            }


        } catch (SQLException e) {


            e.getStackTrace();
            throw new Exception("No va el GetOne " + e.getMessage());
        }

    }

    public ResultSet Get(String sql) throws Exception {
        ResultSet resultset = null;
        PreparedStatement stmt = null;
        String SqlGet = sql;
        try {

            stmt = con.prepareStatement(SqlGet);
            resultset = stmt.executeQuery();
            return resultset;



        } catch (SQLException e) {


            e.getStackTrace();
            throw new Exception("No va el Get " + e.getMessage());
        }

    }
    


    public int execute(String sql) {
        ResultSet resultset = null;
        PreparedStatement stmt = null;

        int numFilas = 0;
        try {
            if (con != null) {
                String primer = sql.substring(0, 1);
                stmt = con.prepareStatement(sql);
                switch (primer) {
                    case "U":
                    case "u":
                        stmt = con.prepareStatement(sql);
                        System.out.println(sql);
                        numFilas = stmt.executeUpdate(sql);
                        break;
                    case "I":
                    case "i":
                        stmt = con.prepareStatement(sql);
                        numFilas = stmt.executeUpdate(sql);
                        break;
                    case "D":
                    case "d":
                        numFilas = stmt.executeUpdate(sql);
                }
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return numFilas;
    }
}
