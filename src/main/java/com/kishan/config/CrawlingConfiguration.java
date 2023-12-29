package com.kishan.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrawlingConfiguration {

    @Builder.Default
    private long maxDepth = 2;
}
