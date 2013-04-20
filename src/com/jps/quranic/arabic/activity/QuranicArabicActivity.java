package com.jps.quranic.arabic.activity;

import android.content.Intent;
import android.os.Bundle;

import com.jps.quranic.arabic.R;
import com.jps.quranic.arabic.activity_helper.FlashCardActivity;
import com.jps.quranic.arabic.activity_helper.HomeActivity;
import com.jps.quranic.arabic.util.Util;

/**
 * User: shah
 * Date: 11/13/11
 * Time: 6:09 PM
 */
public class QuranicArabicActivity extends HomeActivity
{
  private final int[] _lesson1 = { R.array.hooa, R.array.hum, R.array.anta, R.array.antum, R.array.ana,
                                   R.array.nahnu, R.array.heeya };
  private final int[] _lesson3 = { R.array.rabbuhu, R.array.rabbuhum, R.array.rabbuka, R.array.rabbukum,
                                   R.array.rabbi, R.array.rabbuna, R.array.rabbuha };

  private final int[] _lesson5 = { R.array.lahu, R.array.lahum, R.array.laka, R.array.lakum, R.array.lee,
                                   R.array.lana, R.array.laha, R.array.fa_ala, R.array.fa_alu, R.array.fa_alta,
                                   R.array.fa_altum, R.array.fa_altu, R.array.fa_alna };

  @Override
  public void safeOnCreate( Bundle savedInstanceState )
  {
    FlashCardActivity.PREF_NAME = "QuranicArabicActivity";

    _extraResourceIds = Util.append( _lesson1, _lesson3, _lesson5 );
  }

  // todo use this for lessons
  private void launchLessonActivity( int[] lesson1 )
  {
    Intent intent = new Intent( this, LessonActivity.class );
    intent.putIntegerArrayListExtra( EXTRA_RESOURCE_IDS, Util.append( lesson1 ) );
    startActivity( intent );
  }
}
