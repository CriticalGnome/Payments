package com.criticalgnome.payments.beans;

/**
 * @author CriticalGnome
 * Bank Account
 *
 */
public class BlockedAccount {
	private int id;
	private int number;
	private long amount;
	private String firstName;
	private String lastName;
	private String role;
	
	/**
	 * Pattern Builder
	 */
	public static class Builder {
		private int id;
		private int number;
		private long amount;
		private String firstName;
		private String lastName;
		private String role;

		public Builder id(int id) {
			this.id = id;
			return this;
		}

		public Builder number(int number) {
			this.number = number;
			return this;
		}

		public Builder amount(long amount) {
			this.amount = amount;
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

		public Builder role(String role) {
			this.role = role;
			return this;
		}

		public BlockedAccount build() {
			return new BlockedAccount(this);
		}
	}

	private BlockedAccount(Builder builder) {
		id = builder.id;
		number = builder.number;
		amount = builder.amount;
		firstName = builder.firstName;
		lastName = builder.lastName;
		role = builder.role;
	}

	/**
	 * Account constructor
	 * @param id
	 * @param number
	 * @param amount
	 * @param firstName
	 * @param lastName
	 * @param role
	 */
	public BlockedAccount(int id, int number, long amount, String firstName, String lastName, String role) {
		super();
		this.id = id;
		this.number = number;
		this.amount = amount;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}

	/**
	 * Account constructor without parameters
	 */
	public BlockedAccount() {
		super();

	}

	@Override
	public String toString() {
		return "BlockedAccount [id=" + id + ", number=" + number + ", amount=" + amount + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", role=" + role + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (amount ^ (amount >>> 32));
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + number;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		BlockedAccount other = (BlockedAccount) obj;
		if (amount != other.amount)
			return false;
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
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
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

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
