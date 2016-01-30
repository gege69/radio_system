
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
insert into permissao ( id_permissao, codigo, descricao, id_permissaopai ) values ( nextval('permissao_id_permissao_seq') , 'USUARIOS', '', null);
insert into permissao ( id_permissao, codigo, descricao, id_permissaopai ) values ( nextval('permissao_id_permissao_seq') , 'MOBILE', '', null);
insert into permissao ( id_permissao, codigo, descricao, id_permissaopai ) values ( nextval('permissao_id_permissao_seq') , 'PLAYER', '', null);
insert into permissao ( id_permissao, codigo, descricao, id_permissaopai ) values ( nextval('permissao_id_permissao_seq') , 'ADM_SISTEMA', '', null);




INSERT INTO CLIENTE ( id_cliente ,cnpj ,codigo ,razaosocial ,nomefantasia ,dominio ,datacriacao ,dataalteracao ,ativo )
VALUES ( nextval('cliente_id_cliente_seq'), '28372714000140', 'Eterion', 'Eterion', 'Eterion', 'www.eterion.com.br', now(), null, true );



-- Usuários Padrão
insert into usuario ( id_usuario, login, password, id_cliente, dataalteracao, datacriacao, ativo, nome, email ) 
values ( nextval('usuario_id_usuario_seq'), 'fpazin', '$2a$08$jzf4G7i5TxtpYwZwEpsguudbkTgm2vmTmClah6sZkp9FqhGAG5uMC', ( select id_cliente from cliente where codigo = 'Eterion' ), null, now(), true, 'Fernando Pazin', 'pazinfernando@gmail.com');
insert into usuario ( id_usuario, login, password, id_cliente, dataalteracao, datacriacao, ativo, nome, email ) 
values ( nextval('usuario_id_usuario_seq'), 'gaugusto', '$2a$08$jzf4G7i5TxtpYwZwEpsguudbkTgm2vmTmClah6sZkp9FqhGAG5uMC', ( select id_cliente from cliente where codigo = 'Eterion' ), null, now(), true, 'George Augusto', 'george.g.augusto@gmail.com');

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
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'USUARIOS' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'MOBILE' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'PLAYER' ) );
INSERT INTO PERFIL_PERMISSAO VALUES ( nextval('perfil_permissao_id_perfperm_seq'), now(), ( select id_perfil from perfil where nome = 'DESENVOLVEDOR' ),( SELECT ID_PERMISSAO FROM PERMISSAO WHERE codigo = 'ADM_SISTEMA' ) );



INSERT INTO USUARIO_PERFIL VALUES ( nextval('usuario_perfil_id_usuperf_seq'), (select id_perfil from perfil where nome = 'DESENVOLVEDOR'), (SELECT id_usuario FROM USUARIO WHERE login = 'fpazin') );
INSERT INTO USUARIO_PERFIL VALUES ( nextval('usuario_perfil_id_usuperf_seq'), (select id_perfil from perfil where nome = 'DESENVOLVEDOR'), (SELECT id_usuario FROM USUARIO WHERE login = 'gaugusto') );


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






insert into  PARAMETRO ( id_parametro, id_cliente, codigo, valor, descricao, type )
values ( nextval('parametro_id_parametro_seq'), ( SELECT ID_cliente FROM cliente WHERE CODIGO = 'Eterion' ), 'BASE_MIDIA_PATH', '/home/pazin/teste/', 'Pasta em disco onde serão armazenadas as músicas EX: C:\Temp\Musicas', null );

insert into  PARAMETRO ( id_parametro, id_cliente, codigo, valor, descricao, type )
values ( nextval('parametro_id_parametro_seq'), ( SELECT ID_cliente FROM cliente WHERE CODIGO = 'Eterion' ), 'SERVER_REQUEST_PATH', '', 'A definir', null );



insert into funcionalidade ( ordem, nome, url, icone ) values ( 1,  'Gêneros' ,       				'/ambientes/%d/generos/view' ,             			'fa-music'); 
insert into funcionalidade ( ordem, nome, url, icone ) values ( 2,  'Vinhetas' ,      				'/ambientes/%d/view-upload-midia/vinheta/' ,     	'fa-file-audio-o'); 
insert into funcionalidade ( ordem, nome, url, icone ) values ( 3,  'Institucionais',     			'/ambientes/%d/view-upload-midia/inst/' ,      		'fa-headphones'); 
insert into funcionalidade ( ordem, nome, url, icone ) values ( 4,  'Comerciais' ,      			'/ambientes/%d/view-upload-midia/comercial/' ,   	'fa-film'); 
insert into funcionalidade ( ordem, nome, url, icone ) values ( 5,  'Programetes' ,     			'/ambientes/%d/view-upload-midia/programete/' ,  	'fa-bullhorn'); 
insert into funcionalidade ( ordem, nome, url, icone ) values ( 6,  'Chamadas<br/>Instantâneas' , 	'/ambientes/%d/view-upload-midia/chamada_inst/' ,  	'fa-bolt'); 
insert into funcionalidade ( ordem, nome, url, icone ) values ( 7,  'Chamadas<br/>Funcionários' ,   '/ambientes/%d/view-chamada-funcionarios',  		'fa-users'); 
insert into funcionalidade ( ordem, nome, url, icone ) values ( 8,  'Blocos' ,        				'/ambientes/%d/blocos/view' ,          		'fa-th-large'); 
insert into funcionalidade ( ordem, nome, url, icone ) values ( 9,  'Expediente' ,      			'/ambientes/%d/expedientes/view' ,        	'fa-clock-o'); 
insert into funcionalidade ( ordem, nome, url, icone ) values ( 10, 'Eventos' ,       				'/ambientes/%d/eventos/view' ,     	    	'fa-newspaper-o'); 
insert into funcionalidade ( ordem, nome, url, icone ) values ( 11, 'Programação<br/>Musical' ,  	'/ambientes/%d/programacoes/view' ,     	'fa-list-ol'); 
insert into funcionalidade ( ordem, nome, url, icone ) values ( 12, 'Configurações' ,     			'/ambientes/%d/configuracoes/view' ,       	'fa-briefcase'); 
insert into funcionalidade ( ordem, nome, url, icone ) values ( 13, 'Rodoviária' ,      			'/ambientes/%d/rodoviaria/view' ,        	'fa-bus'); 
insert into funcionalidade ( ordem, nome, url, icone ) values ( 14, 'Relatórios' ,      			'/ambientes/%d/relatorios/view' ,        	'fa-files-o'); 
insert into funcionalidade ( ordem, nome, url, icone ) values ( 15, 'Downloads' ,       			'/ambientes/%d/downloads/view' ,         	'fa-floppy-o'); 
insert into funcionalidade ( ordem, nome, url, icone ) values ( 16, 'Restrições' ,      			'/ambientes/%d/restricoes/view' ,        	'fa-unlock-alt'); 
insert into funcionalidade ( ordem, nome, url, icone ) values ( 17, 'Logomarca' ,       			'/ambientes/%d/logomarcas/view' ,         	'fa-trademark'); 
insert into funcionalidade ( ordem, nome, url, icone, extrahtml ) values ( 18, 'Simular' ,       				'/player/ambientes/%d/simulacoes/view' ,         	'fa-play' , ' target="_blank" '); 
insert into funcionalidade ( ordem, nome, url, icone ) values ( 19, 'SendVoice' ,       			'/ambientes/%d/sendvoices/view' ,         	'fa-microphone'); 


