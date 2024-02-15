package java3d;

public class Vector3D extends Point3D {

    public Vector3D(){
        super(0, 0, 0);
    }

    public Vector3D(Vector3D v){
        super(v.getPoint3D());
    }

    public Vector3D(Point3D p1, Point3D p2){
        super(p1.vector(p2));
    }

    public Vector3D(double x, double y, double z){
        super(x,y,z);
    }

    public Vector3D(Point3D p){
        super(p);
    }

    public double dotProduct(Vector3D v){
        return (x3D * v.x3D) + (y3D * v.y3D) + (z3D * v.z3D);
    }

    public Vector3D crossProduct(Vector3D v){
        return new Vector3D(
                y3D * v.z3D - z3D * v.y3D,
                z3D * v.x3D - x3D * v.z3D,
                x3D * v.y3D - y3D * v.x3D
        );
    }

    public static double dotProduct(Vector3D v1, Vector3D v2){
        return (v1.x3D * v2.x3D) + (v1.y3D * v2.y3D) + (v1.z3D * v2.z3D);
    }

    public static Vector3D crossProduct(Vector3D v1, Vector3D v2){
        return new Vector3D(
                v1.y3D * v2.z3D - v1.z3D * v2.y3D,
                v1.z3D * v2.x3D - v1.x3D * v2.z3D,
                v1.x3D * v2.y3D - v1.y3D * v2.x3D
        );
    }

    public Vector3D add(Vector3D v){
        x3D += v.x3D;
        y3D += v.y3D;
        z3D += v.z3D;
        return this;
    }

    public Vector3D subtract(Vector3D v){
        x3D -= v.x3D;
        y3D -= v.y3D;
        z3D -= v.z3D;
        return this;
    }

    public Vector3D multiply(double d){
        x3D = (double) (x3D * d);
        y3D = (double) (y3D * d);
        z3D = (double) (z3D * d);
        return this;
    }

    public static Vector3D add(Vector3D v1, Vector3D v2){
        return new Vector3D(v1.x3D + v2.x3D,
        v1.y3D + v2.y3D,
        v1.z3D + v2.z3D);
    }

    public static Vector3D subtract(Vector3D v1, Vector3D v2){
        return new Vector3D(v1.x3D - v2.x3D,
                v1.y3D - v2.y3D,
                v1.z3D - v2.z3D);
    }

    public static Vector3D multiply(Vector3D v, double d){
        return new Vector3D((double) (v.x3D * d),
        (double) (v.y3D * d),
        (double) (v.z3D * d));
    }

    public double magnituteSq(){
        return  (x3D * x3D + y3D * y3D + z3D * z3D);
    }

    public double magnitute(){
        return Math.sqrt(x3D * x3D + y3D * y3D + z3D * z3D);
    }


    public Point3D getPoint3D(){
        return new Point3D(this);
    }

    public boolean equals(Vector3D v){
        return getPoint3D().equals(v.getPoint3D());
    }

    public Vector3D getCopy(){
        return new Vector3D(this);
    }

    public double getAngleWithX(){
        return Math.acos(this.x3D / this.magnitute());
    }

    public double getAngleWithY(){
        return Math.acos(this.y3D / this.magnitute());
    }

    public double getAngleWithZ(){
        return Math.acos(this.z3D / this.magnitute());
    }


    public Vector3D getProjectionOnXY(){
        return new Vector3D(this.x3D,this.y3D,0);
    }

    public Vector3D getProjectionOnYZ(){
        return new Vector3D(0,this.y3D,this.z3D);
    }

    public Vector3D getProjectionOnXZ(){
        return new Vector3D(this.x3D,0,this.z3D);
    }

    public Vector3D getProjectionOn(Vector3D vector3D){
        return vector3D.getCopy().multiply(this.dotProduct(vector3D)/vector3D.magnituteSq());
    }
    public double getAngleWith(Vector3D vector3D){
        return Math.acos(this.dotProduct(vector3D)/(this.magnitute()*vector3D.magnituteSq()));
    }
    public Vector2D toVector2D(){
        return new Vector2D(this.x, this.y);
    }
    public Vector3D normalize(){
        this.multiply(1 / this.magnitute());
        return this;
    }

    public  Vector3D getPerpendicularVector(){
        return getPerpendicularVector(this);
    }

    public static Vector3D getPerpendicularVector(Vector3D vector3D){
        for (Vector3D normal : NormalVectors.NORMALVECTORS){
            Vector3D perpVector = vector3D.crossProduct(normal);
            if (!perpVector.equals(NormalVectors.OVECTOR))
                return perpVector;
        }
        return vector3D;
    }

    public static Vector3D getPerpendicularVector(Vector3D vector3D, Point3D point3D){
        Vector3D backVector = point3D.toVector();
        return backVector.subtract(backVector.getProjectionOn(vector3D));
    }
}
final class NormalVectors{

    final static Vector3D NORMALPOSX = new Vector3D(1, 0, 0);

    final static Vector3D NORMALPOSY = new Vector3D(0, 1, 0);

    final static Vector3D NORMALPOSZ = new Vector3D(0, 0, 1);

    final static Vector3D NORMALNEGX = new Vector3D(-1, 0, 0);

    final static Vector3D NORMALNEGY = new Vector3D(0, -1, 0);

    final static Vector3D NORMALNEGZ = new Vector3D(0, 0, -1);

    final static Vector3D OVECTOR = new Vector3D(0, 0, 0);

    final static Vector3D[] NORMALVECTORS = {NORMALPOSX,NORMALPOSY,NORMALPOSZ,NORMALNEGX,NORMALNEGY,NORMALNEGZ};
}

