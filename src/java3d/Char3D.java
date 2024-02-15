package java3d;

import java.awt.*;

public class Char3D {
    protected char character;
    protected Vector3D vector;

    protected Point3D point;

    protected Font font ;

    protected Paint paint;
    public Char3D(char c,Point3D point, Vector3D vector,Font font,Paint paint){
        this.character = c;
        this.point = point.getCopy();
        this.vector = vector.getCopy();
        this.font = font;
        this.paint = paint;
    }
    public Char3D(char c,Point3D point, Vector3D vector,Font font){
        this(c, point, vector,font,Color.black);
    }

    public Char3D(char c,Point3D point, Vector3D vector){
        this(c, point, vector, new Font("Arial",Font.PLAIN,20),Color.black);
   }

    public Char3D(char c,Point3D point) {
        this(c, point, new Vector3D(0, 1, 0), new Font("Arial", Font.PLAIN, 20), Color.black);
    }
    public Char3D(char c){
        this(c, new Point3D(), new Vector3D(0,1,0), new Font("Arial",Font.PLAIN,20),Color.black);
    }
    public Char3D setFont(Font font){
        this.font = font;
        return this;
    }
    public Char3D setVector(Vector3D vector){
        this.vector = vector.getCopy();
        return this;
    }

    public Char3D setChar(char c){
        this.character = c;
        return this;
    }

    public Char3D setPoint(Point3D point){
        this.point = point.getCopy();
        return this;
    }

    public Font getFont(){
        return this.font;
    }

    public Vector3D getVector(){
        return this.vector;
    }

    public char getChar(){
        return this.character;
    }

    public Point3D getPoint(){
        return this.point;
    }


}
