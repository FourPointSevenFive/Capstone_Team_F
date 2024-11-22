-- Remove entity_id and entity_type columns
ALTER TABLE favorite
DROP COLUMN entity_id;

ALTER TABLE favorite
DROP COLUMN entity_type;

-- Add location_id column
ALTER TABLE favorite
ADD COLUMN location_id BIGINT;

-- Add foreign key constraint for location_id
ALTER TABLE favorite
ADD CONSTRAINT fk_favorite_location
FOREIGN KEY (location_id) REFERENCES location(id)
ON DELETE CASCADE;