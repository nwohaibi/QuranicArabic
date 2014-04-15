package com.jps.quranic.arabic.adapter;

import android.content.Context;
import android.graphics.Typeface;
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
  private final int _redColor;
  private final int _blackColor;

  public HomeArrayAdapter( Context context, ArrayList<Lesson> lessonList )
  {
    super( context, R.layout.home_list_view, lessonList );

    _context = context;
    _lessonList = lessonList;
    _redColor = _context.getResources().getColor( android.R.color.holo_red_light );
    _blackColor = _context.getResources().getColor( android.R.color.black );
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
    final String lessonTitle = _lessonList.get( position ).getLessonTitle();
    lessonNumberView.setText( _lessonList.get( position ).getLessonNumber() );
    lessonTitleView.setText( lessonTitle );

    // display 'baab' lessons as bold and red in color
    if ( lessonTitle != null && lessonTitle.contains( "باب" ) )
    {
      lessonNumberView.setTypeface( lessonNumberView.getTypeface(), Typeface.BOLD );
      lessonNumberView.setTextColor( _redColor );

      lessonTitleView.setTypeface( lessonTitleView.getTypeface(), Typeface.BOLD );
      lessonTitleView.setTextColor( _redColor );
    }
    else
    {
      lessonNumberView.setTypeface( lessonNumberView.getTypeface(), Typeface.NORMAL );
      lessonNumberView.setTextColor( _blackColor );

      lessonTitleView.setTypeface( lessonTitleView.getTypeface(), Typeface.NORMAL );
      lessonTitleView.setTextColor( _blackColor );
    }

    // 5. return view
    return view;
  }
}