package com.jps.quranic.arabic.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import com.jps.quranic.arabic.adapter.LessonArrayAdapter;

/**
 * User: shah
 * Date: 11/24/11
 * Time: 2:12 PM
 */
public class LessonActivity extends ListActivity
{
  public static final String EXTRA_LESSON_RES_ID = "extra_lesson_res_id";
  public static final String EXTRA_NUM_LESSONS = "extra_num_lessons";

  @Override
  protected void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );

    Intent intent = getIntent();

    int numLessons = intent.getIntExtra( EXTRA_NUM_LESSONS, 0 );

    ArrayList<Integer> resIds = new ArrayList<Integer>();
    for ( int i = 0; i < numLessons; i++ )
    {
      resIds.add( intent.getIntExtra( EXTRA_LESSON_RES_ID + i, 0 ) );
    }

    LessonArrayAdapter adapter = new LessonArrayAdapter( this, resIds );
    setListAdapter( adapter );
  }
}