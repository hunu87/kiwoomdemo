package com.kiwoom.demo.test.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MessageEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String message;
	
	public MessageEntity(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.message = builder.message;
	}

	public static class Builder {
		private Long id;
		private String name;
		private String message;
		
		public Builder id(Long id) {
			this.id = id;
			return this;
		}
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public Builder message(String message) {
			this.message = message;
			return this;
		}
		
		public MessageEntity build() {
			return new MessageEntity(this);
		}
	}
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", name=" + name + ", message=" + message + "]";
	}
}
