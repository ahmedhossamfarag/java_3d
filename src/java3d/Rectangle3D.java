
package java3d;

import java.awt.*;
import java.beans.Transient;
import java.io.Serial;


public class Rectangle3D extends Shape implements ShapeMethods
{

    /**
	 * 
	 */
	@Serial
    private static final long serialVersionUID = 1L;

    protected Plane[] planes = new Plane[6];
    protected Point3D point;

    protected double length;

    protected double width;

    protected double height;
    
    public Rectangle3D(Rectangle3D r){
        this(r.point,r.length, r.width, r.height);
        setProperities(r.getProperities());
    }


    public Rectangle3D(double x3D, double y3D, double z3D,double length, double width, double height){
        this(new Point3D(x3D, y3D, z3D), length, width, height);
    }

    public Rectangle3D(double length, double width, double height){
        this(0, 0, 0,length, width, height);
    }

    public Rectangle3D(Point3D p, Dimension3D d){
        this(p.x3D, p.y3D, p.z3D, d.length, d.width, d.height);
    }

    public Rectangle3D(Point3D p){
        this(p.x3D, p.y3D, p.z3D, 0, 0, 0);
    }

 
    public Rectangle3D(Dimension3D d){
        this(0, 0, 0, d.length, d.width, d.height);
    }

    public Rectangle3D(Point3D point, double length, double width, double height){
        this.point = point.getCopy();
        this.length = length;
        this.width = width;
        this.height = height;
        setPlanes();
    }


    public double getX3D() {
        return point.x3D;
    }


    public double getY3D() {
        return point.y3D;
    }

    public double getZ3D() {
        return point.z3D;
    }
    
    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Transient
    public Rectangle3D getBounds(){
        return new Rectangle3D(point,length, width, height);
    }


    public void setBounds(Rectangle3D r) {
        setBounds(point,r.length, r.width, r.height);
    }

    private void setBounds(Point3D point, double length, double width, double height) {
        this.point = point;
        this.length = length;
        this.width = width;
        this.height = height;
        setPlanes();
    }


    public void setBounds(double x3D, double y3D, double z3D, double length, double width, double height) {
        this.setBounds(new Point3D(x3D, y3D, z3D),length, width, height);

    }



    public Point3D getLocation() {
        return new Point3D(point);
    }

    public void setLocation(Point3D p) {
        point = p.getCopy();
        setPlanes();
    }

    public void setLocation(double x3D, double y3D, double z3D) {
    	this.setLocation(new Point3D(x3D, y3D, z3D));
    }



    public Dimension3D getSize3D() {
        return new Dimension3D(length,width, height);
    }

    public void setSize(Dimension3D d) {
        setSize(d.length, d.width, d.height);
    }

    public void setSize(double length, double width, double height) {
    	this.length = length;
        this.width = width;
        this.height = height;
        setPlanes();
    }



    public Plane[] getPlanes(){
        return planes;
    }
    public Plane getPlane(int i){
        return planes[i];
    }


    @Override
    public Rectangle3D setLinePaint(Paint paint) {
        for (Plane plane : planes)
            plane.linePaint = paint;
        return this;
    }

    @Override
    public Rectangle3D setFillPaint(Paint paint) {
        for (Plane plane : planes)
            plane.fillPaint = paint;
        return this;
    }

    @Override
    public Rectangle3D setStroke(Stroke stroke) {
        for (Plane plane : planes)
            plane.stroke = stroke;
        return this;
    }

    @Override
    public Rectangle3D translate(Vector3D v) {
        for (Plane plane : planes) 
            Shape.translate(plane.getPlanePoints(), v);
        return this;
    }

    @Override
    public Rectangle3D translate(double x, double y, double z) {
        for (Plane plane : planes)
            Shape.translate(plane.getPlanePoints(), x, y, z);
        return this;
    }

    @Override
    public Rectangle3D rotateX(double angle) {
        for (Plane plane : planes)
            Shape.rotateX(plane.getPlanePoints(), angle);
        control();
        return this;
    }

    @Override
    public Rectangle3D rotateY(double angle) {
        for (Plane plane : planes)
            Shape.rotateY(plane.getPlanePoints(), angle);
        control();
        return this;
    }

    @Override
    public Rectangle3D rotateZ(double angle) {
        for (Plane plane : planes)
            Shape.rotateZ(plane.getPlanePoints(), angle);
        control();
        return this;
    }

    @Override
    public Rectangle3D rotateX(double angle, Point3D p) {
        for (Plane plane : planes)
            Shape.rotateX(plane.getPlanePoints(), angle, p);
        control();
        return this;
    }

    @Override
    public Rectangle3D rotateY(double angle, Point3D p) {
        for (Plane plane : planes)
            Shape.rotateY(plane.getPlanePoints(), angle, p);
        control();
        return this;
    }

    @Override
    public Rectangle3D rotateZ(double angle, Point3D p) {
        for (Plane plane : planes)
            Shape.rotateZ(plane.getPlanePoints(), angle, p);
        control();
        return this;
    }

    @Override
    public Rectangle3D setFillChoice(boolean value) {
        this.fillChoice = value;
        return this;
    }
    @Override
    public Rectangle3D setDrawChoice(boolean value) {
        this.drawChoice = value;
        return this;
    }
    public Rectangle3D getCopy(){
        return new Rectangle3D(this);
    }

    private void setPlanes() {
        Point3D newPoint = point.getCopy().translate(-length, width, height);
        planes[5] = new Plane(point, NormalVectors.NORMALPOSY, NormalVectors.NORMALPOSZ, width, height);//Front
        planes[4] = new Plane(newPoint, NormalVectors.NORMALPOSX, NormalVectors.NORMALNEGZ, length, height); //Side
        planes[3] = new Plane(newPoint, NormalVectors.NORMALPOSX, NormalVectors.NORMALNEGY, length, width); //Top
        planes[2] = new Plane(point, NormalVectors.NORMALNEGX, NormalVectors.NORMALPOSZ, length, height);//XSide
        planes[1] = new Plane(newPoint, NormalVectors.NORMALNEGY, NormalVectors.NORMALNEGZ, width, height); //Back
        planes[0] = new Plane(point, NormalVectors.NORMALPOSY, NormalVectors.NORMALNEGX, width, length);//Buttom
    }

    protected void control(){
        this.planes = PlaneController.arrange(this.planes);
    }

    protected Shape[] getProperities(){
        Shape[] shapes = new Shape[6];
        for (byte i = 0; i < 6; i++)
            shapes[i] = planes[i].getProperities();
        return shapes;
    }

    protected void setProperities(Shape[] shapes){
        for (byte i = 0; i < 6; i++)
            planes[i].setProperities(shapes[i]);
    }
}
