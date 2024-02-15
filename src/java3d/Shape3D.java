package java3d;

import java.awt.*;

public class Shape3D extends Shape implements ShapeMethods {
    protected static Point3D referencePoint = new Point3D(1000,1000,1000);
    protected   Point3D[][] points;

    protected Plane[] planes;

    public Shape3D(Point3D[][] points) throws PointsLessThanThreeException {
        setPoints(points);
    }

    public Shape3D(Shape3D shape)  {
        try {
            setPoints(shape.points);
        } catch (PointsLessThanThreeException e) {
            throw new RuntimeException(e);
        }
        setProperities(shape.getProperities());
    }

    public Shape3D(Plane...planes){
        this.planes = planes;
        control();
    }

    public Shape3D(Point3D[] points) throws PointsLessThanThreeException {
        setPoints(new Point3D[][]{points});
    }

    public Shape3D(double...arr) throws PointsLessThanThreeException {
        if (arr.length < 9)
            throw new PointsLessThanThreeException();
        this.points = new Point3D[][] {PlaneController.getPoints(arr)};
        setPlanes();
        control();
    }

    public Shape3D(double[]...arr) throws PointsLessThanThreeException {
        for (double[] doubles : arr)
            if (doubles.length < 9)
                throw new PointsLessThanThreeException();
        this.points = PlaneController.getPoints(arr);
        setPlanes();
        control();
    }

    public void  setPoints(Point3D[][] points) throws PointsLessThanThreeException {
        for (Point3D[] point3DS : points)
            if (point3DS.length < 3)
                throw new PointsLessThanThreeException();
        Point3D[][] point3DS = new Point3D[points.length][];
        for (byte i = 0; i< point3DS.length; i++){
            Point3D[] point3DS1 = new Point3D[points[i].length];
            for (byte j = 0; j< point3DS1.length; j++)
                point3DS1[j] = points[i][j].getCopy();
            point3DS[i] = point3DS1;
        }
        this.points = point3DS;
        setPlanes();
        control();
    }

    public Plane getPlane(int i){
        return planes[i];
    }

    public Shape3D setLinePaint(Paint paint){
        for (Plane plane : planes)
            plane.linePaint = paint;
        return this;
    }

    public Shape3D setFillPaint(Paint paint){
        for (Plane plane : planes)
            plane.fillPaint = paint;
        return this;
    }

    public Shape3D setStroke(Stroke stroke){
        for (Plane plane : planes)
            plane.stroke = stroke;
        return this;
    }

    public Shape3D translate(Vector3D v){
        for (Plane P : planes)
            Shape.translate(P.getPlanePoints(), v);
        return this;
    }

    public Shape3D translate(double x, double y, double z){
        for (Plane P : planes)
               Shape.translate(P.getPlanePoints(), x, y, z);
        return this;
    }

    public Shape3D rotateX(double angle) {
        for (Plane P : planes)
            Shape.rotateX(P.getPlanePoints(), angle);
        control();
        return this;
    }

    public Shape3D rotateY(double angle) {
        for (Plane P : planes)
            Shape.rotateY(P.getPlanePoints(),angle);
        control();
        return this;
    }

    public Shape3D rotateZ(double angle) {
        for (Plane P : planes)
            Shape.rotateZ(P.getPlanePoints(), angle);
        control();
        return this;
    }

    public Shape3D rotateX(double angle, Point3D p) {
        for (Plane P : planes)
            Shape.rotateX(P.getPlanePoints(), angle, p);
        control();
        return this;
    }

    public Shape3D rotateY(double angle, Point3D p) {
        for (Plane P : planes)
            Shape.rotateY(P.getPlanePoints(), angle, p);
        control();
        return this;
    }

    public Shape3D rotateZ(double angle, Point3D p) {
        for (Plane P : planes)
            Shape.rotateZ(P.getPlanePoints(), angle, p);
        control();
        return this;
    }

    public Shape3D setFillChoice(boolean value){
        this.fillChoice = value;
        return this;
    }

    public Shape3D setDrawChoice(boolean value){
        this.drawChoice = value;
        return this;
    }

    public Shape3D getCopy(){
        return new Shape3D(this);
    }

    private void setPlanes() throws PointsLessThanThreeException {
        planes = new Plane[points.length];
        for (byte i = 0; i < planes.length; i++)
            planes[i] = new Plane(points[i]);
    }
    protected void control(){
        this.points = PlaneController.arrange(this.points);
        this.planes = PlaneController.arrange(planes);
    }

    protected Shape[] getProperities(){
        Shape[] shapes = new Shape[planes.length];
        for (byte i = 0; i < planes.length; i++)
            shapes[i] = planes[i].getProperities();
        return shapes;
    }

    protected void setProperities(Shape[] shapes){
        for (byte i = 0; i < planes.length; i++)
            planes[i].setProperities(shapes[i]);
    }

}
