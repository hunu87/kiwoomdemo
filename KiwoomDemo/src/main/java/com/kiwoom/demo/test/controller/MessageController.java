package com.kiwoom.demo.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiwoom.demo.test.dto.MessageDto;
import com.kiwoom.demo.test.entity.MessageEntity;
import com.kiwoom.demo.test.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MessageController {
	
	@Autowired
	private MessageRepository messageRepository;

	/**
	 * Message를 받아 DB에 등록
	 * @param message
	 * @return
	 */
	@PostMapping("/message")
	public MessageEntity postMessage(MessageDto message) {
		log.debug("message post request : {}", message);

		return messageRepository.save(message.toEntity());
	}
	
	/**
	 * DB에 저장된 Message 리스트를 리턴
	 * @return
	 */
	@GetMapping("/message")
	public List<MessageEntity> getMessageList() {
		return messageRepository.findAll();
	}
}
