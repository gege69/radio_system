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
    id_acesso bigint NOT NULL,
    dados text,
    datacriacao timestamp without time zone NOT NULL,
    enderecoip character varying(50) NOT NULL,
    id_usuario bigint
);


ALTER TABLE acesso_usuario OWNER TO "radio-user";

--
-- Name: acesso_usuario_id_acesso_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE acesso_usuario_id_acesso_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE acesso_usuario_id_acesso_seq OWNER TO "radio-user";

--
-- Name: acesso_usuario_id_acesso_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE acesso_usuario_id_acesso_seq OWNED BY acesso_usuario.id_acesso;


--
-- Name: ambiente; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE ambiente (
    id_ambiente bigint NOT NULL,
    anotacoes text,
    bairro text,
    cidade text,
    dataalteracao timestamp without time zone,
    datacriacao timestamp without time zone NOT NULL,
    download boolean,
    email1 character varying(255),
    email2 character varying(255),
    estado character varying(200),
    horafimexpediente integer,
    horainiexpediente integer,
    login character varying(40) NOT NULL,
    logradouro text,
    minutofimexpediente integer,
    minutoiniexpediente integer,
    nome character varying(200) NOT NULL,
    numero character varying(20),
    opcionais boolean,
    password character varying(200) NOT NULL,
    sincronizar boolean,
    telefone1 character varying(255),
    telefone2 character varying(255),
    urlambiente text,
    id_empresa bigint NOT NULL,
    id_fusohorario bigint,
    id_usuario bigint
);


ALTER TABLE ambiente OWNER TO "radio-user";

--
-- Name: ambiente_configuracao; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE ambiente_configuracao (
    id_ambconfig bigint NOT NULL,
    agendmidia boolean,
    atendimento boolean,
    autoplay boolean,
    avancarretornar boolean,
    chamfuncionarios boolean,
    chaminstantanea boolean,
    chamvariosfuncionarios boolean,
    chamveiculo boolean,
    controleblocos boolean,
    controlecomerciais boolean,
    controleinstitucionais boolean,
    controleprogrametes boolean,
    controlevolumeindividual boolean,
    dataalteracao timestamp without time zone,
    datacriacao timestamp without time zone NOT NULL,
    generosbycc boolean,
    horoscopo boolean,
    locutorvirtual boolean,
    menudownloads boolean,
    nobreak boolean,
    opcionais boolean,
    pedidomusical boolean,
    pedidomusicalvinheta boolean,
    relatoriosmidia boolean,
    rodoviarias boolean,
    selecaogenero boolean,
    volumechamadas integer,
    volumecomerciais integer,
    volumegeral integer,
    volumemusicas integer,
    vozlocucao character varying(255),
    id_ambiente bigint,
    id_usuarioalteracao bigint,
    id_usuario bigint
);


ALTER TABLE ambiente_configuracao OWNER TO "radio-user";

--
-- Name: ambiente_configuracao_id_ambconfig_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE ambiente_configuracao_id_ambconfig_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ambiente_configuracao_id_ambconfig_seq OWNER TO "radio-user";

--
-- Name: ambiente_configuracao_id_ambconfig_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE ambiente_configuracao_id_ambconfig_seq OWNED BY ambiente_configuracao.id_ambconfig;


--
-- Name: ambiente_genero; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE ambiente_genero (
    id_ambgen bigint NOT NULL,
    id_ambiente bigint,
    id_genero bigint
);


ALTER TABLE ambiente_genero OWNER TO "radio-user";

--
-- Name: ambiente_genero_id_ambgen_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE ambiente_genero_id_ambgen_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ambiente_genero_id_ambgen_seq OWNER TO "radio-user";

--
-- Name: ambiente_genero_id_ambgen_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE ambiente_genero_id_ambgen_seq OWNED BY ambiente_genero.id_ambgen;


--
-- Name: ambiente_id_ambiente_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE ambiente_id_ambiente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ambiente_id_ambiente_seq OWNER TO "radio-user";

--
-- Name: ambiente_id_ambiente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE ambiente_id_ambiente_seq OWNED BY ambiente.id_ambiente;


--
-- Name: bloco; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE bloco (
    id_bloco bigint NOT NULL,
    indexhoracerta integer,
    indexinstitucionais integer,
    indexprogrametes integer,
    posicaovinheta integer,
    qtdcomerciais integer,
    qtdmusicas integer,
    id_ambiente bigint
);


ALTER TABLE bloco OWNER TO "radio-user";

--
-- Name: bloco_id_bloco_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE bloco_id_bloco_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bloco_id_bloco_seq OWNER TO "radio-user";

--
-- Name: bloco_id_bloco_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE bloco_id_bloco_seq OWNED BY bloco.id_bloco;


--
-- Name: categoria; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE categoria (
    id_categoria bigint NOT NULL,
    codigo text,
    descricao text,
    nome character varying(100) NOT NULL
);


ALTER TABLE categoria OWNER TO "radio-user";

--
-- Name: categoria_id_categoria_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE categoria_id_categoria_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE categoria_id_categoria_seq OWNER TO "radio-user";

--
-- Name: categoria_id_categoria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE categoria_id_categoria_seq OWNED BY categoria.id_categoria;


