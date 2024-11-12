-- View: public.ig_following_query_count_view

-- DROP VIEW public.ig_following_query_count_view;

CREATE OR REPLACE VIEW public.ig_following_query_count_view
 AS
 SELECT count(DISTINCT ig_following.query) AS count
   FROM ig_following;

ALTER TABLE public.ig_following_query_count_view
    OWNER TO phantombust;

-- View: public.ig_users_most_following_view

-- DROP VIEW public.ig_users_most_following_view;

CREATE OR REPLACE VIEW public.ig_users_most_following_view
 AS
 SELECT count(*) AS count,
    u.username
   FROM ig_user u
     JOIN ig_following f ON f.username::text = u.username::text
  GROUP BY u.username
 HAVING count(*) > 20
  ORDER BY (count(*)) DESC;

ALTER TABLE public.ig_users_most_following_view
    OWNER TO phantombust;

