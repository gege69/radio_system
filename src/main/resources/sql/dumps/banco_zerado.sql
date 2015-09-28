--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.4
-- Dumped by pg_dump version 9.4.4
-- Started on 2015-09-27 23:29:45 BRT

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 217 (class 3079 OID 11897)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2322 (class 0 OID 0)
-- Dependencies: 217
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 173 (class 1259 OID 27397)
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
-- TOC entry 172 (class 1259 OID 27395)
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
-- TOC entry 2323 (class 0 OID 0)
-- Dependencies: 172
-- Name: acesso_usuario_id_acesso_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE acesso_usuario_id_acesso_seq OWNED BY acesso_usuario.id_acesso;


--
-- TOC entry 175 (class 1259 OID 27408)
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
-- TOC entry 177 (class 1259 OID 27419)
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
-- TOC entry 176 (class 1259 OID 27417)
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
-- TOC entry 2324 (class 0 OID 0)
-- Dependencies: 176
-- Name: ambiente_configuracao_id_ambconfig_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE ambiente_configuracao_id_ambconfig_seq OWNED BY ambiente_configuracao.id_ambconfig;


--
-- TOC entry 179 (class 1259 OID 27427)
-- Name: ambiente_genero; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE ambiente_genero (
    id_ambgen bigint NOT NULL,
    id_ambiente bigint,
    id_genero bigint
);


ALTER TABLE ambiente_genero OWNER TO "radio-user";

--
-- TOC entry 178 (class 1259 OID 27425)
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
-- TOC entry 2325 (class 0 OID 0)
-- Dependencies: 178
-- Name: ambiente_genero_id_ambgen_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE ambiente_genero_id_ambgen_seq OWNED BY ambiente_genero.id_ambgen;


--
-- TOC entry 174 (class 1259 OID 27406)
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
-- TOC entry 2326 (class 0 OID 0)
-- Dependencies: 174
-- Name: ambiente_id_ambiente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE ambiente_id_ambiente_seq OWNED BY ambiente.id_ambiente;


--
-- TOC entry 181 (class 1259 OID 27435)
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
-- TOC entry 180 (class 1259 OID 27433)
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
-- TOC entry 2327 (class 0 OID 0)
-- Dependencies: 180
-- Name: bloco_id_bloco_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE bloco_id_bloco_seq OWNED BY bloco.id_bloco;


--
-- TOC entry 183 (class 1259 OID 27443)
-- Name: categoria; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE categoria (
    id_categoria bigint NOT NULL,
    codigo text,
    descricao text,
    nome character varying(100) NOT NULL,
    simpleupload boolean
);


ALTER TABLE categoria OWNER TO "radio-user";

--
-- TOC entry 182 (class 1259 OID 27441)
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
-- TOC entry 2328 (class 0 OID 0)
-- Dependencies: 182
-- Name: categoria_id_categoria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE categoria_id_categoria_seq OWNED BY categoria.id_categoria;


--
-- TOC entry 185 (class 1259 OID 27454)
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
-- TOC entry 184 (class 1259 OID 27452)
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
-- TOC entry 2329 (class 0 OID 0)
-- Dependencies: 184
-- Name: conexao_id_conexao_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE conexao_id_conexao_seq OWNED BY conexao.id_conexao;


--
-- TOC entry 187 (class 1259 OID 27465)
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
-- TOC entry 186 (class 1259 OID 27463)
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
-- TOC entry 2330 (class 0 OID 0)
-- Dependencies: 186
-- Name: empresa_id_empresa_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE empresa_id_empresa_seq OWNED BY empresa.id_empresa;


--
-- TOC entry 189 (class 1259 OID 27476)
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
-- TOC entry 188 (class 1259 OID 27474)
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
-- TOC entry 2331 (class 0 OID 0)
-- Dependencies: 188
-- Name: funcionalidade_id_funcionalidade_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE funcionalidade_id_funcionalidade_seq OWNED BY funcionalidade.id_funcionalidade;


--
-- TOC entry 191 (class 1259 OID 27487)
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
-- TOC entry 190 (class 1259 OID 27485)
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
-- TOC entry 2332 (class 0 OID 0)
-- Dependencies: 190
-- Name: fuso_horario_id_fusohorario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE fuso_horario_id_fusohorario_seq OWNED BY fuso_horario.id_fusohorario;


--
-- TOC entry 193 (class 1259 OID 27498)
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
-- TOC entry 192 (class 1259 OID 27496)
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
-- TOC entry 2333 (class 0 OID 0)
-- Dependencies: 192
-- Name: genero_id_genero_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE genero_id_genero_seq OWNED BY genero.id_genero;


--
-- TOC entry 195 (class 1259 OID 27509)
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
-- TOC entry 194 (class 1259 OID 27507)
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
-- TOC entry 2334 (class 0 OID 0)
-- Dependencies: 194
-- Name: mensagem_id_mensagem_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE mensagem_id_mensagem_seq OWNED BY mensagem.id_mensagem;


--
-- TOC entry 197 (class 1259 OID 27520)
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
-- TOC entry 199 (class 1259 OID 27531)
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
-- TOC entry 198 (class 1259 OID 27529)
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
-- TOC entry 2335 (class 0 OID 0)
-- Dependencies: 198
-- Name: midia_ambiente_id_midiaamb_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE midia_ambiente_id_midiaamb_seq OWNED BY midia_ambiente.id_midiaamb;


