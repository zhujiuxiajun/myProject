package kyodai.map;

import java.awt.Point;

/**
 * ����ֱ�ߵ���
*/
public class Line{

	public Point a;
	public Point b;
	public int direct;

	public Line(){
	}

	/**
	 * ͨ������ͷ�����ֱ��
	*/
	public Line(int direct, Point a, Point b){
		this.direct = direct;
		this.a = a;
		this.b = b;
	}
}
