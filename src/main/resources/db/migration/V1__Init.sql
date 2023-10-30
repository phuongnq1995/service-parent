CREATE EXTENSION "pgcrypto";

CREATE TABLE sample_data
(
  id UUID PRIMARY KEY NOT NULL DEFAULT gen_random_uuid(),
  name text
);

CREATE TABLE sub_sample_data
(
  id UUID PRIMARY KEY NOT NULL DEFAULT gen_random_uuid(),
  sample_data_id UUID REFERENCES sample_data(id) NOT NULL,
  name text NOT NULL UNIQUE
);