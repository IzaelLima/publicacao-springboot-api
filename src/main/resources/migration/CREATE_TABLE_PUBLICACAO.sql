CREATE TABLE publicacao (
	id serial PRIMARY KEY,
	data_publicacao timestamp without time zone,
	texto character varying(255),
	total_likes integer
);