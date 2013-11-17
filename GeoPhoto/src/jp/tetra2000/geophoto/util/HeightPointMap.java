package jp.tetra2000.geophoto.util;

public class HeightPointMap {
	public HeightPoint[][] map;
	
	public HeightPointMap(int resolution) {
		map = new HeightPoint[resolution][resolution];
	}
	
	public static HeightPointMap fromArray(HeightPoint[] array, int resolution) {
		HeightPointMap result = new HeightPointMap(resolution);

		HeightPoint[][] map = result.map;

		for (int i = 0; i < resolution; i++) {
			for (int j = 0; j < resolution; j++) {
				map[i][j] = array[i * resolution + j];
			}
		}

		return result;
	}
}
