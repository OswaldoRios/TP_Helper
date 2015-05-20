package conexion;
//referencias de conexion

import java.io.*;
import java.sql.*;
import javax.swing.*;

public class main {

    static Connection conn = null;
    static Statement st = null;
    static ResultSet rs = null;

    static String bd = "TP_H";
    static String login = "TP_H";
    static String password = "9406J";
    static String url = "jdbc:oracle:thin:@localhost:1521:xe";

    public static Connection Enlace(Connection conn) throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(url, login, password);
            System.out.println("Si se pudo");
        } catch (ClassNotFoundException e) {
            System.out.print("Clase no encontrada");
        }
        return conn;
    }

    public static Statement sta(Statement st) throws SQLException {
        conn = Enlace(conn);
        st = conn.createStatement();
        return st;
    }

    public static ResultSet EnlEst(ResultSet rs) throws SQLException {
        st = sta(st);
        rs = st.executeQuery("select * from USUARIOS");
        return rs;
    }

    public static void main(String[] args) throws SQLException {
        Enlace(conn);
    }
}
