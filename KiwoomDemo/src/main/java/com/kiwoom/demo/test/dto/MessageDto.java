package com.kiwoom.demo.test.dto;

import com.kiwoom.demo.test.entity.MessageEntity;

public class MessageDto {
	private Long id;
	private String name;
	private String message;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public MessageEntity toEntity() {
		return new MessageEntity
				.Builder()
				.id(id)
				.name(name)
				.message(message)
				.build();
	}
	
	@Override
	public String toString() {
		return "MessageDto [id=" + id + ", name=" + name + ", message=" + message + "]";
	}
}
