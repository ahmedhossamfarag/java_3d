package java3d;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Animation extends Canvas implements KeyListener,Runnable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -777785629664481328L;

	protected Shape3D shape3D;

    private int step = 10;

    private double thita = Math.PI / 5;

    private Point3D point3D;

    Thread thread;

    boolean movable = false;

    Vector3D velocity;

    public  Animation(Shape3D shape3D){
        this.shape3D = shape3D;
        point3D = shape3D.planes[0].getPlanePoints()[0];
        this.setSize(1000,1000);
        this.setBackground(Color.black);
        this.addKeyListener(this);
    }

    public Animation(Rectangle3D rectangle3D){
        this(new Shape3D(rectangle3D.getPlanes()));
    }

    public Animation(Plane plane){
        this(new Shape3D(plane));
    }

    public void paint(Graphics graphics){
        Graphics3D graphics3D = new Graphics3D(graphics);
        graphics3D.graphics2d.translate(this.getWidth()/3,this.getHeight()/3);
        graphics3D.fillShape(shape3D);
    }

    public void setStep(int value){
        this.step = value;
    }

    public void setThita(double value){
        this.thita = value;
    }

    public void setPointOfRotation(Point3D point3D){
        this.point3D = point3D.getCopy();
    }

    public void addMovement(Vector3D velocity){
        thread = new Thread(this);
        thread.start();
        this.velocity = velocity.getCopy().multiply(1.0/100);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'u')
            shape3D.translate(0,0,step);
        if (e.getKeyChar() == 'd')
            shape3D.translate(0,0,-step);
        if (e.getKeyChar() == 'r')
            shape3D.translate(-step,step,0);
        if (e.getKeyChar() == 'l')
            shape3D.translate(step,-step,0);
        if (e.getKeyChar() == 'x')
            shape3D.rotateX(thita, point3D);
        if (e.getKeyChar() == 'y')
            shape3D.rotateY(thita, point3D);
        if (e.getKeyChar() == 'z')
            shape3D.rotateZ(thita, point3D);
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void run() {
            while (true) {
                shape3D.translate(this.velocity);
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
    }
}
