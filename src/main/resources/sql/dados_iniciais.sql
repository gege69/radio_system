
/* Esse script cria alguns dados básicos necessários como as categorias, usuários iniciais e permissões básicas */


-- SCRIPT INICIAL

INSERT INTO CATEGORIA ( ID_CATEGORIA, DESCRICAO, NOME, CODIGO, SIMPLEUPLOAD ) VALUES ( nextval('categoria_id_categoria_seq'), 'Música', 'Música', 'musica', false );
INSERT INTO CATEGORIA ( ID_CATEGORIA, DESCRICAO, NOME, CODIGO, SIMPLEUPLOAD ) VALUES ( nextval('categoria_id_categoria_seq'), 'Vinheta', 'Vinheta', 'vinheta', true );
INSERT INTO CATEGORIA ( ID_CATEGORIA, DESCRICAO, NOME, CODIGO, SIMPLEUPLOAD ) VALUES ( nextval('categoria_id_categoria_seq'), 'Institucional', 'Institucional', 'inst', true );
INSERT INTO CATEGORIA ( ID_CATEGORIA, DESCRICAO, NOME, CODIGO, SIMPLEUPLOAD ) VALUES ( nextval('categoria_id_categoria_seq'), 'Comercial', 'Comercial', 'comercial', true );
INSERT INTO CATEGORIA ( ID_CATEGORIA, DESCRICAO, NOME, CODIGO, SIMPLEUPLOAD ) VALUES ( nextval('categoria_id_categoria_seq'), 'Programete', 'Programete', 'programete', true );
INSERT INTO CATEGORIA ( ID_CATEGORIA, DESCRICAO, NOME, CODIGO, SIMPLEUPLOAD ) VALUES ( nextval('categoria_id_categoria_seq'), 'Chamada Instantânea', 'Chamada Instantânea', 'chamada_inst', true );
INSERT INTO CATEGORIA ( ID_CATEGORIA, DESCRICAO, NOME, CODIGO, SIMPLEUPLOAD ) VALUES ( nextval('categoria_id_categoria_seq'), 'Nome da chamada de Funcionário', 'Chamada Funcionário (Nome)', 'chamada_func_nome', false );
INSERT INTO CATEGORIA ( ID_CATEGORIA, DESCRICAO, NOME, CODIGO, SIMPLEUPLOAD ) VALUES ( nextval('categoria_id_categoria_seq'), 'Frase da chamada de Funcionário', 'Chamada Funcionário (Frase)', 'chamada_func_frase', false );
	
INSERT INTO CATEGORIA values ( nextval('categoria_id_categoria_seq'), 'veic_frase_ini', 'Frase Inicial Chamada Veículo', 'Frase Inicial Chamada Veículo', false );
INSERT INTO CATEGORIA values ( nextval('categoria_id_categoria_seq'), 'veic_marca', 'Marca Chamada Veículo', 'Marca Chamada Veículo', false );
INSERT INTO CATEGORIA values ( nextval('categoria_id_categoria_seq'), 'veic_modelo', 'Modelo Chamada Veículo', 'Modelo Chamada Veículo', false );
INSERT INTO CATEGORIA values ( nextval('categoria_id_categoria_seq'), 'veic_cor', 'Cor Chamada Veículo', 'Cor Chamada Veículo', false );
INSERT INTO CATEGORIA values ( nextval('categoria_id_categoria_seq'), 'veic_placa_letra', 'Placa Chamada Veículo Letras', 'Placa Chadada Veículo Letras', false );
INSERT INTO CATEGORIA values ( nextval('categoria_id_categoria_seq'), 'veic_placa_numero', 'Placa Chamada Veículo Números', 'Placa Chadada Veículo Números', false );
INSERT INTO CATEGORIA values ( nextval('categoria_id_categoria_seq'), 'veic_frase_fim', 'Frase Final Chamada Veículo', 'Frase Final Chamada Veículo', false );

INSERT INTO CATEGORIA values ( nextval('categoria_id_categoria_seq'), 'opcional', 'Opcional', 'Opcional', false );
INSERT INTO CATEGORIA values ( nextval('categoria_id_categoria_seq'), 'silencio', 'Silêncio', 'Silêncio', false );


