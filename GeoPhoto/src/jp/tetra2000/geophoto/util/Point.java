package jp.tetra2000.geophoto.util;

public class Point
{
    public double latitude, longitude;

	public Point(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	@Override
	public boolean equals(Object o) {
		return	this == o ||
				o.getClass() == getClass() &&
				((HeightPoint)o).latitude == latitude &&
				((HeightPoint)o).longitude == longitude;
			
	}
}
