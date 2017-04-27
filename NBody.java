import java.util.Scanner;
import java.io.*;

public class NBody {
	
	public static void main(String[] args){
		double time = 0;
		double T = 157788000.0;
		double dt = 25000.0;
		String pfile = "data/planets.txt";
		
		if (args.length > 2) {
			T = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			pfile = args[2];
		}	
		Planet[] planets = readPlanets(pfile);
		double radius = readRadius(pfile);
		
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");
	
		for (Planet planet: planets) {
			planet.draw();
		}
		
		System.out.printf("%d\n", planets.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		              planets[i].myXPos, planets[i].myYPos, 
		                      planets[i].myXVel, planets[i].myYVel, 
		                      planets[i].myMass, planets[i].myFileName);	
		}
		
		while (time <= T) {
			
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			
			for (int i = 0; i < planets.length; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			
			for (int i = 0; i < planets.length; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			
			StdDraw.setScale(-radius, radius);
			StdDraw.picture(0, 0, "images/starfield.jpg");
			
			for (Planet planet: planets) {
				planet.draw();
			}
			
			StdDraw.show(10);
			time = time + dt;
		}
		
		System.out.printf("%d\n", planets.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                      planets[i].myXPos, planets[i].myYPos, 
		                      planets[i].myXVel, planets[i].myYVel, 
		                      planets[i].myMass, planets[i].myFileName);	
		}
		
	}
	
	public static double readRadius(String file) {
		
		try {
			Scanner s = new Scanner(new File(file));
			s.nextDouble();
			return s.nextDouble();
		} catch (FileNotFoundException f) {
			System.out.println("File not found.");
			return 0;
		}
	}
	
	// QUESTION: why is there an alert for s? 
	// and how can I not have to use a dummy planet array?
	
	public static Planet[] readPlanets(String file) {
		
		Planet[] notPlanets = new Planet[0];
		
		try {
			Scanner s = new Scanner(new File(file));
			int planetNumber = s.nextInt();
			Planet[] planets = new Planet[planetNumber];
			s.nextDouble();
			
			for (int i = 0; i < planetNumber; i++) {
				double xp = s.nextDouble();
				double yp = s.nextDouble();
				double xv = s.nextDouble();
				double yv = s.nextDouble();
				double m = s.nextDouble();
				String image = s.next();
				
				Planet p = new Planet(xp, yp, xv, yv, m, image);
				planets[i] = p;
			}
			s.close();
			return planets;
			
		} catch (FileNotFoundException f) {
			System.out.println("File not found.");
		}
	
		return notPlanets;
		
	}
	
	
	
}
