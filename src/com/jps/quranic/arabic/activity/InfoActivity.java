package com.jps.quranic.arabic.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.SeriesSelection;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import com.jps.quranic.arabic.R;

/**
 * User: shah
 * Date: 4/8/14
 * Time: 11:58 AM
 */
public class InfoActivity extends Activity
{
  /** The main series that will include all the data. */
  private CategorySeries mSeries = new CategorySeries( "" );
  /** The main renderer for the main dataset. */
  private DefaultRenderer mRenderer = new DefaultRenderer();
  /** The chart view that displays the data. */
  private GraphicalView mChartView;

  @Override
  protected void onRestoreInstanceState( Bundle savedState )
  {
    super.onRestoreInstanceState( savedState );
    mSeries = (CategorySeries) savedState.getSerializable( "current_series" );
    mRenderer = (DefaultRenderer) savedState.getSerializable( "current_renderer" );
  }

  @Override
  protected void onSaveInstanceState( Bundle outState )
  {
    super.onSaveInstanceState( outState );
    outState.putSerializable( "current_series", mSeries );
    outState.putSerializable( "current_renderer", mRenderer );
  }

  @Override
  protected void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.info_view );

    // set action bar title
    getActionBar().setTitle( R.string.info );

    mRenderer.setZoomButtonsVisible( true );
    mRenderer.setStartAngle( 180 );
    mRenderer.setDisplayValues( true );

    if ( mChartView == null )
    {
      LinearLayout layout = (LinearLayout) findViewById( R.id.chart );
      mChartView = ChartFactory.getPieChartView( this, mSeries, mRenderer );
      mRenderer.setClickEnabled( true );
      mChartView.setOnClickListener( new View.OnClickListener()
      {
        @Override
        public void onClick( View v )
        {
          SeriesSelection seriesSelection = mChartView.getCurrentSeriesAndPoint();
          if ( seriesSelection == null )
          {
            Toast.makeText( InfoActivity.this, "No chart element selected", Toast.LENGTH_SHORT )
              .show();
          }
          else
          {
            for ( int i = 0; i < mSeries.getItemCount(); i++ )
            {
              mRenderer.getSeriesRendererAt( i ).setHighlighted( i == seriesSelection.getPointIndex() );
            }
            mChartView.repaint();
            Toast.makeText( InfoActivity.this,
                            "Chart data point index " + seriesSelection.getPointIndex() + " selected"
                            + " point value=" + seriesSelection.getValue(), Toast.LENGTH_SHORT
            ).show();
          }
        }
      } );
      layout.addView( mChartView, new ViewGroup.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT,
                                                              ViewGroup.LayoutParams.MATCH_PARENT ) );
    }
    else
    {
      mChartView.repaint();
    }

    int[] resIdArray = new int[]{ R.array.lesson_1, R.array.lesson_3, R.array.lesson_5, R.array.lesson_6,
                                  R.array.lesson_8, R.array.lesson_9, R.array.lesson_11, R.array.lesson_12,
                                  R.array.lesson_14, R.array.lesson_16, R.array.lesson_17, R.array.lesson_18,
                                  R.array.lesson_19, R.array.lesson_20, R.array.lesson_21, R.array.lesson_22,
                                  R.array.lesson_23, R.array.lesson_24, R.array.lesson_25, R.array.lesson_26,
                                  R.array.lesson_27, R.array.lesson_28, R.array.lesson_29, R.array.lesson_30,
                                  R.array.lesson_31, R.array.lesson_32, R.array.lesson_33, R.array.lesson_34,
                                  R.array.lesson_35, R.array.lesson_36, R.array.lesson_37, R.array.lesson_38,
                                  R.array.lesson_39 };

    int allOccurrences = 0; // occurrences of words from all lectures in Qur'an Majeed
    for ( int resId : resIdArray )
    {
      String[] lesson = getResources().getStringArray( resId );
      int wordOccurrence = Integer.parseInt( lesson[3] );
      allOccurrences += wordOccurrence;
    }

    final int totalWordsQuran = 77934; // total occurrences of all words in Qur'an Majeed
    final int remainingWordsQuran = totalWordsQuran - allOccurrences;

    SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();
    renderer.setColor( Color.GRAY );
    mSeries.add( "All Lessons", allOccurrences );
    mRenderer.addSeriesRenderer( renderer );

    SimpleSeriesRenderer rendererDarkGray = new SimpleSeriesRenderer();
    rendererDarkGray.setColor( Color.DKGRAY );
    mSeries.add( "Remaining", remainingWordsQuran );
    mRenderer.addSeriesRenderer( rendererDarkGray );

    mChartView.repaint();
  }
}
