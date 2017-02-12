package com.criticalgnome.payments.beans;

/**
 * @author CriticalGnome
 * Account, available for Payment
 */
public class AvailableForPayment {
	private int id;
	private int number;
	private String firstName;
	private String lastName;
	
	public static class Builder {
		private int id;
		private int number;
		private String firstName;
		private String lastName;
		
		public Builder id(int id) {
			this.id = id;
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
		
		public AvailableForPayment build() {
			return new AvailableForPayment(this);
		}
		
	}
	
	private AvailableForPayment(Builder builder) {
		id = builder.id;
		number = builder.number;
		firstName = builder.firstName;
		lastName = builder.lastName;
	}

	/**
	 * AvailableForPayment constructor
	 * @param id
	 * @param number
	 * @param firstName
	 * @param lastName
	 */
	public AvailableForPayment(int id, int number, String firstName, String lastName) {
		super();
		this.id = id;
		this.number = number;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	/**
	 * AvailableForPayment constructor without parameters
	 */
	public AvailableForPayment() {

	}

	@Override
	public String toString() {
		return "AvailableForPayment [id=" + id + ", number=" + number + ", firstName=" + firstName + ", lastName="
				+ lastName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
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
		AvailableForPayment other = (AvailableForPayment) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
}
