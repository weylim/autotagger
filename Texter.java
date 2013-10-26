/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autotagger;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Class for manipulating text
 * @author WeeYong
 */
public class Texter {
	
	static String TagPathName = "/Users/Saber-Chan/Documents/autotagger/lib/models/english-bidirectional-distsim.tagger";
								
	
    private MaxentTagger tagger = new MaxentTagger(TagPathName); // Initialize the tagger  
    
    /** Append newline for each <p> tag. Remove all HTML tags. Collapse multiple whitespace to just 1 space.
     * @param text text to be cleaned
     * @return cleaned text */
    public String clean(String text) {
        Document doc = Jsoup.parse(text); // parse HTML
        doc.select("p").append("\\n"); // adds in newline for each <p>
        
        String cleaned = doc.text().replaceAll("\\\\n", System.getProperty("line.separator")); // grab just the text (not the HTML)
        cleaned = cleaned.replaceAll("\\s+", " "); // collapse multiple whitespaces to just 1 space
        return cleaned;
    }
    
    /** Call on the tagger program to tag a given string
     * @param text text to be POS tagged
     * @return the tagged text (e.g. A_DT lot_NN of_IN frameworks_NNS use_VBP URL_NN conventions_NNS) */
    public String tag(String text) {
        String tagged = tagger.tagString(text); 
        return tagged;
    }
    
    /** Grab out all candidate Noun Phrases from a tagged document/string */
    public HashSet<String> getNNPs(String tagged) {
       HashSet<String> NNPs = new HashSet<String>() {};
       String[] words = tagged.split("\\s+"); // explode via blankspaces to words array
       String temp = "";
       
       // search for consecutive words with NN* ending and store these phrases into NNPs
       for (String word: words) {
           if(word.matches(".*_NN\\w?")) {
               word = word.replace("\\","");
               temp += '-' + word.substring(0, word.lastIndexOf("_"));
           }
           else if (!temp.isEmpty()) {
               NNPs.add(temp.substring(1));
               temp = ""; // clear temp
           }
       }
       if(!temp.isEmpty()) {
           NNPs.add(temp);
       }
       return NNPs;
    }
    
    
}
