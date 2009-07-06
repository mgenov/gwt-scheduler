package gwtscheduler.client.benchmark;

import gwtscheduler.client.widgets.view.month.CompositeMonthPanel;

import org.junit.Test;

import com.google.gwt.benchmarks.client.Benchmark;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Bencharks widget creation.
 * @author malp
 */
public class WidgetCreationBenchmark extends Benchmark {

  @Override
  public String getModuleName() {
    return "gwtscheduler.Tests";
  }

  /**
   * Tests month widgets creation and attachment.
   */
  @Test
  public void testMonthWidgetsCreation() {
    CompositeMonthPanel cmp = new CompositeMonthPanel();
    RootPanel.get().add(cmp);
  }

}
