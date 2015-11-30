package hu.ait.android.weatherapp.data;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by kdonahoe on 11/26/15.
 */
public class HttpAsyncTask extends AsyncTask<String, Void, String> {

    public static final String FILTER_HTTP_RESULT = "FILTER_HTTP_RESULT";
    public static final String KEY_EXCHANGE_RESULT = "KEY_EXCHANGE_RESULT";
    private Context context;

    public HttpAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String result = "";

        HttpURLConnection conn = null;
        InputStream is = null;

        try {
            URL url = new URL(params[0]);

            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(1000);

            is = conn.getInputStream();

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int ch;

            while ((ch = is.read()) != -1) {
                bos.write(ch);
            }

            result = new String(bos.toByteArray());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }

        return result;
    }


    @Override
    protected void onPostExecute(String result){
        Intent intentResult = new Intent(FILTER_HTTP_RESULT);
        intentResult.putExtra(KEY_EXCHANGE_RESULT, result);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intentResult);
    }



}

