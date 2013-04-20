package com.jps.quranic.arabic.activity_helper;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.jps.quranic.arabic.util.DialogUtil;

/**
 * User: shah
 * Date: 2/25/12
 * Time: 5:34 PM
 */
public class SafeActivity extends Activity
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
      Log.e( "Arabic", "SafeActivity.onCreate()", e );
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
      Log.e( "Arabic", "SafeActivity.onResume()", e );
      DialogUtil.showErrorDialog( this );
    }
  }

  public void safeOnResume()
  {
  }

  @Override
  public final boolean onCreateOptionsMenu( Menu menu )
  {
    try
    {
      safeOnCreateOptionsMenu( menu );
      return super.onCreateOptionsMenu( menu );
    }
    catch ( Exception e )
    {
      Log.e( "Arabic", "SafeActivity.onCreateOptionsMenu()", e );
      DialogUtil.showErrorDialog( this );
      return false;
    }
  }

  public void safeOnCreateOptionsMenu( Menu menu )
  {
  }

  @Override
  public final boolean onOptionsItemSelected( MenuItem menuItem )
  {
    try
    {
      safeOnOptionsItemSelected( menuItem );
      return super.onOptionsItemSelected( menuItem );
    }
    catch ( Exception e )
    {
      Log.e( "Arabic", "SafeActivity.onOptionsItemSelected()", e );
      DialogUtil.showErrorDialog( this );
      return false;
    }
  }

  public void safeOnOptionsItemSelected( MenuItem menuItem )
  {
  }
}
