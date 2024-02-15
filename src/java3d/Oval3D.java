package java3d;
 
public class Oval3D extends Shape {
     protected Point3D[] points;

    protected double radius;

    protected NormalVector normal;

    protected Point3D center;
    protected Point2D[] point2DS;
    protected Double[] angles;
    protected Double[] radius_s;

    private OvalController ovalController;
    public Oval3D(Point3D point3D, NormalVector normal, double radius){
        this.center = point3D.getCopy();
        this.normal = normal;
        this.radius = radius;
        control();
    }


    public double getRadius(){
        return radius;
    }

    public NormalVector getNormal(){
        return normal ;
    }

    public Oval3D setRadius(double radius){
        this.radius = radius;
        control();
        return this;
    }

    public Oval3D setNormal(NormalVector normal){
        this.normal = normal ;
        control();
        return this;
    }

    protected void control(){
        ovalController = new OvalController(center,normal.get(),radius);
        point2DS = ovalController.arrayList.toArray(Point2D[]::new);
        angles = ovalController.angles.toArray(Double[]::new);
        radius_s = ovalController.radius.toArray(Double[]::new);
        points = ovalController.points;
    }

}