--
-- Name: conexao; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE conexao (
    id_conexao bigint NOT NULL,
    dados text,
    dataconexao timestamp without time zone NOT NULL,
    datadesconexao timestamp without time zone,
    enderecoip character varying(50) NOT NULL,
    id_ambiente bigint
);


ALTER TABLE conexao OWNER TO "radio-user";

--
-- Name: conexao_id_conexao_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE conexao_id_conexao_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE conexao_id_conexao_seq OWNER TO "radio-user";

--
-- Name: conexao_id_conexao_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE conexao_id_conexao_seq OWNED BY conexao.id_conexao;


--
-- Name: empresa; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE empresa (
    id_empresa bigint NOT NULL,
    ativo boolean NOT NULL,
    cnpj character varying(14) NOT NULL,
    codigo text,
    dataalteracao timestamp without time zone,
    datacriacao timestamp without time zone NOT NULL,
    dominio text,
    nomefantasia text,
    razaosocial text NOT NULL
);


ALTER TABLE empresa OWNER TO "radio-user";

--
-- Name: empresa_id_empresa_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE empresa_id_empresa_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa_id_empresa_seq OWNER TO "radio-user";

--
-- Name: empresa_id_empresa_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE empresa_id_empresa_seq OWNED BY empresa.id_empresa;


--
-- Name: funcionalidade; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE funcionalidade (
    id_funcionalidade bigint NOT NULL,
    icone text,
    nome text,
    ordem bigint,
    url text
);


ALTER TABLE funcionalidade OWNER TO "radio-user";

--
-- Name: funcionalidade_id_funcionalidade_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE funcionalidade_id_funcionalidade_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE funcionalidade_id_funcionalidade_seq OWNER TO "radio-user";

--
-- Name: funcionalidade_id_funcionalidade_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE funcionalidade_id_funcionalidade_seq OWNED BY funcionalidade.id_funcionalidade;


--
-- Name: fuso_horario; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE fuso_horario (
    id_fusohorario bigint NOT NULL,
    alias text NOT NULL,
    canonid text NOT NULL,
    offsetfuso character varying(20) NOT NULL,
    ordercomum integer
);


ALTER TABLE fuso_horario OWNER TO "radio-user";

--
-- Name: fuso_horario_id_fusohorario_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE fuso_horario_id_fusohorario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fuso_horario_id_fusohorario_seq OWNER TO "radio-user";

--
-- Name: fuso_horario_id_fusohorario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE fuso_horario_id_fusohorario_seq OWNED BY fuso_horario.id_fusohorario;


--
-- Name: genero; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE genero (
    id_genero bigint NOT NULL,
    datacriacao timestamp without time zone NOT NULL,
    descricao text,
    genero text NOT NULL
);


ALTER TABLE genero OWNER TO "radio-user";

--
-- Name: genero_id_genero_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE genero_id_genero_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE genero_id_genero_seq OWNER TO "radio-user";

--
-- Name: genero_id_genero_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE genero_id_genero_seq OWNED BY genero.id_genero;


--
-- Name: mensagem; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE mensagem (
    id_mensagem bigint NOT NULL,
    assunto text NOT NULL,
    conteudo text NOT NULL,
    datacriacao timestamp without time zone NOT NULL,
    emailcopia text,
    id_ambiente bigint,
    id_usuario bigint
);


ALTER TABLE mensagem OWNER TO "radio-user";

--
-- Name: mensagem_id_mensagem_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE mensagem_id_mensagem_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mensagem_id_mensagem_seq OWNER TO "radio-user";

--
-- Name: mensagem_id_mensagem_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE mensagem_id_mensagem_seq OWNED BY mensagem.id_mensagem;


--
-- Name: midia; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE midia (
    id_midia bigint NOT NULL,
    album text,
    artist text,
    cached boolean,
    comment text,
    datacriacao timestamp without time zone,
    dataupload timestamp without time zone NOT NULL,
    datetag text,
    descricao text,
    extensao character varying(10),
    filehash text NOT NULL,
    filepath text NOT NULL,
    filesize integer NOT NULL,
    genre text,
    mimetype character varying(200) NOT NULL,
    nome text NOT NULL,
    title text,
    valido boolean
);


ALTER TABLE midia OWNER TO "radio-user";

--
-- Name: midia_ambiente; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE midia_ambiente (
    id_midiaamb bigint NOT NULL,
    dataassociacao timestamp without time zone,
    id_ambiente bigint,
    id_midia bigint
);


ALTER TABLE midia_ambiente OWNER TO "radio-user";

--
-- Name: midia_ambiente_id_midiaamb_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE midia_ambiente_id_midiaamb_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE midia_ambiente_id_midiaamb_seq OWNER TO "radio-user";

--
-- Name: midia_ambiente_id_midiaamb_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE midia_ambiente_id_midiaamb_seq OWNED BY midia_ambiente.id_midiaamb;


--
-- Name: midia_categoria; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE midia_categoria (
    id_midia bigint NOT NULL,
    id_categoria bigint NOT NULL
);


ALTER TABLE midia_categoria OWNER TO "radio-user";

--
-- Name: midia_genero; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE midia_genero (
    id_mediagen bigint NOT NULL,
    id_genero bigint,
    id_midia bigint
);


