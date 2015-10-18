--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.5
-- Dumped by pg_dump version 9.4.5
-- Started on 2015-10-18 15:30:08 BRST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 221 (class 3079 OID 11897)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2353 (class 0 OID 0)
-- Dependencies: 221
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 173 (class 1259 OID 33306)
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
-- TOC entry 172 (class 1259 OID 33304)
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
-- TOC entry 2354 (class 0 OID 0)
-- Dependencies: 172
-- Name: acesso_usuario_id_acesso_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE acesso_usuario_id_acesso_seq OWNED BY acesso_usuario.id_acesso;


--
-- TOC entry 175 (class 1259 OID 33317)
-- Name: ambiente; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE ambiente (
    id_ambiente bigint NOT NULL,
    anotacoes text,
    ativo boolean DEFAULT true,
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
    logomarca bytea,
    logomimetype character varying(255),
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
-- TOC entry 177 (class 1259 OID 33329)
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
-- TOC entry 176 (class 1259 OID 33327)
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
-- TOC entry 2355 (class 0 OID 0)
-- Dependencies: 176
-- Name: ambiente_configuracao_id_ambconfig_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE ambiente_configuracao_id_ambconfig_seq OWNED BY ambiente_configuracao.id_ambconfig;


--
-- TOC entry 179 (class 1259 OID 33337)
-- Name: ambiente_genero; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE ambiente_genero (
    id_ambgen bigint NOT NULL,
    id_ambiente bigint,
    id_genero bigint
);


ALTER TABLE ambiente_genero OWNER TO "radio-user";

--
-- TOC entry 178 (class 1259 OID 33335)
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
-- TOC entry 2356 (class 0 OID 0)
-- Dependencies: 178
-- Name: ambiente_genero_id_ambgen_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE ambiente_genero_id_ambgen_seq OWNED BY ambiente_genero.id_ambgen;


--
-- TOC entry 174 (class 1259 OID 33315)
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
-- TOC entry 2357 (class 0 OID 0)
-- Dependencies: 174
-- Name: ambiente_id_ambiente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE ambiente_id_ambiente_seq OWNED BY ambiente.id_ambiente;


--
-- TOC entry 181 (class 1259 OID 33345)
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
-- TOC entry 180 (class 1259 OID 33343)
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
-- TOC entry 2358 (class 0 OID 0)
-- Dependencies: 180
-- Name: bloco_id_bloco_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE bloco_id_bloco_seq OWNED BY bloco.id_bloco;


--
-- TOC entry 183 (class 1259 OID 33353)
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
-- TOC entry 182 (class 1259 OID 33351)
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
-- TOC entry 2359 (class 0 OID 0)
-- Dependencies: 182
-- Name: categoria_id_categoria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE categoria_id_categoria_seq OWNED BY categoria.id_categoria;


--
-- TOC entry 185 (class 1259 OID 33364)
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
-- TOC entry 184 (class 1259 OID 33362)
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
-- TOC entry 2360 (class 0 OID 0)
-- Dependencies: 184
-- Name: conexao_id_conexao_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE conexao_id_conexao_seq OWNED BY conexao.id_conexao;


--
-- TOC entry 187 (class 1259 OID 33375)
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
-- TOC entry 186 (class 1259 OID 33373)
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
-- TOC entry 2361 (class 0 OID 0)
-- Dependencies: 186
-- Name: empresa_id_empresa_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE empresa_id_empresa_seq OWNED BY empresa.id_empresa;


--
-- TOC entry 189 (class 1259 OID 33386)
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
-- TOC entry 188 (class 1259 OID 33384)
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
-- TOC entry 2362 (class 0 OID 0)
-- Dependencies: 188
-- Name: funcionalidade_id_funcionalidade_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE funcionalidade_id_funcionalidade_seq OWNED BY funcionalidade.id_funcionalidade;


--
-- TOC entry 191 (class 1259 OID 33397)
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
-- TOC entry 190 (class 1259 OID 33395)
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
-- TOC entry 2363 (class 0 OID 0)
-- Dependencies: 190
-- Name: fuso_horario_id_fusohorario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE fuso_horario_id_fusohorario_seq OWNED BY fuso_horario.id_fusohorario;


--
-- TOC entry 193 (class 1259 OID 33408)
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
-- TOC entry 192 (class 1259 OID 33406)
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
-- TOC entry 2364 (class 0 OID 0)
-- Dependencies: 192
-- Name: genero_id_genero_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE genero_id_genero_seq OWNED BY genero.id_genero;


--
-- TOC entry 195 (class 1259 OID 33419)
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
    duracao integer,
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
-- TOC entry 197 (class 1259 OID 33430)
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
-- TOC entry 196 (class 1259 OID 33428)
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
-- TOC entry 2365 (class 0 OID 0)
-- Dependencies: 196
-- Name: midia_ambiente_id_midiaamb_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE midia_ambiente_id_midiaamb_seq OWNED BY midia_ambiente.id_midiaamb;


--
-- TOC entry 198 (class 1259 OID 33436)
-- Name: midia_categoria; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE midia_categoria (
    id_midia bigint NOT NULL,
    id_categoria bigint NOT NULL
);


ALTER TABLE midia_categoria OWNER TO "radio-user";

--
-- TOC entry 200 (class 1259 OID 33441)
-- Name: midia_genero; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE midia_genero (
    id_mediagen bigint NOT NULL,
    id_genero bigint,
    id_midia bigint
);


ALTER TABLE midia_genero OWNER TO "radio-user";

--
-- TOC entry 199 (class 1259 OID 33439)
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
-- TOC entry 2366 (class 0 OID 0)
-- Dependencies: 199
-- Name: midia_genero_id_mediagen_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE midia_genero_id_mediagen_seq OWNED BY midia_genero.id_mediagen;


--
-- TOC entry 194 (class 1259 OID 33417)
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
-- TOC entry 2367 (class 0 OID 0)
-- Dependencies: 194
-- Name: midia_id_midia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE midia_id_midia_seq OWNED BY midia.id_midia;


--
-- TOC entry 202 (class 1259 OID 33449)
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
-- TOC entry 201 (class 1259 OID 33447)
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
-- TOC entry 2368 (class 0 OID 0)
-- Dependencies: 201
-- Name: parametro_id_parametro_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE parametro_id_parametro_seq OWNED BY parametro.id_parametro;


--
-- TOC entry 204 (class 1259 OID 33460)
-- Name: perfil; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE perfil (
    id_perfil bigint NOT NULL,
    nome text NOT NULL
);


ALTER TABLE perfil OWNER TO "radio-user";

--
-- TOC entry 203 (class 1259 OID 33458)
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
-- TOC entry 2369 (class 0 OID 0)
-- Dependencies: 203
-- Name: perfil_id_perfil_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE perfil_id_perfil_seq OWNED BY perfil.id_perfil;


--
-- TOC entry 206 (class 1259 OID 33471)
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
-- TOC entry 205 (class 1259 OID 33469)
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
-- TOC entry 2370 (class 0 OID 0)
-- Dependencies: 205
-- Name: perfil_permissao_id_perfperm_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE perfil_permissao_id_perfperm_seq OWNED BY perfil_permissao.id_perfperm;


--
-- TOC entry 208 (class 1259 OID 33479)
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
-- TOC entry 207 (class 1259 OID 33477)
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
-- TOC entry 2371 (class 0 OID 0)
-- Dependencies: 207
-- Name: permissao_id_permissao_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE permissao_id_permissao_seq OWNED BY permissao.id_permissao;


--
-- TOC entry 210 (class 1259 OID 33490)
-- Name: programacao; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE programacao (
    id_programacao bigint NOT NULL,
    ativo boolean,
    datacriacao timestamp without time zone,
    datainativo timestamp without time zone,
    datetime_fim timestamp without time zone NOT NULL,
    datetime_inicio timestamp without time zone NOT NULL,
    dia character varying(255) NOT NULL,
    hora_fim integer NOT NULL,
    hora_inicio integer NOT NULL,
    minuto_fim integer NOT NULL,
    minuto_inicio integer NOT NULL,
    id_ambiente bigint NOT NULL,
    CONSTRAINT programacao_hora_fim_check CHECK (((hora_fim >= 0) AND (hora_fim <= 23))),
    CONSTRAINT programacao_hora_inicio_check CHECK (((hora_inicio >= 0) AND (hora_inicio <= 23))),
    CONSTRAINT programacao_minuto_fim_check CHECK (((minuto_fim >= 0) AND (minuto_fim <= 59))),
    CONSTRAINT programacao_minuto_inicio_check CHECK (((minuto_inicio >= 0) AND (minuto_inicio <= 59)))
);


ALTER TABLE programacao OWNER TO "radio-user";

--
-- TOC entry 212 (class 1259 OID 33502)
-- Name: programacao_genero; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE programacao_genero (
    id_proggen bigint NOT NULL,
    id_genero bigint,
    id_programacao bigint
);


ALTER TABLE programacao_genero OWNER TO "radio-user";

--
-- TOC entry 211 (class 1259 OID 33500)
-- Name: programacao_genero_id_proggen_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE programacao_genero_id_proggen_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE programacao_genero_id_proggen_seq OWNER TO "radio-user";

--
-- TOC entry 2372 (class 0 OID 0)
-- Dependencies: 211
-- Name: programacao_genero_id_proggen_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE programacao_genero_id_proggen_seq OWNED BY programacao_genero.id_proggen;


--
-- TOC entry 209 (class 1259 OID 33488)
-- Name: programacao_id_programacao_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE programacao_id_programacao_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE programacao_id_programacao_seq OWNER TO "radio-user";

--
-- TOC entry 2373 (class 0 OID 0)
-- Dependencies: 209
-- Name: programacao_id_programacao_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE programacao_id_programacao_seq OWNED BY programacao.id_programacao;


--
-- TOC entry 214 (class 1259 OID 33510)
-- Name: transmissao; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE transmissao (
    id_transmissao bigint NOT NULL,
    datacriacao timestamp without time zone,
    datafinishplay timestamp without time zone,
    dataprevisaoplay timestamp without time zone,
    downloadcompleto boolean,
    duracao integer,
    link character varying(255),
    linkativo boolean,
    ordemplay bigint NOT NULL,
    statusplayback character varying(255),
    id_ambiente bigint,
    id_midia bigint,
    id_programacao bigint
);


ALTER TABLE transmissao OWNER TO "radio-user";

--
-- TOC entry 213 (class 1259 OID 33508)
-- Name: transmissao_id_transmissao_seq; Type: SEQUENCE; Schema: public; Owner: radio-user
--

CREATE SEQUENCE transmissao_id_transmissao_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE transmissao_id_transmissao_seq OWNER TO "radio-user";

--
-- TOC entry 2374 (class 0 OID 0)
-- Dependencies: 213
-- Name: transmissao_id_transmissao_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE transmissao_id_transmissao_seq OWNED BY transmissao.id_transmissao;


--
-- TOC entry 216 (class 1259 OID 33521)
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
-- TOC entry 215 (class 1259 OID 33519)
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
-- TOC entry 2375 (class 0 OID 0)
-- Dependencies: 215
-- Name: usuario_id_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE usuario_id_usuario_seq OWNED BY usuario.id_usuario;


--
-- TOC entry 218 (class 1259 OID 33532)
-- Name: usuario_perfil; Type: TABLE; Schema: public; Owner: radio-user; Tablespace: 
--

CREATE TABLE usuario_perfil (
    id_usuperf bigint NOT NULL,
    id_perfil bigint,
    id_usuario bigint
);


ALTER TABLE usuario_perfil OWNER TO "radio-user";

--
-- TOC entry 217 (class 1259 OID 33530)
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
-- TOC entry 2376 (class 0 OID 0)
-- Dependencies: 217
-- Name: usuario_perfil_id_usuperf_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE usuario_perfil_id_usuperf_seq OWNED BY usuario_perfil.id_usuperf;


--
-- TOC entry 220 (class 1259 OID 33540)
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
-- TOC entry 219 (class 1259 OID 33538)
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
-- TOC entry 2377 (class 0 OID 0)
-- Dependencies: 219
-- Name: usuario_permissao_id_usuperm_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: radio-user
--

ALTER SEQUENCE usuario_permissao_id_usuperm_seq OWNED BY usuario_permissao.id_usuperm;


--
-- TOC entry 2077 (class 2604 OID 33309)
-- Name: id_acesso; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY acesso_usuario ALTER COLUMN id_acesso SET DEFAULT nextval('acesso_usuario_id_acesso_seq'::regclass);


--
-- TOC entry 2078 (class 2604 OID 33320)
-- Name: id_ambiente; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente ALTER COLUMN id_ambiente SET DEFAULT nextval('ambiente_id_ambiente_seq'::regclass);


--
-- TOC entry 2080 (class 2604 OID 33332)
-- Name: id_ambconfig; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente_configuracao ALTER COLUMN id_ambconfig SET DEFAULT nextval('ambiente_configuracao_id_ambconfig_seq'::regclass);


--
-- TOC entry 2081 (class 2604 OID 33340)
-- Name: id_ambgen; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente_genero ALTER COLUMN id_ambgen SET DEFAULT nextval('ambiente_genero_id_ambgen_seq'::regclass);


--
-- TOC entry 2082 (class 2604 OID 33348)
-- Name: id_bloco; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY bloco ALTER COLUMN id_bloco SET DEFAULT nextval('bloco_id_bloco_seq'::regclass);


--
-- TOC entry 2083 (class 2604 OID 33356)
-- Name: id_categoria; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY categoria ALTER COLUMN id_categoria SET DEFAULT nextval('categoria_id_categoria_seq'::regclass);


--
-- TOC entry 2084 (class 2604 OID 33367)
-- Name: id_conexao; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY conexao ALTER COLUMN id_conexao SET DEFAULT nextval('conexao_id_conexao_seq'::regclass);


--
-- TOC entry 2085 (class 2604 OID 33378)
-- Name: id_empresa; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY empresa ALTER COLUMN id_empresa SET DEFAULT nextval('empresa_id_empresa_seq'::regclass);


--
-- TOC entry 2086 (class 2604 OID 33389)
-- Name: id_funcionalidade; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY funcionalidade ALTER COLUMN id_funcionalidade SET DEFAULT nextval('funcionalidade_id_funcionalidade_seq'::regclass);


--
-- TOC entry 2087 (class 2604 OID 33400)
-- Name: id_fusohorario; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY fuso_horario ALTER COLUMN id_fusohorario SET DEFAULT nextval('fuso_horario_id_fusohorario_seq'::regclass);


--
-- TOC entry 2088 (class 2604 OID 33411)
-- Name: id_genero; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY genero ALTER COLUMN id_genero SET DEFAULT nextval('genero_id_genero_seq'::regclass);


--
-- TOC entry 2089 (class 2604 OID 33422)
-- Name: id_midia; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia ALTER COLUMN id_midia SET DEFAULT nextval('midia_id_midia_seq'::regclass);


--
-- TOC entry 2090 (class 2604 OID 33433)
-- Name: id_midiaamb; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_ambiente ALTER COLUMN id_midiaamb SET DEFAULT nextval('midia_ambiente_id_midiaamb_seq'::regclass);


--
-- TOC entry 2091 (class 2604 OID 33444)
-- Name: id_mediagen; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_genero ALTER COLUMN id_mediagen SET DEFAULT nextval('midia_genero_id_mediagen_seq'::regclass);


--
-- TOC entry 2092 (class 2604 OID 33452)
-- Name: id_parametro; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY parametro ALTER COLUMN id_parametro SET DEFAULT nextval('parametro_id_parametro_seq'::regclass);


--
-- TOC entry 2093 (class 2604 OID 33463)
-- Name: id_perfil; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY perfil ALTER COLUMN id_perfil SET DEFAULT nextval('perfil_id_perfil_seq'::regclass);


--
-- TOC entry 2094 (class 2604 OID 33474)
-- Name: id_perfperm; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY perfil_permissao ALTER COLUMN id_perfperm SET DEFAULT nextval('perfil_permissao_id_perfperm_seq'::regclass);


--
-- TOC entry 2095 (class 2604 OID 33482)
-- Name: id_permissao; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY permissao ALTER COLUMN id_permissao SET DEFAULT nextval('permissao_id_permissao_seq'::regclass);


--
-- TOC entry 2096 (class 2604 OID 33493)
-- Name: id_programacao; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY programacao ALTER COLUMN id_programacao SET DEFAULT nextval('programacao_id_programacao_seq'::regclass);


--
-- TOC entry 2101 (class 2604 OID 33505)
-- Name: id_proggen; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY programacao_genero ALTER COLUMN id_proggen SET DEFAULT nextval('programacao_genero_id_proggen_seq'::regclass);


--
-- TOC entry 2102 (class 2604 OID 33513)
-- Name: id_transmissao; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY transmissao ALTER COLUMN id_transmissao SET DEFAULT nextval('transmissao_id_transmissao_seq'::regclass);


--
-- TOC entry 2103 (class 2604 OID 33524)
-- Name: id_usuario; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario ALTER COLUMN id_usuario SET DEFAULT nextval('usuario_id_usuario_seq'::regclass);


--
-- TOC entry 2104 (class 2604 OID 33535)
-- Name: id_usuperf; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_perfil ALTER COLUMN id_usuperf SET DEFAULT nextval('usuario_perfil_id_usuperf_seq'::regclass);


--
-- TOC entry 2105 (class 2604 OID 33543)
-- Name: id_usuperm; Type: DEFAULT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_permissao ALTER COLUMN id_usuperm SET DEFAULT nextval('usuario_permissao_id_usuperm_seq'::regclass);


--
-- TOC entry 2298 (class 0 OID 33306)
-- Dependencies: 173
-- Data for Name: acesso_usuario; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY acesso_usuario (id_acesso, dados, datacriacao, enderecoip, id_usuario) FROM stdin;
\.


--
-- TOC entry 2378 (class 0 OID 0)
-- Dependencies: 172
-- Name: acesso_usuario_id_acesso_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('acesso_usuario_id_acesso_seq', 1, false);


--
-- TOC entry 2300 (class 0 OID 33317)
-- Dependencies: 175
-- Data for Name: ambiente; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY ambiente (id_ambiente, anotacoes, ativo, bairro, cidade, dataalteracao, datacriacao, download, email1, email2, estado, horafimexpediente, horainiexpediente, login, logomarca, logomimetype, logradouro, minutofimexpediente, minutoiniexpediente, nome, numero, opcionais, password, sincronizar, telefone1, telefone2, urlambiente, id_empresa, id_fusohorario, id_usuario) FROM stdin;
\.


--
-- TOC entry 2302 (class 0 OID 33329)
-- Dependencies: 177
-- Data for Name: ambiente_configuracao; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY ambiente_configuracao (id_ambconfig, agendmidia, atendimento, autoplay, avancarretornar, chamfuncionarios, chaminstantanea, chamvariosfuncionarios, chamveiculo, controleblocos, controlecomerciais, controleinstitucionais, controleprogrametes, controlevolumeindividual, dataalteracao, datacriacao, generosbycc, horoscopo, locutorvirtual, menudownloads, nobreak, opcionais, pedidomusical, pedidomusicalvinheta, relatoriosmidia, rodoviarias, selecaogenero, volumechamadas, volumecomerciais, volumegeral, volumemusicas, vozlocucao, id_ambiente, id_usuarioalteracao, id_usuario) FROM stdin;
\.


--
-- TOC entry 2379 (class 0 OID 0)
-- Dependencies: 176
-- Name: ambiente_configuracao_id_ambconfig_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('ambiente_configuracao_id_ambconfig_seq', 1, false);


--
-- TOC entry 2304 (class 0 OID 33337)
-- Dependencies: 179
-- Data for Name: ambiente_genero; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY ambiente_genero (id_ambgen, id_ambiente, id_genero) FROM stdin;
\.


--
-- TOC entry 2380 (class 0 OID 0)
-- Dependencies: 178
-- Name: ambiente_genero_id_ambgen_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('ambiente_genero_id_ambgen_seq', 1, false);


--
-- TOC entry 2381 (class 0 OID 0)
-- Dependencies: 174
-- Name: ambiente_id_ambiente_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('ambiente_id_ambiente_seq', 1, false);


--
-- TOC entry 2306 (class 0 OID 33345)
-- Dependencies: 181
-- Data for Name: bloco; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY bloco (id_bloco, indexhoracerta, indexinstitucionais, indexprogrametes, posicaovinheta, qtdcomerciais, qtdmusicas, id_ambiente) FROM stdin;
\.


--
-- TOC entry 2382 (class 0 OID 0)
-- Dependencies: 180
-- Name: bloco_id_bloco_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('bloco_id_bloco_seq', 1, false);


--
-- TOC entry 2308 (class 0 OID 33353)
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
-- TOC entry 2383 (class 0 OID 0)
-- Dependencies: 182
-- Name: categoria_id_categoria_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('categoria_id_categoria_seq', 8, true);


--
-- TOC entry 2310 (class 0 OID 33364)
-- Dependencies: 185
-- Data for Name: conexao; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY conexao (id_conexao, dados, dataconexao, datadesconexao, enderecoip, id_ambiente) FROM stdin;
\.


--
-- TOC entry 2384 (class 0 OID 0)
-- Dependencies: 184
-- Name: conexao_id_conexao_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('conexao_id_conexao_seq', 1, false);


--
-- TOC entry 2312 (class 0 OID 33375)
-- Dependencies: 187
-- Data for Name: empresa; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY empresa (id_empresa, ativo, cnpj, codigo, dataalteracao, datacriacao, dominio, nomefantasia, razaosocial) FROM stdin;
1	t	28372714000140	Eterion	\N	2015-10-18 15:23:27.506808	www.eterion.com.br	Eterion	Eterion
\.


--
-- TOC entry 2385 (class 0 OID 0)
-- Dependencies: 186
-- Name: empresa_id_empresa_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('empresa_id_empresa_seq', 1, true);


--
-- TOC entry 2314 (class 0 OID 33386)
-- Dependencies: 189
-- Data for Name: funcionalidade; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY funcionalidade (id_funcionalidade, icone, nome, ordem, url) FROM stdin;
1	fa-music	Gêneros	1	/ambientes/%d/generos/view
2	fa-file-audio-o	Vinhetas	2	/ambientes/%d/view-upload-midia/vinheta/
3	fa-headphones	Institucionais	3	/ambientes/%d/view-upload-midia/inst/
4	fa-film	Comerciais	4	/ambientes/%d/view-upload-midia/comercial/
5	fa-bullhorn	Programetes	5	/ambientes/%d/view-upload-midia/programete/
6	fa-bolt	Chamadas<br/>Instantâneas	6	/ambientes/%d/view-upload-midia/chamada_inst/
7	fa-users	Chamadas<br/>Funcionários	7	/ambientes/%d/view-chamada-funcionarios
8	fa-th-large	Blocos	8	/ambientes/%d/blocos/view
9	fa-clock-o	Expediente	9	/ambientes/%d/expedientes/view
10	fa-newspaper-o	Eventos	10	/ambientes/%d/eventos/view
11	fa-list-ol	Programação<br/>Musical	11	/ambientes/%d/programacoes/view
12	fa-briefcase	Configurações	12	/ambientes/%d/configuracoes/view
13	fa-bus	Rodoviária	13	/ambientes/%d/rodoviaria/view
14	fa-files-o	Relatórios	14	/ambientes/%d/relatorios/view
15	fa-floppy-o	Downloads	15	/ambientes/%d/downloads/view
16	fa-unlock-alt	Restrições	16	/ambientes/%d/restricoes/view
17	fa-trademark	Logomarca	17	/ambientes/%d/logomarcas/view
18	fa-play	Simular	18	/ambientes/%d/simulacoes/view
19	fa-microphone	SendVoice	19	/ambientes/%d/sendvoices/view
\.


--
-- TOC entry 2386 (class 0 OID 0)
-- Dependencies: 188
-- Name: funcionalidade_id_funcionalidade_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('funcionalidade_id_funcionalidade_seq', 19, true);


--
-- TOC entry 2316 (class 0 OID 33397)
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
-- TOC entry 2387 (class 0 OID 0)
-- Dependencies: 190
-- Name: fuso_horario_id_fusohorario_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('fuso_horario_id_fusohorario_seq', 4, true);


--
-- TOC entry 2318 (class 0 OID 33408)
-- Dependencies: 193
-- Data for Name: genero; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY genero (id_genero, datacriacao, descricao, genero) FROM stdin;
1	2015-10-18 15:23:27.506808	Top 300 - Lançamentos	Top 300 - Lançamentos
2	2015-10-18 15:23:27.506808	Internacional	Internacional
3	2015-10-18 15:23:27.506808	Pop Rock Nacional	Pop Rock Nacional
4	2015-10-18 15:23:27.506808	Sertanejos	Sertanejos
5	2015-10-18 15:23:27.506808	Sertanejo universitário	Sertanejo universitário
6	2015-10-18 15:23:27.506808	Pagode e Samba	Pagode e Samba
7	2015-10-18 15:23:27.506808	Axé Music	Axé Music
8	2015-10-18 15:23:27.506808	Forró	Forró
9	2015-10-18 15:23:27.506808	Country	Country
10	2015-10-18 15:23:27.506808	Dance Music	Dance Music
11	2015-10-18 15:23:27.506808	Flashback	Flashback
12	2015-10-18 15:23:27.506808	Infantil	Infantil
13	2015-10-18 15:23:27.506808	Bossa Nova	Bossa Nova
14	2015-10-18 15:23:27.506808	Reggae Nacional	Reggae Nacional
15	2015-10-18 15:23:27.506808	Arrocha	Arrocha
16	2015-10-18 15:23:27.506808	Fitness Music	Fitness Music
17	2015-10-18 15:23:27.506808	Rock Internacional	Rock Internacional
18	2015-10-18 15:23:27.506808	Sertanejo Romântico	Sertanejo Romântico
19	2015-10-18 15:23:27.506808	Gospel Internacional	Gospel Internacional
20	2015-10-18 15:23:27.506808	Lounge	Lounge
21	2015-10-18 15:23:27.506808	Flash House	Flash House
22	2015-10-18 15:23:27.506808	Disco	Disco
23	2015-10-18 15:23:27.506808	Instrumental	Instrumental
\.


--
-- TOC entry 2388 (class 0 OID 0)
-- Dependencies: 192
-- Name: genero_id_genero_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('genero_id_genero_seq', 23, true);


--
-- TOC entry 2320 (class 0 OID 33419)
-- Dependencies: 195
-- Data for Name: midia; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY midia (id_midia, album, artist, cached, comment, datacriacao, dataupload, datetag, descricao, duracao, extensao, filehash, filepath, filesize, genre, mimetype, nome, title, valido) FROM stdin;
\.


--
-- TOC entry 2322 (class 0 OID 33430)
-- Dependencies: 197
-- Data for Name: midia_ambiente; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY midia_ambiente (id_midiaamb, dataassociacao, id_ambiente, id_midia) FROM stdin;
\.


--
-- TOC entry 2389 (class 0 OID 0)
-- Dependencies: 196
-- Name: midia_ambiente_id_midiaamb_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('midia_ambiente_id_midiaamb_seq', 1, false);


--
-- TOC entry 2323 (class 0 OID 33436)
-- Dependencies: 198
-- Data for Name: midia_categoria; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY midia_categoria (id_midia, id_categoria) FROM stdin;
\.


--
-- TOC entry 2325 (class 0 OID 33441)
-- Dependencies: 200
-- Data for Name: midia_genero; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY midia_genero (id_mediagen, id_genero, id_midia) FROM stdin;
\.


--
-- TOC entry 2390 (class 0 OID 0)
-- Dependencies: 199
-- Name: midia_genero_id_mediagen_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('midia_genero_id_mediagen_seq', 1, false);


--
-- TOC entry 2391 (class 0 OID 0)
-- Dependencies: 194
-- Name: midia_id_midia_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('midia_id_midia_seq', 1, false);


--
-- TOC entry 2327 (class 0 OID 33449)
-- Dependencies: 202
-- Data for Name: parametro; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY parametro (id_parametro, codigo, descricao, type, valor, id_empresa) FROM stdin;
1	BASE_MIDIA_PATH	Pasta em disco onde serão armazenadas as músicas EX: C:\\Temp\\Musicas	\N	/home/pazin/teste/	1
2	SERVER_REQUEST_PATH	Pasta em disco onde serão armazenadas as músicas EX: C:\\Temp\\Musicas	\N	/home/pazin/teste/	1
\.


--
-- TOC entry 2392 (class 0 OID 0)
-- Dependencies: 201
-- Name: parametro_id_parametro_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('parametro_id_parametro_seq', 2, true);


--
-- TOC entry 2329 (class 0 OID 33460)
-- Dependencies: 204
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
-- TOC entry 2393 (class 0 OID 0)
-- Dependencies: 203
-- Name: perfil_id_perfil_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('perfil_id_perfil_seq', 5, true);


--
-- TOC entry 2331 (class 0 OID 33471)
-- Dependencies: 206
-- Data for Name: perfil_permissao; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY perfil_permissao (id_perfperm, datacriacao, id_perfil, id_permissao) FROM stdin;
1	2015-10-18 15:23:27.506808	1	1
2	2015-10-18 15:23:27.506808	1	2
3	2015-10-18 15:23:27.506808	1	3
4	2015-10-18 15:23:27.506808	1	4
5	2015-10-18 15:23:27.506808	1	5
6	2015-10-18 15:23:27.506808	1	6
7	2015-10-18 15:23:27.506808	1	7
8	2015-10-18 15:23:27.506808	1	8
9	2015-10-18 15:23:27.506808	1	9
10	2015-10-18 15:23:27.506808	1	10
11	2015-10-18 15:23:27.506808	1	11
\.


--
-- TOC entry 2394 (class 0 OID 0)
-- Dependencies: 205
-- Name: perfil_permissao_id_perfperm_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('perfil_permissao_id_perfperm_seq', 11, true);


--
-- TOC entry 2333 (class 0 OID 33479)
-- Dependencies: 208
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
-- TOC entry 2395 (class 0 OID 0)
-- Dependencies: 207
-- Name: permissao_id_permissao_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('permissao_id_permissao_seq', 11, true);


--
-- TOC entry 2335 (class 0 OID 33490)
-- Dependencies: 210
-- Data for Name: programacao; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY programacao (id_programacao, ativo, datacriacao, datainativo, datetime_fim, datetime_inicio, dia, hora_fim, hora_inicio, minuto_fim, minuto_inicio, id_ambiente) FROM stdin;
\.


--
-- TOC entry 2337 (class 0 OID 33502)
-- Dependencies: 212
-- Data for Name: programacao_genero; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY programacao_genero (id_proggen, id_genero, id_programacao) FROM stdin;
\.


--
-- TOC entry 2396 (class 0 OID 0)
-- Dependencies: 211
-- Name: programacao_genero_id_proggen_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('programacao_genero_id_proggen_seq', 1, false);


--
-- TOC entry 2397 (class 0 OID 0)
-- Dependencies: 209
-- Name: programacao_id_programacao_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('programacao_id_programacao_seq', 1, false);


--
-- TOC entry 2339 (class 0 OID 33510)
-- Dependencies: 214
-- Data for Name: transmissao; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY transmissao (id_transmissao, datacriacao, datafinishplay, dataprevisaoplay, downloadcompleto, duracao, link, linkativo, ordemplay, statusplayback, id_ambiente, id_midia, id_programacao) FROM stdin;
\.


--
-- TOC entry 2398 (class 0 OID 0)
-- Dependencies: 213
-- Name: transmissao_id_transmissao_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('transmissao_id_transmissao_seq', 1, false);


--
-- TOC entry 2341 (class 0 OID 33521)
-- Dependencies: 216
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY usuario (id_usuario, ativo, dataalteracao, datacriacao, email, login, nome, password, id_empresa) FROM stdin;
1	t	\N	2015-10-18 15:23:27.506808	pazinfernando@gmail.com	fpazin	Fernando Pazin	$2a$08$jzf4G7i5TxtpYwZwEpsguudbkTgm2vmTmClah6sZkp9FqhGAG5uMC	1
2	t	\N	2015-10-18 15:23:27.506808	george.g.augusto@gmail.com	gaugusto	George Augusto	$2a$08$jzf4G7i5TxtpYwZwEpsguudbkTgm2vmTmClah6sZkp9FqhGAG5uMC	1
\.


--
-- TOC entry 2399 (class 0 OID 0)
-- Dependencies: 215
-- Name: usuario_id_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('usuario_id_usuario_seq', 2, true);


--
-- TOC entry 2343 (class 0 OID 33532)
-- Dependencies: 218
-- Data for Name: usuario_perfil; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY usuario_perfil (id_usuperf, id_perfil, id_usuario) FROM stdin;
1	1	1
2	1	2
\.


--
-- TOC entry 2400 (class 0 OID 0)
-- Dependencies: 217
-- Name: usuario_perfil_id_usuperf_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('usuario_perfil_id_usuperf_seq', 2, true);


--
-- TOC entry 2345 (class 0 OID 33540)
-- Dependencies: 220
-- Data for Name: usuario_permissao; Type: TABLE DATA; Schema: public; Owner: radio-user
--

COPY usuario_permissao (id_usuperm, datacriacao, id_permissao, id_usuario) FROM stdin;
1	2015-10-18 15:23:27.506808	1	\N
\.


--
-- TOC entry 2401 (class 0 OID 0)
-- Dependencies: 219
-- Name: usuario_permissao_id_usuperm_seq; Type: SEQUENCE SET; Schema: public; Owner: radio-user
--

SELECT pg_catalog.setval('usuario_permissao_id_usuperm_seq', 1, true);


--
-- TOC entry 2107 (class 2606 OID 33314)
-- Name: acesso_usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY acesso_usuario
    ADD CONSTRAINT acesso_usuario_pkey PRIMARY KEY (id_acesso);


--
-- TOC entry 2111 (class 2606 OID 33334)
-- Name: ambiente_configuracao_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY ambiente_configuracao
    ADD CONSTRAINT ambiente_configuracao_pkey PRIMARY KEY (id_ambconfig);


--
-- TOC entry 2113 (class 2606 OID 33342)
-- Name: ambiente_genero_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY ambiente_genero
    ADD CONSTRAINT ambiente_genero_pkey PRIMARY KEY (id_ambgen);


--
-- TOC entry 2109 (class 2606 OID 33326)
-- Name: ambiente_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY ambiente
    ADD CONSTRAINT ambiente_pkey PRIMARY KEY (id_ambiente);


--
-- TOC entry 2115 (class 2606 OID 33350)
-- Name: bloco_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY bloco
    ADD CONSTRAINT bloco_pkey PRIMARY KEY (id_bloco);


--
-- TOC entry 2117 (class 2606 OID 33361)
-- Name: categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id_categoria);


