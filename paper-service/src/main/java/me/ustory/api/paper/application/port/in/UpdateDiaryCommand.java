package me.ustory.api.paper.application.port.in;

import me.ustory.api.common.kafka.UpdateDiaryKafkaDTO;
import me.ustory.api.paper.domain.DiaryId;
import me.ustory.api.paper.domain.Image;
import me.ustory.api.paper.domain.MemberId;
import me.ustory.api.paper.domain.MemberInfo;

public record UpdateDiaryCommand(
    DiaryId diaryId,
    MemberInfo memberInfo,
    String name,
    Image image,
    String color,
    Image marker
) {
    public static UpdateDiaryCommand of(UpdateDiaryKafkaDTO dto) {
        return new UpdateDiaryCommand(
            DiaryId.of(dto.diaryId()),
            MemberInfo.of(dto.memberIds().stream().map(MemberId::of).toList()),
            dto.diaryName(),
            Image.of(dto.imageUrl()),
            dto.color(),
            Image.of(dto.markerUrl())
        );
    }
}
