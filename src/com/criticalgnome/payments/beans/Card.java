package com.criticalgnome.payments.beans;

/**
 * @author CriticalGnome
 * Credit Card
 *
 */
public class Card {
	private int id;
	private long number;
	private int expMonth;
	private int expYear;
	private String comment;
	
	/**
	 * Pattern Builder
	 */
	public static class Builder {
		private int id;
		private long number;
		private int expMonth;
		private int expYear;
		private String comment;
		
		public Builder id(int id) {
			this.id = id;
			return this;
		}
		
		public Builder number(long number) {
			this.number = number;
			return this;
		}
		
		public Builder expMonth(int expMonth) {
			this.expMonth = expMonth;
			return this;
		}
		
		public Builder expYear(int expYear) {
			this.expYear = expYear;
			return this;
		}
		
		public Builder comment(String comment) {
			this.comment = comment;
			return this;
		}
		
		public Card build() {
			return new Card(this);
		}
	}
	
	private Card(Builder builder) {
		id = builder.id;
		number = builder.number;
		expMonth = builder.expMonth;
		expYear = builder.expYear;
		comment = builder.comment;
	}

	/**
	 * Card constructor
	 * @param id
	 * @param number
	 * @param expMonth
	 * @param expYear
	 * @param comment
	 */
	public Card(int id, long number, int expMonth, int expYear, String comment) {
		super();
		this.id = id;
		this.number = number;
		this.expMonth = expMonth;
		this.expYear = expYear;
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", number=" + number + ", expMonth=" + expMonth + ", expYear=" + expYear
				+ ", comment=" + comment + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + expMonth;
		result = prime * result + expYear;
		result = prime * result + id;
		result = prime * result + (int) (number ^ (number >>> 32));
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
		Card other = (Card) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (expMonth != other.expMonth)
			return false;
		if (expYear != other.expYear)
			return false;
		if (id != other.id)
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

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public int getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(int expMonth) {
		this.expMonth = expMonth;
	}

	public int getExpYear() {
		return expYear;
	}

	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
