package java3d;

import java.awt.*;

public   class Graphics3D  {
	protected Graphics2D graphics2d;

	protected GeneralPath3D generalPath3D;

	private boolean useShapeStoke;

	private boolean useShapeLinePaint;

	private boolean useShapFillPaint;

	private  Stroke stroke;
	private  Paint linePaint;
	private Paint fillPaint;

	private Font font;

	private boolean useDefaultFont = true;

	public Graphics3D(Graphics graphics) {
		this.graphics2d = (Graphics2D) graphics;
		this.stroke = graphics2d.getStroke();
		this.linePaint = graphics2d.getPaint();
		font = graphics2d.getFont();
		useShapeStoke = true;
		useShapeLinePaint = true;
		useShapFillPaint = true;
	}


	public void drawLine3D(Line3D line) {
		if (!line.drawChoice)
			return;
		setProperities(line);
		graphics2d.drawLine((int) line.getFirstPoint().x, (int) line.getFirstPoint().y,
				(int) line.getSecondPoint().x, (int) line.getSecondPoint().y);
	}

	public void drawShape(Point3D[][] points) throws PointsLessThanThreeException {
		drawShape(new Shape3D(points));
	}

	public void drawShape(double...arr) throws PointsLessThanThreeException {
		drawShape(new Shape3D(arr));
	}

	public void drawShape(double[]...arr) throws PointsLessThanThreeException {
		drawShape(new Shape3D(arr));
	}

	public void fillShape(Point3D[][] points) throws PointsLessThanThreeException {
		fillShape(new Shape3D(points));
	}

	public void fillShape(double...arr) throws PointsLessThanThreeException {
		fillShape(new Shape3D(arr));
	}

	public void fillShape(double[]...arr) throws PointsLessThanThreeException {
		fillShape(new Shape3D(arr));
	}

	public void fillShape(Shape3D shape3D) {
		if (!shape3D.fillChoice)
			return;
		for (Plane plane : shape3D.planes)
			fillPlane(plane);
	}

	public void drawShape(Point3D[] points){
		setDefaults();
		generalPath3D = new GeneralPath3D();
		generalPath3D.draw(points);
		graphics2d.draw(generalPath3D.getGeneralPath());
	}

	public void fillShape(Point3D[] points){
		setDefaults();
		generalPath3D = new GeneralPath3D();
		generalPath3D.draw(points);
		graphics2d.fill(generalPath3D.getGeneralPath());
	}

	public void drawRec(Rectangle3D rectangle3D) {
		if (!rectangle3D.drawChoice)
			return;
		for(byte i = 0;i<6; i++) {
			drawPlane(rectangle3D.planes[i]);
		}
	}

	public void fillRec(Rectangle3D rectangle3D){
		if (!rectangle3D.fillChoice)
			return;
		for(byte i = 0;i < 6; i++)
			fillPlane(rectangle3D.planes[i]);
	}



	public void drawPlane(Plane plane) {
		if (!plane.drawChoice)
			return;
		generalPath3D = new GeneralPath3D();
		generalPath3D.draw(plane);
		setProperities(plane);
		graphics2d.draw(generalPath3D.getGeneralPath());
	}

	public void fillPlane(Plane plane){
		if (!plane.fillChoice)
			return;
		generalPath3D = new GeneralPath3D();
		generalPath3D.draw(plane);
		setProperities(plane);
		setFillPaint(plane);
		graphics2d.fill(generalPath3D.getGeneralPath());
	}

	public void drawVector(Vector3D v1) {
		drawLine3D(new Line3D(0,0,0,v1.x3D, v1.y3D, v1.z3D));
	}

	public void drawShape(Shape3D shape3D) {
		if (!shape3D.drawChoice)
			return;
		setProperities(shape3D);
		generalPath3D = new GeneralPath3D();
		generalPath3D.draw(shape3D.points);
		graphics2d.draw(generalPath3D.getGeneralPath());
	}

	public void drawOval(Oval3D oval){
		if (!oval.drawChoice)
			return;
		setProperities(oval);
		drawOval(oval.point2DS,oval.angles,oval.radius_s);
		setDefaults();
	}



	public void drawPoint(Point3D p){
		setDefaults();
		graphics2d.fillOval((int) p.x - 5, (int) p.y - 5, 10,10);
	}

