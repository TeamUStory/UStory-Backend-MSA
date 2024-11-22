package me.ustory.api.paper.application.port.in;

import me.ustory.api.paper.domain.MemberId;

public record GetMemberPapersCommand(
    MemberId memberId
) {
}
