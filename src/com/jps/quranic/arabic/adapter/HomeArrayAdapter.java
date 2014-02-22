package com.jps.quranic.arabic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import com.jps.quranic.arabic.R;
import com.jps.quranic.arabic.util.Lesson;

/**
 * User: shah
 * Date: 2/20/14
 * Time: 9:51 PM
 */
public class HomeArrayAdapter extends ArrayAdapter<Lesson>
{
  private final Context _context;
  private final ArrayList<Lesson> _lessonList;

  public HomeArrayAdapter( Context context, ArrayList<Lesson> lessonList )
  {
    super( context, R.layout.home_list_view, lessonList );

    _context = context;
    _lessonList = lessonList;
  }

  @Override
  public View getView( int position, View convertView, ViewGroup parent )
  {
    // 1. Create inflater
    LayoutInflater inflater = (LayoutInflater) _context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

    // 2. Get view from inflater
    View view = inflater.inflate( R.layout.home_list_view, parent, false );

    // 3. Get the two text view from the view
    TextView lessonNumberView = (TextView) view.findViewById( R.id.lesson_number );
    TextView lessonTitleView = (TextView) view.findViewById( R.id.lesson_title );

    // 4. Set the text for textView
    lessonNumberView.setText( _lessonList.get( position ).getLessonNumber() );
    lessonTitleView.setText( _lessonList.get( position ).getLessonTitle() );

    // 5. return view
    return view;
  }
}