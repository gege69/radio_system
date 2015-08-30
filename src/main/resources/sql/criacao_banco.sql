--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.4
-- Dumped by pg_dump version 9.4.4
-- Started on 2015-08-30 10:05:04 BRT

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 205 (class 3079 OID 11897)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2239 (class 0 OID 0)
-- Dependencies: 205
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 174 (class 1259 OID 20167)
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
-- TOC entry 173 (class 1259 OID 20165)
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
-- TOC entry 2240 (class 0 OID 0)
-- Dependencies: 173
-- Name: acesso_usuario_id_acesso_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE acesso_usuario_id_acesso_seq OWNED BY acesso_usuario.id_acesso;


--
-- TOC entry 176 (class 1259 OID 20178)
-- Name: ambiente; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE ambiente (
    id_ambiente bigint NOT NULL,
    anotacoes text,
    bairro character varying(100),
    cidade character varying(100),
    dataalteracao timestamp without time zone,
    datacriacao timestamp without time zone NOT NULL,
    download boolean,
    email1 character varying(255),
    email2 character varying(255),
    estado character varying(200),
    login character varying(40) NOT NULL,
    logradouro character varying(200),
    nome character varying(200) NOT NULL,
    numero character varying(20),
    opcionais boolean,
    password character varying(200) NOT NULL,
    sincronizar boolean,
    telefone1 character varying(255),
    telefone2 character varying(255),
    id_fusohorario bigint,
    id_usuario bigint
);


ALTER TABLE ambiente OWNER TO "radio-user";

--
-- TOC entry 175 (class 1259 OID 20176)
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
-- TOC entry 2241 (class 0 OID 0)
-- Dependencies: 175
-- Name: ambiente_id_ambiente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE ambiente_id_ambiente_seq OWNED BY ambiente.id_ambiente;


--
-- TOC entry 178 (class 1259 OID 20189)
-- Name: categoria; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE categoria (
    id_categoria bigint NOT NULL,
    descricao text,
    categoria character varying(100) NOT NULL
);


ALTER TABLE categoria OWNER TO "radio-user";

--
-- TOC entry 177 (class 1259 OID 20187)
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
-- TOC entry 2242 (class 0 OID 0)
-- Dependencies: 177
-- Name: categoria_id_categoria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE categoria_id_categoria_seq OWNED BY categoria.id_categoria;


--
-- TOC entry 180 (class 1259 OID 20200)
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
-- TOC entry 179 (class 1259 OID 20198)
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
-- TOC entry 2243 (class 0 OID 0)
-- Dependencies: 179
-- Name: conexao_id_conexao_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE conexao_id_conexao_seq OWNED BY conexao.id_conexao;


--
-- TOC entry 182 (class 1259 OID 20211)
-- Name: fuso_horario; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE fuso_horario (
    id_fusohorario bigint NOT NULL,
    alias character varying(300) NOT NULL,
    canonid character varying(100) NOT NULL,
    offsetfuso character varying(20) NOT NULL,
    ordercomum integer
);


ALTER TABLE fuso_horario OWNER TO "radio-user";

--
-- TOC entry 181 (class 1259 OID 20209)
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
-- TOC entry 2244 (class 0 OID 0)
-- Dependencies: 181
-- Name: fuso_horario_id_fusohorario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE fuso_horario_id_fusohorario_seq OWNED BY fuso_horario.id_fusohorario;


--
-- TOC entry 184 (class 1259 OID 20219)
-- Name: genero; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE genero (
    id_genero bigint NOT NULL,
    datacriacao timestamp without time zone NOT NULL,
    descricao text,
    genero character varying(100) NOT NULL
);


ALTER TABLE genero OWNER TO "radio-user";

--
-- TOC entry 183 (class 1259 OID 20217)
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
-- TOC entry 2245 (class 0 OID 0)
-- Dependencies: 183
-- Name: genero_id_genero_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE genero_id_genero_seq OWNED BY genero.id_genero;


--
-- TOC entry 172 (class 1259 OID 18936)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO "radio-user";

--
-- TOC entry 186 (class 1259 OID 20230)
-- Name: mensagem; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE mensagem (
    id_mensagem bigint NOT NULL,
    assunto character varying(200) NOT NULL,
    conteudo text NOT NULL,
    datacriacao timestamp without time zone NOT NULL,
    emailcopia character varying(200),
    id_ambiente bigint,
    id_usuario bigint
);


