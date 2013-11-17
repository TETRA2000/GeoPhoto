package jp.tetra2000.geophoto.test;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import jp.tetra2000.geophoto.network.YOLPHeight;
import jp.tetra2000.geophoto.stl.StlFactory;
import jp.tetra2000.geophoto.stl.Triangle;
import jp.tetra2000.geophoto.util.HeightPoint;
import jp.tetra2000.geophoto.util.HeightPointMap;
import jp.tetra2000.geophoto.util.Point;
import jp.tetra2000.geophoto.util.PointsMap;
import android.test.AndroidTestCase;

public class StlFactoryTest extends AndroidTestCase {
	public void testテスト() throws ClientProtocolException, IOException {
		YOLPHeight sut = new YOLPHeight();;
		Point base = new Point(138.7159331,35.3625072);
//		Point base = new Point(139.7788842,35.6296699);
		PointsMap map = PointsMap.create(base, 0, 50, 10);
		
		ArrayList<HeightPoint> array = sut.getHeights(map.toPointArray());
		
		HeightPointMap heightPointMap = HeightPointMap.fromArray(array, 10);
		
		ArrayList<Triangle> surface = StlFactory.createTopSurface(heightPointMap);
		String test = StlFactory.test(surface);
		
		System.out.println(test);
	}
}
