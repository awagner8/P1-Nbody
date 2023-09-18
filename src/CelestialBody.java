

/**
 * Celestial Body class for NBody
 * Modified from original Planet class
 * used at Princeton and Berkeley
 * @author ola
 *
 * If you add code here, add yourself as @author below
 * @author Avi Wagner
 *
 */
public class CelestialBody {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;

	/**
	 * Create a Body from parameters	
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */
	public CelestialBody(double xp, double yp, double xv,
			             double yv, double mass, String filename){
							myXPos = xp;
							myYPos = yp;
							myXVel = xv;
							myYVel = yv;
							myMass = mass;
							myFileName = filename;
			
	}

	/**
	 *
	 * @return
	 */
	public double getX() {
		// TODO: complete method
		return myXPos;
	}

	/**
	 *
	 * @return
	 */
	public double getY() {
		// TODO: complete method
		return myYPos;
	}

	/**
	 * Accessor for the x-velocity
	 * @return the value of this object's x-velocity
	 */
	public double getXVel() {
		// TODO: complete method
		return myXVel;
	}
	/**
	 * Return y-velocity of this Body.
	 * @return value of y-velocity.
	 */
	public double getYVel() {
		// TODO: complete method
		return myYVel;
	}

	/**
	 *
	 * @return
	 */
	public double getMass() {
		// TODO: complete method
		return myMass;
	}

	/**
	 *
	 * @return
	 */
	public String getName() {
		// TODO: complete method
		return myFileName;
	}

	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(CelestialBody b) {
		
		return Math.sqrt(Math.pow((b.getX() - this.getX()), 2) + Math.pow((b.getY() - this.getY()), 2));
	}

	public double calcForceExertedBy(CelestialBody b) {
		
		return ((6.67*1e-11)*(this.getMass()*b.getMass())/(Math.pow(this.calcDistance(b), 2)));
	}

	public double calcForceExertedByX(CelestialBody b) {
		
		return ((this.calcForceExertedBy(b)*(b.getX() - this.getX()))/this.calcDistance(b));
	}
	public double calcForceExertedByY(CelestialBody b) {
		
		return ((this.calcForceExertedBy(b)*(b.getY() - this.getY()))/this.calcDistance(b));
	}

	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		// TODO: complete method
		double sum = 0.0;
		for(int i = 0; i < bodies.length; i++) {
			if(!bodies[i].equals(this)) {
				sum += this.calcForceExertedByX(bodies[i]);
			}
		}
		return sum;
	}

	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		double sum = 0.0;
		for(int i = 0; i < bodies.length; i++) {
			if(!bodies[i].equals(this)) {
				sum += this.calcForceExertedByY(bodies[i]);
			}
		}
		return sum;
	}

	public void update(double deltaT, 
			           double xforce, double yforce) {
		double Ax = xforce/this.getMass();
		double Ay = yforce/this.getMass();
		double nvx = this.getXVel() + deltaT*Ax;
		double nvy = this.getYVel() + deltaT*Ay;
		double nx = this.getX() + deltaT*nvx;
		double ny = this.getY() + deltaT*nvy;
		this.myXPos = nx;
		this.myYPos = ny;
		this.myXVel = nvx;
		this.myYVel = nvy;
		
	}

	/**
	 * Draws this planet's image at its current position
	 */
	public void draw() {
		StdDraw.picture(myXPos,myYPos,"images/"+myFileName);
	}
}
