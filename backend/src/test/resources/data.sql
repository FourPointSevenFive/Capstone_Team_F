-- 1. User 테이블 데이터 삽입
INSERT INTO users (id, username, password, nickname)
VALUES (1, 'user1', 'password123', 'nickname1');

INSERT INTO users (id, username, password, nickname)
VALUES (2, 'user2', 'password456', 'nickname2');

-- 2. Content 테이블 데이터 삽입
INSERT INTO content (id, category, title, description, content_image_url, created_at, updated_at)
VALUES (1, 'K_POP', 'Content Title 1', 'Description for content 1', 'http://example.com/image1.jpg', NOW(), NOW());

INSERT INTO content (id, category, title, description, content_image_url, created_at, updated_at)
VALUES (2, 'DRAMA', 'Content Title 2', 'Description for content 2', 'http://example.com/image2.jpg', NOW(), NOW());

-- 3. Location 테이블 데이터 삽입
INSERT INTO location (id, content_id, title, latitude, longitude, description, video_link, favorite_cnt, pose, created_at, updated_at)
VALUES (1, 1, 'Location Title 1', 37.5665, 126.9780, 'Description for location 1', 'http://example.com/video1.mp4', 10, 'Standing', NOW(), NOW());

INSERT INTO location (id, content_id, title, latitude, longitude, description, video_link, favorite_cnt, pose, created_at, updated_at)
VALUES (2, 1, 'Location Title 2', 37.5651, 126.9890, 'Description for location 2', 'http://example.com/video2.mp4', 20, 'Sitting', NOW(), NOW());

INSERT INTO location (id, content_id, title, latitude, longitude, description, video_link, favorite_cnt, pose, created_at, updated_at)
VALUES (3, 1, 'Location Title 1', 37.5665, 126.9780, 'Description for location 1', 'http://example.com/video1.mp4', 10, 'Standing', NOW(), NOW());

INSERT INTO location (id, content_id, title, latitude, longitude, description, video_link, favorite_cnt, pose, created_at, updated_at)
VALUES (4, 1, 'Location Title 2', 37.5651, 126.9890, 'Description for location 2', 'http://example.com/video2.mp4', 20, 'Sitting', NOW(), NOW());

INSERT INTO location (id, content_id, title, latitude, longitude, description, video_link, favorite_cnt, pose, created_at, updated_at)
VALUES (5, 2, 'Location Title 1', 37.5665, 126.9780, 'Description for location 1', 'http://example.com/video1.mp4', 10, 'Standing', NOW(), NOW());

INSERT INTO location (id, content_id, title, latitude, longitude, description, video_link, favorite_cnt, pose, created_at, updated_at)
VALUES (6, 2, 'Location Title 2', 37.5651, 126.9890, 'Description for location 2', 'http://example.com/video2.mp4', 20, 'Sitting', NOW(), NOW());

INSERT INTO location (id, content_id, title, latitude, longitude, description, video_link, favorite_cnt, pose, created_at, updated_at)
VALUES (7, 1, 'Location Title 1', 37.5665, 126.9780, 'Description for location 1', 'http://example.com/video1.mp4', 10, 'Standing', NOW(), NOW());

INSERT INTO location (id, content_id, title, latitude, longitude, description, video_link, favorite_cnt, pose, created_at, updated_at)
VALUES (8, 1, 'Location Title 2', 37.5651, 126.9890, 'Description for location 2', 'http://example.com/video2.mp4', 20, 'Sitting', NOW(), NOW());

INSERT INTO location (id, content_id, title, latitude, longitude, description, video_link, favorite_cnt, pose, created_at, updated_at)
VALUES (9, 1, 'Location Title 1', 37.5665, 126.9780, 'Description for location 1', 'http://example.com/video1.mp4', 10, 'Standing', NOW(), NOW());

INSERT INTO location (id, content_id, title, latitude, longitude, description, video_link, favorite_cnt, pose, created_at, updated_at)
VALUES (10, 1, 'Location Title 2', 37.5651, 126.9890, 'Description for location 2', 'http://example.com/video2.mp4', 20, 'Sitting', NOW(), NOW());

INSERT INTO location (id, content_id, title, latitude, longitude, description, video_link, favorite_cnt, pose, created_at, updated_at)
VALUES (11, 2, 'Location Title 1', 37.5665, 126.9780, 'Description for location 1', 'http://example.com/video1.mp4', 10, 'Standing', NOW(), NOW());

