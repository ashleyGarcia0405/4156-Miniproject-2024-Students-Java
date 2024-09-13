package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * Unit tests for the {@code Course} class.
 *
 * <p>This class contains unit tests to verify the functionality of the {@code Course} class.
 */
@SpringBootTest
@ContextConfiguration
public class CourseUnitTests {

  @BeforeAll
  public static void setupCourseForTesting() {
    testCourse = new Course("Griffin Newbold", "417 IAB", "11:40-12:55", 250);
  }


  @Test
  public void toStringTest() {
    String expectedResult = "\nInstructor: Griffin Newbold; Location: 417 IAB; Time: 11:40-12:55";
    assertEquals(expectedResult, testCourse.toString());
  }

  @Test
  public void enrollStudentTest() {
    testCourse.setEnrolledStudentCount(100);
    boolean enrollSuccess = testCourse.enrollStudent();
    assertTrue(enrollSuccess, "Enrollment should succeed when capacity is not full.");
  }

  @Test
  public void dropsStudentTest() {
    testCourse.setEnrolledStudentCount(100);
    boolean dropSuccess = testCourse.dropStudent();
    assertTrue(dropSuccess, "Dropping should succeed when there's at least one student enrolled.");
  }

  @Test
  public void dropStudentWhenNoneEnrolledTest() {
    testCourse.setEnrolledStudentCount(0);
    boolean dropSuccess = testCourse.dropStudent();
    assertFalse(dropSuccess, "Dropping should fail with zero enrolled students.");
  }

  @Test
  public void getCourseLocationTest() {
    String expectedResult = "417 IAB";
    assertEquals(expectedResult, testCourse.getCourseLocation());
  }

  @Test
  public void getInstructorNameTest() {
    String expectedResult = "Griffin Newbold";
    assertEquals(expectedResult, testCourse.getInstructorName());
  }

  @Test
  public void getCourseTimeSlotTest() {
    String expectedResult = "11:40-12:55";
    assertEquals(expectedResult, testCourse.getCourseTimeSlot());
  }

  @Test
  public void isCourseFullTest() {
    testCourse.setEnrolledStudentCount(250);
    assertTrue(testCourse.isCourseFull(), "Course should be full.");
  }

  @Test
  public void isCourseNotFullTest() {
    testCourse.setEnrolledStudentCount(100);
    assertFalse(testCourse.isCourseFull(), "Course should not be full.");
  }

  /** The test course instance used for testing. */
  public static Course testCourse;
}