--
-- TOC entry 2119 (class 2606 OID 33372)
-- Name: conexao_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY conexao
    ADD CONSTRAINT conexao_pkey PRIMARY KEY (id_conexao);


--
-- TOC entry 2121 (class 2606 OID 33383)
-- Name: empresa_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY empresa
    ADD CONSTRAINT empresa_pkey PRIMARY KEY (id_empresa);


--
-- TOC entry 2123 (class 2606 OID 33394)
-- Name: funcionalidade_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY funcionalidade
    ADD CONSTRAINT funcionalidade_pkey PRIMARY KEY (id_funcionalidade);


--
-- TOC entry 2125 (class 2606 OID 33405)
-- Name: fuso_horario_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY fuso_horario
    ADD CONSTRAINT fuso_horario_pkey PRIMARY KEY (id_fusohorario);


--
-- TOC entry 2127 (class 2606 OID 33416)
-- Name: genero_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY genero
    ADD CONSTRAINT genero_pkey PRIMARY KEY (id_genero);


--
-- TOC entry 2131 (class 2606 OID 33435)
-- Name: midia_ambiente_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY midia_ambiente
    ADD CONSTRAINT midia_ambiente_pkey PRIMARY KEY (id_midiaamb);


--
-- TOC entry 2133 (class 2606 OID 33446)
-- Name: midia_genero_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY midia_genero
    ADD CONSTRAINT midia_genero_pkey PRIMARY KEY (id_mediagen);