INSERT INTO FUSO_HORARIO (id_fusohorario, offsetfuso, canonid, alias, ordercomum ) VALUES ( nextval('fuso_horario_id_fusohorario_seq'), '-05:00', 'America/Lima'    , 'Acre', 2 );
INSERT INTO FUSO_HORARIO (id_fusohorario, offsetfuso, canonid, alias, ordercomum ) VALUES ( nextval('fuso_horario_id_fusohorario_seq'), '-04:00', 'America/Manaus'    , 'Manaus', 1 );
INSERT INTO FUSO_HORARIO (id_fusohorario, offsetfuso, canonid, alias, ordercomum ) VALUES ( nextval('fuso_horario_id_fusohorario_seq'), '-03:00', 'America/Sao_Paulo' , 'Brasília', 0 );
INSERT INTO FUSO_HORARIO (id_fusohorario, offsetfuso, canonid, alias, ordercomum ) VALUES ( nextval('fuso_horario_id_fusohorario_seq'), '-02:00', 'America/Noronha'   , 'Fernando Noronha', 4 );


INSERT INTO permissao (id_permissao, codigo, descricao, id_permissaopai) VALUES(nextval('permissao_id_permissao_seq'), 'PAINEL_GERENCIAL', 'Painel Gerencial', NULL);
INSERT INTO permissao (id_permissao, codigo, descricao, id_permissaopai) VALUES(nextval('permissao_id_permissao_seq'), 'ALTERAR_SENHA', 'Alterar Senha', NULL);
INSERT INTO permissao (id_permissao, codigo, descricao, id_permissaopai) VALUES(nextval('permissao_id_permissao_seq'), 'MONITORAMENTO', 'Monitoramento', NULL);
INSERT INTO permissao (id_permissao, codigo, descricao, id_permissaopai) VALUES(nextval('permissao_id_permissao_seq'), 'PLAYER', 'Simulador do Player', NULL);
INSERT INTO permissao (id_permissao, codigo, descricao, id_permissaopai) VALUES(nextval('permissao_id_permissao_seq'), 'USUARIOS', 'Usuários do Sistema', NULL);
INSERT INTO permissao (id_permissao, codigo, descricao, id_permissaopai) VALUES(nextval('permissao_id_permissao_seq'), 'DADOS_CLIENTE', 'Dados de Cliente', NULL);
INSERT INTO permissao (id_permissao, codigo, descricao, id_permissaopai) VALUES(nextval('permissao_id_permissao_seq'), 'PERFIS', 'Perfis de Usuários', NULL);
INSERT INTO permissao (id_permissao, codigo, descricao, id_permissaopai) VALUES(nextval('permissao_id_permissao_seq'), 'UPLOAD_AMBIENTE', 'Upload Ambientes', NULL);
INSERT INTO permissao (id_permissao, codigo, descricao, id_permissaopai) VALUES(nextval('permissao_id_permissao_seq'), 'ADMINISTRAR_AMB', 'Administrar Ambientes', NULL);
INSERT INTO permissao (id_permissao, codigo, descricao, id_permissaopai) VALUES(nextval('permissao_id_permissao_seq'), 'MENSAGENS', 'Mensagens', NULL);
INSERT INTO permissao (id_permissao, codigo, descricao, id_permissaopai) VALUES(nextval('permissao_id_permissao_seq'), 'SOFTWARES', 'Softwares', NULL);
INSERT INTO permissao (id_permissao, codigo, descricao, id_permissaopai) VALUES(nextval('permissao_id_permissao_seq'), 'CHAMADA_FUNCIO', 'Chamada de Funcionários', NULL);
INSERT INTO permissao (id_permissao, codigo, descricao, id_permissaopai) VALUES(nextval('permissao_id_permissao_seq'), 'INCLUIR_AMB', 'Incluir Ambiente', NULL);
INSERT INTO permissao (id_permissao, codigo, descricao, id_permissaopai, exclusivo ) VALUES(nextval('permissao_id_permissao_seq'), 'ADM_SISTEMA', 'Administrar Sistema', NULL, true);




INSERT INTO CLIENTE 
( id_cliente, ativo, nomefantasia, razaosocial, cnpj, codigo, dominio, dataalteracao, datacriacao, email, cep, logradouro, numero, complemento, bairro, cidade, estado )
VALUES 
( nextval('cliente_id_cliente_seq'), true, 'Eterion', 'Eterion', '28372714000140', 'Eterion', 'eterion.com.br', null, now(), 'pazinfernando@gmail.com', '03081010', 'Rua Margarida de Lima', '25', 'APTO 61', 'Tatuapé', 'São Paulo', 'SP' );


