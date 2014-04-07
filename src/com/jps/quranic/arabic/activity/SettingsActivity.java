package com.jps.quranic.arabic.activity;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.jps.quranic.arabic.R;

/**
 * User: shah
 * Date: 1/13/14
 * Time: 5:11 PM
 */
public class SettingsActivity extends ListActivity
{
  public static final String SETTINGS_PREF = "settings_pref";
  public static final String KEY_CHECKED_LESSON_INDEX_SET = "key_checked_lesson_index_set";
  public static final String KEY_CHECKED_LESSON_NAME_SET = "key_checked_lesson_name_set";
  public static final String EXTRA_LESSON_NAMES = "extra_lesson_names";

  @Override
  @SuppressWarnings( "unchecked" )
  protected void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );

    ListView listView = getListView();

    // add header text
    TextView headerView = new TextView( this );
    headerView.setText( R.string.settings_header );
    headerView.setTextSize( 17 );
    headerView.setPadding( 15, 15, 15, 15 );
    if ( listView.getHeaderViewsCount() != 1 )
    {
      listView.addHeaderView( headerView, null, false );
    }

    listView.setChoiceMode( AbsListView.CHOICE_MODE_MULTIPLE );

    ArrayList<String> lessonList = (ArrayList<String>) getIntent().getSerializableExtra( EXTRA_LESSON_NAMES );

    ArrayAdapter<String> adapter;
    adapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_multiple_choice, lessonList );
    setListAdapter( adapter );

    // get checked lessons from preferences
    SharedPreferences prefs = getSharedPreferences( SETTINGS_PREF, 0 );
    Set<String> checkedLessonSet = prefs.getStringSet( KEY_CHECKED_LESSON_INDEX_SET, new HashSet<String>() );

    if ( !checkedLessonSet.isEmpty() )
    {
      for ( String checkedLesson : checkedLessonSet )
      {
        listView.setItemChecked( Integer.valueOf( checkedLesson ), true );
      }
    }

    // defining an item click listener
    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener()
    {
      @Override
      public void onItemClick( AdapterView<?> arg0, View arg1, int position, long id )
      {
        invalidateOptionsMenu();
      }
    };

    // setting the ItemClickEvent listener for the ListView
    listView.setOnItemClickListener( itemClickListener );
  }

  @Override
  protected void onStop()
  {
    super.onStop();

    ListView listView = getListView();
    Set<String> checkedLessonIndexSet = new HashSet<String>();
    Set<String> checkedLessonNameSet = new HashSet<String>();
    for ( int i = 0; i < listView.getCount(); i++ )
    {
      if ( listView.isItemChecked( i ) )
      {
        checkedLessonIndexSet.add( String.valueOf( i ) );
        checkedLessonNameSet.add( listView.getAdapter().getItem( i ).toString() ); // save lesson names
      }
    }

    // save checked lessons in preferences
    SharedPreferences prefs = getSharedPreferences( SETTINGS_PREF, 0 );
    SharedPreferences.Editor editor = prefs.edit();
    editor.putStringSet( KEY_CHECKED_LESSON_INDEX_SET, checkedLessonIndexSet );
    editor.putStringSet( KEY_CHECKED_LESSON_NAME_SET, checkedLessonNameSet );
    editor.commit();
  }

  @Override
  public boolean onCreateOptionsMenu( Menu menu )
  {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate( R.menu.toggle_menu, menu );
    updateMenu( menu );

    return true;
  }

  private void updateMenu( Menu menu )
  {
    MenuItem toggleCheckMenu = menu.findItem( R.id.toggle_check );

    // if all lessons are checked, set menu icon to checked ( +1 because of header )
    if ( areAllLessonsChecked() )
    {
      setMenuAsChecked( toggleCheckMenu );
    }
    else // if all lessons aren't checked, set menu icon to unchecked
    {
      setMenuAsUnchecked( toggleCheckMenu );
    }
  }

  private boolean areAllLessonsChecked()
  {
    return getListView().getCheckedItemCount() + 1 == getListView().getCount();
  }

  @Override
  public boolean onOptionsItemSelected( MenuItem item )
  {
    switch ( item.getItemId() )
    {
      case R.id.toggle_check:

        // check all options
        if ( item.getTitle().equals( getString( R.string.check_all ) ) )
        {
          for ( int i = 1; i < getListView().getCount(); i++ )
          {
            getListView().setItemChecked( i, true );

            setMenuAsChecked( item );
          }
        }
        else // uncheck all options
        {
          for ( int i = 1; i < getListView().getCount(); i++ )
          {
            getListView().setItemChecked( i, false );

            setMenuAsUnchecked( item );
          }
        }
        return true;
      default:
        return super.onOptionsItemSelected( item );
    }
  }

  @Override
  public boolean onPrepareOptionsMenu( Menu menu )
  {
    updateMenu( menu );
    return super.onPrepareOptionsMenu( menu );
  }

  private void setMenuAsChecked( MenuItem item )
  {
    item.setIcon( android.R.drawable.checkbox_on_background );
    item.setTitle( R.string.uncheck_all );
  }

  private void setMenuAsUnchecked( MenuItem item )
  {
    item.setIcon( android.R.drawable.checkbox_off_background );
    item.setTitle( R.string.check_all );
  }
}
