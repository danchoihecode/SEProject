--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3
-- Dumped by pg_dump version 16.3

-- Started on 2024-07-22 09:28:57

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 17475)
-- Name: banned_user; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.banned_user (
    ban_reason character varying(200) NOT NULL,
    duration integer NOT NULL,
    user_id uuid NOT NULL,
    admin_id uuid NOT NULL,
    band_date timestamp without time zone NOT NULL
);


--
-- TOC entry 216 (class 1259 OID 17478)
-- Name: conversation; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.conversation (
    conversation_id uuid NOT NULL,
    is_group boolean NOT NULL
);


--
-- TOC entry 217 (class 1259 OID 17481)
-- Name: conversation_members; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.conversation_members (
    conversation_id uuid NOT NULL,
    user_id uuid NOT NULL,
    has_read boolean DEFAULT false NOT NULL
);


--
-- TOC entry 231 (class 1259 OID 17652)
-- Name: deleted_post; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.deleted_post (
    post_id bigint NOT NULL,
    delete_reason character varying(200) NOT NULL,
    post_text text,
    admin_id uuid NOT NULL
);


--
-- TOC entry 230 (class 1259 OID 17651)
-- Name: deleted_post_post_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.deleted_post_post_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 4896 (class 0 OID 0)
-- Dependencies: 230
-- Name: deleted_post_post_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.deleted_post_post_id_seq OWNED BY public.deleted_post.post_id;


--
-- TOC entry 218 (class 1259 OID 17489)
-- Name: friends; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.friends (
    user_id uuid NOT NULL,
    friend_id uuid NOT NULL,
    accepted boolean DEFAULT false
);


--
-- TOC entry 219 (class 1259 OID 17492)
-- Name: groups; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.groups (
    group_id uuid NOT NULL,
    group_name character varying(100) NOT NULL,
    group_avatar bytea,
    owner uuid NOT NULL
);


--
-- TOC entry 220 (class 1259 OID 17497)
-- Name: message; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.message (
    message_id bigint NOT NULL,
    message_file bytea,
    message_content text,
    message_image bytea,
    message_date timestamp without time zone DEFAULT now() NOT NULL,
    sender_id uuid NOT NULL,
    conversation_id uuid
);


--
-- TOC entry 221 (class 1259 OID 17503)
-- Name: message_message_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.message_message_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 4897 (class 0 OID 0)
-- Dependencies: 221
-- Name: message_message_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.message_message_id_seq OWNED BY public.message.message_id;


--
-- TOC entry 222 (class 1259 OID 17504)
-- Name: post; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.post (
    post_id bigint NOT NULL,
    post_image bytea,
    post_text text,
    post_date timestamp without time zone DEFAULT now() NOT NULL,
    no_like integer DEFAULT 0,
    user_id uuid NOT NULL
);


--
-- TOC entry 223 (class 1259 OID 17511)
-- Name: post_post_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.post_post_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 4898 (class 0 OID 0)
-- Dependencies: 223
-- Name: post_post_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.post_post_id_seq OWNED BY public.post.post_id;


--
-- TOC entry 224 (class 1259 OID 17512)
-- Name: post_reaction; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.post_reaction (
    user_id uuid NOT NULL,
    post_id bigint NOT NULL
);


--
-- TOC entry 225 (class 1259 OID 17515)
-- Name: post_reaction_post_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.post_reaction_post_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 4899 (class 0 OID 0)
-- Dependencies: 225
-- Name: post_reaction_post_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.post_reaction_post_id_seq OWNED BY public.post_reaction.post_id;


--
-- TOC entry 226 (class 1259 OID 17516)
-- Name: report; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.report (
    report_id bigint NOT NULL,
    report_reason character varying(200) NOT NULL,
    user_id uuid NOT NULL,
    post_id bigint NOT NULL
);


--
-- TOC entry 227 (class 1259 OID 17519)
-- Name: report_post_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.report_post_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 4900 (class 0 OID 0)
-- Dependencies: 227
-- Name: report_post_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.report_post_id_seq OWNED BY public.report.post_id;


