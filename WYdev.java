package autotagger;

import java.sql.SQLException;
/**
 * @author WeeYong
 */
public class WYdev {
        
    static String DBName = "keywords";
    
    public boolean dev() {
        Weka naivesBayes = new Weka();
        
        MySQL mysql = new MySQL(); // init database interface object
        try {
            // grab a sample
            mysql.connectDB("root", "password", "localhost", DBName);
            Sample sample = mysql.readSingle("train1000", 15);
            System.out.println(sample.body);
            
            // print screen cleaned sample
            Texter texter = new Texter();
            String body = texter.clean(sample.body); // clean the text
            String tagged = texter.tag(body); // apply POS tagging
            System.out.println(body);
            System.out.println(tagged);
            
            
            ParseDoc.ParseDoc(tagged);
            
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        
        
        
        return true;
    }
    
}
