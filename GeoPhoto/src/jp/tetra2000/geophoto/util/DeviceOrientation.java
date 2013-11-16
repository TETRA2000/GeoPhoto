package jp.tetra2000.geophoto.util;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class DeviceOrientation {
	SensorManager mManager;
	private Sensor mAccelerometer;
	private Sensor mMagneticField;

	public float[] valueAccelerometer, valueMagneticField;

	public DeviceOrientation(Context context) {
		mManager = (SensorManager) context
				.getSystemService(Context.SENSOR_SERVICE);

		mAccelerometer = mManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mMagneticField = mManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
	}

	public void resume() {
		mManager.registerListener(listener, mAccelerometer,
				SensorManager.SENSOR_DELAY_NORMAL);
		mManager.registerListener(listener, mMagneticField,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	public void pause() {
		mManager.unregisterListener(listener, mAccelerometer);
		mManager.unregisterListener(listener, mMagneticField);
	}

	private SensorEventListener listener = new SensorEventListener() {

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {

		}

		@Override
		public void onSensorChanged(SensorEvent event) {
			switch (event.sensor.getType()) {
			case Sensor.TYPE_ACCELEROMETER:
				valueAccelerometer = event.values;
				break;
			case Sensor.TYPE_MAGNETIC_FIELD:
				valueMagneticField = event.values;
				break;
			}
		}

	};
}
