//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Tester cases for Access Control 
// Course: Spring 2022 CS 300 
//
// Author: Sreya Sarathy 
// Email: sarathy2@wisc.edu
// Lecturer: Professor Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class contains tester methods to check the functionality of the methods in AccessControl and
 * User class
 *
 * @author sreya sarathy 
 *
 */
public class AccessControlTester {

  /**
   * The correctness of the constructor is checked and all the accesor and mutator methods are defined in the User class 
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
	
  public static boolean testUserConstructorAndMethods() {
    User user = new User("JoeBiden", "A1234", true);
    if (!user.isValidLogin("A1234") || user.isValidLogin("B1234")) {
      System.out.println("user.isValidLogin() method does not work as its supposed to");
      return false;
    }
    if (!user.getUsername().equals("JoeBiden") || user.getUsername().equals("joebiden")) {
      System.out.println("user.getUsername() method does not work as its supposed to");
      return false;
    }
    if (!user.getIsAdmin()) {
      System.out.println("user.getIsAdmin() method does not work as its supposed to");
      return false;
    }
    user.setPassword("ABCD1");
    if (!user.isValidLogin("ABCD1") || user.isValidLogin("A1234")) {
      System.out.println("user.setPassword() method does not work as its supposed to");
      return false;
    }
    user.setIsAdmin(false);
    if (user.getIsAdmin()) {
      System.out.println("user.setIsAdmin() method does not work as its supposed to");
      return false;
    }
    return true;
  }

  /**
   * The correctness of AccessControl.isValidLogin() method is checked when called with 
   * incorrect username or not matching (username, password) pair 
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  
  public static boolean testAccessControlIsValidLoginNotValidUser() {
    AccessControl newAccessControl = new AccessControl();
    newAccessControl.setCurrentUser("admin");
    if (!newAccessControl.addUser("JoeBiden", true)) {
      System.out.println("Failed to add new user");
      return false;
    }
    newAccessControl.setCurrentUser("JoeBiden");
    newAccessControl.changePassword("presidentofUSA");
    if (!newAccessControl.addUser("BarackObama")) {
      System.out.println("Failed to add new user");
      return false;
    }

    if (newAccessControl.isValidLogin("joebiden", "presidentofUSA")
        || newAccessControl.isValidLogin("BarackObama", "presidentofUSA")
        || !newAccessControl.isValidLogin("JoeBiden", "presidentofUSA")) {
      System.out.println("AccessControl.isValidLogin() method does not work as its supposed to");
      return false;
    }
    return true;
  }

  /**
   * A new AccessControl object is created but it does not log in an admin. 
   * This test must fail if addUser (String username) does not return false 
   * or if a user was added to the list of users after the method returns 
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  
  public static boolean testAddUserWithNoAdminPowers() {
    AccessControl newAccessControl = new AccessControl();
    newAccessControl.setCurrentUser("BarackObama");
    if (newAccessControl.addUser("DonaldTrump", true)) {
      System.out.println("AccessControl.addUser() method does not work as its supposed to");
      return false;
    }
    if (newAccessControl.addUser("BillClinton")) {
      System.out.println("AccessControl.addUser() method does not work as its supposed to");
      return false;
    }
    return true;
  }

  /**
   * The correctness of addUser and removeUser methods are checked if the current user has admin powers
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  
  public static boolean testAddRemoveUserWithAdminPowers() {
    AccessControl newAccessControl = new AccessControl();
    newAccessControl.setCurrentUser("admin");
    if (!newAccessControl.addUser("GeorgeBush", true)) {
      System.out.println("AccessControl.addUser() method does not work as its supposed to");
      return false;
    }
    if (!newAccessControl.addUser("JohnKennedy")) {
      System.out.println("AccessControl.addUser() method does not work as its supposed to");
      return false;
    }
    if (!newAccessControl.removeUser("GeorgeBush")) {
      System.out.println("AccessControl.removeUser() method does not work as its supposed to");
      return false;
    }
    if (!newAccessControl.removeUser("JohnKennedy")) {
      System.out.println("AccessControl.removeUser() method does not work as its supposed to");
      return false;
    }
    return true;
  }

  /** 
   * this tester runs all the other tester methods defined in this tester class.
   *
   * @return true when this tester verifies a correct functionality, and false otherwise
   */
  
  public static boolean runAllTests() {
    try {
      if (!testUserConstructorAndMethods()) {
        return false;
      }
      if (!testAccessControlIsValidLoginNotValidUser()) {
        return false;
      }
      if (!testAddUserWithNoAdminPowers()) {
        return false;
      }
      if (!testAddRemoveUserWithAdminPowers()) {
        return false;
      }
    } catch (Exception e) {
      System.out.println("Unexpected Exception Thrown" + e);
      return false;
    }
    return true;
  }

  /**
   * runAllTests() is run in this main method 
   *
   * @param args input arguments if any
   */
  
  public static void main(String[] args) {
    System.out.print(runAllTests());
  }
}