--
-- TOC entry 200 (class 1259 OID 27537)
-- Name: midia_categoria; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE midia_categoria (
    id_midia bigint NOT NULL,
    id_categoria bigint NOT NULL
);


ALTER TABLE midia_categoria OWNER TO "radio-user";

--
-- TOC entry 202 (class 1259 OID 27542)
-- Name: midia_genero; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE midia_genero (
    id_mediagen bigint NOT NULL,
    id_genero bigint,
    id_midia bigint
);


ALTER TABLE midia_genero OWNER TO "radio-user";

--
-- TOC entry 201 (class 1259 OID 27540)
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
-- TOC entry 2336 (class 0 OID 0)
-- Dependencies: 201
-- Name: midia_genero_id_mediagen_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE midia_genero_id_mediagen_seq OWNED BY midia_genero.id_mediagen;


--
-- TOC entry 196 (class 1259 OID 27518)
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
-- TOC entry 2337 (class 0 OID 0)
-- Dependencies: 196
-- Name: midia_id_midia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE midia_id_midia_seq OWNED BY midia.id_midia;


--
-- TOC entry 204 (class 1259 OID 27550)
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
-- TOC entry 203 (class 1259 OID 27548)
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
-- TOC entry 2338 (class 0 OID 0)
-- Dependencies: 203
-- Name: parametro_id_parametro_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE parametro_id_parametro_seq OWNED BY parametro.id_parametro;


--
-- TOC entry 206 (class 1259 OID 27561)
-- Name: perfil; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE perfil (
    id_perfil bigint NOT NULL,
    nome text NOT NULL
);


ALTER TABLE perfil OWNER TO "radio-user";

--
-- TOC entry 205 (class 1259 OID 27559)
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
-- TOC entry 2339 (class 0 OID 0)
-- Dependencies: 205
-- Name: perfil_id_perfil_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE perfil_id_perfil_seq OWNED BY perfil.id_perfil;


--
-- TOC entry 208 (class 1259 OID 27572)
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
-- TOC entry 207 (class 1259 OID 27570)
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
-- TOC entry 2340 (class 0 OID 0)
-- Dependencies: 207
-- Name: perfil_permissao_id_perfperm_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE perfil_permissao_id_perfperm_seq OWNED BY perfil_permissao.id_perfperm;


--
-- TOC entry 210 (class 1259 OID 27580)
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
-- TOC entry 209 (class 1259 OID 27578)
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
-- TOC entry 2341 (class 0 OID 0)
-- Dependencies: 209
-- Name: permissao_id_permissao_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE permissao_id_permissao_seq OWNED BY permissao.id_permissao;


--
-- TOC entry 212 (class 1259 OID 27591)
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
-- TOC entry 211 (class 1259 OID 27589)
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
-- TOC entry 2342 (class 0 OID 0)
-- Dependencies: 211
-- Name: usuario_id_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE usuario_id_usuario_seq OWNED BY usuario.id_usuario;


--
-- TOC entry 214 (class 1259 OID 27602)
-- Name: usuario_perfil; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE usuario_perfil (
    id_usuperf bigint NOT NULL,
    id_perfil bigint,
    id_usuario bigint
);


ALTER TABLE usuario_perfil OWNER TO "radio-user";

--
-- TOC entry 213 (class 1259 OID 27600)
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
-- TOC entry 2343 (class 0 OID 0)
-- Dependencies: 213
-- Name: usuario_perfil_id_usuperf_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE usuario_perfil_id_usuperf_seq OWNED BY usuario_perfil.id_usuperf;


--
-- TOC entry 216 (class 1259 OID 27610)
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
-- TOC entry 215 (class 1259 OID 27608)
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
-- TOC entry 2344 (class 0 OID 0)
-- Dependencies: 215
-- Name: usuario_permissao_id_usuperm_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE usuario_permissao_id_usuperm_seq OWNED BY usuario_permissao.id_usuperm;


--
-- TOC entry 2065 (class 2604 OID 27400)
-- Name: id_acesso; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY acesso_usuario ALTER COLUMN id_acesso SET DEFAULT nextval('acesso_usuario_id_acesso_seq'::regclass);


--
-- TOC entry 2066 (class 2604 OID 27411)
-- Name: id_ambiente; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente ALTER COLUMN id_ambiente SET DEFAULT nextval('ambiente_id_ambiente_seq'::regclass);


--
-- TOC entry 2067 (class 2604 OID 27422)
-- Name: id_ambconfig; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente_configuracao ALTER COLUMN id_ambconfig SET DEFAULT nextval('ambiente_configuracao_id_ambconfig_seq'::regclass);


--
-- TOC entry 2068 (class 2604 OID 27430)
-- Name: id_ambgen; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente_genero ALTER COLUMN id_ambgen SET DEFAULT nextval('ambiente_genero_id_ambgen_seq'::regclass);


--
-- TOC entry 2069 (class 2604 OID 27438)
-- Name: id_bloco; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY bloco ALTER COLUMN id_bloco SET DEFAULT nextval('bloco_id_bloco_seq'::regclass);


--
-- TOC entry 2070 (class 2604 OID 27446)
-- Name: id_categoria; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY categoria ALTER COLUMN id_categoria SET DEFAULT nextval('categoria_id_categoria_seq'::regclass);


--
-- TOC entry 2071 (class 2604 OID 27457)
-- Name: id_conexao; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY conexao ALTER COLUMN id_conexao SET DEFAULT nextval('conexao_id_conexao_seq'::regclass);


