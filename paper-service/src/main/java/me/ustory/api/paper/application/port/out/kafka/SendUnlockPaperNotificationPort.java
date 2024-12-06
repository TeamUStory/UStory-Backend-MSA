package me.ustory.api.paper.application.port.out.kafka;

import me.ustory.api.paper.domain.MemberId;
import me.ustory.api.paper.domain.PaperId;

import java.util.List;

public interface SendUnlockPaperNotificationPort {
    void sendUnlockPaperNotification(PaperId paperId, List<MemberId> memberIds);
}
