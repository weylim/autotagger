package autotagger;

import java.sql.SQLException;
import java.util.HashSet;
/**
 * @author WeeYong
 */
public class WYdev {
    public boolean dev() {
        MySQL mysql = new MySQL(); // init database interface object
        try {
            // grab a sample
            mysql.connectDB("root", "password", "localhost", "keywords");
            Sample sample = mysql.readSingle("train", 15);
            System.out.println(sample.body);
            
            // print screen cleaned sample
            Texter texter = new Texter();
            String body = texter.clean(sample.body); // clean the text
            String tagged = texter.tag(body); // apply POS tagging
            System.out.println(body);
            System.out.println(tagged);
            
            HashSet<String> NNPs = texter.getNNPs(tagged);
            for (String NNP : NNPs) {
                System.out.println(NNP);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        
        return true;
    }
    
}
