-- database: film
-- user: postgres

CREATE TABLE film (
    id serial primary key,
    titre varchar(50) not null
);

CREATE TABLE scene (
    id serial primary key,
    idplateau int not null,
    idfilm int not null,
    duree double precision not null,
    label varchar(50) not null,
    -- crée: 1, ecriture: 2, terminé: 3, plannifié: 4
    etat int
);

CREATE TABLE  plateau (
    id serial primary key,
    label varchar(50) not null,
    date_indispo DATE
);

CREATE TABLE personnage (
    id serial primary key,
    nom varchar(50) not null,
    date_indispo DATE
);

CREATE TABLE action (
    id serial primary key,
    idscene int not null,
    label text not null
);

CREATE TABLE action_personnage (
    id serial primary key,
    idP int not null,
    idA int not null
);

ALTER TABLE scene ADD FOREIGN KEY (idplateau) REFERENCES plateau(id);
ALTER TABLE scene ADD FOREIGN KEY (idfilm) REFERENCES film(id);
ALTER TABLE action ADD FOREIGN KEY (idscene) REFERENCES scene(id);
ALTER TABLE action_personnage ADD FOREIGN KEY (idP) REFERENCES personnage(id);
ALTER TABLE action_personnage ADD FOREIGN KEY (idA) REFERENCES action(id);

insert into film(titre) values('film1');
insert into plateau values
(default,'plateau3', '2023-03-21'),
(default,'plateau4', '2023-03-30'),
(default,'plateau5', '2023-03-30'),
(default,'plateau6', '2023-03-21'),
(default,'plateau7', '2023-03-20');
insert into personnage values
(default,'pers1', '2023-03-20'),
(default,'pers2', '2023-03-21'),
(default,'pers3', '2023-03-10'),
(default,'pers4', '2023-03-11'),
(default,'pers5', '2023-03-18'),
(default,'pers6', '2023-03-28');

insert into scene values
-- 1: creé
-- 2: en ecriture
-- 3: terminé
-- 4 plannifié

(default,1,1,4,'scene1', 1),
(default,1,1,2,'scene2', 3),
(default,2,1,5,'scene3', 3),
(default,2,1,1,'scene4', 3),
(default,3,1,2,'scene5', 3),
(default,4,1,7,'scene6', 3),
(default,5,1,5,'scene7', 3),
(default,5,1,4,'scene8', 2);

insert into action values
(default, 1, 'bla'),
(default, 2, 'bla'),
(default, 3, 'bla'),
(default, 4, 'bla'),
(default, 5, 'bla'),
(default, 6, 'bla'),
(default, 7, 'bla'),
(default, 8, 'bla');

insert into action_personnage values
(default, 1,1),
(default, 2,2),
(default, 3,3),
(default, 4,4),
(default, 5,5),
(default, 6,6),
(default, 6,7),
(default, 6,8);