--
-- TOC entry 2129 (class 2606 OID 33427)
-- Name: midia_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY midia
    ADD CONSTRAINT midia_pkey PRIMARY KEY (id_midia);


--
-- TOC entry 2135 (class 2606 OID 33457)
-- Name: parametro_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY parametro
    ADD CONSTRAINT parametro_pkey PRIMARY KEY (id_parametro);


--
-- TOC entry 2139 (class 2606 OID 33476)
-- Name: perfil_permissao_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY perfil_permissao
    ADD CONSTRAINT perfil_permissao_pkey PRIMARY KEY (id_perfperm);


--
-- TOC entry 2137 (class 2606 OID 33468)
-- Name: perfil_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY perfil
    ADD CONSTRAINT perfil_pkey PRIMARY KEY (id_perfil);


--
-- TOC entry 2141 (class 2606 OID 33487)
-- Name: permissao_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY permissao
    ADD CONSTRAINT permissao_pkey PRIMARY KEY (id_permissao);


--
-- TOC entry 2145 (class 2606 OID 33507)
-- Name: programacao_genero_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY programacao_genero
    ADD CONSTRAINT programacao_genero_pkey PRIMARY KEY (id_proggen);


--
-- TOC entry 2143 (class 2606 OID 33499)
-- Name: programacao_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY programacao
    ADD CONSTRAINT programacao_pkey PRIMARY KEY (id_programacao);


