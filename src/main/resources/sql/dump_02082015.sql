--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: acesso_usuario; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE acesso_usuario (
    id_acesso_acu bigint NOT NULL,
    ds_dados_acu text,
    ds_enderecoip_acu character varying(50) NOT NULL,
    dt_criacao_acu timestamp without time zone NOT NULL,
    id_usuario_usu bigint
);


ALTER TABLE acesso_usuario OWNER TO "radio-user";

--
-- Name: acesso_usuario_id_acesso_acu_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE acesso_usuario_id_acesso_acu_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE acesso_usuario_id_acesso_acu_seq OWNER TO "radio-user";

--
-- Name: acesso_usuario_id_acesso_acu_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE acesso_usuario_id_acesso_acu_seq OWNED BY acesso_usuario.id_acesso_acu;


--
-- Name: ambiente; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE ambiente (
    id_ambiente_amb bigint NOT NULL,
    cd_email1_amb character varying(255),
    cd_email2_amb character varying(255),
    cd_login_amb character varying(40) NOT NULL,
    cd_numero_amb character varying(20),
    cd_password_amb character varying(200) NOT NULL,
    cd_telefone1_amb character varying(255),
    cd_telefone2_amb character varying(255),
    ds_anotacoes_amb text,
    dt_alteracao_amb timestamp without time zone,
    dt_criacao_amb timestamp without time zone NOT NULL,
    fl_download_amb boolean,
    fl_opcionais_amb boolean,
    fl_sincronizar_amb boolean,
    nm_ambiente_amb character varying(200) NOT NULL,
    nm_bairro_amb character varying(100),
    nm_cidade_amb character varying(100),
    nm_estado_amb character varying(200),
    nm_logradouro_amb character varying(200),
    id_fusohorario_fuh bigint,
    id_usuario_usu bigint
);


ALTER TABLE ambiente OWNER TO "radio-user";

--
-- Name: ambiente_id_ambiente_amb_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE ambiente_id_ambiente_amb_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ambiente_id_ambiente_amb_seq OWNER TO "radio-user";

--
-- Name: ambiente_id_ambiente_amb_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE ambiente_id_ambiente_amb_seq OWNED BY ambiente.id_ambiente_amb;


--
-- Name: categoria; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE categoria (
    id_categoria_cat bigint NOT NULL,
    ds_descricao_cat text,
    nm_categoria_cat character varying(100) NOT NULL
);


ALTER TABLE categoria OWNER TO "radio-user";

--
-- Name: categoria_id_categoria_cat_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE categoria_id_categoria_cat_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE categoria_id_categoria_cat_seq OWNER TO "radio-user";

--
-- Name: categoria_id_categoria_cat_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE categoria_id_categoria_cat_seq OWNED BY categoria.id_categoria_cat;


--
-- Name: conexao; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE conexao (
    id_conexao_cnx bigint NOT NULL,
    ds_dados_acu text,
    ds_enderecoip_acu character varying(50) NOT NULL,
    dt_conexao_cnx timestamp without time zone NOT NULL,
    dt_desconexao_cnx timestamp without time zone,
    id_ambiente_amb bigint
);


ALTER TABLE conexao OWNER TO "radio-user";

--
-- Name: conexao_id_conexao_cnx_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE conexao_id_conexao_cnx_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE conexao_id_conexao_cnx_seq OWNER TO "radio-user";

--
-- Name: conexao_id_conexao_cnx_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE conexao_id_conexao_cnx_seq OWNED BY conexao.id_conexao_cnx;


--
-- Name: fuso_horario; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE fuso_horario (
    id_fusohorario_fuh bigint NOT NULL,
    ds_canonid_fuh character varying(100) NOT NULL,
    ds_offset_fuh character varying(20) NOT NULL,
    id_ordercomum_fuh integer,
    nm_alias_fuh character varying(300) NOT NULL
);


ALTER TABLE fuso_horario OWNER TO "radio-user";

--
-- Name: fuso_horario_id_fusohorario_fuh_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE fuso_horario_id_fusohorario_fuh_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fuso_horario_id_fusohorario_fuh_seq OWNER TO "radio-user";

--
-- Name: fuso_horario_id_fusohorario_fuh_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE fuso_horario_id_fusohorario_fuh_seq OWNED BY fuso_horario.id_fusohorario_fuh;


