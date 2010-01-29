package gwtscheduler.tests;

import gwtscheduler.tests.gwt.date.DateFactoryTests;
import gwtscheduler.tests.gwt.lasso.LassoTests;
import junit.framework.Test;
import junit.framework.TestSuite;

import com.google.gwt.junit.tools.GWTTestSuite;

/**
 * Test suite for GWT tests.
 * @author malp
 */
public class GwtTestsTestSuite extends GWTTestSuite {

  /**
   * Default constructor.
   * @param name the name
   */
  public GwtTestsTestSuite(String name) {
    super(name);
  }

  /**
   * Creates the test suite
   * @return the test
   */
  public static Test suite() {
    TestSuite suite = new TestSuite();
    suite.addTest(new DateFactoryTests());
    suite.addTest(new LassoTests());
    return suite;
  }

  //runs the tests
  public static void main(String args[]) {
    String[] testCaseName = {GwtTestsTestSuite.class.getName()};
    junit.textui.TestRunner.main(testCaseName);
  }

}