--
-- TOC entry 2147 (class 2606 OID 33518)
-- Name: transmissao_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY transmissao
    ADD CONSTRAINT transmissao_pkey PRIMARY KEY (id_transmissao);


--
-- TOC entry 2149 (class 2606 OID 33547)
-- Name: uk_pm3f4m4fqv89oeeeac4tbe2f4; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT uk_pm3f4m4fqv89oeeeac4tbe2f4 UNIQUE (login);


--
-- TOC entry 2153 (class 2606 OID 33537)
-- Name: usuario_perfil_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY usuario_perfil
    ADD CONSTRAINT usuario_perfil_pkey PRIMARY KEY (id_usuperf);


--
-- TOC entry 2155 (class 2606 OID 33545)
-- Name: usuario_permissao_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY usuario_permissao
    ADD CONSTRAINT usuario_permissao_pkey PRIMARY KEY (id_usuperm);


--
-- TOC entry 2151 (class 2606 OID 33529)
-- Name: usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: radio-user; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario);


--
-- TOC entry 2170 (class 2606 OID 33618)
-- Name: fk_22epv3x8dnwseyn4awoxklqlq; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_categoria
    ADD CONSTRAINT fk_22epv3x8dnwseyn4awoxklqlq FOREIGN KEY (id_midia) REFERENCES midia(id_midia);


