

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




create sequence ambiente_genero_id_ambgen_seq;

CREATE TABLE AMBIENTE_GENERO
(
	id_ambgen bigint default nextval('ambiente_genero_id_ambgen_seq'),
	id_ambiente bigint not null,
	id_genero bigint not null,
	constraint ambiente_genero_pkey primary key (id_ambgen),
	constraint ambiente_genero_genero_fk foreign key (id_genero) references genero (id_genero),
	constraint ambiente_genero_ambiente_fk foreign key (id_ambiente) references ambiente (id_ambiente)
);





ALTER TABLE AMBIENTE ADD urlambiente text;


create sequence funcionalidade_id_func_seq;

CREATE TABLE FUNCIONALIDADE
(
	id_funcionalidade integer default nextval('funcionalidade_id_func_seq'),
	nome text,
	ordem integer,
	url text,
	icone text,
	constraint funcionalidade_pkey primary key (id_funcionalidade)
);


insert into funcionalidade ( id_funcionalidade, ordem, nome, url, icone ) values ( nextval('funcionalidade_id_func_seq'), 1,  'Gêneros' , 				'/ambientes/%d/view-generos/' , 						'fa-music'); 
insert into funcionalidade ( id_funcionalidade, ordem, nome, url, icone ) values ( nextval('funcionalidade_id_func_seq'), 2,  'Vinhetas' , 				'/ambientes/%d/view-list-upload-midia/vinheta/' , 		'fa-sound-o'); 
insert into funcionalidade ( id_funcionalidade, ordem, nome, url, icone ) values ( nextval('funcionalidade_id_func_seq'), 3,  'Institucionais', 		'/ambientes/%d/view-list-upload-midia/inst/' , 			'fa-headphones'); 
insert into funcionalidade ( id_funcionalidade, ordem, nome, url, icone ) values ( nextval('funcionalidade_id_func_seq'), 4,  'Comerciais' , 			'/ambientes/%d/view-list-upload-midia/comercial/' , 	'fa-film'); 
insert into funcionalidade ( id_funcionalidade, ordem, nome, url, icone ) values ( nextval('funcionalidade_id_func_seq'), 5,  'Programetes' , 			'/ambientes/%d/view-list-upload-midia/programete/' , 	'fa-bullhorn'); 
insert into funcionalidade ( id_funcionalidade, ordem, nome, url, icone ) values ( nextval('funcionalidade_id_func_seq'), 6,  'Chamadas Instantâneas' , '/ambientes/%d/view-list-upload-midia/chamada-inst/' , 	'fa-bolt'); 
insert into funcionalidade ( id_funcionalidade, ordem, nome, url, icone ) values ( nextval('funcionalidade_id_func_seq'), 7,  'Configurações' , 		'/ambientes/%d/view-configuracoes/' , 					'fa-briefcase'); 
insert into funcionalidade ( id_funcionalidade, ordem, nome, url, icone ) values ( nextval('funcionalidade_id_func_seq'), 8,  'Blocos' , 				'/ambientes/%d/view-blocos/' , 					'fa-th-large'); 
insert into funcionalidade ( id_funcionalidade, ordem, nome, url, icone ) values ( nextval('funcionalidade_id_func_seq'), 9,  'Expediente' ,			'/ambientes/%d/view-expediente/' , 				'fa-clock-o'); 
insert into funcionalidade ( id_funcionalidade, ordem, nome, url, icone ) values ( nextval('funcionalidade_id_func_seq'), 10, 'Eventos' , 				'/ambientes/%d/view-eventos/' , 				'fa-newspaper-o'); 
insert into funcionalidade ( id_funcionalidade, ordem, nome, url, icone ) values ( nextval('funcionalidade_id_func_seq'), 11, 'Funcionários' , 			'/ambientes/%d/view-funcionarios/' , 			'fa-users'); 
insert into funcionalidade ( id_funcionalidade, ordem, nome, url, icone ) values ( nextval('funcionalidade_id_func_seq'), 12, 'Rodoviária' , 			'/ambientes/%d/view-rodoviaria/' , 				'fa-bus'); 
insert into funcionalidade ( id_funcionalidade, ordem, nome, url, icone ) values ( nextval('funcionalidade_id_func_seq'), 13, 'Relatórios' , 			'/ambientes/%d/view-relatórios/' , 				'fa-files-o'); 
insert into funcionalidade ( id_funcionalidade, ordem, nome, url, icone ) values ( nextval('funcionalidade_id_func_seq'), 14, 'Downloads' , 			'/ambientes/%d/view-downloads/' , 				'fa-floppy-o'); 
insert into funcionalidade ( id_funcionalidade, ordem, nome, url, icone ) values ( nextval('funcionalidade_id_func_seq'), 15, 'Restrições' , 			'/ambientes/%d/view-restricoes/' , 				'fa-unlock-alt'); 
insert into funcionalidade ( id_funcionalidade, ordem, nome, url, icone ) values ( nextval('funcionalidade_id_func_seq'), 16, 'Logomarca' , 			'/ambientes/%d/view-logomarca/' , 				'fa-trademark'); 
insert into funcionalidade ( id_funcionalidade, ordem, nome, url, icone ) values ( nextval('funcionalidade_id_func_seq'), 17, 'Simular' , 				'/ambientes/%d/view-simular/' , 				'fa-play'); 
insert into funcionalidade ( id_funcionalidade, ordem, nome, url, icone ) values ( nextval('funcionalidade_id_func_seq'), 18, 'SendVoice' , 			'/ambientes/%d/view-sendvoice/' , 				'fa-microphone'); 