ALTER TABLE mensagem OWNER TO "radio-user";

--
-- TOC entry 185 (class 1259 OID 20228)
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
-- TOC entry 2246 (class 0 OID 0)
-- Dependencies: 185
-- Name: mensagem_id_mensagem_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE mensagem_id_mensagem_seq OWNED BY mensagem.id_mensagem;


--
-- TOC entry 188 (class 1259 OID 20241)
-- Name: midia; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE midia (
    id_midia bigint NOT NULL,
    album character varying(255),
    artist character varying(255),
    cached boolean,
    comment character varying(255),
    datacriacao timestamp without time zone,
    dataupload timestamp without time zone NOT NULL,
    datetag character varying(255),
    descricao text,
    extensao character varying(10),
    filehash character varying(200) NOT NULL,
    filepath character varying(200) NOT NULL,
    genre character varying(255),
    mimetype character varying(200) NOT NULL,
    nome character varying(200) NOT NULL,
    title character varying(255),
    valido boolean,
    id_categoria bigint
);


ALTER TABLE midia OWNER TO "radio-user";

--
-- TOC entry 190 (class 1259 OID 20252)
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
-- TOC entry 189 (class 1259 OID 20250)
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
-- TOC entry 2247 (class 0 OID 0)
-- Dependencies: 189
-- Name: midia_ambiente_id_midiaamb_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE midia_ambiente_id_midiaamb_seq OWNED BY midia_ambiente.id_midiaamb;


--
-- TOC entry 192 (class 1259 OID 20260)
-- Name: midia_genero; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE midia_genero (
    id_mediagen bigint NOT NULL,
    id_genero bigint,
    id_midia bigint
);


ALTER TABLE midia_genero OWNER TO "radio-user";

--
-- TOC entry 191 (class 1259 OID 20258)
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
-- TOC entry 2248 (class 0 OID 0)
-- Dependencies: 191
-- Name: midia_genero_id_mediagen_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE midia_genero_id_mediagen_seq OWNED BY midia_genero.id_mediagen;


--
-- TOC entry 187 (class 1259 OID 20239)
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
-- TOC entry 2249 (class 0 OID 0)
-- Dependencies: 187
-- Name: midia_id_midia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE midia_id_midia_seq OWNED BY midia.id_midia;


--
-- TOC entry 194 (class 1259 OID 20268)
-- Name: perfil; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE perfil (
    id_perfil bigint NOT NULL,
    nome character varying(100) NOT NULL
);


ALTER TABLE perfil OWNER TO "radio-user";

--
-- TOC entry 193 (class 1259 OID 20266)
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
-- TOC entry 2250 (class 0 OID 0)
-- Dependencies: 193
-- Name: perfil_id_perfil_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE perfil_id_perfil_seq OWNED BY perfil.id_perfil;


--
-- TOC entry 196 (class 1259 OID 20276)
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
-- TOC entry 195 (class 1259 OID 20274)
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
-- TOC entry 2251 (class 0 OID 0)
-- Dependencies: 195
-- Name: perfil_permissao_id_perfperm_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE perfil_permissao_id_perfperm_seq OWNED BY perfil_permissao.id_perfperm;


--
-- TOC entry 198 (class 1259 OID 20284)
-- Name: permissao; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE permissao (
    id_permissao bigint NOT NULL,
    codigo character varying(100) NOT NULL,
    descricao character varying(400) NOT NULL,
    id_permissaopai bigint
);


ALTER TABLE permissao OWNER TO "radio-user";

--
-- TOC entry 197 (class 1259 OID 20282)
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
-- TOC entry 2252 (class 0 OID 0)
-- Dependencies: 197
-- Name: permissao_id_permissao_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE permissao_id_permissao_seq OWNED BY permissao.id_permissao;


--
-- TOC entry 200 (class 1259 OID 20295)
-- Name: usuario; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE usuario (
    id_usuario bigint NOT NULL,
    ativo boolean NOT NULL,
    dataalteracao timestamp without time zone,
    datacriacao timestamp without time zone NOT NULL,
    email character varying(200) NOT NULL,
    login character varying(40) NOT NULL,
    nome character varying(200) NOT NULL,
    password character varying(255) NOT NULL
);


