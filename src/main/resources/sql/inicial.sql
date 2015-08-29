
/* Esse script cria alguns dados básicos necessários como as categorias, usuários iniciais e permissões básicas */


-- SCRIPT INICIAL


INSERT INTO CATEGORIA VALUES ( nextval('categoria_id_categoria_seq'), 'Música', 'Música' );
INSERT INTO CATEGORIA VALUES ( nextval('categoria_id_categoria_seq'), 'Vinheta', 'Vinheta' );
INSERT INTO CATEGORIA VALUES ( nextval('categoria_id_categoria_seq'), 'Institucional', 'Institucional' );
INSERT INTO CATEGORIA VALUES ( nextval('categoria_id_categoria_seq'), 'Comercial', 'Comercial' );
INSERT INTO CATEGORIA VALUES ( nextval('categoria_id_categoria_seq'), 'Programete', 'Programete' );
INSERT INTO CATEGORIA VALUES ( nextval('categoria_id_categoria_seq'), 'Chamada Funcionário', 'Chamada Funcionário' );
INSERT INTO CATEGORIA VALUES ( nextval('categoria_id_categoria_seq'), 'Chamada Instantânea', 'Chamada Instantânea' );


INSERT INTO FUSO_HORARIO (id_fusohorario, offsetfuso, canonid, alias, ordercomum ) VALUES ( nextval('fuso_horario_id_fusohorario_seq'), '-05:00', 'America/Lima'    , 'Acre', 2 );
INSERT INTO FUSO_HORARIO (id_fusohorario, offsetfuso, canonid, alias, ordercomum ) VALUES ( nextval('fuso_horario_id_fusohorario_seq'), '-04:00', 'America/Manaus'    , 'Manaus', 1 );
INSERT INTO FUSO_HORARIO (id_fusohorario, offsetfuso, canonid, alias, ordercomum ) VALUES ( nextval('fuso_horario_id_fusohorario_seq'), '-03:00', 'America/Sao_Paulo' , 'Brasília', 0 );
INSERT INTO FUSO_HORARIO (id_fusohorario, offsetfuso, canonid, alias, ordercomum ) VALUES ( nextval('fuso_horario_id_fusohorario_seq'), '-02:00', 'America/Noronha'   , 'Fernando Noronha', 4 );


insert into permissao ( id_permissao, codigo, descricao, id_permissaopai ) values ( nextval('permissao_id_permissao_seq') , 'INCLUIR_AMB', '', null);
insert into permissao ( id_permissao, codigo, descricao, id_permissaopai ) values ( nextval('permissao_id_permissao_seq') , 'ADMINISTRAR_AMB', '', null);
insert into permissao ( id_permissao, codigo, descricao, id_permissaopai ) values ( nextval('permissao_id_permissao_seq') , 'ALTERAR_SENHA', '', null);
insert into permissao ( id_permissao, codigo, descricao, id_permissaopai ) values ( nextval('permissao_id_permissao_seq') , 'MENSAGENS', '', null);
insert into permissao ( id_permissao, codigo, descricao, id_permissaopai ) values ( nextval('permissao_id_permissao_seq') , 'MONITORAMENTO', '', null);
insert into permissao ( id_permissao, codigo, descricao, id_permissaopai ) values ( nextval('permissao_id_permissao_seq') , 'CHAMADA_FUNCIO', '', null);
insert into permissao ( id_permissao, codigo, descricao, id_permissaopai ) values ( nextval('permissao_id_permissao_seq') , 'UPLOAD_AMBIENTE', '', null);
insert into permissao ( id_permissao, codigo, descricao, id_permissaopai ) values ( nextval('permissao_id_permissao_seq') , 'FERRAMENTAS', '', null);
insert into permissao ( id_permissao, codigo, descricao, id_permissaopai ) values ( nextval('permissao_id_permissao_seq') , 'SOFTWARES', '', null);
insert into permissao ( id_permissao, codigo, descricao, id_permissaopai ) values ( nextval('permissao_id_permissao_seq') , 'ADMINISTRADORES', '', null);
insert into permissao ( id_permissao, codigo, descricao, id_permissaopai ) values ( nextval('permissao_id_permissao_seq') , 'MOBILE', '', null);

-- Usuários Padrão
insert into usuario ( id_usuario, login, password, dataalteracao, datacriacao, ativo, nome, email ) 
values ( nextval('usuario_id_usuario_seq'), 'fpazin', '$2a$08$jzf4G7i5TxtpYwZwEpsguudbkTgm2vmTmClah6sZkp9FqhGAG5uMC', null, now(), true, 'Fernando Pazin', 'pazinfernando@gmail.com');
insert into usuario ( id_usuario, login, password, dataalteracao, datacriacao, ativo, nome, email ) 
values ( nextval('usuario_id_usuario_seq'), 'gaugusto', '$2a$08$jzf4G7i5TxtpYwZwEpsguudbkTgm2vmTmClah6sZkp9FqhGAG5uMC', null, now(), true, 'George Augusto', 'george.g.augusto@gmail.com');

insert into perfil values ( nextval('perfil_id_perfil_seq'), 'DESENVOLVEDOR' );
insert into perfil values ( nextval('perfil_id_perfil_seq'), 'ADMINISTRADOR' );
insert into perfil values ( nextval('perfil_id_perfil_seq'), 'GERENTE' );
insert into perfil values ( nextval('perfil_id_perfil_seq'), 'SUPERVISOR' );
insert into perfil values ( nextval('perfil_id_perfil_seq'), 'USUARIO' );


-- dando as permissões padrão do perfil...
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'INCLUIR_AMB' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'ADMINISTRAR_AMB' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'ALTERAR_SENHA' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'MENSAGENS' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'MONITORAMENTO' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'CHAMADA_FUNCIO' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'UPLOAD_AMBIENTE' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'FERRAMENTAS' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'SOFTWARES' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'ADMINISTRADORES' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'MOBILE' ) );


INSERT INTO USUARIO_PERFIL VALUES ( nextval('usuario_perfil_id_usuperf_seq'), (select id_perfil from perfil where nome = 'DESENVOLVEDOR'), (SELECT id_usuario FROM USUARIO WHERE login = 'fpazin') );
INSERT INTO USUARIO_PERFIL VALUES ( nextval('usuario_perfil_id_usuperf_seq'), (select id_perfil from perfil where nome = 'DESENVOLVEDOR'), (SELECT id_usuario FROM USUARIO WHERE login = 'gaugusto') );


INSERT INTO USUARIO_PERMISSAO VALUES ( NEXTVAL('USUARIO_PERMISSAO_ID_USUPERM_SEQ'), NOW(), ( SELECT ID_PERMISSAO FROM PERMISSAO WHERE CODIGO = 'INCLUIR_AMB'), (SELECT ID_USUARIO FROM USUARIO WHERE LOGIN = 'FPAZIN') );