INSERT INTO CLIENTE 
( id_cliente, ativo, nomefantasia, razaosocial, cnpj, codigo, dominio, dataalteracao, datacriacao, email, cep, logradouro, numero, complemento, bairro, cidade, estado )
VALUES 
( nextval('cliente_id_cliente_seq'), true, 'Grupo RDL', 'Grupo RDL', '05553155000179', 'rdl', 'grupordl.com.br', null, now(), 'marcelradialista@hotmail.com', '08529310', 'Av. Brasil', '2035', 'sala 13', 'Vila Romanopolis', 'Ferraz de Vasconcelos', 'SP' );


INSERT INTO CLIENTE 
( id_cliente, ativo, nomefantasia, razaosocial, cnpj, codigo, dominio, dataalteracao, datacriacao, email, cep, logradouro, numero, complemento, bairro, cidade, estado )
VALUES 
( nextval('cliente_id_cliente_seq'), true, 'AP3', 'AP3 Projetos e Eventos', '71205686000103', 'ap3', 'ap3.com.br', null, now(), 'gilmar@ap3.com', '08500405', 'Av. Quinze de Novembro', '550', 'Sala 1', 'Vila Romanopolis', 'Ferraz de Vasconcelos', 'SP' );



-- Usuários Padrão
insert into usuario ( id_usuario, login, password, id_cliente, dataalteracao, datacriacao, ativo, nome, email ) 
values ( nextval('usuario_id_usuario_seq'), 'fpazin', '$2a$10$c2AzJBuNjf0GXAxLO89lmOeVpYlwp.geolsgdav9D3b7yoMpfAW7u', ( select id_cliente from cliente where codigo = 'Eterion' ), null, now(), true, 'Fernando Pazin', 'pazinfernando@gmail.com');
insert into usuario ( id_usuario, login, password, id_cliente, dataalteracao, datacriacao, ativo, nome, email ) 
values ( nextval('usuario_id_usuario_seq'), 'gaugusto', '$2a$10$rIcoJp7N6yBr0bV/dsogk.acNO3NrTzlqLY2sFRNAKyMuLTMRgX1u', ( select id_cliente from cliente where codigo = 'Eterion' ), null, now(), true, 'George Augusto', 'george.g.augusto@gmail.com');

insert into usuario ( id_usuario, login, password, id_cliente, dataalteracao, datacriacao, ativo, nome, email ) 
values ( nextval('usuario_id_usuario_seq'), 'marcel', '$2a$10$PFRzo8nLpc0wXPY39jjCOeDXn852hYkTICrbkdTHr3anGqRTDAcx2', ( select id_cliente from cliente where codigo = 'rdl' ), null, now(), true, 'Marcel', 'marcelradialista@hotmail.com');
insert into usuario ( id_usuario, login, password, id_cliente, dataalteracao, datacriacao, ativo, nome, email ) 
values ( nextval('usuario_id_usuario_seq'), 'gilmar', '$2a$10$d/V6dDfa.OPJDj/uH4sS/OfkqG6U1b4OAG6EtzKZGXgimG7Z0EbP2', ( select id_cliente from cliente where codigo = 'ap3' ), null, now(), true, 'Gilmar', 'gilmar@ap3.com');



insert into perfil ( id_perfil, nome, comum ) values ( 1, 'DESENVOLVEDOR', false );
insert into perfil ( id_perfil, nome, comum ) values ( 2, 'ADMINISTRADOR', false );
insert into perfil ( id_perfil, nome, comum ) values ( 3, 'GERENTE', true );
insert into perfil ( id_perfil, nome, comum ) values ( 4, 'SUPERVISOR', true );
insert into perfil ( id_perfil, nome, comum ) values ( 5, 'USUARIO', true );


-- dando as permissões padrão do perfil...
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'INCLUIR_AMB' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'ADMINISTRAR_AMB' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'ALTERAR_SENHA' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'MENSAGENS' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'DADOS_CLIENTE' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'MONITORAMENTO' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'CHAMADA_FUNCIO' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'UPLOAD_AMBIENTE' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'SOFTWARES' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'USUARIOS' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'PLAYER' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'ADM_SISTEMA' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'PERFIS' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'PAINEL_GERENCIAL' ) );


INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'ADMINISTRADOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'INCLUIR_AMB' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'ADMINISTRADOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'ADMINISTRAR_AMB' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'ADMINISTRADOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'ALTERAR_SENHA' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'ADMINISTRADOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'MENSAGENS' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'ADMINISTRADOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'DADOS_CLIENTE' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'ADMINISTRADOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'MONITORAMENTO' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'ADMINISTRADOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'CHAMADA_FUNCIO' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'ADMINISTRADOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'UPLOAD_AMBIENTE' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'ADMINISTRADOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'SOFTWARES' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'ADMINISTRADOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'USUARIOS' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'ADMINISTRADOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'PLAYER' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'ADMINISTRADOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'ADM_SISTEMA' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'ADMINISTRADOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'PERFIS' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'ADMINISTRADOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'PAINEL_GERENCIAL' ) );



INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'GERENTE' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'INCLUIR_AMB' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'GERENTE' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'ADMINISTRAR_AMB' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'GERENTE' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'ALTERAR_SENHA' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'GERENTE' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'MENSAGENS' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'GERENTE' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'DADOS_CLIENTE' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'GERENTE' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'MONITORAMENTO' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'GERENTE' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'CHAMADA_FUNCIO' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'GERENTE' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'UPLOAD_AMBIENTE' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'GERENTE' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'SOFTWARES' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'GERENTE' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'USUARIOS' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'GERENTE' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'PLAYER' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'GERENTE' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'PERFIS' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'GERENTE' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'PAINEL_GERENCIAL' ) );


INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'SUPERVISOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'INCLUIR_AMB' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'SUPERVISOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'ADMINISTRAR_AMB' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'SUPERVISOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'ALTERAR_SENHA' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'SUPERVISOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'MENSAGENS' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'SUPERVISOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'DADOS_CLIENTE' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'SUPERVISOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'MONITORAMENTO' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'SUPERVISOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'CHAMADA_FUNCIO' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'SUPERVISOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'UPLOAD_AMBIENTE' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'SUPERVISOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'SOFTWARES' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'SUPERVISOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'USUARIOS' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'SUPERVISOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'PLAYER' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'SUPERVISOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'PAINEL_GERENCIAL' ) );


INSERT INTO USUARIO_PERFIL VALUES ( nextval('usuario_perfil_id_usuperf_seq'), (select id_perfil from perfil where nome = 'DESENVOLVEDOR'), (SELECT id_usuario FROM USUARIO WHERE login = 'fpazin') );
INSERT INTO USUARIO_PERFIL VALUES ( nextval('usuario_perfil_id_usuperf_seq'), (select id_perfil from perfil where nome = 'DESENVOLVEDOR'), (SELECT id_usuario FROM USUARIO WHERE login = 'gaugusto') );


INSERT INTO USUARIO_PERFIL VALUES ( nextval('usuario_perfil_id_usuperf_seq'), (select id_perfil from perfil where nome = 'ADMINISTRADOR'), (SELECT id_usuario FROM USUARIO WHERE login = 'marcel') );
INSERT INTO USUARIO_PERFIL VALUES ( nextval('usuario_perfil_id_usuperf_seq'), (select id_perfil from perfil where nome = 'ADMINISTRADOR'), (SELECT id_usuario FROM USUARIO WHERE login = 'gilmar') );


--INSERT INTO USUARIO_PERMISSAO VALUES ( NEXTVAL('USUARIO_PERMISSAO_ID_USUPERM_SEQ'), NOW(), ( SELECT ID_PERMISSAO FROM PERMISSAO WHERE CODIGO = 'INCLUIR_AMB'), (SELECT ID_USUARIO FROM USUARIO WHERE LOGIN = 'FPAZIN') );



-- ALGUNS GÊNEROS... MELHORAR


