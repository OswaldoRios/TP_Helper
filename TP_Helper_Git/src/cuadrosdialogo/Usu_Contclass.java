/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuadrosdialogo;

import PaginaPrincipalAdmin.Interfaz;
import PaginaPrincipalAdmin.PaginaPrincipalAdmin;
import conexion.main;
import static conexion.main.sta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author javier
 */
public class Usu_Contclass {
    static Connection conn = null;
    static Statement st = null;
    static ResultSet rs = null;
    private static String usu2=null;
    
    public void conexionUsu(String usu, char[] cont2){
        String nom, cont;
            try {
                //ejecuta la conexion
                conn = main.Enlace(conn);
                //ejecuta la consulta
                st = sta(st);
                rs = st.executeQuery("select *\n"
                        + "from USUARIOS\n"
                        + "where USUARIO like '" + usu + "'");
                rs.next();
                nom = rs.getString(2);
                cont = rs.getString(3);

                if (nom.equals(usu) && authenticate(cont,cont2)) {
                    usu2 = rs.getObject(1).toString();
                    System.out.println("valor de usu "+usu2);
                    PaginaPrincipalAdmin pp = new PaginaPrincipalAdmin();
                    pp.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Contraseña o Usuario incorrecto", "ERROR", 1);
                }
                rs.close();
                conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }
    
     public boolean authenticate(String cont, char[] cont2) {

        char passArray[] = cont2;
        for (int i = 0; i < passArray.length; i++) {
            char c = passArray[i];
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }

        String pass = new String(passArray);

        if (pass.equals(cont)) {
            return true;
        } else {
            return false;
        }
     }

    public String getUsu2() {
        System.out.println("Valor de usu2"+usu2);
        return usu2;
    }
}