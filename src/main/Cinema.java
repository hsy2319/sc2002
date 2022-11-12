package main;

//Cinplex == Shaw, Cathaycineplex, GoldenVillage
////Cinema is like shaw - JE, shaw - CCK
//Each cineplex has 3 cinemas
//Cinema is more specific than Cineplex
public class Cinema{
	
    private Cineplex cineplex;
    private boolean isPlatinum;
    private boolean is3D;
    private String code;
    private double basePrice;
    
	public Cinema(Cineplex cineplex, boolean isPlatinum, boolean is3D, String code, double basePrice) {
		this.cineplex = cineplex;
		this.isPlatinum = isPlatinum;
		this.is3D = is3D;
		this.code = code;
		this.basePrice = basePrice;
	}
	
	public Cineplex getcineplex() {
		return cineplex;
	}
	
	public boolean getisPlatinum() {
		return isPlatinum;
	}
	
	public boolean getis3D() {
		return isPlatinum;
	}
	
	public String getcode() {
		return code;
	}
	
}