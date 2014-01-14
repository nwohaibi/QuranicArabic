package com.jps.quranic.arabic.util;

/**
 * User: shah
 * Date: 1/13/14
 * Time: 4:30 PM
 */
public class WordMeaning
{
  private String _word;
  private String _meaning;

  public WordMeaning( String word, String meaning )
  {
    _word = word;
    _meaning = meaning;
  }

  public String getWord()
  {
    return _word;
  }

  public String getMeaning()
  {
    return _meaning;
  }
}
