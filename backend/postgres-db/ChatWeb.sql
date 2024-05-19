CREATE TABLE Users (
  UserID int PRIMARY KEY,
  UserFullName varchar(100) NOT NULL,
  UserNickName varchar(100) NOT NULL,
  UserAvatar bytea,
  UserEmail varchar(100) UNIQUE NOT NULL,
  UserPassword varchar NOT NULL
);

CREATE TABLE Friends (
  UserID int,
  FriendID int,
    PRIMARY KEY (UserID,FriendID)
);

CREATE TABLE Conversation (
  ConversationID int PRIMARY KEY,
  IsGroup bool NOT NULL
);

CREATE TABLE ConversationMembers (
  ConversationID int,
  UserID int,
  HasRead bool NOT NULL DEFAULT false,
  PRIMARY KEY (UserID,ConversationID)
);

CREATE TABLE Groups (
  GroupID int PRIMARY KEY,
  GroupName varchar(100) NOT NULL,
  GroupAvatar bytea,
  Owner int NOT NULL
);

CREATE TABLE Message (
  MessageID int PRIMARY KEY,
  MessageFile bytea,
  MessageContent text,
  MessageImage bytea,
  MessageDate timestamp NOT NULL DEFAULT (now()),
  SenderID int NOT NULL,
  ConversationID int
);

CREATE TABLE Post (
  PostID int PRIMARY KEY,
  PostImage bytea,
  PostText text,
  PostDate timestamp NOT NULL DEFAULT (now()),
  NoLike int DEFAULT 0,
  UserID int NOT NULL
);

CREATE TABLE PostReaction (
  UserID int,
  PostID int,
  PRIMARY KEY (UserID,PostID)
);

CREATE TABLE Report (
  ReportID int PRIMARY KEY,
  ReportReason varchar(200) NOT NULL,
  UserID int NOT NULL,
  PostID int NOT NULL
);

CREATE TABLE Admin (
  AdminID int PRIMARY KEY,
  AdminPassword varchar NOT NULL,
  AdminEmail varchar(100) UNIQUE NOT NULL
);

CREATE TABLE DeletedPost (
  DeleteReason varchar(200) NOT NULL,
  PostID int NOT NULL PRIMARY KEY,
  AdminID int NOT NULL
);

CREATE TABLE BannedUser (
  BanReason varchar(200) NOT NULL,
  Duration int NOT NULL,
  UserID int NOT NULL,
  AdminID int NOT NULL,
  BanDate timestamp,
  PRIMARY KEY (UserID,BanDate)
);

ALTER TABLE Friends ADD FOREIGN KEY (UserID) REFERENCES Users (UserID);

ALTER TABLE Friends ADD FOREIGN KEY (FriendID) REFERENCES Users (UserID);

ALTER TABLE ConversationMembers ADD FOREIGN KEY (ConversationID) REFERENCES Conversation (ConversationID);

ALTER TABLE ConversationMembers ADD FOREIGN KEY (UserID) REFERENCES Users (UserID);

ALTER TABLE Groups ADD FOREIGN KEY (GroupID) REFERENCES Conversation (ConversationID);

ALTER TABLE Groups ADD FOREIGN KEY (Owner) REFERENCES Users (UserID);

ALTER TABLE Message ADD FOREIGN KEY (SenderID) REFERENCES Users (UserID);

ALTER TABLE Message ADD FOREIGN KEY (ConversationID) REFERENCES Conversation (ConversationID);

ALTER TABLE Post ADD FOREIGN KEY (UserID) REFERENCES Users (UserID);

ALTER TABLE PostReaction ADD FOREIGN KEY (UserID) REFERENCES Users (UserID);

ALTER TABLE PostReaction ADD FOREIGN KEY (PostID) REFERENCES Post (PostID);

ALTER TABLE Report ADD FOREIGN KEY (UserID) REFERENCES Users (UserID);

ALTER TABLE Report ADD FOREIGN KEY (PostID) REFERENCES Post (PostID);

ALTER TABLE DeletedPost ADD FOREIGN KEY (PostID) REFERENCES Post (PostID);

ALTER TABLE DeletedPost ADD FOREIGN KEY (AdminID) REFERENCES Admin (AdminID);

ALTER TABLE BannedUser ADD FOREIGN KEY (UserID) REFERENCES Users (UserID);

ALTER TABLE BannedUser ADD FOREIGN KEY (AdminID) REFERENCES Admin (AdminID);

INSERT INTO Admin (AdminID, AdminPassword, AdminEmail)
VALUES (0,'admin1234','admin@gmail.com');