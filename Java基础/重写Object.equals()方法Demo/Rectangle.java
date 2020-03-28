package GeometricTest;
/*
 * Rectangle - 矩形
 */
public class Rectangle extends GeometricObject{
	private double height;
	private double width;
	public Rectangle(double height,double width,String color, double weight) {
		super(color, weight);
		setName("矩形");
		this.height = height;
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	
	// 求矩形面积
	public double findArea() {
		return width * height;
	}
	
	// 矩形類equals方法重寫
		public boolean equals(Rectangle r) {
			// 校驗父類屬性
			if(!super.equals(r)) return false;
			// 對比該類中的屬性
			if(r instanceof Rectangle) {			
				return height == r.height && width == r.width;
			}
			return false;
		}
	
	
}
