package com.CustomMarkers;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class CustomMarkersPluginTest
{
	public static void main( String[] args ) throws Exception
	{
		ExternalPluginManager.loadBuiltin( CustomMarkersPlugin.class );
		RuneLite.main( args );
	}
}