package com.donation.backend.demo.controller;

import com.donation.backend.demo.message.request.MessageForm;
import com.donation.backend.demo.message.response.ResponseMessage;
import com.donation.backend.demo.model.Message;
import com.donation.backend.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    MessageRepository messageRepository;

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Message>> allMessages() {

        try {
            List<Message> messages = new ArrayList<>(messageRepository.findAll());
            if(messages.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(messages, HttpStatus.OK);

        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Message> getMessageById(@PathVariable("id") long id) {

        Optional<Message> messageOptional = messageRepository.findById(id);

        if(messageOptional.isPresent()) {

            Message message = messageOptional.get();
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> newMessage(@RequestBody MessageForm messageForm) {

        Message message = new Message(messageForm.getContent(), messageForm.getEmail(), true);
        messageRepository.save(message);
        return new ResponseEntity<>(new ResponseMessage("Message created successfully!"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteMessage(@PathVariable("id") Long id) {

        Optional<Message> messageOptional = messageRepository.findById(id);
        if(messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setEnabled(false);
            messageRepository.save(message);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
