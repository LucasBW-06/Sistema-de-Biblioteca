DROP DATABASE biblioteca;
CREATE DATABASE biblioteca;

USE biblioteca;

CREATE TABLE livro (
	id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(150) NOT NULL,
    autor VARCHAR(150) NOT NULL,
    editora VARCHAR(150),
    genero VARCHAR(50),
    ano INT,
    isbn VARCHAR(13),
    codigo VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE usuario (
	id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    telefone VARCHAR(11)
);

CREATE TABLE emprestimo (
	id INT PRIMARY KEY AUTO_INCREMENT,
    livro_id INT NOT NULL,
    FOREIGN KEY (livro_id) REFERENCES livro(id),
    usuario_id INT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    data_emprestimo DATE NOT NULL,
    data_devolucao DATE NOT NULL,
    data_devolvido DATE,
    estado ENUM("EMPRESTADO", "DEVOLVIDO", "ATRASADO") DEFAULT "EMPRESTADO"
);

CREATE TABLE funcionario (
	id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    cargo VARCHAR(150) NOT NULL,
    login VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL
)