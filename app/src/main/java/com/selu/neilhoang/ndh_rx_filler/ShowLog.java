package com.selu.neilhoang.ndh_rx_filler;

import android.content.Context;
import android.widget.Toast;
import android.util.Log;


/**
 * Created by Neil Hoang on 4/14/2017.
 * This class is for debugging purposes
 */

public class ShowLog {
    public static void m(String message)
    {
        Log.d(" ",message);
    }

    public static void s(Context context, String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
