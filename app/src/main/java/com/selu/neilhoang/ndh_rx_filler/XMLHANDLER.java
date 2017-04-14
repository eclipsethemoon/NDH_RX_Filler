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
 * Created by Neil Hoang on 4/4/2017.
 */

public class XMLHANDLER
{

    private static final String ns = null;

    public List parse(InputStream in) throws XmlPullParserException, IOException
    {
        try
        {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
            parser.setInput(in,null);
            parser.nextTag();
            return readEMR(parser);
        }
        finally
        {
            in.close();
        }
    }
    private List readEMR(XmlPullParser parser) throws XmlPullParserException, IOException
    {
        List patientInfo = new ArrayList();

        parser.require(XmlPullParser.START_TAG, ns, "emr");
        while (parser.next() != XmlPullParser.START_TAG)
        {
            continue;
        }
        String name = parser.getName();
        //Starts by looking for the entry tag
        if (name.equals("patient_info"))
        {
            patientInfo.add(readEMR(parser));
        }
        else
        {
            skip(parser);
        }
        return patientInfo;
    }


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
