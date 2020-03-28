package GeometricTest;
/* 
 * GeometricObject - 几何对象类
 * Geometric：几何  
 */
public class GeometricObject {
	protected String color; // 颜色
	private String name; //几何图形名称
	protected double weight; // 权重
	public GeometricObject(String color, double weight) {
		super();
		this.color = color;
		this.weight = weight;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	} 
	// 求几何图形面积
	public double findArea() {
		return 0.0;
	}
	
	// 幾何類equals方法重写-判断对象中的属性是否相等
	public boolean equals(GeometricObject obj) {
		// 1.地址相等直接返回true
		if(this == obj) return true;
		// 2.obj是否是GeometricObject的實例
		if(obj instanceof GeometricObject) {
			GeometricObject geo = (GeometricObject)obj;
			// 3.判斷對應具體屬性值
			boolean name_status;
			boolean color_status;
			// 3.1 判斷名字
			name_status = (name==null && geo.name == null) || name.equals(geo.name);
			// 3.2 判斷顏色
			color_status = (color==null && geo.color == null) || color.equals(geo.color);
			return name_status && 
					color_status &&
					this.weight == geo.weight;
		}
		return false;
	}
}
