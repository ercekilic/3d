

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;




public abstract class AppRoot implements GLEventListener{
	protected GL2 g;
	
	private static GLProfile profile;
	private static GLCapabilities capabilities;
	
	private FPSAnimator fpsAnimator;
	private GLCanvas glcanvas;
	
	private JFrame frame;
	private JPanel panel;
	
	private static ActionMap actionMap;
	private static InputMap inputMap;
	
	private static final GLU glu = new GLU();
	
	public abstract void registerAllKeyActions();
	public abstract void display(GLAutoDrawable d);
	
	private SatirToObject sekiller  = null;
	
	
	protected void drawFigure3f(int mode,double[][] figure) {
	g.glBegin(GL2.GL_LINES);
		for(KutleYuzeyi kutleYuzeyi : sekiller.getKutleYuzeyleri())
		{
		
			for(Yuzey yuzey : kutleYuzeyi.getYuzeyler())
			{
			
				for(Kenar kenar : yuzey.getKenarlar())
				{
					  g.glColor3f( 0.0f,1.0f,0.0f );
				      g.glVertex3d(Double.valueOf(kenar.getaNoktasi().getX()).doubleValue()/100, Double.valueOf(kenar.getaNoktasi().getY()).doubleValue()/100,
				      Double.valueOf(kenar.getaNoktasi().getZ()).doubleValue()/100);
				}
			}
		}
		
			/*if(null == figure || (figure[0].length != 3)) return;
		g.glBegin(mode);
	
		for (int v=0; v<figure.length; v++) {
			
			g.glVertex3d(figure[v][0], figure[v] [1], figure[v] [2]);
		}*/
	//g.glVertex3f(-1, -1, 1);
	 g.glEnd();
	}
	/*
	protected void drawFigureQuads3f(double[][] figure) {
		drawFigure3f(GL2.GL_QUADS,figure);
		
	}
	protected void drawFigureTriangles3f(double[][] figure) {
		drawFigure3f(GL2.GL_POLYGON,figure);
		
	}*/
	protected void drawFigure(double[][] figure) {
		drawFigure3f(GL2.GL_LINES,figure);
		
	}
	
	

	
	private void initGLObjects()
	{
	  profile = GLProfile.get(GLProfile.GL2);
	  capabilities = new GLCapabilities(profile);
	
	}
	private void createWindow() {
		glcanvas = new GLCanvas(capabilities);
		glcanvas.addGLEventListener(this);
		glcanvas.setSize(800,800);
		
		frame = new JFrame("sc1");
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int  x = (d.width-frame.getWidth())/2;
		int  y = (d.width-frame.getHeight())/2;
		
		frame.setLocation(x, y);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(0,0));
		
		frame.add(panel);
		
		actionMap = panel.getActionMap();
		inputMap =panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		frame.addWindowListener(new WindowAdapter(){
			public void windowIsClosing() {
				if(fpsAnimator.isStarted()) {fpsAnimator.stop();
				System.exit(0);
				}
			}
		});
		
	}
		public void registerKeyAction(Integer key , AbstractAction a) {
			inputMap.put(KeyStroke.getKeyStroke(key,0),key.toString());
			actionMap.put(key.toString(), a);
			
			
		}
		
		public void start() {
			fpsAnimator.start();
			
		}
		public void clearCanvas() {
		  g.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);	
		  g.glLoadIdentity();
		  
		
		}
			
		public void init(GLAutoDrawable d) {
			g = d.getGL().getGL2();
			g.glClear(GL2.GL_COLOR_BUFFER_BIT);
			g.glShadeModel(GL2.GL_SMOOTH);
			g.glClearColor(0, 0, 0, 0);
			g.glClearDepth(1);
			g.glEnable(GL2.GL_DEPTH_TEST);
			g.glDepthFunc(GL2.GL_LEQUAL);
			g.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
			
			 DosyaOkuma dosyaOkuyucu = new DosyaOkuma("ilk_uc_kutle_Yuzey_Kenar_Kose.txt");
			 
			 List<String> lines=null;
			try {
				lines = dosyaOkuyucu.getLines();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			  sekiller= new SatirToObject(lines);
			 sekiller.createObjects();
			 
			
		}
		public void reshape(GLAutoDrawable d,int x,int y,int width,int height) {
			g.glViewport(0,0,width,height);
			g.glMatrixMode(GL2.GL_PROJECTION);
			g.glLoadIdentity();
			glu.gluPerspective(45f, (float) width/(float) height,1,20);
			g.glMatrixMode(GL2.GL_MODELVIEW);
			g.glLoadIdentity();
		}
		public void dispose(GLAutoDrawable drawable) {
			
		}
		public AppRoot() {
			initGLObjects();
			createWindow();
			registerAllKeyActions();
			fpsAnimator= new FPSAnimator(glcanvas,200,true);
			frame.setVisible(true);
			
		}
	
}
