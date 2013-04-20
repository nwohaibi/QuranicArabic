package com.jps.quranic.arabic.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * User: shah
 * Date: 2/25/12
 * Time: 5:28 PM
 */
public class DialogUtil
{
  public static void showErrorDialog( final Activity activity )
  {
    AlertDialog.Builder builder = new AlertDialog.Builder( activity );
    builder.setMessage( "An error occurred on this page, do you want to exit?" )
      .setCancelable( false )
      .setPositiveButton( "Yes", new DialogInterface.OnClickListener()
      {
        public void onClick( DialogInterface dialog, int id )
        {
          activity.finish();
        }
      } )
      .setNegativeButton( "No", new DialogInterface.OnClickListener()
      {
        public void onClick( DialogInterface dialog, int id )
        {
          dialog.cancel();
        }
      } );
    AlertDialog alert = builder.create();
    alert.show();
  }
}
