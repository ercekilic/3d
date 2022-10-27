
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;
public class DosyaOkuma {
		
	private String fileName;
	
	public DosyaOkuma(String fileName) {
	this.fileName=fileName;
	
		
	}
	public  List<String> getLines() throws IOException{
		List<String> satirlar= new ArrayList<>();
		
		FileReader fr=new FileReader(this.fileName);    
        BufferedReader br=new BufferedReader(fr);
        
        
        String a=br.readLine();
       
        	satirlar.add(a);
        
        while(a!= null) {
        	a=br.readLine();
        	 if(a!=null)
        		 satirlar.add(a);
        
        
        
        }
         
        br.close();
        fr.close();
        return satirlar;
	}
	


		
	}


