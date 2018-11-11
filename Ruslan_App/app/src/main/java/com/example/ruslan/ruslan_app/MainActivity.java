package com.example.ruslan.ruslan_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());



    }
    public void buttonOnClick(View v){
        Button button=(Button) v;
        ((Button) v).setText("clicked");


        //new URL("http://users.bugred.ru/tasks/rest/doregister?email=test_video_1@mail.xom&name=mahadeva1008&password=1").openStream();
/*
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL("http://users.bugred.ru/tasks/rest/doregister?email=test_video_3@mail.xom&name=mahadeva3&password=1");

            urlConnection = (HttpURLConnection) url
                    .openConnection();

            InputStream in = urlConnection.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);

            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                System.out.print(current);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
*/
        String /*jsonText*/ data = "{\"email\": \"test_video_3@mail.com\", \"name\": \"mahadeva3\", \"password\": \"1\" }" ; //data to post;
        try {
        JSONObject obj = new JSONObject();

        obj.put("email","test_video_3@mail.com");
        obj.put("name","mahadeva3");
        obj.put("password","1");
//        obj.put("name",new Integer(100));
//        obj.put("balance",new Double(1000.21));
//        obj.put("is_vip",new Boolean(true));

         /*jsonText*/ data = obj.toString();
        System.out.print(/*jsonText*/data);

    } catch (JSONException e) {
        //some exception handler code.
    }
    //StringWriter out = new StringWriter();
        //obj. .writeJSONString(out);




        String urlString = "http://users.bugred.ru/tasks/rest/doregister"; // URL to call
        //String data = "{\"email\": \"test_video_3@mail.com\", \"name\": \"mahadeva3\", \"password\": \"1\" }" ; //data to post
        OutputStream out = null;

        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            out = new BufferedOutputStream(urlConnection.getOutputStream());

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(data);
            writer.flush();
            writer.close();
            out.close();

            urlConnection.connect();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
