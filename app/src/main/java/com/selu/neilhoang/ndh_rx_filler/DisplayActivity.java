package com.selu.neilhoang.ndh_rx_filler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class DisplayActivity extends AppCompatActivity
{

    // Declare variables
    private ListView listView;
    XMLHANDLER xmlobj;
    private static final String URL = "http://www2.southeastern.edu/Academics/Faculty/jburris/emr.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_display);

       listView = (ListView) findViewById(R.id.list);

        List<Patient> patients = null;


        try
        {
            XMLHANDLER parser = new XMLHANDLER();

            patients = parser.parse(getAssets().open(URL));

            ArrayAdapter<Patient> adapter = new ArrayAdapter<Patient>(this,R.layout.list_item,patients);

            listView.setAdapter(adapter);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}


