DROP DATABASE IF EXISTS studentsystem;
CREATE DATABASE studentsystem;
USE studentsystem;

DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS teacher;
DROP TABLE IF EXISTS score;

CREATE TABLE student (
  id            INT AUTO_INCREMENT PRIMARY KEY,
  name          VARCHAR(255),
  sex           VARCHAR(12),
  department    VARCHAR(255),
  profession    VARCHAR(255),
  password      VARCHAR(32) NOT NULL
);

CREATE TABLE teacher (
  id            INT AUTO_INCREMENT PRIMARY KEY,
  name      VARCHAR(255),
  password      VARCHAR(32) NOT NULL
);

CREATE TABLE score (
  id            INT AUTO_INCREMENT PRIMARY KEY,
  student_id    INT,
  javass        DOUBLE,
  databasess    DOUBLE,
  css           DOUBLE,
  linearalgebra DOUBLE,
  FOREIGN KEY (student_id) REFERENCES student (id) ON DELETE CASCADE
);

DROP TRIGGER IF EXISTS tg1;
CREATE TRIGGER tg1 AFTER INSERT ON student FOR EACH ROW BEGIN INSERT INTO score(student_id) VALUES(NEW.id); END;

INSERT INTO student(id,name,sex,department,profession,password)VALUES(20161111,'大','男','软件学院','软件工程','123456');
INSERT INTO student(id,name,sex,department,profession,password)VALUES(20161112,'帅','男','软件学院','软件工程','123456');
INSERT INTO student(id,name,sex,department,profession,password)VALUES(20161113,'哥','男','软件学院','软件工程','123456');
INSERT INTO student(id,name,sex,department,profession,password)VALUES(20161114,'啊','男','软件学院','软件工程','123456');

INSERT INTO teacher(id,name,password)VALUES(222000,'帅哥','123456');

COMMIT;