ALTER TABLE midia_genero OWNER TO "radio-user";

--
-- Name: midia_genero_id_mediagen_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE midia_genero_id_mediagen_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE midia_genero_id_mediagen_seq OWNER TO "radio-user";

--
-- Name: midia_genero_id_mediagen_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE midia_genero_id_mediagen_seq OWNED BY midia_genero.id_mediagen;


--
-- Name: midia_id_midia_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE midia_id_midia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE midia_id_midia_seq OWNER TO "radio-user";

--
-- Name: midia_id_midia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE midia_id_midia_seq OWNED BY midia.id_midia;


--
-- Name: parametro; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE parametro (
    id_parametro bigint NOT NULL,
    codigo text NOT NULL,
    descricao text,
    type text,
    valor text NOT NULL,
    id_empresa bigint NOT NULL
);


ALTER TABLE parametro OWNER TO "radio-user";

--
-- Name: parametro_id_parametro_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE parametro_id_parametro_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE parametro_id_parametro_seq OWNER TO "radio-user";

--
-- Name: parametro_id_parametro_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE parametro_id_parametro_seq OWNED BY parametro.id_parametro;


--
-- Name: perfil; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE perfil (
    id_perfil bigint NOT NULL,
    nome text NOT NULL
);


ALTER TABLE perfil OWNER TO "radio-user";

--
-- Name: perfil_id_perfil_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE perfil_id_perfil_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE perfil_id_perfil_seq OWNER TO "radio-user";

--
-- Name: perfil_id_perfil_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE perfil_id_perfil_seq OWNED BY perfil.id_perfil;


--
-- Name: perfil_permissao; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE perfil_permissao (
    id_perfperm bigint NOT NULL,
    datacriacao timestamp without time zone NOT NULL,
    id_perfil bigint,
    id_permissao bigint
);


ALTER TABLE perfil_permissao OWNER TO "radio-user";

--
-- Name: perfil_permissao_id_perfperm_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE perfil_permissao_id_perfperm_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE perfil_permissao_id_perfperm_seq OWNER TO "radio-user";

--
-- Name: perfil_permissao_id_perfperm_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE perfil_permissao_id_perfperm_seq OWNED BY perfil_permissao.id_perfperm;


--
-- Name: permissao; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE permissao (
    id_permissao bigint NOT NULL,
    codigo text NOT NULL,
    descricao text NOT NULL,
    id_permissaopai bigint
);


ALTER TABLE permissao OWNER TO "radio-user";

--
-- Name: permissao_id_permissao_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE permissao_id_permissao_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE permissao_id_permissao_seq OWNER TO "radio-user";

--
-- Name: permissao_id_permissao_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE permissao_id_permissao_seq OWNED BY permissao.id_permissao;


--
-- Name: usuario; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE usuario (
    id_usuario bigint NOT NULL,
    ativo boolean NOT NULL,
    dataalteracao timestamp without time zone,
    datacriacao timestamp without time zone NOT NULL,
    email character varying(120) NOT NULL,
    login character varying(40) NOT NULL,
    nome text NOT NULL,
    password character varying(200) NOT NULL,
    id_empresa bigint NOT NULL
);


ALTER TABLE usuario OWNER TO "radio-user";

--
-- Name: usuario_id_usuario_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE usuario_id_usuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE usuario_id_usuario_seq OWNER TO "radio-user";

--
-- Name: usuario_id_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE usuario_id_usuario_seq OWNED BY usuario.id_usuario;


--
-- Name: usuario_perfil; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE usuario_perfil (
    id_usuperf bigint NOT NULL,
    id_perfil bigint,
    id_usuario bigint
);


ALTER TABLE usuario_perfil OWNER TO "radio-user";

--
-- Name: usuario_perfil_id_usuperf_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE usuario_perfil_id_usuperf_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE usuario_perfil_id_usuperf_seq OWNER TO "radio-user";

--
-- Name: usuario_perfil_id_usuperf_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE usuario_perfil_id_usuperf_seq OWNED BY usuario_perfil.id_usuperf;


--
-- Name: usuario_permissao; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE usuario_permissao (
    id_usuperm bigint NOT NULL,
    datacriacao timestamp without time zone NOT NULL,
    id_permissao bigint,
    id_usuario bigint
);


ALTER TABLE usuario_permissao OWNER TO "radio-user";

--
-- Name: usuario_permissao_id_usuperm_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE usuario_permissao_id_usuperm_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE usuario_permissao_id_usuperm_seq OWNER TO "radio-user";

--
-- Name: usuario_permissao_id_usuperm_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE usuario_permissao_id_usuperm_seq OWNED BY usuario_permissao.id_usuperm;


--
-- Name: id_acesso; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY acesso_usuario ALTER COLUMN id_acesso SET DEFAULT nextval('acesso_usuario_id_acesso_seq'::regclass);


--
-- Name: id_ambiente; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente ALTER COLUMN id_ambiente SET DEFAULT nextval('ambiente_id_ambiente_seq'::regclass);


--
-- Name: id_ambconfig; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente_configuracao ALTER COLUMN id_ambconfig SET DEFAULT nextval('ambiente_configuracao_id_ambconfig_seq'::regclass);


