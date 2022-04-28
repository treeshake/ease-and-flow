ALTER TABLE IF EXISTS public.ig_following
    ADD CONSTRAINT username_fkey FOREIGN KEY (username)
    REFERENCES public.ig_user (username) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;
CREATE INDEX IF NOT EXISTS username_idx
    ON public.ig_following(username);

-- Constraint: ig_profile_unique
