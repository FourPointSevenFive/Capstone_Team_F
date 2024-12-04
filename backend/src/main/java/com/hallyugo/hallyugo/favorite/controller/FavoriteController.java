package com.hallyugo.hallyugo.favorite.controller;

import com.hallyugo.hallyugo.auth.annotation.AuthUser;
import com.hallyugo.hallyugo.favorite.domain.response.FavoriteResponseDto;
import com.hallyugo.hallyugo.favorite.service.FavoriteService;
import com.hallyugo.hallyugo.user.domain.User;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @GetMapping("/favorite")
    public ResponseEntity<FavoriteResponseDto> getUserFavorite(
            @AuthUser User user,
            @RequestParam(name = "limit", defaultValue = "2") int limit
    ) {
        FavoriteResponseDto result = favoriteService.getFavoritesByUser(user, limit);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/favorite/on", params = "location_id")
    public ResponseEntity<Void> increaseFavoriteCount(
            @AuthUser User user,
            @RequestParam(name = "location_id") Long locationId
    ) {
        favoriteService.increaseFavoriteCountAndSave(user, locationId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/favorite/off", params = "location_id")
    public ResponseEntity<Void> decreaseFavoriteCount(
            @AuthUser User user,
            @RequestParam(name = "location_id") Long locationId
    ) {
        favoriteService.decreaseFavoriteCountAndDelete(user, locationId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/favorite", params = "location_id")
    public ResponseEntity<String> checkFavoriteClicked(
            @AuthUser User user,
            @RequestParam(name = "location_id") Long locationId
    ) {
        JSONObject result = new JSONObject();
        boolean isClicked = favoriteService.checkFavoriteClicked(user, locationId);
        long total = favoriteService.getTotalFavoriteCountByLocation(locationId);

        if (isClicked) {
            result.put("result", true);
        } else {
            result.put("result", false);
        }

        result.put("total", total);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result.toString());
    }
}
