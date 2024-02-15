package java3d;

import java.beans.Transient;

class Point2D {
    double x;
    double y;
    protected Point2D(double x,double y){
        this.x = x;
        this.y = y;
    }

    protected Point2D(Point2D point2D) {
        this(point2D.x, point2D.y);
    }


    protected Point2D move(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    protected Vector2D toVector2D(){
        return new Vector2D(this);
    }
    
    
    protected double getX() {
        return x;
    }

    protected double getY() {
        return y;
    }
    

    @Transient
    protected Point2D getLocation() {
        return new Point2D(x, y);
    }


    protected Point2D setLocation(Point2D p) {
        return move(p.x, p.y);
    }


    protected Point2D setLocation(double x, double y, double z) {
        return move(x, y);
    }

    
    protected Point2D move(Point2D p) {
        return setLocation(p);
    }


    protected Point2D translate(Vector2D v){
        return translate(v.x, v.y);
    }

    private Point2D translate(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }


    protected boolean equals(Point2D p) {
        return (x == p.x) && (y == p.y);
    }

    protected Vector2D vector(Point2D p) {
        return new Vector2D(  p.x - this.x,  p.y - this.y);
    }

    protected Vector2D _toVector(){
        return new Vector2D(this);
    }



    protected double distanceSq(double px, double py) {
        px -= getX();
        py -= getY();
        return (px * px + py * py);
    }

    protected double distanceSq(Point2D pt) {
        return distanceSq(pt.x, pt.y);
    }

    protected double distance(double px, double py) {
        return Math.sqrt(distanceSq(px, py));
    }

    protected double distance(Point2D pt) {
        return Math.sqrt(distanceSq(pt.x, pt.y));
    }

    protected double[] coordinates(){
        return new double[]{x, y};
    }
    protected Point2D rotateZ(double angle) {
        double tx = (double) ((x * Math.cos(angle))  -  (y * Math.sin(angle)));
        double ty = (double) ((x * Math.sin(angle))  +  (y * Math.cos(angle)));
        this.x = tx;
        this.y = ty;
        return this;
    }


    protected Point2D rotateZ(double angle, Point2D p) {
        if (this.equals(p)) return p;
        this.setLocation(vector(p));
        this.rotateZ(angle);
        this.translate(p.x, p.y);
        return this;
    }



    protected Point2D getCopy(){
        return new Point2D(this);
    }
    protected static Point2D getCenter(Point2D p1, Point2D p2){
        return new Point2D((p1.x + p2.x) /2, (p1.y + p2.y) /2);
    }


    public String toString() {
        return getClass().getName() + "[x=" + x + ",y=" + y + "]";
    }


}

