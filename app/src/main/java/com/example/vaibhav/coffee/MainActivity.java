package com.example.vaibhav.coffee;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
StringBuilder results =new StringBuilder();
    public TextView txtview,n_name,p_price;
    public ArrayList name=new ArrayList<String>();
    public ArrayList price =new ArrayList<String>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        new background().execute();



    }

    class background extends AsyncTask<StringBuilder,StringBuilder,StringBuilder>{

Context context;


        @Override
        protected StringBuilder doInBackground(StringBuilder... params) {
            try {
                URL url =new URL("http://192.168.43.145/coffee_shop/test.php");
                HttpURLConnection conn=(HttpURLConnection)url.openConnection();

                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setRequestMethod("GET");

                InputStream is = conn.getInputStream();
                BufferedReader br =new BufferedReader(new InputStreamReader(is,"UTF-8"));

                String jsonstring=new String();

                while((jsonstring=br.readLine())!=null){
                    results.append(jsonstring+"\n");

                }
                JSONObject jsonresponse =new JSONObject(results.toString());
                JSONArray array =jsonresponse.optJSONArray("response");

                for(int i=0;i<array.length();i++){
                    String strJSONobj  =array.getString(i);
                    JSONObject json = new JSONObject(strJSONobj);
                    name.add(json.get("name"));
                    price.add(json.get("price"));
                    Log.d("result",json.getString("name"));
                    Log.d("result",json.getString("price"));
                }

                br.close();
                is.close();
                conn.disconnect();
                return results;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }


        public void onPostExecute(StringBuilder res){

            results = res;
            Log.d("res",results.toString());
//           txtview.setText(results);
            show();
        }

    }
    public void show()
    {
////        txtview=(TextView)findViewById(R.id.tb);
//        p_price=(TextView)findViewById(R.id.price);
//        n_name=(TextView)findViewById(R.id.name);
//        StringBuilder sb =new StringBuilder();
//        for(int i=0;i<name.size();i++){
//            sb.append(name.get(i) + "\n");
//        }
//        n_name.setText(sb.toString());



        MyAdapter adapter = new MyAdapter(name,price);
        recyclerView.setAdapter(adapter);


    }

}