--
-- Name: genero; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE genero (
    id_genero_gen bigint NOT NULL,
    ds_descricao_gen text,
    dt_criacao_gen timestamp without time zone NOT NULL,
    nm_genero_gen character varying(100) NOT NULL
);


ALTER TABLE genero OWNER TO "radio-user";

--
-- Name: genero_id_genero_gen_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE genero_id_genero_gen_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE genero_id_genero_gen_seq OWNER TO "radio-user";

--
-- Name: genero_id_genero_gen_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE genero_id_genero_gen_seq OWNED BY genero.id_genero_gen;


--
-- Name: mensagem; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE mensagem (
    id_mensagem_msg bigint NOT NULL,
    ds_assunto_msg character varying(200) NOT NULL,
    ds_conteudo_msg text NOT NULL,
    ds_emailcopia_msg character varying(200),
    dt_criacao_msg timestamp without time zone NOT NULL,
    id_ambiente_amb bigint,
    id_usuario_usu bigint
);


ALTER TABLE mensagem OWNER TO "radio-user";

--
-- Name: mensagem_id_mensagem_msg_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE mensagem_id_mensagem_msg_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mensagem_id_mensagem_msg_seq OWNER TO "radio-user";

--
-- Name: mensagem_id_mensagem_msg_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE mensagem_id_mensagem_msg_seq OWNED BY mensagem.id_mensagem_msg;


--
-- Name: midia; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE midia (
    id_midia_mid bigint NOT NULL,
    ds_album_mid character varying(255),
    ds_artist_mid character varying(255),
    ds_comment_mid character varying(255),
    ds_date_mid character varying(255),
    ds_descricao_mid text,
    ds_filehash_mid character varying(200) NOT NULL,
    ds_filepath_mid character varying(200) NOT NULL,
    ds_genre_mid character varying(255),
    ds_mimetype_mid character varying(200) NOT NULL,
    ds_title_mid character varying(255),
    dt_criacao_amb timestamp without time zone,
    dt_upload_amb timestamp without time zone NOT NULL,
    fl_cache_mid boolean,
    fl_valido_mid boolean,
    nm_arquivo_mid character varying(200) NOT NULL,
    nm_extensao_mid character varying(10),
    id_categoria_cat bigint
);


ALTER TABLE midia OWNER TO "radio-user";

--
-- Name: midia_ambiente; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE midia_ambiente (
    id_midiaamb_mia bigint NOT NULL,
    dt_associacao_mia timestamp without time zone,
    id_ambiente_amb bigint,
    id_midia_mid bigint
);


ALTER TABLE midia_ambiente OWNER TO "radio-user";

--
-- Name: midia_ambiente_id_midiaamb_mia_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE midia_ambiente_id_midiaamb_mia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE midia_ambiente_id_midiaamb_mia_seq OWNER TO "radio-user";

--
-- Name: midia_ambiente_id_midiaamb_mia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE midia_ambiente_id_midiaamb_mia_seq OWNED BY midia_ambiente.id_midiaamb_mia;


--
-- Name: midia_genero; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE midia_genero (
    id_mediagen_mgn bigint NOT NULL,
    id_genero_gen bigint,
    id_midia_mid bigint
);


ALTER TABLE midia_genero OWNER TO "radio-user";

--
-- Name: midia_genero_id_mediagen_mgn_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE midia_genero_id_mediagen_mgn_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE midia_genero_id_mediagen_mgn_seq OWNER TO "radio-user";

--
-- Name: midia_genero_id_mediagen_mgn_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE midia_genero_id_mediagen_mgn_seq OWNED BY midia_genero.id_mediagen_mgn;


--
-- Name: midia_id_midia_mid_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE midia_id_midia_mid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE midia_id_midia_mid_seq OWNER TO "radio-user";

--
-- Name: midia_id_midia_mid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE midia_id_midia_mid_seq OWNED BY midia.id_midia_mid;


--
-- Name: perfil; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE perfil (
    id_perfil_per bigint NOT NULL,
    nm_perfil_per character varying(100) NOT NULL
);


ALTER TABLE perfil OWNER TO "radio-user";

--
-- Name: perfil_id_perfil_per_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE perfil_id_perfil_per_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE perfil_id_perfil_per_seq OWNER TO "radio-user";

--
-- Name: perfil_id_perfil_per_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE perfil_id_perfil_per_seq OWNED BY perfil.id_perfil_per;


