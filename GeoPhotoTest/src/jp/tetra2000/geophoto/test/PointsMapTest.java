package jp.tetra2000.geophoto.test;

import jp.tetra2000.geophoto.util.Point;
import jp.tetra2000.geophoto.util.PointsMap;
import android.test.AndroidTestCase;

public class PointsMapTest extends AndroidTestCase {
	
	
	@Override
	public void setUp() {
		
	}
	
	public void testその1() {
		Point base = new Point(139.732293,35.663613);
		PointsMap map = PointsMap.create(base, 30, 5, 2);
		
		assertTrue(true);
		
		fail();
	}
	
//	public void testArrayからのCreate() {
//		Point p1 = new Point(1,2);
//		Point p2 = new Point(3,4);
//		Point p3 = new Point(5,6);
//		Point p4 = new Point(7,8);
//		
//		Point[][] expected = new PointsMap(2).map;
//		expected[0][0] = p1;
//		expected[0][1] = p2;
//		expected[1][0] = p3;
//		expected[1][1] = p4;
//		
//		Point[] array = {p1, p2, p3, p4};
//		
//		PointsMap actual = PointsMap.create(array, 2);
//		
//		assertEquals(expected, actual.map);
//	}
}
