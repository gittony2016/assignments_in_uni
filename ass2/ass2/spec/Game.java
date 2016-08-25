package spec;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLJPanel;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.*;
/*import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.glu.GLU;*/
import com.jogamp.opengl.util.FPSAnimator;

/**
 * COMMENT: Comment Game
 *
 * @author malcolmr
 */
public class Game extends JFrame implements GLEventListener, KeyListener, MouseMotionListener {

	/**
	 * texture variables
	 */
	private String textureFileName1 = "land.jpg"; // for terrain
	private String textureFileName2 = "wood.jpg"; // for tree chunk
	private String textureFileName3 = "grass.jpg"; // for tree crown
	private String textureFileName4 = "road.jpg"; // for road
	private String textureFileName5 = "sunwukong.png"; // for role
	private String textureFileName6 = "sun.jpg"; // for sun
	private String textureExt1 = "jpg";
	private String textureExt2 = "jpg";
	private String textureExt3 = "jpg";
	private String textureExt4 = "jpg";
	private String textureExt5 = "png";
	private String textureExt6 = "jpg";

	private int imageSize = 64;
	private static final int rgba = 4;
	private ByteBuffer chessImageBuf = Buffers.newDirectByteBuffer(imageSize * imageSize * rgba);
	private MyTexture myTextures[];

	/**
	 * for drawing trees
	 */
	private static final int SLICES = 32; // cylinder cover
	private static final int STACKS = 20; // sphere cover
	private static final int SLICES_SP = 24;
	private static final double radius_cy = 0.1; // cylinder radius
	private static final double radius = 0.6; // sphere radius

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// private static final int ROTATION_SCALE = 1;
	private Terrain myTerrain;
	// private Point myMousePoint = null;
	// private double myRotateX = 0;
	private double myRotateY = 0;
	private double rotateSun = 0;
	private double enemyAngle = 0;
	private double move = 0;
	private double cameraX = 0.0;
	private double cameraY = 0.0;
	private double cameraZ = 0.0;
	private double lookatX = 0.0;
	private double lookatY = 0.0;
	private double lookatZ = 0.0;
	private double playerX = 0.0;
	private double playerY = 0.0;
	private double playerZ = 0.0;
	private static int flag = 0;
	private double cameraToPlayer = 0;
	private int camera = 0;

	/* VBO */
	private int bufferIds[];
	private float positions[] = { 0.0f, 0.0f, 0.5f, -0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f,

			0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f,

			0.0f, 0.0f, 0.5f, 0.5f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f,

			0.0f, 0.0f, 0.5f, 0.0f, 1.0f, 0.0f, -0.5f, 0.0f, 0.0f, };

	private FloatBuffer positionBuffer = Buffers.newDirectFloatBuffer(positions);

	public Game(Terrain terrain) {
		super("Assignment 2");
		myTerrain = terrain;

	}

	/**
	 * Run the game.
	 *
	 */
	public void run() {
		GLProfile glp = GLProfile.getDefault();
		@SuppressWarnings("unused")
		GLCapabilities caps = new GLCapabilities(glp);
		GLJPanel panel = new GLJPanel();
		panel.addGLEventListener(this);
		panel.addKeyListener(this);
		panel.addMouseMotionListener(this);
		panel.setFocusable(true);

		// Add an animator to call 'display' at 60fps
		FPSAnimator animator = new FPSAnimator(60);
		animator.add(panel);
		animator.start();

		getContentPane().add(panel);
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * Load a level file and display it.
	 * 
	 * @param args
	 *            - The first argument is a level file in JSON format
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Terrain terrain = LevelIO.load(new File(args[0]));
		Game game = new Game(terrain);
		game.run();
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		// GLUT glut = new GLUT();
		GLU glu = new GLU();

		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		rotateSun += 0.5;
		enemyAngle += 2.0;
		// Specify how texture values combine with current surface color values.
		gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_MODULATE);
		float sun = (float) (rotateSun % 360);
		float ss = 0.1f;
		if (sun >= 90 && sun < 270) {
			ss = 0.1f;
			gl.glDisable(GL2.GL_LIGHT0);
			gl.glEnable(GL2.GL_LIGHT1);
		} else if (sun >= 0 && sun < 90) {
			ss = 1.0f - sun * 0.01f;
			gl.glEnable(GL2.GL_LIGHT0);
			gl.glDisable(GL2.GL_LIGHT1);
		} else {
			ss = 0.1f + (sun - 270f) * 0.01f;
			gl.glEnable(GL2.GL_LIGHT0);
			gl.glDisable(GL2.GL_LIGHT1);
		}
		gl.glClearColor(ss * 100f / 255f, ss * 149f / 255f, ss * 237f / 255f, 0.0f);



