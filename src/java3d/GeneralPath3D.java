package java3d;


import java.awt.geom.GeneralPath;
import java.util.ArrayList;

public class GeneralPath3D {

    private ArrayList<Point3D> facePoints = new ArrayList<>();
    private ArrayList<Point3D[]> points = new ArrayList<>();

    private GeneralPath generalPath = new GeneralPath();


    public GeneralPath3D(){

    }
    public GeneralPath3D(Shape3D shape3D){

    }

    public void moveTo(Point3D p){
        generalPath.moveTo(p.x, p.y);
        facePoints.add(p);
    }

    public void lineTo(Point3D p){
        generalPath.lineTo(p.x, p.y);
        facePoints.add(p);
    }

    public void moveTo(double x, double y, double z){
        generalPath.moveTo(Point3D.getX(x, y, z), Point3D.getY(x, y, z));
        facePoints.add(new Point3D(x, y, z));
    }

    public void lineTo(double x, double y, double z){
        generalPath.lineTo(Point3D.getX(x, y, z), Point3D.getY(x, y, z));
        facePoints.add(new Point3D(x, y, z));
    }

    public void endFace(){
        points.add(facePoints.toArray(Point3D[]::new));
        facePoints = new ArrayList<>();
    }
    public void closePath(){
        generalPath.closePath();
    }

    public GeneralPath getGeneralPath(){
        return generalPath;
    }

    public Shape3D getShape() throws PointsLessThanThreeException {
        return new Shape3D(facePoints.toArray(Point3D[][]::new));
    }

    public  void draw(Point3D[][] point3DS){
        for (Point3D[] P : point3DS){
            moveTo(P[0]);

            for (byte i= 1;i<P.length;i++){
                lineTo(P[i]);
            }

            closePath();

        }
    }

    public void draw(Point3D[] point3DS){
        draw(new Point3D[][]{point3DS});
    }

    public void draw(Shape3D shape){
        draw(shape.points);
    }
    public void draw(Plane plane){
        draw(plane.getPlanePoints());
    }
}
