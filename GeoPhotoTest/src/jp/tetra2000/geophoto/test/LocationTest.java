package jp.tetra2000.geophoto.test;

import jp.tetra2000.geophoto.util.Location;
import jp.tetra2000.geophoto.util.LocationCallback;
import android.test.InstrumentationTestCase;

public class LocationTest extends InstrumentationTestCase {
	private Location sut;
	private boolean updateFlag;
	private LocationCallback callback;
	
	@Override
	public void setUp() {
		sut = new Location(getInstrumentation().getTargetContext());
		updateFlag = false;
		callback = new LocationCallback() {
			@Override
			public void updated() {
				updateFlag = true;
			}
		};
	}
	
	public void testStart及びstopメソッドが実行出来る() {
		sut.start();
		sut.stop();
	}
	
	public void test位置が取得できる() throws InterruptedException {
		sut.setCallback(callback);
		
		sut.start();
		
		for(int i=0; i<100; i++) {
			if(updateFlag)
				break;
			
			Thread.sleep(1000);
		}
		
		assertTrue(updateFlag);
		assertFalse(sut.getLatitude() == Double.MAX_VALUE);
		assertFalse(sut.getLongitude() == Double.MAX_VALUE);
	}
}