ALTER TABLE usuario OWNER TO "radio-user";

--
-- TOC entry 199 (class 1259 OID 20293)
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
-- TOC entry 2253 (class 0 OID 0)
-- Dependencies: 199
-- Name: usuario_id_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE usuario_id_usuario_seq OWNED BY usuario.id_usuario;


--
-- TOC entry 202 (class 1259 OID 20306)
-- Name: usuario_perfil; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE usuario_perfil (
    id_usuperf bigint NOT NULL,
    id_perfil bigint,
    id_usuario bigint
);


ALTER TABLE usuario_perfil OWNER TO "radio-user";

--
-- TOC entry 201 (class 1259 OID 20304)
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
-- TOC entry 2254 (class 0 OID 0)
-- Dependencies: 201
-- Name: usuario_perfil_id_usuperf_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE usuario_perfil_id_usuperf_seq OWNED BY usuario_perfil.id_usuperf;


--
-- TOC entry 204 (class 1259 OID 20314)
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
-- TOC entry 203 (class 1259 OID 20312)
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
-- TOC entry 2255 (class 0 OID 0)
-- Dependencies: 203
-- Name: usuario_permissao_id_usuperm_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE usuario_permissao_id_usuperm_seq OWNED BY usuario_permissao.id_usuperm;


--
-- TOC entry 2022 (class 2604 OID 20170)
-- Name: id_acesso; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY acesso_usuario ALTER COLUMN id_acesso SET DEFAULT nextval('acesso_usuario_id_acesso_seq'::regclass);


--
-- TOC entry 2023 (class 2604 OID 20181)
-- Name: id_ambiente; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente ALTER COLUMN id_ambiente SET DEFAULT nextval('ambiente_id_ambiente_seq'::regclass);


--
-- TOC entry 2024 (class 2604 OID 20192)
-- Name: id_categoria; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY categoria ALTER COLUMN id_categoria SET DEFAULT nextval('categoria_id_categoria_seq'::regclass);


--
-- TOC entry 2025 (class 2604 OID 20203)
-- Name: id_conexao; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY conexao ALTER COLUMN id_conexao SET DEFAULT nextval('conexao_id_conexao_seq'::regclass);


--
-- TOC entry 2026 (class 2604 OID 20214)
-- Name: id_fusohorario; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY fuso_horario ALTER COLUMN id_fusohorario SET DEFAULT nextval('fuso_horario_id_fusohorario_seq'::regclass);


--
-- TOC entry 2027 (class 2604 OID 20222)
-- Name: id_genero; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY genero ALTER COLUMN id_genero SET DEFAULT nextval('genero_id_genero_seq'::regclass);


--
-- TOC entry 2028 (class 2604 OID 20233)
-- Name: id_mensagem; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY mensagem ALTER COLUMN id_mensagem SET DEFAULT nextval('mensagem_id_mensagem_seq'::regclass);


--
-- TOC entry 2029 (class 2604 OID 20244)
-- Name: id_midia; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia ALTER COLUMN id_midia SET DEFAULT nextval('midia_id_midia_seq'::regclass);


--
-- TOC entry 2030 (class 2604 OID 20255)
-- Name: id_midiaamb; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_ambiente ALTER COLUMN id_midiaamb SET DEFAULT nextval('midia_ambiente_id_midiaamb_seq'::regclass);


--
-- TOC entry 2031 (class 2604 OID 20263)
-- Name: id_mediagen; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_genero ALTER COLUMN id_mediagen SET DEFAULT nextval('midia_genero_id_mediagen_seq'::regclass);


--
-- TOC entry 2032 (class 2604 OID 20271)
-- Name: id_perfil; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY perfil ALTER COLUMN id_perfil SET DEFAULT nextval('perfil_id_perfil_seq'::regclass);


--
-- TOC entry 2033 (class 2604 OID 20279)
-- Name: id_perfperm; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY perfil_permissao ALTER COLUMN id_perfperm SET DEFAULT nextval('perfil_permissao_id_perfperm_seq'::regclass);


--
-- TOC entry 2034 (class 2604 OID 20287)
-- Name: id_permissao; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY permissao ALTER COLUMN id_permissao SET DEFAULT nextval('permissao_id_permissao_seq'::regclass);