--
-- Name: id_ambgen; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente_genero ALTER COLUMN id_ambgen SET DEFAULT nextval('ambiente_genero_id_ambgen_seq'::regclass);


--
-- Name: id_bloco; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY bloco ALTER COLUMN id_bloco SET DEFAULT nextval('bloco_id_bloco_seq'::regclass);


--
-- Name: id_categoria; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY categoria ALTER COLUMN id_categoria SET DEFAULT nextval('categoria_id_categoria_seq'::regclass);


--
-- Name: id_conexao; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY conexao ALTER COLUMN id_conexao SET DEFAULT nextval('conexao_id_conexao_seq'::regclass);


--
-- Name: id_empresa; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY empresa ALTER COLUMN id_empresa SET DEFAULT nextval('empresa_id_empresa_seq'::regclass);


--
-- Name: id_funcionalidade; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY funcionalidade ALTER COLUMN id_funcionalidade SET DEFAULT nextval('funcionalidade_id_funcionalidade_seq'::regclass);


--
-- Name: id_fusohorario; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY fuso_horario ALTER COLUMN id_fusohorario SET DEFAULT nextval('fuso_horario_id_fusohorario_seq'::regclass);


--
-- Name: id_genero; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY genero ALTER COLUMN id_genero SET DEFAULT nextval('genero_id_genero_seq'::regclass);


--
-- Name: id_mensagem; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY mensagem ALTER COLUMN id_mensagem SET DEFAULT nextval('mensagem_id_mensagem_seq'::regclass);


--
-- Name: id_midia; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia ALTER COLUMN id_midia SET DEFAULT nextval('midia_id_midia_seq'::regclass);


--
-- Name: id_midiaamb; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_ambiente ALTER COLUMN id_midiaamb SET DEFAULT nextval('midia_ambiente_id_midiaamb_seq'::regclass);


--
-- Name: id_mediagen; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_genero ALTER COLUMN id_mediagen SET DEFAULT nextval('midia_genero_id_mediagen_seq'::regclass);


--
-- Name: id_parametro; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY parametro ALTER COLUMN id_parametro SET DEFAULT nextval('parametro_id_parametro_seq'::regclass);


--
-- Name: id_perfil; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY perfil ALTER COLUMN id_perfil SET DEFAULT nextval('perfil_id_perfil_seq'::regclass);


--
-- Name: id_perfperm; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY perfil_permissao ALTER COLUMN id_perfperm SET DEFAULT nextval('perfil_permissao_id_perfperm_seq'::regclass);


--
-- Name: id_permissao; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY permissao ALTER COLUMN id_permissao SET DEFAULT nextval('permissao_id_permissao_seq'::regclass);


--
-- Name: id_usuario; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario ALTER COLUMN id_usuario SET DEFAULT nextval('usuario_id_usuario_seq'::regclass);


--
-- Name: id_usuperf; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_perfil ALTER COLUMN id_usuperf SET DEFAULT nextval('usuario_perfil_id_usuperf_seq'::regclass);


--
-- Name: id_usuperm; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_permissao ALTER COLUMN id_usuperm SET DEFAULT nextval('usuario_permissao_id_usuperm_seq'::regclass);


--
-- Data for Name: acesso_usuario; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY acesso_usuario (id_acesso, dados, datacriacao, enderecoip, id_usuario) FROM stdin;
\.


--
-- Name: acesso_usuario_id_acesso_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('acesso_usuario_id_acesso_seq', 1, false);


--
-- Data for Name: ambiente; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY ambiente (id_ambiente, anotacoes, bairro, cidade, dataalteracao, datacriacao, download, email1, email2, estado, horafimexpediente, horainiexpediente, login, logradouro, minutofimexpediente, minutoiniexpediente, nome, numero, opcionais, password, sincronizar, telefone1, telefone2, urlambiente, id_empresa, id_fusohorario, id_usuario) FROM stdin;
\.


--
-- Data for Name: ambiente_configuracao; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY ambiente_configuracao (id_ambconfig, agendmidia, atendimento, autoplay, avancarretornar, chamfuncionarios, chaminstantanea, chamvariosfuncionarios, chamveiculo, controleblocos, controlecomerciais, controleinstitucionais, controleprogrametes, controlevolumeindividual, dataalteracao, datacriacao, generosbycc, horoscopo, locutorvirtual, menudownloads, nobreak, opcionais, pedidomusical, pedidomusicalvinheta, relatoriosmidia, rodoviarias, selecaogenero, volumechamadas, volumecomerciais, volumegeral, volumemusicas, vozlocucao, id_ambiente, id_usuarioalteracao, id_usuario) FROM stdin;
\.


--
-- Name: ambiente_configuracao_id_ambconfig_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('ambiente_configuracao_id_ambconfig_seq', 1, false);


--
-- Data for Name: ambiente_genero; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY ambiente_genero (id_ambgen, id_ambiente, id_genero) FROM stdin;
\.


--
-- Name: ambiente_genero_id_ambgen_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('ambiente_genero_id_ambgen_seq', 1, false);


--
-- Name: ambiente_id_ambiente_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('ambiente_id_ambiente_seq', 1, false);


