package com.jps.quranic.arabic.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.jps.quranic.arabic.R;
import com.jps.quranic.arabic.util.WordMeaning;

/**
 * User: shah
 * Date: 11/25/11
 * Time: 4:07 PM
 */
public class LessonArrayAdapter extends ArrayAdapter<Integer>
{
  private final Activity _context;

  /** Holds a list of arabic words */
  private List<WordMeaning> _wordMeaningList;

  public LessonArrayAdapter( Activity context, ArrayList<Integer> lessonResIds )
  {
    super( context, R.layout.lesson_list_item, lessonResIds );

    _context = context;

    _wordMeaningList = new ArrayList<WordMeaning>();

    for ( Integer lessonResId : lessonResIds )
    {
      String[] stringArray = context.getResources().getStringArray( lessonResId );
      _wordMeaningList.add( new WordMeaning( stringArray[0], stringArray[1] ) );
    }
  }

  // static to save the reference to the outer class and to avoid access to
  // any members of the containing class
  static class ViewHolder
  {
    public TextView _wordView;
    public TextView _meaningView;
  }

  // todo disable all list cells
  @Override
  public View getView( int position, View convertView, ViewGroup parent )
  {
    // ViewHolder will buffer the assess to the individual fields of the row
    // layout

    ViewHolder holder;
    // Recycle existing view if passed as parameter
    // This will save memory and time on Android
    // This only works if the base layout for all classes are the same
    View rowView = convertView;
    if ( rowView == null )
    {
      LayoutInflater inflater = _context.getLayoutInflater();
      rowView = inflater.inflate( R.layout.lesson_list_item, null, true );

      holder = new ViewHolder();
      holder._wordView = (TextView) rowView.findViewById( R.id.word_view );
      holder._meaningView = (TextView) rowView.findViewById( R.id.meaning_view );

      rowView.setTag( holder );
    }
    else
    {
      holder = (ViewHolder) rowView.getTag();
    }

    holder._wordView.setText( _wordMeaningList.get( position ).getWord() );
    holder._meaningView.setText( _wordMeaningList.get( position ).getMeaning() );

    return rowView;
  }
}
