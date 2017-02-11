package com.criticalgnome.payments.beans;

/**
 * @author CriticalGnome
 * Bank Account
 *
 */
public class Account {
	private int id;
	private int number;
	private long amount;
	private boolean isBlocked;
	private String comment;
	
	/**
	 * Pattern Builder
	 */
	public static class Builder {
		private int id;
		private int number;
		private long amount;
		private boolean isBlocked;
		private String comment;

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

		public Builder isBlocked(boolean isBlocked) {
			this.isBlocked = isBlocked;
			return this;
		}

		public Builder comment(String comment) {
			this.comment = comment;
			return this;
		}

		public Account build() {
			return new Account(this);
		}
	}

	private Account(Builder builder) {
		id = builder.id;
		number = builder.number;
		amount = builder.amount;
		isBlocked = builder.isBlocked;
		comment = builder.comment;
	}

	/**
	 * Account constructor
	 * @param id
	 * @param number
	 * @param amount
	 * @param isBlocked
	 * @param comment
	 */
	public Account(int id, int number, long amount, boolean isBlocked, String comment) {
		super();
		this.id = id;
		this.number = number;
		this.amount = amount;
		this.isBlocked = isBlocked;
		this.comment = comment;
	}

	/**
	 * Account constructor without parameters
	 */
	public Account() {
		super();

	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", number=" + number + ", amount=" + amount + ", isBlocked=" + isBlocked
				+ ", comment=" + comment + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (amount ^ (amount >>> 32));
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + id;
		result = prime * result + (isBlocked ? 1231 : 1237);
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
		Account other = (Account) obj;
		if (amount != other.amount)
			return false;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (id != other.id)
			return false;
		if (isBlocked != other.isBlocked)
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

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public boolean isisBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
