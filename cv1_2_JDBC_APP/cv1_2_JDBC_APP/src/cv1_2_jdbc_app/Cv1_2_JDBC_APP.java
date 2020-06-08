/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv1_2_jdbc_app;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <a href = 'http://www.kaivt.elf.stuba.sk/Predmety/B-VSA/CV1' >http://www.kaivt.elf.stuba.sk/Predmety/B-VSA/CV1</a>
 * praca s JDBC
 * executeQuery, executeUpdate, DriverManager, SQL.exceptions
 * spravene ulohy 3/3
 * 
 * @author vsa
 */
public class Cv1_2_JDBC_APP {
    static java.sql.Statement st;

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //toto info najdem v  services\databases\jdbc:derby..sample\properties
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
         st =  con.createStatement();
        //pridať do lib jdbc derby driver 
        //otestovat
        ResultSet rs = st.executeQuery("SELECT count(*) FROM kniha");
        while (rs.next()) {
            System.out.println("" + rs.getInt(1));
        }
        // 1 uloha, najdi cenu
        double cena = cenaKnihy("stara");
        System.out.println("cena knihy je " + cena);
        // 2 uloha - pridaj knihu
        //select + bez sql.exception from update
        boolean insert_res = pridajKnihu("warkraft123",1234.45 );
        System.out.println("vysledok operacie pridaj knihu");
        System.out.println((insert_res) ? "kniha vlozena" : "kniha uz existuje ");
        //select + catch sql.exception from update
        insert_res = pridajKnihu2("ss4s",44);  
        System.out.println("vysledok operacie pridaj knihu II");
        System.out.println((insert_res) ? "kniha vlozena" : "kniha uz existuje ");
        //3  uloha - sprav zlavu
        zlava("ss4s");
        
    }
    

   /**
    * 3. Implementácia JDBC aplikácie, ktorá modifikuje údaje v DB
     nájde v DB knihu so zadaným menom a vráti jej cenu. Ak neexistuje taká kniha vráti -1 a vypíše spávu "Knihu nemáme" 
    * @param meno meno knihy
    * @return  cena knihy
    * @throws SQLException 
    */
    static double cenaKnihy(String meno) throws SQLException{
        double out = -1;
                ResultSet rs = st.executeQuery("SELECT cena FROM kniha where nazov = '" + meno + "'");
        if (rs.next()) {
            out = rs.getDouble(1);
        }
        else {
            System.out.println("Knihu nemáme");
        }
        return out;
    }
    
    

    /**
     *     pridá do DB knihu s daným menom a cenou. Ak kniha s daným menom v DB už existuje, vráti false. <br>
     * Check with select if book axists get the id of book with largest isbn (last item) increment him and insert new book to DB
     * @param meno
     * @param cena
     * @return
     * @throws SQLException 
     */
    static boolean pridajKnihu(String meno, double cena) throws SQLException{
        boolean result = false;
        ResultSet rs = st.executeQuery("SELECT ISBN, cena FROM kniha where nazov = '" + meno + "'");
        //kniha neexistuje
        if (!rs.next()) {
            //convert id and inc
            rs = st.executeQuery("SELECT max(isbn) as isbn from kniha");
            if (rs.next()) {
                String isbn = rs.getString("isbn");
                String NEW_isbn ;
                if(isbn == null){
                    NEW_isbn = "a0";
                }
                else{
                    NEW_isbn= createNewISBN(isbn);
                }
                //convert back to the String
                st.executeUpdate("insert into kniha (ISBN,nazov,cena) values('"+NEW_isbn+"', '"+meno+"',"+cena+")");
            }
            result = true;
        }
        return result;
    }

    /**
     *  inceremnt numeric part of isbn and return as new isbn
     * @param bn
     * @return 
     */
    private static String createNewISBN(String bn) {
        //create reverse 
        int length = bn.length();
        int i;
        for( i= 0; i < length; i++ ){
            if(Character.isAlphabetic(bn.charAt(i))){
                continue;
            }
            else 
                break;
        }
           String letters = bn.substring(0, i);
           int  numbers = Integer.parseInt(bn.substring(i)) ;
           numbers++;
           StringBuffer b = new StringBuffer();
           b.append(letters);
           b.append(numbers);
           return b.toString();
           //todo change letters   
    }
    
    /**
     * 
     * get largest id, increment him and try to insert data to db if exception occurs return false 
     to do check if exist kniha with name
     * @param meno
     * @param cena
     * @return
     * @throws SQLException 
     */
    static boolean pridajKnihu2(String meno, double cena) throws SQLException  {
        boolean result = false;
        ResultSet rs = st.executeQuery("SELECT ISBN, cena FROM kniha where nazov = '" + meno + "'");
        //kniha neexistuje
        if (!rs.next()) {
            int x = 0;//used to store result from executeUpdate function
            
            rs = st.executeQuery("SELECT max(isbn) as isbn from kniha");


            String NEW_isbn = "a0";
            //check if db is empty
            if (rs.next()) {
                    String isbn = rs.getString("isbn");
                    NEW_isbn = createNewISBN(isbn);
            }
            // try to insert data
            try {
                x = st.executeUpdate("insert into kniha (isbn, nazov,cena) values('"+NEW_isbn+"','"+meno+"',"+cena+")");
            } catch (SQLException ex) {
                Logger.getLogger(Cv1_2_JDBC_APP.class.getName()).log(Level.SEVERE, null, ex);
                result = false;
            }
        }
        return result;
    }
    
    /**
     * nájde v DB knihu so zadaným menom a zníži jej cenu o o 20% (v databáze). Ak neexistuje taká kniha neurobí nič. 
     * 
     * @param meno the name of the book in ehich will be discounted
     * @throws SQLException 
     */
    static void zlava(String meno) throws SQLException {
        ResultSet rs = st.executeQuery("SELECT cena FROM kniha where nazov = '" + meno + "'");
        if (rs.next()) {
                st.executeUpdate("update kniha set cena = cena * 0.9 where nazov = '" + meno +"'");
            }
    } 
    
}
