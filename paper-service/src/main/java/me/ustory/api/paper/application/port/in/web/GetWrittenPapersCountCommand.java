package me.ustory.api.paper.application.port.in.web;

import me.ustory.api.paper.domain.MemberId;

public record GetWrittenPapersCountCommand(
    MemberId writerId
) {
}
