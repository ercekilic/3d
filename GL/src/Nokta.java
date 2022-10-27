

public class Nokta {
	private  Integer noktaNumara;
	private  String x;
	private  String y;
	private  String z;
	public Nokta (Integer noktaNumara, String x , String y, String z) {
		this.noktaNumara=noktaNumara;
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	public Integer getNumara() {
		return noktaNumara;
	}
	public void setnoktaNumara(Integer noktaNumara) {
		this.noktaNumara = noktaNumara;
	}
	public String getX() {
		return x;
	}
	public boolean equals(Nokta nokta)
	{
		if(this.noktaNumara==nokta.getNumara())
				return true;
		
		return false;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getZ() {
		return z;
	}
	public void setZ(String z) {
		this.z = z;
	}
	
}
