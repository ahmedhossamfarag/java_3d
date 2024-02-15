package java3d;

import java.util.ArrayList;

class OvalController {
    protected  ArrayList<Point2D> arrayList = new ArrayList<>();
    protected  ArrayList<Double> angles= new ArrayList<>();
    protected  Point3D[] points;
    protected  ArrayList<Double> radius = new ArrayList<>();


    protected OvalController(Point3D point3D, Vector3D normal, double radis){
        Plane plane = new Plane(point3D,radis,radis,normal);
        this.points = plane.getPlanePoints();
        Point2D[] point2DS = toPoints2D(points);
        double[] angles = getAngles(point2DS);
        setPointsAndAngles(angles,point2DS);
    }


    protected  Point2D[] toPoints2D(Point3D[] point3DS){
        Point2D[] point2DS = new Point2D[point3DS.length];
        for (byte i = 0; i < point2DS.length; i++)
            point2DS[i] = point3DS[i].toPoint2D();
        return point2DS;
    }

    protected  double[] getAngles(Point2D[] point2DS){
        Vector2D v1 = point2DS[0].vector(point2DS[1]);
        Vector2D v2 = point2DS[0].vector(point2DS[3]);
        Vector2D v3 = point2DS[1].vector(point2DS[2]);
        return new double[]{
                Math.abs(v1.getAngleWith(v2)),
                Math.abs(v3.getAngleWith(v1.multiply(-1)))
        };
    }

    protected  double getAngle(Point2D p1, Point2D p2, Point2D p3){
        Vector2D v1 = p1.vector(p2);
        Vector2D v2 = p1.vector(p3);
        return v1.getAngleWith(v2);
    }

    protected  void setPointsAndAngles(double[] doubles, Point2D[] point2DS){
        int i,j;
        if (doubles[0] > doubles [1]){
            i = 0;
            j = 3;
        }
        else {
            i = 1;
            j = 0;

        }
            arrayList.add(point2DS[i]);
            arrayList.add(Point2D.getCenter(point2DS[i+2],point2DS[i+1]));
            arrayList.add(Point2D.getCenter(point2DS[i+2],point2DS[j]));
            helper2(0);

            arrayList.add(point2DS[i+2]);
            arrayList.add(Point2D.getCenter(point2DS[i],point2DS[i+1]));
            arrayList.add(Point2D.getCenter(point2DS[i],point2DS[j]));

            helper2(3);

            helper1(1);
            helper1(2);

            helper2(6);
            helper2(9);

    }

    protected  void helper1(int i){
        arrayList.add(intersectionPoint(
                arrayList.get(0),arrayList.get(i),
                arrayList.get(3),arrayList.get(i+3)
        ));
        arrayList.add(arrayList.get(i));
        arrayList.add(arrayList.get(i+3));
    }

    protected  void helper2(int i){
        radius.add(arrayList.get(i).vector(arrayList.get(i+1)).getMagnitute());
        angles.add(arrayList.get(i).vector(arrayList.get(i+1)).getAngleWithPosX());
        angles.add(getAngle(arrayList.get(i++),arrayList.get(i++),arrayList.get(i)));

    }


    protected  Point2D intersectionPoint(double a1, double b1, double c1, double a2, double b2, double c2){
        if ((a1 >= 1E-6) && (a2 >= 1E-6)) {
            c2 = (c2 * (-a1 / a2) + c1) / (b2 * (-a1 / a2) + b1);
            c1 = (c1 - (c2 * b1)) / a1;
            System.out.println("zero");
            return new Point2D(c1,c2);
        }
        else{
            c2 = (c2 * (-b1 / b2) + c1) / (a2 * (-b1 / b2) + a1);
            c1 = (c1 - (c2 * a1)) / b1;
            return new Point2D(c2,c1);
        }

    }

    protected  Point2D intersectionPoint(double[] d1, double[] d2){
        return intersectionPoint(d1[0], d1[1], d1[2], d2[0], d2[1], d2[2]);
    }

    protected  double[] lineEquation(double x1, double y1, double x2, double y2){
        return new double[]{
                y1-y2,
                x2-x1,
                 (x1 * (y1-y2)) - (y1 * (x1-x2))
        };
    }

    protected  double[] lineEquation(Point2D p1, Point2D p2){
        return lineEquation(p1.x, p1.y, p2.x, p2.y);
    }

    protected  Point2D intersectionPoint(Point2D p1, Point2D p2, Point2D p3, Point2D p4){
        return intersectionPoint(lineEquation(p1, p2),lineEquation(p3, p4));
    }




}
