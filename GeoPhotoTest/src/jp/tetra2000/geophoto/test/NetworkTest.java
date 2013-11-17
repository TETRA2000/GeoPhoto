package jp.tetra2000.geophoto.test;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import jp.tetra2000.geophoto.network.YOLPHeight;
import jp.tetra2000.geophoto.util.HeightPoint;
import jp.tetra2000.geophoto.util.HeightPointMap;
import jp.tetra2000.geophoto.util.Point;
import jp.tetra2000.geophoto.util.PointsMap;
import android.test.AndroidTestCase;

public class NetworkTest extends AndroidTestCase {
	private YOLPHeight sut;
	
	@Override
	public void setUp() {
		sut = new YOLPHeight();
	}
	
	public void testGetHeightsで高さが取得できる() throws ClientProtocolException, IOException {
		Point[] points = new Point[2];
		points[0] = new Point(139.732293,35.663613);
		points[1] = new Point(139.6847782,35.6265008);
		
		ArrayList<HeightPoint> actual = sut.getHeights(points);
		
		ArrayList<HeightPoint> expected = new ArrayList<HeightPoint>(2);
		expected.add(new HeightPoint(139.732293, 35.663613, 30));
		expected.add(new HeightPoint(139.6847782,35.6265008, 30.3));
		
		assertEquals(actual, expected);
	}
	
	public void testGetHeightsで高さが取得できるその2() throws ClientProtocolException, IOException {
		Point base = new Point(139.732293,35.663613);
		PointsMap map = PointsMap.create(base, 30, 5, 10);
		
		ArrayList<HeightPoint> actual = sut.getHeights(map.toPointArray());

		
		assertEquals(actual.size(), 10*10);
	}
	
	public void testHeightPointMapに変換() throws ClientProtocolException, IOException {
		Point base = new Point(139.732293,35.663613);
		PointsMap map = PointsMap.create(base, 30, 5, 10);
		
		ArrayList<HeightPoint> array = sut.getHeights(map.toPointArray());
		
		HeightPointMap heightPointMap = HeightPointMap.fromArray(array, 10);
		
		assertEquals(heightPointMap.map.length, 10);
	}
}
