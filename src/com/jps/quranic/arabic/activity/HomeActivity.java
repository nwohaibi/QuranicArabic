package com.jps.quranic.arabic.activity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jps.quranic.arabic.R;
import com.jps.quranic.arabic.adapter.HomeArrayAdapter;
import com.jps.quranic.arabic.util.Lesson;
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
  private final int[] _lesson8 = { R.array.ma_ahu, R.array.ma_ahum, R.array.ma_aka, R.array.ma_akum, R.array.ma_ee,
                                   R.array.ma_ee_na, R.array.ma_aha };
  private final int[] _lesson9 = { R.array.fee_hee, R.array.fee_him, R.array.fee_ka, R.array.fee_kum, R.array.fee_ya,
                                   R.array.fee_na, R.array.fee_ha };
  private final int[] _lesson10 = { R.array.if_al, R.array.if_a_lu, R.array.la_taf_al, R.array.la_taf_a_lu };
  private final int[] _lesson11 = { R.array.bi_hi, R.array.bi_him, R.array.bi_ka, R.array.bi_kum, R.array.bi,
                                    R.array.bi_na, R.array.bi_ha };
  private final int[] _lesson12 = { R.array.a_lai_hi, R.array.a_lai_him, R.array.a_lai_ka, R.array.a_lai_kum,
                                    R.array.a_lai_ya, R.array.a_lai_na, R.array.a_lai_ha };
  private final int[] _lesson13 = { R.array.fa_il, R.array.maf_ul, R.array.fail, R.array.fa_i_lun, R.array.maf_u_lun };
  private final int[] _lesson14 = { R.array.ilai_hi, R.array.ilai_him, R.array.ilai_ka, R.array.ilai_kum,
                                    R.array.ilai_ya, R.array.ilai_na, R.array.ilai_ha };

  private Map<String, int[]> _lessonMap;
  private ArrayList<Lesson> _lessonList;

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
    _lessonMap.put( getString( R.string.lesson_9 ), _lesson9 );
    _lessonMap.put( getString( R.string.lesson_10 ), _lesson10 );
    _lessonMap.put( getString( R.string.lesson_11 ), _lesson11 );
    _lessonMap.put( getString( R.string.lesson_12 ), _lesson12 );
    _lessonMap.put( getString( R.string.lesson_13 ), _lesson13 );
    _lessonMap.put( getString( R.string.lesson_14 ), _lesson14 );

    _lessonList = new ArrayList<Lesson>();
    _lessonList.add( new Lesson( getString( R.string.lesson_1 ), getString( R.string.lesson_title_1 ) ) );
    _lessonList.add( new Lesson( getString( R.string.lesson_2 ), getString( R.string.lesson_title_2 ) ) );
    _lessonList.add( new Lesson( getString( R.string.lesson_3 ), getString( R.string.lesson_title_3 ) ) );
    _lessonList.add( new Lesson( getString( R.string.lesson_4 ), getString( R.string.lesson_title_4 ) ) );
    _lessonList.add( new Lesson( getString( R.string.lesson_5 ), getString( R.string.lesson_title_5 ) ) );
    _lessonList.add( new Lesson( getString( R.string.lesson_6 ), getString( R.string.lesson_title_6 ) ) );
    _lessonList.add( new Lesson( getString( R.string.lesson_7 ), getString( R.string.lesson_title_7 ) ) );
    _lessonList.add( new Lesson( getString( R.string.lesson_8 ), getString( R.string.lesson_title_8 ) ) );
    _lessonList.add( new Lesson( getString( R.string.lesson_9 ), getString( R.string.lesson_title_9 ) ) );
    _lessonList.add( new Lesson( getString( R.string.lesson_10 ), getString( R.string.lesson_title_10 ) ) );
    _lessonList.add( new Lesson( getString( R.string.lesson_11 ), getString( R.string.lesson_title_11 ) ) );
    _lessonList.add( new Lesson( getString( R.string.lesson_12 ), getString( R.string.lesson_title_12 ) ) );
    _lessonList.add( new Lesson( getString( R.string.lesson_13 ), getString( R.string.lesson_title_13 ) ) );
    _lessonList.add( new Lesson( getString( R.string.lesson_14 ), getString( R.string.lesson_title_14 ) ) );
  }

  @Override
  protected void onResume()
  {
    super.onResume();

    // display options
    ArrayList<Lesson> optionsList = new ArrayList<Lesson>();
    optionsList.add( new Lesson( getString( R.string.start_new_session ), null ) );

    if ( !areSharedPreferencesEmpty() )
    {
      optionsList.add( new Lesson( getString( R.string.continue_with_saved_session ), null ) );
    }
    optionsList.addAll( getSortedLessonList() );

    HomeArrayAdapter adapter = new HomeArrayAdapter( this, optionsList );
    setListAdapter( adapter );
  }

  private List<Lesson> getSortedLessonList()
  {
    List<Lesson> lessonList = new ArrayList<Lesson>( _lessonList );
    Collections.sort( lessonList, new Comparator<Lesson>()
    {
      @Override
      public int compare( Lesson lesson1, Lesson lesson2 )
      {
        return Integer.valueOf( lesson1.getLessonNumber().substring( 7 ) ).compareTo(
          Integer.valueOf( lesson2.getLessonNumber().substring( 7 ) ) );
      }
    } );
    return lessonList;
  }

  private List<String> getLessonNameList()
  {
    List<String> lessonNameList = new ArrayList<String>( _lessonMap.keySet() );
    Collections.sort( lessonNameList, new Comparator<String>()
    {
      @Override
      public int compare( String s1, String s2 )
      {
        return Integer.valueOf( s1.substring( 7 ) ).compareTo( Integer.valueOf( s2.substring( 7 ) ) );
      }
    } );
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

    Lesson lesson = (Lesson) getListAdapter().getItem( position );
    String option = lesson.getLessonNumber();

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