--
-- TOC entry 2168 (class 2606 OID 33608)
-- Name: fk_2ty4qfxrn30sdi6wos7aslh5t; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_ambiente
    ADD CONSTRAINT fk_2ty4qfxrn30sdi6wos7aslh5t FOREIGN KEY (id_midia) REFERENCES midia(id_midia);


--
-- TOC entry 2173 (class 2606 OID 33633)
-- Name: fk_39f4ndp7slw3lecg7s35bs880; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY parametro
    ADD CONSTRAINT fk_39f4ndp7slw3lecg7s35bs880 FOREIGN KEY (id_empresa) REFERENCES empresa(id_empresa);


--
-- TOC entry 2176 (class 2606 OID 33648)
-- Name: fk_3c9s7w987gf54ijwv9iokr0qh; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY permissao
    ADD CONSTRAINT fk_3c9s7w987gf54ijwv9iokr0qh FOREIGN KEY (id_permissaopai) REFERENCES permissao(id_permissao);


--
-- TOC entry 2166 (class 2606 OID 33598)
-- Name: fk_437f1gfc5fchywfr6garfgjfs; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY conexao
    ADD CONSTRAINT fk_437f1gfc5fchywfr6garfgjfs FOREIGN KEY (id_ambiente) REFERENCES ambiente(id_ambiente);


