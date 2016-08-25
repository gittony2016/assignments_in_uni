package spec;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * COMMENT: Comment HeightMap
 *
 * @author malcolmr
 */
public class Terrain {

	private Dimension mySize;
	private double[][] myAltitude;
	private List<Tree> myTrees;
	private List<Enemy> myEnemies;
	private List<Road> myRoads;
	private float[] mySunlight;
	/* Faces and normals */
	private ArrayList<ArrayList<double[]>> myFaces;
	private ArrayList<double[]> myNormals;

	/**
	 * Create a new terrain
	 *
	 * @param width
	 *            The number of vertices in the x-direction
	 * @param depth
	 *            The number of vertices in the z-direction
	 */
	public Terrain(int width, int depth) {
		mySize = new Dimension(width, depth);
		myAltitude = new double[width][depth];
		myTrees = new ArrayList<Tree>();
		myEnemies = new ArrayList<Enemy>();
		myRoads = new ArrayList<Road>();
		mySunlight = new float[3];
		/* Initialize faces and normals */
		myFaces = new ArrayList<ArrayList<double[]>>();
		myNormals = new ArrayList<double[]>();
	}

	/* generateFaces */
	public void generateFaces() {
		for (int i = 0; i < mySize.width - 1; i++) {
			for (int j = 0; j < mySize.height - 1; j++) {
				/*
				 * y 
				 * o---------- x 
				 * | p0-----p2
				 * | |    / | 
				 * | |  /   | 
				 * | p1-----p3 
				 * z
				 */
				double p0[] = { i, myAltitude[i][j], j };
				double p1[] = { i, myAltitude[i][j + 1], j + 1 };
				double p2[] = { i + 1, myAltitude[i + 1][j], j };
				double p3[] = { i + 1, myAltitude[i + 1][j + 1], j + 1 };

				myFaces.add(new ArrayList<double[]>(Arrays.asList(p0, p1, p2)));
				generateNormals(p0, p1, p2);

				// ArrayList<double[]> temp1 = new ArrayList<double[]>();
				// temp1.add(p2);
				// temp1.add(p3);
				// temp1.add(p1);
				myFaces.add(new ArrayList<double[]>(Arrays.asList(p1, p3, p2)));
				generateNormals(p1, p3, p2);
			}
		}
	}

	/* generateNormals */
	private void generateNormals(double[] p0, double[] p1, double[] p2) {
		/* p0 -> p1 */
		double u[] = { p1[0] - p0[0], p1[1] - p0[1], p1[2] - p0[2] };
		/* p0 -> p2 */
		double v[] = { p2[0] - p0[0], p2[1] - p0[1], p2[2] - p0[2] };

		double[] normal = normalise(crossProduct(u, v));
		myNormals.add(normal);
	}

	/* normalise */
	double[] normalise(double[] n) {
		double mag = n[0] * n[0] + n[1] * n[1] + n[2] * n[2];
		mag = Math.sqrt(mag);
		double norm[] = { n[0] / mag, n[1] / mag, n[2] / mag };
		return norm;
	}

	/* crossProduct */
	double[] crossProduct(double u[], double v[]) {
		double crossProduct[] = new double[3];
		crossProduct[0] = u[1] * v[2] - u[2] * v[1];
		crossProduct[1] = u[2] * v[0] - u[0] * v[2];
		crossProduct[2] = u[0] * v[1] - u[1] * v[0];
		return crossProduct;
	}

	/* getFaces */
	public ArrayList<ArrayList<double[]>> getFaces() {
		return myFaces;
	}

	/* getNormals */
	public ArrayList<double[]> getNormals() {
		return myNormals;
	}

	public Terrain(Dimension size) {
		this(size.width, size.height);
	}

	public Dimension size() {
		return mySize;
	}

	public List<Tree> trees() {
		return myTrees;
	}
	
	public List<Enemy> enemies() {
		return myEnemies;
	}

	public List<Road> roads() {
		return myRoads;
	}

	public float[] getSunlight() {
		return mySunlight;
	}

	/**
	 * Set the sunlight direction.
	 * 
	 * Note: the sun should be treated as a directional light, without a
	 * position
	 * 
	 * @param dx
	 * @param dy
	 * @param dz
	 */
	public void setSunlightDir(float dx, float dy, float dz) {
		mySunlight[0] = dx;
		mySunlight[1] = dy;
		mySunlight[2] = dz;
	}

	/**
	 * Resize the terrain, copying any old altitudes.
	 * 
	 * @param width
	 * @param height
	 */
	public void setSize(int width, int height) {
		mySize = new Dimension(width, height);
		double[][] oldAlt = myAltitude;
		myAltitude = new double[width][height];

		for (int i = 0; i < width && i < oldAlt.length; i++) {
			for (int j = 0; j < height && j < oldAlt[i].length; j++) {
				myAltitude[i][j] = oldAlt[i][j];
			}
		}
	}

	/**
	 * Get the altitude at a grid point
	 * 
	 * @param x
	 * @param z
	 * @return
	 */
	public double getGridAltitude(int x, int z) {
		return myAltitude[x][z];
	}

	/**
	 * Set the altitude at a grid point
	 * 
	 * @param x
	 * @param z
	 * @return
	 */
	public void setGridAltitude(int x, int z, double h) {
		myAltitude[x][z] = h;
	}

	/**
	 * Get the altitude at an arbitrary point. Non-integer points should be
	 * interpolated from neighbouring grid points
	 * 
	 * @param x
	 * @param z
	 * @return
	 */
	public double altitude(double x, double z) {
		double altitude = 0;
		double centre = 0;
		if (x > mySize.width - 1 || x < 0 || z > mySize.height - 1 || z < 0) {
			altitude = 0;
			return altitude;
		} else {
			int fx = (int) Math.floor(x);
			int cz = (int) Math.ceil(z);
			int cx = (int) Math.ceil(x);
			int fz = (int) Math.floor(z);

			if (fx > mySize.width - 1 || fx < 0 || cz > mySize.height - 1 || cz < 0 || cx > mySize.width - 1 || cx < 0
					|| fz > mySize.height - 1 || fz < 0) {
				altitude = 0;
				return altitude;
			} else {
				centre = (myAltitude[fx][cz] + myAltitude[cx][fz]) / 2.0;
				if (x - fx > 0.5 && z - fz > 0.5) {
					altitude = (centre + myAltitude[cx][cz]) / 2.0;
				} else {
					altitude = (centre + myAltitude[fx][fz]) / 2.0;
				}
			}
		}

		return altitude;
	}

	/**
	 * Add a tree at the specified (x,z) point. The tree's y coordinate is
	 * calculated from the altitude of the terrain at that point.
	 * 
	 * @param x
	 * @param z
	 */
	public void addTree(double x, double z) {
		double y = altitude(x, z);
		Tree tree = new Tree(x, y, z);
		myTrees.add(tree);
	}
	
	public void addEnemy(double x, double z) {
		double y = altitude(x, z);
		Enemy Enemy = new Enemy(x, y, z);
		myEnemies.add(Enemy);
	}

	/**
	 * Add a road.
	 * 
	 * @param x
	 * @param z
	 */
	public void addRoad(double width, double[] spine) {
		Road road = new Road(width, spine);
		myRoads.add(road);
	}

}
