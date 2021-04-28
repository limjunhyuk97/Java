package comparable_vs_comparator;

import java.util.Objects;

public class comparablePoint implements Comparable<comparablePoint>, Cloneable{
	
	private String name;
	private int x;
	private int y;
	private StringBuilder alias;
	
	public comparablePoint(int x, int y, StringBuilder alias, String name) {
		this.x = x; this.y = y; this.name = name; this.alias = alias;
	}
	
	public void setAlias(String appended) {
		this.alias.append(appended);
	}
	
	public int getX() { return this.x; }
	
	public int getY() { return this.y; }
	
	// java.lang.Object -> .equals(Object object)
	// java.util.Objects -> .equals(Object object1, Object object2)
	// equals 재정의
	public boolean equals(Object object) {
		if(object == null) return false;
		if(this == object) return true;
		if(this.getClass() != object.getClass()) return false;
		
		comparablePoint point = (comparablePoint) object;
		
		// Objects의 equals 메소드로 두 객체 field 같은지 비교
		return (Objects.equals(this.name, point.name) && this.x == point.x && this.y == point.y && Objects.equals(this.alias, point.alias));
	}
	
	// hashCode 재정의
	public int hashCode() {
		return Objects.hash(name, x, y, alias);
	}
	
	// toString 재정의
	public String toString() {
		return "Point " + this.name + " : ( " + this.x + ", " + this.y + ", " + this.alias + " )";
	}
	
	// Comparable method의 재정의
	public int compareTo(comparablePoint point) {
		if( x!= point.x )
			return x - point.x;
		else
			return y - point.y;
	}
	
	// clone() 메소드 재정의
	public comparablePoint clone() throws CloneNotSupportedException {
		comparablePoint retPoint = (comparablePoint) super.clone();
		retPoint.alias = new StringBuilder(this.name);
		
		return retPoint;
	}
		
}
