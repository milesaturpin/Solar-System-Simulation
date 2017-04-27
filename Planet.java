
public class Planet {
	
	public double myXPos;
	public double myYPos;
	public double myXVel;
	public double myYVel;
	public double myMass;
	String myFileName;
	
	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		this.myXPos = xP;
		this.myYPos = yP;
		this.myXVel = xV;
		this.myYVel = yV;
		this.myMass = m;
		this.myFileName = img;
	}
	
	public Planet(Planet p) {
		this.myXPos = p.myXPos;
		this.myYPos = p.myYPos;
		this.myXVel = p.myXVel;
		this.myYVel = p.myYVel;
		this.myMass = p.myMass;
		this.myFileName = p.myFileName;
	}
	
	public double calcDistance(Planet otherPlanet) {
		return Math.sqrt((this.myXPos - otherPlanet.myXPos) * (this.myXPos - otherPlanet.myXPos) + (this.myYPos - otherPlanet.myYPos) * (this.myYPos - otherPlanet.myYPos));
	}
	
	public double calcForceExertedBy(Planet otherPlanet) {
		
		double distance;
		double force;
		
		distance = calcDistance(otherPlanet);
		force = ((6.67e-11) * this.myMass * otherPlanet.myMass) / (distance * distance);
		
		return force;
	}
	
	public double calcForceExertedByX(Planet otherPlanet) {
		
		double distance;
		double distanceX;
		double force;
		double forceX;
		
		distance = calcDistance(otherPlanet);
		force = calcForceExertedBy(otherPlanet);
		//distanceX = Math.sqrt((otherPlanet.myXPos - this.myXPos) * (otherPlanet.myXPos - this.myXPos));
		distanceX = otherPlanet.myXPos - this.myXPos;
		forceX = force * distanceX / distance; 
		return forceX;
	}
	
	public double calcForceExertedByY(Planet otherPlanet) {
		
		double distance;
		double distanceY;
		double force;
		double forceY;
		
		distance = calcDistance(otherPlanet);
		force = calcForceExertedBy(otherPlanet);
		//distanceY = Math.sqrt((otherPlanet.myYPos - this.myYPos) * (otherPlanet.myYPos - this.myYPos));
		distanceY = otherPlanet.myYPos - this.myYPos;
		forceY = force * distanceY / distance; 
		
		return forceY;
	}
	
	public double calcNetForceExertedByX(Planet[] allPlanets) {
		
		double sum = 0;
		
		for (Planet p: allPlanets) {
			if (! p.equals(this)) {
				sum += calcForceExertedByX(p);
			}
		}
		
		return sum;
	}
	
	public double calcNetForceExertedByY(Planet[] allPlanets) {
		
		double sum = 0;
		
		for (Planet p: allPlanets) {
			if (! p.equals(this)) {
				sum += calcForceExertedByY(p);
			}
		}
		
		return sum;
	}
	
	public void update(double seconds, double xforce, double yforce) {
		
		double ax = xforce / myMass;
		double ay = yforce / myMass;
		myXVel = myXVel + seconds*ax;
		myYVel = myYVel + seconds*ay;
		myXPos = myXPos + seconds*myXVel;
		myYPos = myYPos + seconds*myYVel;
		
	}
	
	public void draw() {
		StdDraw.picture(myXPos, myYPos, String.format("images/%s", myFileName));
	}
	
}
