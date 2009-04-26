package gwtscheduler.client.modules;

import gwtscheduler.client.modules.views.IUIRegistry;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(UIModule.class)
public interface IUIInjector extends Ginjector {

	/**
	 * @return
	 */
	IUIRegistry getUIRegistry();

}
