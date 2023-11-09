-- Создание схемы tasks
create schema if not exists tasks;

-- Таблица "Пользователи"
create table if not exists tasks."Users" (
                                             "login" VARCHAR(50) NOT NULL PRIMARY KEY,
                                             "password" VARCHAR(100) NOT NULL

);

-- Создание таблицы "All Tasks"
create table if not exists tasks."All Tasks" (
                                                 "login" VARCHAR NOT NULL REFERENCES tasks."Users"("login"),
                                                 "id" INT,
                                                 "title" VARCHAR NOT NULL,
                                                 "description" VARCHAR NOT NULL,
                                                 "deadline" DATE NOT NULL,
                                                 "supplement" VARCHAR,
                                                 "status" BOOLEAN NOT NULL
);



