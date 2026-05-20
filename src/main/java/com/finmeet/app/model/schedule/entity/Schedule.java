package com.finmeet.app.model.schedule.entity;

import com.finmeet.app.model.common.entity.BaseEntity;
import com.finmeet.app.model.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "start_at", nullable = false)
    private LocalDateTime startAt;

    @Column(name = "end_at", nullable = false)
    private LocalDateTime endAt;

    @Column(name = "all_day", nullable = false)
    private boolean allDay;

    public Schedule(
        Member member, String title, String description, LocalDateTime startAt, LocalDateTime endAt, boolean allDay
    ) {
        validateTime(startAt, endAt);

        this.member = member;
        this.title = title;
        this.description = description;
        this.startAt = startAt;
        this.endAt = endAt;
        this.allDay = allDay;
    }

    public void update(
        String title, String description, LocalDateTime startAt, LocalDateTime endAt, Boolean allDay
    ) {
        LocalDateTime newStartAt = startAt != null ? startAt : this.startAt;
        LocalDateTime newEndAt = endAt != null ? endAt : this.endAt;

        validateTime(newStartAt, newEndAt);

        this.startAt = newStartAt;
        this.endAt = newEndAt;

        if (title != null) {
            this.title = title;
        }

        /*
            description 필드 없음, null -> 기존 설명 유지
            description : "" -> 설명 비우기
            description : "내용" -> 설명 수정
         */
        if (description != null) {
            this.description = description;
        }

        if (allDay != null) {
            this.allDay = allDay;
        }
    }

    private void validateTime(LocalDateTime startAt, LocalDateTime endAt) {
        if (startAt == null || endAt == null) {
            throw new IllegalArgumentException("시작 시간과 종료 시간은 필수입니다.");
        }

        if (!endAt.isAfter(startAt)) {
            throw new IllegalArgumentException("종료 시간은 시작 시간보다 늦어야 합니다.");
        }
    }

}