--
-- TOC entry 2035 (class 2604 OID 20298)
-- Name: id_usuario; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario ALTER COLUMN id_usuario SET DEFAULT nextval('usuario_id_usuario_seq'::regclass);


--
-- TOC entry 2036 (class 2604 OID 20309)
-- Name: id_usuperf; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_perfil ALTER COLUMN id_usuperf SET DEFAULT nextval('usuario_perfil_id_usuperf_seq'::regclass);


--
-- TOC entry 2037 (class 2604 OID 20317)
-- Name: id_usuperm; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_permissao ALTER COLUMN id_usuperm SET DEFAULT nextval('usuario_permissao_id_usuperm_seq'::regclass);


--
-- TOC entry 2201 (class 0 OID 20167)
-- Dependencies: 174
-- Data for Name: acesso_usuario; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY acesso_usuario (id_acesso, dados, datacriacao, enderecoip, id_usuario) FROM stdin;
\.


--
-- TOC entry 2256 (class 0 OID 0)
-- Dependencies: 173
-- Name: acesso_usuario_id_acesso_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('acesso_usuario_id_acesso_seq', 1, false);


--
-- TOC entry 2203 (class 0 OID 20178)
-- Dependencies: 176
-- Data for Name: ambiente; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY ambiente (id_ambiente, anotacoes, bairro, cidade, dataalteracao, datacriacao, download, email1, email2, estado, login, logradouro, nome, numero, opcionais, password, sincronizar, telefone1, telefone2, id_fusohorario, id_usuario) FROM stdin;
\.


--
-- TOC entry 2257 (class 0 OID 0)
-- Dependencies: 175
-- Name: ambiente_id_ambiente_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('ambiente_id_ambiente_seq', 1, false);


--
-- TOC entry 2205 (class 0 OID 20189)
-- Dependencies: 178
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY categoria (id_categoria, descricao, categoria) FROM stdin;
\.


--
-- TOC entry 2258 (class 0 OID 0)
-- Dependencies: 177
-- Name: categoria_id_categoria_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('categoria_id_categoria_seq', 1, false);


--
-- TOC entry 2207 (class 0 OID 20200)
-- Dependencies: 180
-- Data for Name: conexao; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY conexao (id_conexao, dados, dataconexao, datadesconexao, enderecoip, id_ambiente) FROM stdin;
\.


--
-- TOC entry 2259 (class 0 OID 0)
-- Dependencies: 179
-- Name: conexao_id_conexao_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('conexao_id_conexao_seq', 1, false);


--
-- TOC entry 2209 (class 0 OID 20211)
-- Dependencies: 182
-- Data for Name: fuso_horario; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY fuso_horario (id_fusohorario, alias, canonid, offsetfuso, ordercomum) FROM stdin;
\.


--
-- TOC entry 2260 (class 0 OID 0)
-- Dependencies: 181
-- Name: fuso_horario_id_fusohorario_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('fuso_horario_id_fusohorario_seq', 1, false);


--
-- TOC entry 2211 (class 0 OID 20219)
-- Dependencies: 184
-- Data for Name: genero; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY genero (id_genero, datacriacao, descricao, genero) FROM stdin;
\.


--
-- TOC entry 2261 (class 0 OID 0)
-- Dependencies: 183
-- Name: genero_id_genero_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('genero_id_genero_seq', 1, false);


--
-- TOC entry 2262 (class 0 OID 0)
-- Dependencies: 172
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('hibernate_sequence', 3, true);


--
-- TOC entry 2213 (class 0 OID 20230)
-- Dependencies: 186
-- Data for Name: mensagem; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY mensagem (id_mensagem, assunto, conteudo, datacriacao, emailcopia, id_ambiente, id_usuario) FROM stdin;
\.


--
-- TOC entry 2263 (class 0 OID 0)
-- Dependencies: 185
-- Name: mensagem_id_mensagem_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('mensagem_id_mensagem_seq', 1, false);


--
-- TOC entry 2215 (class 0 OID 20241)
-- Dependencies: 188
-- Data for Name: midia; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY midia (id_midia, album, artist, cached, comment, datacriacao, dataupload, datetag, descricao, extensao, filehash, filepath, genre, mimetype, nome, title, valido, id_categoria) FROM stdin;
\.


