package com.jps.quranic.arabic.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.util.ArrayList;

import com.jps.quranic.arabic.R;
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
  public static final String EXTRA_STRING_ARRAY_ID = "extra_string_array_id";

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

    // get lesson header text
    String lessonHeaderText = null;
    int stringArrayId = intent.getIntExtra( EXTRA_STRING_ARRAY_ID, 0 );
    String[] lesson = getResources().getStringArray( stringArrayId );
    if ( lesson.length > 2 )
    {
      lessonHeaderText = lesson[2];
    }

    // set action bar title
    getActionBar().setTitle( lesson[0] );

    // set lesson header text
    if ( !isEmpty( lessonHeaderText ) && getListView().getHeaderViewsCount() == 0 )
    {
      LayoutInflater inflater = getLayoutInflater();
      TextView lessonHeaderView = (TextView) inflater.inflate( R.layout.lesson_header_view, null );
      lessonHeaderView.setText( lessonHeaderText );
      getListView().addHeaderView( lessonHeaderView, null, false );
    }

    LessonArrayAdapter adapter = new LessonArrayAdapter( this, resIds );
    setListAdapter( adapter );
  }

  private boolean isEmpty( String string )
  {
    return string != null && string.isEmpty();
  }
}