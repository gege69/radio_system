


alter table midia add filesize integer;


create index on midia (filehash);




create sequence empresa_id_empresa_seq;

CREATE TABLE IF NOT EXISTS EMPRESA
(
	id_empresa bigint default nextval('empresa_id_empresa_seq'),
	cnpj text not null,
	codigo text,
	razaosocial text not null,
	nomefantasia text,
	dominio text,
	datacriacao timestamp not null, 
	dataalteracao timestamp,
	ativo boolean default true,
	constraint empresa_pkey primary key (id_empresa)
);




INSERT INTO EMPRESA ( id_empresa ,cnpj ,codigo ,razaosocial ,nomefantasia ,dominio ,datacriacao ,dataalteracao ,ativo )
VALUES ( nextval('empresa_id_empresa_seq'), '28372714000140', 'Eterion', 'Eterion', 'Eterion', 'www.eterion.com.br', now(), null, true );



create sequence parametro_id_parametro_seq;

CREATE TABLE IF NOT EXISTS PARAMETRO
(
	id_parametro bigint default nextval('parametro_id_parametro_seq'),
	id_empresa bigint not null,
	param text,
	descricao text,
	type text,
	constraint parametro_pkey primary key (id_parametro),
	constraint parametro_empresa_fk foreign key (id_empresa) references empresa (id_empresa)
);



ALTER TABLE AMBIENTE ADD ID_EMPRESA BIGINT;

ALTER TABLE AMBIENTE ADD constraint AMBIENTE_empresa_fk foreign key (id_empresa) references empresa (id_empresa);

UPDATE AMBIENTE SET ID_EMPRESA = ( SELECT ID_EMPRESA FROM EMPRESA WHERE CODIGO = 'Eterion' );

ALTER TABLE AMBIENTE ALTER COLUMN ID_EMPRESA SET NOT NULL;



ALTER TABLE USUARIO ADD ID_EMPRESA BIGINT;

ALTER TABLE USUARIO ADD constraint USUARIO_empresa_fk foreign key (id_empresa) references empresa (id_empresa);

UPDATE USUARIO SET ID_EMPRESA = ( SELECT ID_EMPRESA FROM EMPRESA WHERE CODIGO = 'Eterion' );

ALTER TABLE USUARIO ALTER COLUMN ID_EMPRESA SET NOT NULL;



