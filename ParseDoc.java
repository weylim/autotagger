package autotagger;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParseDoc {
	
    public static void ParseDoc(String MsgBody) {      
        List<Record> records = new ArrayList<Record>();

        String[] words = MsgBody.split("\\s+"); // explode via blankspaces to words array
        String phrase = "";
        int Count = 0;
        int WordCounter = 0;
        //int Position = -1;
        int MsgLen = words.length;
                
        // For each NN* phrases, instantiate a record
        for (int i = 0; i < words.length; i++) { // for (String word: words) {
            if(words[i].matches(".*_NN\\w?")) {
                words[i] = words[i].replace("\\","");
                phrase += '-' + words[i].substring(0, words[i].lastIndexOf("_"));
                
                WordCounter++;
                //if (Position == -1)
                    //Position = Count;
            }
            else if (!phrase.isEmpty()) {
                int Position = i - WordCounter;
                //Record(String i_phrase, int i_absPosition, float i_relativePosition, int i_TF, int i_numChars, int i_numWords) 
                Record record = new Record(phrase, Position, 0, phrase.length(), WordCounter, ((float)Position / MsgLen));
                records.add(record);
                phrase = ""; // clear temp
                Position = -1; // re-init position for next phrase
                WordCounter = 0;
            }      
            Count++;
        }
        
        
        
        GetTermFreq(myObjList);
        for (int i = 0; i < myObjList.size(); i++)
        	System.out.println(myObjList.get(i).TagTerm);
        
        OutputTermParamToFile(myObjList);
	}
	
	
	public static void GetTermFreq(List<TagObject> myObjList)
    {
		System.out.println("GetTermFreq: List Size -> ");
		System.out.println(myObjList.size());
		
    	Stack aStack = new Stack();
    	
    	for (int i = 0; i < myObjList.size(); i++)
    	{
    		if (myObjList.get(i).TF == 0)
    			aStack.push ( new Integer(i) );
    		else
    			continue;
    		
    		//String BaseType = myObjList.get(i).TagType;
    		String BaseTerm = myObjList.get(i).TagTerm;
    		int Freq = 1;
    		
    		//System.out.println(BaseTerm);
    		//System.out.println(BaseType);
    		
        	for (int j = i+1; j < myObjList.size(); j++)
        	{
        		//if ((BaseType.equals(myObjList.get(j).TagType)) && (BaseTerm.equals(myObjList.get(j).TagTerm)))	//Checks the type and term. Not sure if there will be a case where a term can be tagged as different types
        		if (BaseTerm.equals(myObjList.get(j).TagTerm))
        		{
        			Freq++;
        			aStack.push ( new Integer(j) );
        		}
        	}
        	
        	while ( !aStack.empty() )
            {
        		
        		Integer stackValue = (Integer) aStack.pop();
        		if (myObjList.get(stackValue).TF == 0)
        			myObjList.get(stackValue).setObjTF(Freq);
        		System.out.println(Freq);
            }
    	}
    	
    	System.out.println("Printing TF: ");
    	
    	for (int i = 0; i < myObjList.size(); i++)
    		System.out.println(myObjList.get(i).TF);
    }
	
	
	
	public static void OutputTermParamToFile(List<TagObject> myObjList)
	{
		String OutputFile = "ParamOutput.txt";
		
		try {
			
			FileWriter WriteFile = new FileWriter(OutputFile, false);	//For IDF readings, we can use append then use scripts
			PrintWriter PrintLine = new PrintWriter(WriteFile);
		
			for (int i = 0; i < myObjList.size(); i++)
			{
				PrintLine.printf("%s\t", myObjList.get(i).TagTerm);
				PrintLine.printf("%d\t", myObjList.get(i).TF);
				PrintLine.printf("%f\t", myObjList.get(i).RelativePosition);
				PrintLine.printf("%d\t", myObjList.get(i).numWords);
				PrintLine.printf("%d\n", myObjList.get(i).numChars);
			}
			
			PrintLine.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
		
//		for (int i = 0; i < myObjList.size(); i++)
//		{
//			System.out.println(myObjList.get(i).TagTerm);
//			System.out.println(" ");
//			
//			System.out.println(myObjList.get(i).TF);
//			System.out.println(" ");
//			
//			System.out.println(myObjList.get(i).RelativePosition);
//			System.out.println(" ");
//			
//			System.out.println(myObjList.get(i).numWords);
//			System.out.println(" ");
//			
//			System.out.println(myObjList.get(i).numChars);
//			System.out.println(" ");
//			
//			System.out.println("\n");
//		}
	}
}
