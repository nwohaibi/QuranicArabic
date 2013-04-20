package com.jps.quranic.arabic.util;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jps.quranic.arabic.R;

/**
 * User: shah
 * Date: 11/25/11
 * Time: 4:07 PM
 */
public class LessonArrayAdapter extends ArrayAdapter<Integer>
{
  private final Activity _context;

  /** Holds a list of arabic words */
  private List<String> _wordList;

  /** For every arabic word, holds its urdu meaning */
  private Map<String, String> _wordMeaningMap;

  public LessonArrayAdapter( Activity context, ArrayList<Integer> lessonResIds )
  {
    super( context, R.layout.lesson_view, lessonResIds );

    _context = context;

    // initialize _wordMeaningMap with all arabic words and their meanings from strings.xml
    _wordMeaningMap = Util.getWordMeaningMap( _context, lessonResIds );

    _wordList = new ArrayList<String>();
    _wordList.addAll( _wordMeaningMap.keySet() );
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
    // todo try catch
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
      rowView = inflater.inflate( R.layout.lesson_view, null, true );

      holder = new ViewHolder();
      holder._wordView = (TextView) rowView.findViewById( R.id.word_view );
      holder._meaningView = (TextView) rowView.findViewById( R.id.meaning_view );

      rowView.setTag( holder );
    }
    else
    {
      holder = (ViewHolder) rowView.getTag();
    }

    String word = _wordList.get( position );
    holder._wordView.setText( word );

    String meaning = _wordMeaningMap.get( word );
    holder._meaningView.setText( meaning );

    return rowView;
  }
}