--
-- TOC entry 228 (class 1259 OID 17520)
-- Name: report_report_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.report_report_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 4901 (class 0 OID 0)
-- Dependencies: 228
-- Name: report_report_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.report_report_id_seq OWNED BY public.report.report_id;


--
-- TOC entry 229 (class 1259 OID 17521)
-- Name: users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users (
    user_id uuid NOT NULL,
    full_name character varying(100) NOT NULL,
    nick_name character varying(100) NOT NULL,
    avatar bytea,
    email character varying(100) NOT NULL,
    password character varying NOT NULL,
    is_admin boolean DEFAULT false
);


--
-- TOC entry 4690 (class 2604 OID 17655)
-- Name: deleted_post post_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.deleted_post ALTER COLUMN post_id SET DEFAULT nextval('public.deleted_post_post_id_seq'::regclass);


--
-- TOC entry 4681 (class 2604 OID 17528)
-- Name: message message_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.message ALTER COLUMN message_id SET DEFAULT nextval('public.message_message_id_seq'::regclass);


--
-- TOC entry 4683 (class 2604 OID 17529)
-- Name: post post_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.post ALTER COLUMN post_id SET DEFAULT nextval('public.post_post_id_seq'::regclass);


--
-- TOC entry 4686 (class 2604 OID 17530)
-- Name: post_reaction post_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.post_reaction ALTER COLUMN post_id SET DEFAULT nextval('public.post_reaction_post_id_seq'::regclass);


--
-- TOC entry 4687 (class 2604 OID 17531)
-- Name: report report_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.report ALTER COLUMN report_id SET DEFAULT nextval('public.report_report_id_seq'::regclass);


--
-- TOC entry 4688 (class 2604 OID 17532)
-- Name: report post_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.report ALTER COLUMN post_id SET DEFAULT nextval('public.report_post_id_seq'::regclass);


--
-- TOC entry 4874 (class 0 OID 17475)
-- Dependencies: 215
-- Data for Name: banned_user; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.banned_user (ban_reason, duration, user_id, admin_id, band_date) FROM stdin;
Ngon tu khong phu hop	12	0dbfad3f-a936-4fcd-850a-5e409ace2457	076ee415-1d98-4e8c-a8b3-09d2a66e6efb	2024-08-03 02:16:07.3739
\.


--
-- TOC entry 4875 (class 0 OID 17478)
-- Dependencies: 216
-- Data for Name: conversation; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.conversation (conversation_id, is_group) FROM stdin;
b2c1a4d2-6f58-43c7-a6e5-9e817ec5a4a9	t
42bcc1d9-8dd2-415c-930b-21df76715b96	f
1ffd52c0-0384-4b7c-8f13-e4b2c644a672	t
aaa655b3-fe61-44ad-89ed-d012bdb93cdb	f
5138bc00-26a7-45a3-b2f5-4dc35438cb34	f
9548d820-9a88-4654-bf5a-e8dfdf2a7c59	f
\.


--
-- TOC entry 4876 (class 0 OID 17481)
-- Dependencies: 217
-- Data for Name: conversation_members; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.conversation_members (conversation_id, user_id, has_read) FROM stdin;
b2c1a4d2-6f58-43c7-a6e5-9e817ec5a4a9	0dbfad3f-a936-4fcd-850a-5e409ace2457	t
42bcc1d9-8dd2-415c-930b-21df76715b96	0e1812a4-3031-452f-aebe-aafd4a001b5c	t
42bcc1d9-8dd2-415c-930b-21df76715b96	0dbfad3f-a936-4fcd-850a-5e409ace2457	t
1ffd52c0-0384-4b7c-8f13-e4b2c644a672	0dbfad3f-a936-4fcd-850a-5e409ace2457	f
aaa655b3-fe61-44ad-89ed-d012bdb93cdb	0dbfad3f-a936-4fcd-850a-5e409ace2457	t
5138bc00-26a7-45a3-b2f5-4dc35438cb34	0dbfad3f-a936-4fcd-850a-5e409ace2457	t
9548d820-9a88-4654-bf5a-e8dfdf2a7c59	0dbfad3f-a936-4fcd-850a-5e409ace2457	t
1ffd52c0-0384-4b7c-8f13-e4b2c644a672	0e1812a4-3031-452f-aebe-aafd4a001b5c	f
b2c1a4d2-6f58-43c7-a6e5-9e817ec5a4a9	0e1812a4-3031-452f-aebe-aafd4a001b5c	f
b2c1a4d2-6f58-43c7-a6e5-9e817ec5a4a9	076ee415-1d98-4e8c-a8b3-09d2a66e6efb	f
\.


