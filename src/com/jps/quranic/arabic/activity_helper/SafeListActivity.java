package com.jps.quranic.arabic.activity_helper;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.jps.quranic.arabic.util.DialogUtil;

/**
 * User: shah
 * Date: 2/25/12
 * Time: 5:38 PM
 */
public class SafeListActivity extends ListActivity
{
  public final void onCreate( Bundle savedInstanceState )
  {
    try
    {
      super.onCreate( savedInstanceState );
      safeOnCreate( savedInstanceState );
    }
    catch ( Exception e )
    {
      Log.e( "Arabic", "SafeListActivity.onCreate()", e );
      DialogUtil.showErrorDialog( this );
    }
  }

  public void safeOnCreate( Bundle savedInstanceState )
  {
  }

  @Override
  protected final void onResume()
  {
    try
    {
      super.onResume();
      safeOnResume();
    }
    catch ( Exception e )
    {
      Log.e( "Arabic", "SafeListActivity.onResume()", e );
      DialogUtil.showErrorDialog( this );
    }
  }

  public void safeOnResume()
  {
  }

  @Override
  protected void onListItemClick( ListView l, View v, int position, long id )
  {
    try
    {
      safeOnListItemClick( l, v, position, id );
    }
    catch ( Exception e )
    {
      Log.e( "Arabic", "onListItemClick.onResume()", e );
      DialogUtil.showErrorDialog( this );
    }
  }

  public void safeOnListItemClick( ListView l, View v, int position, long id )
  {
  }
}
