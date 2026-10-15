DROP SCHEMA IF EXISTS criarte CASCADE;
CREATE SCHEMA criarte;

CREATE TABLE criarte.receita (
    id INT AUTO_INCREMENT PRIMARY KEY
    codigo VARCHAR(50) UNIQUE NOT NULL,
    nome VARCHAR(255) NOT NULL,
    tipo_artesanato VARCHAR(50) NOT NULL 
        CHECK (tipo_artesanato IN ('costura', 'croche', 'trico', 'pintura', 'ceramica', 'outros')),
    criador VARCHAR(255) NOT NULL,
    descricao TEXT NOT NULL,
    materiais TEXT NOT NULL,
    data_criacao DATE NOT NULL,
    preco NUMERIC(10, 2) NOT NULL,
    caminho_pdf VARCHAR(500) NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE criarte.receita_imagem (
    id INT AUTO_INCREMENT PRIMARY KEY
    id_receita INTEGER NOT NULL,
    caminho_foto VARCHAR(500) NOT NULL,
    
    CONSTRAINT fk_receita
      FOREIGN KEY (id_receita) 
      REFERENCES criarte.receita(id) 
      ON DELETE CASCADE
);

INSERT INTO criarte.receita (
    codigo, nome, tipo_artesanato, criador, descricao, 
    materiais, data_criacao, preco, caminho_pdf
) VALUES (
    'CRO-001', 
    'Amigurumi de Ursinho', 
    'croche', 
    'Maria da Silva', 
    'Lindo ursinho de amigurumi feito à mão, perfeito para presentear ou decorar.',
    '1 Novelo de fio de algodão na cor marrom',
    '2026-10-14', 
    45.50, 
    '/assets/pdf/receitas/cro-001_ursinho.pdf'
);

INSERT INTO criarte.receita_imagem (id_receita, caminho_foto) VALUES 
(1, '/assets/img/produtos/cro-001_frente.jpg'),
(1, '/assets/img/produtos/cro-001_costas.jpg'),
(1, '/assets/img/produtos/cro-001_detalhe.jpg');