--
-- TOC entry 2217 (class 0 OID 20252)
-- Dependencies: 190
-- Data for Name: midia_ambiente; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY midia_ambiente (id_midiaamb, dataassociacao, id_ambiente, id_midia) FROM stdin;
\.


--
-- TOC entry 2264 (class 0 OID 0)
-- Dependencies: 189
-- Name: midia_ambiente_id_midiaamb_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('midia_ambiente_id_midiaamb_seq', 1, false);


--
-- TOC entry 2219 (class 0 OID 20260)
-- Dependencies: 192
-- Data for Name: midia_genero; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY midia_genero (id_mediagen, id_genero, id_midia) FROM stdin;
\.


--
-- TOC entry 2265 (class 0 OID 0)
-- Dependencies: 191
-- Name: midia_genero_id_mediagen_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('midia_genero_id_mediagen_seq', 1, false);


--
-- TOC entry 2266 (class 0 OID 0)
-- Dependencies: 187
-- Name: midia_id_midia_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('midia_id_midia_seq', 1, false);


--
-- TOC entry 2221 (class 0 OID 20268)
-- Dependencies: 194
-- Data for Name: perfil; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY perfil (id_perfil, nome) FROM stdin;
\.


--
-- TOC entry 2267 (class 0 OID 0)
-- Dependencies: 193
-- Name: perfil_id_perfil_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('perfil_id_perfil_seq', 1, false);


--
-- TOC entry 2223 (class 0 OID 20276)
-- Dependencies: 196
-- Data for Name: perfil_permissao; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY perfil_permissao (id_perfperm, datacriacao, id_perfil, id_permissao) FROM stdin;
\.


--
-- TOC entry 2268 (class 0 OID 0)
-- Dependencies: 195
-- Name: perfil_permissao_id_perfperm_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('perfil_permissao_id_perfperm_seq', 1, false);


--
-- TOC entry 2225 (class 0 OID 20284)
-- Dependencies: 198
-- Data for Name: permissao; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY permissao (id_permissao, codigo, descricao, id_permissaopai) FROM stdin;
\.


--
-- TOC entry 2269 (class 0 OID 0)
-- Dependencies: 197
-- Name: permissao_id_permissao_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('permissao_id_permissao_seq', 1, false);


--
-- TOC entry 2227 (class 0 OID 20295)
-- Dependencies: 200
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY usuario (id_usuario, ativo, dataalteracao, datacriacao, email, login, nome, password) FROM stdin;
\.


--
-- TOC entry 2270 (class 0 OID 0)
-- Dependencies: 199
-- Name: usuario_id_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('usuario_id_usuario_seq', 1, false);


--
-- TOC entry 2229 (class 0 OID 20306)
-- Dependencies: 202
-- Data for Name: usuario_perfil; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY usuario_perfil (id_usuperf, id_perfil, id_usuario) FROM stdin;
\.


--
-- TOC entry 2271 (class 0 OID 0)
-- Dependencies: 201
-- Name: usuario_perfil_id_usuperf_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('usuario_perfil_id_usuperf_seq', 1, false);


--
-- TOC entry 2231 (class 0 OID 20314)
-- Dependencies: 204
-- Data for Name: usuario_permissao; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY usuario_permissao (id_usuperm, datacriacao, id_permissao, id_usuario) FROM stdin;
\.


--
-- TOC entry 2272 (class 0 OID 0)
-- Dependencies: 203
-- Name: usuario_permissao_id_usuperm_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('usuario_permissao_id_usuperm_seq', 1, false);


--
-- TOC entry 2039 (class 2606 OID 20175)
-- Name: acesso_usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY acesso_usuario
    ADD CONSTRAINT acesso_usuario_pkey PRIMARY KEY (id_acesso);


--
-- TOC entry 2041 (class 2606 OID 20186)
-- Name: ambiente_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY ambiente
    ADD CONSTRAINT ambiente_pkey PRIMARY KEY (id_ambiente);


--
-- TOC entry 2043 (class 2606 OID 20197)
-- Name: categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id_categoria);


