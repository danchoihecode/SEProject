CREATE TABLE "User" (
  "UserID" int PRIMARY KEY,
  "UserFullName" varchar NOT NULL,
  "UserNickName" varchar NOT NULL,
  "UserAvatar" bytea,
  "UserEmail" varchar UNIQUE NOT NULL,
  "UserPassword" varchar NOT NULL
);

CREATE TABLE "Friends" (
  "UserID" int,
  "FriendID" int
);

CREATE TABLE "Conversation" (
  "ConversationID" int PRIMARY KEY
);

CREATE TABLE "ConversationMembers" (
  "ConversationID" int,
  "UserID" int,
  "HasRead" bool NOT NULL DEFAULT false
);

CREATE TABLE "Group" (
  "GroupID" int PRIMARY KEY,
  "GroupName" varchar NOT NULL,
  "GroupAvatar" bytea,
  "Owner" int NOT NULL
);

CREATE TABLE "GroupMembers" (
  "UserID" int,
  "GroupID" int,
  "HasRead" bool NOT NULL DEFAULT false
);

CREATE TABLE "Message" (
  "MessageID" int PRIMARY KEY,
  "MessageFile" bytea,
  "MessageContent" text,
  "MessageImage" bytea,
  "MessageDate" timestamp NOT NULL DEFAULT (now()),
  "SenderID" int NOT NULL,
  "ConversationID" int,
  "GroupID" int
);

CREATE TABLE "Post" (
  "PostID" int PRIMARY KEY,
  "PostImage" bytea,
  "PostText" text,
  "PostDate" timestamp NOT NULL DEFAULT (now()),
  "Like" int DEFAULT 0,
  "UserID" int NOT NULL
);

CREATE TABLE "PostReaction" (
  "UserID" int,
  "PostID" int
);

CREATE TABLE "Report" (
  "ReportID" int PRIMARY KEY,
  "ReportReason" varchar NOT NULL,
  "UserID" int,
  "PostID" int
);

CREATE TABLE "Admin" (
  "AdminID" int PRIMARY KEY,
  "AdminPassword" varchar NOT NULL,
  "AdminEmail" varchar UNIQUE NOT NULL
);

CREATE TABLE "DeletedPost" (
  "DeleteReason" varchar NOT NULL,
  "PostID" int,
  "AdminID" int
);

CREATE TABLE "BannedUser" (
  "BanReason" varchar NOT NULL,
  "Duration" int NOT NULL,
  "UserID" int,
  "AdminID" int
);

ALTER TABLE "Friends" ADD FOREIGN KEY ("UserID") REFERENCES "User" ("UserID");

ALTER TABLE "Friends" ADD FOREIGN KEY ("FriendID") REFERENCES "User" ("UserID");

ALTER TABLE "ConversationMembers" ADD FOREIGN KEY ("ConversationID") REFERENCES "Conversation" ("ConversationID");

ALTER TABLE "ConversationMembers" ADD FOREIGN KEY ("UserID") REFERENCES "User" ("UserID");

ALTER TABLE "Group" ADD FOREIGN KEY ("Owner") REFERENCES "User" ("UserID");

ALTER TABLE "GroupMembers" ADD FOREIGN KEY ("UserID") REFERENCES "User" ("UserID");

ALTER TABLE "GroupMembers" ADD FOREIGN KEY ("GroupID") REFERENCES "Group" ("GroupID");

ALTER TABLE "Message" ADD FOREIGN KEY ("SenderID") REFERENCES "User" ("UserID");

ALTER TABLE "Message" ADD FOREIGN KEY ("ConversationID") REFERENCES "Conversation" ("ConversationID");

ALTER TABLE "Message" ADD FOREIGN KEY ("GroupID") REFERENCES "Group" ("GroupID");

ALTER TABLE "Post" ADD FOREIGN KEY ("UserID") REFERENCES "User" ("UserID");

ALTER TABLE "PostReaction" ADD FOREIGN KEY ("UserID") REFERENCES "User" ("UserID");

ALTER TABLE "PostReaction" ADD FOREIGN KEY ("PostID") REFERENCES "Post" ("PostID");

ALTER TABLE "Report" ADD FOREIGN KEY ("UserID") REFERENCES "User" ("UserID");

ALTER TABLE "Report" ADD FOREIGN KEY ("PostID") REFERENCES "Post" ("PostID");

ALTER TABLE "DeletedPost" ADD FOREIGN KEY ("PostID") REFERENCES "Post" ("PostID");

ALTER TABLE "DeletedPost" ADD FOREIGN KEY ("AdminID") REFERENCES "Admin" ("AdminID");

ALTER TABLE "BannedUser" ADD FOREIGN KEY ("UserID") REFERENCES "User" ("UserID");

ALTER TABLE "BannedUser" ADD FOREIGN KEY ("AdminID") REFERENCES "Admin" ("AdminID");