		if (camera == 0) {
			/* 1st camera */
			cameraToPlayer = 0.01;
		} else {
			/* 3rd camera */
			cameraToPlayer = 3;
		}

		if (camera == 0) {
			/* 1st camera */
			glu.gluLookAt(cameraX, cameraY, cameraZ, lookatX, lookatY, lookatZ, 0.0, 1.0, 0.0);
		} else {
			/* 3rd camera */
			glu.gluLookAt(cameraX, cameraY + 3.0, cameraZ, lookatX, lookatY, lookatZ, 0.0, 1.0, 0.0);
		}

		// gl.glRotated(angle, 1, 0, 0);

		/* Draw terrain */
		myTerrain.generateFaces();
		ArrayList<ArrayList<double[]>> myFaces = myTerrain.getFaces();
		ArrayList<double[]> myNormals = myTerrain.getNormals();

		gl.glBindTexture(GL2.GL_TEXTURE_2D, myTextures[0].getTextureId());
		for (int i = 0; i < myFaces.size(); i++) {
			gl.glBegin(GL2.GL_POLYGON);
			{
				double s = 0.0;
				double t = 0.0;
				gl.glNormal3dv(myNormals.get(i), 0);
				for (int j = 0; j < 3; j++) {
					if (j == 0) {
						s = 0.0;
						t = 0.0;
					}
					if (j == 1) {
						s = 0.8;
						t = 0.0;
					}
					if (j == 2) {
						s = 0.4;
						t = 0.4;
					}
					gl.glTexCoord2d(s, t);
					gl.glVertex3dv(myFaces.get(i).get(j), 0);
				}
			}
			gl.glEnd();
		}

