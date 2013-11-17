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
}
