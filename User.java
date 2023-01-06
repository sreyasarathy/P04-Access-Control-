//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: User class
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
 * In this particular class, all the details of a user trying to login are present. 
 * It also verifies the details. 
 *
 * @author sreya sarathy 
 *
 */
public class User {
  private final String USERNAME;
  private String password;
  private boolean isAdmin;

  /**
   * A new user with the given username, password and admin status are created
   * @param username username of the user trying to login
   * @param password password of the user trying to login
   * @param isAdmin  admin status of the user trying to login
   */
  
  public User(String username, String password, boolean isAdmin) {
    this.USERNAME = username;
    this.password = password;
    this.isAdmin = isAdmin;
  }

  /**
   * It is reported if the password is correct or not. 
   *
   * @param password correct password of the user
   * @return true is returned if the password is correct and false if incorrect
   */
  
  public boolean isValidLogin(String password) {
    if (this.password.equals(password)) {
      return true;
    }
    return false;
  }

  /**
   * The name of the user is returned 
   *
   * @return the username 
   *
   */
  
  public String getUsername() {
    return this.USERNAME;
  }

  /**
   * It is reported if the user is an admin or not 
   *
   * @return if the user is an admin then true is returned and if the user isn't then false is
   *
   */
  
  public boolean getIsAdmin() {
    return this.isAdmin;
  }

  /**
   * the new password is set 
   *
   * @param password the new password of the user 
   * 
   */
  
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * the new admin status is set here 
   *
   * @param isAdmin new admin status of the user
   */
  
  public void setIsAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }
}