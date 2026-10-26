
package com.traction.backend.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.traction.backend.dto.ConnectionResponse;
import com.traction.backend.service.ConnectionService;

@RestController
@RequestMapping("/connections")
public class ConnectionController {

    private final ConnectionService connectionService;

    public ConnectionController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @PostMapping("/{receiverId}")
    public ConnectionResponse sendRequest(
            Authentication authentication,
            @PathVariable Integer receiverId) {

        Integer senderId = (Integer) authentication.getPrincipal();

        return connectionService.sendRequest(senderId, receiverId);
    }

    @GetMapping("/requests")
    public List<ConnectionResponse> getIncomingRequests(
            Authentication authentication) {

        Integer receiverId = (Integer) authentication.getPrincipal();

        return connectionService.getIncomingRequests(receiverId);
    }

    @PutMapping("/{connectionId}/respond")
    public ConnectionResponse respondToRequest(
            Authentication authentication,
            @PathVariable Integer connectionId,
            @RequestParam String decision) {

        Integer receiverId = (Integer) authentication.getPrincipal();

        return connectionService.respondToRequest(
                receiverId, connectionId, decision);
    }
}
