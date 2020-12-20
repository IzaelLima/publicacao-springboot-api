CREATE TABLE public.publicacao
(
    id bigint NOT NULL DEFAULT nextval('publicacao_id_seq'::regclass),
    data_publicacao timestamp without time zone,
    texto character varying(255) COLLATE pg_catalog."default",
    total_likes integer,
    CONSTRAINT publicacao_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.publicacao
    OWNER to postgres;