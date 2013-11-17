package jp.tetra2000.geophoto.util;

import java.util.ArrayList;

public class PointsMap {
	private static final Double R = 6378.137;
	private static final Double BASE_INTERVAL = 360.0 / R;

	public Point[][] map;

	public PointsMap(int sideLen) {
		map = new Point[sideLen][sideLen];
	}

	/**
	 * 
	 * @param base
	 * @param angle
	 * @param sideLen
	 *            in KM
	 * @param resolution
	 */
	public static PointsMap create(Point base, double angle, double sideLen,
			int resolution) {
		PointsMap result = new PointsMap(resolution);

		final double X_ANGLE = angle - 45;
		final double Y_ANGLE = angle + 45;
		final double DIAGONAL = (sideLen / (double) resolution) * Math.sqrt(2);
		final double X_INTERVAL = BASE_INTERVAL * DIAGONAL
				* Math.toRadians(X_ANGLE);
		final double Y_INTERVAL = BASE_INTERVAL * DIAGONAL
				* Math.toRadians(Y_ANGLE);

		for (int i = 0; i < resolution; i++) {
			for (int j = 0; j < resolution; j++) {
				result.map[i][j] = new Point(base.latitude + i * X_INTERVAL,
						base.longitude + j * Y_INTERVAL);
			}
		}

		return result;
	}
	
	public Point[] toPointArray() {
		int len = map.length;
		
		Point[] result = new Point[len * len];
		
		for(int i=0; i<len; i++) {
			for(int j=0; j<len; j++) {
				result[i*len + j] = map[i][j];
			}
		}
		
		return result;
	}
}
