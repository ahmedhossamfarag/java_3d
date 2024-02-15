package java3d;

import java.awt.*;

public class Plane extends  Shape implements ShapeMethods {

    private Point3D[] planePoints;
    private Point3D centre;
    private double length;
    public double width;
    private Vector3D normal;

    private boolean changable = false;

    public Plane(Point3D point, double length, double width, Vector3D normal) {
        this.centre = point;
        this.length = length;
        this.width = width;
        this.normal = normal;
        this.planePoints = new Point3D[4];
        planePoints();
        changable = true;
    }

    public Plane(Plane plane){
        this(new Point3D(plane.centre),plane.length, plane.width,new Vector3D(plane.normal));
        setProperities(plane.getProperities());
    }

    public Plane(Point3D[] planePoints) throws PointsLessThanThreeException {
        if (planePoints.length < 3)
            throw  new PointsLessThanThreeException();
        this.setPoints(planePoints);
        this.normal = PlaneController.getNormal(this.planePoints);
        setCenter();
    }
    public Plane(Point3D point, double length, double width) {
        this(point, length, width, new Vector3D(0, 0, 1));
    }

    public Plane(Point3D point, Vector3D normal) {
        this(point, 100, 100, normal);
    }
    public Plane(Point3D point, Vector3D v1,Vector3D v2) {
        this.planePoints = PlaneController.getPoints(point,v1,v2);
        this.width = v1.magnitute();
        this.length = v2.magnitute();
        this.normal = PlaneController.getNormal(this.planePoints);
        setCenter();
        changable = true;
    }

    public Plane(Point3D point, Vector3D v1, Vector3D v2, double length, double width) {
        this(point, v1.normalize().multiply(length), v2.normalize().multiply(width) );
    }

    private void planePoints(){
        this.planePoints = PlaneController.getPoints(centre,normal,length,width);
        setCenter();

    }

    public Plane setPoints(Point3D[] planePoints) throws PointsLessThanThreeException {
        if (planePoints.length < 3)
            throw new PointsLessThanThreeException();
        this.planePoints = planePoints;
        setCenter();
        this.normal = PlaneController.getNormal(this.planePoints);
        changable = false;
        return this;
    }

    public Point3D[] getPlanePoints(){
            return planePoints;
    }

    public Point3D getCentre(){
        return centre;
    }

    public Plane setNormal(Vector3D v){
        this.normal = v;
        planePoints();
        return this;
    }

    public Vector3D getNormal(){
        return new Vector3D(normal);
    }

    public Plane setWidth(double width){
        if (changable) {
            this.width = width;
            planePoints();
        }
        return this;
    }

    public double getWidth(){
        if (!changable)
            return 0;
        return width;
    }

    public Plane setLength(double length){
        if (changable) {
            this.length = length;
            planePoints();
        }
        return this;
    }

    public double getLength(){
        if (!changable)
            return 0;
        return length;
    }
    @Override
    public Plane setLinePaint(Paint paint) {
        this.linePaint = paint;
        return this;
    }

    @Override
    public Plane setFillPaint(Paint paint) {
        this.fillPaint = paint;
        return this;
    }

    @Override
    public Plane setStroke(Stroke stroke) {
        this.stroke = stroke;
        return this;
    }

    @Override
    public Plane translate(Vector3D v) {
        Shape.translate(this.planePoints, v);
        return this;
    }

    @Override
    public Plane translate(double x, double y, double z) {
        Shape.translate(this.planePoints, x, y, z);
        return this;
    }

    @Override
    public Plane rotateX(double angle) {
        Shape.rotateX(this.planePoints, angle);
        return this;
    }

    @Override
    public Plane rotateY(double angle) {
        Shape.rotateY(this.planePoints, angle);
        return this;
    }

    @Override
    public Plane rotateZ(double angle) {
        Shape.rotateZ(this.planePoints, angle);
        return this;
    }

    @Override
    public Plane rotateX(double angle, Point3D p) {
        Shape.rotateX(this.planePoints, angle, p);
        return this;
    }

    @Override
    public Plane rotateY(double angle, Point3D p) {
        Shape.rotateY(this.planePoints, angle, p);
        return this;
    }

    @Override
    public Plane rotateZ(double angle, Point3D p) {
        Shape.rotateZ(this.planePoints, angle, p);
        return this;
    }

    @Override
    public Plane setFillChoice(boolean value) {
        this.fillChoice = value;
        return this;
    }
    @Override
    public Plane setDrawChoice(boolean value) {
        this.drawChoice = value;
        return this;
    }

    public Plane getCopy(){
        return new Plane(this);
    }

    public double getDistance(Point3D point3D){
        return Math.abs(point3D.vector(centre).dotProduct(normal) / normal.magnitute());
    }

    protected void setCenter(){
        centre = PlaneController.getAverPoint(planePoints);
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
}

