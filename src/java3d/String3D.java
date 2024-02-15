package java3d;

import java.awt.*;

public class String3D extends Char3D{
    protected String string;
    protected Char3D[] chars;
    public String3D(String str, Point3D point, Vector3D vector,Font font) {
        super(str.charAt(0), point, vector,font);
        this.string = str;
        setChars(str);
    }

    public String3D(String str,Point3D point, Vector3D vector){
        this(str, point, vector, new Font("Arial",Font.PLAIN,20));
    }

    public String3D(String str,Point3D point){
        this(str, point, new Vector3D(0,1,0), new Font("Arial",Font.PLAIN,20));
    }

    public String3D(String str){
        this(str, new Point3D(), new Vector3D(0,1,0), new Font("Arial",Font.PLAIN,20));
    }
    public String3D setFont(Font font){
        for (Char3D char3D : chars)
            char3D.setFont(font);
        return this;
    }

    public String3D setVector(Vector3D vector){
        this.vector = vector.getCopy();
        for (Char3D char3D : chars)
           char3D.setVector(vector);
        return this;
    }

    public String3D setString(String str){
        this.string = str;
        setChars(str);
        return this;
    }

    public String3D setPoint(Point3D point){
        this.point = point.getCopy();
        return this;
    }

    public String getString(){
        return this.string;
    }

    public Point3D getPoint(){
        return this.point;
    }
    private void setChars(String s){
        this.chars = new Char3D[s.length()];
        for (int i = 0; i<s.length();i++)
            chars[i] = new Char3D(s.charAt(i));
    }
}
