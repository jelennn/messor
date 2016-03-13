package com.example.szymon.myapplication;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    String clear_text = "";
    String connected = "Connected";
    String disconnected = "Disconnected";
    TextView ip_adr;
    TextView port_adr;
    Button ip_button;
    Button port_button;
    Button connect_button;
    TextView connect_status;
    String ip_adres;
    String port_adres;
    TextView response_set;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ip_adr = (TextView) findViewById(R.id.ip_adr);
        port_adr = (TextView) findViewById(R.id.port_adr);
        ip_button = (Button) findViewById(R.id.ip_btn);
        port_button = (Button) findViewById(R.id.port_btn);
        connect_button = (Button) findViewById(R.id.connect_btn);
        connect_status = (TextView) findViewById(R.id.status_bar);
        response_set = (TextView) findViewById(R.id.response);

        ip_adr.setText(clear_text);
        port_adr.setText(clear_text);
        response_set.setText(clear_text);

        ip_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Ip Set", Toast.LENGTH_SHORT).show();
                ip_adres = ip_adr.getText().toString();
            }
        });

        port_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Port set", Toast.LENGTH_SHORT).show();
                port_adres = port_adr.getText().toString();



            }
        });

        connect_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Attempt to Connect", Toast.LENGTH_SHORT).show();
                connect_status.setText(port_adres);
                Client newClient = new Client(ip_adres,Integer.parseInt(port_adres));




            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

public class Client extends AsyncTask<Void,Void,Void>{
    String ip_dst;
    int port_dst;
    String response = "";

    Client(String IP_dst,int Port_dst){

        ip_dst=IP_dst;
        port_dst=Port_dst;
    }


    @Override
    protected Void doInBackground(Void... params) {
        Socket socket= null;


        try{
            socket = new Socket(ip_dst,port_dst);
            ByteArrayOutputStream OutputStream = new ByteArrayOutputStream(1024);
            byte[] buffer = new byte[1024];

            int bytesRead;
            InputStream InputStream = socket.getInputStream();

            while((bytesRead=InputStream.read(buffer))!=-1){

                OutputStream.write(buffer,0,bytesRead);
                response+=OutputStream.toString("UTF-8");


            }


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}








    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.szymon.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.szymon.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}


