

drop table if exists mensagem;
drop table if exists conexao;
drop table if exists acesso_usuario;
drop table if exists usuario_permissao;
drop table if exists perfil_permissao;
drop table if exists usuario_perfil;
drop table if exists perfil;
drop table if exists midia_genero;
drop table if exists genero;
drop table if exists midia_ambiente;
drop table if exists midia;
drop table if exists ambiente;
drop table if exists fuso_horario;
drop table if exists usuario;
drop table if exists categoria;
drop table if exists permissao;

-- SCRIPT INICIAL


INSERT INTO CATEGORIA VALUES ( nextval('categoria_id_categoria_cat_seq'), 'Música', 'Música' );
INSERT INTO CATEGORIA VALUES ( nextval('categoria_id_categoria_cat_seq'), 'Vinheta', 'Vinheta' );
INSERT INTO CATEGORIA VALUES ( nextval('categoria_id_categoria_cat_seq'), 'Institucional', 'Institucional' );
INSERT INTO CATEGORIA VALUES ( nextval('categoria_id_categoria_cat_seq'), 'Comercial', 'Comercial' );
INSERT INTO CATEGORIA VALUES ( nextval('categoria_id_categoria_cat_seq'), 'Programete', 'Programete' );
INSERT INTO CATEGORIA VALUES ( nextval('categoria_id_categoria_cat_seq'), 'Chamada Funcionário', 'Chamada Funcionário' );
INSERT INTO CATEGORIA VALUES ( nextval('categoria_id_categoria_cat_seq'), 'Chamada Instantânea', 'Chamada Instantânea' );


INSERT INTO FUSO_HORARIO (id_fusohorario_fuh, ds_offset_fuh, ds_canonid_fuh, nm_alias_fuh, id_ordercomum_fuh ) VALUES ( nextval('fuso_horario_id_fusohorario_fuh_seq'), '-05:00', 'America/Lima'    , 'Acre', 2 );
INSERT INTO FUSO_HORARIO (id_fusohorario_fuh, ds_offset_fuh, ds_canonid_fuh, nm_alias_fuh, id_ordercomum_fuh ) VALUES ( nextval('fuso_horario_id_fusohorario_fuh_seq'), '-04:00', 'America/Manaus'    , 'Manaus', 1 );
INSERT INTO FUSO_HORARIO (id_fusohorario_fuh, ds_offset_fuh, ds_canonid_fuh, nm_alias_fuh, id_ordercomum_fuh ) VALUES ( nextval('fuso_horario_id_fusohorario_fuh_seq'), '-03:00', 'America/Sao_Paulo' , 'Brasília', 0 );
INSERT INTO FUSO_HORARIO (id_fusohorario_fuh, ds_offset_fuh, ds_canonid_fuh, nm_alias_fuh, id_ordercomum_fuh ) VALUES ( nextval('fuso_horario_id_fusohorario_fuh_seq'), '-02:00', 'America/Noronha'   , 'Fernando Noronha', 4 );


insert into permissao ( id_permissao_prm, cd_permiss_prm, ds_permiss_prm, id_permissaopai_prm ) values ( nextval('permissao_id_permissao_prm_seq') , 'INCLUIR_AMB', '', null);
insert into permissao ( id_permissao_prm, cd_permiss_prm, ds_permiss_prm, id_permissaopai_prm ) values ( nextval('permissao_id_permissao_prm_seq') , 'ADMINISTRAR_AMB', '', null);
insert into permissao ( id_permissao_prm, cd_permiss_prm, ds_permiss_prm, id_permissaopai_prm ) values ( nextval('permissao_id_permissao_prm_seq') , 'ALTERAR_SENHA', '', null);
insert into permissao ( id_permissao_prm, cd_permiss_prm, ds_permiss_prm, id_permissaopai_prm ) values ( nextval('permissao_id_permissao_prm_seq') , 'MENSAGENS', '', null);
insert into permissao ( id_permissao_prm, cd_permiss_prm, ds_permiss_prm, id_permissaopai_prm ) values ( nextval('permissao_id_permissao_prm_seq') , 'MONITORAMENTO', '', null);
insert into permissao ( id_permissao_prm, cd_permiss_prm, ds_permiss_prm, id_permissaopai_prm ) values ( nextval('permissao_id_permissao_prm_seq') , 'CHAMADA_FUNCIO', '', null);
insert into permissao ( id_permissao_prm, cd_permiss_prm, ds_permiss_prm, id_permissaopai_prm ) values ( nextval('permissao_id_permissao_prm_seq') , 'UPLOAD_AMBIENTE', '', null);
insert into permissao ( id_permissao_prm, cd_permiss_prm, ds_permiss_prm, id_permissaopai_prm ) values ( nextval('permissao_id_permissao_prm_seq') , 'FERRAMENTAS', '', null);
insert into permissao ( id_permissao_prm, cd_permiss_prm, ds_permiss_prm, id_permissaopai_prm ) values ( nextval('permissao_id_permissao_prm_seq') , 'SOFTWARES', '', null);
insert into permissao ( id_permissao_prm, cd_permiss_prm, ds_permiss_prm, id_permissaopai_prm ) values ( nextval('permissao_id_permissao_prm_seq') , 'ADMINISTRADORES', '', null);
insert into permissao ( id_permissao_prm, cd_permiss_prm, ds_permiss_prm, id_permissaopai_prm ) values ( nextval('permissao_id_permissao_prm_seq') , 'MOBILE', '', null);

