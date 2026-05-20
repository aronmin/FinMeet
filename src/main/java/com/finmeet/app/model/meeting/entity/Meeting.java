package com.finmeet.app.model.meeting.entity;

import com.finmeet.app.model.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "meetings")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Meeting extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "invite_code", nullable = false, unique = true, updatable = false)
    private String inviteCode;

    public Meeting(String name, String description, String inviteCode) {
        this.name = name;
        this.description = description;
        this.inviteCode = inviteCode;
    }

    public void update(String name, String description) {
        if (name != null) {
            this.name = name;
        }

        if (description != null) {
            this.description = description;
        }
    }

}
