package com.RxRunelite.UI;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import net.runelite.client.ui.PluginPanel;

public class PluginPanelRx
	extends PluginPanel
{
	private final Subject<PluginPanel> activated = PublishSubject.create();
	private final Subject<PluginPanel> deactivated = PublishSubject.create();

	protected PluginPanelRx()
	{
		super();
	}

	protected PluginPanelRx( boolean wrap )
	{
		super( wrap );
	}

	@Override
	public void onActivate()
	{
		super.onActivate();

		activated.onNext( this );
	}

	@Override
	public void onDeactivate()
	{
		super.onDeactivate();

		deactivated.onNext( this );
	}

	public Observable<PluginPanel> activated()
	{
		return Observable.wrap( activated );
	}

	public Observable<PluginPanel> deactivated()
	{
		return Observable.wrap( deactivated );
	}
}
