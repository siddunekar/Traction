
package com.traction.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traction.backend.entity.Connection;

public interface ConnectionRepository
        extends JpaRepository<Connection, Integer> {

    List<Connection> findByReceiverIdAndStatusIgnoreCase(
            Integer receiverId, String status);

    List<Connection> findBySenderIdAndStatusIgnoreCase(
            Integer senderId, String status);

    List<Connection> findBySenderIdOrReceiverId(
            Integer senderId, Integer receiverId);

    boolean existsBySenderIdAndReceiverIdAndStatusIgnoreCase(
            Integer senderId, Integer receiverId, String status);
}