--
-- TOC entry 4890 (class 0 OID 17652)
-- Dependencies: 231
-- Data for Name: deleted_post; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.deleted_post (post_id, delete_reason, post_text, admin_id) FROM stdin;
6	Ngon tu khong phu hop	Haha	076ee415-1d98-4e8c-a8b3-09d2a66e6efb
\.


--
-- TOC entry 4877 (class 0 OID 17489)
-- Dependencies: 218
-- Data for Name: friends; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.friends (user_id, friend_id, accepted) FROM stdin;
0dbfad3f-a936-4fcd-850a-5e409ace2457	0e1812a4-3031-452f-aebe-aafd4a001b5c	t
0e1812a4-3031-452f-aebe-aafd4a001b5c	0dbfad3f-a936-4fcd-850a-5e409ace2457	t
\.


--
-- TOC entry 4878 (class 0 OID 17492)
-- Dependencies: 219
-- Data for Name: groups; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.groups (group_id, group_name, group_avatar, owner) FROM stdin;
1ffd52c0-0384-4b7c-8f13-e4b2c644a672	Lop A1 K22	\N	0dbfad3f-a936-4fcd-850a-5e409ace2457
b2c1a4d2-6f58-43c7-a6e5-9e817ec5a4a9	ICT 01	\N	0dbfad3f-a936-4fcd-850a-5e409ace2457
\.


--
-- TOC entry 4879 (class 0 OID 17497)
-- Dependencies: 220
-- Data for Name: message; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.message (message_id, message_file, message_content, message_image, message_date, sender_id, conversation_id) FROM stdin;
8	\N	hello	\N	2024-06-28 10:10:22.621958	0dbfad3f-a936-4fcd-850a-5e409ace2457	b2c1a4d2-6f58-43c7-a6e5-9e817ec5a4a9
9	\N	hello	\N	2024-06-28 11:33:41.000481	0e1812a4-3031-452f-aebe-aafd4a001b5c	b2c1a4d2-6f58-43c7-a6e5-9e817ec5a4a9
10	\N	i am from vn	\N	2024-06-28 12:20:03.547594	0dbfad3f-a936-4fcd-850a-5e409ace2457	b2c1a4d2-6f58-43c7-a6e5-9e817ec5a4a9
11	\N	hello i am hai	\N	2024-06-28 12:27:03.366493	0dbfad3f-a936-4fcd-850a-5e409ace2457	b2c1a4d2-6f58-43c7-a6e5-9e817ec5a4a9
12	\N	may la ai	\N	2024-06-29 04:55:33.343452	0dbfad3f-a936-4fcd-850a-5e409ace2457	b2c1a4d2-6f58-43c7-a6e5-9e817ec5a4a9
13	\N	hello may	\N	2024-06-29 04:56:43.088381	0e1812a4-3031-452f-aebe-aafd4a001b5c	42bcc1d9-8dd2-415c-930b-21df76715b96
14	\N	hello cai gi	\N	2024-06-29 05:06:05.894406	0dbfad3f-a936-4fcd-850a-5e409ace2457	42bcc1d9-8dd2-415c-930b-21df76715b96
15	\N	hello	\N	2024-06-29 05:06:12.913214	0dbfad3f-a936-4fcd-850a-5e409ace2457	1ffd52c0-0384-4b7c-8f13-e4b2c644a672
16	\N	hello i am hai	\N	2024-07-21 07:57:23.381827	0dbfad3f-a936-4fcd-850a-5e409ace2457	42bcc1d9-8dd2-415c-930b-21df76715b96
\.


