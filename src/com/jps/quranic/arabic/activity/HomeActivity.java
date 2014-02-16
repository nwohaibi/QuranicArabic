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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
  private final int[] _lesson1 = { R.array.hooa, R.array.hum, R.array.anta, R.array.antum, R.array.ana, R.array.nahnu,
                                   R.array.heeya };
  private final int[] _lesson2 = { R.array.rabbuhu, R.array.rabbuhum, R.array.rabbuka, R.array.rabbukum, R.array.rabbi,
                                   R.array.rabbuna, R.array.rabbuha };
  private final int[] _lesson3 = { R.array.lahu, R.array.lahum, R.array.laka, R.array.lakum, R.array.lee, R.array.lana,
                                   R.array.laha };
  private final int[] _lesson4 = { R.array.fa_ala, R.array.fa_alu, R.array.fa_alta, R.array.fa_altum, R.array.fa_altu,
                                   R.array.fa_alna };
  private final int[] _lesson5 = { R.array.min_hu, R.array.min_hum, R.array.min_ka, R.array.min_kum, R.array.min_ni,
                                   R.array.min_na, R.array.min_ha };
  private final int[] _lesson6 = { R.array.an_hu, R.array.an_hum, R.array.an_ka, R.array.an_kum, R.array.an_ni,
                                   R.array.an_na, R.array.an_ha };
  private final int[] _lesson7 = { R.array.yaf_alu, R.array.yaf_alu_na, R.array.taf_alu, R.array.taf_alu_na,
                                   R.array.af_alu, R.array.naf_alu };
  private final int[] _lesson8 = { R.array.ma_ahu, R.array.ma_ahum, R.array.ma_aka, R.array.ma_akum,
                                   R.array.ma_ee, R.array.ma_ee_na, R.array.ma_aha };

  private Map<String, int[]> _lessonMap;

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
        intent.putExtra( SettingsActivity.EXTRA_LESSON_NAMES, (Serializable) getLessonNameList() );
        startActivity( intent );
      }
    } );

    _lessonMap = new HashMap<String, int[]>();
    _lessonMap.put( getString( R.string.lesson_1 ), _lesson1 );
    _lessonMap.put( getString( R.string.lesson_2 ), _lesson2 );
    _lessonMap.put( getString( R.string.lesson_3 ), _lesson3 );
    _lessonMap.put( getString( R.string.lesson_4 ), _lesson4 );
    _lessonMap.put( getString( R.string.lesson_5 ), _lesson5 );
    _lessonMap.put( getString( R.string.lesson_6 ), _lesson6 );
    _lessonMap.put( getString( R.string.lesson_7 ), _lesson7 );
    _lessonMap.put( getString( R.string.lesson_8 ), _lesson8 );
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
    optionsList.addAll( getLessonNameList() );

    ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, optionsList );
    setListAdapter( adapter );
  }

  private List<String> getLessonNameList()
  {
    List<String> lessonNameList = new ArrayList<String>( _lessonMap.keySet() );
    Collections.sort( lessonNameList );
    return lessonNameList;
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
      Set<String> checkedLessonNameSet = prefs.getStringSet( SettingsActivity.KEY_CHECKED_LESSON_NAME_SET, new HashSet<String>() );

      if ( checkedLessonNameSet.isEmpty() )
      {
        Toast.makeText( getBaseContext(), "Visit Settings to add Lessons", Toast.LENGTH_LONG ).show();
      }
      else
      {
        // lesson ids of lessons checked in settings
        ArrayList<Integer> lessonIds = new ArrayList<Integer>();

        for ( String checkedLessonName : checkedLessonNameSet )
        {
          int[] lessonArray = _lessonMap.get( checkedLessonName );
          lessonIds.addAll( Util.append( lessonArray ) );
        }

        startSession( false, lessonIds );
      }
    }
    else if ( getString( R.string.continue_with_saved_session ).equals( option ) )
    {
      List<int[]> lessons = new ArrayList<int[]>( _lessonMap.values() );
      int[][] lessonArray = new int[lessons.size()][];

      for ( int i = 0; i < lessons.size(); i++ )
      {
        lessonArray[i] = lessons.get( i );
      }

      startSession( true, Util.append( lessonArray ) );
    }
    else
    {
      launchLessonActivity( option );
    }
  }

  private void launchLessonActivity( String lessonName )
  {
    int[] lessonArray = _lessonMap.get( lessonName );
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