		/* Draw trees */
		for (int tree_i = 0; tree_i < myTerrain.trees().size(); tree_i++) {

			double[] myPos = myTerrain.trees().get(tree_i).getPosition();

			gl.glPushMatrix();

			/* Calculate the altitude of the tree */
			myPos[1] = myTerrain.altitude(myPos[0], myPos[2]);
			gl.glTranslated(myPos[0], myPos[1] + 1.6, myPos[2]);
			gl.glRotated(-90, 1, 0, 0);

			/*
			 * tree chunk (0)
			 */
			gl.glBindTexture(GL2.GL_TEXTURE_2D, myTextures[1].getTextureId());
			double angleIncrement = (Math.PI * 2.0) / SLICES;
			double zFront = -0.55; // tree height
			double zBack = -1.8;
			gl.glBegin(GL2.GL_QUAD_STRIP);
			{
				for (int i = 0; i <= SLICES; i++) {
					double angle0 = i * angleIncrement;
					// double angle1 = (i + 1) * angleIncrement;
					double xPos0 = Math.cos(angle0) * radius_cy; // radius is
																	// 0.1
					double yPos0 = Math.sin(angle0) * radius_cy;
					double sCoord = 1.0 / SLICES * i; // Or * 2 to repeat label

					gl.glNormal3d(xPos0, yPos0, 0);
					gl.glTexCoord2d(sCoord, 1);
					gl.glVertex3d(xPos0, yPos0, zFront);
					gl.glTexCoord2d(sCoord, 0);
					gl.glVertex3d(xPos0, yPos0, zBack);

				}
			}
			gl.glEnd();

			/*
			 * tree crown (1)
			 */

			gl.glBindTexture(GL2.GL_TEXTURE_2D, myTextures[2].getTextureId());
			double deltaT = 0.5 / STACKS;
			int ang;
			int delang = 360 / SLICES_SP;
			double x1, x2, z1, z2, y1, y2;
			for (int i = 0; i < STACKS; i++) {
				double t = -0.25 + i * deltaT;

				gl.glBegin(GL2.GL_TRIANGLE_STRIP);
				for (int j = 0; j <= SLICES; j++) {
					ang = j * delang;
					x1 = radius * r(t) * Math.cos((double) ang * 2.0 * Math.PI / 360.0);
					x2 = radius * r(t + deltaT) * Math.cos((double) ang * 2.0 * Math.PI / 360.0);
					y1 = radius * getY(t);

					z1 = radius * r(t) * Math.sin((double) ang * 2.0 * Math.PI / 360.0);
					z2 = radius * r(t + deltaT) * Math.sin((double) ang * 2.0 * Math.PI / 360.0);
					y2 = radius * getY(t + deltaT);

					double normal[] = { x1, y1, z1 };

					normalize(normal);

					gl.glNormal3dv(normal, 0);
					double tCoord = 1.0 / STACKS * i; // Or * 2 to repeat label
					double sCoord = 1.0 / SLICES_SP * j;
					gl.glTexCoord2d(sCoord, tCoord);
					gl.glVertex3d(x1, y1, z1);
					normal[0] = x2;
					normal[1] = y2;
					normal[2] = z2;

					normalize(normal);
					gl.glNormal3dv(normal, 0);
					tCoord = 1.0 / STACKS * (i + 1); // Or * 2 to repeat label
					gl.glTexCoord2d(sCoord, tCoord);
					gl.glVertex3d(x2, y2, z2);

				}
				gl.glEnd();

			}

			gl.glPopMatrix();
		}

		/* Draw enemies */
		for (int i = 0; i < myTerrain.enemies().size(); i++) {
			double[] myPos = myTerrain.enemies().get(i).getPosition();

			gl.glPushMatrix();
			/* Calculate the altitude of the enemies */
			myPos[1] = myTerrain.altitude(myPos[0], myPos[2]);
			gl.glTranslated(myPos[0], myPos[1], myPos[2]);
			gl.glRotated(enemyAngle, 0, 1, 0);

			/* VBO */

			gl.glDrawArrays(GL2.GL_TRIANGLES, 0, 12);
			gl.glDrawArrays(GL2.GL_TRIANGLES, 3, 12);
			gl.glDrawArrays(GL2.GL_TRIANGLES, 6, 12);
			gl.glDrawArrays(GL2.GL_TRIANGLES, 9, 12);
			gl.glPopMatrix();
		}

