CREATE TABLE Users (
  user_id uuid PRIMARY KEY,
  full_name varchar(100) NOT NULL,
  nick_name varchar(100) NOT NULL,
  avatar bytea,
  email varchar(100) UNIQUE NOT NULL,
  password varchar NOT NULL,
  is_admin boolean default false
);

CREATE TABLE Friends (
  user_id uuid,
  friend_id uuid,
    PRIMARY KEY (user_id,friend_id)
);

CREATE TABLE Conversation (
  conversation_id uuid PRIMARY KEY,
  is_group bool NOT NULL
);

CREATE TABLE Conversation_Members (
  conversation_id uuid,
  user_id uuid,
  has_read bool NOT NULL DEFAULT false,
  PRIMARY KEY (user_id,conversation_id)
);

CREATE TABLE Groups (
  group_id uuid PRIMARY KEY,
  group_name varchar(100) NOT NULL,
  group_avatar bytea,
  owner uuid NOT NULL
);

CREATE TABLE Message (
  message_id bigserial PRIMARY KEY,
  message_file bytea,
  message_content text,
  message_image bytea,
  message_date timestamp NOT NULL DEFAULT (now()),
  sender_id uuid NOT NULL,
  conversation_id uuid
);

CREATE TABLE Post (
  post_id bigserial PRIMARY KEY,
  post_image bytea,
  post_text text,
  post_date timestamp NOT NULL DEFAULT (now()),
  no_like int DEFAULT 0,
  user_id uuid NOT NULL
);

CREATE TABLE Post_Reaction (
  user_id uuid,
  post_id bigserial,
  PRIMARY KEY (user_id,post_id)
);

CREATE TABLE Report (
  report_id bigserial PRIMARY KEY,
  report_reason varchar(200) NOT NULL,
  user_id uuid NOT NULL,
  post_id bigserial NOT NULL
);

CREATE TABLE Deleted_Post (
  delete_reason varchar(200) NOT NULL,
  post_id bigserial NOT NULL PRIMARY KEY,
  admin_id uuid NOT NULL
);

CREATE TABLE Banned_User (
  ban_reason varchar(200) NOT NULL,
  duration int NOT NULL,
  user_id uuid NOT NULL,
  admin_id uuid NOT NULL,
  band_date timestamp,
  PRIMARY KEY (user_id,band_date)
);

ALTER TABLE Friends ADD FOREIGN KEY (user_id) REFERENCES Users (user_id);

ALTER TABLE Friends ADD FOREIGN KEY (friend_id) REFERENCES Users (user_id);

ALTER TABLE Conversation_Members ADD FOREIGN KEY (conversation_id) REFERENCES Conversation (conversation_id);

ALTER TABLE Conversation_Members ADD FOREIGN KEY (user_id) REFERENCES Users (user_id);

ALTER TABLE Groups ADD FOREIGN KEY (group_id) REFERENCES Conversation (conversation_id);

ALTER TABLE Groups ADD FOREIGN KEY (owner) REFERENCES Users (user_id);

ALTER TABLE Message ADD FOREIGN KEY (sender_id) REFERENCES Users (user_id);

ALTER TABLE Message ADD FOREIGN KEY (conversation_id) REFERENCES Conversation (conversation_id);

ALTER TABLE Post ADD FOREIGN KEY (user_id) REFERENCES Users (user_id);

ALTER TABLE Post_Reaction ADD FOREIGN KEY (user_id) REFERENCES Users (user_id);

ALTER TABLE Post_Reaction ADD FOREIGN KEY (post_id) REFERENCES Post (post_id);

ALTER TABLE Report ADD FOREIGN KEY (user_id) REFERENCES Users (user_id);

ALTER TABLE Report ADD FOREIGN KEY (post_id) REFERENCES Post (post_id);

ALTER TABLE Deleted_Post ADD FOREIGN KEY (post_id) REFERENCES Post (post_id);

ALTER TABLE Deleted_Post ADD FOREIGN KEY (admin_id) REFERENCES Users (user_id);

ALTER TABLE Banned_User ADD FOREIGN KEY (user_id) REFERENCES Users (user_id);

ALTER TABLE Banned_User ADD FOREIGN KEY (admin_id) REFERENCES Users (user_id);
