package com.RxRunelite.UI;

import hu.akarnokd.rxjava3.swing.SwingObservable;
import hu.akarnokd.rxjava3.swing.SwingSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.Subject;
import lombok.Getter;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.components.IconTextField;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@Getter
public class SearchBarRx
	extends IconTextField
{
	private final Observable<String> text;

	public SearchBarRx()
	{
		super();

		this.setBackground( ColorScheme.DARKER_GRAY_COLOR );
		this.setHoverBackgroundColor( ColorScheme.DARKER_GRAY_HOVER_COLOR );

		text = SwingObservable
			.document( this.getDocument() )
			.map( event ->
			{
				var document = event.getDocument();
				return document.getText( 0, document.getLength() );
			});
	}
}
