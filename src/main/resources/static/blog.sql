create table author (
                        id serial not null
                            constraint  author_pk primary key ,
                        name varchar(200),
                        email varchar(255)
);

create table post (
                      id serial not null
                          constraint post_pk primary key ,
                      text_news varchar(1000),
                      date timestamp,
                      title varchar(150),
                      author_id bigint constraint post_author_fk references author(id)
);

create table comments
(
    id serial not null
        constraint comments_pk primary key,
    text_comment varchar(1000),
    date timestamp,
    author_id bigint constraint comments_author_fk references author(id),
    post_id bigint constraint comments_post_fk references post(id)
);