--
-- TOC entry 2072 (class 2604 OID 27468)
-- Name: id_empresa; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY empresa ALTER COLUMN id_empresa SET DEFAULT nextval('empresa_id_empresa_seq'::regclass);


--
-- TOC entry 2073 (class 2604 OID 27479)
-- Name: id_funcionalidade; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY funcionalidade ALTER COLUMN id_funcionalidade SET DEFAULT nextval('funcionalidade_id_funcionalidade_seq'::regclass);


--
-- TOC entry 2074 (class 2604 OID 27490)
-- Name: id_fusohorario; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY fuso_horario ALTER COLUMN id_fusohorario SET DEFAULT nextval('fuso_horario_id_fusohorario_seq'::regclass);


--
-- TOC entry 2075 (class 2604 OID 27501)
-- Name: id_genero; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY genero ALTER COLUMN id_genero SET DEFAULT nextval('genero_id_genero_seq'::regclass);


--
-- TOC entry 2076 (class 2604 OID 27512)
-- Name: id_mensagem; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY mensagem ALTER COLUMN id_mensagem SET DEFAULT nextval('mensagem_id_mensagem_seq'::regclass);


--
-- TOC entry 2077 (class 2604 OID 27523)
-- Name: id_midia; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia ALTER COLUMN id_midia SET DEFAULT nextval('midia_id_midia_seq'::regclass);


--
-- TOC entry 2078 (class 2604 OID 27534)
-- Name: id_midiaamb; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_ambiente ALTER COLUMN id_midiaamb SET DEFAULT nextval('midia_ambiente_id_midiaamb_seq'::regclass);


--
-- TOC entry 2079 (class 2604 OID 27545)
-- Name: id_mediagen; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_genero ALTER COLUMN id_mediagen SET DEFAULT nextval('midia_genero_id_mediagen_seq'::regclass);


--
-- TOC entry 2080 (class 2604 OID 27553)
-- Name: id_parametro; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY parametro ALTER COLUMN id_parametro SET DEFAULT nextval('parametro_id_parametro_seq'::regclass);


--
-- TOC entry 2081 (class 2604 OID 27564)
-- Name: id_perfil; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY perfil ALTER COLUMN id_perfil SET DEFAULT nextval('perfil_id_perfil_seq'::regclass);


--
-- TOC entry 2082 (class 2604 OID 27575)
-- Name: id_perfperm; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY perfil_permissao ALTER COLUMN id_perfperm SET DEFAULT nextval('perfil_permissao_id_perfperm_seq'::regclass);


--
-- TOC entry 2083 (class 2604 OID 27583)
-- Name: id_permissao; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY permissao ALTER COLUMN id_permissao SET DEFAULT nextval('permissao_id_permissao_seq'::regclass);


--
-- TOC entry 2084 (class 2604 OID 27594)
-- Name: id_usuario; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario ALTER COLUMN id_usuario SET DEFAULT nextval('usuario_id_usuario_seq'::regclass);


--
-- TOC entry 2085 (class 2604 OID 27605)
-- Name: id_usuperf; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_perfil ALTER COLUMN id_usuperf SET DEFAULT nextval('usuario_perfil_id_usuperf_seq'::regclass);


--
-- TOC entry 2086 (class 2604 OID 27613)
-- Name: id_usuperm; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_permissao ALTER COLUMN id_usuperm SET DEFAULT nextval('usuario_permissao_id_usuperm_seq'::regclass);


--
-- TOC entry 2271 (class 0 OID 27397)
-- Dependencies: 173
-- Data for Name: acesso_usuario; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY acesso_usuario (id_acesso, dados, datacriacao, enderecoip, id_usuario) FROM stdin;
\.


--
-- TOC entry 2345 (class 0 OID 0)
-- Dependencies: 172
-- Name: acesso_usuario_id_acesso_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('acesso_usuario_id_acesso_seq', 1, false);


--
-- TOC entry 2273 (class 0 OID 27408)
-- Dependencies: 175
-- Data for Name: ambiente; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY ambiente (id_ambiente, anotacoes, bairro, cidade, dataalteracao, datacriacao, download, email1, email2, estado, horafimexpediente, horainiexpediente, login, logradouro, minutofimexpediente, minutoiniexpediente, nome, numero, opcionais, password, sincronizar, telefone1, telefone2, urlambiente, id_empresa, id_fusohorario, id_usuario) FROM stdin;
\.


--
-- TOC entry 2275 (class 0 OID 27419)
-- Dependencies: 177
-- Data for Name: ambiente_configuracao; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY ambiente_configuracao (id_ambconfig, agendmidia, atendimento, autoplay, avancarretornar, chamfuncionarios, chaminstantanea, chamvariosfuncionarios, chamveiculo, controleblocos, controlecomerciais, controleinstitucionais, controleprogrametes, controlevolumeindividual, dataalteracao, datacriacao, generosbycc, horoscopo, locutorvirtual, menudownloads, nobreak, opcionais, pedidomusical, pedidomusicalvinheta, relatoriosmidia, rodoviarias, selecaogenero, volumechamadas, volumecomerciais, volumegeral, volumemusicas, vozlocucao, id_ambiente, id_usuarioalteracao, id_usuario) FROM stdin;
\.


