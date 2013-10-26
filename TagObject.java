package autotagger;

import java.util.List;
import java.util.Stack;

//This is a class for storing the object information for each candidate
public class TagObject {

	//public String TagType;
	public String TagTerm;
	public int Position;
	public int TF;
	public int numChars;
	public int numWords;
	public float RelativePosition;
	
    public TagObject(	/*String ObjTagType,*/ String ObjTagTerm, int ObjPosition, int ObjTF, int numOfChars, int numOfWords,
    					float RelativePos) 
    {
    	//TagType = ObjTagType;
    	TagTerm = ObjTagTerm;
    	Position = ObjPosition;
    	TF = ObjTF;
    	numChars = numOfChars;
    	numWords = numOfWords;
    	RelativePosition = RelativePos;
    }
    
    
    public void setObjTF(int Freq)
    {
    	TF = Freq;
    }

}
