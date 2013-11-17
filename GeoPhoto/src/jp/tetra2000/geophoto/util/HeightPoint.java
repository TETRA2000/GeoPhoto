package jp.tetra2000.geophoto.util;

public class HeightPoint {
    public double latitude, longitude, height;
	
	public HeightPoint(double latitude, double longitude, double height) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.height = height;
	}
	
	@Override
	public boolean equals(Object o)
	{
		return	o.getClass() == getClass() ||
				((HeightPoint)o).latitude == latitude &&
				((HeightPoint)o).longitude == longitude &&
				((HeightPoint)o).height == height;
			
	}
	
	public double[] toDoubleArray() {
		double[] array = {this.latitude, this.longitude, this.height};
		return array;
	}
}