--
-- Name: perfil_permissao; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE perfil_permissao (
    id_perfperm_prp bigint NOT NULL,
    dt_criacao_prp timestamp without time zone NOT NULL,
    id_perfil_per bigint,
    id_permissao_prm bigint
);


ALTER TABLE perfil_permissao OWNER TO "radio-user";

--
-- Name: perfil_permissao_id_perfperm_prp_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE perfil_permissao_id_perfperm_prp_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE perfil_permissao_id_perfperm_prp_seq OWNER TO "radio-user";

--
-- Name: perfil_permissao_id_perfperm_prp_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE perfil_permissao_id_perfperm_prp_seq OWNED BY perfil_permissao.id_perfperm_prp;


--
-- Name: permissao; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE permissao (
    id_permissao_prm bigint NOT NULL,
    cd_permiss_per character varying(100) NOT NULL,
    ds_permiss_per character varying(400) NOT NULL,
    id_permissaopai_prm bigint
);


ALTER TABLE permissao OWNER TO "radio-user";

--
-- Name: permissao_id_permissao_prm_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE permissao_id_permissao_prm_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE permissao_id_permissao_prm_seq OWNER TO "radio-user";

--
-- Name: permissao_id_permissao_prm_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE permissao_id_permissao_prm_seq OWNED BY permissao.id_permissao_prm;


--
-- Name: usuario; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE usuario (
    id_usuario_usu bigint NOT NULL,
    cd_login_usu character varying(40) NOT NULL,
    cd_password_usu character varying(200) NOT NULL,
    dt_alteracao_usu timestamp without time zone,
    dt_criacao_usu timestamp without time zone NOT NULL,
    fl_ativo_usu boolean NOT NULL,
    nm_usuario_usu character varying(200) NOT NULL
);


ALTER TABLE usuario OWNER TO "radio-user";

--
-- Name: usuario_id_usuario_usu_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE usuario_id_usuario_usu_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE usuario_id_usuario_usu_seq OWNER TO "radio-user";

--
-- Name: usuario_id_usuario_usu_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE usuario_id_usuario_usu_seq OWNED BY usuario.id_usuario_usu;


--
-- Name: usuario_perfil; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE usuario_perfil (
    id_usuperf_upf bigint NOT NULL,
    id_perfil_per bigint,
    id_usuario_usu bigint
);


ALTER TABLE usuario_perfil OWNER TO "radio-user";

--
-- Name: usuario_perfil_id_usuperf_upf_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE usuario_perfil_id_usuperf_upf_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE usuario_perfil_id_usuperf_upf_seq OWNER TO "radio-user";

--
-- Name: usuario_perfil_id_usuperf_upf_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE usuario_perfil_id_usuperf_upf_seq OWNED BY usuario_perfil.id_usuperf_upf;


--
-- Name: usuario_permissao; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE usuario_permissao (
    id_usuperm_upp bigint NOT NULL,
    dt_criacao_upp timestamp without time zone NOT NULL,
    id_permissao_prm bigint,
    id_usuario_usu bigint
);


ALTER TABLE usuario_permissao OWNER TO "radio-user";

--
-- Name: usuario_permissao_id_usuperm_upp_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE usuario_permissao_id_usuperm_upp_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE usuario_permissao_id_usuperm_upp_seq OWNER TO "radio-user";

--
-- Name: usuario_permissao_id_usuperm_upp_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE usuario_permissao_id_usuperm_upp_seq OWNED BY usuario_permissao.id_usuperm_upp;


--
-- Name: id_acesso_acu; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY acesso_usuario ALTER COLUMN id_acesso_acu SET DEFAULT nextval('acesso_usuario_id_acesso_acu_seq'::regclass);


--
-- Name: id_ambiente_amb; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente ALTER COLUMN id_ambiente_amb SET DEFAULT nextval('ambiente_id_ambiente_amb_seq'::regclass);


--
-- Name: id_categoria_cat; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY categoria ALTER COLUMN id_categoria_cat SET DEFAULT nextval('categoria_id_categoria_cat_seq'::regclass);


--
-- Name: id_conexao_cnx; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY conexao ALTER COLUMN id_conexao_cnx SET DEFAULT nextval('conexao_id_conexao_cnx_seq'::regclass);