--
-- Data for Name: bloco; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY bloco (id_bloco, indexhoracerta, indexinstitucionais, indexprogrametes, posicaovinheta, qtdcomerciais, qtdmusicas, id_ambiente) FROM stdin;
\.


--
-- Name: bloco_id_bloco_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('bloco_id_bloco_seq', 1, false);


--
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY categoria (id_categoria, codigo, descricao, nome) FROM stdin;
1	musica	Música	Música
2	vinheta	Vinheta	Vinheta
3	inst	Institucional	Institucional
4	comercial	Comercial	Comercial
5	programete	Programete	Programete
6	chamada-func	Chamada Funcionário	Chamada Funcionário
7	chamada-inst	Chamada Instantânea	Chamada Instantânea
\.


--
-- Name: categoria_id_categoria_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('categoria_id_categoria_seq', 7, true);


--
-- Data for Name: conexao; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY conexao (id_conexao, dados, dataconexao, datadesconexao, enderecoip, id_ambiente) FROM stdin;
\.


--
-- Name: conexao_id_conexao_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('conexao_id_conexao_seq', 1, false);


--
-- Data for Name: empresa; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY empresa (id_empresa, ativo, cnpj, codigo, dataalteracao, datacriacao, dominio, nomefantasia, razaosocial) FROM stdin;
1	t	28372714000140	Eterion	\N	2015-09-13 23:56:53.987601	www.eterion.com.br	Eterion	Eterion
\.


--
-- Name: empresa_id_empresa_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('empresa_id_empresa_seq', 1, true);


--
-- Data for Name: funcionalidade; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY funcionalidade (id_funcionalidade, icone, nome, ordem, url) FROM stdin;
1	fa-music	Gêneros	1	/ambientes/%d/view-generos/
2	fa-file-audio-o	Vinhetas	2	/ambientes/%d/view-list-upload-midia/vinheta/
3	fa-headphones	Institucionais	3	/ambientes/%d/view-list-upload-midia/inst/
4	fa-film	Comerciais	4	/ambientes/%d/view-list-upload-midia/comercial/
5	fa-bullhorn	Programetes	5	/ambientes/%d/view-list-upload-midia/programete/
6	fa-bolt	Chamadas<br/>Instantâneas	6	/ambientes/%d/view-list-upload-midia/chamada-inst/
7	fa-users	Chamadas<br/>Funcionários	7	/ambientes/%d/view-list-upload-midia/chamada-func/
8	fa-th-large	Blocos	8	/ambientes/%d/view-bloco/
9	fa-clock-o	Expediente	9	/ambientes/%d/view-expediente/
10	fa-newspaper-o	Eventos	10	/ambientes/%d/view-eventos/
11	fa-briefcase	Configurações	11	/ambientes/%d/view-configuracoes/
12	fa-bus	Rodoviária	12	/ambientes/%d/view-rodoviaria/
13	fa-files-o	Relatórios	13	/ambientes/%d/view-relatórios/
14	fa-floppy-o	Downloads	14	/ambientes/%d/view-downloads/
15	fa-unlock-alt	Restrições	15	/ambientes/%d/view-restricoes/
16	fa-trademark	Logomarca	16	/ambientes/%d/view-logomarca/
17	fa-play	Simular	17	/ambientes/%d/view-simular/
18	fa-microphone	SendVoice	18	/ambientes/%d/view-sendvoice/
\.


--
-- Name: funcionalidade_id_funcionalidade_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('funcionalidade_id_funcionalidade_seq', 18, true);


--
-- Data for Name: fuso_horario; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY fuso_horario (id_fusohorario, alias, canonid, offsetfuso, ordercomum) FROM stdin;
1	Acre	America/Lima	-05:00	2
2	Manaus	America/Manaus	-04:00	1
3	Brasília	America/Sao_Paulo	-03:00	0
4	Fernando Noronha	America/Noronha	-02:00	4
\.


--
-- Name: fuso_horario_id_fusohorario_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('fuso_horario_id_fusohorario_seq', 4, true);


--
-- Data for Name: genero; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY genero (id_genero, datacriacao, descricao, genero) FROM stdin;
1	2015-09-13 23:56:53.987601	Top 300 - Lançamentos	Top 300 - Lançamentos
2	2015-09-13 23:56:53.987601	Internacional	Internacional
3	2015-09-13 23:56:53.987601	Pop Rock Nacional	Pop Rock Nacional
4	2015-09-13 23:56:53.987601	Sertanejos	Sertanejos
5	2015-09-13 23:56:53.987601	Sertanejo universitário	Sertanejo universitário
6	2015-09-13 23:56:53.987601	Pagode e Samba	Pagode e Samba
7	2015-09-13 23:56:53.987601	Axé Music	Axé Music
8	2015-09-13 23:56:53.987601	Forró	Forró
9	2015-09-13 23:56:53.987601	Country	Country
10	2015-09-13 23:56:53.987601	Dance Music	Dance Music
11	2015-09-13 23:56:53.987601	Flashback	Flashback
12	2015-09-13 23:56:53.987601	Infantil	Infantil
13	2015-09-13 23:56:53.987601	Bossa Nova	Bossa Nova
14	2015-09-13 23:56:53.987601	Reggae Nacional	Reggae Nacional
15	2015-09-13 23:56:53.987601	Arrocha	Arrocha
16	2015-09-13 23:56:53.987601	Fitness Music	Fitness Music
17	2015-09-13 23:56:53.987601	Rock Internacional	Rock Internacional
18	2015-09-13 23:56:53.987601	Sertanejo Romântico	Sertanejo Romântico
19	2015-09-13 23:56:53.987601	Gospel Internacional	Gospel Internacional
20	2015-09-13 23:56:53.987601	Lounge	Lounge
21	2015-09-13 23:56:53.987601	Flash House	Flash House
22	2015-09-13 23:56:53.987601	Disco	Disco
23	2015-09-13 23:56:53.987601	Instrumental	Instrumental
\.


