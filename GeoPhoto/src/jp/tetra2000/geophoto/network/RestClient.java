package jp.tetra2000.geophoto.network;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import  	org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class RestClient
{
	private String mBaseUrl;
	
	public RestClient(String baseUrl)
	{
		mBaseUrl = baseUrl;
	}
	
	public String post(List<NameValuePair> params) throws ClientProtocolException, IOException
	{
		HttpPost request =
			new HttpPost(mBaseUrl);
        try {
            request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpClient httpClient = new DefaultHttpClient();
        String response = httpClient.execute(request, new ResponseHandler<String>() {
            @Override
            public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                switch (httpResponse.getStatusLine().getStatusCode()) {
                    case HttpStatus.SC_OK:
                        return EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
				default:
                        	return null;
                }
            }
        });
        
        return response;
    }
}
