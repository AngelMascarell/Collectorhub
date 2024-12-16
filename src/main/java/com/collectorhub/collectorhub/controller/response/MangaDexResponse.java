package com.collectorhub.collectorhub.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Validated
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MangaDexResponse {
    private Data data;

    @Validated
    @NoArgsConstructor
    @AllArgsConstructor
    @lombok.Data
    @Builder
    public static class Data {
        private Attributes attributes;

        @Validated
        @NoArgsConstructor
        @AllArgsConstructor
        @lombok.Data
        @Builder
        public static class Attributes {
            private String fileName;

        }
    }
}