--
-- TOC entry 2159 (class 2606 OID 33563)
-- Name: fk_611okm324dpxd0tyd9k3lb0d1; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente
    ADD CONSTRAINT fk_611okm324dpxd0tyd9k3lb0d1 FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);


--
-- TOC entry 2160 (class 2606 OID 33568)
-- Name: fk_69h6lninhg65xd7od6vscb1tf; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente_configuracao
    ADD CONSTRAINT fk_69h6lninhg65xd7od6vscb1tf FOREIGN KEY (id_ambiente) REFERENCES ambiente(id_ambiente);


--
-- TOC entry 2161 (class 2606 OID 33573)
-- Name: fk_6bb079ml5usas4l2jacvs2dev; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente_configuracao
    ADD CONSTRAINT fk_6bb079ml5usas4l2jacvs2dev FOREIGN KEY (id_usuarioalteracao) REFERENCES usuario(id_usuario);


--
-- TOC entry 2182 (class 2606 OID 33678)
-- Name: fk_9tr6rrvx5tmv30469fylhrn0i; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY transmissao
    ADD CONSTRAINT fk_9tr6rrvx5tmv30469fylhrn0i FOREIGN KEY (id_programacao) REFERENCES programacao(id_programacao);


--
-- TOC entry 2174 (class 2606 OID 33638)
-- Name: fk_aq0t3uo6gxdvjy7woo7j3orlh; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY perfil_permissao
    ADD CONSTRAINT fk_aq0t3uo6gxdvjy7woo7j3orlh FOREIGN KEY (id_perfil) REFERENCES perfil(id_perfil);