--
-- TOC entry 2346 (class 0 OID 0)
-- Dependencies: 176
-- Name: ambiente_configuracao_id_ambconfig_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('ambiente_configuracao_id_ambconfig_seq', 1, false);


--
-- TOC entry 2277 (class 0 OID 27427)
-- Dependencies: 179
-- Data for Name: ambiente_genero; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY ambiente_genero (id_ambgen, id_ambiente, id_genero) FROM stdin;
\.


--
-- TOC entry 2347 (class 0 OID 0)
-- Dependencies: 178
-- Name: ambiente_genero_id_ambgen_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('ambiente_genero_id_ambgen_seq', 1, false);


--
-- TOC entry 2348 (class 0 OID 0)
-- Dependencies: 174
-- Name: ambiente_id_ambiente_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('ambiente_id_ambiente_seq', 1, false);


--
-- TOC entry 2279 (class 0 OID 27435)
-- Dependencies: 181
-- Data for Name: bloco; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY bloco (id_bloco, indexhoracerta, indexinstitucionais, indexprogrametes, posicaovinheta, qtdcomerciais, qtdmusicas, id_ambiente) FROM stdin;
\.


--
-- TOC entry 2349 (class 0 OID 0)
-- Dependencies: 180
-- Name: bloco_id_bloco_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('bloco_id_bloco_seq', 1, false);


--
-- TOC entry 2281 (class 0 OID 27443)
-- Dependencies: 183
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY categoria (id_categoria, codigo, descricao, nome, simpleupload) FROM stdin;
1	musica	Música	Música	f
2	vinheta	Vinheta	Vinheta	t
3	inst	Institucional	Institucional	t
4	comercial	Comercial	Comercial	t
5	programete	Programete	Programete	t
6	chamada_inst	Chamada Instantânea	Chamada Instantânea	t
7	chamada_func_nome	Nome da chamada de Funcionário	Chamada Funcionário (Nome)	f
8	chamada_func_frase	Frase da chamada de Funcionário	Chamada Funcionário (Frase)	f
\.


--
-- TOC entry 2350 (class 0 OID 0)
-- Dependencies: 182
-- Name: categoria_id_categoria_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('categoria_id_categoria_seq', 8, true);


--
-- TOC entry 2283 (class 0 OID 27454)
-- Dependencies: 185
-- Data for Name: conexao; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY conexao (id_conexao, dados, dataconexao, datadesconexao, enderecoip, id_ambiente) FROM stdin;
\.


--
-- TOC entry 2351 (class 0 OID 0)
-- Dependencies: 184
-- Name: conexao_id_conexao_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('conexao_id_conexao_seq', 1, false);


--
-- TOC entry 2285 (class 0 OID 27465)
-- Dependencies: 187
-- Data for Name: empresa; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY empresa (id_empresa, ativo, cnpj, codigo, dataalteracao, datacriacao, dominio, nomefantasia, razaosocial) FROM stdin;
1	t	28372714000140	Eterion	\N	2015-09-27 23:29:05.337594	www.eterion.com.br	Eterion	Eterion
\.


--
-- TOC entry 2352 (class 0 OID 0)
-- Dependencies: 186
-- Name: empresa_id_empresa_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('empresa_id_empresa_seq', 1, true);


--
-- TOC entry 2287 (class 0 OID 27476)
-- Dependencies: 189
-- Data for Name: funcionalidade; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY funcionalidade (id_funcionalidade, icone, nome, ordem, url) FROM stdin;
1	fa-music	Gêneros	1	/ambientes/%d/view-generos/
2	fa-file-audio-o	Vinhetas	2	/ambientes/%d/view-list-upload-midia/vinheta/
3	fa-headphones	Institucionais	3	/ambientes/%d/view-list-upload-midia/inst/
4	fa-film	Comerciais	4	/ambientes/%d/view-list-upload-midia/comercial/
5	fa-bullhorn	Programetes	5	/ambientes/%d/view-list-upload-midia/programete/
6	fa-bolt	Chamadas<br/>Instantâneas	6	/ambientes/%d/view-list-upload-midia/chamada-inst/
7	fa-users	Chamadas<br/>Funcionários	7	/ambientes/%d/view-list-chamada-funcionarios
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
-- TOC entry 2353 (class 0 OID 0)
-- Dependencies: 188
-- Name: funcionalidade_id_funcionalidade_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('funcionalidade_id_funcionalidade_seq', 18, true);


--
-- TOC entry 2289 (class 0 OID 27487)
-- Dependencies: 191
-- Data for Name: fuso_horario; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY fuso_horario (id_fusohorario, alias, canonid, offsetfuso, ordercomum) FROM stdin;
1	Acre	America/Lima	-05:00	2
2	Manaus	America/Manaus	-04:00	1
3	Brasília	America/Sao_Paulo	-03:00	0
4	Fernando Noronha	America/Noronha	-02:00	4
\.


--
-- TOC entry 2354 (class 0 OID 0)
-- Dependencies: 190
-- Name: fuso_horario_id_fusohorario_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('fuso_horario_id_fusohorario_seq', 4, true);


