# 상담원+챗봇 상담 서비스

헬프데스크와 바로 커뮤니케이션 할 수 있는, 채팅 플랫폼의 서버 코드. 상담원 채팅 기능, 룰 베이스 기반의 챗봇을 정의 기능, 기타 일반 기능 (e.g. 사용자 등록, 연계시스템 관리, 태그 관리 등)

## Project Statck

- Spring boot
- Spring security
- JPA
- Mapstruct
- jwt
- redis
- postgreSQL
- stomp
- Kafka

## API 정의

- room
  - 채팅방 생성
  - 채팅방 리스트 조회
  - 채팅방 단일 조회
  - 채팅방 상태 수정
- message
  - 메시지 생성
  - 메시지 리스트 조회 (roomId)
- user
  - 사용자 로그인
  - 사용자 로그아웃
  - 사용자 등록
- legacy
- role
- tag
- .....