--
-- Name: genero_id_genero_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('genero_id_genero_seq', 23, true);


--
-- Data for Name: mensagem; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY mensagem (id_mensagem, assunto, conteudo, datacriacao, emailcopia, id_ambiente, id_usuario) FROM stdin;
\.


--
-- Name: mensagem_id_mensagem_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('mensagem_id_mensagem_seq', 1, false);


--
-- Data for Name: midia; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY midia (id_midia, album, artist, cached, comment, datacriacao, dataupload, datetag, descricao, extensao, filehash, filepath, filesize, genre, mimetype, nome, title, valido) FROM stdin;
\.


--
-- Data for Name: midia_ambiente; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY midia_ambiente (id_midiaamb, dataassociacao, id_ambiente, id_midia) FROM stdin;
\.


--
-- Name: midia_ambiente_id_midiaamb_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('midia_ambiente_id_midiaamb_seq', 1, false);


--
-- Data for Name: midia_categoria; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY midia_categoria (id_midia, id_categoria) FROM stdin;
\.


--
-- Data for Name: midia_genero; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY midia_genero (id_mediagen, id_genero, id_midia) FROM stdin;
\.


--
-- Name: midia_genero_id_mediagen_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('midia_genero_id_mediagen_seq', 1, false);


--
-- Name: midia_id_midia_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('midia_id_midia_seq', 1, false);


--
-- Data for Name: parametro; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY parametro (id_parametro, codigo, descricao, type, valor, id_empresa) FROM stdin;
1	BASE_MIDIA_PATH	Pasta em disco onde serão armazenadas as músicas EX: C:\\Temp\\Musicas	\N	/home/pazin/teste/	1
\.


--
-- Name: parametro_id_parametro_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('parametro_id_parametro_seq', 1, true);


--
-- Data for Name: perfil; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY perfil (id_perfil, nome) FROM stdin;
1	DESENVOLVEDOR
2	ADMINISTRADOR
3	GERENTE
4	SUPERVISOR
5	USUARIO
\.


--
-- Name: perfil_id_perfil_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('perfil_id_perfil_seq', 5, true);


--
-- Data for Name: perfil_permissao; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY perfil_permissao (id_perfperm, datacriacao, id_perfil, id_permissao) FROM stdin;
1	2015-09-13 23:56:53.987601	1	1
2	2015-09-13 23:56:53.987601	1	2
3	2015-09-13 23:56:53.987601	1	3
4	2015-09-13 23:56:53.987601	1	4
5	2015-09-13 23:56:53.987601	1	5
6	2015-09-13 23:56:53.987601	1	6
7	2015-09-13 23:56:53.987601	1	7
8	2015-09-13 23:56:53.987601	1	8
9	2015-09-13 23:56:53.987601	1	9
10	2015-09-13 23:56:53.987601	1	10
11	2015-09-13 23:56:53.987601	1	11
\.


--
-- Name: perfil_permissao_id_perfperm_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('perfil_permissao_id_perfperm_seq', 11, true);


--
-- Data for Name: permissao; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY permissao (id_permissao, codigo, descricao, id_permissaopai) FROM stdin;
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
-- Name: permissao_id_permissao_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('permissao_id_permissao_seq', 11, true);


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY usuario (id_usuario, ativo, dataalteracao, datacriacao, email, login, nome, password, id_empresa) FROM stdin;
1	t	\N	2015-09-13 23:56:53.987601	pazinfernando@gmail.com	fpazin	Fernando Pazin	$2a$08$jzf4G7i5TxtpYwZwEpsguudbkTgm2vmTmClah6sZkp9FqhGAG5uMC	1
2	t	\N	2015-09-13 23:56:53.987601	george.g.augusto@gmail.com	gaugusto	George Augusto	$2a$08$jzf4G7i5TxtpYwZwEpsguudbkTgm2vmTmClah6sZkp9FqhGAG5uMC	1
\.


--
-- Name: usuario_id_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('usuario_id_usuario_seq', 2, true);


--
-- Data for Name: usuario_perfil; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY usuario_perfil (id_usuperf, id_perfil, id_usuario) FROM stdin;
1	1	1
2	1	2
\.


--
-- Name: usuario_perfil_id_usuperf_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('usuario_perfil_id_usuperf_seq', 2, true);


--
-- Data for Name: usuario_permissao; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY usuario_permissao (id_usuperm, datacriacao, id_permissao, id_usuario) FROM stdin;
1	2015-09-13 23:56:53.987601	1	\N
\.


