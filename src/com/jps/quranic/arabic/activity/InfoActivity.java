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
                            + " point value=" + seriesSelection.getValue(), Toast.LENGTH_SHORT ).show();
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

    SimpleSeriesRenderer rendererLightGray = new SimpleSeriesRenderer();
    rendererLightGray.setColor( Color.LTGRAY );

    SimpleSeriesRenderer rendererGray = new SimpleSeriesRenderer();
    rendererGray.setColor( Color.GRAY );

    SimpleSeriesRenderer rendererDarkGray = new SimpleSeriesRenderer();
    rendererDarkGray.setColor( Color.DKGRAY );

    mSeries.add( "Lesson 1", 100 );
    mRenderer.addSeriesRenderer( rendererLightGray );

    mSeries.add( "Lesson 2", 100 );
    mRenderer.addSeriesRenderer( rendererGray );

    mSeries.add( "Lesson 3", 100 );
    mRenderer.addSeriesRenderer( rendererDarkGray );

    mChartView.repaint();
  }
}
