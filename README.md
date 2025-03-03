# SEProject

## Front-end 

## Back-end
### how to initialize database
#### option 1: sử dụng docker
- tải docker-desktop về bật lên (khuyên config processor với memory trước không thì nặng máy)
- tạo file .env cùng folder với compose.yaml
- edit file .env theo các trường sau
  ```bash
    POSTGRES_DB=chatdb
    POSTGRES_PASSWORD=hustICT
    POSTGRES_USER=group15
  ```
- chạy cmd docker-compose up 
#### option 2: sử dụng postgres database thật từ pgadmin4 
- cài đặt rồi tạo database bằng GUI cho cùng tên db user với password như trong file .env
- rồi chạy file sql có sẵn
