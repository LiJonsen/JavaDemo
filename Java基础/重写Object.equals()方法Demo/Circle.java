package GeometricTest;
/*
 * Circle - 圆
 */
public class Circle extends GeometricObject{
	private double radius; // 半径
	public Circle(double radius, String color, double weight) {
		super(color, weight);
		setName("圆形");
		this.radius = radius;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	// 方法重写-计算圆面积
	public double findArea() {
		return Math.PI * radius * radius;
	}
	
	// 圓形類equals方法重寫
	public boolean equals(Circle c) {
		// 校驗父類屬性
		if(!super.equals(c)) return false;
		
		if(c instanceof Circle) {			
			return radius == c.radius;
		}
		return false;
	}
}
