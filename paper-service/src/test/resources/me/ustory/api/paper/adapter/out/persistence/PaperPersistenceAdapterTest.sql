insert into diary_info_entity (id, name, image_url, color, marker_url, created_at, updated_at)
values (1, '다이어리', 'https://example.com/다이어리이미지.gif', '#000000', 'https://example.com/마크업이미지.png', '2024-11-18 00:00:00', '2024-11-18 00:00:00');

insert into paper_entity (id, writer_id, diary_info_id, title, thumbnail_image_url, store, visited_at, deleted_at, is_locked, created_at, updated_at)
values (1, 1, 1, '제목1', 'https://example.com/썸네일이미지.png', '상호명1', '2024-11-18', null, false, '2024-11-18 12:00:00', '2024-11-18 12:00:00');
insert into paper_entity (id, writer_id, diary_info_id, title, thumbnail_image_url, store, visited_at, deleted_at, is_locked, created_at, updated_at)
values (2, 1, 1, '제목2', 'https://example.com/썸네일이미지2.png', '상호명2', '2024-11-18', null, false, '2024-11-18 18:00:00', '2024-11-18 18:00:00');
insert into paper_entity (id, writer_id, diary_info_id, title, thumbnail_image_url, store, visited_at, deleted_at, is_locked, created_at, updated_at)
values (3, 1, 1, '제목3', 'https://example.com/썸네일이미지3.png', '상호명3', '2024-11-19', null, false, '2024-11-19 18:00:00', '2024-11-19 18:00:00');

insert into paper_detail_entity (id, city, coordinate_x, coordinate_y, created_at, updated_at)
values (1, '주소1', 34.1234, 128.1234, '2024-11-18 12:00:00', '2024-11-18 12:00:00');
insert into paper_detail_entity (id, city, coordinate_x, coordinate_y, created_at, updated_at)
values (2, '주소2', 34.1321, 128.1324, '2024-11-18 18:00:00', '2024-11-18 18:00:00');
insert into paper_detail_entity (id, city, coordinate_x, coordinate_y, created_at, updated_at)
values (3, '주소3', 34.1423, 128.1434, '2024-11-19 18:00:00', '2024-11-19 18:00:00');

insert into images_entity (paper_detail_id, image_url)
values (1, 'https://www.example.com/이미지1.png');
insert into images_entity (paper_detail_id, image_url)
values (1, 'https://www.example.com/이미지2.png');
insert into images_entity (paper_detail_id, image_url)
values (2, 'https://www.example.com/이미지3.png');
insert into images_entity (paper_detail_id, image_url)
values (2, 'https://www.example.com/이미지4.png');
insert into images_entity (paper_detail_id, image_url)
values (3, 'https://www.example.com/이미지5.png');
insert into images_entity (paper_detail_id, image_url)
values (3, 'https://www.example.com/이미지6.png');
insert into images_entity (paper_detail_id, image_url)
values (3, 'https://www.example.com/이미지7.png');

insert into members_info_entity (diary_id, member_id)
values (1, 1);