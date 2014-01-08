package com.jps.quranic.arabic.activity;

import android.app.ListActivity;
import android.os.Bundle;

import java.util.ArrayList;

import com.jps.quranic.arabic.util.LessonArrayAdapter;

/**
 * User: shah
 * Date: 11/24/11
 * Time: 2:12 PM
 */
public class LessonActivity extends ListActivity
{
  @Override
  protected void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );

    ArrayList<Integer> resIds = getIntent().getIntegerArrayListExtra( QuranicArabicActivity.EXTRA_RESOURCE_IDS );

    LessonArrayAdapter adapter = new LessonArrayAdapter( this, resIds );
    setListAdapter( adapter );
  }
}