package com.CustomMarkers.View;

import com.RxRunelite.IPluginSchedulers;
import com.PluginUI.SearchBarRx;
import hu.akarnokd.rxjava3.swing.SwingSchedulers;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.ui.components.DragAndDropReorderPane;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MarkerConfigListView
	extends JPanel
{
	public MarkerConfigListView()
	{
		this.setLayout( new BorderLayout() );

		final var scrollPane = new JScrollPane(
			ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
		);

		final var searchBar = new SearchBarRx();

		scrollPane.setColumnHeaderView( searchBar );

		final var itemsPane = new DragAndDropReorderPane();

		for (int index = 0; index < 100; ++index)
		{
			itemsPane.add( new JLabel( "Hello World!" ) );
		}

		scrollPane.setViewportView( itemsPane );

		this.add( scrollPane );

		searchBar.getText()
			.throttleLatest( 100, TimeUnit.MILLISECONDS )
			// Throttle runs on a worker thread. Bring it back to the UI thread.
			.observeOn( SwingSchedulers.edt() )
			.subscribe( this::filter );
	}

	public void filter( String text )
	{
		log.debug( "Filter: {}", text );
	}
}
