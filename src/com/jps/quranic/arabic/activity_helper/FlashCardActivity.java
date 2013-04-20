package com.jps.quranic.arabic.activity_helper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jps.quranic.arabic.R;
import com.jps.quranic.arabic.util.DialogUtil;
import com.jps.quranic.arabic.util.Util;

/**
 * User: shah
 * Date: 2/25/12
 * Time: 5:51 PM
 */
public class FlashCardActivity extends SafeActivity
{
  /** For every word, holds its meaning */
  private Map<String, String> _wordMeaningMap;

  /** Holds an array of words */
  private String[] _wordArray;

  /** Holds index of current word in _wordArray */
  private int _currentWordIndex = 0;

  private boolean _isCurrentSessionSaved = false;

  // views
  private TextView _wordView;
  private TextView _meaningView;
  private TextView _wordNumberView;

  // buttons
  private Button _previousButton;
  private Button _nextButton;

  public static String PREF_NAME = "MyPrefFile";

  // menu options
  private static final int MENU_SAVE_PROGRESS = 5;

  // extras
  public static final String EXTRA_CONTINUE_WITH_SAVED_SESSION = "start_from_saved_session";

  @Override
  public void safeOnCreate( Bundle savedInstanceState )
  {
    setContentView( R.layout.flash_card_view );

    // get extras
    Intent intent = getIntent();
    boolean doStartFromSavedProgress = intent.getBooleanExtra( EXTRA_CONTINUE_WITH_SAVED_SESSION, false );
    ArrayList<Integer> resIds = intent.getIntegerArrayListExtra( HomeActivity.EXTRA_RESOURCE_IDS );

    // initialize _wordMeaningMap with all words and their meanings from strings.xml
    _wordMeaningMap = Util.getWordMeaningMap( this, resIds );

    // _wordArray is initialized based on number of words taken from _wordMeaningMap's key set
    _wordArray = new String[_wordMeaningMap.keySet().size()];

    // start from saved progress
    if ( doStartFromSavedProgress )
    {
      retrieveSavedProgress(); // initialize _wordArray from preferences
    }
    else
    {
      clearSavedProgress();
      initializeWordList(); // initialize _wordArray from _wordMeaningMap
    }

    // view meaning button
    Button viewMeaningButton = (Button) findViewById( R.id.view_meaning_button );
    viewMeaningButton.setOnClickListener( new ViewMeaningOnClickListener() );

    // previous button
    _previousButton = (Button) findViewById( R.id.previous_button );
    _previousButton.setOnClickListener( new ShowWordOnClickListener( ShowWordOnClickListener.PREVIOUS_WORD ) );

    // next button
    _nextButton = (Button) findViewById( R.id.next_button );
    _nextButton.setOnClickListener( new ShowWordOnClickListener( ShowWordOnClickListener.NEXT_WORD ) );

    _wordNumberView = (TextView) findViewById( R.id.word_number_view );
    _wordView = (TextView) findViewById( R.id.word_view );
    _meaningView = (TextView) findViewById( R.id.meaning_view );
    _meaningView.setVisibility( View.GONE );

    displayWord();
  }

  @Override
  public void safeOnCreateOptionsMenu( Menu menu )
  {
    menu.add( 0, MENU_SAVE_PROGRESS, 0, "Save Progress" );
  }

  @Override
  public void safeOnOptionsItemSelected( MenuItem menuItem )
  {
    switch ( menuItem.getItemId() )
    {
      case MENU_SAVE_PROGRESS:
        saveProgress();
        break;
    }
  }

  @Override
  public void onBackPressed()
  {
    // prompt user to save session if it's not completed
    if ( !_isCurrentSessionSaved )
    {
      AlertDialog.Builder builder = new AlertDialog.Builder( this );
      builder.setMessage( "You haven't completed this session, do you want to save your progress before exiting?" )
        .setCancelable( false )
        .setPositiveButton( "Save", new DialogInterface.OnClickListener()
        {
          public void onClick( DialogInterface dialog, int id )
          {
            saveProgress();
            FlashCardActivity.super.onBackPressed();
          }
        } )
        .setNegativeButton( "Exit", new DialogInterface.OnClickListener()
        {
          public void onClick( DialogInterface dialog, int id )
          {
            FlashCardActivity.super.onBackPressed();
          }
        } );
      AlertDialog alert = builder.create();
      alert.show();
    }
    else
    {
      FlashCardActivity.super.onBackPressed();
    }
  }

