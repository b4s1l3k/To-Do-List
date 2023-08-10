-- Создание схемы tasks
create schema if not exists tasks;

-- Таблица "Пользователи"
create table if not exists tasks."Users" (
                                             "Логин" VARCHAR(50) NOT NULL PRIMARY KEY,
                                             "Пароль" VARCHAR(100) NOT NULL

);

-- Создание таблицы "All Tasks"
create table if not exists tasks."All Tasks" (
                                                 "Логин" VARCHAR NOT NULL REFERENCES tasks."Users"("Логин"),
                                                 "ID" INT NOT NULL,
                                                 "Заголовок" VARCHAR NOT NULL,
                                                 "Описание" VARCHAR NOT NULL,
                                                 "Дедлайн" DATE NOT NULL,
                                                 "Дополнение" VARCHAR,
                                                 "Выполнена" BOOLEAN NOT NULL
);