--
-- TOC entry 2291 (class 0 OID 27498)
-- Dependencies: 193
-- Data for Name: genero; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY genero (id_genero, datacriacao, descricao, genero) FROM stdin;
1	2015-09-27 23:29:05.337594	Top 300 - Lançamentos	Top 300 - Lançamentos
2	2015-09-27 23:29:05.337594	Internacional	Internacional
3	2015-09-27 23:29:05.337594	Pop Rock Nacional	Pop Rock Nacional
4	2015-09-27 23:29:05.337594	Sertanejos	Sertanejos
5	2015-09-27 23:29:05.337594	Sertanejo universitário	Sertanejo universitário
6	2015-09-27 23:29:05.337594	Pagode e Samba	Pagode e Samba
7	2015-09-27 23:29:05.337594	Axé Music	Axé Music
8	2015-09-27 23:29:05.337594	Forró	Forró
9	2015-09-27 23:29:05.337594	Country	Country
10	2015-09-27 23:29:05.337594	Dance Music	Dance Music
11	2015-09-27 23:29:05.337594	Flashback	Flashback
12	2015-09-27 23:29:05.337594	Infantil	Infantil
13	2015-09-27 23:29:05.337594	Bossa Nova	Bossa Nova
14	2015-09-27 23:29:05.337594	Reggae Nacional	Reggae Nacional
15	2015-09-27 23:29:05.337594	Arrocha	Arrocha
16	2015-09-27 23:29:05.337594	Fitness Music	Fitness Music
17	2015-09-27 23:29:05.337594	Rock Internacional	Rock Internacional
18	2015-09-27 23:29:05.337594	Sertanejo Romântico	Sertanejo Romântico
19	2015-09-27 23:29:05.337594	Gospel Internacional	Gospel Internacional
20	2015-09-27 23:29:05.337594	Lounge	Lounge
21	2015-09-27 23:29:05.337594	Flash House	Flash House
22	2015-09-27 23:29:05.337594	Disco	Disco
23	2015-09-27 23:29:05.337594	Instrumental	Instrumental
\.


--
-- TOC entry 2355 (class 0 OID 0)
-- Dependencies: 192
-- Name: genero_id_genero_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('genero_id_genero_seq', 23, true);


--
-- TOC entry 2293 (class 0 OID 27509)
-- Dependencies: 195
-- Data for Name: mensagem; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY mensagem (id_mensagem, assunto, conteudo, datacriacao, emailcopia, id_ambiente, id_usuario) FROM stdin;
\.


--
-- TOC entry 2356 (class 0 OID 0)
-- Dependencies: 194
-- Name: mensagem_id_mensagem_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('mensagem_id_mensagem_seq', 1, false);


--
-- TOC entry 2295 (class 0 OID 27520)
-- Dependencies: 197
-- Data for Name: midia; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY midia (id_midia, album, artist, cached, comment, datacriacao, dataupload, datetag, descricao, extensao, filehash, filepath, filesize, genre, mimetype, nome, title, valido) FROM stdin;
\.


--
-- TOC entry 2297 (class 0 OID 27531)
-- Dependencies: 199
-- Data for Name: midia_ambiente; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY midia_ambiente (id_midiaamb, dataassociacao, id_ambiente, id_midia) FROM stdin;
\.


--
-- TOC entry 2357 (class 0 OID 0)
-- Dependencies: 198
-- Name: midia_ambiente_id_midiaamb_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('midia_ambiente_id_midiaamb_seq', 1, false);


--
-- TOC entry 2298 (class 0 OID 27537)
-- Dependencies: 200
-- Data for Name: midia_categoria; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY midia_categoria (id_midia, id_categoria) FROM stdin;
\.


--
-- TOC entry 2300 (class 0 OID 27542)
-- Dependencies: 202
-- Data for Name: midia_genero; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY midia_genero (id_mediagen, id_genero, id_midia) FROM stdin;
\.


--
-- TOC entry 2358 (class 0 OID 0)
-- Dependencies: 201
-- Name: midia_genero_id_mediagen_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('midia_genero_id_mediagen_seq', 1, false);


--
-- TOC entry 2359 (class 0 OID 0)
-- Dependencies: 196
-- Name: midia_id_midia_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('midia_id_midia_seq', 1, false);


--
-- TOC entry 2302 (class 0 OID 27550)
-- Dependencies: 204
-- Data for Name: parametro; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY parametro (id_parametro, codigo, descricao, type, valor, id_empresa) FROM stdin;
1	BASE_MIDIA_PATH	Pasta em disco onde serão armazenadas as músicas EX: C:\\Temp\\Musicas	\N	/home/pazin/teste/	1
\.


--
-- TOC entry 2360 (class 0 OID 0)
-- Dependencies: 203
-- Name: parametro_id_parametro_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('parametro_id_parametro_seq', 1, true);


--
-- TOC entry 2304 (class 0 OID 27561)
-- Dependencies: 206
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
-- TOC entry 2361 (class 0 OID 0)
-- Dependencies: 205
-- Name: perfil_id_perfil_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('perfil_id_perfil_seq', 5, true);


--
-- TOC entry 2306 (class 0 OID 27572)
-- Dependencies: 208
-- Data for Name: perfil_permissao; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY perfil_permissao (id_perfperm, datacriacao, id_perfil, id_permissao) FROM stdin;
1	2015-09-27 23:29:05.337594	1	1
2	2015-09-27 23:29:05.337594	1	2
3	2015-09-27 23:29:05.337594	1	3
4	2015-09-27 23:29:05.337594	1	4
5	2015-09-27 23:29:05.337594	1	5
6	2015-09-27 23:29:05.337594	1	6
7	2015-09-27 23:29:05.337594	1	7
8	2015-09-27 23:29:05.337594	1	8
9	2015-09-27 23:29:05.337594	1	9
10	2015-09-27 23:29:05.337594	1	10
11	2015-09-27 23:29:05.337594	1	11
\.


