-- Criar o banco de dados se não existir
CREATE DATABASE IF NOT EXISTS VendaPassagemOnibus;

-- Usar o banco de dados
USE VendaPassagemOnibus;

-- Tabela para Vendedor
CREATE TABLE IF NOT EXISTS Vendedor (
    id_vendedor INT PRIMARY KEY AUTO_INCREMENT,
    nome_vendedor VARCHAR(255)
);

-- Tabela para Administrador
CREATE TABLE IF NOT EXISTS Administrador (
    id_administrador INT PRIMARY KEY AUTO_INCREMENT,
    nome_administrador VARCHAR(255),
    senha VARCHAR(255) NOT NULL
);

-- Tabela para Viagem
CREATE TABLE IF NOT EXISTS Viagem (
    id_viagem INT PRIMARY KEY AUTO_INCREMENT,
    origem VARCHAR(255),
    destino VARCHAR(255),
    horario VARCHAR(20),
    preco DOUBLE,
    assentos_disponiveis INT,
    id_administrador INT,
    FOREIGN KEY (id_administrador) REFERENCES Administrador(id_administrador)
);

-- Tabela para Bilhete
CREATE TABLE IF NOT EXISTS Bilhete (
    id_bilhete INT PRIMARY KEY AUTO_INCREMENT,
    id_viagem INT,
    id_vendedor INT,
    nomeCliente VARCHAR(255),
    dataHoraEmbarque VARCHAR(255),
    FOREIGN KEY (id_viagem) REFERENCES Viagem(id_viagem),
    FOREIGN KEY (id_vendedor) REFERENCES Vendedor(id_vendedor)
);

-- Tabela para Venda
CREATE TABLE IF NOT EXISTS Venda (
    id_venda INT PRIMARY KEY AUTO_INCREMENT,
    id_viagem INT,
    nomeCliente VARCHAR(255),
    numeroAssento VARCHAR(255),
    valor DOUBLE,
    dataHoraVenda VARCHAR(255),
    FOREIGN KEY (id_viagem) REFERENCES Viagem(id_viagem)
);

-- Tabela intermediária para representar o relacionamento entre Viagem e Administrador
CREATE TABLE IF NOT EXISTS ViagemAdministrador (
    id_viagem INT,
    id_administrador INT,
    PRIMARY KEY (id_viagem, id_administrador),
    FOREIGN KEY (id_viagem) REFERENCES Viagem(id_viagem),
    FOREIGN KEY (id_administrador) REFERENCES Administrador(id_administrador)
);


select * from Venda;

select * from Viagem;