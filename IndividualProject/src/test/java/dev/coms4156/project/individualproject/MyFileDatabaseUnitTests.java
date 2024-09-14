package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Unit tests for the {@code MyFileDatabase} class.
 *
 * <p>This class contains unit tests to verify the functionality of the
 * {@code MyFileDatabase} class.
 */
public class MyFileDatabaseUnitTests {

  public MyFileDatabase fileDatabase;
  private final String testFilePath = "test_database.ser";

  /** Create a mock department mapping and serializes test data to file for testing. */
  @BeforeEach
  public void setup() {
    HashMap<String, Department> testMapping = new HashMap<>();
    Department testDepartment = Mockito.mock(Department.class);
    testMapping.put("Engineering", testDepartment);

    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(testFilePath))) {
      out.writeObject(testMapping);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testConstructorWithDeserialization() {
    fileDatabase = new MyFileDatabase(0, testFilePath);
    HashMap<String, Department> deserializedMapping = fileDatabase.getDepartmentMapping();
    assertNotNull(deserializedMapping);
    assertTrue(deserializedMapping.containsKey("Engineering"));
  }

  @Test
  public void testSetAndGetMapping() {
    fileDatabase = new MyFileDatabase(1, testFilePath);
    HashMap<String, Department> newMapping = new HashMap<>();
    Department newDept = Mockito.mock(Department.class);
    newMapping.put("Science", newDept);

    fileDatabase.setMapping(newMapping);
    HashMap<String, Department> retrievedMapping = fileDatabase.getDepartmentMapping();

    assertEquals(1, retrievedMapping.size());
    assertTrue(retrievedMapping.containsKey("Science"));
  }

  @Test
  public void testSaveContentsToFile() {
    fileDatabase = new MyFileDatabase(1, testFilePath);
    HashMap<String, Department> newMapping = new HashMap<>();
    Department newDept = Mockito.mock(Department.class);
    newMapping.put("Science", newDept);

    fileDatabase.setMapping(newMapping);
    fileDatabase.saveContentsToFile();

    HashMap<String, Department> deserializedMapping;
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(testFilePath))) {
      deserializedMapping = (HashMap<String, Department>) in.readObject();
    } catch (IOException | ClassNotFoundException e) {
      deserializedMapping = null;
      e.printStackTrace();
    }

    assertNotNull(deserializedMapping);
    assertTrue(deserializedMapping.containsKey("Science"));
  }

  @Test
  public void testToString() {
    fileDatabase = new MyFileDatabase(0, testFilePath);
    Department department = Mockito.mock(Department.class);
    Mockito.when(department.toString()).thenReturn("This is the Engineering Department");

    HashMap<String, Department> mapping = new HashMap<>();
    mapping.put("Engineering", department);
    fileDatabase.setMapping(mapping);

    String expectedString = "For the Engineering department: \nThis is the Engineering Department";
    assertEquals(expectedString, fileDatabase.toString());
  }
}
