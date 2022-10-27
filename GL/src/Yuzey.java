

import java.util.List;

public class Yuzey {
	private Integer yuzeyNumarasi;
	private List <Kenar> kenarlar;
	

	
	public Yuzey ( Integer yuzeyNumarasi,List <Kenar> kenarlar ) {
		this.yuzeyNumarasi=yuzeyNumarasi;
		this.kenarlar=kenarlar;
		
	}


	public Integer getYuzeyNumarasi() {
		return yuzeyNumarasi;
	}


	public void setYuzeyNumarasi(Integer yuzeyNumarasi) {
		this.yuzeyNumarasi = yuzeyNumarasi;
	}


	public List<Kenar> getKenarlar() {
		return kenarlar;
	}


	public void setKenarlar(List<Kenar> kenarlar) {
		this.kenarlar = kenarlar;
	}
	public boolean equals(Yuzey yuzey)
	{
		if(this.yuzeyNumarasi==yuzey.getYuzeyNumarasi())
			return true;
		
		return false;
	}
	
}
