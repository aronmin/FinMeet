package com.finmeet.app.model.meeting.entity;

import com.finmeet.app.model.common.entity.BaseEntity;
import com.finmeet.app.model.meeting.role.MeetingMemberRole;
import com.finmeet.app.model.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
    name = "meeting_members",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_meeting_member",
            columnNames = {"meeting_id", "member_id"}
        )
    }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MeetingMember extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id", nullable = false)
    private Meeting meeting;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private MeetingMemberRole meetingMemberRole; // 방장(OWNER), 참가자(PARTICIPANT)

    public MeetingMember(Member member, Meeting meeting, MeetingMemberRole meetingMemberRole) {
        this.member = member;
        this.meeting = meeting;
        this.meetingMemberRole = meetingMemberRole;
    }

    // 방장 확인 여부
    public boolean isOwner() {
        return this.meetingMemberRole == MeetingMemberRole.OWNER;
    }

}
