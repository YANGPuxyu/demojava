-- 插入测试数据到 chat_rooms 表
INSERT INTO chat_rooms (id, name, course_id, created_at, updated_at)
VALUES
(1, 'Java学习小组', 101, NOW(), NOW()),
(2, '算法研究室', 102, NOW(), NOW());

-- 插入测试数据到 chat_room_members 表
INSERT INTO chat_room_members (id, chat_room_id, user_id, joined_at, is_active)
VALUES
(1, 1, 1001, NOW(), TRUE),
(2, 1, 1002, NOW(), TRUE),
(3, 2, 1003, NOW(), TRUE),
(4, 2, 1004, NOW(), FALSE);

-- 插入测试数据到 messages 表
INSERT INTO messages (id, chat_room_id, user_id, content, message_type, created_at)
VALUES
(1, 1, 1001, '大家好，欢迎加入Java学习小组！', 'text', NOW()),
(2, 1, 1002, '请问有Java基础教程推荐吗？', 'text', NOW()),
(3, 2, 1003, '今天的算法课题是动态规划！', 'text', NOW());

-- 插入测试数据到 attachments 表
INSERT INTO attachments (id, message_id, file_name, file_type, file_url, uploaded_at)
VALUES
(1, 3, '动态规划示例.pdf', 'document', 'https://example.com/files/dp_example.pdf', NOW());

-- 插入测试数据到 message_notifications 表
INSERT INTO message_notifications (id, user_id, message_id, is_read, notified_at)
VALUES
(1, 1001, 1, FALSE, NOW()),
(2, 1002, 2, TRUE, NOW()),
(3, 1003, 3, FALSE, NOW());

INSERT INTO users (id, username, email, password, created_at, updated_at)
VALUES
(1001, 'alice', 'alice@example.com', 'password123', NOW(), NOW()),
(1002, 'bob', 'bob@example.com', 'password456', NOW(), NOW()),
(1003, 'carol', 'carol@example.com', 'password789', NOW(), NOW()),
(1004, 'dave', 'dave@example.com', 'password012', NOW(), NOW());
