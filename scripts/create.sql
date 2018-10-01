DROP TABLE IF EXISTS images;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS wishes;
DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS content;
DROP TABLE IF EXISTS feedback;
DROP TABLE IF EXISTS ratings;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS users;



-- -----------------------------------------------------
-- Table public.users
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS public.users (
  id SERIAL NOT NULL,
  login VARCHAR(64) NOT NULL,
  name VARCHAR(64) NOT NULL,
  surname VARCHAR(64) NOT NULL,
  email VARCHAR(320) NOT NULL, --64 characters for the "local part" (username). 1 character for the @ symbol. 255 characters for the domain name.
  password VARCHAR(128) NOT NULL,
  birth DATE NULL,
  reg_date DATE DEFAULT CURRENT_DATE,
  phone VARCHAR(16) NULL,
  sex BOOLEAN NULL,
  image VARCHAR(2083) NULL,
  notiffication_sett BOOLEAN NOT NULL DEFAULT TRUE,

  CONSTRAINT pk_users_id PRIMARY KEY (id),
  CONSTRAINT uk_users_login UNIQUE (login),
  CONSTRAINT uk_users_email UNIQUE (email),
  CONSTRAINT uk_users_phone UNIQUE (phone)

	);

-- -----------------------------------------------------
-- Table public.categories
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS public.categories (
    id SERIAL NOT NULL ,
    name VARCHAR(45) NOT NULL,

    CONSTRAINT pk_categories_id PRIMARY KEY (id),
    CONSTRAINT uk_categories_name UNIQUE(name)

);


-- -----------------------------------------------------
-- Table public.products
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS public.products (
  id SERIAL NOT NULL,         --PK
  owner_id INT NOT NULL,      --FK
  category_id INT NULL,       --FK
  name VARCHAR(60) NOT NULL,
  description TEXT NOT NULL,
  start_date TIMESTAMP NOT NULL,
  finish_date TIMESTAMP NOT NULL,
  start_price REAL NOT NULL,
  finish_price REAL NOT NULL,
  views INT DEFAULT 0,

  CONSTRAINT pk_products_id PRIMARY KEY (id),

  CONSTRAINT fk_products_owner FOREIGN KEY (owner_id)
    REFERENCES public.users (id),

  CONSTRAINT fk_products_category FOREIGN KEY (category_id)
    REFERENCES public.categories (id)
);


-- -----------------------------------------------------
-- Table public.ratings
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS public.ratings (
  owner_id INT NOT NULL,
  rater_id INT NOT NULL,
  grade SMALLINT NOT NULL,

  CONSTRAINT pk_ratings_users_id PRIMARY KEY (owner_id, rater_id),

  CONSTRAINT fk_ratings_owner_id FOREIGN KEY (owner_id)
    REFERENCES public.users (id),

  CONSTRAINT fk_ratings_rater_id FOREIGN KEY (rater_id)
    REFERENCES public.users (id),

  CONSTRAINT ck_ratings_grade CHECK (grade >= 0 AND grade <=5)
  );


-- -----------------------------------------------------
-- Table public.feedback
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS public.feedback (
  owner_id INT NOT NULL,
  creator_id INT NOT NULL,
  content TEXT NOT NULL,

  CONSTRAINT pk_feedback_users_id PRIMARY KEY (owner_id, creator_id),

  CONSTRAINT fk_feedback_owner_id FOREIGN KEY (owner_id)
    REFERENCES public.users (id),

  CONSTRAINT fk_feedback_creator_id FOREIGN KEY (creator_id)
    REFERENCES public.users (id)
  );

-- -----------------------------------------------------
-- Table public.content
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS public.content (
  id SERIAL NOT NULL,
  date TIMESTAMP NOT NULL DEFAULT current_timestamp,
  text TEXT NOT NULL,
  is_read BOOLEAN NOT NULL DEFAULT false,

  CONSTRAINT pk_content_id PRIMARY KEY (id)
  );

-- -----------------------------------------------------
-- Table public.messages
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS public.messages (
  sender_id INT NOT NULL,
  receiver_id INT NOT NULL,
  content_id INT NOT NULL,

  CONSTRAINT pk_messages_id PRIMARY KEY (sender_id, receiver_id, content_id),

  CONSTRAINT fk_messages_sender_id FOREIGN KEY (sender_id)
    REFERENCES public.users (id),

  CONSTRAINT fk_messages_receiver_id FOREIGN KEY (receiver_id)
    REFERENCES public.users (id),

  CONSTRAINT fk_messages_content_id FOREIGN KEY (content_id)
    REFERENCES public.content (id)
  );


-- -----------------------------------------------------
-- Table public.wishes
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS public.wishes (
  user_id INT NOT NULL,
  product_id INT NOT NULL,

  CONSTRAINT pk_wishes_id PRIMARY KEY (user_id, product_id),

  CONSTRAINT fk_wishes_user_id FOREIGN KEY (user_id)
    REFERENCES public.users (id),

  CONSTRAINT fk_wishes_product_id FOREIGN KEY (product_id)
    REFERENCES public.products (id)
  );


-- -----------------------------------------------------
-- Table public.orders
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS public.orders (
  id SERIAL NOT NULL,
  user_id INT NOT NULL,
  product_id INT NOT NULL,
  date TIMESTAMP NOT NULL DEFAULT current_timestamp,
  price INT NOT NULL,
  status VARCHAR(20) NOT NULL,

  CONSTRAINT pk_orders_id PRIMARY KEY (id),

  CONSTRAINT uk_orders_product_id UNIQUE (product_id),

  CONSTRAINT fk_orders_user_id FOREIGN KEY (user_id)
    REFERENCES public.users (id),

  CONSTRAINT fk_orders_product_id FOREIGN KEY (product_id)
    REFERENCES public.products (id)
  );

-- -----------------------------------------------------
-- Table public.images
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS public.images (
  id SERIAL NOT NULL,
  image VARCHAR(2083) NOT NULL,
  product_id INT NOT NULL,

  CONSTRAINT pk_item_images_itemId_imageId PRIMARY KEY (id),

  CONSTRAINT fk_images_product_id FOREIGN KEY (product_id)
    REFERENCES public.products (id)
  );