--
-- TOC entry 4881 (class 0 OID 17504)
-- Dependencies: 222
-- Data for Name: post; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.post (post_id, post_image, post_text, post_date, no_like, user_id) FROM stdin;
1	\N	Helo helo	2024-07-21 20:40:07.581768	0	0dbfad3f-a936-4fcd-850a-5e409ace2457
2	\N	Ngon tu khong phu hop	2024-07-22 08:13:56.173781	0	0dbfad3f-a936-4fcd-850a-5e409ace2457
\.


--
-- TOC entry 4883 (class 0 OID 17512)
-- Dependencies: 224
-- Data for Name: post_reaction; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.post_reaction (user_id, post_id) FROM stdin;
\.


--
-- TOC entry 4885 (class 0 OID 17516)
-- Dependencies: 226
-- Data for Name: report; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.report (report_id, report_reason, user_id, post_id) FROM stdin;
8	inappropriate post	0dbfad3f-a936-4fcd-850a-5e409ace2457	1
14	Ngon tu khong on	0dbfad3f-a936-4fcd-850a-5e409ace2457	2
15	Ngon tu khong tot	0dbfad3f-a936-4fcd-850a-5e409ace2457	2
\.


--
-- TOC entry 4888 (class 0 OID 17521)
-- Dependencies: 229
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.users (user_id, full_name, nick_name, avatar, email, password, is_admin) FROM stdin;
0dbfad3f-a936-4fcd-850a-5e409ace2457	Dương Hoàng Hải	user2	\N	an@gmail.com	$2a$10$M4AE8WV57M/OPZXWgR2dh.XOU/vD1PBMUV7uEoZohXeu0PdQvAbKi	f
0e1812a4-3031-452f-aebe-aafd4a001b5c	Lê Minh	user3	\N	ad@gmail.com	$2a$10$tyeFEC3s6FJq/nxtEYS5pO8jk4JRPvH5liFdsVA/VszYp/3V3a2H.	f
076ee415-1d98-4e8c-a8b3-09d2a66e6efb	Trần Hoàng	Trần Hoàng	\N	admin@gmail.com	$2a$10$5kiSdSFxyLNBvRDbqOVKZ.bLpyeDQA1LwVGIgClSkhXx9eDGPRsyi	t
\.


--
-- TOC entry 4902 (class 0 OID 0)
-- Dependencies: 230
-- Name: deleted_post_post_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.deleted_post_post_id_seq', 6, true);


--
-- TOC entry 4903 (class 0 OID 0)
-- Dependencies: 221
-- Name: message_message_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.message_message_id_seq', 16, true);


--
-- TOC entry 4904 (class 0 OID 0)
-- Dependencies: 223
-- Name: post_post_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.post_post_id_seq', 5, true);


--
-- TOC entry 4905 (class 0 OID 0)
-- Dependencies: 225
-- Name: post_reaction_post_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.post_reaction_post_id_seq', 1, false);


--
-- TOC entry 4906 (class 0 OID 0)
-- Dependencies: 227
-- Name: report_post_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.report_post_id_seq', 1, true);


--
-- TOC entry 4907 (class 0 OID 0)
-- Dependencies: 228
-- Name: report_report_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.report_report_id_seq', 15, true);


--
-- TOC entry 4692 (class 2606 OID 17534)
-- Name: banned_user banned_user_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.banned_user
    ADD CONSTRAINT banned_user_pkey PRIMARY KEY (user_id, band_date);


--
-- TOC entry 4696 (class 2606 OID 17536)
-- Name: conversation_members conversation_members_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.conversation_members
    ADD CONSTRAINT conversation_members_pkey PRIMARY KEY (user_id, conversation_id);


--
-- TOC entry 4694 (class 2606 OID 17538)
-- Name: conversation conversation_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.conversation
    ADD CONSTRAINT conversation_pkey PRIMARY KEY (conversation_id);


--
-- TOC entry 4714 (class 2606 OID 17659)
-- Name: deleted_post deleted_post_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.deleted_post
    ADD CONSTRAINT deleted_post_pkey PRIMARY KEY (post_id);


--
-- TOC entry 4698 (class 2606 OID 17542)
-- Name: friends friends_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.friends
    ADD CONSTRAINT friends_pkey PRIMARY KEY (user_id, friend_id);


--
-- TOC entry 4700 (class 2606 OID 17544)
-- Name: groups groups_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (group_id);