	public void drawCircularString(String str,Point3D point3D, int radius){
		char[] chars = new char[str.length()];
		for (byte i = 0; i < str.length(); i++)
			chars[i] = str.charAt(i);
		drawCircularChars(chars,point3D, radius);
	}
	public void drawChar(Char3D char3D){
		graphics2d.translate(char3D.point.x,char3D.point.y);
		graphics2d.rotate(char3D.vector.toVector2D().getAngleWithPosX());
		graphics2d.drawString(char3D.character + "",0,0);
		graphics2d.rotate(-char3D.vector.toVector2D().getAngleWithPosX());
		graphics2d.translate(-char3D.point.x,-char3D.point.y);
	}
	public void drawString(String3D str){
		graphics2d.setPaint(str.paint);
		graphics2d.setFont(str.font);
		graphics2d.translate(str.point.x,str.point.y);
		graphics2d.rotate(str.vector.toVector2D().getAngleWithPosX());
		graphics2d.drawString(str.string,0,0);
		graphics2d.rotate(-str.vector.toVector2D().getAngleWithPosX());
		graphics2d.translate(-str.point.x,-str.point.y);
	}
	public void drawString(String str){
		drawString(new String3D(str));
	}

	public void drawCircularString(String3D string,Point3D point3D, int radius){
		graphics2d.setFont(string.font);
		graphics2d.setPaint(string.paint);
		String s = string.string;
		char[] chars = new char[s.length()];
		for (byte i = 0; i < s.length(); i++)
			chars[i] = s.charAt(i);
		useDefaultFont = false;
		drawCircularChars(chars,string.point, radius);
		useDefaultFont = true;
	}
	public void drawCircularChars(char[] chars,Point3D point,int radius) {
		if (useDefaultFont)
			setDefaults();
		double angle = Math.PI * 2 / chars.length;
		graphics2d.rotate(- Math.PI / 2);
		graphics2d.translate(point.x,point.y);
		for (char char_ : chars) {
			graphics2d.translate(radius,0);
			graphics2d.drawString("" + char_,0,0);
			graphics2d.translate(-radius,0);
			graphics2d.rotate(angle);
		}
		graphics2d.translate(-point.x,-point.y);
		graphics2d.rotate(Math.PI / 2);
	}

	public void drawAxis(){
		drawLine3D((Line3D) new Line3D(-1000,0,0,1000,0,0).setLinePaint(Color.white));
		drawLine3D((Line3D) new Line3D(0,-1000,0,0,1000,0).setLinePaint(Color.white));
		drawLine3D((Line3D) new Line3D(0,0,-1000,0,0,1000).setLinePaint(Color.white));
	}

	public void setScale(double dx, double dy, double dz){
		graphics2d.scale(Point3D.getX(dx,dy,dz),Point3D.getY(dx,dy,dz));
	}

	public void setBackground(Color color){
		graphics2d.setBackground(color);
	}

	public void setUseShapeLinePaint(boolean value){
		this.useShapeLinePaint = value;
	}

	public void setUseShapeStoke(boolean value){
		this.useShapeStoke = value;
	}

	public void setUseShapeFillPaint(boolean value){
		this.useShapFillPaint = value;
	}

	public void setStroke(Stroke stroke){
		graphics2d.setStroke(stroke);
		this.stroke = stroke;
	}

	public void setLinePaint(Paint paint){
		graphics2d.setPaint(paint);
		this.linePaint = paint;
	}

	public void setFillPaint(Paint paint){
		graphics2d.setPaint(paint);
		this.fillPaint = paint;
	}

	public void setFont(Font font){
		graphics2d.setFont(font);
		this.font = font;
	}

	public Stroke getStroke(){
		return this.stroke;
	}

	public Paint getLinePaint(){
		return this.linePaint;
	}

	public Paint getFillPaint(){
		return this.fillPaint;
	}

	public void translate(double x, double y, double z){
		graphics2d.translate(Point3D.getX(x,y,z),Point3D.getY(x,y,z));
	}

	public void translate2D(double x, double y) {
		graphics2d.translate(x, y);
	}

	private void setProperities(java3d.Shape shape){
		if(useShapeStoke)
			graphics2d.setStroke(shape.stroke);
		if (useShapeLinePaint)
			graphics2d.setPaint(shape.linePaint);
	}
	private void setDefaults(){
		graphics2d.setStroke(stroke);
		graphics2d.setPaint(linePaint);
		graphics2d.setFont(font);
	}

	private void setFillPaint(java3d.Shape shape){
		if(useShapFillPaint)
			graphics2d.setPaint(shape.getFillPaint());
	}
	private void drawOval(Point2D[] point2DS,Double[] angles,Double[] radius){
		byte i = 0,j = 0;
		while (i<10){
			graphics2d.translate(point2DS[i].x - radius[i/3],point2DS[i].y - radius[i/3]);
			graphics2d.drawArc(0,0,radius[i/3].intValue() * 2,radius[i/3].intValue()* 2,(int) -Math.toDegrees(angles[j++]),(int) -Math.toDegrees(angles[j++]));
			graphics2d.translate(-point2DS[i].x + radius[i/3],-point2DS[i].y + radius[i/3]);
			i +=3;
		}
	}



}
