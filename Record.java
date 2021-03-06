package autotagger;

// Structure for features for each candidate
public class Record {
    public String phrase; // candidate phrase
    public int absPosition; // absolute position in document
    public float relativePosition; // relative position in document (0 to 1)
    public int TF; // term-frequency
    public int numChars; // number of chars in phrase
    public int numWords; // number of words in phrase
    public int label; // class label {-1,0,1} for unknown, no, yes, respectively 
	
    public Record(String i_phrase, int i_absPosition, float i_relativePosition, int i_TF, int i_numChars, int i_numWords) {
    	phrase = i_phrase;
    	absPosition = i_absPosition;
    	relativePosition = i_relativePosition;
        TF = i_TF;
    	numChars = i_numChars;
    	numWords = i_numWords;
        label = -1; // default -1 for unknown
    }
    
    public void setObjTF(int Freq) {
    	TF = Freq;
    }

}
