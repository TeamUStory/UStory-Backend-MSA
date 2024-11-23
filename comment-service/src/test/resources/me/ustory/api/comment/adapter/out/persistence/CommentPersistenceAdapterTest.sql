insert into member_info_entity(id, nickname, profile_image, created_at, updated_at)
values (1, '닉네임', 'https://www.example.com/프로필.png', '2024-11-18 11:00:00', '2024-11-18 11:00:00');

insert into comment_entity (id, member_info_id, paper_id, content, created_at, updated_at)
values (1, 1, 1, '댓글1', '2024-11-18 12:00:00', '2024-11-18 12:00:00');