--
-- TOC entry 2362 (class 0 OID 0)
-- Dependencies: 207
-- Name: perfil_permissao_id_perfperm_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('perfil_permissao_id_perfperm_seq', 11, true);


--
-- TOC entry 2308 (class 0 OID 27580)
-- Dependencies: 210
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
10	USUARIOS		\N
11	MOBILE		\N
\.


--
-- TOC entry 2363 (class 0 OID 0)
-- Dependencies: 209
-- Name: permissao_id_permissao_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('permissao_id_permissao_seq', 11, true);


--
-- TOC entry 2310 (class 0 OID 27591)
-- Dependencies: 212
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY usuario (id_usuario, ativo, dataalteracao, datacriacao, email, login, nome, password, id_empresa) FROM stdin;
1	t	\N	2015-09-27 23:29:05.337594	pazinfernando@gmail.com	fpazin	Fernando Pazin	$2a$08$jzf4G7i5TxtpYwZwEpsguudbkTgm2vmTmClah6sZkp9FqhGAG5uMC	1
2	t	\N	2015-09-27 23:29:05.337594	george.g.augusto@gmail.com	gaugusto	George Augusto	$2a$08$jzf4G7i5TxtpYwZwEpsguudbkTgm2vmTmClah6sZkp9FqhGAG5uMC	1
\.


--
-- TOC entry 2364 (class 0 OID 0)
-- Dependencies: 211
-- Name: usuario_id_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('usuario_id_usuario_seq', 2, true);


--
-- TOC entry 2312 (class 0 OID 27602)
-- Dependencies: 214
-- Data for Name: usuario_perfil; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY usuario_perfil (id_usuperf, id_perfil, id_usuario) FROM stdin;
1	1	1
2	1	2
\.


--
-- TOC entry 2365 (class 0 OID 0)
-- Dependencies: 213
-- Name: usuario_perfil_id_usuperf_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('usuario_perfil_id_usuperf_seq', 2, true);


--
-- TOC entry 2314 (class 0 OID 27610)
-- Dependencies: 216
-- Data for Name: usuario_permissao; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY usuario_permissao (id_usuperm, datacriacao, id_permissao, id_usuario) FROM stdin;
1	2015-09-27 23:29:05.337594	1	\N
\.


--
-- TOC entry 2366 (class 0 OID 0)
-- Dependencies: 215
-- Name: usuario_permissao_id_usuperm_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('usuario_permissao_id_usuperm_seq', 1, true);


--
-- TOC entry 2088 (class 2606 OID 27405)
-- Name: acesso_usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY acesso_usuario
    ADD CONSTRAINT acesso_usuario_pkey PRIMARY KEY (id_acesso);


--
-- TOC entry 2092 (class 2606 OID 27424)
-- Name: ambiente_configuracao_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY ambiente_configuracao
    ADD CONSTRAINT ambiente_configuracao_pkey PRIMARY KEY (id_ambconfig);


--
-- TOC entry 2094 (class 2606 OID 27432)
-- Name: ambiente_genero_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY ambiente_genero
    ADD CONSTRAINT ambiente_genero_pkey PRIMARY KEY (id_ambgen);


--
-- TOC entry 2090 (class 2606 OID 27416)
-- Name: ambiente_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY ambiente
    ADD CONSTRAINT ambiente_pkey PRIMARY KEY (id_ambiente);


--
-- TOC entry 2096 (class 2606 OID 27440)
-- Name: bloco_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY bloco
    ADD CONSTRAINT bloco_pkey PRIMARY KEY (id_bloco);


--
-- TOC entry 2098 (class 2606 OID 27451)
-- Name: categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id_categoria);


--
-- TOC entry 2100 (class 2606 OID 27462)
-- Name: conexao_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY conexao
    ADD CONSTRAINT conexao_pkey PRIMARY KEY (id_conexao);


--
-- TOC entry 2102 (class 2606 OID 27473)
-- Name: empresa_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY empresa
    ADD CONSTRAINT empresa_pkey PRIMARY KEY (id_empresa);


--
-- TOC entry 2104 (class 2606 OID 27484)
-- Name: funcionalidade_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY funcionalidade
    ADD CONSTRAINT funcionalidade_pkey PRIMARY KEY (id_funcionalidade);


--
-- TOC entry 2106 (class 2606 OID 27495)
-- Name: fuso_horario_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY fuso_horario
    ADD CONSTRAINT fuso_horario_pkey PRIMARY KEY (id_fusohorario);


--
-- TOC entry 2108 (class 2606 OID 27506)
-- Name: genero_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY genero
    ADD CONSTRAINT genero_pkey PRIMARY KEY (id_genero);


--
-- TOC entry 2110 (class 2606 OID 27517)
-- Name: mensagem_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY mensagem
    ADD CONSTRAINT mensagem_pkey PRIMARY KEY (id_mensagem);


--
-- TOC entry 2114 (class 2606 OID 27536)
-- Name: midia_ambiente_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY midia_ambiente
    ADD CONSTRAINT midia_ambiente_pkey PRIMARY KEY (id_midiaamb);