--
-- TOC entry 4702 (class 2606 OID 17546)
-- Name: message message_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_pkey PRIMARY KEY (message_id);


--
-- TOC entry 4704 (class 2606 OID 17548)
-- Name: post post_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT post_pkey PRIMARY KEY (post_id);


--
-- TOC entry 4706 (class 2606 OID 17550)
-- Name: post_reaction post_reaction_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.post_reaction
    ADD CONSTRAINT post_reaction_pkey PRIMARY KEY (user_id, post_id);


--
-- TOC entry 4708 (class 2606 OID 17552)
-- Name: report report_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.report
    ADD CONSTRAINT report_pkey PRIMARY KEY (report_id);


--
-- TOC entry 4710 (class 2606 OID 17554)
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- TOC entry 4712 (class 2606 OID 17556)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- TOC entry 4715 (class 2606 OID 17557)
-- Name: banned_user banned_user_admin_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.banned_user
    ADD CONSTRAINT banned_user_admin_id_fkey FOREIGN KEY (admin_id) REFERENCES public.users(user_id);


--
-- TOC entry 4716 (class 2606 OID 17562)
-- Name: banned_user banned_user_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.banned_user
    ADD CONSTRAINT banned_user_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- TOC entry 4717 (class 2606 OID 17567)
-- Name: conversation_members conversation_members_conversation_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.conversation_members
    ADD CONSTRAINT conversation_members_conversation_id_fkey FOREIGN KEY (conversation_id) REFERENCES public.conversation(conversation_id);


--
-- TOC entry 4718 (class 2606 OID 17572)
-- Name: conversation_members conversation_members_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.conversation_members
    ADD CONSTRAINT conversation_members_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- TOC entry 4730 (class 2606 OID 17660)
-- Name: deleted_post fkjqcdmtqasn0xc2sd62x9ys627; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.deleted_post
    ADD CONSTRAINT fkjqcdmtqasn0xc2sd62x9ys627 FOREIGN KEY (admin_id) REFERENCES public.users(user_id);


--
-- TOC entry 4719 (class 2606 OID 17587)
-- Name: friends friends_friend_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.friends
    ADD CONSTRAINT friends_friend_id_fkey FOREIGN KEY (friend_id) REFERENCES public.users(user_id);


--
-- TOC entry 4720 (class 2606 OID 17592)
-- Name: friends friends_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.friends
    ADD CONSTRAINT friends_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- TOC entry 4721 (class 2606 OID 17597)
-- Name: groups groups_group_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_group_id_fkey FOREIGN KEY (group_id) REFERENCES public.conversation(conversation_id);


--
-- TOC entry 4722 (class 2606 OID 17602)
-- Name: groups groups_owner_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_owner_fkey FOREIGN KEY (owner) REFERENCES public.users(user_id);


--
-- TOC entry 4723 (class 2606 OID 17607)
-- Name: message message_conversation_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_conversation_id_fkey FOREIGN KEY (conversation_id) REFERENCES public.conversation(conversation_id);


--
-- TOC entry 4724 (class 2606 OID 17612)
-- Name: message message_sender_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_sender_id_fkey FOREIGN KEY (sender_id) REFERENCES public.users(user_id);


--
-- TOC entry 4726 (class 2606 OID 17617)
-- Name: post_reaction post_reaction_post_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.post_reaction
    ADD CONSTRAINT post_reaction_post_id_fkey FOREIGN KEY (post_id) REFERENCES public.post(post_id);


--
-- TOC entry 4727 (class 2606 OID 17622)
-- Name: post_reaction post_reaction_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.post_reaction
    ADD CONSTRAINT post_reaction_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- TOC entry 4725 (class 2606 OID 17627)
-- Name: post post_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT post_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- TOC entry 4728 (class 2606 OID 17632)
-- Name: report report_post_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.report
    ADD CONSTRAINT report_post_id_fkey FOREIGN KEY (post_id) REFERENCES public.post(post_id);


--
-- TOC entry 4729 (class 2606 OID 17637)
-- Name: report report_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.report
    ADD CONSTRAINT report_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


-- Completed on 2024-07-22 09:28:57

--
-- PostgreSQL database dump complete
--