--
-- TOC entry 2158 (class 2606 OID 33558)
-- Name: fk_av276cn7k7b8fqlsadbaw39tr; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente
    ADD CONSTRAINT fk_av276cn7k7b8fqlsadbaw39tr FOREIGN KEY (id_fusohorario) REFERENCES fuso_horario(id_fusohorario);


--
-- TOC entry 2183 (class 2606 OID 33683)
-- Name: fk_b0k4dx3ok7qinoeireica0ci5; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT fk_b0k4dx3ok7qinoeireica0ci5 FOREIGN KEY (id_empresa) REFERENCES empresa(id_empresa);


--
-- TOC entry 2169 (class 2606 OID 33613)
-- Name: fk_brudn4ldkpjmoaey8pe21datg; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_categoria
    ADD CONSTRAINT fk_brudn4ldkpjmoaey8pe21datg FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria);


--
-- TOC entry 2177 (class 2606 OID 33653)
-- Name: fk_dblh15xjfuv4mf3g1ksd1i7tj; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY programacao
    ADD CONSTRAINT fk_dblh15xjfuv4mf3g1ksd1i7tj FOREIGN KEY (id_ambiente) REFERENCES ambiente(id_ambiente);


--
-- TOC entry 2163 (class 2606 OID 33583)
-- Name: fk_dhsed93pxsxvk88x2ec9hgt15; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente_genero
    ADD CONSTRAINT fk_dhsed93pxsxvk88x2ec9hgt15 FOREIGN KEY (id_ambiente) REFERENCES ambiente(id_ambiente);


