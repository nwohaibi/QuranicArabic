package com.jps.quranic.arabic.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import com.jps.quranic.arabic.R;

/**
 * User: shah
 * Date: 1/13/14
 * Time: 5:11 PM
 */
public class SettingsActivity extends ListActivity
{
  @Override
  protected void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );

    getListView().setChoiceMode( AbsListView.CHOICE_MODE_MULTIPLE );

    ArrayList<String> lessonList = new ArrayList<String>();
    lessonList.add( getString( R.string.lesson_1 ) );
    lessonList.add( getString( R.string.lesson_2 ) );
    lessonList.add( getString( R.string.lesson_3 ) );
    lessonList.add( getString( R.string.lesson_4 ) );

    ArrayAdapter<String> adapter;
    adapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_multiple_choice, lessonList );
    setListAdapter( adapter );
  }
}