		/* Draw Light */
		gl.glPushMatrix();
		gl.glTranslated(myTerrain.size().getWidth() / 2, 0, myTerrain.size().getHeight() / 2);
		gl.glRotated(rotateSun, 0, 0, 1);
		double sunHeight1 = Math.max(myTerrain.size().getWidth() / 2 + 4, myTerrain.size().getHeight() / 2 + 4);
		gl.glTranslated(0, sunHeight1, 0);
		float lightPos0[] = { 0f, 0f, 0f, 1 };
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, lightPos0, 0);

		gl.glPopMatrix();

		/* Draw sun */
		gl.glPushMatrix();
		gl.glTranslated(myTerrain.size().getWidth() / 2, 0, myTerrain.size().getHeight() / 2);
		gl.glRotated(rotateSun, 0, 0, 1);

		double sunHeight = Math.max(myTerrain.size().getWidth() / 2 + 4, myTerrain.size().getHeight() / 2 + 4);
		gl.glTranslated(0, sunHeight, 0);

		gl.glBindTexture(GL2.GL_TEXTURE_2D, myTextures[5].getTextureId());
		double deltaT = 0.5 / STACKS;
		int ang;
		int delang = 360 / SLICES_SP;
		double x1, x2, z1, z2, y1, y2;
		for (int i = 0; i < STACKS; i++) {
			double t = -0.25 + i * deltaT;

			gl.glBegin(GL2.GL_TRIANGLE_STRIP);
			for (int j = 0; j <= SLICES; j++) {
				ang = j * delang;
				x1 = radius / 4 * r(t) * Math.cos((double) ang * 2.0 * Math.PI / 360.0);
				x2 = radius / 4 * r(t + deltaT) * Math.cos((double) ang * 2.0 * Math.PI / 360.0);
				y1 = radius / 4 * getY(t);

				z1 = radius / 4 * r(t) * Math.sin((double) ang * 2.0 * Math.PI / 360.0);
				z2 = radius / 4 * r(t + deltaT) * Math.sin((double) ang * 2.0 * Math.PI / 360.0);
				y2 = radius / 4 * getY(t + deltaT);

				double normal[] = { x1, y1, z1 };

				normalize(normal);

				gl.glNormal3dv(normal, 0);
				double tCoord = 1.0 / STACKS * i; // Or * 2 to repeat label
				double sCoord = 1.0 / SLICES_SP * j;
				gl.glTexCoord2d(sCoord, tCoord);
				gl.glVertex3d(x1, y1, z1);
				normal[0] = x2;
				normal[1] = y2;
				normal[2] = z2;

				normalize(normal);
				gl.glNormal3dv(normal, 0);
				tCoord = 1.0 / STACKS * (i + 1); // Or * 2 to repeat label
				gl.glTexCoord2d(sCoord, tCoord);
				gl.glVertex3d(x2, y2, z2);

			}
			gl.glEnd();
		}

		gl.glPopMatrix();

		/* Draw roads */
		for (int i = 0; i < myTerrain.roads().size(); i++) {
			gl.glPushMatrix();
			ArrayList<double[]> spine = new ArrayList<double[]>();
			ArrayList<double[]> edge1 = new ArrayList<double[]>();
			ArrayList<double[]> edge2 = new ArrayList<double[]>();

			/* generate spine */
			for (double j = 0.0; j < myTerrain.roads().get(i).size(); j += 0.1) {
				spine.add(myTerrain.roads().get(i).point(j));
			}

			/* generate edges */
			for (int j = 1; j < spine.size(); j++) {
				double[] points = new double[6];
				double[] e1 = new double[3];
				double[] e2 = new double[3];
				double[] p0 = new double[3];
				double[] p1 = new double[3];

				p0[0] = spine.get(j - 1)[0];
				p0[2] = spine.get(j - 1)[1];
				p0[1] = myTerrain.altitude(p0[0], p0[2]);

				p1[0] = spine.get(j)[0];
				p1[2] = spine.get(j)[1];
				p1[1] = myTerrain.altitude(p1[0], p1[2]);

				points = myTerrain.roads().get(i).roadEdge(p0, p1);

				e1[0] = points[0];
				e1[1] = points[1] + 0.05;
				e1[2] = points[2];
				edge1.add(e1);

				e2[0] = points[3];
				e2[1] = points[4] + 0.05;
				e2[2] = points[5];
				edge2.add(e2);
			}

			// double myWidth = myTerrain.roads().get(i).width();

			/* Spine */
			// gl.glBegin(GL.GL_LINE_STRIP);
			// for (int j = 0; j < spine.size(); j++) {
			// double x = spine.get(j)[0];
			// double z = spine.get(j)[1];
			// double y = myTerrain.altitude(x, z);
			// gl.glVertex3d(x, y, z);
			// }
			// gl.glEnd();

			/* Edge1 */
			// gl.glBegin(GL.GL_LINE_STRIP);
			// for (int j = 0; j < edge1.size(); j++) {
			// double x = edge1.get(j)[0];
			// double y = edge1.get(j)[1];
			// double z = edge1.get(j)[2];
			// gl.glVertex3d(x, y, z);
			// }
			// gl.glEnd();

			/* Edge2 */
			// gl.glBegin(GL.GL_LINE_STRIP);
			// for (int j = 0; j < edge2.size(); j++) {
			// double x = edge2.get(j)[0];
			// double y = edge2.get(j)[1];
			// double z = edge2.get(j)[2];
			// gl.glVertex3d(x, y, z);
			// }
			// gl.glEnd();

			gl.glBindTexture(GL2.GL_TEXTURE_2D, myTextures[3].getTextureId());
			for (int j = 1; j < edge1.size(); j++) {
				gl.glBegin(GL2.GL_POLYGON);
				gl.glNormal3d(0.0, 1.0, 0.0);
				gl.glTexCoord2d(0.0, 0.0);
				gl.glVertex3d(edge2.get(j)[0], edge2.get(j)[1], edge2.get(j)[2]);
				gl.glTexCoord2d(1.0, 0.0);
				gl.glVertex3d(edge1.get(j - 1)[0], edge1.get(j - 1)[1], edge1.get(j - 1)[2]);
				gl.glTexCoord2d(0.5, 1.0);
				gl.glVertex3d(edge2.get(j - 1)[0], edge2.get(j - 1)[1], edge2.get(j - 1)[2]);
				gl.glNormal3d(0.0, 1.0, 0.0);
				gl.glTexCoord2d(0.0, 0.0);
				gl.glVertex3d(edge1.get(j)[0], edge1.get(j)[1], edge1.get(j)[2]);
				gl.glTexCoord2d(1.0, 0.0);
				gl.glVertex3d(edge1.get(j - 1)[0], edge1.get(j - 1)[1], edge1.get(j - 1)[2]);
				gl.glTexCoord2d(0.5, 1.0);
				gl.glVertex3d(edge2.get(j)[0], edge2.get(j)[1], edge2.get(j)[2]);
				gl.glEnd();
			}

			gl.glPopMatrix();
		}

		/* Draw player */
		gl.glPushMatrix();
		gl.glBindTexture(GL2.GL_TEXTURE_2D, myTextures[4].getTextureId());

		if (myTerrain.roads().size() > 0 && flag == 0) {
			playerX = myTerrain.roads().get(0).point(0)[0];
			playerZ = myTerrain.roads().get(0).point(0)[1];
			playerY = myTerrain.altitude(playerX, playerZ);
			flag = 1;
		}

		gl.glTranslated(playerX, playerY, playerZ);
		gl.glRotated(myRotateY, 0, 1, 0);
		// gl.glRotated(myRotateX, 1, 0, 0);
		gl.glScaled(0.5, 0.5, 0.5);
		// System.out.println("X = " + playerX + " " + "Z = " + playerZ + " " +
		// "Y = " + playerY);

		/* Calculate start point for next display */
		playerX += move * Math.cos(Math.toRadians(270 - myRotateY));
		if (playerX >= myTerrain.size().getWidth() - 1) {
			playerX = myTerrain.size().getWidth() - 1;
		} else if (playerX <= 0) {
			playerX = 0;
		}
		playerZ += move * Math.sin(Math.toRadians(270 - myRotateY));
		if (playerZ > myTerrain.size().getHeight() - 1) {
			playerZ = myTerrain.size().getHeight() - 1;
		} else if (playerZ <= 0) {
			playerZ = 0;
		}
		double higherThanGround = 0.1;
		playerY = myTerrain.altitude(playerX, playerZ) + higherThanGround;

		/* Fresh camera position */
		cameraX = playerX + cameraToPlayer * Math.cos(Math.toRadians(90 - myRotateY));
		cameraZ = playerZ + cameraToPlayer * Math.sin(Math.toRadians(90 - myRotateY));
		// cameraY = playerY + cameraToPlayer *
		// Math.cos(Math.toRadians(myRotateX));
		cameraY = playerY + 0.25;

		lookatX = playerX;
		lookatY = playerY + 0.25;
		lookatZ = playerZ;

		gl.glBegin(GL2.GL_QUADS);
		// front
		gl.glNormal3f(0, 0, 1);
		gl.glTexCoord2d(0.0, 0.0);
		gl.glVertex3f(-0.5f, 0.0f, 0.5f);
		gl.glTexCoord2d(1.0, 0.0);
		gl.glVertex3f(0.5f, 0.0f, 0.5f);
		gl.glTexCoord2d(1.0, 1.0);
		gl.glVertex3f(0.5f, 1.0f, 0.5f);
		gl.glTexCoord2d(0.0, 1.0);
		gl.glVertex3f(-0.5f, 1.0f, 0.5f);

		// back
		gl.glNormal3f(0, 0, -1);
		gl.glVertex3f(-0.5f, 1.0f, -0.5f);
		gl.glVertex3f(0.5f, 1.0f, -0.5f);
		gl.glVertex3f(0.5f, 0.0f, -0.5f);
		gl.glVertex3f(-0.5f, 0.0f, -0.5f);

		// bottom
		gl.glNormal3f(0, -1, 0);
		gl.glVertex3f(-0.5f, 0.0f, -0.5f);
		gl.glVertex3f(0.5f, 0.0f, -0.5f);
		gl.glVertex3f(0.5f, 0.0f, 0.5f);
		gl.glVertex3f(-0.5f, 0.0f, 0.5f);

		// top
		gl.glNormal3f(0, 1, 0);
		gl.glTexCoord2d(0.0, 0.0);
		gl.glVertex3f(-0.5f, 1.0f, 0.5f);
		gl.glTexCoord2d(1.0, 0.0);
		gl.glVertex3f(0.5f, 1.0f, 0.5f);
		gl.glTexCoord2d(1.0, 1.0);
		gl.glVertex3f(0.5f, 1.0f, -0.5f);
		gl.glTexCoord2d(0.0, 1.0);
		gl.glVertex3f(-0.5f, 1.0f, -0.5f);

		// left
		gl.glNormal3f(-1, 0, 0);
		gl.glVertex3f(-0.5f, 0.0f, 0.5f);
		gl.glVertex3f(-0.5f, 1.0f, 0.5f);
		gl.glVertex3f(-0.5f, 1.0f, -0.5f);
		gl.glVertex3f(-0.5f, 0.0f, -0.5f);

		// right
		gl.glNormal3f(1, 0, 0);
		gl.glVertex3f(0.5f, 0.0f, -0.5f);
		gl.glVertex3f(0.5f, 1.0f, -0.5f);
		gl.glVertex3f(0.5f, 1.0f, 0.5f);
		gl.glVertex3f(0.5f, 0.0f, 0.5f);

		gl.glEnd();

		/* Torch */
		gl.glBegin(GL2.GL_QUADS);
		// front
		gl.glNormal3f(0, 0, 1);
		gl.glVertex3f(-0.7f, 0.4f, 0.3f);
		gl.glVertex3f(-0.5f, 0.4f, 0.3f);
		gl.glVertex3f(-0.5f, 0.6f, 0.3f);
		gl.glVertex3f(-0.7f, 0.6f, 0.3f);

		// back
		gl.glNormal3f(0, 0, -1);
		gl.glVertex3f(-0.5f, 0.4f, -0.3f);
		gl.glVertex3f(-0.7f, 0.4f, -0.3f);
		gl.glVertex3f(-0.7f, 0.6f, -0.3f);
		gl.glVertex3f(-0.5f, 0.6f, -0.3f);

		// bottom
		gl.glNormal3f(0, -1, 0);
		gl.glVertex3f(-0.5f, 0.4f, 0.3f);
		gl.glVertex3f(-0.7f, 0.4f, 0.3f);
		gl.glVertex3f(-0.7f, 0.4f, -0.3f);
		gl.glVertex3f(-0.5f, 0.4f, -0.3f);

		// top
		gl.glNormal3f(0, 1, 0);
		gl.glVertex3f(-0.5f, 0.6f, -0.3f);
		gl.glVertex3f(-0.7f, 0.6f, -0.3f);
		gl.glVertex3f(-0.7f, 0.6f, 0.3f);
		gl.glVertex3f(-0.5f, 0.6f, 0.3f);

		// left
		gl.glNormal3f(-1, 0, 0);
		gl.glVertex3f(-0.7f, 0.4f, 0.3f);
		gl.glVertex3f(-0.7f, 0.6f, 0.3f);
		gl.glVertex3f(-0.7f, 0.6f, -0.3f);
		gl.glVertex3f(-0.7f, 0.4f, -0.3f);

		// right
		gl.glNormal3f(1, 0, 0);
		gl.glVertex3f(-0.5f, 0.4f, -0.3f);
		gl.glVertex3f(-0.5f, 0.6f, -0.3f);
		gl.glVertex3f(-0.5f, 0.6f, 0.3f);
		gl.glVertex3f(-0.5f, 0.4f, 0.3f);

		gl.glEnd();

		gl.glPopMatrix();
		gl.glPushMatrix();

		gl.glTranslated(playerX, playerY + 0.5, playerZ + 0.3);
		gl.glRotated(myRotateY, 0, 1, 0);
		// gl.glRotated(myRotateX, 1, 0, 0);
		gl.glScaled(0.5, 0.5, 0.5);
		float lightAmb1[] = { 0.0f, 0.0f, 0.0f, 1.0f };
		float lightDifAndSpec1[] = { 1.0f, 1.0f, 1.0f, 1.0f };

		// Light properties.
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, lightAmb1, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, lightDifAndSpec1, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, lightDifAndSpec1, 0);
		float lightPos1[] = { 0.0f, 0f, 0f, 1.0f };
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, lightPos1, 0);

		float spotAngle = 30.0f; // Spotlight cone half-angle.
		float spotDirection[] = { 0.0f, 0.0f, -1f }; // Spotlight direction.
		float spotExponent = 0.0f; // Spotlight exponent = attenuation factor.
		gl.glLightf(GL2.GL_LIGHT1, GL2.GL_SPOT_CUTOFF, spotAngle);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPOT_DIRECTION, spotDirection, 0);
		gl.glLightf(GL2.GL_LIGHT1, GL2.GL_SPOT_EXPONENT, spotExponent);
		// float spotDirection[] = {0.0f, -1.0f, 0.0f}; // Spotlight direction.
		gl.glPopMatrix();
		move = 0;
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {

	}

	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();

		gl.glEnable(GL2.GL_DEPTH_TEST);

		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glClearColor(0.7f, 0.7f, 0.3f, 0.0f);

		gl.glEnable(GL2.GL_LIGHTING);
		// gl.glEnable(GL2.GL_LIGHT0);
		gl.glEnable(GL2.GL_NORMALIZE);

		gl.glEnable(GL2.GL_CULL_FACE);
		gl.glCullFace(GL2.GL_BACK);

		/* OpenGL texturing */
		gl.glEnable(GL2.GL_TEXTURE_2D);
		myTextures = new MyTexture[6];
		createChessboard();
		// Compare setting mipmap to true instead of false
		myTextures[0] = new MyTexture(gl, textureFileName1, textureExt1, false);
		myTextures[1] = new MyTexture(gl, textureFileName2, textureExt2, false);
		myTextures[2] = new MyTexture(gl, textureFileName3, textureExt3, false);
		myTextures[3] = new MyTexture(gl, textureFileName4, textureExt4, false);
		myTextures[4] = new MyTexture(gl, textureFileName5, textureExt5, false);
		myTextures[5] = new MyTexture(gl, textureFileName6, textureExt6, false);

		/* VBO */
		bufferIds = new int[1];
		gl.glGenBuffers(1, bufferIds, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, bufferIds[0]);
		// gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, bufferIds[1]);
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, positions.length * Float.BYTES, positionBuffer, GL2.GL_STATIC_DRAW);
		gl.glBufferSubData(GL2.GL_ARRAY_BUFFER, 0, positions.length * Float.BYTES, positionBuffer);

		gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
		// gl.glEnableClientState(GL2.GL_COLOR_ARRAY);
		gl.glVertexPointer(3, GL2.GL_FLOAT, 0, 0);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();

		GLU glu = new GLU();
		if (camera == 0) {
			glu.gluPerspective(90, (float) width / (float) height, 0.01, 50.0);
		} else {
			glu.gluPerspective(150, (float) width / (float) height, 5.0, 50.0);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println("123123");
		switch (e.getKeyCode()) {

		case KeyEvent.VK_W:
			move = 0.2;
			//System.out.println("123123");
			break;
		case KeyEvent.VK_S:
			move = -0.2;

			break;
		case KeyEvent.VK_A:
			myRotateY += 1.5;

			break;
		case KeyEvent.VK_D:
			myRotateY += -1.5;

			break;
		case KeyEvent.VK_C:
			if (camera == 0) {
				camera = 1;
			} else {
				camera = 0;
			}

			break;
		default:
			break;
		}
		// System.out.println(angle);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// Point p = e.getPoint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// Point p = e.getPoint();

		/*
		 * if (myMousePoint != null) { int dx = p.x - myMousePoint.x; int dy =
		 * p.y - myMousePoint.y;
		 * 
		 * myRotateY -= dx * ROTATION_SCALE; myRotateX -= dy * ROTATION_SCALE; }
		 * myMousePoint = p;
		 */
	}

	/*
	 * k_b_x: Texturing assist functions
	 */
	private void createChessboard() {
		int i, j;
		for (i = 0; i < imageSize; i++)
			for (j = 0; j < imageSize; j++)
				if ((((i / 8) % 2 == 1) && ((j / 8) % 2 == 1)) || (!((i / 8) % 2 == 1) && !((j / 8) % 2 == 1))) {

					chessImageBuf.put((byte) 0x00); // R = 0
					chessImageBuf.put((byte) 0x00); // G = 0
					chessImageBuf.put((byte) 0x00); // B = 0
					chessImageBuf.put((byte) 0xFF); // A = 1
				} else {

					chessImageBuf.put((byte) 0xFF); // R = 1
					chessImageBuf.put((byte) 0xFF); // G = 1
					chessImageBuf.put((byte) 0xFF); // B = 1
					chessImageBuf.put((byte) 0xFF); // A = 1
				}
		chessImageBuf.rewind();
	}

	/*
	 * for texturing the sphere
	 */

	public void normalize(double v[]) {
		double d = Math.sqrt(v[0] * v[0] + v[1] * v[1] + v[2] * v[2]);
		if (d != 0.0) {
			v[0] /= d;
			v[1] /= d;
			v[2] /= d;
		}
	}

	void normCrossProd(double v1[], double v2[], double out[]) {
		out[0] = v1[1] * v2[2] - v1[2] * v2[1];
		out[1] = v1[2] * v2[0] - v1[0] * v2[2];
		out[2] = v1[0] * v2[1] - v1[1] * v2[0];
		normalize(out);
	}

	double r(double t) {
		double x = Math.cos(2 * Math.PI * t);
		return x;
	}

	double getY(double t) {

		double y = Math.sin(2 * Math.PI * t);
		return y;
	}

}
