package java3d;


import java.awt.Paint;
import java.awt.Stroke;

public class Line3D extends Shape implements ShapeMethods {

	protected Point3D[]  points;
	protected Point3D p1;

	protected Point3D p2;

	protected Vector3D vector;


	public Line3D(Point3D p1, Point3D p2) {
		this(p1.x3D, p1.y3D, p1.z3D, p2.x3D, p2.y3D, p2.z3D);
	}

	public Line3D(Point3D point3D, Vector3D vector3D){
		this(point3D,point3D.getCopy().translate(vector3D));
	}

	public Line3D(Line3D line3D) {
		this(line3D.p1, line3D.p2);
		setProperities(line3D.getProperities());
	}

	public Line3D(double x1, double y1, double z1, double x2, double y2, double z2) {
		super();
		p1 = new Point3D(x1, y1, z1);
		p2 = new Point3D(x2, y2, z2);
		this.vector = new Vector3D(p1,p2);
		this.points = new Point3D[]{p1, p2};
	}

	public void setFirstPoint(Point3D p) {
		this.p1.move(p);
	}

	public void setSecondPoint(Point3D p) {
		this.p2.move(p);
	}

	public Point3D getFirstPoint() {
		return this.p1;
	}
	
	public Point3D getSecondPoint() {
		return this.p2;
	}
	
	public void setFirstPoint(double x, double y, double z) {
		p1.move(x, y, z);
	}
	
	public void setSecondPoint(double x, double y, double z) {
		p2.move(x, y, z);
	}

	public void translateFirstPoint(Vector3D v){
		this.p1.translate(v);
	}

	public void translateSecondPoint(Vector3D v) {
		this.p2.translate(v);
	}

	public Line3D getCopy(){
		return new Line3D(this);
	}

	public double getDistance(Point3D point3D){
		return Math.abs(point3D.vector(p1).crossProduct(vector).magnitute() / vector.magnitute());
	}

	@Override
	public Line3D setLinePaint(Paint paint) {
		this.linePaint = paint;
		return this;
	}

	@Override
	public Line3D setFillPaint(Paint paint) {
		this.fillPaint = paint;
		return this;
	}

	@Override
	public Line3D setStroke(Stroke stroke) {
		this.stroke = stroke;
		return this;
	}

	@Override
	public Line3D translate(Vector3D v) {
		Shape.translate(this.points, v);
		return this;
	}

	@Override
	public Line3D translate(double x, double y, double z) {
		Shape.translate(this.points, x, y, z);
		return this;
	}

	@Override
	public Line3D rotateX(double angle) {
		Shape.rotateX(this.points, angle);
		return this;
	}

	@Override
	public Line3D rotateY(double angle) {
		Shape.rotateY(this.points, angle);
		return this;
	}

	@Override
	public Line3D rotateZ(double angle) {
		Shape.rotateZ(this.points, angle);
		return this;
	}

	@Override
	public Line3D rotateX(double angle, Point3D p) {
		Shape.rotateX(this.points, angle, p);
		return this;
	}

	@Override
	public Line3D rotateY(double angle, Point3D p) {
		Shape.rotateY(this.points, angle, p);
		return this;
	}

	@Override
	public Line3D rotateZ(double angle, Point3D p) {
		Shape.rotateZ(this.points, angle, p);
		return this;
	}

	@Override
	public Line3D setFillChoice(boolean value) {
		this.fillChoice = value;
		return this;
	}
	@Override
	public Line3D setDrawChoice(boolean value) {
		this.drawChoice = value;
		return this;
	}
	
	protected Shape getProperities(){
		return new Shape(this.stroke, this.linePaint, this.fillPaint, this.fillChoice, this.drawChoice);
	}

	protected void setProperities(Shape shape){
		this.stroke = shape.stroke;
		this.linePaint = shape.linePaint;
		this.fillPaint = shape.fillPaint;
		this.fillChoice = shape.fillChoice;
		this.drawChoice = shape.drawChoice;
	}


	public String toString(){
		return p1.toString() + " --> " + p2.toString();
	}
}