--
-- Name: id_fusohorario_fuh; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY fuso_horario ALTER COLUMN id_fusohorario_fuh SET DEFAULT nextval('fuso_horario_id_fusohorario_fuh_seq'::regclass);


--
-- Name: id_genero_gen; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY genero ALTER COLUMN id_genero_gen SET DEFAULT nextval('genero_id_genero_gen_seq'::regclass);


--
-- Name: id_mensagem_msg; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY mensagem ALTER COLUMN id_mensagem_msg SET DEFAULT nextval('mensagem_id_mensagem_msg_seq'::regclass);


--
-- Name: id_midia_mid; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia ALTER COLUMN id_midia_mid SET DEFAULT nextval('midia_id_midia_mid_seq'::regclass);


--
-- Name: id_midiaamb_mia; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_ambiente ALTER COLUMN id_midiaamb_mia SET DEFAULT nextval('midia_ambiente_id_midiaamb_mia_seq'::regclass);


--
-- Name: id_mediagen_mgn; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_genero ALTER COLUMN id_mediagen_mgn SET DEFAULT nextval('midia_genero_id_mediagen_mgn_seq'::regclass);


--
-- Name: id_perfil_per; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY perfil ALTER COLUMN id_perfil_per SET DEFAULT nextval('perfil_id_perfil_per_seq'::regclass);


--
-- Name: id_perfperm_prp; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY perfil_permissao ALTER COLUMN id_perfperm_prp SET DEFAULT nextval('perfil_permissao_id_perfperm_prp_seq'::regclass);


--
-- Name: id_permissao_prm; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY permissao ALTER COLUMN id_permissao_prm SET DEFAULT nextval('permissao_id_permissao_prm_seq'::regclass);


--
-- Name: id_usuario_usu; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario ALTER COLUMN id_usuario_usu SET DEFAULT nextval('usuario_id_usuario_usu_seq'::regclass);


--
-- Name: id_usuperf_upf; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_perfil ALTER COLUMN id_usuperf_upf SET DEFAULT nextval('usuario_perfil_id_usuperf_upf_seq'::regclass);


--
-- Name: id_usuperm_upp; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_permissao ALTER COLUMN id_usuperm_upp SET DEFAULT nextval('usuario_permissao_id_usuperm_upp_seq'::regclass);


--
-- Data for Name: acesso_usuario; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY acesso_usuario (id_acesso_acu, ds_dados_acu, ds_enderecoip_acu, dt_criacao_acu, id_usuario_usu) FROM stdin;
\.


--
-- Name: acesso_usuario_id_acesso_acu_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('acesso_usuario_id_acesso_acu_seq', 1, false);


--
-- Data for Name: ambiente; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY ambiente (id_ambiente_amb, cd_email1_amb, cd_email2_amb, cd_login_amb, cd_numero_amb, cd_password_amb, cd_telefone1_amb, cd_telefone2_amb, ds_anotacoes_amb, dt_alteracao_amb, dt_criacao_amb, fl_download_amb, fl_opcionais_amb, fl_sincronizar_amb, nm_ambiente_amb, nm_bairro_amb, nm_cidade_amb, nm_estado_amb, nm_logradouro_amb, id_fusohorario_fuh, id_usuario_usu) FROM stdin;
\.


--
-- Name: ambiente_id_ambiente_amb_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('ambiente_id_ambiente_amb_seq', 1, false);


--
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY categoria (id_categoria_cat, ds_descricao_cat, nm_categoria_cat) FROM stdin;
1	Música	Música
2	Vinheta	Vinheta
3	Institucional	Institucional
4	Comercial	Comercial
5	Programete	Programete
6	Chamada Funcionário	Chamada Funcionário
7	Chamada Instantânea	Chamada Instantânea
\.


--
-- Name: categoria_id_categoria_cat_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('categoria_id_categoria_cat_seq', 7, true);


--
-- Data for Name: conexao; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY conexao (id_conexao_cnx, ds_dados_acu, ds_enderecoip_acu, dt_conexao_cnx, dt_desconexao_cnx, id_ambiente_amb) FROM stdin;
\.


--
-- Name: conexao_id_conexao_cnx_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('conexao_id_conexao_cnx_seq', 1, false);


