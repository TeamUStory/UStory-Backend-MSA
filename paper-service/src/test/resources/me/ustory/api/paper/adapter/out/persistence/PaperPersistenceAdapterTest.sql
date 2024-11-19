insert into diary_info_entity (id, name, image_url, color, marker_url, created_at, updated_at)
values (1, '다이어리', 'https://example.com/다이어리이미지.gif', '#00000', 'https://example.com/마크업이미지.png', '2024-11-18 00:00:00', '2024-11-18 00:00:00');

insert into paper_entity (id, writer_id, diary_info_id, title, thumbnail_image_url, store, visited_at, deleted_at, is_locked, created_at, updated_at)
values (1, 1, 1, '제목1', 'https://example.com/썸네일이미지.png', '상호명1', '2024-11-18', null, false, '2024-11-18 12:00:00', '2024-11-18 12:00:00');
insert into paper_entity (id, writer_id, diary_info_id, title, thumbnail_image_url, store, visited_at, deleted_at, is_locked, created_at, updated_at)
values (2, 1, 1, '제목2', 'https://example.com/썸네일이미지2.png', '상호명2', '2024-11-18', null, false, '2024-11-18 18:00:00', '2024-11-18 18:00:00');
insert into paper_entity (id, writer_id, diary_info_id, title, thumbnail_image_url, store, visited_at, deleted_at, is_locked, created_at, updated_at)
values (3, 1, 1, '제목3', 'https://example.com/썸네일이미지3.png', '상호명3', '2024-11-19', null, false, '2024-11-19 18:00:00', '2024-11-19 18:00:00');