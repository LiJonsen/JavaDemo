package GeometricTest;
/*
 * 實現equals方法重寫具備以下功能：
 * 1、 GeometricObject父類可以判斷自己屬性，但不能判斷子類
 * 2、 Circle或Rectangle子類可以判斷父類和自己的屬性
 */
public class Test {
	public static void main(String[] args) {
		
		Test test = new Test();
		Circle c1 = new Circle(3.3,"red",1);
		Circle c2 = new Circle(3.3,"red",1);
		test.getArea(c1);
		test.getArea(c2);
		boolean flag = test.isEquals(c1, c2);
		System.out.println(flag);
		
	
	}
	// 多态性-获取不同的几何图形面积
	public void getArea(GeometricObject geometric) {
		double area = geometric.findArea();
		System.out.println("当前"+geometric.getName()+"面积为："+area);
	}
	// 多态性-判断面积是否相等
	public boolean isEquals(GeometricObject g1,GeometricObject g2) {
		return g1.findArea() == g2.findArea();
	}
}
