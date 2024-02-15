package java3d;

import java.awt.*;

interface ShapeMethods {
    public ShapeMethods setLinePaint(Paint paint);

    public ShapeMethods setFillPaint(Paint paint);
    public ShapeMethods setStroke(Stroke stroke);
    public ShapeMethods translate(Vector3D v);

    public ShapeMethods translate(double x, double y, double z);

    public ShapeMethods rotateX(double angle);

    public ShapeMethods rotateY(double angle);

    public ShapeMethods rotateZ(double angle);

    public ShapeMethods rotateX(double angle, Point3D p);

    public ShapeMethods rotateY(double angle, Point3D p);

    public ShapeMethods rotateZ(double angle, Point3D p);

    public ShapeMethods setFillChoice(boolean value);

    public ShapeMethods setDrawChoice(boolean value);

    public ShapeMethods getCopy();
    
}
