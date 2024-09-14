package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * Unit tests for the {@code Department} class.
 *
 * <p>This class contains unit tests to verify the functionality of the {@code Department} class.
 */
@SpringBootTest
@ContextConfiguration
public class DepartmentUnitTests {

  /** Sets up a Department for testing. */
  @BeforeAll
  public static void setupDepartmentForTesting() {
    IndividualProjectApplication application = new IndividualProjectApplication();
    application.resetDataFile();

    HashMap<String, Department> departments =
            IndividualProjectApplication.myFileDatabase.getDepartmentMapping();
    testDepartment = departments.get("COMS");
  }

  @Test
  public void getNumberofMajorsTest() {
    assertEquals(2700, testDepartment.getNumberOfMajors(), "COMS should have 2700 majors.");
  }

  @Test
  public void getDepartmentChairTest() {
    assertEquals("Luca Carloni", testDepartment.getDepartmentChair(),
            "Department chair should be Luca Carloni.");
  }

  @Test
  public void addPersonToMajorTest() {
    testDepartment.addPersonToMajor();
    assertEquals(2701, testDepartment.getNumberOfMajors(),
            "Number of COMS majors should increase to 2701.");
    testDepartment.dropPersonFromMajor(); // Resetting to original configuration.
  }

  @Test
  public void dropPersonFromMajorPositiveMajorsTest() {
    testDepartment.dropPersonFromMajor();
    assertEquals(2699, testDepartment.getNumberOfMajors());
    testDepartment.addPersonToMajor();
  }

  @Test
  public void dropPersonFromMajorZeroMajorsTest() {
    testDepartment2 = new Department("COMS", new HashMap<>(), "John Doe", 0);
    testDepartment2.dropPersonFromMajor();
    assertEquals(0, testDepartment2.getNumberOfMajors());
  }


  /** The test department instance used for testing. */
  public static Department testDepartment;
  public static Department testDepartment2;
}
