package me.ustory.api.comment.application.port.out;

import me.ustory.api.comment.domain.PaperId;

public interface UnlockPaperPort {
    void createdComment(PaperId paperId,int commentCount);
}