--
-- TOC entry 2116 (class 2606 OID 27547)
-- Name: midia_genero_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY midia_genero
    ADD CONSTRAINT midia_genero_pkey PRIMARY KEY (id_mediagen);


--
-- TOC entry 2112 (class 2606 OID 27528)
-- Name: midia_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY midia
    ADD CONSTRAINT midia_pkey PRIMARY KEY (id_midia);


--
-- TOC entry 2118 (class 2606 OID 27558)
-- Name: parametro_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY parametro
    ADD CONSTRAINT parametro_pkey PRIMARY KEY (id_parametro);


--
-- TOC entry 2122 (class 2606 OID 27577)
-- Name: perfil_permissao_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY perfil_permissao
    ADD CONSTRAINT perfil_permissao_pkey PRIMARY KEY (id_perfperm);


--
-- TOC entry 2120 (class 2606 OID 27569)
-- Name: perfil_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY perfil
    ADD CONSTRAINT perfil_pkey PRIMARY KEY (id_perfil);


--
-- TOC entry 2124 (class 2606 OID 27588)
-- Name: permissao_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY permissao
    ADD CONSTRAINT permissao_pkey PRIMARY KEY (id_permissao);


--
-- TOC entry 2126 (class 2606 OID 27617)
-- Name: uk_pm3f4m4fqv89oeeeac4tbe2f4; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT uk_pm3f4m4fqv89oeeeac4tbe2f4 UNIQUE (login);


--
-- TOC entry 2130 (class 2606 OID 27607)
-- Name: usuario_perfil_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY usuario_perfil
    ADD CONSTRAINT usuario_perfil_pkey PRIMARY KEY (id_usuperf);


--
-- TOC entry 2132 (class 2606 OID 27615)
-- Name: usuario_permissao_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY usuario_permissao
    ADD CONSTRAINT usuario_permissao_pkey PRIMARY KEY (id_usuperm);


--
-- TOC entry 2128 (class 2606 OID 27599)
-- Name: usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario);


--
-- TOC entry 2149 (class 2606 OID 27698)
-- Name: fk_22epv3x8dnwseyn4awoxklqlq; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_categoria
    ADD CONSTRAINT fk_22epv3x8dnwseyn4awoxklqlq FOREIGN KEY (id_midia) REFERENCES midia(id_midia);


--
-- TOC entry 2147 (class 2606 OID 27688)
-- Name: fk_2ty4qfxrn30sdi6wos7aslh5t; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_ambiente
    ADD CONSTRAINT fk_2ty4qfxrn30sdi6wos7aslh5t FOREIGN KEY (id_midia) REFERENCES midia(id_midia);


--
-- TOC entry 2152 (class 2606 OID 27713)
-- Name: fk_39f4ndp7slw3lecg7s35bs880; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY parametro
    ADD CONSTRAINT fk_39f4ndp7slw3lecg7s35bs880 FOREIGN KEY (id_empresa) REFERENCES empresa(id_empresa);


--
-- TOC entry 2155 (class 2606 OID 27728)
-- Name: fk_3c9s7w987gf54ijwv9iokr0qh; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY permissao
    ADD CONSTRAINT fk_3c9s7w987gf54ijwv9iokr0qh FOREIGN KEY (id_permissaopai) REFERENCES permissao(id_permissao);


--
-- TOC entry 2143 (class 2606 OID 27668)
-- Name: fk_437f1gfc5fchywfr6garfgjfs; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY conexao
    ADD CONSTRAINT fk_437f1gfc5fchywfr6garfgjfs FOREIGN KEY (id_ambiente) REFERENCES ambiente(id_ambiente);


--
-- TOC entry 2144 (class 2606 OID 27673)
-- Name: fk_4cb7oihrgl9o10julhjtu2q2l; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY mensagem
    ADD CONSTRAINT fk_4cb7oihrgl9o10julhjtu2q2l FOREIGN KEY (id_ambiente) REFERENCES ambiente(id_ambiente);


--
-- TOC entry 2136 (class 2606 OID 27633)
-- Name: fk_611okm324dpxd0tyd9k3lb0d1; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente
    ADD CONSTRAINT fk_611okm324dpxd0tyd9k3lb0d1 FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);


--
-- TOC entry 2137 (class 2606 OID 27638)
-- Name: fk_69h6lninhg65xd7od6vscb1tf; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente_configuracao
    ADD CONSTRAINT fk_69h6lninhg65xd7od6vscb1tf FOREIGN KEY (id_ambiente) REFERENCES ambiente(id_ambiente);


--
-- TOC entry 2138 (class 2606 OID 27643)
-- Name: fk_6bb079ml5usas4l2jacvs2dev; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente_configuracao
    ADD CONSTRAINT fk_6bb079ml5usas4l2jacvs2dev FOREIGN KEY (id_usuarioalteracao) REFERENCES usuario(id_usuario);


--
-- TOC entry 2145 (class 2606 OID 27678)
-- Name: fk_9w1iw0eoe93w4m2aq0tihjc5d; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY mensagem
    ADD CONSTRAINT fk_9w1iw0eoe93w4m2aq0tihjc5d FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);


--
-- TOC entry 2153 (class 2606 OID 27718)
-- Name: fk_aq0t3uo6gxdvjy7woo7j3orlh; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY perfil_permissao
    ADD CONSTRAINT fk_aq0t3uo6gxdvjy7woo7j3orlh FOREIGN KEY (id_perfil) REFERENCES perfil(id_perfil);


