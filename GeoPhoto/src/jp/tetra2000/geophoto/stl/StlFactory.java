package jp.tetra2000.geophoto.stl;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import jp.tetra2000.geophoto.network.YOLPHeight;
import jp.tetra2000.geophoto.util.HeightPoint;
import jp.tetra2000.geophoto.util.HeightPointMap;
import jp.tetra2000.geophoto.util.Point;
import jp.tetra2000.geophoto.util.PointsMap;

public class StlFactory {
	public static ArrayList<Triangle> createTopSurface(HeightPointMap hpm) {
		HeightPoint[][] map = hpm.map;
		int len = map.length;

		ArrayList<Triangle> result = new ArrayList<Triangle>();

		// 端を残す
		for (int i = 0; i < len - 1; i++) {
			for (int j = 0; j < len - 1; j++) {
				HeightPoint a1 = map[i][j];
				HeightPoint b1 = map[i + 1][j + 1];
				HeightPoint c1 = map[i + 1][j];

				HeightPoint a2 = map[i][j];
				HeightPoint b2 = map[i][j + 1];
				HeightPoint c2 = map[i+1][j + 1];

				result.add(new Triangle(a1.toDoubleArray(), b1.toDoubleArray(),
						c1.toDoubleArray()));
				result.add(new Triangle(a2.toDoubleArray(), b2.toDoubleArray(),
						c2.toDoubleArray()));
			}
		}
		
		return result;
	}

	public static void main(String args) throws ClientProtocolException, IOException {
		YOLPHeight sut = new YOLPHeight();;
		Point base = new Point(139.732293,35.663613);
		PointsMap map = PointsMap.create(base, 30, 5, 10);
		
		ArrayList<HeightPoint> array = sut.getHeights(map.toPointArray());
		
		HeightPointMap heightPointMap = HeightPointMap.fromArray(array, 10);
		
//		/*
//		 * リフォーマット用
//		 */
//		{
//			HeightPoint first =  heightPointMap.map[0][0];
////			HeightPoint last = heightPointMap.map[10 - 1][10-1];
//			
//			for(HeightPoint row[] : heightPointMap.map) {
//				for(HeightPoint p : row) {
//					p.latitude-=first.latitude;
//					p.longitude-=first.longitude;
//				}
//			}
//		}
		
		
		ArrayList<Triangle> surface = StlFactory.createTopSurface(heightPointMap);
		String test = test(surface);
		
		System.out.println(test);
	}

	public static String test(ArrayList<Triangle> surface) {		
		StringBuilder builder = new StringBuilder();
		
		builder.append("solid ascii\n");
		
		for(Triangle t : surface) {
			// TODO 要チェック(法線ベクトル)
			builder.append("facet normal 0.000000e+000 0.000000e+000 0.000000e+000\n");
			builder.append("outer loop\n");
			
			builder.append(vertexLine(t.a));
			builder.append(vertexLine(t.b));
			builder.append(vertexLine(t.c));
			
			builder.append("endloop\n");
			builder.append("endfacet\n");
		}
		
		builder.append("endsolid");
		
		return builder.toString();
	}
	
	private static String vertexLine(double[] array) {
	    DecimalFormat df = new DecimalFormat("0.000000E000");
	    
		StringBuilder builder =  new StringBuilder();
		
		builder.append("vertex   ");
		builder.append(df.format(array[0]));
		builder.append(" ");
		builder.append(df.format(array[1]));
		builder.append(" ");
		builder.append(df.format(array[2] / 3000));
		builder.append("\n");
		
		return builder.toString();
	}
}
