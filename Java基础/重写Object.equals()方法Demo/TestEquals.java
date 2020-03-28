package GeometricTest;
// 測試equals方法重寫
public class TestEquals {
	public static void main(String[] args) {
		System.out.println("*****************父類GeometricObject*******************");
		// 1.測試父類GeometricObject equals方法重寫
		GeometricObject g1 = new GeometricObject("red", 1);
		GeometricObject g2 = new GeometricObject("red", 1);
	
		System.out.println("g1地址值："+g1);
		System.out.println("g2地址值："+g2);
		System.out.println("調用equals方法結果："+g1.equals(g2));
		System.out.println("******************子類Circle******************");
		// 2.測試子類Circle圓形 equals方法重寫
		Circle c1 = new Circle(2.2,"blue",2);
		Circle c2 = new Circle(2.2,"blue",2);
		
		System.out.println("c1地址值："+c1);
		System.out.println("c2地址值："+c2);
		System.out.println("調用equals方法結果："+c1.equals(c2));
		System.out.println("****************子類Rectangle********************");
		// 3.測試子類Rectangle矩形 equals方法重寫
		Rectangle r1 = new Rectangle(2.2,3.3,"blue",2);
		Rectangle r2 = new Rectangle(2.2,2.3,"blue",2);
		
		System.out.println("r1地址值："+r1);
		System.out.println("r2地址值："+r2);
		System.out.println("調用equals方法結果："+r1.equals(r2));
	}
}
