package com.fivesquare.koda.fivesquare.Models;

/**
 * Checkin class hold the data of user checkin 
 * it construct and add the information of the checkin
 * @author Menna
 * @since 24-12-2015
 * @version 1.0
 */
public class CheckIn {
	String text;
	String time;
	int ID;
	int userID;
	int placeID;
    float rate ;

    public CheckIn(){
        text="";
        time="";
        ID =-1;
        userID=-1;
        placeID=-1;
        rate=-1;


    }

	/**
	 * Constructor that take copy the data in check in
	 * @param text . the feedback on the checkin .
	 * @param time . it indicate the time that the check in take place.
	 * @param iD . the check in id.
	 * @param userID . the user that make the checkin id.
	 * @param placeID . the id of the place at which the check takeplace.
	 */
	public CheckIn(String text, String time, int iD, int userID, int placeID) {
		this.text = text;
		this.time = time;
		ID = iD;
		this.userID = userID;
		this.placeID = placeID;
	}

	/**
	 * Return a string value indicate the feedback of the user
	 * or comment of the checkin.
	 * @return text. String contain the feedback of the user.
	 */
	public String getText() {
		return text;
	}

	/**
	 *Set the string of the user.
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Return a string value of user 
	 * represent the time of the user check in.
	 * @return time.time that the check in take place. 
	 */
	public String getTime() {
		return time;
	}

	/**
	 * String set the time value.
	 * @param time . time that the check in take place.
	 */
	public void setTime(String time) {
		this.time = time;
	}

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
	/**
	 * Return a unique positive value.
	 * @return ID. positive integer value represent the checkin.
	 */
	public int getID() {
		return ID;
	}

	/**
	 * set the ID of the user.
	 * @param iD . unique positive value represent the checkin
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * Return the user id. 
	 * @return id. positive unique integer represent the checkin.
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * Set user ID.
	 * @param userID . unique positive value used to describe the user information.
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * Return a unique integer postive value.that identify the place.
	 * @return placeID. a positive int that describe the place information.
	 */
	public int getPlaceID() {
		return placeID;
	}

	/**
	 *Set place ID. 
	 *@param placeID . a unique positive value that represent the data of the place.
	 */
	public void setPlaceID(int placeID) {
		this.placeID = placeID;
	}

}