--
-- TOC entry 2135 (class 2606 OID 27628)
-- Name: fk_av276cn7k7b8fqlsadbaw39tr; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente
    ADD CONSTRAINT fk_av276cn7k7b8fqlsadbaw39tr FOREIGN KEY (id_fusohorario) REFERENCES fuso_horario(id_fusohorario);


--
-- TOC entry 2156 (class 2606 OID 27733)
-- Name: fk_b0k4dx3ok7qinoeireica0ci5; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT fk_b0k4dx3ok7qinoeireica0ci5 FOREIGN KEY (id_empresa) REFERENCES empresa(id_empresa);


--
-- TOC entry 2148 (class 2606 OID 27693)
-- Name: fk_brudn4ldkpjmoaey8pe21datg; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_categoria
    ADD CONSTRAINT fk_brudn4ldkpjmoaey8pe21datg FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria);


--
-- TOC entry 2140 (class 2606 OID 27653)
-- Name: fk_dhsed93pxsxvk88x2ec9hgt15; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente_genero
    ADD CONSTRAINT fk_dhsed93pxsxvk88x2ec9hgt15 FOREIGN KEY (id_ambiente) REFERENCES ambiente(id_ambiente);


--
-- TOC entry 2154 (class 2606 OID 27723)
-- Name: fk_dwru3uterlja2wy88f0pikxbv; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY perfil_permissao
    ADD CONSTRAINT fk_dwru3uterlja2wy88f0pikxbv FOREIGN KEY (id_permissao) REFERENCES permissao(id_permissao);


--
-- TOC entry 2160 (class 2606 OID 27753)
-- Name: fk_fcoumipohnx3ye4dbc490itr7; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_permissao
    ADD CONSTRAINT fk_fcoumipohnx3ye4dbc490itr7 FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);


--
-- TOC entry 2157 (class 2606 OID 27738)
-- Name: fk_fp4srv5mbiqyc9cfb7dvucbiq; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_perfil
    ADD CONSTRAINT fk_fp4srv5mbiqyc9cfb7dvucbiq FOREIGN KEY (id_perfil) REFERENCES perfil(id_perfil);


--
-- TOC entry 2158 (class 2606 OID 27743)
-- Name: fk_g6mv5dao2a6xlg6c0wcx1im4o; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_perfil
    ADD CONSTRAINT fk_g6mv5dao2a6xlg6c0wcx1im4o FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);


--
-- TOC entry 2146 (class 2606 OID 27683)
-- Name: fk_iatvwjgc79bfpq3jp2savgfh; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_ambiente
    ADD CONSTRAINT fk_iatvwjgc79bfpq3jp2savgfh FOREIGN KEY (id_ambiente) REFERENCES ambiente(id_ambiente);


--
-- TOC entry 2133 (class 2606 OID 27618)
-- Name: fk_jr9i3w2v1vxtkwg6vo9hksqax; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY acesso_usuario
    ADD CONSTRAINT fk_jr9i3w2v1vxtkwg6vo9hksqax FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);


--
-- TOC entry 2134 (class 2606 OID 27623)
-- Name: fk_lb6vjulov4165n2up8ks5nvu1; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente
    ADD CONSTRAINT fk_lb6vjulov4165n2up8ks5nvu1 FOREIGN KEY (id_empresa) REFERENCES empresa(id_empresa);


--
-- TOC entry 2142 (class 2606 OID 27663)
-- Name: fk_lniwf79yvm66g1vyrb8iouu3w; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY bloco
    ADD CONSTRAINT fk_lniwf79yvm66g1vyrb8iouu3w FOREIGN KEY (id_ambiente) REFERENCES ambiente(id_ambiente);


--
-- TOC entry 2151 (class 2606 OID 27708)
-- Name: fk_mk1oe4srv9i0fqwbv9vxjw67c; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_genero
    ADD CONSTRAINT fk_mk1oe4srv9i0fqwbv9vxjw67c FOREIGN KEY (id_midia) REFERENCES midia(id_midia);


--
-- TOC entry 2141 (class 2606 OID 27658)
-- Name: fk_n5evd4n1wy3bc82h7tp5r2kt7; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente_genero
    ADD CONSTRAINT fk_n5evd4n1wy3bc82h7tp5r2kt7 FOREIGN KEY (id_genero) REFERENCES genero(id_genero);


--
-- TOC entry 2150 (class 2606 OID 27703)
-- Name: fk_ob42vsibs7gt9qrwmrg3gwax3; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_genero
    ADD CONSTRAINT fk_ob42vsibs7gt9qrwmrg3gwax3 FOREIGN KEY (id_genero) REFERENCES genero(id_genero);


--
-- TOC entry 2159 (class 2606 OID 27748)
-- Name: fk_q3um0dky0pwbo14outjmpexba; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_permissao
    ADD CONSTRAINT fk_q3um0dky0pwbo14outjmpexba FOREIGN KEY (id_permissao) REFERENCES permissao(id_permissao);


--
-- TOC entry 2139 (class 2606 OID 27648)
-- Name: fk_texupuhsyc125mpvqfg2elgqw; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente_configuracao
    ADD CONSTRAINT fk_texupuhsyc125mpvqfg2elgqw FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);


--
-- TOC entry 2321 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-09-27 23:29:45 BRT

--
-- PostgreSQL database dump complete
--

