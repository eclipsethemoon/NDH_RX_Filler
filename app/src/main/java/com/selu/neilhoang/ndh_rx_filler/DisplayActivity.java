package com.selu.neilhoang.ndh_rx_filler;

import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.text.style.ReplacementSpan;
import android.util.EventLog;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DisplayActivity extends AppCompatActivity
{
    PlaceholderFragment taskFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        if(savedInstanceState == null)
        {
            taskFragment = new PlaceholderFragment();
            getSupportFragmentManager().beginTransaction().add(taskFragment,"MyFragment").commit();
        }
        else
        {
           taskFragment = (PlaceholderFragment) getSupportFragmentManager().findFragmentByTag("MyFragment");
        }
        taskFragment.startTask();
    }

// *************************************************************************
 public static class PlaceholderFragment extends Fragment
    {
        PatientManagerTask downloadTask;
        public  PlaceholderFragment()
        {

        }
        @Override
        public void onActivityCreated(Bundle savedInstanceState)
        {
            super.onActivityCreated(savedInstanceState);
            setRetainInstance(true);
        }
        public void startTask()
        {
            if(downloadTask != null)
            {
                downloadTask.cancel(true);
            }
            else
            {
                downloadTask = new PatientManagerTask();
                downloadTask.execute();
            }
        }


    }
//**************************************************************************************
    public static class PatientManagerTask extends AsyncTask<Void, Void,Void>
{
        @Override
        protected Void doInBackground(Void... params) {
            String downloadUrl = "http://www2.southeastern.edu/Academics/Faculty/jburris/emr.xml";

            try {
                URL url = new URL(downloadUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                InputStream inputStream = connection.getInputStream();
                proccessXML(inputStream);
            } catch (Exception e) {
                ShowLog.m(e + " ");
            }
            return null;
        }

//*******************************************************************************************
        public void proccessXML (InputStream inputStream)throws Exception
        {

            //Singleton class that why no New
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document XmlDocument = dBuilder.parse(inputStream);

            Element rootElement = XmlDocument.getDocumentElement();
            //ShowLog.m(" " + rootElement.getNodeName());
            //ShowLog.m(" " + rootElement.getTextContent());
            NodeList patientInfo = rootElement.getElementsByTagName("patient_info");
            NodeList patientOrders = null;

            Node currentPatientInfo = null;
            Node currentPatientOrder = null;


            for (int i = 0; i < patientInfo.getLength(); i++) {
                currentPatientInfo = patientInfo.item(i);

                patientOrders = currentPatientInfo.getChildNodes();
                for (int j = 0; j < patientOrders.getLength(); j++) {
                    currentPatientOrder = patientOrders.item(j);


                    if (currentPatientOrder.getNodeName().equalsIgnoreCase("type")) {
                        ShowLog.m("" + currentPatientOrder.getTextContent());

                    }
                    if (currentPatientOrder.getNodeName().equalsIgnoreCase("patientID")) {
                        ShowLog.m("" + currentPatientOrder.getTextContent());

                    }
                    if (currentPatientOrder.getNodeName().equalsIgnoreCase("patientName")) {
                        ShowLog.m("" + currentPatientOrder.getTextContent());

                    }
                    if (currentPatientOrder.getNodeName().equalsIgnoreCase("medicine")) {
                        ShowLog.m("" + currentPatientOrder.getTextContent());

                    }
                    if (currentPatientOrder.getNodeName().equalsIgnoreCase("dosage")) {
                        ShowLog.m("" + currentPatientOrder.getTextContent());

                    }
                    if (currentPatientOrder.getNodeName().equalsIgnoreCase("refillsRemaining")) {
                        ShowLog.m("" + currentPatientOrder.getTextContent());
                    }
                     ShowLog.m("" + currentPatientOrder.getNodeName());
                           ShowLog.m(" " + currentPatientOrder.getTextContent());
                }
                ShowLog.m(" " + currentPatientInfo.getNodeName());
            }

        }//End of ProcessXML

    }//End of PatientManagerTask
}//End of DisplayActivity