--
-- TOC entry 2045 (class 2606 OID 20208)
-- Name: conexao_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY conexao
    ADD CONSTRAINT conexao_pkey PRIMARY KEY (id_conexao);


--
-- TOC entry 2047 (class 2606 OID 20216)
-- Name: fuso_horario_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY fuso_horario
    ADD CONSTRAINT fuso_horario_pkey PRIMARY KEY (id_fusohorario);


--
-- TOC entry 2049 (class 2606 OID 20227)
-- Name: genero_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY genero
    ADD CONSTRAINT genero_pkey PRIMARY KEY (id_genero);


--
-- TOC entry 2051 (class 2606 OID 20238)
-- Name: mensagem_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY mensagem
    ADD CONSTRAINT mensagem_pkey PRIMARY KEY (id_mensagem);


--
-- TOC entry 2055 (class 2606 OID 20257)
-- Name: midia_ambiente_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY midia_ambiente
    ADD CONSTRAINT midia_ambiente_pkey PRIMARY KEY (id_midiaamb);


--
-- TOC entry 2057 (class 2606 OID 20265)
-- Name: midia_genero_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY midia_genero
    ADD CONSTRAINT midia_genero_pkey PRIMARY KEY (id_mediagen);


--
-- TOC entry 2053 (class 2606 OID 20249)
-- Name: midia_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY midia
    ADD CONSTRAINT midia_pkey PRIMARY KEY (id_midia);


--
-- TOC entry 2061 (class 2606 OID 20281)
-- Name: perfil_permissao_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY perfil_permissao
    ADD CONSTRAINT perfil_permissao_pkey PRIMARY KEY (id_perfperm);


--
-- TOC entry 2059 (class 2606 OID 20273)
-- Name: perfil_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY perfil
    ADD CONSTRAINT perfil_pkey PRIMARY KEY (id_perfil);


--
-- TOC entry 2063 (class 2606 OID 20292)
-- Name: permissao_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY permissao
    ADD CONSTRAINT permissao_pkey PRIMARY KEY (id_permissao);


--
-- TOC entry 2065 (class 2606 OID 20321)
-- Name: uk_pm3f4m4fqv89oeeeac4tbe2f4; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT uk_pm3f4m4fqv89oeeeac4tbe2f4 UNIQUE (login);


--
-- TOC entry 2069 (class 2606 OID 20311)
-- Name: usuario_perfil_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY usuario_perfil
    ADD CONSTRAINT usuario_perfil_pkey PRIMARY KEY (id_usuperf);


--
-- TOC entry 2071 (class 2606 OID 20319)
-- Name: usuario_permissao_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY usuario_permissao
    ADD CONSTRAINT usuario_permissao_pkey PRIMARY KEY (id_usuperm);


--
-- TOC entry 2067 (class 2606 OID 20303)
-- Name: usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario);


--
-- TOC entry 2080 (class 2606 OID 20362)
-- Name: fk_2ty4qfxrn30sdi6wos7aslh5t; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_ambiente
    ADD CONSTRAINT fk_2ty4qfxrn30sdi6wos7aslh5t FOREIGN KEY (id_midia) REFERENCES midia(id_midia);


--
-- TOC entry 2085 (class 2606 OID 20387)
-- Name: fk_3c9s7w987gf54ijwv9iokr0qh; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY permissao
    ADD CONSTRAINT fk_3c9s7w987gf54ijwv9iokr0qh FOREIGN KEY (id_permissaopai) REFERENCES permissao(id_permissao);


--
-- TOC entry 2075 (class 2606 OID 20337)
-- Name: fk_437f1gfc5fchywfr6garfgjfs; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY conexao
    ADD CONSTRAINT fk_437f1gfc5fchywfr6garfgjfs FOREIGN KEY (id_ambiente) REFERENCES ambiente(id_ambiente);


--
-- TOC entry 2076 (class 2606 OID 20342)
-- Name: fk_4cb7oihrgl9o10julhjtu2q2l; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY mensagem
    ADD CONSTRAINT fk_4cb7oihrgl9o10julhjtu2q2l FOREIGN KEY (id_ambiente) REFERENCES ambiente(id_ambiente);


