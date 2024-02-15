package java3d;
import java.beans.Transient;

public class Point3D extends Point2D {


			protected double x3D;
			protected double y3D;
			protected double z3D;

	 		public Point3D() {
	        this(0, 0, 0);
	 		}

	   
	 		public Point3D(Point3D p) {
	        this(p.x3D, p.y3D, p.z3D);
	 		}

	   
	 		public Point3D(double x, double y,double z) {
		 	super(getX( x, y, z),getY( x, y, z));
	        this.x3D = x;
	        this.y3D = y;
	        this.z3D = z;
	 		}
	 
	 		public static double getX(double x,double y,double z) {

				 return  ((y - x) * Math.sin(Math.PI/3)) ;
	 		}
	 		public static double getY(double x,double y,double z) {

				 return  ((x + y) * Math.sin(Math.PI/6)) - z ;
	 		}
		
	 		public double getX3D() {
		        return x3D;
		    }
	 		
		    public double getY3D() {
		        return y3D;
		    }
		    
		    public double getZ3D() {
		        return z3D;
		    }
		    
		    @Transient
		    public Point3D getLocation3D() {
		        return new Point3D(x3D, y3D, z3D);
		    }


		    public Point3D setLocation(Point3D p) {
		       return move(p.x3D, p.y3D, p.z3D);
		    }


		    public Point3D setLocation(double x, double y, double z) {
		        return move(x, y, z);
		    }




		    public Point3D move(double x, double y, double z) {
		        this.x3D = x;
		        this.y3D = y;
		        this.z3D = z;
				super.move(getX( x3D, y3D, z3D),getY( x3D, y3D, z3D));
				return this;
		    }
			public Point3D move(Point3D p) {
				return setLocation(p);
			}


		    public Point3D translate(double dx, double dy, double dz) {
		        this.x3D += dx;
		        this.y3D += dy;
		        this.z3D += dz;
				super.move(getX( x3D, y3D, z3D),getY( x3D, y3D, z3D));
				return this;
		    }
			public Point3D translate(Vector3D v){
				return translate(v.x3D, v.y3D, v.z3D);
			}


		    public boolean equals(Point3D p) {
		            return (x3D == p.x3D) && (y3D == p.y3D) && (z3D == p.z3D);
		    }

			public Vector3D vector(Point3D p) {
		return new Vector3D(  p.x3D - this.x3D,  p.y3D - this.y3D,   p.z3D - this.z3D);
	}
			public Vector3D toVector(){
				 return new Vector3D(this);
			}

			public static boolean equals(Point3D p1, Point3D p2) {
				return (p1.x3D == p2.x3D) && (p1.y3D == p2.y3D) && (p1.z3D == p2.z3D);
			}
		    
		    public static double distanceSq(double x1, double y1, double z1,
                    double x2, double y2, double z2)
			{
			x1 -= x2;
			y1 -= y2;
			z1 -= z2;
			return (x1 * x1 + y1 * y1 + z1 * z1);
			}
		    
		    public static double distance(double x1, double y1, double z1,
                    double x2, double y2, double z2)
			{
			x1 -= x2;
			y1 -= y2;
			z1 -= z2;
			return Math.sqrt(x1 * x1 + y1 * y1 + z1 * z1);
			}
		    
		    public double distanceSq(double px, double py, double pz) {
		        px -= getX3D();
		        py -= getY3D();
		        pz -= getZ3D();
		        return (px * px + py * py + pz * pz);
		    }
		    
		    public double distanceSq(Point3D pt) {
		        double px = pt.getX3D() - this.getX3D();
		        double py = pt.getY3D() - this.getY3D();
		        double pz = pt.getZ3D() - this.getZ3D();
		        return (px * px + py * py + pz * pz);
		    }
		    
		    public double distance(double px, double py, double pz) {
		        px -= getX3D();
		        py -= getY3D();
		        pz -= getZ3D();
		        return Math.sqrt(px * px + py * py + pz * pz);
		    }

		    public double distance(Point3D pt) {
		        double px = pt.getX3D() - this.getX3D();
		        double py = pt.getY3D() - this.getY3D();
		        double pz = pt.getZ3D() - this.getZ3D();
		        return Math.sqrt(px * px + py * py+ pz * pz);
		    }

			public double[] coordinates(){
				 return new double[]{x3D, y3D, z3D};
			}

		    public Point3D rotateX(double angle) {

		    	double ty = (double) ((y3D * Math.cos(angle))  -  (z3D * Math.sin(angle)));
		    	double tz = (double) ((y3D * Math.sin(angle))  +  (z3D * Math.cos(angle)));
				this.y3D = ty;
				this.z3D = tz;
				super.move(getX( x3D, y3D, z3D),getY( x3D, y3D, z3D));
				return this;
		    }
			public Point3D rotateY(double angle) {
				double tx = (double) ((x3D * Math.cos(angle))  -  (z3D * Math.sin(angle)));
		    	double tz = (double) ((x3D * Math.sin(angle))  +  (z3D * Math.cos(angle)));
				this.x3D = tx;
				this.z3D = tz;
				super.move(getX( x3D, y3D, z3D),getY( x3D, y3D, z3D));
				return this;
			}
			public Point3D rotateZ(double angle) {
				double tx = (double) ((x3D * Math.cos(angle))  -  (y3D * Math.sin(angle)));
		    	double ty = (double) ((x3D * Math.sin(angle))  +  (y3D * Math.cos(angle)));
				this.x3D = tx;
				this.y3D = ty;
				super.move(getX( x3D, y3D, z3D),getY( x3D, y3D, z3D));
				return this;
			}
			
			public Point3D rotateX(double angle, Point3D p) {
				Vector3D v = p.vector(this);
				v.rotateX(angle);
				this.move(p.getCopy().translate(v));
				return this;
		    }
			public Point3D rotateY(double angle, Point3D p) {
				Vector3D v = p.vector(this);
				v.rotateY(angle);
				this.move(p.getCopy().translate(v));
				return this;
			}
			public Point3D rotateZ(double angle, Point3D p) {
				 Vector3D v = p.vector(this);
				 v.rotateZ(angle);
				 this.move(p.getCopy().translate(v));
				 return this;
			}
			public Point3D getCopy(){
				 return new Point3D(this);
			}


		    public String toString() {
		        return getClass().getName() + "[x=" + x3D + ",y=" + y3D + ",z=" + z3D + "]";
		    }

			public Point2D toPoint2D(){
				 return new Point2D(this.x,this.y);
			}

}
