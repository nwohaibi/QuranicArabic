package com.jps.quranic.arabic.util;

/**
 * User: shah
 * Date: 2/20/14
 * Time: 9:53 PM
 */
public class Lesson
{
  private String _lessonNumber;
  private String _lessonTitle;

  public Lesson( String lessonNumber, String lessonTitle )
  {
    _lessonNumber = lessonNumber;
    _lessonTitle = lessonTitle;
  }

  public String getLessonNumber()
  {
    return _lessonNumber;
  }

  public String getLessonTitle()
  {
    return _lessonTitle;
  }
}