insert into genero (id_genero, datacriacao, descricao, genero ) values ( nextval('genero_id_genero_seq'), now(), 'Top 300 - Lançamentos' ,'Top 300 - Lançamentos' );
insert into genero (id_genero, datacriacao, descricao, genero ) values ( nextval('genero_id_genero_seq'), now(), 'Internacional' ,'Internacional' );
insert into genero (id_genero, datacriacao, descricao, genero ) values ( nextval('genero_id_genero_seq'), now(), 'Pop Rock Nacional' ,'Pop Rock Nacional' );
insert into genero (id_genero, datacriacao, descricao, genero ) values ( nextval('genero_id_genero_seq'), now(), 'Sertanejos' ,'Sertanejos' );
insert into genero (id_genero, datacriacao, descricao, genero ) values ( nextval('genero_id_genero_seq'), now(), 'Sertanejo universitário' ,'Sertanejo universitário' );
insert into genero (id_genero, datacriacao, descricao, genero ) values ( nextval('genero_id_genero_seq'), now(), 'Pagode e Samba' ,'Pagode e Samba' );
insert into genero (id_genero, datacriacao, descricao, genero ) values ( nextval('genero_id_genero_seq'), now(), 'Axé Music' ,'Axé Music' );
insert into genero (id_genero, datacriacao, descricao, genero ) values ( nextval('genero_id_genero_seq'), now(), 'Forró' ,'Forró' );
insert into genero (id_genero, datacriacao, descricao, genero ) values ( nextval('genero_id_genero_seq'), now(), 'Country' ,'Country' );
insert into genero (id_genero, datacriacao, descricao, genero ) values ( nextval('genero_id_genero_seq'), now(), 'Dance Music' ,'Dance Music' );
insert into genero (id_genero, datacriacao, descricao, genero ) values ( nextval('genero_id_genero_seq'), now(), 'Flashback' ,'Flashback' );
insert into genero (id_genero, datacriacao, descricao, genero ) values ( nextval('genero_id_genero_seq'), now(), 'Infantil' ,'Infantil' );
insert into genero (id_genero, datacriacao, descricao, genero ) values ( nextval('genero_id_genero_seq'), now(), 'Bossa Nova' ,'Bossa Nova' );
insert into genero (id_genero, datacriacao, descricao, genero ) values ( nextval('genero_id_genero_seq'), now(), 'Reggae Nacional' ,'Reggae Nacional' );
insert into genero (id_genero, datacriacao, descricao, genero ) values ( nextval('genero_id_genero_seq'), now(), 'Arrocha' ,'Arrocha' );
insert into genero (id_genero, datacriacao, descricao, genero ) values ( nextval('genero_id_genero_seq'), now(), 'Fitness Music' ,'Fitness Music' );
insert into genero (id_genero, datacriacao, descricao, genero ) values ( nextval('genero_id_genero_seq'), now(), 'Rock Internacional' ,'Rock Internacional' );
insert into genero (id_genero, datacriacao, descricao, genero ) values ( nextval('genero_id_genero_seq'), now(), 'Sertanejo Romântico' ,'Sertanejo Romântico' );
insert into genero (id_genero, datacriacao, descricao, genero ) values ( nextval('genero_id_genero_seq'), now(), 'Gospel Internacional' ,'Gospel Internacional' );
insert into genero (id_genero, datacriacao, descricao, genero ) values ( nextval('genero_id_genero_seq'), now(), 'Lounge' ,'Lounge' );
insert into genero (id_genero, datacriacao, descricao, genero ) values ( nextval('genero_id_genero_seq'), now(), 'Flash House' ,'Flash House' );
insert into genero (id_genero, datacriacao, descricao, genero ) values ( nextval('genero_id_genero_seq'), now(), 'Disco' ,'Disco' );
insert into genero (id_genero, datacriacao, descricao, genero ) values ( nextval('genero_id_genero_seq'), now(), 'Instrumental' ,'Instrumental' );






/*
insert into  PARAMETRO ( id_parametro, id_cliente, codigo, valor, descricao, type )
values ( nextval('parametro_id_parametro_seq'), null, 'NEW_MIDIA_PATH', '/home/pazin/musicas/Exceto Sertanejas/AC-DC/', 'Pasta em disco onde estão as músicas novas que precisam ser importadas', null );
*/


insert into  PARAMETRO ( id_parametro, id_cliente, codigo, valor, descricao, type )
values ( nextval('parametro_id_parametro_seq'), null, 'BASE_MIDIA_PATH', '/home/ubuntu/repositorioMusicas/', 'Pasta em disco onde serão armazenadas as músicas EX: C:\Temp\Musicas', null );


