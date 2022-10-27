

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SatirToObject {
	
	private List<String> satirlar;
	private List<Nokta> noktalar;
	private List<Kenar> kenarlar;
	private List<Yuzey> yuzeyler;
	private List<KutleYuzeyi> kutleYuzeyleri;
	private StringTokenizer tokenizerLine;

	
	public SatirToObject(List<String> satirlar)
	{
		this.satirlar = satirlar;
		this.noktalar = new ArrayList<Nokta>();
		this.kenarlar = new ArrayList<>();
		this.yuzeyler = new ArrayList<>();
		this.kutleYuzeyleri = new ArrayList();
	}

	public void createObjects() {
	
		createNoktalar();
		createKenarlar();
		createYuzeyler();
		createKutleYuzeyleri();
	}
	private void createNoktalar()
	{
	
		for(int i =0;i<satirlar.size();i++)
		{
			if(satirlar.get(i).startsWith("TUM NOKTALAR"))
			{
				int j=i+1;
				while(satirlar.get(j).startsWith("Numara="))
				{
					tokenizerLine= new StringTokenizer(satirlar.get(j)," ");
					while(tokenizerLine.hasMoreTokens())
					{
						tokenizerLine.nextToken();
						Integer numara = Integer.valueOf(tokenizerLine.nextToken().trim());
						tokenizerLine.nextToken();
						String x = tokenizerLine.nextToken().trim();	
						tokenizerLine.nextToken();
						String y = tokenizerLine.nextToken().trim();	
						tokenizerLine.nextToken();
						String z =tokenizerLine.nextToken().trim();	
						Nokta nokta = new Nokta(numara, x, y, z);
						noktalar.add(nokta);
					}
				
					j++;
				}
					
			}
		}
	}
	private void createKenarlar()
	{
		Nokta aNoktasi = null ;
		Nokta bNoktasi = null;
		
		for(int i =0;i<satirlar.size();i++)
		{
			if(satirlar.get(i).startsWith("TUM KENARLAR"))
			{
				int j=i+1;
				while(satirlar.get(j).startsWith("Kenar Numarasý="))
				{
					tokenizerLine= new StringTokenizer(satirlar.get(j)," ");
					while(tokenizerLine.hasMoreTokens())
					{
						tokenizerLine.nextToken();
						tokenizerLine.nextToken();
						Integer numara = Integer.valueOf(tokenizerLine.nextToken().trim());
						tokenizerLine.nextToken();
						tokenizerLine.nextToken();
						tokenizerLine.nextToken();
						Integer aInt = Integer.valueOf(tokenizerLine.nextToken().trim());	
						tokenizerLine.nextToken();
						tokenizerLine.nextToken();
						tokenizerLine.nextToken();
						Integer bInt = Integer.valueOf(tokenizerLine.nextToken().trim());		
						
						String eksenPart = tokenizerLine.nextToken();
						
						
						String eksen =eksenPart.substring(eksenPart.length()-1,eksenPart.length());
						
						for(Nokta nokta : noktalar)
						{
							if(nokta.equals(new Nokta(aInt,null,null,null)))
								aNoktasi=nokta;
							if(nokta.equals(new Nokta(bInt,null,null,null)))
								bNoktasi=nokta;
						}
						
						Kenar kenar = new Kenar(numara, aNoktasi, bNoktasi, eksen);
						kenarlar.add(kenar);
					}
				
					j++;
				}
					
			}
		}
	}
	private void createYuzeyler() {
		
		for(int i =0;i<satirlar.size();i++)
		{
			if(satirlar.get(i).startsWith("TUM YUZEYLER"))
			{
				int j=i+1;
				while(satirlar.get(j).startsWith("Yüzey Numarasý="))
				{
					tokenizerLine= new StringTokenizer(satirlar.get(j),"\t");
					while(tokenizerLine.hasMoreTokens())
					{
						tokenizerLine.nextToken();
						Integer numara = Integer.valueOf(tokenizerLine.nextToken().trim());
						tokenizerLine.nextToken();
						String kenarNumarasiVirgullu=tokenizerLine.nextToken();
						
						StringTokenizer tokenizer2= new StringTokenizer(kenarNumarasiVirgullu,",");
						List<Kenar> kenarlar= new ArrayList<>();		
					    while(tokenizer2.hasMoreTokens()) {
					    	
					    	String kenarNo=tokenizer2.nextToken();
					    	
					    	for(Kenar kenar : this.kenarlar)
					    	{
					    		if(kenar.equals(new Kenar(Integer.valueOf(kenarNo), null, null, null)))
					    			kenarlar.add(kenar);
					    			
					    	}
					    }
						
				
						Yuzey yuzey = new Yuzey(numara,kenarlar);
						yuzeyler.add(yuzey);
						
					}
				
					j++;
				}
					
			}
		}
	}
	
	private void createKutleYuzeyleri()
	{
		
		for(int i =0;i<satirlar.size();i++)
		{
			if(satirlar.get(i).startsWith("Kutle Yüzeyleri"))
			{
				tokenizerLine= new StringTokenizer(satirlar.get(i),"\t");
				while(tokenizerLine.hasMoreTokens())
				{
					tokenizerLine.nextToken();
					
					String yuzeyNumaraVirgullu =  tokenizerLine.nextToken();
					StringTokenizer tokenizer2 =  new StringTokenizer(yuzeyNumaraVirgullu,",");
					List<Yuzey> yuzeylerTemp = new ArrayList();
					while(tokenizer2.hasMoreTokens()) {
						String yuzeyNumarasi = tokenizer2.nextToken();
						
						for(Yuzey yuzey : yuzeyler)
						{
							if(yuzey.equals(new Yuzey(Integer.valueOf(yuzeyNumarasi), null)))
								yuzeylerTemp.add(yuzey);
								
						}
						
						
					}
					kutleYuzeyleri.add(new KutleYuzeyi(yuzeylerTemp));		
					
				}
				
			}
		}
	}
	public List<String> getSatirlar() {
		return satirlar;
	}

	public void setSatirlar(List<String> satirlar) {
		this.satirlar = satirlar;
	}
	
	public List<Nokta> getNoktalar() {
		return noktalar;
	}

	public void setNoktalar(List<Nokta> noktalar) {
		this.noktalar = noktalar;
	}

	public List<Kenar> getKenarlar() {
		return kenarlar;
	}

	public void setKenarlar(List<Kenar> kenarlar) {
		this.kenarlar = kenarlar;
	}

	public List<Yuzey> getYuzeyler() {
		return yuzeyler;
	}

	public void setYuzeyler(List<Yuzey> yuzeyler) {
		this.yuzeyler = yuzeyler;
	}

	public List<KutleYuzeyi> getKutleYuzeyleri() {
		return kutleYuzeyleri;
	}

	public void setKutleYuzeyleri(List<KutleYuzeyi> kutleYuzeyleri) {
		this.kutleYuzeyleri = kutleYuzeyleri;
	}


	
	
	

	
}
