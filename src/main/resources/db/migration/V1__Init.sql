CREATE EXTENSION "pgcrypto";

CREATE TABLE sample_data
(
  id UUID PRIMARY KEY NOT NULL DEFAULT gen_random_uuid() ,
  name text
);