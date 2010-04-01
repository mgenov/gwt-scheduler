package gwtscheduler.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTestsTestSuite extends TestSuite {
  /**
   * Default constructor.
   * @param name the name
   */
  public AllTestsTestSuite(String name) {
    super(name);
  }

  /**
   * Creates the test suite
   * @return the test
   */
  public static Test suite() {
    TestSuite suite = new TestSuite();
    suite.addTest(new GwtTestsTestSuite(GwtTestsTestSuite.class.getName()));
    suite.addTest(new NonGwtTestsTestSuite(NonGwtTestsTestSuite.class.getName()));
    return suite;
  }

  //runs the tests
  public static void main(String args[]) {
    String[] testCaseName = {AllTestsTestSuite.class.getName()};
    junit.textui.TestRunner.main(testCaseName);
  }
}
