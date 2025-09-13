-- Adds Image_Path column to Product table if it does not already exist
ALTER TABLE Product
    ADD COLUMN IF NOT EXISTS Image_Path VARCHAR(255) DEFAULT NULL AFTER Category;
