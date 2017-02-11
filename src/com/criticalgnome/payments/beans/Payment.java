package com.criticalgnome.payments.beans;

/**
 * @author CriticalGnome
 * Payment
 *
 */
public class Payment {
	private String dateTime;
	private int number;
	private String firstName;
	private String lastName;
	private int amount;
	private String Comment;
	private int accountID;
	
	public static class Builder {
		private String dateTime;
		private int number;
		private String firstName;
		private String lastName;
		private int amount;
		private String Comment;
		private int accountID;
		
		public Builder dateTime(String dateTime) {
			this.dateTime = dateTime;
			return this;
		}
		
		public Builder number(int number) {
			this.number = number;
			return this;
		}
		
		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		public Builder amount(int amount) {
			this.amount = amount;
			return this;
		}
		
		public Builder comment(String Comment) {
			this.Comment = Comment;
			return this;
		}
		
		public Builder accountID(int accountID) {
			this.accountID = accountID;
			return this;
		}
		
		public Payment build() {
			return new Payment(this);
		}
	}
	
	private Payment(Builder builder) {
		dateTime = builder.dateTime;
		number = builder.number;
		firstName = builder.firstName;
		lastName = builder.lastName;
		amount = builder.amount;
		Comment = builder.Comment;
		accountID = builder.accountID;
	}

	/**
	 * Payment constructor
	 * @param dateTime
	 * @param number
	 * @param firstName
	 * @param lastName
	 * @param amount
	 * @param comment
	 * @param accountID
	 */
	public Payment(String dateTime, int number, String firstName, String lastName, int amount, String comment,
			int accountID) {
		super();
		this.dateTime = dateTime;
		this.number = number;
		this.firstName = firstName;
		this.lastName = lastName;
		this.amount = amount;
		Comment = comment;
		this.accountID = accountID;
	}
	
	/**
	 * Payment constructor without parameters
	 */
	public Payment() {

	}

	@Override
	public String toString() {
		return "Payment [dateTime=" + dateTime + ", number=" + number + ", firstName=" + firstName + ", lastName="
				+ lastName + ", amount=" + amount + ", Comment=" + Comment + ", accountID=" + accountID + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Comment == null) ? 0 : Comment.hashCode());
		result = prime * result + accountID;
		result = prime * result + amount;
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + number;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		if (Comment == null) {
			if (other.Comment != null)
				return false;
		} else if (!Comment.equals(other.Comment))
			return false;
		if (accountID != other.accountID)
			return false;
		if (amount != other.amount)
			return false;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (number != other.number)
			return false;
		return true;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		Comment = comment;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	
}
