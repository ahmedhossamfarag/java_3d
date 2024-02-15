package java3d;


class PlaneController {

    protected static Point3D getAverPoint(Point3D[] point3DS) {
        double x = 0, y = 0, z = 0;
        for (Point3D point3D : point3DS) {
            x += point3D.x3D;
            y += point3D.y3D;
            z += point3D.z3D;
        }
        return new Point3D(x / point3DS.length,y / point3DS.length,z / point3DS.length);
    }
        protected static Point3D[] getAverPoint(Point3D[][] point3DS){
            Point3D[] point3DS1 = new Point3D[point3DS.length];
            for (byte i = 0; i< point3DS.length ; i++)
                point3DS1[i] = getAverPoint(point3DS[i]);
            return  point3DS1;
        }

        protected static double[] getDistances(Point3D[] point3DS){
            double[] doubles = new double[point3DS.length];
            for (byte i = 0; i< point3DS.length ; i++)
                doubles[i] = point3DS[i].distance(Shape3D.referencePoint);
            return doubles;
        }
        protected static int max(double[] doubles){
        int i = 0;
        for (int j = 1; j < doubles.length; j++)
            if (doubles[j] > doubles[i])
                i = j;
        doubles[i] = 0;
        return i;
        }


        protected static int[] getArrange(double[] doubles){
            int[] indexes = new int[doubles.length];
            for (byte i = 0;i<doubles.length;i++)
                indexes[i] = max(doubles);
            return indexes;
        }

        protected static Point3D[][] arrange(Point3D[][] point3DS){
            if (point3DS == null)
                return new Point3D[0][];
            int[] ints = getArrange(getDistances(getAverPoint(point3DS)));
            Point3D[][] point3DS1 = new Point3D[point3DS.length][];
            for (byte i = 0; i< point3DS.length ; i++)
                point3DS1[i] = point3DS[ints[i]];
            return point3DS1;

        }

        protected static Plane[] arrange(Plane[] planes){
            if (planes == null)
                return new Plane[0];
            Point3D[][] point3DS = new Point3D[planes.length][];
            for (byte i = 0; i< point3DS.length ; i++)
                point3DS[i] = planes[i].getPlanePoints();
            int[] ints = getArrange(getDistances(getAverPoint(point3DS)));
            Plane[] planes1 = new Plane[point3DS.length];
            for (byte i = 0; i< point3DS.length ; i++)
                planes1[i] = planes[ints[i]];
            return planes1;
        }








    protected static Vector3D getNormal(Point3D[] point3DS){
        return point3DS[0].vector(point3DS[1]).crossProduct(point3DS[0].vector(point3DS[2]));
    }

    protected static Point3D[] getPoints(Point3D point3D,Vector3D v1, Vector3D v2){
        return new Point3D[]{point3D.getCopy(),
                point3D.getCopy().translate(v1),
                point3D.getCopy().translate(v1).translate(v2),
                point3D.getCopy().translate(v2)};
    }
    protected static Point3D[] getPoints(Point3D point3D,Vector3D normal, double l, double w){
        Vector3D v1;
        if (point3D.toVector().crossProduct(normal).equals(NormalVectors.OVECTOR))
            v1 = normal.getPerpendicularVector().normalize().multiply(l/2);
        else
            v1 = Vector3D.getPerpendicularVector(normal,point3D).normalize().multiply(l/2);
        Vector3D v2 = normal.crossProduct(v1).normalize().multiply(w/2);
        return new Point3D[]{point3D.getCopy().translate(v1).translate(v2),
                point3D.getCopy().translate(v1.multiply(-1)).translate(v2),
                point3D.getCopy().translate(v1).translate(v2.multiply(-1)),
                point3D.getCopy().translate(v1.multiply(-1)).translate(v2)};
    }

    protected static Point3D[] getPoints(double... arr){
        Point3D[] point3DS = new Point3D[arr.length / 3];
        byte i = 0;
        for (byte j = 0; j + 2 <arr.length;)
            point3DS[i++] = new Point3D(arr[j++], arr[j++], arr[j++]);
        return point3DS;
    }
    protected static Point3D[][] getPoints(double[]...arr) {
        Point3D[][] point3DS = new Point3D[arr.length][];
        for (byte i = 0; i < arr.length; i++)
            point3DS[i] = getPoints(arr[i]);
        return point3DS;
    }
}
