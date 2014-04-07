package com.jps.quranic.arabic.activity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
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
  private final int[] _lesson13 = { R.array.fa_il, R.array.maf_ul, R.array.fail, R.array.fa_i_lun, R.array.fa_i_lin,
                                    R.array.maf_u_lun, R.array.maf_u_lin };
  private final int[] _lesson14 = { R.array.ilai_hi, R.array.ilai_him, R.array.ilai_ka, R.array.ilai_kum,
                                    R.array.ilai_ya, R.array.ilai_na, R.array.ilai_ha };
  private final int[] _lesson15 = { R.array.fa_a_lat, R.array.hiya_taf_a_lu, R.array.fa_ila, R.array.fa_ilat,
                                    R.array.maf_ula, R.array.maf_ulat };
  private final int[] _lesson16 = { R.array.fa_ta_ha, R.array.fa_ta_hu, R.array.fa_tah_ta, R.array.fa_tah_tum,
                                    R.array.fa_tah_tu, R.array.fa_tah_na, R.array.yaf_ta_hu, R.array.yaf_ta_hu_na,
                                    R.array.taf_ta_hu, R.array.taf_ta_hu_na, R.array.af_ta_hu, R.array.naf_ta_hu,
                                    R.array.if_tah, R.array.if_ta_hu, R.array.la_taf_tah, R.array.la_taf_ta_hu,
                                    R.array.fa_tih, R.array.maf_tuh, R.array.fath, R.array.fa_ta_hat,
                                    R.array.hiya_taf_ta_hu };
  private final int[] _lesson17 = { R.array.inda_hu, R.array.inda_hum, R.array.inda_ka, R.array.inda_kum, R.array.in_di,
                                    R.array.inda_na, R.array.inda_ha };
  private final int[] _lesson18 = { R.array.ja_ala, R.array.ja_alu, R.array.ja_al_ta, R.array.ja_al_tum,
                                    R.array.ja_al_tu, R.array.ja_al_na, R.array.yaj_alu, R.array.yaj_alu_na,
                                    R.array.taj_alu, R.array.taj_alu_na, R.array.aj_alu, R.array.naj_alu, R.array.ij_al,
                                    R.array.ij_alu, R.array.la_taj_al, R.array.la_taj_alu, R.array.ja_il,
                                    R.array.maj_ul, R.array.jal, R.array.ja_a_lat, R.array.hiya_taj_alu };
  private final int[] _lesson19 = { R.array.na_sa_ra, R.array.na_sa_ru, R.array.na_sar_ta, R.array.na_sar_tum,
                                    R.array.na_sar_tu, R.array.na_sar_na, R.array.yan_su_ru, R.array.yan_su_ru_na,
                                    R.array.tan_su_ru, R.array.tan_su_ru_na, R.array.an_su_ru, R.array.nan_su_ru,
                                    R.array.un_sur, R.array.un_su_ru, R.array.la_tan_sur, R.array.la_tan_su_ru,
                                    R.array.na_sir, R.array.man_sur, R.array.nasr, R.array.na_sa_rat,
                                    R.array.hiya_tan_su_ru };
  private final int[] _lesson20 = { R.array.a_ba_da, R.array.a_ba_du, R.array.a_bat_ta, R.array.a_bat_tum,
                                    R.array.a_bat_tu, R.array.a_bad_na, R.array.ya_bu_du, R.array.ya_bu_du_na,
                                    R.array.ta_bu_du, R.array.ta_bu_du_na, R.array.a_bu_du, R.array.na_bu_du,
                                    R.array.o_bud, R.array.o_bu_du, R.array.la_ta_bud, R.array.la_ta_bu_du,
                                    R.array.a_bid, R.array.ma_bud, R.array.i_ba_da, R.array.a_ba_dat,
                                    R.array.hiya_ta_bu_du };
  private final int[] _lesson21 = { R.array.za_ka_ra, R.array.za_ka_ru, R.array.za_kar_ta, R.array.za_kar_tum,
                                    R.array.za_kar_tu, R.array.za_kar_na, R.array.yaz_ku_ru, R.array.yaz_ku_ru_na,
                                    R.array.taz_ku_ru, R.array.taz_ku_ru_na, R.array.az_ku_ru, R.array.naz_ku_ru,
                                    R.array.uz_kur, R.array.uz_ku_ru, R.array.la_taz_kur, R.array.la_taz_ku_ru,
                                    R.array.za_kir, R.array.maz_kur, R.array.zikr, R.array.za_ka_rat,
                                    R.array.hiya_taz_ku_ru };
  private final int[] _lesson22 = { R.array.ha_za, R.array.ha_u_la_i, R.array.za_li_ka, R.array.u_la_i_ka,
                                    R.array.al_la_zi, R.array.al_la_zi_na, R.array.ha_zi_hi, R.array.til_ka,
                                    R.array.al_la_ti, R.array.al_laa_ti };
  private final int[] _lesson23 = { R.array.ka_fa_ra, R.array.ka_fa_ru, R.array.ka_far_ta, R.array.ka_far_tum,
                                    R.array.ka_far_tu, R.array.ka_far_na, R.array.yak_fu_ru, R.array.yak_fu_ru_na,
                                    R.array.tak_fu_ru, R.array.tak_fu_ru_na, R.array.ak_fu_ru, R.array.nak_fu_ru,
                                    R.array.uk_fur, R.array.uk_fu_ru, R.array.la_tak_fur, R.array.la_tak_fu_ru,
                                    R.array.ka_fir, R.array.mak_fur, R.array.kufr, R.array.ka_fa_rat,
                                    R.array.hiya_tak_fur };
  private final int[] _lesson24 = { R.array.da_ra_ba, R.array.da_ra_bu, R.array.da_rab_ta, R.array.da_rab_tum,
                                    R.array.da_rab_tu, R.array.da_rab_na, R.array.yad_ri_bu, R.array.yad_ri_bu_na,
                                    R.array.tad_ri_bu, R.array.tad_ri_bu_na, R.array.ad_ri_bu, R.array.nad_ri_bu,
                                    R.array.id_rib, R.array.id_ri_bu, R.array.la_tad_rib, R.array.la_tad_ri_bu,
                                    R.array.da_rib, R.array.mad_rub, R.array.darb, R.array.da_ra_bat,
                                    R.array.hiya_tad_ri_bu };
  private final int[] _lesson25 = { R.array.za_la_ma, R.array.za_la_mu, R.array.za_lam_ta, R.array.za_lam_tum,
                                    R.array.za_lam_tu, R.array.za_lam_na, R.array.yaz_li_mu, R.array.yaz_li_mu_na,
                                    R.array.taz_li_mu, R.array.taz_li_mu_na, R.array.az_li_mu, R.array.naz_li_mu,
                                    R.array.iz_lim, R.array.iz_li_mu, R.array.la_taz_lim, R.array.la_taz_li_mu,
                                    R.array.za_lim, R.array.maz_lum, R.array.zulm, R.array.za_la_mat,
                                    R.array.hiya_taz_li_mu };

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

    Resources resources = getResources();
    String[] lesson1 = resources.getStringArray( R.array.lesson_1 );
    String[] lesson2 = resources.getStringArray( R.array.lesson_2 );
    String[] lesson3 = resources.getStringArray( R.array.lesson_3 );
    String[] lesson4 = resources.getStringArray( R.array.lesson_4 );
    String[] lesson5 = resources.getStringArray( R.array.lesson_5 );
    String[] lesson6 = resources.getStringArray( R.array.lesson_6 );
    String[] lesson7 = resources.getStringArray( R.array.lesson_7 );
    String[] lesson8 = resources.getStringArray( R.array.lesson_8 );
    String[] lesson9 = resources.getStringArray( R.array.lesson_9 );
    String[] lesson10 = resources.getStringArray( R.array.lesson_10 );
    String[] lesson11 = resources.getStringArray( R.array.lesson_11 );
    String[] lesson12 = resources.getStringArray( R.array.lesson_12 );
    String[] lesson13 = resources.getStringArray( R.array.lesson_13 );
    String[] lesson14 = resources.getStringArray( R.array.lesson_14 );
    String[] lesson15 = resources.getStringArray( R.array.lesson_15 );
    String[] lesson16 = resources.getStringArray( R.array.lesson_16 );
    String[] lesson17 = resources.getStringArray( R.array.lesson_17 );
    String[] lesson18 = resources.getStringArray( R.array.lesson_18 );
    String[] lesson19 = resources.getStringArray( R.array.lesson_19 );
    String[] lesson20 = resources.getStringArray( R.array.lesson_20 );
    String[] lesson21 = resources.getStringArray( R.array.lesson_21 );
    String[] lesson22 = resources.getStringArray( R.array.lesson_22 );
    String[] lesson23 = resources.getStringArray( R.array.lesson_23 );
    String[] lesson24 = resources.getStringArray( R.array.lesson_24 );
    String[] lesson25 = resources.getStringArray( R.array.lesson_25 );

    _lessonMap = new HashMap<String, int[]>();
    _lessonMap.put( lesson1[0], _lesson1 );
    _lessonMap.put( lesson2[0], _lesson2 );
    _lessonMap.put( lesson3[0], _lesson3 );
    _lessonMap.put( lesson4[0], _lesson4 );
    _lessonMap.put( lesson5[0], _lesson5 );
    _lessonMap.put( lesson6[0], _lesson6 );
    _lessonMap.put( lesson7[0], _lesson7 );
    _lessonMap.put( lesson8[0], _lesson8 );
    _lessonMap.put( lesson9[0], _lesson9 );
    _lessonMap.put( lesson10[0], _lesson10 );
    _lessonMap.put( lesson11[0], _lesson11 );
    _lessonMap.put( lesson12[0], _lesson12 );
    _lessonMap.put( lesson13[0], _lesson13 );
    _lessonMap.put( lesson14[0], _lesson14 );
    _lessonMap.put( lesson15[0], _lesson15 );
    _lessonMap.put( lesson16[0], _lesson16 );
    _lessonMap.put( lesson17[0], _lesson17 );
    _lessonMap.put( lesson18[0], _lesson18 );
    _lessonMap.put( lesson19[0], _lesson19 );
    _lessonMap.put( lesson20[0], _lesson20 );
    _lessonMap.put( lesson21[0], _lesson21 );
    _lessonMap.put( lesson22[0], _lesson22 );
    _lessonMap.put( lesson23[0], _lesson23 );
    _lessonMap.put( lesson24[0], _lesson24 );
    _lessonMap.put( lesson25[0], _lesson25 );

    _lessonList = new ArrayList<Lesson>();
    _lessonList.add( new Lesson( R.array.lesson_1, lesson1[0], lesson1[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_2, lesson2[0], lesson2[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_3, lesson3[0], lesson3[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_4, lesson4[0], lesson4[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_5, lesson5[0], lesson5[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_6, lesson6[0], lesson6[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_7, lesson7[0], lesson7[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_8, lesson8[0], lesson8[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_9, lesson9[0], lesson9[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_10, lesson10[0], lesson10[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_11, lesson11[0], lesson11[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_12, lesson12[0], lesson12[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_13, lesson13[0], lesson13[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_14, lesson14[0], lesson14[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_15, lesson15[0], lesson15[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_16, lesson16[0], lesson16[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_17, lesson17[0], lesson17[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_18, lesson18[0], lesson18[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_19, lesson19[0], lesson19[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_20, lesson20[0], lesson20[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_21, lesson21[0], lesson21[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_22, lesson22[0], lesson22[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_23, lesson23[0], lesson23[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_24, lesson24[0], lesson24[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_25, lesson25[0], lesson25[1] ) );
  }

  @Override
  protected void onResume()
  {
    super.onResume();

    // display options
    ArrayList<Lesson> optionsList = new ArrayList<Lesson>();
    optionsList.add( new Lesson( 0, getString( R.string.start_new_session ), null ) );

    if ( !areSharedPreferencesEmpty() )
    {
      optionsList.add( new Lesson( 0, getString( R.string.continue_with_saved_session ), null ) );
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
    else // launch LessonActivity
    {
      int[] lessonArray = _lessonMap.get( option );
      Intent intent = new Intent( this, LessonActivity.class );

      for ( int i = 0; i < lessonArray.length; i++ )
      {
        intent.putExtra( LessonActivity.EXTRA_LESSON_RES_ID + i, lessonArray[i] );
      }

      intent.putExtra( LessonActivity.EXTRA_NUM_LESSONS, lessonArray.length );
      intent.putExtra( LessonActivity.EXTRA_STRING_ARRAY_ID, lesson.getStringArrayId() );
      startActivity( intent );
    }
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
