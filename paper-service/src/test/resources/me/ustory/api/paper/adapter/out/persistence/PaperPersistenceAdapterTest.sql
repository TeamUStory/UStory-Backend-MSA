insert into image_entity(id, url, created_at, updated_at)
values (1, 'https://www.example.com/썸네일이미지.png', '2024-11-18 12:00:00', '2024-11-18 12:00:00');
insert into image_entity(id, url, created_at, updated_at)
values (2, 'https://www.example.com/썸네일이미지2.png', '2024-11-18 18:00:00', '2024-11-18 18:00:00');
insert into image_entity(id, url, created_at, updated_at)
values (3, 'https://www.example.com/썸네일이미지3.png', '2024-11-19 18:00:00', '2024-11-19 18:00:00');
insert into image_entity (id, url, created_at, updated_at)
values (11, 'https://example.com/다이어리이미지.gif', '2024-11-19 18:00:00', '2024-11-19 18:00:00');
insert into image_entity (id, url, created_at, updated_at)
values (12, 'https://example.com/마크업이미지.png', '2024-11-19 18:00:00', '2024-11-19 18:00:00');

insert into diary_info_entity (id, name, image_id, color, marker_id, created_at, updated_at)
values (1, '다이어리', 11, '#000000', 12, '2024-11-18 00:00:00', '2024-11-18 00:00:00');


insert into paper_entity (id, writer_id, diary_info_id, title, thumbnail_id, store, visited_at, deleted_at, is_locked,
                          created_at, updated_at)
values (1, 1, 1, '제목1', 1, '상호명1', '2024-11-18', null, false, '2024-11-18 12:00:00', '2024-11-18 12:00:00');
insert into paper_entity (id, writer_id, diary_info_id, title, thumbnail_id, store, visited_at, deleted_at, is_locked,
                          created_at, updated_at)
values (2, 1, 1, '제목2', 2, '상호명2', '2024-11-18', null, false, '2024-11-18 18:00:00', '2024-11-18 18:00:00');
insert into paper_entity (id, writer_id, diary_info_id, title, thumbnail_id, store, visited_at, deleted_at, is_locked,
                          created_at, updated_at)
values (3, 1, 1, '제목3', 3, '상호명3', '2024-11-19', null, false, '2024-11-19 18:00:00', '2024-11-19 18:00:00');

insert into paper_detail_entity (id, city, coordinate_x, coordinate_y, images, created_at, updated_at)
values (1, '주소1', 34.1234, 128.1234, 'https://www.example.com/이미지1.png,https://www.example.com/이미지2.png','2024-11-18 12:00:00', '2024-11-18 12:00:00');
insert into paper_detail_entity (id, city, coordinate_x, coordinate_y, images, created_at, updated_at)
values (2, '주소2', 34.1321, 128.1324, 'https://www.example.com/이미지3.png,https://www.example.com/이미지4.png','2024-11-18 18:00:00', '2024-11-18 18:00:00');
insert into paper_detail_entity (id, city, coordinate_x, coordinate_y, images, created_at, updated_at)
values (3, '주소3', 34.1423, 128.1434, 'https://www.example.com/이미지5.png,https://www.example.com/이미지6.png,https://www.example.com/이미지7.png','2024-11-19 18:00:00', '2024-11-19 18:00:00');

insert into members_info_entity (diary_id, member_id)
values (1, 1);