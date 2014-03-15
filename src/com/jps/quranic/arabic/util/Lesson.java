package com.jps.quranic.arabic.util;

/**
 * User: shah
 * Date: 2/20/14
 * Time: 9:53 PM
 */
public class Lesson
{
  private int _stringArrayId; // R.array.lesson_1
  private String _lessonNumber; // Lesson 1
  private String _lessonTitle;

  public Lesson( int stringArrayId, String lessonNumber, String lessonTitle )
  {
    _stringArrayId = stringArrayId;
    _lessonNumber = lessonNumber;
    _lessonTitle = lessonTitle;
  }

  public int getStringArrayId()
  {
    return _stringArrayId;
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
