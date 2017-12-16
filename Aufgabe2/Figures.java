interface Figur {
	double flaeche();
}

class Rechteck implements Figur {
	private final double x;
	private final double y;
	private final double width;
	private final double height;
	private final double flaeche;

	Rechteck(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.flaeche=width*height;
	}
    public double flaeche(){
     return this.flaeche;
	}
}

class Kreis implements Figur {
	private final double x;
	private final double y;
	private final double radius;
	private final double flaeche;

	Kreis(double x, double y, double radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.flaeche=radius*radius*3.1415926;
	}
	public double flaeche(){
		return this.flaeche;
	}
}
class Figuren{
	static double flaeche(Figur[] figuren){
		double x=0;
		for(int i=0;i<figuren.length;i++){
			x=x+figuren[i].flaeche();
		}
		return x;
	}
	
}