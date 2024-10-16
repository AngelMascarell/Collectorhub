package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.controller.request.AppReviewRequest;
import com.collectorhub.collectorhub.controller.request.RateRequest;
import com.collectorhub.collectorhub.controller.response.AppReviewResponse;
import com.collectorhub.collectorhub.controller.response.RateResponse;
import com.collectorhub.collectorhub.database.entities.AppReviewEntity;
import com.collectorhub.collectorhub.database.entities.RateEntity;
import com.collectorhub.collectorhub.dto.AppReviewDto;
import com.collectorhub.collectorhub.dto.RateDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AbstractAppReviewDtoMapper {

    AppReviewDto fromAppReviewEntityToAppReviewDto(AppReviewEntity AppReviewEntity);

    default List<AppReviewDto> fromAppReviewEntityListToAppReviewDtoList(List<AppReviewEntity> appReviewEntityList) {
        return appReviewEntityList.stream()
                .map(review -> new AppReviewDto(
                        review.getId(),
                        review.getUser() != null ? review.getUser().getId() : null,
                        review.getRate(),
                        review.getComment(),
                        review.getDate()
                ))
                .collect(Collectors.toList());
    }
    AppReviewDto fromAppReviewRequestToAppReviewDto(AppReviewRequest AppReviewRequest);

    AppReviewResponse fromAppReviewDtoToAppReviewResponse(AppReviewDto AppReviewDto);

    List<AppReviewResponse> fromAppReviewDtoListToAppReviewResponseList(List<AppReviewDto> AppReviewDtoList);

    AppReviewEntity fromAppReviewDtoToAppReviewEntity(AppReviewDto AppReviewDto);

}
