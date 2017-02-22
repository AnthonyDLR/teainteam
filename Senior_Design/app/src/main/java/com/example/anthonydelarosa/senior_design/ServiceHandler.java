package com.example.anthonydelarosa.senior_design;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ServiceHandler {

    StringBuilder sb = new StringBuilder();
    String http = "http://10.0.2.2 /";

    public ServiceHandler() {
    }


    public void connect(String tea, String strength)
    {
        try
        {

            HttpURLConnection connection = null;

            URL url = new URL(http);
            connection = (HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setConnectTimeout(720000000);
            connection.setReadTimeout(72000000);
            System.out.println("GETS HERE 1");
            connection.connect();
            System.out.println("GETS HERE 2");
            //Create JSONObject here
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("Tea", tea);
            jsonParam.put("Strength", strength);
            jsonParam.put("Date", "true");
            OutputStreamWriter out = new   OutputStreamWriter(connection.getOutputStream());
            out.write(jsonParam.toString());
            out.close();

            int HttpResult =connection.getResponseCode();
            if(HttpResult ==HttpURLConnection.HTTP_OK){
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        connection.getInputStream(),"utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();

                System.out.println(""+sb.toString());

            }
            else
            {
                System.out.println(connection.getResponseMessage());
            }
        }
        catch (MalformedURLException e)
        {

            e.printStackTrace();
        }
        catch (IOException e)
        {

            e.printStackTrace();
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            //if(urlConnection!=null)
              //urlConnection.disconnect();
        }
    }

}