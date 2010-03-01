package gwtscheduler.client.widgets.view.columns;

import com.google.inject.Inject;
import com.google.inject.Provider;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.annotation.ColumnView;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.widgets.view.dayweek.AbstractDaysView;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class MultiColumnsViewProvider implements Provider<MultiColumnsView> {

  private AppConfiguration cfg;
  @ColumnView AbstractDaysView display;

  @Inject
  public MultiColumnsViewProvider(AppConfiguration cfg) {
    this.cfg = cfg;
  }





  @Override
  public MultiColumnsView get() {
    return new MultiColumnsView();
  }
}