--
-- Data for Name: fuso_horario; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY fuso_horario (id_fusohorario_fuh, ds_canonid_fuh, ds_offset_fuh, id_ordercomum_fuh, nm_alias_fuh) FROM stdin;
1	America/Lima	-05:00	2	Acre
2	America/Manaus	-04:00	1	Manaus
3	America/Sao_Paulo	-03:00	0	Brasília
4	America/Noronha	-02:00	4	Fernando Noronha
\.


--
-- Name: fuso_horario_id_fusohorario_fuh_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('fuso_horario_id_fusohorario_fuh_seq', 4, true);


--
-- Data for Name: genero; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY genero (id_genero_gen, ds_descricao_gen, dt_criacao_gen, nm_genero_gen) FROM stdin;
\.


--
-- Name: genero_id_genero_gen_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('genero_id_genero_gen_seq', 1, false);


--
-- Data for Name: mensagem; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY mensagem (id_mensagem_msg, ds_assunto_msg, ds_conteudo_msg, ds_emailcopia_msg, dt_criacao_msg, id_ambiente_amb, id_usuario_usu) FROM stdin;
\.


--
-- Name: mensagem_id_mensagem_msg_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('mensagem_id_mensagem_msg_seq', 1, false);


--
-- Data for Name: midia; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY midia (id_midia_mid, ds_album_mid, ds_artist_mid, ds_comment_mid, ds_date_mid, ds_descricao_mid, ds_filehash_mid, ds_filepath_mid, ds_genre_mid, ds_mimetype_mid, ds_title_mid, dt_criacao_amb, dt_upload_amb, fl_cache_mid, fl_valido_mid, nm_arquivo_mid, nm_extensao_mid, id_categoria_cat) FROM stdin;
\.


--
-- Data for Name: midia_ambiente; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY midia_ambiente (id_midiaamb_mia, dt_associacao_mia, id_ambiente_amb, id_midia_mid) FROM stdin;
\.


--
-- Name: midia_ambiente_id_midiaamb_mia_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('midia_ambiente_id_midiaamb_mia_seq', 1, false);


--
-- Data for Name: midia_genero; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY midia_genero (id_mediagen_mgn, id_genero_gen, id_midia_mid) FROM stdin;
\.


--
-- Name: midia_genero_id_mediagen_mgn_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('midia_genero_id_mediagen_mgn_seq', 1, false);


--
-- Name: midia_id_midia_mid_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('midia_id_midia_mid_seq', 1, false);


--
-- Data for Name: perfil; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY perfil (id_perfil_per, nm_perfil_per) FROM stdin;
1	DESENVOLVEDOR
2	ADMINISTRADOR
3	GERENTE
\.


--
-- Name: perfil_id_perfil_per_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('perfil_id_perfil_per_seq', 3, true);


--
-- Data for Name: perfil_permissao; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY perfil_permissao (id_perfperm_prp, dt_criacao_prp, id_perfil_per, id_permissao_prm) FROM stdin;
2	2015-08-02 23:42:15.191246	1	1
3	2015-08-02 23:42:15.191246	1	2
4	2015-08-02 23:42:15.191246	1	3
5	2015-08-02 23:42:15.191246	1	4
6	2015-08-02 23:42:15.191246	1	5
7	2015-08-02 23:42:15.191246	1	6
8	2015-08-02 23:42:15.191246	1	7
9	2015-08-02 23:42:15.191246	1	8
10	2015-08-02 23:42:15.191246	1	9
11	2015-08-02 23:42:15.191246	1	10
12	2015-08-02 23:42:15.191246	1	11
\.


--
-- Name: perfil_permissao_id_perfperm_prp_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('perfil_permissao_id_perfperm_prp_seq', 12, true);


--
-- Data for Name: permissao; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY permissao (id_permissao_prm, cd_permiss_per, ds_permiss_per, id_permissaopai_prm) FROM stdin;
1	INCLUIR_AMB		\N
2	ADMINISTRAR_AMB		\N
3	ALTERAR_SENHA		\N
4	MENSAGENS		\N
5	MONITORAMENTO		\N
6	CHAMADA_FUNCIO		\N
7	UPLOAD_AMBIENTE		\N
8	FERRAMENTAS		\N
9	SOFTWARES		\N
10	ADMINISTRADORES		\N
11	MOBILE		\N
\.