insert into  PARAMETRO ( id_parametro, id_cliente, codigo, valor, descricao, type )
values ( nextval('parametro_id_parametro_seq'), null, 'BITRATE_TYPE', 'VARIABLE', 'Tipo de BitRate utilizado na Conversão de MP3 (Médio, Constante ou Variável)', null );

insert into  PARAMETRO ( id_parametro, id_cliente, codigo, valor, descricao, type )
values ( nextval('parametro_id_parametro_seq'), null, 'VALOR_BITRATE', '5', 'Valor padrão do BitRate utilizado na Conversão de MP3 (Ex: 96, 128 para constante e médio ou 4, 5 no caso de variável)', null );




insert into  PARAMETRO ( id_parametro, id_cliente, codigo, valor, descricao, type )
values ( nextval('parametro_id_parametro_seq'), ( SELECT ID_cliente FROM cliente WHERE CODIGO = 'rdl' ), 'TEMA', '', 'Tema de CSS utilizado dentro do Gerenciador', null );

insert into  PARAMETRO ( id_parametro, id_cliente, codigo, valor, descricao, type )
values ( nextval('parametro_id_parametro_seq'), ( SELECT ID_cliente FROM cliente WHERE CODIGO = 'rdl' ), 'BACKGROUNDCOLOR', '', 'Cor de fundo utilizada dentro do Gerenciador. Não pode ser utilizada junto com tema.', null );

insert into  PARAMETRO ( id_parametro, id_cliente, codigo, valor, descricao, type )
values ( nextval('parametro_id_parametro_seq'), ( SELECT ID_cliente FROM cliente WHERE CODIGO = 'rdl' ), 'APARENCIA', 'TEMA', 'Configuração que determina se vai usar TEMA ou BACKGROUNDCOLOR', null );


insert into  PARAMETRO ( id_parametro, id_cliente, codigo, valor, descricao, type )
values ( nextval('parametro_id_parametro_seq'), ( SELECT ID_cliente FROM cliente WHERE CODIGO = 'Eterion' ), 'TEMA', 'darkly', 'Tema de CSS utilizado dentro do Gerenciador', null );

insert into  PARAMETRO ( id_parametro, id_cliente, codigo, valor, descricao, type )
values ( nextval('parametro_id_parametro_seq'), ( SELECT ID_cliente FROM cliente WHERE CODIGO = 'Eterion' ), 'BACKGROUNDCOLOR', '#54678b', 'Cor de fundo utilizada dentro do Gerenciador. Não pode ser utilizada junto com tema.', null );

insert into  PARAMETRO ( id_parametro, id_cliente, codigo, valor, descricao, type )
values ( nextval('parametro_id_parametro_seq'), ( SELECT ID_cliente FROM cliente WHERE CODIGO = 'Eterion' ), 'APARENCIA', 'TEMA', 'Configuração que determina se vai usar TEMA ou BACKGROUNDCOLOR', null );


insert into  PARAMETRO ( id_parametro, id_cliente, codigo, valor, descricao, type )
values ( nextval('parametro_id_parametro_seq'), ( SELECT ID_cliente FROM cliente WHERE CODIGO = 'ap3' ), 'TEMA', '', 'Tema de CSS utilizado dentro do Gerenciador', null );

insert into  PARAMETRO ( id_parametro, id_cliente, codigo, valor, descricao, type )
values ( nextval('parametro_id_parametro_seq'), ( SELECT ID_cliente FROM cliente WHERE CODIGO = 'ap3' ), 'BACKGROUNDCOLOR', '', 'Cor de fundo utilizada dentro do Gerenciador. Não pode ser utilizada junto com tema.', null );

insert into  PARAMETRO ( id_parametro, id_cliente, codigo, valor, descricao, type )
values ( nextval('parametro_id_parametro_seq'), ( SELECT ID_cliente FROM cliente WHERE CODIGO = 'ap3' ), 'APARENCIA', 'TEMA', 'Configuração que determina se vai usar TEMA ou BACKGROUNDCOLOR', null );