  /** Initialize _wordArray with words from _wordMeaningMap. */
  private void initializeWordList()
  {
    Set<String> wordSet = _wordMeaningMap.keySet();

    // copy wordSet to wordList
    List<String> wordList = new ArrayList<String>();
    for ( String word : wordSet )
    {
      wordList.add( word );
    }
    Collections.shuffle( wordList );

    int index = 0;
    for ( String word : wordList )
    {
      _wordArray[index] = word;
      index++;
    }
  }

  /** Saves progress for later use. */
  private void saveProgress()
  {
    SharedPreferences settings = getSharedPreferences( PREF_NAME, 0 );
    SharedPreferences.Editor editor = settings.edit();

    // save words with their index numbers from _wordArray
    for ( int index = 0; index < _wordArray.length; index++ )
    {
      editor.putString( Integer.toString( index ), _wordArray[index] );
    }

    // save index of current word
    editor.putInt( "current word index", _currentWordIndex );

    editor.commit();

    _isCurrentSessionSaved = true;
  }

  /** Retrieves saved progress. */
  @SuppressWarnings("unchecked")
  private void retrieveSavedProgress()
  {
    // restore preferences
    SharedPreferences settings = getSharedPreferences( PREF_NAME, 0 );
    Map<String, ?> preferencesMap = settings.getAll();

    // retrieve _currentWordIndex
    _currentWordIndex = (Integer) preferencesMap.remove( "current word index" );

    // initialize _wordArray from preferences
    for ( Map.Entry<String, ?> entry : preferencesMap.entrySet() )
    {
      _wordArray[Integer.parseInt( entry.getKey() )] = entry.getValue().toString();
    }
  }

  /** Clears saved progress. */
  private void clearSavedProgress()
  {
    SharedPreferences settings = getSharedPreferences( PREF_NAME, 0 );
    SharedPreferences.Editor editor = settings.edit();
    editor.clear();
    editor.commit();
  }

  /** Displays word on flash card. */
  private void displayWord()
  {
    String word = _wordArray[_currentWordIndex];
    String meaning = _wordMeaningMap.get( word );

    _wordView.setText( word );

    _meaningView.setVisibility( View.GONE );
    _meaningView.setText( meaning );

    int currentWordNumber = _currentWordIndex + 1;
    _wordNumberView.setText( currentWordNumber + " of " + _wordArray.length );

    setButtonStates();

    _isCurrentSessionSaved = false;
  }

  /** Set previous and next button states (enabled or disabled). */
  private void setButtonStates()
  {
    // enable previous button if this is NOT the first word, disable otherwise
    _previousButton.setEnabled( !isFirstWord() );

    // enable next button if this is NOT the last word, disable otherwise
    _nextButton.setEnabled( !isLastWord() );
  }

  /**
   * Returns true if this is the first word.
   *
   * @return
   */
  private boolean isFirstWord()
  {
    return _currentWordIndex == 0;
  }

  /**
   * Returns true if this is the last word.
   *
   * @return
   */
  private boolean isLastWord()
  {

    return _currentWordIndex == _wordArray.length - 1;
  }

  private class ShowWordOnClickListener implements View.OnClickListener
  {
    // holds value PREVIOUS_WORD or NEXT_WORD
    private int _displayWord;

    public static final int PREVIOUS_WORD = 5;
    public static final int NEXT_WORD = 15;

    public ShowWordOnClickListener( int displayWord )
    {
      _displayWord = displayWord;
    }

    public void onClick( View view )
    {
      try
      {
        if ( _currentWordIndex >= 0 || _currentWordIndex < _wordArray.length )
        {
          // user clicked on previous button and this is NOT the first word, decrement _currentWordIndex
          if ( _displayWord == PREVIOUS_WORD && !isFirstWord() )
          {
            _currentWordIndex--;
          }

          // user clicked on next button and this is NOT the last word, increment _currentWordIndex
          else if ( _displayWord == NEXT_WORD && !isLastWord() )
          {
            _currentWordIndex++;
          }

          displayWord();
        }
      }
      catch ( Exception e )
      {
        Log.e( "FlashCardActivity", "ShowWordOnClickListener.onClick()", e );
        DialogUtil.showErrorDialog( FlashCardActivity.this );
      }
    }
  }

  private class ViewMeaningOnClickListener implements View.OnClickListener
  {
    public void onClick( View view )
    {
      try
      {
        _meaningView.setVisibility( View.VISIBLE );
      }
      catch ( Exception e )
      {
        Log.e( "FlashCardActivity", "ViewMeaningOnClickListener.onClick()", e );
        DialogUtil.showErrorDialog( FlashCardActivity.this );
      }
    }
  }
}