--
-- Name: permissao_id_permissao_prm_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('permissao_id_permissao_prm_seq', 11, true);


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY usuario (id_usuario_usu, cd_login_usu, cd_password_usu, dt_alteracao_usu, dt_criacao_usu, fl_ativo_usu, nm_usuario_usu) FROM stdin;
1	fpazin	12456	\N	2015-08-02 23:35:11.263716	t	Fernando Pazin
2	gaugusto	12456	\N	2015-08-02 23:35:11.263716	t	George Augusto
\.


--
-- Name: usuario_id_usuario_usu_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('usuario_id_usuario_usu_seq', 2, true);


--
-- Data for Name: usuario_perfil; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY usuario_perfil (id_usuperf_upf, id_perfil_per, id_usuario_usu) FROM stdin;
1	1	1
2	1	2
\.


--
-- Name: usuario_perfil_id_usuperf_upf_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('usuario_perfil_id_usuperf_upf_seq', 2, true);


--
-- Data for Name: usuario_permissao; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY usuario_permissao (id_usuperm_upp, dt_criacao_upp, id_permissao_prm, id_usuario_usu) FROM stdin;
\.


--
-- Name: usuario_permissao_id_usuperm_upp_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('usuario_permissao_id_usuperm_upp_seq', 1, false);


--
-- Name: acesso_usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY acesso_usuario
    ADD CONSTRAINT acesso_usuario_pkey PRIMARY KEY (id_acesso_acu);


--
-- Name: ambiente_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY ambiente
    ADD CONSTRAINT ambiente_pkey PRIMARY KEY (id_ambiente_amb);


--
-- Name: categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id_categoria_cat);


--
-- Name: conexao_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY conexao
    ADD CONSTRAINT conexao_pkey PRIMARY KEY (id_conexao_cnx);


--
-- Name: fuso_horario_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY fuso_horario
    ADD CONSTRAINT fuso_horario_pkey PRIMARY KEY (id_fusohorario_fuh);


--
-- Name: genero_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY genero
    ADD CONSTRAINT genero_pkey PRIMARY KEY (id_genero_gen);


--
-- Name: mensagem_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY mensagem
    ADD CONSTRAINT mensagem_pkey PRIMARY KEY (id_mensagem_msg);


--
-- Name: midia_ambiente_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY midia_ambiente
    ADD CONSTRAINT midia_ambiente_pkey PRIMARY KEY (id_midiaamb_mia);


--
-- Name: midia_genero_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY midia_genero
    ADD CONSTRAINT midia_genero_pkey PRIMARY KEY (id_mediagen_mgn);


--
-- Name: midia_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY midia
    ADD CONSTRAINT midia_pkey PRIMARY KEY (id_midia_mid);


--
-- Name: perfil_permissao_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY perfil_permissao
    ADD CONSTRAINT perfil_permissao_pkey PRIMARY KEY (id_perfperm_prp);


--
-- Name: perfil_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY perfil
    ADD CONSTRAINT perfil_pkey PRIMARY KEY (id_perfil_per);


--
-- Name: permissao_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY permissao
    ADD CONSTRAINT permissao_pkey PRIMARY KEY (id_permissao_prm);


--
-- Name: usuario_perfil_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY usuario_perfil
    ADD CONSTRAINT usuario_perfil_pkey PRIMARY KEY (id_usuperf_upf);


--
-- Name: usuario_permissao_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY usuario_permissao
    ADD CONSTRAINT usuario_permissao_pkey PRIMARY KEY (id_usuperm_upp);


--
-- Name: usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario_usu);


--
-- Name: fk_1ch9f77yaf9006gpob4dg7dmx; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_perfil
    ADD CONSTRAINT fk_1ch9f77yaf9006gpob4dg7dmx FOREIGN KEY (id_usuario_usu) REFERENCES usuario(id_usuario_usu);


--
-- Name: fk_31iaaim29u91jqrq43ul7r29c; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente
    ADD CONSTRAINT fk_31iaaim29u91jqrq43ul7r29c FOREIGN KEY (id_usuario_usu) REFERENCES usuario(id_usuario_usu);


--
-- Name: fk_46s00k84qswsee334lk33ut7q; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY mensagem
    ADD CONSTRAINT fk_46s00k84qswsee334lk33ut7q FOREIGN KEY (id_usuario_usu) REFERENCES usuario(id_usuario_usu);


