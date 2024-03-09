create table configs
(
    name  varchar(255) not null,
    value varchar(255) not null
);


create table users
(
    id      int auto_increment primary key,
    api_key varchar(255) not null
);


create table channel_subscriptions
(
    id                    int auto_increment primary key,
    name                  varchar(255) not null,
    active                boolean      not null,
    live_monitored        boolean      not null,
    telegram_channel_id   bigint       not null,
    telegram_channel_link varchar(255) not null
);


create table post_updates
(
    channel_subscription_id int       not null,
    telegram_post_id        int       not null,
    fetched_at              timestamp not null,
    comments_count          int       not null,
    reactions_count         int       not null,
    reactions               json      not null,
    media_count             int       not null,
    media                   json      not null,
    foreign key (channel_subscription_id) references channel_subscriptions (id)
);

create index idx_channel_subscription_id_fetched_at on post_updates (channel_subscription_id, fetched_at);