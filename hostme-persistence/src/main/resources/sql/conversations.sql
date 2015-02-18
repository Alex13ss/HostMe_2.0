TRUNCATE conversations CASCADE;
INSERT INTO "conversations" (title,created_at,group_id,owner_id) VALUES ('English speakers, join here!','2015-09-20 15:15:54',1,1),('Weather in Lviv','2015-07-14 20:11:59',1,1),('Seeking a guide in Lviv','2015-12-08 12:16:29',1,6);

TRUNCATE conversations_moderators CASCADE;
INSERT INTO "conversations_moderators" (conversation_id,moderator_id) VALUES (2,6);

TRUNCATE posts CASCADE;
INSERT INTO "posts" (content,postedAt,author_id,conversation_id) VALUES ('Hello i am looking for person who can teach me English!','2015-02-10 10:15:54',1,1),('Hi! I can help you!','2015-02-10 16:11:59',2,1),('Nice, thx! I will write you an e-mail','2015-02-10 17:16:29',1,1);
INSERT INTO "posts" (content,postedAt,author_id,conversation_id) VALUES ('Hi what do you think about weather today?','2015-02-15 09:16:41',1,2),('Hi! this snow makes me angry...','2015-02-15 10:11:59',2,2),('Snow makes you angry? lol))','2015-02-15 11:13:29',6,2),('I like snow. It is the best weather for me!','2015-02-15 17:06:33',3,2);
INSERT INTO "posts" (content,postedAt,author_id,conversation_id) VALUES ('Hi can somebody show me interesting places in Lviv?','2015-02-16 09:16:41',6,3),('Hi! I can show you some places','2015-02-16 10:10:53',2,3),('We can meet next week and i will help you','2015-02-16 16:13:29',1,3),('Hi! Tomorrow we can go all together if you want?','2015-02-16 17:06:33',3,3),('Sure, it will be cool!','2015-02-16 17:08:43',2,3);