--
-- Name: fk_93vjp4hc7yf24b1br00uu16wv; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY mensagem
    ADD CONSTRAINT fk_93vjp4hc7yf24b1br00uu16wv FOREIGN KEY (id_ambiente_amb) REFERENCES ambiente(id_ambiente_amb);


--
-- Name: fk_99rrdt54uojf31hjfngxn2bq8; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_ambiente
    ADD CONSTRAINT fk_99rrdt54uojf31hjfngxn2bq8 FOREIGN KEY (id_midia_mid) REFERENCES midia(id_midia_mid);


--
-- Name: fk_9s7dwrpdnii5pyth7v7nfgcca; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY acesso_usuario
    ADD CONSTRAINT fk_9s7dwrpdnii5pyth7v7nfgcca FOREIGN KEY (id_usuario_usu) REFERENCES usuario(id_usuario_usu);


--
-- Name: fk_a7hisvu0n7e3wapb02j8y9e4c; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_genero
    ADD CONSTRAINT fk_a7hisvu0n7e3wapb02j8y9e4c FOREIGN KEY (id_midia_mid) REFERENCES midia(id_midia_mid);


--
-- Name: fk_db5vy0qxsk05qhrhanad3j8p8; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY permissao
    ADD CONSTRAINT fk_db5vy0qxsk05qhrhanad3j8p8 FOREIGN KEY (id_permissaopai_prm) REFERENCES permissao(id_permissao_prm);


--
-- Name: fk_dx7ytoqewj05oiocbkfq29i5t; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente
    ADD CONSTRAINT fk_dx7ytoqewj05oiocbkfq29i5t FOREIGN KEY (id_fusohorario_fuh) REFERENCES fuso_horario(id_fusohorario_fuh);


--
-- Name: fk_hrx9n4lne6b2atlbxrvv4mb0s; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_permissao
    ADD CONSTRAINT fk_hrx9n4lne6b2atlbxrvv4mb0s FOREIGN KEY (id_permissao_prm) REFERENCES permissao(id_permissao_prm);


--
-- Name: fk_icktrsf6s68dr2wng442e3tex; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_permissao
    ADD CONSTRAINT fk_icktrsf6s68dr2wng442e3tex FOREIGN KEY (id_usuario_usu) REFERENCES usuario(id_usuario_usu);


--
-- Name: fk_lfahbgjrpt72gbq5aqwx7lb4e; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY conexao
    ADD CONSTRAINT fk_lfahbgjrpt72gbq5aqwx7lb4e FOREIGN KEY (id_ambiente_amb) REFERENCES ambiente(id_ambiente_amb);


--
-- Name: fk_na2wettk9k07g745wvrir03l1; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia
    ADD CONSTRAINT fk_na2wettk9k07g745wvrir03l1 FOREIGN KEY (id_categoria_cat) REFERENCES categoria(id_categoria_cat);


--
-- Name: fk_oy8q1xp9db0tcgvf18ef0swxy; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY perfil_permissao
    ADD CONSTRAINT fk_oy8q1xp9db0tcgvf18ef0swxy FOREIGN KEY (id_permissao_prm) REFERENCES permissao(id_permissao_prm);


--
-- Name: fk_p53idx2muto8t4i7coy3vie00; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_ambiente
    ADD CONSTRAINT fk_p53idx2muto8t4i7coy3vie00 FOREIGN KEY (id_ambiente_amb) REFERENCES ambiente(id_ambiente_amb);


--
-- Name: fk_pshsw8ha6jm912civv91p5ojq; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_perfil
    ADD CONSTRAINT fk_pshsw8ha6jm912civv91p5ojq FOREIGN KEY (id_perfil_per) REFERENCES perfil(id_perfil_per);


--
-- Name: fk_q00eqa26d5sfmstn8reu7tp38; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY perfil_permissao
    ADD CONSTRAINT fk_q00eqa26d5sfmstn8reu7tp38 FOREIGN KEY (id_perfil_per) REFERENCES perfil(id_perfil_per);


--
-- Name: fk_qluil6jk3q01o5yuukmpu8lqm; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_genero
    ADD CONSTRAINT fk_qluil6jk3q01o5yuukmpu8lqm FOREIGN KEY (id_genero_gen) REFERENCES genero(id_genero_gen);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