--
-- TOC entry 2175 (class 2606 OID 33643)
-- Name: fk_dwru3uterlja2wy88f0pikxbv; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY perfil_permissao
    ADD CONSTRAINT fk_dwru3uterlja2wy88f0pikxbv FOREIGN KEY (id_permissao) REFERENCES permissao(id_permissao);


--
-- TOC entry 2187 (class 2606 OID 33703)
-- Name: fk_fcoumipohnx3ye4dbc490itr7; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_permissao
    ADD CONSTRAINT fk_fcoumipohnx3ye4dbc490itr7 FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);


--
-- TOC entry 2184 (class 2606 OID 33688)
-- Name: fk_fp4srv5mbiqyc9cfb7dvucbiq; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_perfil
    ADD CONSTRAINT fk_fp4srv5mbiqyc9cfb7dvucbiq FOREIGN KEY (id_perfil) REFERENCES perfil(id_perfil);


--
-- TOC entry 2185 (class 2606 OID 33693)
-- Name: fk_g6mv5dao2a6xlg6c0wcx1im4o; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_perfil
    ADD CONSTRAINT fk_g6mv5dao2a6xlg6c0wcx1im4o FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);


--
-- TOC entry 2179 (class 2606 OID 33658)
-- Name: fk_gybiegdes4kvqelvt6llayh3k; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY programacao_genero
    ADD CONSTRAINT fk_gybiegdes4kvqelvt6llayh3k FOREIGN KEY (id_genero) REFERENCES genero(id_genero);


--
-- TOC entry 2167 (class 2606 OID 33603)
-- Name: fk_iatvwjgc79bfpq3jp2savgfh; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_ambiente
    ADD CONSTRAINT fk_iatvwjgc79bfpq3jp2savgfh FOREIGN KEY (id_ambiente) REFERENCES ambiente(id_ambiente);


--
-- TOC entry 2156 (class 2606 OID 33548)
-- Name: fk_jr9i3w2v1vxtkwg6vo9hksqax; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY acesso_usuario
    ADD CONSTRAINT fk_jr9i3w2v1vxtkwg6vo9hksqax FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);


--
-- TOC entry 2157 (class 2606 OID 33553)
-- Name: fk_lb6vjulov4165n2up8ks5nvu1; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente
    ADD CONSTRAINT fk_lb6vjulov4165n2up8ks5nvu1 FOREIGN KEY (id_empresa) REFERENCES empresa(id_empresa);


--
-- TOC entry 2178 (class 2606 OID 33663)
-- Name: fk_li6ogicm8cfxi9e2iajntnamp; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY programacao_genero
    ADD CONSTRAINT fk_li6ogicm8cfxi9e2iajntnamp FOREIGN KEY (id_programacao) REFERENCES programacao(id_programacao);


--
-- TOC entry 2165 (class 2606 OID 33593)
-- Name: fk_lniwf79yvm66g1vyrb8iouu3w; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY bloco
    ADD CONSTRAINT fk_lniwf79yvm66g1vyrb8iouu3w FOREIGN KEY (id_ambiente) REFERENCES ambiente(id_ambiente);


--
-- TOC entry 2172 (class 2606 OID 33628)
-- Name: fk_mk1oe4srv9i0fqwbv9vxjw67c; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_genero
    ADD CONSTRAINT fk_mk1oe4srv9i0fqwbv9vxjw67c FOREIGN KEY (id_midia) REFERENCES midia(id_midia);


--
-- TOC entry 2164 (class 2606 OID 33588)
-- Name: fk_n5evd4n1wy3bc82h7tp5r2kt7; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente_genero
    ADD CONSTRAINT fk_n5evd4n1wy3bc82h7tp5r2kt7 FOREIGN KEY (id_genero) REFERENCES genero(id_genero);


--
-- TOC entry 2171 (class 2606 OID 33623)
-- Name: fk_ob42vsibs7gt9qrwmrg3gwax3; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY midia_genero
    ADD CONSTRAINT fk_ob42vsibs7gt9qrwmrg3gwax3 FOREIGN KEY (id_genero) REFERENCES genero(id_genero);


--
-- TOC entry 2186 (class 2606 OID 33698)
-- Name: fk_q3um0dky0pwbo14outjmpexba; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY usuario_permissao
    ADD CONSTRAINT fk_q3um0dky0pwbo14outjmpexba FOREIGN KEY (id_permissao) REFERENCES permissao(id_permissao);


--
-- TOC entry 2180 (class 2606 OID 33668)
-- Name: fk_sk1i5a1hlflwgw8f2967hwc77; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY transmissao
    ADD CONSTRAINT fk_sk1i5a1hlflwgw8f2967hwc77 FOREIGN KEY (id_ambiente) REFERENCES ambiente(id_ambiente);


--
-- TOC entry 2181 (class 2606 OID 33673)
-- Name: fk_t4drc4aun6r3d88eokfs7dntg; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY transmissao
    ADD CONSTRAINT fk_t4drc4aun6r3d88eokfs7dntg FOREIGN KEY (id_midia) REFERENCES midia(id_midia);


--
-- TOC entry 2162 (class 2606 OID 33578)
-- Name: fk_texupuhsyc125mpvqfg2elgqw; Type: FK CONSTRAINT; Schema: public; Owner: radio-user
--

ALTER TABLE ONLY ambiente_configuracao
    ADD CONSTRAINT fk_texupuhsyc125mpvqfg2elgqw FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);


--
-- TOC entry 2352 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-10-18 15:30:08 BRST

--
-- PostgreSQL database dump complete
--

