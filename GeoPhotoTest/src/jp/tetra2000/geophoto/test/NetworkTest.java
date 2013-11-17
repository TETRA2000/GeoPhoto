package jp.tetra2000.geophoto.test;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import jp.tetra2000.geophoto.network.YOLPHeight;
import jp.tetra2000.geophoto.util.HeightPoint;
import jp.tetra2000.geophoto.util.Point;
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
}
