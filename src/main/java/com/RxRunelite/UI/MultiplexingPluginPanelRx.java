package com.RxRunelite.UI;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import net.runelite.client.ui.MultiplexingPluginPanel;
import net.runelite.client.ui.PluginPanel;

public class MultiplexingPluginPanelRx
	extends MultiplexingPluginPanel
{
	private final Subject<PluginPanel> panelAdded = PublishSubject.create();
	private final Subject<PluginPanel> panelRemoved = PublishSubject.create();

	private final Subject<PluginPanel> activated = PublishSubject.create();
	private final Subject<PluginPanel> deactivated = PublishSubject.create();

	public MultiplexingPluginPanelRx( PluginPanel root )
	{
		super( root );
	}

	@Override
	protected void onAdd( PluginPanel p )
	{
		super.onAdd( p );

		panelAdded.onNext( p );
	}

	@Override
	protected void onRemove( PluginPanel p )
	{
		super.onRemove( p );

		panelRemoved.onNext( p );
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

	public Observable<PluginPanel> panelAdded()
	{
		return Observable.wrap( panelAdded );
	}

	public Observable<PluginPanel> removedAdded()
	{
		return Observable.wrap( panelRemoved );
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
