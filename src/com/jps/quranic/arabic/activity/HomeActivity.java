package com.jps.quranic.arabic.activity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jps.quranic.arabic.R;
import com.jps.quranic.arabic.util.Util;

/**
 * User: shah
 * Date: 2/25/12
 * Time: 5:57 PM
 */
public class HomeActivity extends ListActivity
{
  private final int[] _lesson1 = { R.array.hooa, R.array.hum, R.array.anta, R.array.antum, R.array.ana,
                                   R.array.nahnu, R.array.heeya };
  private final int[] _lesson3 = { R.array.rabbuhu, R.array.rabbuhum, R.array.rabbuka, R.array.rabbukum,
                                   R.array.rabbi, R.array.rabbuna, R.array.rabbuha };

  private final int[] _lesson5 = { R.array.lahu, R.array.lahum, R.array.laka, R.array.lakum, R.array.lee,
                                   R.array.lana, R.array.laha, R.array.fa_ala, R.array.fa_alu, R.array.fa_alta,
                                   R.array.fa_altum, R.array.fa_altu, R.array.fa_alna };

  protected ArrayList<Integer> _extraResourceIds;

  // extras
  public static final String EXTRA_RESOURCE_IDS = "extra_resource_ids";

  @Override
  protected void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );

    FlashCardActivity.PREF_NAME = this.getClass().getName();

    _extraResourceIds = Util.append( _lesson1, _lesson3, _lesson5 );
  }

  @Override
  protected void onResume()
  {
    super.onResume();

    // display options
    List<String> optionsList = new ArrayList<String>();
    optionsList.add( getString( R.string.start_new_session ) );

    if ( !areSharedPreferencesEmpty() )
    {
      optionsList.add( getString( R.string.continue_with_saved_session ) );
    }

    ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, optionsList );
    setListAdapter( adapter );
  }

  /**
   * Returns true if SharedPreferences are empty for FlashCardActivity.PREF_NAME.
   *
   * @return
   */
  private boolean areSharedPreferencesEmpty()
  {
    SharedPreferences settings = getSharedPreferences( FlashCardActivity.PREF_NAME, 0 );
    Map<String, ?> preferenceMap = settings.getAll();

    return preferenceMap.isEmpty();
  }

  @Override
  protected void onListItemClick( ListView l, View v, int position, long id )
  {
    super.onListItemClick( l, v, position, id );

    String option = (String) getListAdapter().getItem( position );

    if ( getString( R.string.start_new_session ).equals( option ) )
    {
      startSession( false );
    }
    else if ( getString( R.string.continue_with_saved_session ).equals( option ) )
    {
      startSession( true );
    }
  }

  // todo use this for lessons
  private void launchLessonActivity( int[] lesson1 )
  {
    Intent intent = new Intent( this, LessonActivity.class );
    intent.putIntegerArrayListExtra( EXTRA_RESOURCE_IDS, Util.append( lesson1 ) );
    startActivity( intent );
  }

  private void startSession( final boolean doContinueWithSavedSession )
  {
    // if user wants to start a new session and there is already a saved session, warn user that
    // if they start a new session, the saved one will be deleted
    if ( !doContinueWithSavedSession && !areSharedPreferencesEmpty() )
    {
      AlertDialog.Builder builder = new AlertDialog.Builder( HomeActivity.this );
      builder.setMessage( "Are you sure you want to start a new session? If you do, your saved session will be lost." )
        .setCancelable( false )
        .setPositiveButton( "Yes", new DialogInterface.OnClickListener()
        {
          public void onClick( DialogInterface dialog, int id )
          {
            doStartSession( doContinueWithSavedSession );
          }
        } )
        .setNegativeButton( "No", new DialogInterface.OnClickListener()
        {
          public void onClick( DialogInterface dialog, int id )
          {
            // do nothing
          }
        } );
      AlertDialog alert = builder.create();
      alert.show();
    }
    else
    {
      doStartSession( doContinueWithSavedSession );
    }
  }

  private void doStartSession( boolean doContinueWithSavedSession )
  {
    Intent intent = new Intent( HomeActivity.this, FlashCardActivity.class );
    intent.putExtra( FlashCardActivity.EXTRA_CONTINUE_WITH_SAVED_SESSION, doContinueWithSavedSession );
    intent.putIntegerArrayListExtra( EXTRA_RESOURCE_IDS, _extraResourceIds );
    startActivity( intent );
  }
}
