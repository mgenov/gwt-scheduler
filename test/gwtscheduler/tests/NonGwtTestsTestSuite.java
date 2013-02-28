package gwtscheduler.tests;

import gwtscheduler.tests.gwt.date.DateFactoryTests;
import gwtscheduler.tests.lasso.GenericLassoSelectionTests;
import gwtscheduler.tests.lasso.HorizontalLassoSelectionTests;
import gwtscheduler.tests.lasso.VerticalLassoSelectionTests;
import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Test suite for non-gwt tests, ie, tests that do not require a module.
 * @author malp
 */
public class NonGwtTestsTestSuite extends TestSuite {

  /**
   * Default constructor.
   * @param name the name
   */
  public NonGwtTestsTestSuite(String name) {
    super(name);
  }

  /**
   * Creates the test suite
   * @return the test
   */
  public static Test suite() {
    TestSuite suite = new TestSuite();
    suite.addTest(new JUnit4TestAdapter(GenericLassoSelectionTests.class));
    suite.addTest(new JUnit4TestAdapter(HorizontalLassoSelectionTests.class));
    suite.addTest(new JUnit4TestAdapter(VerticalLassoSelectionTests.class));
    suite.addTest(new DateFactoryTests());
    return suite;
  }

  //runs the tests
  public static void main(String args[]) {
    String[] testCaseName = {NonGwtTestsTestSuite.class.getName()};
    junit.textui.TestRunner.main(testCaseName);
  }
}