--
-- TOC entry 2074 (class 2606 OID 20332)
-- Name: fk_611okm324dpxd0tyd9k3lb0d1; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente
    ADD CONSTRAINT fk_611okm324dpxd0tyd9k3lb0d1 FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);


--
-- TOC entry 2077 (class 2606 OID 20347)
-- Name: fk_9w1iw0eoe93w4m2aq0tihjc5d; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY mensagem
    ADD CONSTRAINT fk_9w1iw0eoe93w4m2aq0tihjc5d FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);


--
-- TOC entry 2083 (class 2606 OID 20377)
-- Name: fk_aq0t3uo6gxdvjy7woo7j3orlh; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY perfil_permissao
    ADD CONSTRAINT fk_aq0t3uo6gxdvjy7woo7j3orlh FOREIGN KEY (id_perfil) REFERENCES perfil(id_perfil);


--
-- TOC entry 2073 (class 2606 OID 20327)
-- Name: fk_av276cn7k7b8fqlsadbaw39tr; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente
    ADD CONSTRAINT fk_av276cn7k7b8fqlsadbaw39tr FOREIGN KEY (id_fusohorario) REFERENCES fuso_horario(id_fusohorario);


--
-- TOC entry 2084 (class 2606 OID 20382)
-- Name: fk_dwru3uterlja2wy88f0pikxbv; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY perfil_permissao
    ADD CONSTRAINT fk_dwru3uterlja2wy88f0pikxbv FOREIGN KEY (id_permissao) REFERENCES permissao(id_permissao);


--
-- TOC entry 2089 (class 2606 OID 20407)
-- Name: fk_fcoumipohnx3ye4dbc490itr7; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_permissao
    ADD CONSTRAINT fk_fcoumipohnx3ye4dbc490itr7 FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);


--
-- TOC entry 2086 (class 2606 OID 20392)
-- Name: fk_fp4srv5mbiqyc9cfb7dvucbiq; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_perfil
    ADD CONSTRAINT fk_fp4srv5mbiqyc9cfb7dvucbiq FOREIGN KEY (id_perfil) REFERENCES perfil(id_perfil);


--
-- TOC entry 2087 (class 2606 OID 20397)
-- Name: fk_g6mv5dao2a6xlg6c0wcx1im4o; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_perfil
    ADD CONSTRAINT fk_g6mv5dao2a6xlg6c0wcx1im4o FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);


--
-- TOC entry 2079 (class 2606 OID 20357)
-- Name: fk_iatvwjgc79bfpq3jp2savgfh; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_ambiente
    ADD CONSTRAINT fk_iatvwjgc79bfpq3jp2savgfh FOREIGN KEY (id_ambiente) REFERENCES ambiente(id_ambiente);


--
-- TOC entry 2072 (class 2606 OID 20322)
-- Name: fk_jr9i3w2v1vxtkwg6vo9hksqax; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY acesso_usuario
    ADD CONSTRAINT fk_jr9i3w2v1vxtkwg6vo9hksqax FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);


--
-- TOC entry 2082 (class 2606 OID 20372)
-- Name: fk_mk1oe4srv9i0fqwbv9vxjw67c; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_genero
    ADD CONSTRAINT fk_mk1oe4srv9i0fqwbv9vxjw67c FOREIGN KEY (id_midia) REFERENCES midia(id_midia);


--
-- TOC entry 2081 (class 2606 OID 20367)
-- Name: fk_ob42vsibs7gt9qrwmrg3gwax3; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_genero
    ADD CONSTRAINT fk_ob42vsibs7gt9qrwmrg3gwax3 FOREIGN KEY (id_genero) REFERENCES genero(id_genero);


--
-- TOC entry 2088 (class 2606 OID 20402)
-- Name: fk_q3um0dky0pwbo14outjmpexba; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_permissao
    ADD CONSTRAINT fk_q3um0dky0pwbo14outjmpexba FOREIGN KEY (id_permissao) REFERENCES permissao(id_permissao);


--
-- TOC entry 2078 (class 2606 OID 20352)
-- Name: fk_qds4sfjvty82ldp5ucrv6cktj; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia
    ADD CONSTRAINT fk_qds4sfjvty82ldp5ucrv6cktj FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria);


--
-- TOC entry 2238 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-08-30 10:05:04 BRT

--
-- PostgreSQL database dump complete
--

