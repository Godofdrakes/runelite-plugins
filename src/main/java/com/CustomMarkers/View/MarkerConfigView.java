package com.CustomMarkers.View;

import com.PluginRx.IPluginSchedulers;
import com.PluginUI.MultiplexingPluginPanelRx;
import com.google.inject.Inject;
import net.runelite.client.ui.PluginPanel;

import javax.swing.*;
import java.awt.*;

public class MarkerConfigView
	extends PluginPanel
{
	private final MultiplexingPluginPanelRx mux = new MultiplexingPluginPanelRx( this );

	private final MarkerConfigListView listView;

	@Inject
	public MarkerConfigView( IPluginSchedulers schedulers )
	{
		super(false);

		listView = new MarkerConfigListView( schedulers );
	}

	@Override
	public void onActivate()
	{
		this.removeAll();

		final var titleLabel = new JLabel("Custom Markers");

		titleLabel.setFont( titleLabel.getFont().deriveFont( Font.BOLD ) );
		titleLabel.setHorizontalAlignment( JLabel.CENTER );
		titleLabel.setForeground( Color.WHITE );

		final var titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 3));

		titlePanel.add( titleLabel );

		final var topPanel = new JPanel(new BorderLayout());

		topPanel.add( titlePanel );

		this.add( topPanel, BorderLayout.NORTH );
		this.add( listView, BorderLayout.CENTER );
	}
}
