package me.ustory.api.paper.domain;

import lombok.Getter;

@Getter
public class PaperId {

    private Long id;

    public static PaperId of(Long id) {
        return new PaperId(id);
    }

    private PaperId(Long id) {
        this.id = id;
    }

}
