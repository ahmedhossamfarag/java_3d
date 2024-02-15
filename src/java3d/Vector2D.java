package java3d;

class Vector2D extends Point2D {

    protected Vector2D(Point2D p1, Point2D p2){
        super(p2.x - p1.x, p2.y - p1.y);
    }
    protected Vector2D(Point2D p){
        super(p.x, p.y);
    }

    protected Vector2D(double x, double y){
        super(x, y);
    }
    protected double getSlope(){
        return this.y / (double)this.x;
    }
    protected double getAngleWithPosX(){
        if(x > 0)
            if(y > 0)
                return Math.atan((getSlope()));
            else
                return Math.atan((getSlope()));
        else
            if(y>0)
                return Math.PI + Math.atan((getSlope()));
            else
                return Math.atan((getSlope())) - Math.PI ;
    }

    protected double dotProduct(Vector2D vector2D){
        return (this.x * vector2D.x) + (this.y * vector2D.y);
    }
    protected double crossProduct(Vector2D vector2D){
        return (this.x * vector2D.y) - (this.y * vector2D.x);
    }
    
    protected static Point2D getCenter(Point2D p1, Point2D p2){
        return new Point2D((p1.x + p2.x) / 2 ,(p1.y + p2.y) / 2);
    }

    protected double getMagnituteSQ(){
        return (this.x * this.x) + (this.y * this.y);
    }

    protected double getMagnitute(){
        return Math.sqrt(this.getMagnituteSQ());
    }

    protected double getAngleWith(Vector2D vector2D){
        double angle = Math.acos(this.dotProduct(vector2D)/(this.getMagnitute()*vector2D.getMagnitute()));
        if (angle < 0)
            angle += Math.PI;
        if(this.crossProduct(vector2D) > 0)
            return angle;
        else
            return -angle;
    }

    public String toString(){
        return "Vector2D( " + this.x + " , " + this.y + " )";
    }


    protected Vector2D multiply(int i) {
        this.x *= i;
        this.y *= i;
        return this;
    }

    public static double getDistance(Point2D point2D1,Point2D point2D2,Point2D point2D3){
        Vector2D vector = new Vector2D(point2D2,point2D3);
        return Math.abs(point2D1.vector(point2D2).crossProduct(vector) / vector.getMagnitute());
    }
}
