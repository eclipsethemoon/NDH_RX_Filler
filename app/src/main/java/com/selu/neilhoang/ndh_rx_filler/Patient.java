package com.selu.neilhoang.ndh_rx_filler;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neil on 4/4/2017.
 */

public class Patient
{
    private static final String ns = null;
    public final String pName;
    public final String patientID;
    public final String type;
    public final String medicine;
    public final String dosage;
    public final String refillsRemaining;


    private Patient(String name, String pName, String patientID, String type, String medicine, String dosage, String refillsRemaining)
    {
        this.pName = pName;
        this.patientID = patientID;
        this.type = type;
        this.medicine = medicine;
        this.dosage = dosage;
        this.refillsRemaining = refillsRemaining;
    }

    private Patient readPatient(XmlPullParser parser) throws XmlPullParserException, IOException
    {
        parser.require(XmlPullParser.START_TAG, ns, "patient_info");
        String pName = null;
        String patientID = null;
        String type = null;
        String medicine = null;
        String dosage = null;
        String refillsRemaining = null;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("name"))
            {
                pName = readName(parser);
            }
            else if (name.equals("patientID"))
            {
                patientID = readPID(parser);
            }
            else if (name.equals("type"))
            {
                type = readType(parser);
            }
            else if (name.equals("medicine"))
            {
                medicine = readMedicine(parser);
            }
            else if (name.equals("dosage"))
            {
                dosage = readDosage(parser);
            }
            else if (name.equals("refillsRemaining"))
            {
                refillsRemaining = readRefill(parser);
            }
            else
            {
                skip(parser);
            }
        }
        return new Patient(pName,patientID,dosage,type,medicine,dosage,refillsRemaining);
    }

    // Processes name tags in the patient_info.
    private String readName(XmlPullParser parser) throws IOException, XmlPullParserException
    {
        parser.require(XmlPullParser.START_TAG, ns, "name");
        String pName = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "name");
        return pName;
    }
    private String readPID(XmlPullParser parser) throws IOException, XmlPullParserException
    {
        parser.require(XmlPullParser.START_TAG, ns, "patientID");
        String patientID = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "patientID");
        return patientID;
    }
    private String readType(XmlPullParser parser) throws IOException, XmlPullParserException
    {
        parser.require(XmlPullParser.START_TAG, ns, "type");
        String type = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "type");
        return type;
    }
    private String readMedicine(XmlPullParser parser) throws IOException, XmlPullParserException
    {
        parser.require(XmlPullParser.START_TAG, ns, "medicine");
        String medicine = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "medicine");
        return medicine;
    }
    private String readDosage(XmlPullParser parser) throws IOException, XmlPullParserException
    {
        parser.require(XmlPullParser.START_TAG, ns, "dosage");
        String dosage = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "dosage");
        return dosage;
    }
    private String readRefill(XmlPullParser parser) throws IOException, XmlPullParserException
    {
        parser.require(XmlPullParser.START_TAG, ns, "refillsRemaining");
        String refillsRemaining = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "refillsRemaining");
        return refillsRemaining;
    }

    // For the tags name and summary, extracts their text values.
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException
    {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    //Skip
    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException
    {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