-- Usuários Padrão
insert into usuario ( id_usuario_usu, cd_login_usu, cd_password_usu, dt_alteracao_usu, dt_criacao_usu, fl_ativo_usu, nm_usuario_usu ) 
values ( nextval('usuario_id_usuario_usu_seq'), 'fpazin', '12456', null, now(), true, 'Fernando Pazin');
insert into usuario ( id_usuario_usu, cd_login_usu, cd_password_usu, dt_alteracao_usu, dt_criacao_usu, fl_ativo_usu, nm_usuario_usu ) 
values ( nextval('usuario_id_usuario_usu_seq'), 'gaugusto', '12456', null, now(), true, 'George Augusto');

insert into perfil values ( nextval('perfil_id_perfil_per_seq'), 'DESENVOLVEDOR' );
insert into perfil values ( nextval('perfil_id_perfil_per_seq'), 'ADMINISTRADOR' );
insert into perfil values ( nextval('perfil_id_perfil_per_seq'), 'GERENTE' );
insert into perfil values (nextval('perfil_id_perfil_per_seq'), 'SUPERVISOR' );
insert into perfil values ( nextval('perfil_id_perfil_per_seq'), 'USUARIO' );


-- dando as permissões padrão do perfil...
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_prp_seq'), now(), ( select id_perfil_per from perfil where nm_perfil_per = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO_PRM FROM PERMISSAO WHERE CD_PERMISS_PRM = 'INCLUIR_AMB' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_prp_seq'), now(), ( select id_perfil_per from perfil where nm_perfil_per = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO_PRM FROM PERMISSAO WHERE CD_PERMISS_PRM = 'ADMINISTRAR_AMB' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_prp_seq'), now(), ( select id_perfil_per from perfil where nm_perfil_per = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO_PRM FROM PERMISSAO WHERE CD_PERMISS_PRM = 'ALTERAR_SENHA' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_prp_seq'), now(), ( select id_perfil_per from perfil where nm_perfil_per = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO_PRM FROM PERMISSAO WHERE CD_PERMISS_PRM = 'MENSAGENS' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_prp_seq'), now(), ( select id_perfil_per from perfil where nm_perfil_per = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO_PRM FROM PERMISSAO WHERE CD_PERMISS_PRM = 'MONITORAMENTO' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_prp_seq'), now(), ( select id_perfil_per from perfil where nm_perfil_per = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO_PRM FROM PERMISSAO WHERE CD_PERMISS_PRM = 'CHAMADA_FUNCIO' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_prp_seq'), now(), ( select id_perfil_per from perfil where nm_perfil_per = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO_PRM FROM PERMISSAO WHERE CD_PERMISS_PRM = 'UPLOAD_AMBIENTE' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_prp_seq'), now(), ( select id_perfil_per from perfil where nm_perfil_per = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO_PRM FROM PERMISSAO WHERE CD_PERMISS_PRM = 'FERRAMENTAS' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_prp_seq'), now(), ( select id_perfil_per from perfil where nm_perfil_per = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO_PRM FROM PERMISSAO WHERE CD_PERMISS_PRM = 'SOFTWARES' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_prp_seq'), now(), ( select id_perfil_per from perfil where nm_perfil_per = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO_PRM FROM PERMISSAO WHERE CD_PERMISS_PRM = 'ADMINISTRADORES' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_prp_seq'), now(), ( select id_perfil_per from perfil where nm_perfil_per = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO_PRM FROM PERMISSAO WHERE CD_PERMISS_PRM = 'MOBILE' ) );


INSERT INTO USUARIO_PERFIL VALUES ( nextval('usuario_perfil_id_usuperf_upf_seq'), (select id_perfil_per from perfil where nm_perfil_per = 'DESENVOLVEDOR'), (SELECT id_usuario_usu FROM USUARIO WHERE cd_login_usu = 'fpazin') );
INSERT INTO USUARIO_PERFIL VALUES ( nextval('usuario_perfil_id_usuperf_upf_seq'), (select id_perfil_per from perfil where nm_perfil_per = 'DESENVOLVEDOR'), (SELECT id_usuario_usu FROM USUARIO WHERE cd_login_usu = 'gaugusto') );


INSERT INTO USUARIO_PERMISSAO VALUES ( NEXTVAL('USUARIO_PERMISSAO_ID_USUPERM_UPP_SEQ'), NOW(), ( SELECT ID_PERMISSAO_PRM FROM PERMISSAO WHERE CD_PERMISS_PRM = 'INCLUIR_AMB'), (SELECT ID_USUARIO_USU FROM USUARIO WHERE CD_LOGIN_USU = 'FPAZIN') );

