
package com.traction.backend.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.traction.backend.dto.ConnectionResponse;
import com.traction.backend.entity.Connection;
import com.traction.backend.entity.User;
import com.traction.backend.repository.ConnectionRepository;
import com.traction.backend.repository.UserRepository;

@Service
public class ConnectionService {

    private final ConnectionRepository connectionRepository;
    private final UserRepository userRepository;

    public ConnectionService(
            ConnectionRepository connectionRepository,
            UserRepository userRepository) {
        this.connectionRepository = connectionRepository;
        this.userRepository = userRepository;
    }

    public ConnectionResponse sendRequest(Integer senderId, Integer receiverId) {
        if (senderId.equals(receiverId)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "You cannot send a connection request to yourself");
        }

        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Sender not found"));

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Receiver not found"));

        List<Connection> existing = connectionRepository
                .findBySenderIdOrReceiverId(senderId, senderId);

        for (Connection connection : existing) {
            boolean samePair =
                    (connection.getSenderId().equals(senderId)
                            && connection.getReceiverId().equals(receiverId))
                    || (connection.getSenderId().equals(receiverId)
                            && connection.getReceiverId().equals(senderId));

            if (samePair && !"REJECTED".equalsIgnoreCase(connection.getStatus())
                    && !"WITHDRAWN".equalsIgnoreCase(connection.getStatus())) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "A connection request or connection already exists");
            }
        }

        Connection connection = new Connection();
        connection.setSenderId(sender.getId());
        connection.setReceiverId(receiver.getId());
        connection.setStatus("PENDING");

        return mapToResponse(connectionRepository.save(connection), sender, receiver);
    }

    public List<ConnectionResponse> getIncomingRequests(Integer receiverId) {
        return connectionRepository
                .findByReceiverIdAndStatusIgnoreCase(receiverId, "PENDING")
                .stream()
                .map(connection -> {
                    User sender = userRepository.findById(connection.getSenderId())
                            .orElseThrow(() -> new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "Sender not found"));

                    User receiver = userRepository.findById(connection.getReceiverId())
                            .orElseThrow(() -> new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "Receiver not found"));

                    return mapToResponse(connection, sender, receiver);
                })
                .toList();
    }

    @Transactional
    public ConnectionResponse respondToRequest(
            Integer receiverId, Integer connectionId, String decision) {

        Connection connection = connectionRepository.findById(connectionId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Connection request not found"));

        if (!receiverId.equals(connection.getReceiverId())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You cannot respond to this connection request");
        }

        if (!"PENDING".equalsIgnoreCase(connection.getStatus())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "This connection request is no longer pending");
        }

        if (!"ACCEPTED".equalsIgnoreCase(decision)
                && !"REJECTED".equalsIgnoreCase(decision)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Decision must be ACCEPTED or REJECTED");
        }

        connection.setStatus(decision.toUpperCase());

        Connection saved = connectionRepository.save(connection);

        User sender = userRepository.findById(saved.getSenderId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Sender not found"));

        User receiver = userRepository.findById(saved.getReceiverId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Receiver not found"));

        return mapToResponse(saved, sender, receiver);
    }

    private ConnectionResponse mapToResponse(
            Connection connection, User sender, User receiver) {

        ConnectionResponse response = new ConnectionResponse();
        response.setConnectionId(connection.getId());
        response.setStatus(connection.getStatus());
        response.setCreatedAt(connection.getCreatedAt());

        response.setSenderId(sender.getId());
        response.setSenderUsername(sender.getUsername());
        response.setSenderOccupation(sender.getOccupation());
        response.setSenderLocation(sender.getLocation());
        response.setSenderProfilePhotoUrl(sender.getProfilePhotoUrl());

        response.setReceiverId(receiver.getId());
        response.setReceiverUsername(receiver.getUsername());

        return response;
    }
}
