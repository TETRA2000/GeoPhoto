package jp.tetra2000.geophoto.network;
import android.util.Xml;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.lang.annotation.Documented;
import java.util.ArrayList;

import jp.tetra2000.geophoto.util.*;

public class YOLPHeight
{
	private static final String BASE_URL
		= "http://alt.search.olp.yahooapis.jp/OpenLocalPlatform/V1/getAltitude";
    private static final String APP_ID = "";
	
	private RestClient mClient;
	
	public YOLPHeight() {
		mClient = new RestClient(BASE_URL);
	}
	
	public ArrayList<HeightPoint> getHeights(Point[] points) throws ClientProtocolException, IOException
	{
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>(2);

        StringBuilder builder = new StringBuilder();
        builder.append(points[0].latitude);
        builder.append(",");
        builder.append(points[0].longitude);
        
        int len = points.length;
        for(int i=1; i<len; i++) {
            builder.append(",");
            builder.append(points[i].latitude);
            builder.append(",");
            builder.append(points[i].longitude);
        }

        params.add(new BasicNameValuePair("appid", APP_ID));
        params.add(new BasicNameValuePair("coordinates", builder.toString()));

        String response =  mClient.post(params);

        return parseXml(response);
	}

    private ArrayList<HeightPoint> parseXml(String source)  {
        ArrayList<HeightPoint> result = new ArrayList<HeightPoint>();

        XmlPullParser xpp = Xml.newPullParser();
        try {
            xpp.setInput(new StringReader(source));

            int eventType = xpp.getEventType();
            Point buffer = null;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if(eventType == XmlPullParser.START_TAG) {
                    if(xpp.getName().equals("Coordinates")) {
                        String[] values = xpp.nextText().split(",");
                        double latitude = Double.parseDouble(values[0]);
                        double longitude = Double.parseDouble(values[1]);
                        buffer = new Point(latitude, longitude);
                    } else if(xpp.getName().equals("Altitude")) {
                        double height = Double.parseDouble(xpp.nextText());

                        HeightPoint heightPoint =
                                new HeightPoint(buffer.latitude, buffer.longitude, height);
                        result.add(heightPoint);
                    }
                }
                eventType = xpp.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }
}
