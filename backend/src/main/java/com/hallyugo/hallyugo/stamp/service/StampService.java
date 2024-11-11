package com.hallyugo.hallyugo.stamp.service;

import com.hallyugo.hallyugo.stamp.domain.Stamp;
import com.hallyugo.hallyugo.stamp.domain.response.StampResponseDto;
import com.hallyugo.hallyugo.stamp.domain.response.StampResponseItem;
import com.hallyugo.hallyugo.stamp.repository.StampRepository;
import com.hallyugo.hallyugo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StampService {

    private final StampRepository stampRepository;

    public StampResponseDto getStampsByUser(User user, int limit) {
        List<Stamp> stamps = stampRepository.findByUserId(user.getId());

        List<Stamp> limitedStamps = stamps.size() > limit ? stamps.subList(0, limit) : stamps;

        List<StampResponseItem> stampResponseItems = limitedStamps.stream()
                .map(stamp -> {
                    StampResponseItem item = new StampResponseItem();
                    item.setId(stamp.getId());
                    item.setLocationId(stamp.getLocation().getId());
                    item.setCategory(stamp.getLocation().getContent().getCategory());
                    item.setTitle(stamp.getLocation().getTitle());
                    item.setCreatedAt(stamp.getCreatedAt());
                    return item;
                })
                .toList();

        StampResponseDto result = new StampResponseDto();
        result.setTotal(stamps.size());
        result.setStamps(stampResponseItems);
        return result;
    }
}