INSERT INTO location (id, content_id, title, latitude, longitude, description, video_link, favorite_cnt, pose, created_at, updated_at)
VALUES (12, 2, 'Location Title 2', 37.5651, 126.9890, 'Description for location 2', 'http://example.com/video2.mp4', 20, 'Sitting', NOW(), NOW());

INSERT INTO location (id, content_id, title, latitude, longitude, description, video_link, favorite_cnt, pose, created_at, updated_at)
VALUES (13, 1, 'Location Title 1', 37.5665, 126.9780, 'Description for location 1', 'http://example.com/video1.mp4', 10, 'Standing', NOW(), NOW());

INSERT INTO location (id, content_id, title, latitude, longitude, description, video_link, favorite_cnt, pose, created_at, updated_at)
VALUES (14, 1, 'Location Title 2', 37.5651, 126.9890, 'Description for location 2', 'http://example.com/video2.mp4', 20, 'Sitting', NOW(), NOW());


-- 4. Image 테이블 데이터 삽입
INSERT INTO image (id, location_id, image_url, description, created_at)
VALUES (1, 1, 'http://example.com/image1_location1.jpg', 'Image description for location 1', NOW());

INSERT INTO image (id, location_id, image_url, description, created_at)
VALUES (2, 1, 'http://example.com/image2_location1.jpg', 'Image description for location 1', NOW());

INSERT INTO image (id, location_id, image_url, description, created_at)
VALUES (3, 2, 'http://example.com/image1_location2.jpg', 'Image description for location 2', NOW());

-- 5. Favorite 테이블 데이터 삽입
INSERT INTO favorite (id, user_id, location_id, created_at)
VALUES (1, 1, 1, NOW());

INSERT INTO favorite (id, user_id, location_id, created_at)
VALUES (2, 1, 2, NOW());

INSERT INTO favorite (id, user_id, location_id, created_at)
VALUES (3, 2, 3, NOW());

INSERT INTO favorite (id, user_id, location_id, created_at)
VALUES (4, 2, 4, NOW());

-- 6. ProofShot 테이블 데이터 삽입
INSERT INTO proof_shot (user_id, location_id, image_url, description, created_at)
VALUES (1, 1, 'http://example.com/proofshot1_user1_location1.jpg', 'ProofShot description for user 1, location 1', NOW());

INSERT INTO proof_shot (user_id, location_id, image_url, description, created_at)
VALUES (1, 2, 'http://example.com/proofshot2_user1_location2.jpg', 'ProofShot description for user 1, location 2', NOW());

INSERT INTO proof_shot (user_id, location_id, image_url, description, created_at)
VALUES (2, 1, 'http://example.com/proofshot1_user2_location1.jpg', 'ProofShot description for user 2, location 1', NOW());

INSERT INTO proof_shot (user_id, location_id, image_url, description, created_at)
VALUES (2, 2, 'http://example.com/proofshot2_user2_location2.jpg', 'ProofShot description for user 2, location 2', NOW());

-- 7. Stamp 테이블 데이터 삽입
INSERT INTO stamp (user_id, location_id, created_at)
VALUES (1, 1, NOW());

INSERT INTO stamp (user_id, location_id, created_at)
VALUES (1, 2, NOW());

INSERT INTO stamp (user_id, location_id, created_at)
VALUES (1, 3, NOW());

INSERT INTO stamp (user_id, location_id, created_at)
VALUES (1, 4, NOW());

INSERT INTO stamp (user_id, location_id, created_at)
VALUES (1, 5, NOW());

INSERT INTO stamp (user_id, location_id, created_at)
VALUES (1, 6, NOW());

INSERT INTO stamp (user_id, location_id, created_at)
VALUES (1, 7, NOW());

INSERT INTO stamp (user_id, location_id, created_at)
VALUES (1, 8, NOW());

INSERT INTO stamp (user_id, location_id, created_at)
VALUES (1, 9, NOW());

INSERT INTO stamp (user_id, location_id, created_at)
VALUES (1, 10, NOW());

INSERT INTO stamp (user_id, location_id, created_at)
VALUES (1, 11, NOW());

INSERT INTO stamp (user_id, location_id, created_at)
VALUES (1, 12, NOW());

INSERT INTO stamp (user_id, location_id, created_at)
VALUES (2, 1, NOW());

INSERT INTO stamp (user_id, location_id, created_at)
VALUES (2, 2, NOW());