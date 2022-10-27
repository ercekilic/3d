

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class App extends AppRoot {

	private float xrotation,yrotation,zrotation;
	private double [][] cubeUsingQuads= {
			// front
			{-1, 1, -1},//1
			{1, -1, 1}, //2
			{1, 1, 1},  //3
			{-1, 1, 1}  ,//4
		    //top
			{-1, 1, 1} , //4
			{1, 1, 1},  //3
			{1, 1, -1},  //5
			{-1, 1, -1}, //6
			//left 
			{-1, -1, 1} , //1
			{-1, 1, 1} , //4
			{-1, 1, -1} , //6
			{-1, -1, -1} , //7
			//right
			{1, 1, -1} , //5
			{1, 1, 1} , //3
			{1, 1, 1} , //2
			{-1, -1, -1} ,//8
			//bottom
			{-1, -1, 1} , //1
			{-1, -1, -1} , //7
			{1, -1, -1} , //8
			{1, -1, 1} ,//2
			
			//back
			{-1, 1, -1},//6
			{1, 1, -1},//5
			{1, -1, -1},//8
			{-1, -1, -1},//7

	};
	private double [][] figurePolygon= {
			
		
	};
	
	
	public void display(GLAutoDrawable d) {
		
		clearCanvas();
		
		g.glTranslatef(0, 0, -6.6f);
		g.glRotatef(xrotation,1,0,0);
		g.glRotatef(yrotation,0,1,0);
		g.glRotatef(zrotation,0,0,1);
		
		
		
		
		//drawFigureQuads3f(cubeUsingQuads);
		//drawFigureTriangles3f(figureTriangle);
		drawFigure(figurePolygon);
		
	   
		    g.glFlush();
	}
	public void registerAllKeyActions() {
		registerKeyAction(KeyEvent.VK_UP, new AbstractAction() {
		
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				xrotation += 3;
			}
		});
		registerKeyAction(KeyEvent.VK_DOWN, new AbstractAction() {
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) {
				xrotation -= 3;
			}
		});
		registerKeyAction(KeyEvent.VK_LEFT, new AbstractAction() {
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) {
				yrotation += 3;
			}
		});
		registerKeyAction(KeyEvent.VK_RIGHT, new AbstractAction() {
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) {
				yrotation -= 3;
			}
		});
	}

	
	public static void main(String[] args){
		

	App app=new App();
	app.start();
}
}