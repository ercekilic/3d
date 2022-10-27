

public class Kenar {
	private  Integer kenarNumara;
	private  Nokta aNoktasi;
	private  Nokta bNoktasi;
	private  String  eksen;

	public Kenar(Integer kenarNumara,Nokta aNoktasi,Nokta bNoktasi,String eksen){
		this.kenarNumara=kenarNumara;
		this.aNoktasi=aNoktasi;
		this.bNoktasi=bNoktasi;
		this.eksen=eksen;
		
		
	}

	public Integer getKenarNumara() {
		return kenarNumara;
	}

	public void setKenarNumara(Integer kenarNumara) {
		this.kenarNumara = kenarNumara;
	}

	public Nokta getaNoktasi() {
		return aNoktasi;
	}

	public void setaNoktasi(Nokta aNoktasi) {
		this.aNoktasi = aNoktasi;
	}

	public Nokta getbNoktasi() {
		return bNoktasi;
	}

	public void setbNoktasi(Nokta bNoktasi) {
		this.bNoktasi = bNoktasi;
	}

	public String getEksen() {
		return eksen;
	}

	public void setEksen(String eksen) {
		this.eksen = eksen;
	}
	public boolean equals(Kenar kenar)
	{
		if(this.kenarNumara==kenar.getKenarNumara())
			return true;
		
		return false;
	}
	
}
