package com.PluginUI;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.Subject;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.components.IconTextField;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SearchBarRx
	extends IconTextField
{
	private final Subject<String> text = BehaviorSubject.createDefault( "" );

	public SearchBarRx()
	{
		super();

		this.setBackground( ColorScheme.DARKER_GRAY_COLOR );
		this.setHoverBackgroundColor( ColorScheme.DARKER_GRAY_HOVER_COLOR );

		final var listener = new DocumentListener()
		{
			@Override
			public void insertUpdate( DocumentEvent e )
			{
				text.onNext( getText() );
			}

			@Override
			public void removeUpdate( DocumentEvent e )
			{
				text.onNext( getText() );
			}

			@Override
			public void changedUpdate( DocumentEvent e )
			{
				text.onNext( getText() );
			}
		};

		this.getDocument().addDocumentListener( listener );
	}

	public Observable<String> text()
	{
		return Observable.wrap( text );
	}
}