insert into funcionalidade ( ordem, nome, url, icone, ativo, codigo ) values ( 1,  'Gêneros' ,              '/ambientes/%d/generos/view' ,                  'fa-music', true  , 'generos' );
insert into funcionalidade ( ordem, nome, url, icone, ativo, codigo ) values ( 2,  'Vinhetas' ,             '/ambientes/%d/view-upload-midia/vinheta/' ,      'fa-file-audio-o', true  , 'vinheta' );
insert into funcionalidade ( ordem, nome, url, icone, ativo, codigo ) values ( 3,  'Institucionais',        '/ambientes/%d/view-upload-midia/inst/' ,         'fa-headphones', true  , 'inst' );
insert into funcionalidade ( ordem, nome, url, icone, ativo, codigo ) values ( 4,  'Comerciais' ,           '/ambientes/%d/view-upload-midia/comercial/' ,    'fa-film', true  , 'comercial' );
insert into funcionalidade ( ordem, nome, url, icone, ativo, codigo ) values ( 5,  'Programetes' ,          '/ambientes/%d/view-upload-midia/programete/' ,   'fa-bullhorn', true  , 'programete' );
insert into funcionalidade ( ordem, nome, url, icone, ativo, codigo ) values ( 6,  'Chamadas<br/>Instantâneas' ,  '/ambientes/%d/view-upload-midia/chamada_inst/' ,   'fa-bolt', true  , 'chamada_inst' );
insert into funcionalidade ( ordem, nome, url, icone, ativo, codigo ) values ( 7,  'Chamadas<br/>Funcionários' ,   '/ambientes/%d/view-chamada-funcionarios',     'fa-users', true  , 'chamada_func' );
insert into funcionalidade ( ordem, nome, url, icone, ativo, codigo ) values ( 8,  'Blocos' ,               '/ambientes/%d/blocos/view' ,             'fa-th-large', true  , 'blocos' );
insert into funcionalidade ( ordem, nome, url, icone, ativo, codigo ) values ( 9,  'Expediente' ,           '/ambientes/%d/expedientes/view' ,          'fa-clock-o', true  , 'expediente' );
insert into funcionalidade ( ordem, nome, url, icone, ativo, codigo ) values ( 10, 'Eventos' ,              '/ambientes/%d/eventos/view' ,            'fa-newspaper-o', true  , 'eventos' );
insert into funcionalidade ( ordem, nome, url, icone, ativo, codigo ) values ( 11, 'Programação<br/>Musical' ,    '/ambientes/%d/programacoes/view' ,       'fa-list-ol', true  , 'prog_musical' );
insert into funcionalidade ( ordem, nome, url, icone, ativo, codigo ) values ( 12, 'Configurações' ,          '/ambientes/%d/configuracoes/view' ,        'fa-briefcase', true  , 'config' );
insert into funcionalidade ( ordem, nome, url, icone, ativo, codigo ) values ( 13, 'Relatórios' ,           '/ambientes/%d/relatorios/view' ,         'fa-files-o', true  , 'relatorios' );
insert into funcionalidade ( ordem, nome, url, icone, ativo, codigo ) values ( 14, 'Downloads' ,            '/ambientes/%d/downloads/view' ,          'fa-floppy-o', true  , 'downloads' );
insert into funcionalidade ( ordem, nome, url, icone, ativo, codigo ) values ( 15, 'Logomarca' ,            '/ambientes/%d/logomarcas/view' ,           'fa-trademark', true  , 'logomarca' );
insert into funcionalidade ( ordem, nome, url, icone, extrahtml, ativo, codigo ) values ( 16, 'Simular' ,               '/player/ambientes/%d/player/view' ,          'fa-play' , ' target="_blank" ', true  , 'simular' );
insert into funcionalidade ( ordem, nome, url, icone, ativo, codigo ) values ( 17, 'SendVoice' ,            '/ambientes/%d/sendvoices/view' ,           'fa-microphone', false  , 'sendvoice' );


-- MELHORAR ISSO AQUI PARA FICAR TUDO NO INSERT

