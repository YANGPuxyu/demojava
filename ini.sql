-- 允许 root 用户从任何主机连接
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '123456';
USE mysql;

-- 更新 root 用户允许从任何主机（%）连接
UPDATE user SET Host = '%' WHERE User = 'root';

-- 授予 root 用户所有权限
UPDATE user SET Grant_priv = 'Y', Super_priv = 'Y' WHERE User = 'root';

-- 刷新权限
FLUSH PRIVILEGES;

-- -- 插入聊天室数据
-- INSERT INTO `chat_rooms` (`course_id`, `name`, `created_at`, `updated_at`)
-- VALUES
--     (1, 'Java基础课程聊天室', NOW(), NOW()),
--     (2, 'Python数据分析聊天室', NOW(), NOW()),
--     (3, 'Web开发基础聊天室', NOW(), NOW()),
--     (4, '数据库系统聊天室', NOW(), NOW()),
--     (5, '算法与数据结构聊天室', NOW(), NOW()),
--     (6, '操作系统原理聊天室', NOW(), NOW()),
--     (7, '网络安全基础聊天室', NOW(), NOW()),
--     (8, '人工智能导论聊天室', NOW(), NOW()),
--     (9, '数据挖掘与机器学习聊天室', NOW(), NOW()),
--     (10, '区块链技术聊天室', NOW(), NOW());
--
--
-- -- 插入测试数据到 chat_room_members 表
-- INSERT INTO chat_room_members (id, chat_room_id, user_id, joined_at, is_active)
-- VALUES
-- (1, 1, 1001, NOW(), TRUE),
-- (2, 1, 1002, NOW(), TRUE),
-- (3, 2, 1003, NOW(), TRUE),
-- (4, 2, 1004, NOW(), FALSE);
--
-- -- 插入测试数据到 messages 表
-- INSERT INTO messages (id, chat_room_id, user_id, content, message_type, created_at)
-- VALUES
-- (1, 1, 1001, '大家好，欢迎加入Java学习小组！', 'text', NOW()),
-- (2, 1, 1002, '请问有Java基础教程推荐吗？', 'text', NOW()),
-- (3, 2, 1003, '今天的算法课题是动态规划！', 'text', NOW());
--
-- -- 插入测试数据到 attachments 表
-- INSERT INTO attachments (id, message_id, file_name, file_type, file_url, uploaded_at)
-- VALUES
-- (1, 3, '动态规划示例.pdf', 'document', 'https://example.com/files/dp_example.pdf', NOW());
--
-- -- 插入测试数据到 message_notifications 表
-- INSERT INTO message_notifications (id, user_id, message_id, is_read, notified_at)
-- VALUES
-- (1, 1001, 1, FALSE, NOW()),
-- (2, 1002, 2, TRUE, NOW()),
-- (3, 1003, 3, FALSE, NOW());
--
-- INSERT INTO users (id, username, email, password, created_at, updated_at)
-- VALUES
-- (1001, 'alice', 'alice@example.com', 'password123', NOW(), NOW()),
-- (1002, 'bob', 'bob@example.com', 'password456', NOW(), NOW()),
-- (1003, 'carol', 'carol@example.com', 'password789', NOW(), NOW()),
-- (1004, 'dave', 'dave@example.com', 'password012', NOW(), NOW());
--
-- INSERT INTO `courses` (`name`, `description`, `price`, `chat_room_id`, `credits`, `start_date`, `end_date`, `exam_date`, `has_exam`, `location`, `teacher_name`, `teaching_sessions`, `created_at`, `updated_at`)
-- VALUES
--     ('Java基础课程', '学习Java基础知识', 199.99, 1, 3, '2024-01-01 09:00:00', '2024-06-01 09:00:00', '2024-06-01 09:00:00', 1, '第一教室', '张三', 36, NOW(), NOW()),
--     ('Python数据分析', '深入学习Python用于数据分析', 299.99, 2, 4, '2024-02-01 09:00:00', '2024-07-01 09:00:00', '2024-07-01 09:00:00', 1, '第二教室', '李四', 40, NOW(), NOW()),
--     ('Web开发基础', '学习HTML、CSS和JavaScript', 149.99, 3, 2, '2024-03-01 09:00:00', '2024-08-01 09:00:00', '2024-08-01 09:00:00', 0, '第三教室', '王五', 30, NOW(), NOW()),
--     ('数据库系统', '深入学习关系型数据库与SQL', 249.99, 4, 3, '2024-04-01 09:00:00', '2024-09-01 09:00:00', '2024-09-01 09:00:00', 1, '第四教室', '赵六', 45, NOW(), NOW()),
--     ('算法与数据结构', '学习常见的算法与数据结构', 299.99, 5, 5, '2024-05-01 09:00:00', '2024-10-01 09:00:00', '2024-10-01 09:00:00', 1, '第五教室', '钱七', 50, NOW(), NOW()),
--     ('操作系统原理', '深入理解操作系统的原理与实现', 199.99, 6, 3, '2024-06-01 09:00:00', '2024-11-01 09:00:00', '2024-11-01 09:00:00', 0, '第六教室', '孙八', 42, NOW(), NOW()),
--     ('网络安全基础', '学习网络安全的基本概念', 349.99, 7, 4, '2024-07-01 09:00:00', '2024-12-01 09:00:00', '2024-12-01 09:00:00', 1, '第七教室', '周九', 38, NOW(), NOW()),
--     ('人工智能导论', '介绍人工智能的基本概念与应用', 399.99, 8, 4, '2024-08-01 09:00:00', '2024-12-31 09:00:00', '2024-12-31 09:00:00', 1, '第八教室', '吴十', 48, NOW(), NOW()),
--     ('数据挖掘与机器学习', '探索数据挖掘与机器学习技术', 399.99, 9, 5, '2024-09-01 09:00:00', '2025-02-01 09:00:00', '2025-02-01 09:00:00', 0, '第九教室', '郑十一', 55, NOW(), NOW()),
--     ('区块链技术', '深入了解区块链技术及应用', 499.99, 10, 5, '2024-10-01 09:00:00', '2025-03-01 09:00:00', '2025-03-01 09:00:00', 1, '第十教室', '冯十二', 60, NOW(), NOW());
--