--
-- Name: usuario_permissao_id_usuperm_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('usuario_permissao_id_usuperm_seq', 1, true);


--
-- Name: acesso_usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY acesso_usuario
    ADD CONSTRAINT acesso_usuario_pkey PRIMARY KEY (id_acesso);


--
-- Name: ambiente_configuracao_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY ambiente_configuracao
    ADD CONSTRAINT ambiente_configuracao_pkey PRIMARY KEY (id_ambconfig);


--
-- Name: ambiente_genero_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY ambiente_genero
    ADD CONSTRAINT ambiente_genero_pkey PRIMARY KEY (id_ambgen);


--
-- Name: ambiente_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY ambiente
    ADD CONSTRAINT ambiente_pkey PRIMARY KEY (id_ambiente);


--
-- Name: bloco_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY bloco
    ADD CONSTRAINT bloco_pkey PRIMARY KEY (id_bloco);


--
-- Name: categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id_categoria);


--
-- Name: conexao_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY conexao
    ADD CONSTRAINT conexao_pkey PRIMARY KEY (id_conexao);


--
-- Name: empresa_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY empresa
    ADD CONSTRAINT empresa_pkey PRIMARY KEY (id_empresa);


--
-- Name: funcionalidade_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY funcionalidade
    ADD CONSTRAINT funcionalidade_pkey PRIMARY KEY (id_funcionalidade);


--
-- Name: fuso_horario_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY fuso_horario
    ADD CONSTRAINT fuso_horario_pkey PRIMARY KEY (id_fusohorario);


--
-- Name: genero_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY genero
    ADD CONSTRAINT genero_pkey PRIMARY KEY (id_genero);


--
-- Name: mensagem_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY mensagem
    ADD CONSTRAINT mensagem_pkey PRIMARY KEY (id_mensagem);


--
-- Name: midia_ambiente_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY midia_ambiente
    ADD CONSTRAINT midia_ambiente_pkey PRIMARY KEY (id_midiaamb);


--
-- Name: midia_genero_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY midia_genero
    ADD CONSTRAINT midia_genero_pkey PRIMARY KEY (id_mediagen);


--
-- Name: midia_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY midia
    ADD CONSTRAINT midia_pkey PRIMARY KEY (id_midia);


--
-- Name: parametro_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY parametro
    ADD CONSTRAINT parametro_pkey PRIMARY KEY (id_parametro);


--
-- Name: perfil_permissao_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY perfil_permissao
    ADD CONSTRAINT perfil_permissao_pkey PRIMARY KEY (id_perfperm);


--
-- Name: perfil_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY perfil
    ADD CONSTRAINT perfil_pkey PRIMARY KEY (id_perfil);


--
-- Name: permissao_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY permissao
    ADD CONSTRAINT permissao_pkey PRIMARY KEY (id_permissao);


--
-- Name: uk_pm3f4m4fqv89oeeeac4tbe2f4; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT uk_pm3f4m4fqv89oeeeac4tbe2f4 UNIQUE (login);


--
-- Name: usuario_perfil_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY usuario_perfil
    ADD CONSTRAINT usuario_perfil_pkey PRIMARY KEY (id_usuperf);


--
-- Name: usuario_permissao_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY usuario_permissao
    ADD CONSTRAINT usuario_permissao_pkey PRIMARY KEY (id_usuperm);


--
-- Name: usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario);


--
-- Name: fk_22epv3x8dnwseyn4awoxklqlq; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_categoria
    ADD CONSTRAINT fk_22epv3x8dnwseyn4awoxklqlq FOREIGN KEY (id_midia) REFERENCES midia(id_midia);


--
-- Name: fk_2ty4qfxrn30sdi6wos7aslh5t; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_ambiente
    ADD CONSTRAINT fk_2ty4qfxrn30sdi6wos7aslh5t FOREIGN KEY (id_midia) REFERENCES midia(id_midia);


--
-- Name: fk_39f4ndp7slw3lecg7s35bs880; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY parametro
    ADD CONSTRAINT fk_39f4ndp7slw3lecg7s35bs880 FOREIGN KEY (id_empresa) REFERENCES empresa(id_empresa);


--
-- Name: fk_3c9s7w987gf54ijwv9iokr0qh; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY permissao
    ADD CONSTRAINT fk_3c9s7w987gf54ijwv9iokr0qh FOREIGN KEY (id_permissaopai) REFERENCES permissao(id_permissao);


--
-- Name: fk_437f1gfc5fchywfr6garfgjfs; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY conexao
    ADD CONSTRAINT fk_437f1gfc5fchywfr6garfgjfs FOREIGN KEY (id_ambiente) REFERENCES ambiente(id_ambiente);


--
-- Name: fk_4cb7oihrgl9o10julhjtu2q2l; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY mensagem
    ADD CONSTRAINT fk_4cb7oihrgl9o10julhjtu2q2l FOREIGN KEY (id_ambiente) REFERENCES ambiente(id_ambiente);


--
-- Name: fk_611okm324dpxd0tyd9k3lb0d1; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente
    ADD CONSTRAINT fk_611okm324dpxd0tyd9k3lb0d1 FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);


