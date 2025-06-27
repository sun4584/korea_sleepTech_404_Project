package com.example.back404.teamproject.dto.subject.request;

import com.example.back404.teamproject.common.enums.SubjectStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubjectStatusUpdateRequestDto {
    @NotNull(message = "과목 상태는 필수입니다.")
    private SubjectStatus status;
}