package java3d;

import java.awt.*;

class Shape {
    protected Stroke stroke = new BasicStroke(1);
    
    protected Paint linePaint = Color.BLACK ;
    
    protected Paint fillPaint = Color.WHITE;

    protected boolean fillChoice = true;
    
    protected boolean drawChoice = true;

    protected Shape(Stroke stroke, Paint linePaint, Paint fillPaint, boolean fillChoice, boolean drawChoice){
        this.stroke = stroke;
        this.linePaint = linePaint;
        this.fillPaint = fillPaint;
        this.fillChoice = fillChoice;
        this.drawChoice = drawChoice;
    }

    public Shape() {

    }

    public Paint getLinePaint(){
        return this.linePaint;
    }

    public Paint getFillPaint(){
        return this.fillPaint;
    }

    public Stroke getStroke(){
        return this.stroke;
    }

    public static void translate(Point3D[] points, Vector3D v){
        for (Point3D P : points)
                P.translate(v);
    }

    public static void translate(Point3D[] points, double x, double y, double z){
        for (Point3D P : points)
                P.translate(x, y, z);
    }

    public static void rotateX(Point3D[] points, double angle) {
            for (Point3D P : points)
                P.rotateX(angle);
    }

    public static void rotateY(Point3D[] points, double angle) {
        for (Point3D P : points)
                P.rotateY(angle);
    }

    public static void rotateZ(Point3D[] points, double angle) {
        for (Point3D P : points)
                P.rotateZ(angle);
    }

    public static void rotateX(Point3D[] points, double angle, Point3D p) {
        for (Point3D P : points)
                P.rotateX(angle, p);
    }

    public static void rotateY(Point3D[] points, double angle, Point3D p) {
        for (Point3D P : points)
                P.rotateY(angle, p);
    }

    public static void rotateZ(Point3D[] points, double angle, Point3D p) {
        for (Point3D P : points)
                P.rotateZ(angle, p);
    }

}
