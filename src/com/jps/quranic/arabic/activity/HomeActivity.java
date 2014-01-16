package com.jps.quranic.arabic.activity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
  private final int[] _lesson2 = { R.array.rabbuhu, R.array.rabbuhum, R.array.rabbuka, R.array.rabbukum,
                                   R.array.rabbi, R.array.rabbuna, R.array.rabbuha };
  private final int[] _lesson3 = { R.array.lahu, R.array.lahum, R.array.laka, R.array.lakum, R.array.lee,
                                   R.array.lana, R.array.laha };
  private final int[] _lesson4 = { R.array.fa_ala, R.array.fa_alu, R.array.fa_alta,
                                   R.array.fa_altum, R.array.fa_altu, R.array.fa_alna };
  private final int[] _lesson5 = { R.array.min_hu, R.array.min_hum, R.array.min_ka,
                                   R.array.min_kum, R.array.min_ni, R.array.min_na };

  // extras
  public static final String EXTRA_RESOURCE_IDS = "extra_resource_ids";

  @Override
  protected void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );

    setContentView( R.layout.home_view );

    FlashCardActivity.PREF_NAME = this.getClass().getName();

    Button settingsButton = (Button) findViewById( R.id.settings_button );
    settingsButton.setOnClickListener( new View.OnClickListener()
    {
      @Override
      public void onClick( View view )
      {
        Intent intent = new Intent( HomeActivity.this, SettingsActivity.class );
        startActivity( intent );
      }
    } );
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

    optionsList.add( getString( R.string.lesson_1 ) );
    optionsList.add( getString( R.string.lesson_2 ) );
    optionsList.add( getString( R.string.lesson_3 ) );
    optionsList.add( getString( R.string.lesson_4 ) );
    optionsList.add( getString( R.string.lesson_5 ) );

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
      SharedPreferences prefs = getSharedPreferences( SettingsActivity.SETTINGS_PREF, 0 );
      Set<String> checkedLessonSet = prefs.getStringSet( SettingsActivity.KEY_CHECKED_LESSON_SET, new HashSet<String>() );

      if ( checkedLessonSet.isEmpty() )
      {
        Toast.makeText( getBaseContext(), "Visit Settings to add Lessons", Toast.LENGTH_LONG ).show();
      }
      else
      {
        ArrayList<Integer> lessonIds = new ArrayList<Integer>();

        for ( String checkedLesson : checkedLessonSet )
        {
          if ( Integer.valueOf( checkedLesson ) == 1 )
          {
            lessonIds.addAll( Util.append( _lesson1 ) );
          }
          else if ( Integer.valueOf( checkedLesson ) == 2 )
          {
            lessonIds.addAll( Util.append( _lesson2 ) );
          }
          else if ( Integer.valueOf( checkedLesson ) == 3 )
          {
            lessonIds.addAll( Util.append( _lesson3 ) );
          }
          else if ( Integer.valueOf( checkedLesson ) == 4 )
          {
            lessonIds.addAll( Util.append( _lesson4 ) );
          }
          else if ( Integer.valueOf( checkedLesson ) == 5 )
          {
            lessonIds.addAll( Util.append( _lesson5 ) );
          }
        }

        startSession( false, lessonIds );
      }
    }
    else if ( getString( R.string.continue_with_saved_session ).equals( option ) )
    {
      startSession( true, Util.append( _lesson1, _lesson2, _lesson3, _lesson4, _lesson5 ) );
    }
    else if ( getString( R.string.lesson_1 ).equals( option ) )
    {
      launchLessonActivity( _lesson1 );
    }
    else if ( getString( R.string.lesson_2 ).equals( option ) )
    {
      launchLessonActivity( _lesson2 );
    }
    else if ( getString( R.string.lesson_3 ).equals( option ) )
    {
      launchLessonActivity( _lesson3 );
    }
    else if ( getString( R.string.lesson_4 ).equals( option ) )
    {
      launchLessonActivity( _lesson4 );
    }
    else if ( getString( R.string.lesson_5 ).equals( option ) )
    {
      launchLessonActivity( _lesson5 );
    }
  }

  private void launchLessonActivity( int[] lessonArray )
  {
    Intent intent = new Intent( this, LessonActivity.class );

    for ( int i = 0; i < lessonArray.length; i++ )
    {
      intent.putExtra( LessonActivity.EXTRA_LESSON_RES_ID + i, lessonArray[i] );
    }

    intent.putExtra( LessonActivity.EXTRA_NUM_LESSONS, lessonArray.length );
    startActivity( intent );
  }

  private void startSession( final boolean doContinueWithSavedSession, final ArrayList<Integer> lessonIds )
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
            doStartSession( doContinueWithSavedSession, lessonIds );
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
      doStartSession( doContinueWithSavedSession, lessonIds );
    }
  }

  private void doStartSession( boolean doContinueWithSavedSession, ArrayList<Integer> lessonIds )
  {
    Intent intent = new Intent( HomeActivity.this, FlashCardActivity.class );
    intent.putExtra( FlashCardActivity.EXTRA_CONTINUE_WITH_SAVED_SESSION, doContinueWithSavedSession );
    intent.putIntegerArrayListExtra( EXTRA_RESOURCE_IDS, lessonIds );
    startActivity( intent );
  }
}
