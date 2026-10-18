CREATE DATABASE criarte;

CREATE TABLE criarte.usuario (
    id AUTO_INCREMENT PRIMARY KEY, 
    nome VARCHAR(100) NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    foto_perfil VARCHAR(255),
    sexo VARCHAR(30) CHECK (sexo IN ('Masculino', 'Feminino', 'Outro', 'Prefiro não informar')),
    contato VARCHAR(50),
    aceita_termos BOOLEAN DEFAULT TRUE,
    ativo BOOLEAN DEFAULT TRUE,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ultimo_acesso TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE criarte.loja (
    id AUTO_INCREMENT PRIMARY KEY,
    usuario_id INTEGER UNIQUE NOT NULL,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    foto_perfil VARCHAR(255),
    ativo BOOLEAN DEFAULT TRUE,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_usuario_loja FOREIGN KEY (usuario_id) REFERENCES criarte.usuario(id) ON DELETE CASCADE
);

CREATE TABLE criarte.receita (
    id AUTO_INCREMENT PRIMARY KEY,
    usuario_id INTEGER NOT NULL,
    codigo VARCHAR(7) DEFAULT criarte.gerar_codigo_receita() UNIQUE NOT NULL,
    nome VARCHAR(100) NOT NULL,
    tipo VARCHAR(50) CHECK (tipo IN ('Costura', 'Crochê', 'Tricô', 'Bordado', 'Cerâmica', 'Escultura', 'Desenho', 'Outros')),
    preco NUMERIC(10,2),
    descricao TEXT,
    resumo VARCHAR(100),
    materiais TEXT,
    url_video VARCHAR(255),
    url_pdf VARCHAR(255),
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_receita_usuario FOREIGN KEY (usuario_id) REFERENCES criarte.usuario(id) ON DELETE CASCADE
);

CREATE TABLE criarte.galeria (
    id AUTO_INCREMENT PRIMARY KEY,
    receita_id INTEGER NOT NULL,
    url_imagem VARCHAR(255) NOT NULL,   
    CONSTRAINT fk_galeria_receita FOREIGN KEY (receita_id) REFERENCES criarte.receita(id) ON DELETE CASCADE
);