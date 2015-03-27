/**
 * Initial migration
 * UP
 */

CREATE TABLE task (
    id integer NOT NULL,
    title text NOT NULL,
    completed boolean,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL
);

CREATE SEQUENCE task_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE task_id_seq OWNED BY task.id;
ALTER TABLE ONLY task ALTER COLUMN id SET DEFAULT nextval('task_id_seq'::regclass);
ALTER TABLE ONLY task ADD CONSTRAINT task_primary_key_id PRIMARY KEY (id);
