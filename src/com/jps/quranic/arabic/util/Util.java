package com.jps.quranic.arabic.util;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * User: shah
 * Date: 2/25/12
 * Time: 5:31 PM
 */
public class Util
{
  /**
   * Returns a map with an arabic word as the key, and it's urdu meaning as the value.
   *
   * @param context
   * @param stringResourceIds a list of string ids from strings.xml
   * @return
   */
  public static Map<String, String> getWordMeaningMap( Context context, ArrayList<Integer> stringResourceIds )
  {
    HashMap<String, String> wordMeaningMap = new HashMap<String, String>();

    for ( Integer wordResId : stringResourceIds )
    {
      String[] stringArray = context.getResources().getStringArray( wordResId );

      String wordInArabic = stringArray[0];
      String meaningInUrdu = stringArray[1];

      wordMeaningMap.put( wordInArabic, meaningInUrdu );
    }

    return wordMeaningMap;
  }

  /**
   * Takes in int[] arrays, appends them together and returns an ArrayList.
   *
   * @param arrays
   * @return
   */
  public static ArrayList<Integer> append( int[]... arrays )
  {
    ArrayList<Integer> list = new ArrayList<Integer>();

    for ( int[] array : arrays )
    {
      for ( int anArray : array )
      {
        list.add( anArray );
      }
    }

    return list;
  }
}
