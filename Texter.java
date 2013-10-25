/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autotagger;
//import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author WeeYong
 */
public class Texter {
    private MaxentTagger tagger = new MaxentTagger("C:\\Users\\WeeYong\\Documents\\autotagger\\lib\\models\\english-bidirectional-distsim.tagger"); // Initialize the tagger  
    
    public String clean(String text) {
        Document doc = Jsoup.parse(text); // parse HTML
        doc.select("p").append("\\n"); // adds in newline for each <p>
        
        String cleaned = doc.text().replaceAll("\\\\n", System.getProperty("line.separator")); // grab just the text (not the HTML)
        cleaned = cleaned.replaceAll("\\s+", " "); // collapse multiple whitespaces to just 1 space
        return cleaned;
    }
    
    public String tag(String text) {
        String tagged = tagger.tagString(text); 
        return tagged;
    }
        
    public ArrayList<String> getNNP(String tagged) {
        ArrayList<String> NNP = new ArrayList<>();
        NNP.add("hi");
        return NNP;
    }
    
    
}