update funcionalidade set size_big = 'md-48', size_small = 'md-18', classes_icone = 'material-icons', icone = 'library_music'	where codigo = 'generos';
update funcionalidade set size_big = 'fa-3x', size_small = '', 		classes_icone = 'fa fa-file-audio-o', icone = ''  			where codigo = 'vinheta';
update funcionalidade set size_big = 'fa-3x', size_small = '', 		classes_icone = 'fa fa-headphones', icone = ''  			where codigo = 'inst';
update funcionalidade set size_big = 'fa-3x', size_small = '', 		classes_icone = 'fa fa-film', icone = ''  					where codigo = 'comercial';
update funcionalidade set size_big = 'fa-3x', size_small = '', 		classes_icone = 'fa fa-bullhorn', icone = ''  				where codigo = 'programete';
update funcionalidade set size_big = 'fa-3x', size_small = '', 		classes_icone = 'fa fa-bolt', icone = ''  					where codigo = 'chamada_inst';
update funcionalidade set size_big = 'fa-3x', size_small = '', 		classes_icone = 'fa fa-users', icone = ''  					where codigo = 'chamada_func';
update funcionalidade set size_big = 'md-48', size_small = 'md-18', classes_icone = 'material-icons', icone = 'view_comfy'  	where codigo = 'blocos';
update funcionalidade set size_big = 'md-48', size_small = 'md-18', classes_icone = 'material-icons', icone = 'watch_later'  	where codigo = 'expediente';
update funcionalidade set size_big = 'md-48', size_small = 'md-18', classes_icone = 'material-icons', icone = 'alarm'  			where codigo = 'eventos';
update funcionalidade set size_big = 'md-48', size_small = 'md-18', classes_icone = 'material-icons', icone = 'grid_on'  		where codigo = 'prog_musical';
update funcionalidade set size_big = 'md-48', size_small = 'md-18', classes_icone = 'material-icons', icone = 'settings' 		where codigo = 'config';
update funcionalidade set size_big = 'fa-3x', size_small = '', 		classes_icone = 'fa fa-files-o', icone = ''  				where codigo = 'relatorios';
update funcionalidade set size_big = 'fa-3x', size_small = '', 		classes_icone = 'fa fa-floppy-o', icone = ''  				where codigo = 'downloads';
update funcionalidade set size_big = 'md-48', size_small = 'md-18', classes_icone = 'material-icons', icone = 'wallpaper'	  	where codigo = 'logomarca';
update funcionalidade set size_big = 'fa-3x', size_small = '', 		classes_icone = 'fa fa-play', icone = ''  					where codigo = 'simular';
update funcionalidade set size_big = 'fa-3x', size_small = '', 		classes_icone = 'fa fa-microphone', icone = ''  			where codigo = 'sendvoice';






insert into tipo_taxa ( descricao, por_ambiente )  values ( 'Serviço', false );
insert into tipo_taxa ( descricao, por_ambiente )  values ( 'Armazenamento', false );
insert into tipo_taxa ( descricao, por_ambiente )  values ( 'Taxa Genérica', false );
insert into tipo_taxa ( descricao, por_ambiente )  values ( 'Atraso por Ambiente', true );
insert into tipo_taxa ( descricao, operacao, por_ambiente )  values ( 'Desconto Genérico', 'DESCONTO' ,true );


insert into condicao_comercial ( dataalteracao, definicaotaxa, valor, id_cliente, id_tipotaxa ) values ( now(), 'VALOR', 30, null, ( select id_tipotaxa from tipo_taxa where descricao = 'Serviço' ) );
insert into condicao_comercial ( dataalteracao, definicaotaxa, valor, id_cliente, id_tipotaxa ) values ( now(), 'VALOR', 2, null, ( select id_tipotaxa from tipo_taxa where descricao = 'Armazenamento' ) );
insert into condicao_comercial ( dataalteracao, definicaotaxa, valor, id_cliente, id_tipotaxa ) values ( now(), 'PORCENTAGEM', 2.3, null, ( select id_tipotaxa from tipo_taxa where descricao = 'Atraso por Ambiente' ) );



insert into condicao_comercial ( dataalteracao, definicaotaxa, valor, id_cliente, id_tipotaxa ) values ( now(), 'VALOR', 30, ( select id_cliente from cliente where codigo = 'Eterion' ), ( select id_tipotaxa from tipo_taxa where descricao = 'Serviço' ) );
insert into condicao_comercial ( dataalteracao, definicaotaxa, valor, id_cliente, id_tipotaxa ) values ( now(), 'VALOR', 2, ( select id_cliente from cliente where codigo = 'Eterion' ), ( select id_tipotaxa from tipo_taxa where descricao = 'Armazenamento' ) );
insert into condicao_comercial ( dataalteracao, definicaotaxa, valor, id_cliente, id_tipotaxa ) values ( now(), 'PORCENTAGEM', 2.3, ( select id_cliente from cliente where codigo = 'Eterion' ), ( select id_tipotaxa from tipo_taxa where descricao = 'Atraso por Ambiente' ) );


