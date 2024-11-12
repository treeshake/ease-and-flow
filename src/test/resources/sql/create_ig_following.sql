--CREATE TABLE IF NOT EXISTS ig_following
--(
--    query character varying(80) NOT NULL,
--    'timestamp' time with time zone,
--    error character varying(255),
--    profile_url character varying(80),
--    username character varying(60) NOT NULL,
--    full_name character varying(255),
--    img_url character varying(1000),
--    instagram_id bigint NOT NULL,
--    is_private boolean DEFAULT false,
--    is_verified boolean DEFAULT false,
--    id bigint NOT NULL DEFAULT nextval('ig_following_id_seq'::regclass),
--    CONSTRAINT ig_following_unique UNIQUE (query, username)
--)

CREATE TABLE IF NOT EXISTS ig_following (
  query character varying(80) NOT NULL,
  "timestamp" timestamp,
  error character varying(255),
  profile_url character varying(80),
  username character varying(60) NOT NULL,
  full_name character varying(255),
  img_url character varying(1000),
  instagram_id bigint NOT NULL,
  is_private boolean DEFAULT false,
  is_verified boolean DEFAULT false,
  id serial NOT NULL,
  CONSTRAINT ig_following_unique UNIQUE (query, username)
);
