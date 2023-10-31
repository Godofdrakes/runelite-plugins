package com.CustomMarkers.View;

import com.PluginRx.IPluginSchedulers;
import com.PluginUI.SearchBarRx;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.ui.components.DragAndDropReorderPane;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MarkerConfigListView
	extends JPanel
{
	public MarkerConfigListView(
		IPluginSchedulers schedulers )
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

		searchBar.text()
			.throttleLatest( 100, TimeUnit.MILLISECONDS )
			.observeOn( schedulers.guiThread() )
			.subscribe( this::filter );
	}

	public void filter( String text )
	{
		log.debug( "Filter: {}", text );
	}
}