--
-- Name: fk_69h6lninhg65xd7od6vscb1tf; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente_configuracao
    ADD CONSTRAINT fk_69h6lninhg65xd7od6vscb1tf FOREIGN KEY (id_ambiente) REFERENCES ambiente(id_ambiente);


--
-- Name: fk_6bb079ml5usas4l2jacvs2dev; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente_configuracao
    ADD CONSTRAINT fk_6bb079ml5usas4l2jacvs2dev FOREIGN KEY (id_usuarioalteracao) REFERENCES usuario(id_usuario);


--
-- Name: fk_9w1iw0eoe93w4m2aq0tihjc5d; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY mensagem
    ADD CONSTRAINT fk_9w1iw0eoe93w4m2aq0tihjc5d FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);


--
-- Name: fk_aq0t3uo6gxdvjy7woo7j3orlh; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY perfil_permissao
    ADD CONSTRAINT fk_aq0t3uo6gxdvjy7woo7j3orlh FOREIGN KEY (id_perfil) REFERENCES perfil(id_perfil);


--
-- Name: fk_av276cn7k7b8fqlsadbaw39tr; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente
    ADD CONSTRAINT fk_av276cn7k7b8fqlsadbaw39tr FOREIGN KEY (id_fusohorario) REFERENCES fuso_horario(id_fusohorario);


--
-- Name: fk_b0k4dx3ok7qinoeireica0ci5; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT fk_b0k4dx3ok7qinoeireica0ci5 FOREIGN KEY (id_empresa) REFERENCES empresa(id_empresa);


--
-- Name: fk_brudn4ldkpjmoaey8pe21datg; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_categoria
    ADD CONSTRAINT fk_brudn4ldkpjmoaey8pe21datg FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria);


--
-- Name: fk_dhsed93pxsxvk88x2ec9hgt15; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente_genero
    ADD CONSTRAINT fk_dhsed93pxsxvk88x2ec9hgt15 FOREIGN KEY (id_ambiente) REFERENCES ambiente(id_ambiente);


--
-- Name: fk_dwru3uterlja2wy88f0pikxbv; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY perfil_permissao
    ADD CONSTRAINT fk_dwru3uterlja2wy88f0pikxbv FOREIGN KEY (id_permissao) REFERENCES permissao(id_permissao);


--
-- Name: fk_fcoumipohnx3ye4dbc490itr7; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_permissao
    ADD CONSTRAINT fk_fcoumipohnx3ye4dbc490itr7 FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);


--
-- Name: fk_fp4srv5mbiqyc9cfb7dvucbiq; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_perfil
    ADD CONSTRAINT fk_fp4srv5mbiqyc9cfb7dvucbiq FOREIGN KEY (id_perfil) REFERENCES perfil(id_perfil);


--
-- Name: fk_g6mv5dao2a6xlg6c0wcx1im4o; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_perfil
    ADD CONSTRAINT fk_g6mv5dao2a6xlg6c0wcx1im4o FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);


--
-- Name: fk_iatvwjgc79bfpq3jp2savgfh; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_ambiente
    ADD CONSTRAINT fk_iatvwjgc79bfpq3jp2savgfh FOREIGN KEY (id_ambiente) REFERENCES ambiente(id_ambiente);


--
-- Name: fk_jr9i3w2v1vxtkwg6vo9hksqax; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY acesso_usuario
    ADD CONSTRAINT fk_jr9i3w2v1vxtkwg6vo9hksqax FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);


--
-- Name: fk_lb6vjulov4165n2up8ks5nvu1; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente
    ADD CONSTRAINT fk_lb6vjulov4165n2up8ks5nvu1 FOREIGN KEY (id_empresa) REFERENCES empresa(id_empresa);


--
-- Name: fk_lniwf79yvm66g1vyrb8iouu3w; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY bloco
    ADD CONSTRAINT fk_lniwf79yvm66g1vyrb8iouu3w FOREIGN KEY (id_ambiente) REFERENCES ambiente(id_ambiente);


--
-- Name: fk_mk1oe4srv9i0fqwbv9vxjw67c; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_genero
    ADD CONSTRAINT fk_mk1oe4srv9i0fqwbv9vxjw67c FOREIGN KEY (id_midia) REFERENCES midia(id_midia);


--
-- Name: fk_n5evd4n1wy3bc82h7tp5r2kt7; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente_genero
    ADD CONSTRAINT fk_n5evd4n1wy3bc82h7tp5r2kt7 FOREIGN KEY (id_genero) REFERENCES genero(id_genero);


--
-- Name: fk_ob42vsibs7gt9qrwmrg3gwax3; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_genero
    ADD CONSTRAINT fk_ob42vsibs7gt9qrwmrg3gwax3 FOREIGN KEY (id_genero) REFERENCES genero(id_genero);


--
-- Name: fk_q3um0dky0pwbo14outjmpexba; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_permissao
    ADD CONSTRAINT fk_q3um0dky0pwbo14outjmpexba FOREIGN KEY (id_permissao) REFERENCES permissao(id_permissao);


--
-- Name: fk_texupuhsyc125mpvqfg2elgqw; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente_configuracao
    ADD CONSTRAINT fk_texupuhsyc125mpvqfg2elgqw FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);


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

