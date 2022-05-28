create table category (
  id varchar(255) not null, 
  create_at timestamp, 
  create_by varchar(100), 
  last_modified_at timestamp, 
  last_modified_by varchar(100), 
  version int4 not null, 
  name varchar(100) not null, 
  primary key (id)
);
create table ecom_user (
  id varchar(255) not null, 
  create_at timestamp, 
  create_by varchar(100), 
  last_modified_at timestamp, 
  last_modified_by varchar(100), 
  version int4 not null, 
  address varchar(255), 
  delete boolean, 
  email varchar(100), 
  facebook varchar(100), 
  first_name varchar(100), 
  full_name varchar(100), 
  last_name varchar(100), 
  password varchar(100) not null, 
  phone_number varchar(15), 
  provider varchar(50), 
  status varchar(50), 
  username varchar(100) not null unique, 
  primary key (id)
);
create table group_with_role (
  group_id varchar(255) not null, 
  role_id varchar(255) not null, 
  primary key (group_id, role_id)
);
create table product (
  id varchar(255) not null, 
  create_at timestamp, 
  create_by varchar(100), 
  last_modified_at timestamp, 
  last_modified_by varchar(100), 
  version int4 not null, 
  brand_new boolean not null, 
  category varchar(100) not null, 
  description varchar(255) not null, 
  discount float4 not null, 
  images bytea, 
  name varchar(100) not null, 
  price float8 default 0 not null, 
  quantity int2 not null, 
  rate int2 not null, 
  review_count int2 not null, 
  sex varchar(20) not null, 
  slug varchar(100) not null, 
  sub_category varchar(100) not null, 
  thumb_image bytea, 
  subcategory_id varchar(255), 
  primary key (id)
);
create table product_product_size (
  size_id varchar(255) not null, 
  product_id varchar(255) not null, 
  primary key (size_id, product_id)
);
create table product_size (
  id varchar(255) not null, 
  create_at timestamp, 
  create_by varchar(100), 
  last_modified_at timestamp, 
  last_modified_by varchar(100), 
  version int4 not null, 
  name varchar(100) not null, 
  primary key (id)
);
create table role_with_program (
  program_id varchar(255) not null, 
  role_id varchar(255) not null, 
  primary key (program_id, role_id)
);
create table sub_category (
  id varchar(255) not null, 
  create_at timestamp, 
  create_by varchar(100), 
  last_modified_at timestamp, 
  last_modified_by varchar(100), 
  version int4 not null, 
  name varchar(100) not null, 
  category_id varchar(255), 
  primary key (id)
);
create table user_group (
  id varchar(255) not null, 
  create_at timestamp, 
  create_by varchar(100), 
  last_modified_at timestamp, 
  last_modified_by varchar(100), 
  version int4 not null, 
  description varchar(255) not null, 
  name varchar(100) not null unique, 
  primary key (id)
);
create table user_program (
  id varchar(255) not null, 
  create_at timestamp, 
  create_by varchar(100), 
  last_modified_at timestamp, 
  last_modified_by varchar(100), 
  version int4 not null, 
  description varchar(255), 
  module varchar(50), 
  name varchar(100) unique, 
  type varchar(50), 
  primary key (id)
);
create table user_role (
  id varchar(255) not null, 
  create_at timestamp, 
  create_by varchar(100), 
  last_modified_at timestamp, 
  last_modified_by varchar(100), 
  version int4 not null, 
  description varchar(255) not null, 
  name varchar(100) not null unique, 
  primary key (id)
);
create table user_with_group (
  user_id varchar(255) not null, 
  group_id varchar(255) not null, 
  primary key (user_id, group_id)
);

alter table group_with_role add constraint fk_group_role_role foreign key (role_id) references user_role;
alter table group_with_role add constraint fk_group_role_group foreign key (group_id) references user_group;
alter table product add constraint fk_product_subcategory foreign key (subcategory_id) references sub_category;
alter table product_product_size add constraint fk_produc_size_product foreign key (product_id) references product;
alter table product_product_size add constraint fk_produc_size_size foreign key (size_id) references product_size;
alter table role_with_program add constraint fk_role_program_program foreign key (role_id) references user_program;
alter table role_with_program add constraint fk_role_program_role foreign key (program_id) references user_role;
alter table sub_category add constraint fk_category_subcategory foreign key (category_id) references category;
alter table user_with_group add constraint fk_user_group_user foreign key (group_id) references ecom_user;
alter table user_with_group add constraint fk_user_group_group foreign key (user_id) references user_group;