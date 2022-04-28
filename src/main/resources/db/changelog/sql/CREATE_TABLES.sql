-- Table: public.ig_following

-- DROP TABLE IF EXISTS public.ig_following;

CREATE TABLE IF NOT EXISTS public.ig_following
(
    query character varying(80) COLLATE pg_catalog."default" NOT NULL,
    "timestamp" time with time zone,
    error character varying(255) COLLATE pg_catalog."default",
    profile_url character varying(80) COLLATE pg_catalog."default",
    username character varying(60) COLLATE pg_catalog."default" NOT NULL,
    full_name character varying(255) COLLATE pg_catalog."default",
    img_url character varying(1000) COLLATE pg_catalog."default",
    instagram_id bigint NOT NULL,
    is_private boolean DEFAULT false,
    is_verified boolean DEFAULT false,
    id bigint NOT NULL DEFAULT nextval('ig_following_id_seq'::regclass),
    CONSTRAINT ig_following_unique UNIQUE (query, username)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.ig_following
    OWNER to phantombust;
-- Index: ig_following_idx

-- DROP INDEX IF EXISTS public.ig_following_idx;

CREATE INDEX IF NOT EXISTS ig_following_idx
    ON public.ig_following USING btree
    (query COLLATE pg_catalog."default" ASC NULLS LAST, username COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;


-- Table: public.ig_user

-- DROP TABLE IF EXISTS public.ig_user;

CREATE TABLE IF NOT EXISTS public.ig_user
(
    id integer NOT NULL DEFAULT nextval('ig_user_id_seq'::regclass),
    username character varying(60) COLLATE pg_catalog."default",
    CONSTRAINT ig_user_pkey PRIMARY KEY (id),
    CONSTRAINT username_unique UNIQUE (username)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.ig_user
    OWNER to phantombust;

-- Table: public.ig_profile

-- DROP TABLE IF EXISTS public.ig_profile;

CREATE TABLE IF NOT EXISTS public.ig_profile
(
    id integer NOT NULL DEFAULT nextval('ig_profile_id_seq'::regclass),
    profile_url character varying(80) COLLATE pg_catalog."default",
    public_email character varying(255) COLLATE pg_catalog."default",
    contact_phone_number character varying(80) COLLATE pg_catalog."default",
    username character varying(60) COLLATE pg_catalog."default" NOT NULL,
    full_name character varying(255) COLLATE pg_catalog."default",
    bio text COLLATE pg_catalog."default",
    snapchat character varying(80) COLLATE pg_catalog."default",
    followers_count integer,
    following_count integer,
    instagram_id bigint,
    is_business_account boolean DEFAULT false,
    is_verified boolean DEFAULT false,
    posts_count integer,
    query character varying(80) COLLATE pg_catalog."default" NOT NULL,
    "timestamp" time with time zone,
    business_category character varying(255) COLLATE pg_catalog."default",
    website character varying(255) COLLATE pg_catalog."default",
    mail_found character varying(255) COLLATE pg_catalog."default",
    error character varying(255) COLLATE pg_catalog."default",
    mail_found2 character varying(255) COLLATE pg_catalog."default",
    mail_found3 character varying(255) COLLATE pg_catalog."default",
    image_url character varying(1000) COLLATE pg_catalog."default",
    CONSTRAINT ig_profile_pkey PRIMARY KEY (id),
    CONSTRAINT ig_profile_unique UNIQUE (username)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.ig_profile
    OWNER to phantombust;