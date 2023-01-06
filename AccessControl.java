//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Access Control 
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

import java.util.ArrayList;

/**
 * In this class, the methods for access control of admins and non admins are contained
 *
 * @author sreya sarathy 
 *  
 */

public class AccessControl {

  private static ArrayList<User> users = new ArrayList<User>();
  private User currentUser;
  private static final String DEFAULT_PASSWORD = "changeme";

  /**
   * the static and non static fields are checked and intialized in this particular area 
   *
   */
  
  public AccessControl() {
    if (users.size() == 0) {
      users.add(new User("admin", "root", true));
    }
    currentUser = null;
    
  } // A no-argument constructor

  /**
   * If the username/password pair is a valid login then it is reported 
   *
   * @param username the username to be verified
   * @param password the password to be verified
   * @return true if both the password and the username are valid, otherwise false is returned 
   *
   */
  
  public static boolean isValidLogin(String username, String password) {
    for (int i = 0; i < users.size(); i++) {
      if (username.equals(users.get(i).getUsername()) && users.get(i).isValidLogin(password)) {
        return true;
      }
    }
    return false;
  }

  /**
   * the password of the current user is changed
   *
   * @param newPassword is the new password that has to be set for the current user 
   *
   *
   */
  
  public void changePassword(String newPassword) {
    currentUser.setPassword(newPassword);
  }

  /**
   * the current user is logged out 
   *
   */
  
  public void logout() {
    currentUser = null;
  }

  /**
   * The current user is set to the user from the users list. Their usernames match the string provided 
   * as input to the method is match case sensitive 
   *
   * @param username name of the user who is to be set to current user
   *
   */
  
  public void setCurrentUser(String username) {
    for (int i = 0; i < users.size(); i++) {
      if (username.equals(users.get(i).getUsername())) {
        currentUser = users.get(i);
      }
    }
  }

  /**
   * A new user with a default password and isAdmin as false are created. 
   * It adds to the users ArrayList 
   *
   * @param username is the name of the user which is to be added 
   * @return true is returned if the current user has Admin power and the new user was successfully added 
   *		 otherwise false is returned if the current user is null or does not have any admin power 
   * 
   * @throws an IllegalArgumentException with a descriptive error message if username is null or if
   *            its length is less than 5, or if a user with the same username is already in the
   *            list of users
   *
   */
  
  public boolean addUser(String username) {
    if (username == "" || username == null) {
      throw new IllegalArgumentException("username cannot be null or blank");
    } else if (username.length() < 5) {
      throw new IllegalArgumentException("username cannot be less than 5 characters");
    } else if (currentUser == null || currentUser.getIsAdmin() == false) {
      return false;
    }

    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getUsername().equals(username)) {
        throw new IllegalArgumentException("username already exists, username must be unique");
      }
    }
    users.add(new User(username, DEFAULT_PASSWORD, false));
    return true;
  }

  /**
   * A new user with the default password and specific status are created. This is then added to the users ArrayList 
   *
   * @param username name of the user to be added
   * @param isAdmin  admin status of the user to be added
   * @return true if the current user has Admin power and the new user was successfully added or
   *         false if the the current user is null or does not have Admin power
   * @throws an IllegalArgumentException with a descriptive error message if username is null or if
   *            its length is less than 5, or if a user with the same username is already in the
   *            list of users
   *
   */
  public boolean addUser(String username, boolean isAdmin) {
    if (username == "" || username == null) {
      throw new IllegalArgumentException("username cannot be null or blank");
    } else if (username.length() < 5) {
      throw new IllegalArgumentException("username cannot be less than 5 characters");
    } else if (currentUser == null || currentUser.getIsAdmin() == false) {
      return false;
    }

    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getUsername().equals(username)) {
        throw new IllegalArgumentException("username already exists, username must be unique");
      }
    }
    users.add(new User(username, DEFAULT_PASSWORD, isAdmin));
    return true;
  }

  /**
   * The user is removed given their unique username 
   *
   * @param username name of the user to be removed
   * @return true if the current user has Admin powers and the user who had the username passed as
   *         input is successfully removed or false if the the current user is null or does not
   *         have Admin power
   * @throws NoSuchElementException with a descriptive error message if no match with username is
   *                                found in the list of users
   *
   */
  
  public boolean removeUser(String username) {
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getUsername().equals(username)) {
        if (currentUser == null || currentUser.getIsAdmin() == false) {
          return false;
        } else {
          users.remove(i);
          return true;
        }
      }
    }
    throw new IllegalArgumentException("username does not exist");
  }

  /**
   * Gives a user admin power
   *
   * @param username name of the user who is to be given admin power
   * @return true if this operation terminates successfully or false if the current user is null or
   *         does not have admin powers
   * @throws NoSuchElementException with a descriptive error message if no match with username is
   *                                found in the list of users
   *
   */
  public boolean giveAdmin(String username) {
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getUsername().equals(username)) {
        if (currentUser == null || currentUser.getIsAdmin() == false) {
          return false;
        } else {
          users.get(i).setIsAdmin(true);;
          return true;
        }
      }
    }
    throw new IllegalArgumentException("username does not exist");
  }

  /**
   * The admin power of a user is removed if their username is given 
   *
   * @param username name of the user who needs their admin power to be taken away
   * @return true if the operation is terminated successfully otherwise false if the current user is null or does not have any admin powers 
   * 
   * @throws NoSuchElementException with a descriptive error message if no match with username is
   *                                found in the list of users
   *
   */
  
  public boolean takeAdmin(String username) {
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getUsername().equals(username)) {
        if (currentUser == null || currentUser.getIsAdmin() == false) {
          return false;
        } else {
          users.get(i).setIsAdmin(false);;
          return true;
        }
      }
    }
    throw new IllegalArgumentException("username does not exist");
  }

  /**
   * The password of a user is reset but only if their username is given 
   * @param username name of the user whose password is to be reset
   * @return true if this operation terminates successfully or false if the current user is null or
   *         does not have admin powers
   * @throws NoSuchElementException with a descriptive error message if no match with username is
   *                                found in the list of users
   */
  
  public boolean resetPassword(String username) {
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getUsername().equals(username)) {
        if (currentUser == null || currentUser.getIsAdmin() == false) {
          return false;
        } else {
          users.get(i).setPassword(DEFAULT_PASSWORD);
          return true;
        }
      }
    }
    throw new IllegalArgumentException("username does not exist");
  }

  /**
   * The main method of the program is present here 
   *
   * @param args these are the input arguments if there are any
   */
  
  public static void main(String[] args) {

  }
}